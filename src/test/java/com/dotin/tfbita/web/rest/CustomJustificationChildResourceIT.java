package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.CustomJustificationChildAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.CustomJustificationChild;
import com.dotin.tfbita.repository.CustomJustificationChildRepository;
import com.dotin.tfbita.service.dto.CustomJustificationChildDTO;
import com.dotin.tfbita.service.mapper.CustomJustificationChildMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CustomJustificationChildResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomJustificationChildResourceIT {

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_TARIFF_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TARIFF_CODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRODUCT_NAME = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRODUCT_NAME = new BigDecimal(2);

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final Long UPDATED_PRODUCT_ID = 2L;

    private static final BigDecimal DEFAULT_BOX_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BOX_COUNT = new BigDecimal(2);

    private static final String DEFAULT_BOX_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BOX_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PURE_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PURE_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_IMPURE_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_IMPURE_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT_CURRENCY = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT_CURRENCY = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/custom-justification-children";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomJustificationChildRepository customJustificationChildRepository;

    @Autowired
    private CustomJustificationChildMapper customJustificationChildMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomJustificationChildMockMvc;

    private CustomJustificationChild customJustificationChild;

    private CustomJustificationChild insertedCustomJustificationChild;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomJustificationChild createEntity(EntityManager em) {
        CustomJustificationChild customJustificationChild = new CustomJustificationChild()
            .item(DEFAULT_ITEM)
            .tariffCode(DEFAULT_TARIFF_CODE)
            .productName(DEFAULT_PRODUCT_NAME)
            .productId(DEFAULT_PRODUCT_ID)
            .boxCount(DEFAULT_BOX_COUNT)
            .boxType(DEFAULT_BOX_TYPE)
            .pureWeight(DEFAULT_PURE_WEIGHT)
            .impureWeight(DEFAULT_IMPURE_WEIGHT)
            .amountCurrency(DEFAULT_AMOUNT_CURRENCY);
        return customJustificationChild;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CustomJustificationChild createUpdatedEntity(EntityManager em) {
        CustomJustificationChild customJustificationChild = new CustomJustificationChild()
            .item(UPDATED_ITEM)
            .tariffCode(UPDATED_TARIFF_CODE)
            .productName(UPDATED_PRODUCT_NAME)
            .productId(UPDATED_PRODUCT_ID)
            .boxCount(UPDATED_BOX_COUNT)
            .boxType(UPDATED_BOX_TYPE)
            .pureWeight(UPDATED_PURE_WEIGHT)
            .impureWeight(UPDATED_IMPURE_WEIGHT)
            .amountCurrency(UPDATED_AMOUNT_CURRENCY);
        return customJustificationChild;
    }

    @BeforeEach
    public void initTest() {
        customJustificationChild = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCustomJustificationChild != null) {
            customJustificationChildRepository.delete(insertedCustomJustificationChild);
            insertedCustomJustificationChild = null;
        }
    }

    @Test
    @Transactional
    void createCustomJustificationChild() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CustomJustificationChild
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(customJustificationChild);
        var returnedCustomJustificationChildDTO = om.readValue(
            restCustomJustificationChildMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customJustificationChildDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CustomJustificationChildDTO.class
        );

        // Validate the CustomJustificationChild in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCustomJustificationChild = customJustificationChildMapper.toEntity(returnedCustomJustificationChildDTO);
        assertCustomJustificationChildUpdatableFieldsEquals(
            returnedCustomJustificationChild,
            getPersistedCustomJustificationChild(returnedCustomJustificationChild)
        );

        insertedCustomJustificationChild = returnedCustomJustificationChild;
    }

    @Test
    @Transactional
    void createCustomJustificationChildWithExistingId() throws Exception {
        // Create the CustomJustificationChild with an existing ID
        customJustificationChild.setId(1L);
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(customJustificationChild);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomJustificationChildMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customJustificationChildDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustificationChild in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomJustificationChildren() throws Exception {
        // Initialize the database
        insertedCustomJustificationChild = customJustificationChildRepository.saveAndFlush(customJustificationChild);

        // Get all the customJustificationChildList
        restCustomJustificationChildMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customJustificationChild.getId().intValue())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].tariffCode").value(hasItem(DEFAULT_TARIFF_CODE)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(sameNumber(DEFAULT_PRODUCT_NAME))))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.intValue())))
            .andExpect(jsonPath("$.[*].boxCount").value(hasItem(sameNumber(DEFAULT_BOX_COUNT))))
            .andExpect(jsonPath("$.[*].boxType").value(hasItem(DEFAULT_BOX_TYPE)))
            .andExpect(jsonPath("$.[*].pureWeight").value(hasItem(sameNumber(DEFAULT_PURE_WEIGHT))))
            .andExpect(jsonPath("$.[*].impureWeight").value(hasItem(sameNumber(DEFAULT_IMPURE_WEIGHT))))
            .andExpect(jsonPath("$.[*].amountCurrency").value(hasItem(sameNumber(DEFAULT_AMOUNT_CURRENCY))));
    }

    @Test
    @Transactional
    void getCustomJustificationChild() throws Exception {
        // Initialize the database
        insertedCustomJustificationChild = customJustificationChildRepository.saveAndFlush(customJustificationChild);

        // Get the customJustificationChild
        restCustomJustificationChildMockMvc
            .perform(get(ENTITY_API_URL_ID, customJustificationChild.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customJustificationChild.getId().intValue()))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
            .andExpect(jsonPath("$.tariffCode").value(DEFAULT_TARIFF_CODE))
            .andExpect(jsonPath("$.productName").value(sameNumber(DEFAULT_PRODUCT_NAME)))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.intValue()))
            .andExpect(jsonPath("$.boxCount").value(sameNumber(DEFAULT_BOX_COUNT)))
            .andExpect(jsonPath("$.boxType").value(DEFAULT_BOX_TYPE))
            .andExpect(jsonPath("$.pureWeight").value(sameNumber(DEFAULT_PURE_WEIGHT)))
            .andExpect(jsonPath("$.impureWeight").value(sameNumber(DEFAULT_IMPURE_WEIGHT)))
            .andExpect(jsonPath("$.amountCurrency").value(sameNumber(DEFAULT_AMOUNT_CURRENCY)));
    }

    @Test
    @Transactional
    void getNonExistingCustomJustificationChild() throws Exception {
        // Get the customJustificationChild
        restCustomJustificationChildMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomJustificationChild() throws Exception {
        // Initialize the database
        insertedCustomJustificationChild = customJustificationChildRepository.saveAndFlush(customJustificationChild);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customJustificationChild
        CustomJustificationChild updatedCustomJustificationChild = customJustificationChildRepository
            .findById(customJustificationChild.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCustomJustificationChild are not directly saved in db
        em.detach(updatedCustomJustificationChild);
        updatedCustomJustificationChild
            .item(UPDATED_ITEM)
            .tariffCode(UPDATED_TARIFF_CODE)
            .productName(UPDATED_PRODUCT_NAME)
            .productId(UPDATED_PRODUCT_ID)
            .boxCount(UPDATED_BOX_COUNT)
            .boxType(UPDATED_BOX_TYPE)
            .pureWeight(UPDATED_PURE_WEIGHT)
            .impureWeight(UPDATED_IMPURE_WEIGHT)
            .amountCurrency(UPDATED_AMOUNT_CURRENCY);
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(updatedCustomJustificationChild);

        restCustomJustificationChildMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customJustificationChildDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customJustificationChildDTO))
            )
            .andExpect(status().isOk());

        // Validate the CustomJustificationChild in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomJustificationChildToMatchAllProperties(updatedCustomJustificationChild);
    }

    @Test
    @Transactional
    void putNonExistingCustomJustificationChild() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustificationChild.setId(longCount.incrementAndGet());

        // Create the CustomJustificationChild
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(customJustificationChild);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomJustificationChildMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customJustificationChildDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customJustificationChildDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustificationChild in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomJustificationChild() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustificationChild.setId(longCount.incrementAndGet());

        // Create the CustomJustificationChild
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(customJustificationChild);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomJustificationChildMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customJustificationChildDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustificationChild in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomJustificationChild() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustificationChild.setId(longCount.incrementAndGet());

        // Create the CustomJustificationChild
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(customJustificationChild);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomJustificationChildMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customJustificationChildDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomJustificationChild in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomJustificationChildWithPatch() throws Exception {
        // Initialize the database
        insertedCustomJustificationChild = customJustificationChildRepository.saveAndFlush(customJustificationChild);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customJustificationChild using partial update
        CustomJustificationChild partialUpdatedCustomJustificationChild = new CustomJustificationChild();
        partialUpdatedCustomJustificationChild.setId(customJustificationChild.getId());

        partialUpdatedCustomJustificationChild
            .item(UPDATED_ITEM)
            .tariffCode(UPDATED_TARIFF_CODE)
            .productId(UPDATED_PRODUCT_ID)
            .impureWeight(UPDATED_IMPURE_WEIGHT);

        restCustomJustificationChildMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomJustificationChild.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomJustificationChild))
            )
            .andExpect(status().isOk());

        // Validate the CustomJustificationChild in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomJustificationChildUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCustomJustificationChild, customJustificationChild),
            getPersistedCustomJustificationChild(customJustificationChild)
        );
    }

    @Test
    @Transactional
    void fullUpdateCustomJustificationChildWithPatch() throws Exception {
        // Initialize the database
        insertedCustomJustificationChild = customJustificationChildRepository.saveAndFlush(customJustificationChild);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customJustificationChild using partial update
        CustomJustificationChild partialUpdatedCustomJustificationChild = new CustomJustificationChild();
        partialUpdatedCustomJustificationChild.setId(customJustificationChild.getId());

        partialUpdatedCustomJustificationChild
            .item(UPDATED_ITEM)
            .tariffCode(UPDATED_TARIFF_CODE)
            .productName(UPDATED_PRODUCT_NAME)
            .productId(UPDATED_PRODUCT_ID)
            .boxCount(UPDATED_BOX_COUNT)
            .boxType(UPDATED_BOX_TYPE)
            .pureWeight(UPDATED_PURE_WEIGHT)
            .impureWeight(UPDATED_IMPURE_WEIGHT)
            .amountCurrency(UPDATED_AMOUNT_CURRENCY);

        restCustomJustificationChildMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomJustificationChild.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomJustificationChild))
            )
            .andExpect(status().isOk());

        // Validate the CustomJustificationChild in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomJustificationChildUpdatableFieldsEquals(
            partialUpdatedCustomJustificationChild,
            getPersistedCustomJustificationChild(partialUpdatedCustomJustificationChild)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCustomJustificationChild() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustificationChild.setId(longCount.incrementAndGet());

        // Create the CustomJustificationChild
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(customJustificationChild);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomJustificationChildMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customJustificationChildDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customJustificationChildDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustificationChild in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomJustificationChild() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustificationChild.setId(longCount.incrementAndGet());

        // Create the CustomJustificationChild
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(customJustificationChild);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomJustificationChildMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customJustificationChildDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CustomJustificationChild in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomJustificationChild() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customJustificationChild.setId(longCount.incrementAndGet());

        // Create the CustomJustificationChild
        CustomJustificationChildDTO customJustificationChildDTO = customJustificationChildMapper.toDto(customJustificationChild);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomJustificationChildMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customJustificationChildDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CustomJustificationChild in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomJustificationChild() throws Exception {
        // Initialize the database
        insertedCustomJustificationChild = customJustificationChildRepository.saveAndFlush(customJustificationChild);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the customJustificationChild
        restCustomJustificationChildMockMvc
            .perform(delete(ENTITY_API_URL_ID, customJustificationChild.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customJustificationChildRepository.count();
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

    protected CustomJustificationChild getPersistedCustomJustificationChild(CustomJustificationChild customJustificationChild) {
        return customJustificationChildRepository.findById(customJustificationChild.getId()).orElseThrow();
    }

    protected void assertPersistedCustomJustificationChildToMatchAllProperties(CustomJustificationChild expectedCustomJustificationChild) {
        assertCustomJustificationChildAllPropertiesEquals(
            expectedCustomJustificationChild,
            getPersistedCustomJustificationChild(expectedCustomJustificationChild)
        );
    }

    protected void assertPersistedCustomJustificationChildToMatchUpdatableProperties(
        CustomJustificationChild expectedCustomJustificationChild
    ) {
        assertCustomJustificationChildAllUpdatablePropertiesEquals(
            expectedCustomJustificationChild,
            getPersistedCustomJustificationChild(expectedCustomJustificationChild)
        );
    }
}
