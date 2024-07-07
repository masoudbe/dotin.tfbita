package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainerAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer;
import com.dotin.tfbita.repository.DraftReceiptDocumentTransactionContainerRepository;
import com.dotin.tfbita.service.dto.DraftReceiptDocumentTransactionContainerDTO;
import com.dotin.tfbita.service.mapper.DraftReceiptDocumentTransactionContainerMapper;
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
 * Integration tests for the {@link DraftReceiptDocumentTransactionContainerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftReceiptDocumentTransactionContainerResourceIT {

    private static final Boolean DEFAULT_RECEIVE_RECEIPT_COMMISSION = false;
    private static final Boolean UPDATED_RECEIVE_RECEIPT_COMMISSION = true;

    private static final String ENTITY_API_URL = "/api/draft-receipt-document-transaction-containers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftReceiptDocumentTransactionContainerRepository draftReceiptDocumentTransactionContainerRepository;

    @Autowired
    private DraftReceiptDocumentTransactionContainerMapper draftReceiptDocumentTransactionContainerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftReceiptDocumentTransactionContainerMockMvc;

    private DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer;

    private DraftReceiptDocumentTransactionContainer insertedDraftReceiptDocumentTransactionContainer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftReceiptDocumentTransactionContainer createEntity(EntityManager em) {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer = new DraftReceiptDocumentTransactionContainer()
            .receiveReceiptCommission(DEFAULT_RECEIVE_RECEIPT_COMMISSION);
        return draftReceiptDocumentTransactionContainer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftReceiptDocumentTransactionContainer createUpdatedEntity(EntityManager em) {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer = new DraftReceiptDocumentTransactionContainer()
            .receiveReceiptCommission(UPDATED_RECEIVE_RECEIPT_COMMISSION);
        return draftReceiptDocumentTransactionContainer;
    }

    @BeforeEach
    public void initTest() {
        draftReceiptDocumentTransactionContainer = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftReceiptDocumentTransactionContainer != null) {
            draftReceiptDocumentTransactionContainerRepository.delete(insertedDraftReceiptDocumentTransactionContainer);
            insertedDraftReceiptDocumentTransactionContainer = null;
        }
    }

    @Test
    @Transactional
    void createDraftReceiptDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftReceiptDocumentTransactionContainer
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);
        var returnedDraftReceiptDocumentTransactionContainerDTO = om.readValue(
            restDraftReceiptDocumentTransactionContainerMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftReceiptDocumentTransactionContainerDTO.class
        );

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerMapper.toEntity(
            returnedDraftReceiptDocumentTransactionContainerDTO
        );
        assertDraftReceiptDocumentTransactionContainerUpdatableFieldsEquals(
            returnedDraftReceiptDocumentTransactionContainer,
            getPersistedDraftReceiptDocumentTransactionContainer(returnedDraftReceiptDocumentTransactionContainer)
        );

        insertedDraftReceiptDocumentTransactionContainer = returnedDraftReceiptDocumentTransactionContainer;
    }

    @Test
    @Transactional
    void createDraftReceiptDocumentTransactionContainerWithExistingId() throws Exception {
        // Create the DraftReceiptDocumentTransactionContainer with an existing ID
        draftReceiptDocumentTransactionContainer.setId(1L);
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftReceiptDocumentTransactionContainers() throws Exception {
        // Initialize the database
        insertedDraftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerRepository.saveAndFlush(
            draftReceiptDocumentTransactionContainer
        );

        // Get all the draftReceiptDocumentTransactionContainerList
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftReceiptDocumentTransactionContainer.getId().intValue())))
            .andExpect(jsonPath("$.[*].receiveReceiptCommission").value(hasItem(DEFAULT_RECEIVE_RECEIPT_COMMISSION.booleanValue())));
    }

    @Test
    @Transactional
    void getDraftReceiptDocumentTransactionContainer() throws Exception {
        // Initialize the database
        insertedDraftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerRepository.saveAndFlush(
            draftReceiptDocumentTransactionContainer
        );

        // Get the draftReceiptDocumentTransactionContainer
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(get(ENTITY_API_URL_ID, draftReceiptDocumentTransactionContainer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftReceiptDocumentTransactionContainer.getId().intValue()))
            .andExpect(jsonPath("$.receiveReceiptCommission").value(DEFAULT_RECEIVE_RECEIPT_COMMISSION.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingDraftReceiptDocumentTransactionContainer() throws Exception {
        // Get the draftReceiptDocumentTransactionContainer
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftReceiptDocumentTransactionContainer() throws Exception {
        // Initialize the database
        insertedDraftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerRepository.saveAndFlush(
            draftReceiptDocumentTransactionContainer
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftReceiptDocumentTransactionContainer
        DraftReceiptDocumentTransactionContainer updatedDraftReceiptDocumentTransactionContainer =
            draftReceiptDocumentTransactionContainerRepository.findById(draftReceiptDocumentTransactionContainer.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftReceiptDocumentTransactionContainer are not directly saved in db
        em.detach(updatedDraftReceiptDocumentTransactionContainer);
        updatedDraftReceiptDocumentTransactionContainer.receiveReceiptCommission(UPDATED_RECEIVE_RECEIPT_COMMISSION);
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(updatedDraftReceiptDocumentTransactionContainer);

        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftReceiptDocumentTransactionContainerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftReceiptDocumentTransactionContainerToMatchAllProperties(updatedDraftReceiptDocumentTransactionContainer);
    }

    @Test
    @Transactional
    void putNonExistingDraftReceiptDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceiptDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftReceiptDocumentTransactionContainer
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftReceiptDocumentTransactionContainerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftReceiptDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceiptDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftReceiptDocumentTransactionContainer
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftReceiptDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceiptDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftReceiptDocumentTransactionContainer
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftReceiptDocumentTransactionContainerWithPatch() throws Exception {
        // Initialize the database
        insertedDraftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerRepository.saveAndFlush(
            draftReceiptDocumentTransactionContainer
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftReceiptDocumentTransactionContainer using partial update
        DraftReceiptDocumentTransactionContainer partialUpdatedDraftReceiptDocumentTransactionContainer =
            new DraftReceiptDocumentTransactionContainer();
        partialUpdatedDraftReceiptDocumentTransactionContainer.setId(draftReceiptDocumentTransactionContainer.getId());

        partialUpdatedDraftReceiptDocumentTransactionContainer.receiveReceiptCommission(UPDATED_RECEIVE_RECEIPT_COMMISSION);

        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftReceiptDocumentTransactionContainer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftReceiptDocumentTransactionContainer))
            )
            .andExpect(status().isOk());

        // Validate the DraftReceiptDocumentTransactionContainer in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftReceiptDocumentTransactionContainerUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftReceiptDocumentTransactionContainer, draftReceiptDocumentTransactionContainer),
            getPersistedDraftReceiptDocumentTransactionContainer(draftReceiptDocumentTransactionContainer)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftReceiptDocumentTransactionContainerWithPatch() throws Exception {
        // Initialize the database
        insertedDraftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerRepository.saveAndFlush(
            draftReceiptDocumentTransactionContainer
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftReceiptDocumentTransactionContainer using partial update
        DraftReceiptDocumentTransactionContainer partialUpdatedDraftReceiptDocumentTransactionContainer =
            new DraftReceiptDocumentTransactionContainer();
        partialUpdatedDraftReceiptDocumentTransactionContainer.setId(draftReceiptDocumentTransactionContainer.getId());

        partialUpdatedDraftReceiptDocumentTransactionContainer.receiveReceiptCommission(UPDATED_RECEIVE_RECEIPT_COMMISSION);

        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftReceiptDocumentTransactionContainer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftReceiptDocumentTransactionContainer))
            )
            .andExpect(status().isOk());

        // Validate the DraftReceiptDocumentTransactionContainer in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftReceiptDocumentTransactionContainerUpdatableFieldsEquals(
            partialUpdatedDraftReceiptDocumentTransactionContainer,
            getPersistedDraftReceiptDocumentTransactionContainer(partialUpdatedDraftReceiptDocumentTransactionContainer)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftReceiptDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceiptDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftReceiptDocumentTransactionContainer
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftReceiptDocumentTransactionContainerDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftReceiptDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceiptDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftReceiptDocumentTransactionContainer
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftReceiptDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftReceiptDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftReceiptDocumentTransactionContainer
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftReceiptDocumentTransactionContainerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftReceiptDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftReceiptDocumentTransactionContainer() throws Exception {
        // Initialize the database
        insertedDraftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerRepository.saveAndFlush(
            draftReceiptDocumentTransactionContainer
        );

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftReceiptDocumentTransactionContainer
        restDraftReceiptDocumentTransactionContainerMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftReceiptDocumentTransactionContainer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftReceiptDocumentTransactionContainerRepository.count();
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

    protected DraftReceiptDocumentTransactionContainer getPersistedDraftReceiptDocumentTransactionContainer(
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer
    ) {
        return draftReceiptDocumentTransactionContainerRepository.findById(draftReceiptDocumentTransactionContainer.getId()).orElseThrow();
    }

    protected void assertPersistedDraftReceiptDocumentTransactionContainerToMatchAllProperties(
        DraftReceiptDocumentTransactionContainer expectedDraftReceiptDocumentTransactionContainer
    ) {
        assertDraftReceiptDocumentTransactionContainerAllPropertiesEquals(
            expectedDraftReceiptDocumentTransactionContainer,
            getPersistedDraftReceiptDocumentTransactionContainer(expectedDraftReceiptDocumentTransactionContainer)
        );
    }

    protected void assertPersistedDraftReceiptDocumentTransactionContainerToMatchUpdatableProperties(
        DraftReceiptDocumentTransactionContainer expectedDraftReceiptDocumentTransactionContainer
    ) {
        assertDraftReceiptDocumentTransactionContainerAllUpdatablePropertiesEquals(
            expectedDraftReceiptDocumentTransactionContainer,
            getPersistedDraftReceiptDocumentTransactionContainer(expectedDraftReceiptDocumentTransactionContainer)
        );
    }
}
