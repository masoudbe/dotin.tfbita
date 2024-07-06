package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftCertificateTypeAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftCertificateType;
import com.dotin.tfbita.repository.DraftCertificateTypeRepository;
import com.dotin.tfbita.service.dto.DraftCertificateTypeDTO;
import com.dotin.tfbita.service.mapper.DraftCertificateTypeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link DraftCertificateTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftCertificateTypeResourceIT {

    private static final Boolean DEFAULT_MANDATORY = false;
    private static final Boolean UPDATED_MANDATORY = true;

    private static final String ENTITY_API_URL = "/api/draft-certificate-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftCertificateTypeRepository draftCertificateTypeRepository;

    @Autowired
    private DraftCertificateTypeMapper draftCertificateTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftCertificateTypeMockMvc;

    private DraftCertificateType draftCertificateType;

    private DraftCertificateType insertedDraftCertificateType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftCertificateType createEntity(EntityManager em) {
        DraftCertificateType draftCertificateType = new DraftCertificateType().mandatory(DEFAULT_MANDATORY);
        return draftCertificateType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftCertificateType createUpdatedEntity(EntityManager em) {
        DraftCertificateType draftCertificateType = new DraftCertificateType().mandatory(UPDATED_MANDATORY);
        return draftCertificateType;
    }

    @BeforeEach
    public void initTest() {
        draftCertificateType = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftCertificateType != null) {
            draftCertificateTypeRepository.delete(insertedDraftCertificateType);
            insertedDraftCertificateType = null;
        }
    }

    @Test
    @Transactional
    void createDraftCertificateType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftCertificateType
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(draftCertificateType);
        var returnedDraftCertificateTypeDTO = om.readValue(
            restDraftCertificateTypeMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftCertificateTypeDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftCertificateTypeDTO.class
        );

        // Validate the DraftCertificateType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftCertificateType = draftCertificateTypeMapper.toEntity(returnedDraftCertificateTypeDTO);
        assertDraftCertificateTypeUpdatableFieldsEquals(
            returnedDraftCertificateType,
            getPersistedDraftCertificateType(returnedDraftCertificateType)
        );

        insertedDraftCertificateType = returnedDraftCertificateType;
    }

    @Test
    @Transactional
    void createDraftCertificateTypeWithExistingId() throws Exception {
        // Create the DraftCertificateType with an existing ID
        draftCertificateType.setId(1L);
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(draftCertificateType);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftCertificateTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftCertificateTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftCertificateType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftCertificateTypes() throws Exception {
        // Initialize the database
        insertedDraftCertificateType = draftCertificateTypeRepository.saveAndFlush(draftCertificateType);

        // Get all the draftCertificateTypeList
        restDraftCertificateTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftCertificateType.getId().intValue())))
            .andExpect(jsonPath("$.[*].mandatory").value(hasItem(DEFAULT_MANDATORY.booleanValue())));
    }

    @Test
    @Transactional
    void getDraftCertificateType() throws Exception {
        // Initialize the database
        insertedDraftCertificateType = draftCertificateTypeRepository.saveAndFlush(draftCertificateType);

        // Get the draftCertificateType
        restDraftCertificateTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, draftCertificateType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftCertificateType.getId().intValue()))
            .andExpect(jsonPath("$.mandatory").value(DEFAULT_MANDATORY.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingDraftCertificateType() throws Exception {
        // Get the draftCertificateType
        restDraftCertificateTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftCertificateType() throws Exception {
        // Initialize the database
        insertedDraftCertificateType = draftCertificateTypeRepository.saveAndFlush(draftCertificateType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftCertificateType
        DraftCertificateType updatedDraftCertificateType = draftCertificateTypeRepository
            .findById(draftCertificateType.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedDraftCertificateType are not directly saved in db
        em.detach(updatedDraftCertificateType);
        updatedDraftCertificateType.mandatory(UPDATED_MANDATORY);
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(updatedDraftCertificateType);

        restDraftCertificateTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftCertificateTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftCertificateTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftCertificateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftCertificateTypeToMatchAllProperties(updatedDraftCertificateType);
    }

    @Test
    @Transactional
    void putNonExistingDraftCertificateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCertificateType.setId(longCount.incrementAndGet());

        // Create the DraftCertificateType
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(draftCertificateType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftCertificateTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftCertificateTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftCertificateTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCertificateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftCertificateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCertificateType.setId(longCount.incrementAndGet());

        // Create the DraftCertificateType
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(draftCertificateType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftCertificateTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftCertificateTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCertificateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftCertificateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCertificateType.setId(longCount.incrementAndGet());

        // Create the DraftCertificateType
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(draftCertificateType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftCertificateTypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftCertificateTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftCertificateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftCertificateTypeWithPatch() throws Exception {
        // Initialize the database
        insertedDraftCertificateType = draftCertificateTypeRepository.saveAndFlush(draftCertificateType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftCertificateType using partial update
        DraftCertificateType partialUpdatedDraftCertificateType = new DraftCertificateType();
        partialUpdatedDraftCertificateType.setId(draftCertificateType.getId());

        partialUpdatedDraftCertificateType.mandatory(UPDATED_MANDATORY);

        restDraftCertificateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftCertificateType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftCertificateType))
            )
            .andExpect(status().isOk());

        // Validate the DraftCertificateType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftCertificateTypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftCertificateType, draftCertificateType),
            getPersistedDraftCertificateType(draftCertificateType)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftCertificateTypeWithPatch() throws Exception {
        // Initialize the database
        insertedDraftCertificateType = draftCertificateTypeRepository.saveAndFlush(draftCertificateType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftCertificateType using partial update
        DraftCertificateType partialUpdatedDraftCertificateType = new DraftCertificateType();
        partialUpdatedDraftCertificateType.setId(draftCertificateType.getId());

        partialUpdatedDraftCertificateType.mandatory(UPDATED_MANDATORY);

        restDraftCertificateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftCertificateType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftCertificateType))
            )
            .andExpect(status().isOk());

        // Validate the DraftCertificateType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftCertificateTypeUpdatableFieldsEquals(
            partialUpdatedDraftCertificateType,
            getPersistedDraftCertificateType(partialUpdatedDraftCertificateType)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftCertificateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCertificateType.setId(longCount.incrementAndGet());

        // Create the DraftCertificateType
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(draftCertificateType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftCertificateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftCertificateTypeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftCertificateTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCertificateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftCertificateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCertificateType.setId(longCount.incrementAndGet());

        // Create the DraftCertificateType
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(draftCertificateType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftCertificateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftCertificateTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCertificateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftCertificateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCertificateType.setId(longCount.incrementAndGet());

        // Create the DraftCertificateType
        DraftCertificateTypeDTO draftCertificateTypeDTO = draftCertificateTypeMapper.toDto(draftCertificateType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftCertificateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftCertificateTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftCertificateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftCertificateType() throws Exception {
        // Initialize the database
        insertedDraftCertificateType = draftCertificateTypeRepository.saveAndFlush(draftCertificateType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftCertificateType
        restDraftCertificateTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftCertificateType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftCertificateTypeRepository.count();
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

    protected DraftCertificateType getPersistedDraftCertificateType(DraftCertificateType draftCertificateType) {
        return draftCertificateTypeRepository.findById(draftCertificateType.getId()).orElseThrow();
    }

    protected void assertPersistedDraftCertificateTypeToMatchAllProperties(DraftCertificateType expectedDraftCertificateType) {
        assertDraftCertificateTypeAllPropertiesEquals(
            expectedDraftCertificateType,
            getPersistedDraftCertificateType(expectedDraftCertificateType)
        );
    }

    protected void assertPersistedDraftCertificateTypeToMatchUpdatableProperties(DraftCertificateType expectedDraftCertificateType) {
        assertDraftCertificateTypeAllUpdatablePropertiesEquals(
            expectedDraftCertificateType,
            getPersistedDraftCertificateType(expectedDraftCertificateType)
        );
    }
}
