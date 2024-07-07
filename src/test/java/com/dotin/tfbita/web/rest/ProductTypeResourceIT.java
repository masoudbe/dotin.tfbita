package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.ProductTypeAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.ProductType;
import com.dotin.tfbita.repository.ProductTypeRepository;
import com.dotin.tfbita.service.ProductTypeService;
import com.dotin.tfbita.service.dto.ProductTypeDTO;
import com.dotin.tfbita.service.mapper.ProductTypeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProductTypeResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ProductTypeResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFICATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFICATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_TOPIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TOPIC_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/product-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Mock
    private ProductTypeRepository productTypeRepositoryMock;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Mock
    private ProductTypeService productTypeServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProductTypeMockMvc;

    private ProductType productType;

    private ProductType insertedProductType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductType createEntity(EntityManager em) {
        ProductType productType = new ProductType()
            .description(DEFAULT_DESCRIPTION)
            .modificationDate(DEFAULT_MODIFICATION_DATE)
            .topicCode(DEFAULT_TOPIC_CODE);
        return productType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProductType createUpdatedEntity(EntityManager em) {
        ProductType productType = new ProductType()
            .description(UPDATED_DESCRIPTION)
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .topicCode(UPDATED_TOPIC_CODE);
        return productType;
    }

    @BeforeEach
    public void initTest() {
        productType = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProductType != null) {
            productTypeRepository.delete(insertedProductType);
            insertedProductType = null;
        }
    }

    @Test
    @Transactional
    void createProductType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProductType
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(productType);
        var returnedProductTypeDTO = om.readValue(
            restProductTypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(productTypeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProductTypeDTO.class
        );

        // Validate the ProductType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedProductType = productTypeMapper.toEntity(returnedProductTypeDTO);
        assertProductTypeUpdatableFieldsEquals(returnedProductType, getPersistedProductType(returnedProductType));

        insertedProductType = returnedProductType;
    }

    @Test
    @Transactional
    void createProductTypeWithExistingId() throws Exception {
        // Create the ProductType with an existing ID
        productType.setId(1L);
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(productType);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(productTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProductType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProductTypes() throws Exception {
        // Initialize the database
        insertedProductType = productTypeRepository.saveAndFlush(productType);

        // Get all the productTypeList
        restProductTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productType.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.[*].topicCode").value(hasItem(DEFAULT_TOPIC_CODE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProductTypesWithEagerRelationshipsIsEnabled() throws Exception {
        when(productTypeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProductTypeMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(productTypeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProductTypesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(productTypeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProductTypeMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(productTypeRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getProductType() throws Exception {
        // Initialize the database
        insertedProductType = productTypeRepository.saveAndFlush(productType);

        // Get the productType
        restProductTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, productType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(productType.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE))
            .andExpect(jsonPath("$.topicCode").value(DEFAULT_TOPIC_CODE));
    }

    @Test
    @Transactional
    void getNonExistingProductType() throws Exception {
        // Get the productType
        restProductTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProductType() throws Exception {
        // Initialize the database
        insertedProductType = productTypeRepository.saveAndFlush(productType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the productType
        ProductType updatedProductType = productTypeRepository.findById(productType.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProductType are not directly saved in db
        em.detach(updatedProductType);
        updatedProductType.description(UPDATED_DESCRIPTION).modificationDate(UPDATED_MODIFICATION_DATE).topicCode(UPDATED_TOPIC_CODE);
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(updatedProductType);

        restProductTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(productTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProductType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProductTypeToMatchAllProperties(updatedProductType);
    }

    @Test
    @Transactional
    void putNonExistingProductType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        productType.setId(longCount.incrementAndGet());

        // Create the ProductType
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(productType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, productTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(productTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProductType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        productType.setId(longCount.incrementAndGet());

        // Create the ProductType
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(productType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(productTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProductType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        productType.setId(longCount.incrementAndGet());

        // Create the ProductType
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(productType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductTypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(productTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProductTypeWithPatch() throws Exception {
        // Initialize the database
        insertedProductType = productTypeRepository.saveAndFlush(productType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the productType using partial update
        ProductType partialUpdatedProductType = new ProductType();
        partialUpdatedProductType.setId(productType.getId());

        partialUpdatedProductType.description(UPDATED_DESCRIPTION).modificationDate(UPDATED_MODIFICATION_DATE);

        restProductTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProductType))
            )
            .andExpect(status().isOk());

        // Validate the ProductType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProductTypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProductType, productType),
            getPersistedProductType(productType)
        );
    }

    @Test
    @Transactional
    void fullUpdateProductTypeWithPatch() throws Exception {
        // Initialize the database
        insertedProductType = productTypeRepository.saveAndFlush(productType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the productType using partial update
        ProductType partialUpdatedProductType = new ProductType();
        partialUpdatedProductType.setId(productType.getId());

        partialUpdatedProductType
            .description(UPDATED_DESCRIPTION)
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .topicCode(UPDATED_TOPIC_CODE);

        restProductTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProductType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProductType))
            )
            .andExpect(status().isOk());

        // Validate the ProductType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProductTypeUpdatableFieldsEquals(partialUpdatedProductType, getPersistedProductType(partialUpdatedProductType));
    }

    @Test
    @Transactional
    void patchNonExistingProductType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        productType.setId(longCount.incrementAndGet());

        // Create the ProductType
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(productType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, productTypeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(productTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProductType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        productType.setId(longCount.incrementAndGet());

        // Create the ProductType
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(productType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(productTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProductType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProductType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        productType.setId(longCount.incrementAndGet());

        // Create the ProductType
        ProductTypeDTO productTypeDTO = productTypeMapper.toDto(productType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProductTypeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(productTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProductType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProductType() throws Exception {
        // Initialize the database
        insertedProductType = productTypeRepository.saveAndFlush(productType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the productType
        restProductTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, productType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return productTypeRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected ProductType getPersistedProductType(ProductType productType) {
        return productTypeRepository.findById(productType.getId()).orElseThrow();
    }

    protected void assertPersistedProductTypeToMatchAllProperties(ProductType expectedProductType) {
        assertProductTypeAllPropertiesEquals(expectedProductType, getPersistedProductType(expectedProductType));
    }

    protected void assertPersistedProductTypeToMatchUpdatableProperties(ProductType expectedProductType) {
        assertProductTypeAllUpdatablePropertiesEquals(expectedProductType, getPersistedProductType(expectedProductType));
    }
}
