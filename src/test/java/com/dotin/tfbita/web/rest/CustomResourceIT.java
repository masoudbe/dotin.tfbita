package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.CustomAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.repository.CustomRepository;
import com.dotin.tfbita.service.CustomService;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.mapper.CustomMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
 * Integration tests for the {@link CustomResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class CustomResourceIT {

    private static final String DEFAULT_MODIFICATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFICATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_LATIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LATIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_TEMP_ID = 1L;
    private static final Long UPDATED_TEMP_ID = 2L;

    private static final String ENTITY_API_URL = "/api/customs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomRepository customRepository;

    @Mock
    private CustomRepository customRepositoryMock;

    @Autowired
    private CustomMapper customMapper;

    @Mock
    private CustomService customServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomMockMvc;

    private Custom custom;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Custom createEntity(EntityManager em) {
        Custom custom = new Custom()
            .modificationDate(DEFAULT_MODIFICATION_DATE)
            .latinName(DEFAULT_LATIN_NAME)
            .name(DEFAULT_NAME)
            .tempId(DEFAULT_TEMP_ID);
        return custom;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Custom createUpdatedEntity(EntityManager em) {
        Custom custom = new Custom()
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .latinName(UPDATED_LATIN_NAME)
            .name(UPDATED_NAME)
            .tempId(UPDATED_TEMP_ID);
        return custom;
    }

    @BeforeEach
    public void initTest() {
        custom = createEntity(em);
    }

    @Test
    @Transactional
    void createCustom() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Custom
        CustomDTO customDTO = customMapper.toDto(custom);
        var returnedCustomDTO = om.readValue(
            restCustomMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CustomDTO.class
        );

        // Validate the Custom in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCustom = customMapper.toEntity(returnedCustomDTO);
        assertCustomUpdatableFieldsEquals(returnedCustom, getPersistedCustom(returnedCustom));
    }

    @Test
    @Transactional
    void createCustomWithExistingId() throws Exception {
        // Create the Custom with an existing ID
        custom.setId(1L);
        CustomDTO customDTO = customMapper.toDto(custom);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Custom in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustoms() throws Exception {
        // Initialize the database
        customRepository.saveAndFlush(custom);

        // Get all the customList
        restCustomMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(custom.getId().intValue())))
            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.[*].latinName").value(hasItem(DEFAULT_LATIN_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].tempId").value(hasItem(DEFAULT_TEMP_ID.intValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCustomsWithEagerRelationshipsIsEnabled() throws Exception {
        when(customServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCustomMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(customServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCustomsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(customServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCustomMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(customRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getCustom() throws Exception {
        // Initialize the database
        customRepository.saveAndFlush(custom);

        // Get the custom
        restCustomMockMvc
            .perform(get(ENTITY_API_URL_ID, custom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(custom.getId().intValue()))
            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE))
            .andExpect(jsonPath("$.latinName").value(DEFAULT_LATIN_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.tempId").value(DEFAULT_TEMP_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCustom() throws Exception {
        // Get the custom
        restCustomMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustom() throws Exception {
        // Initialize the database
        customRepository.saveAndFlush(custom);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the custom
        Custom updatedCustom = customRepository.findById(custom.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustom are not directly saved in db
        em.detach(updatedCustom);
        updatedCustom.modificationDate(UPDATED_MODIFICATION_DATE).latinName(UPDATED_LATIN_NAME).name(UPDATED_NAME).tempId(UPDATED_TEMP_ID);
        CustomDTO customDTO = customMapper.toDto(updatedCustom);

        restCustomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customDTO))
            )
            .andExpect(status().isOk());

        // Validate the Custom in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomToMatchAllProperties(updatedCustom);
    }

    @Test
    @Transactional
    void putNonExistingCustom() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        custom.setId(longCount.incrementAndGet());

        // Create the Custom
        CustomDTO customDTO = customMapper.toDto(custom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Custom in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustom() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        custom.setId(longCount.incrementAndGet());

        // Create the Custom
        CustomDTO customDTO = customMapper.toDto(custom);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Custom in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustom() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        custom.setId(longCount.incrementAndGet());

        // Create the Custom
        CustomDTO customDTO = customMapper.toDto(custom);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Custom in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomWithPatch() throws Exception {
        // Initialize the database
        customRepository.saveAndFlush(custom);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the custom using partial update
        Custom partialUpdatedCustom = new Custom();
        partialUpdatedCustom.setId(custom.getId());

        partialUpdatedCustom.name(UPDATED_NAME).tempId(UPDATED_TEMP_ID);

        restCustomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustom.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustom))
            )
            .andExpect(status().isOk());

        // Validate the Custom in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCustom, custom), getPersistedCustom(custom));
    }

    @Test
    @Transactional
    void fullUpdateCustomWithPatch() throws Exception {
        // Initialize the database
        customRepository.saveAndFlush(custom);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the custom using partial update
        Custom partialUpdatedCustom = new Custom();
        partialUpdatedCustom.setId(custom.getId());

        partialUpdatedCustom
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .latinName(UPDATED_LATIN_NAME)
            .name(UPDATED_NAME)
            .tempId(UPDATED_TEMP_ID);

        restCustomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustom.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustom))
            )
            .andExpect(status().isOk());

        // Validate the Custom in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomUpdatableFieldsEquals(partialUpdatedCustom, getPersistedCustom(partialUpdatedCustom));
    }

    @Test
    @Transactional
    void patchNonExistingCustom() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        custom.setId(longCount.incrementAndGet());

        // Create the Custom
        CustomDTO customDTO = customMapper.toDto(custom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Custom in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustom() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        custom.setId(longCount.incrementAndGet());

        // Create the Custom
        CustomDTO customDTO = customMapper.toDto(custom);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Custom in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustom() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        custom.setId(longCount.incrementAndGet());

        // Create the Custom
        CustomDTO customDTO = customMapper.toDto(custom);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Custom in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustom() throws Exception {
        // Initialize the database
        customRepository.saveAndFlush(custom);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the custom
        restCustomMockMvc
            .perform(delete(ENTITY_API_URL_ID, custom.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customRepository.count();
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

    protected Custom getPersistedCustom(Custom custom) {
        return customRepository.findById(custom.getId()).orElseThrow();
    }

    protected void assertPersistedCustomToMatchAllProperties(Custom expectedCustom) {
        assertCustomAllPropertiesEquals(expectedCustom, getPersistedCustom(expectedCustom));
    }

    protected void assertPersistedCustomToMatchUpdatableProperties(Custom expectedCustom) {
        assertCustomAllUpdatablePropertiesEquals(expectedCustom, getPersistedCustom(expectedCustom));
    }
}
