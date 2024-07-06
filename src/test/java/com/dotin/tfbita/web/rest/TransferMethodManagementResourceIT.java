package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.TransferMethodManagementAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.TransferMethodManagement;
import com.dotin.tfbita.repository.TransferMethodManagementRepository;
import com.dotin.tfbita.service.dto.TransferMethodManagementDTO;
import com.dotin.tfbita.service.mapper.TransferMethodManagementMapper;
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
 * Integration tests for the {@link TransferMethodManagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TransferMethodManagementResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/transfer-method-managements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TransferMethodManagementRepository transferMethodManagementRepository;

    @Autowired
    private TransferMethodManagementMapper transferMethodManagementMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransferMethodManagementMockMvc;

    private TransferMethodManagement transferMethodManagement;

    private TransferMethodManagement insertedTransferMethodManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransferMethodManagement createEntity(EntityManager em) {
        TransferMethodManagement transferMethodManagement = new TransferMethodManagement().code(DEFAULT_CODE).desc(DEFAULT_DESC);
        return transferMethodManagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransferMethodManagement createUpdatedEntity(EntityManager em) {
        TransferMethodManagement transferMethodManagement = new TransferMethodManagement().code(UPDATED_CODE).desc(UPDATED_DESC);
        return transferMethodManagement;
    }

    @BeforeEach
    public void initTest() {
        transferMethodManagement = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTransferMethodManagement != null) {
            transferMethodManagementRepository.delete(insertedTransferMethodManagement);
            insertedTransferMethodManagement = null;
        }
    }

    @Test
    @Transactional
    void createTransferMethodManagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TransferMethodManagement
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(transferMethodManagement);
        var returnedTransferMethodManagementDTO = om.readValue(
            restTransferMethodManagementMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transferMethodManagementDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TransferMethodManagementDTO.class
        );

        // Validate the TransferMethodManagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTransferMethodManagement = transferMethodManagementMapper.toEntity(returnedTransferMethodManagementDTO);
        assertTransferMethodManagementUpdatableFieldsEquals(
            returnedTransferMethodManagement,
            getPersistedTransferMethodManagement(returnedTransferMethodManagement)
        );

        insertedTransferMethodManagement = returnedTransferMethodManagement;
    }

    @Test
    @Transactional
    void createTransferMethodManagementWithExistingId() throws Exception {
        // Create the TransferMethodManagement with an existing ID
        transferMethodManagement.setId(1L);
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(transferMethodManagement);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransferMethodManagementMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transferMethodManagementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransferMethodManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTransferMethodManagements() throws Exception {
        // Initialize the database
        insertedTransferMethodManagement = transferMethodManagementRepository.saveAndFlush(transferMethodManagement);

        // Get all the transferMethodManagementList
        restTransferMethodManagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transferMethodManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }

    @Test
    @Transactional
    void getTransferMethodManagement() throws Exception {
        // Initialize the database
        insertedTransferMethodManagement = transferMethodManagementRepository.saveAndFlush(transferMethodManagement);

        // Get the transferMethodManagement
        restTransferMethodManagementMockMvc
            .perform(get(ENTITY_API_URL_ID, transferMethodManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transferMethodManagement.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC));
    }

    @Test
    @Transactional
    void getNonExistingTransferMethodManagement() throws Exception {
        // Get the transferMethodManagement
        restTransferMethodManagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTransferMethodManagement() throws Exception {
        // Initialize the database
        insertedTransferMethodManagement = transferMethodManagementRepository.saveAndFlush(transferMethodManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transferMethodManagement
        TransferMethodManagement updatedTransferMethodManagement = transferMethodManagementRepository
            .findById(transferMethodManagement.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedTransferMethodManagement are not directly saved in db
        em.detach(updatedTransferMethodManagement);
        updatedTransferMethodManagement.code(UPDATED_CODE).desc(UPDATED_DESC);
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(updatedTransferMethodManagement);

        restTransferMethodManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transferMethodManagementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transferMethodManagementDTO))
            )
            .andExpect(status().isOk());

        // Validate the TransferMethodManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTransferMethodManagementToMatchAllProperties(updatedTransferMethodManagement);
    }

    @Test
    @Transactional
    void putNonExistingTransferMethodManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transferMethodManagement.setId(longCount.incrementAndGet());

        // Create the TransferMethodManagement
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(transferMethodManagement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransferMethodManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transferMethodManagementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transferMethodManagementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransferMethodManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTransferMethodManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transferMethodManagement.setId(longCount.incrementAndGet());

        // Create the TransferMethodManagement
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(transferMethodManagement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransferMethodManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transferMethodManagementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransferMethodManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTransferMethodManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transferMethodManagement.setId(longCount.incrementAndGet());

        // Create the TransferMethodManagement
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(transferMethodManagement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransferMethodManagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transferMethodManagementDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TransferMethodManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTransferMethodManagementWithPatch() throws Exception {
        // Initialize the database
        insertedTransferMethodManagement = transferMethodManagementRepository.saveAndFlush(transferMethodManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transferMethodManagement using partial update
        TransferMethodManagement partialUpdatedTransferMethodManagement = new TransferMethodManagement();
        partialUpdatedTransferMethodManagement.setId(transferMethodManagement.getId());

        partialUpdatedTransferMethodManagement.code(UPDATED_CODE);

        restTransferMethodManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransferMethodManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTransferMethodManagement))
            )
            .andExpect(status().isOk());

        // Validate the TransferMethodManagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTransferMethodManagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTransferMethodManagement, transferMethodManagement),
            getPersistedTransferMethodManagement(transferMethodManagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateTransferMethodManagementWithPatch() throws Exception {
        // Initialize the database
        insertedTransferMethodManagement = transferMethodManagementRepository.saveAndFlush(transferMethodManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transferMethodManagement using partial update
        TransferMethodManagement partialUpdatedTransferMethodManagement = new TransferMethodManagement();
        partialUpdatedTransferMethodManagement.setId(transferMethodManagement.getId());

        partialUpdatedTransferMethodManagement.code(UPDATED_CODE).desc(UPDATED_DESC);

        restTransferMethodManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransferMethodManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTransferMethodManagement))
            )
            .andExpect(status().isOk());

        // Validate the TransferMethodManagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTransferMethodManagementUpdatableFieldsEquals(
            partialUpdatedTransferMethodManagement,
            getPersistedTransferMethodManagement(partialUpdatedTransferMethodManagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTransferMethodManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transferMethodManagement.setId(longCount.incrementAndGet());

        // Create the TransferMethodManagement
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(transferMethodManagement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransferMethodManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, transferMethodManagementDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(transferMethodManagementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransferMethodManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTransferMethodManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transferMethodManagement.setId(longCount.incrementAndGet());

        // Create the TransferMethodManagement
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(transferMethodManagement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransferMethodManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(transferMethodManagementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransferMethodManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTransferMethodManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transferMethodManagement.setId(longCount.incrementAndGet());

        // Create the TransferMethodManagement
        TransferMethodManagementDTO transferMethodManagementDTO = transferMethodManagementMapper.toDto(transferMethodManagement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransferMethodManagementMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(transferMethodManagementDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TransferMethodManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTransferMethodManagement() throws Exception {
        // Initialize the database
        insertedTransferMethodManagement = transferMethodManagementRepository.saveAndFlush(transferMethodManagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the transferMethodManagement
        restTransferMethodManagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, transferMethodManagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return transferMethodManagementRepository.count();
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

    protected TransferMethodManagement getPersistedTransferMethodManagement(TransferMethodManagement transferMethodManagement) {
        return transferMethodManagementRepository.findById(transferMethodManagement.getId()).orElseThrow();
    }

    protected void assertPersistedTransferMethodManagementToMatchAllProperties(TransferMethodManagement expectedTransferMethodManagement) {
        assertTransferMethodManagementAllPropertiesEquals(
            expectedTransferMethodManagement,
            getPersistedTransferMethodManagement(expectedTransferMethodManagement)
        );
    }

    protected void assertPersistedTransferMethodManagementToMatchUpdatableProperties(
        TransferMethodManagement expectedTransferMethodManagement
    ) {
        assertTransferMethodManagementAllUpdatablePropertiesEquals(
            expectedTransferMethodManagement,
            getPersistedTransferMethodManagement(expectedTransferMethodManagement)
        );
    }
}
