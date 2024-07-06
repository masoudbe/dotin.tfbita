package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftDocumentTransactionContainerAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftDocumentTransactionContainer;
import com.dotin.tfbita.repository.DraftDocumentTransactionContainerRepository;
import com.dotin.tfbita.service.dto.DraftDocumentTransactionContainerDTO;
import com.dotin.tfbita.service.mapper.DraftDocumentTransactionContainerMapper;
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
 * Integration tests for the {@link DraftDocumentTransactionContainerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftDocumentTransactionContainerResourceIT {

    private static final String ENTITY_API_URL = "/api/draft-document-transaction-containers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftDocumentTransactionContainerRepository draftDocumentTransactionContainerRepository;

    @Autowired
    private DraftDocumentTransactionContainerMapper draftDocumentTransactionContainerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftDocumentTransactionContainerMockMvc;

    private DraftDocumentTransactionContainer draftDocumentTransactionContainer;

    private DraftDocumentTransactionContainer insertedDraftDocumentTransactionContainer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftDocumentTransactionContainer createEntity(EntityManager em) {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = new DraftDocumentTransactionContainer();
        return draftDocumentTransactionContainer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftDocumentTransactionContainer createUpdatedEntity(EntityManager em) {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = new DraftDocumentTransactionContainer();
        return draftDocumentTransactionContainer;
    }

    @BeforeEach
    public void initTest() {
        draftDocumentTransactionContainer = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftDocumentTransactionContainer != null) {
            draftDocumentTransactionContainerRepository.delete(insertedDraftDocumentTransactionContainer);
            insertedDraftDocumentTransactionContainer = null;
        }
    }

    @Test
    @Transactional
    void createDraftDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftDocumentTransactionContainer
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            draftDocumentTransactionContainer
        );
        var returnedDraftDocumentTransactionContainerDTO = om.readValue(
            restDraftDocumentTransactionContainerMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftDocumentTransactionContainerDTO.class
        );

        // Validate the DraftDocumentTransactionContainer in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftDocumentTransactionContainer = draftDocumentTransactionContainerMapper.toEntity(
            returnedDraftDocumentTransactionContainerDTO
        );
        assertDraftDocumentTransactionContainerUpdatableFieldsEquals(
            returnedDraftDocumentTransactionContainer,
            getPersistedDraftDocumentTransactionContainer(returnedDraftDocumentTransactionContainer)
        );

        insertedDraftDocumentTransactionContainer = returnedDraftDocumentTransactionContainer;
    }

    @Test
    @Transactional
    void createDraftDocumentTransactionContainerWithExistingId() throws Exception {
        // Create the DraftDocumentTransactionContainer with an existing ID
        draftDocumentTransactionContainer.setId(1L);
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            draftDocumentTransactionContainer
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftDocumentTransactionContainerMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftDocumentTransactionContainers() throws Exception {
        // Initialize the database
        insertedDraftDocumentTransactionContainer = draftDocumentTransactionContainerRepository.saveAndFlush(
            draftDocumentTransactionContainer
        );

        // Get all the draftDocumentTransactionContainerList
        restDraftDocumentTransactionContainerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftDocumentTransactionContainer.getId().intValue())));
    }

    @Test
    @Transactional
    void getDraftDocumentTransactionContainer() throws Exception {
        // Initialize the database
        insertedDraftDocumentTransactionContainer = draftDocumentTransactionContainerRepository.saveAndFlush(
            draftDocumentTransactionContainer
        );

        // Get the draftDocumentTransactionContainer
        restDraftDocumentTransactionContainerMockMvc
            .perform(get(ENTITY_API_URL_ID, draftDocumentTransactionContainer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftDocumentTransactionContainer.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDraftDocumentTransactionContainer() throws Exception {
        // Get the draftDocumentTransactionContainer
        restDraftDocumentTransactionContainerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftDocumentTransactionContainer() throws Exception {
        // Initialize the database
        insertedDraftDocumentTransactionContainer = draftDocumentTransactionContainerRepository.saveAndFlush(
            draftDocumentTransactionContainer
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftDocumentTransactionContainer
        DraftDocumentTransactionContainer updatedDraftDocumentTransactionContainer = draftDocumentTransactionContainerRepository
            .findById(draftDocumentTransactionContainer.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedDraftDocumentTransactionContainer are not directly saved in db
        em.detach(updatedDraftDocumentTransactionContainer);
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            updatedDraftDocumentTransactionContainer
        );

        restDraftDocumentTransactionContainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftDocumentTransactionContainerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftDocumentTransactionContainerToMatchAllProperties(updatedDraftDocumentTransactionContainer);
    }

    @Test
    @Transactional
    void putNonExistingDraftDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftDocumentTransactionContainer
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            draftDocumentTransactionContainer
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftDocumentTransactionContainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftDocumentTransactionContainerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftDocumentTransactionContainer
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            draftDocumentTransactionContainer
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftDocumentTransactionContainerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftDocumentTransactionContainer
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            draftDocumentTransactionContainer
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftDocumentTransactionContainerMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftDocumentTransactionContainerWithPatch() throws Exception {
        // Initialize the database
        insertedDraftDocumentTransactionContainer = draftDocumentTransactionContainerRepository.saveAndFlush(
            draftDocumentTransactionContainer
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftDocumentTransactionContainer using partial update
        DraftDocumentTransactionContainer partialUpdatedDraftDocumentTransactionContainer = new DraftDocumentTransactionContainer();
        partialUpdatedDraftDocumentTransactionContainer.setId(draftDocumentTransactionContainer.getId());

        restDraftDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftDocumentTransactionContainer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftDocumentTransactionContainer))
            )
            .andExpect(status().isOk());

        // Validate the DraftDocumentTransactionContainer in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftDocumentTransactionContainerUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftDocumentTransactionContainer, draftDocumentTransactionContainer),
            getPersistedDraftDocumentTransactionContainer(draftDocumentTransactionContainer)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftDocumentTransactionContainerWithPatch() throws Exception {
        // Initialize the database
        insertedDraftDocumentTransactionContainer = draftDocumentTransactionContainerRepository.saveAndFlush(
            draftDocumentTransactionContainer
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftDocumentTransactionContainer using partial update
        DraftDocumentTransactionContainer partialUpdatedDraftDocumentTransactionContainer = new DraftDocumentTransactionContainer();
        partialUpdatedDraftDocumentTransactionContainer.setId(draftDocumentTransactionContainer.getId());

        restDraftDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftDocumentTransactionContainer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftDocumentTransactionContainer))
            )
            .andExpect(status().isOk());

        // Validate the DraftDocumentTransactionContainer in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftDocumentTransactionContainerUpdatableFieldsEquals(
            partialUpdatedDraftDocumentTransactionContainer,
            getPersistedDraftDocumentTransactionContainer(partialUpdatedDraftDocumentTransactionContainer)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftDocumentTransactionContainer
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            draftDocumentTransactionContainer
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftDocumentTransactionContainerDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftDocumentTransactionContainer
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            draftDocumentTransactionContainer
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftDocumentTransactionContainer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftDocumentTransactionContainer.setId(longCount.incrementAndGet());

        // Create the DraftDocumentTransactionContainer
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = draftDocumentTransactionContainerMapper.toDto(
            draftDocumentTransactionContainer
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftDocumentTransactionContainerMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftDocumentTransactionContainerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftDocumentTransactionContainer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftDocumentTransactionContainer() throws Exception {
        // Initialize the database
        insertedDraftDocumentTransactionContainer = draftDocumentTransactionContainerRepository.saveAndFlush(
            draftDocumentTransactionContainer
        );

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftDocumentTransactionContainer
        restDraftDocumentTransactionContainerMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftDocumentTransactionContainer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftDocumentTransactionContainerRepository.count();
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

    protected DraftDocumentTransactionContainer getPersistedDraftDocumentTransactionContainer(
        DraftDocumentTransactionContainer draftDocumentTransactionContainer
    ) {
        return draftDocumentTransactionContainerRepository.findById(draftDocumentTransactionContainer.getId()).orElseThrow();
    }

    protected void assertPersistedDraftDocumentTransactionContainerToMatchAllProperties(
        DraftDocumentTransactionContainer expectedDraftDocumentTransactionContainer
    ) {
        assertDraftDocumentTransactionContainerAllPropertiesEquals(
            expectedDraftDocumentTransactionContainer,
            getPersistedDraftDocumentTransactionContainer(expectedDraftDocumentTransactionContainer)
        );
    }

    protected void assertPersistedDraftDocumentTransactionContainerToMatchUpdatableProperties(
        DraftDocumentTransactionContainer expectedDraftDocumentTransactionContainer
    ) {
        assertDraftDocumentTransactionContainerAllUpdatablePropertiesEquals(
            expectedDraftDocumentTransactionContainer,
            getPersistedDraftDocumentTransactionContainer(expectedDraftDocumentTransactionContainer)
        );
    }
}
