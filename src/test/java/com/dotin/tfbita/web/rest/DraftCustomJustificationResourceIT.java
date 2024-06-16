package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftCustomJustificationAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftCustomJustification;
import com.dotin.tfbita.repository.DraftCustomJustificationRepository;
import com.dotin.tfbita.service.DraftCustomJustificationService;
import com.dotin.tfbita.service.dto.DraftCustomJustificationDTO;
import com.dotin.tfbita.service.mapper.DraftCustomJustificationMapper;
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
 * Integration tests for the {@link DraftCustomJustificationResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class DraftCustomJustificationResourceIT {

    private static final String ENTITY_API_URL = "/api/draft-custom-justifications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftCustomJustificationRepository draftCustomJustificationRepository;

    @Mock
    private DraftCustomJustificationRepository draftCustomJustificationRepositoryMock;

    @Autowired
    private DraftCustomJustificationMapper draftCustomJustificationMapper;

    @Mock
    private DraftCustomJustificationService draftCustomJustificationServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftCustomJustificationMockMvc;

    private DraftCustomJustification draftCustomJustification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftCustomJustification createEntity(EntityManager em) {
        DraftCustomJustification draftCustomJustification = new DraftCustomJustification();
        return draftCustomJustification;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftCustomJustification createUpdatedEntity(EntityManager em) {
        DraftCustomJustification draftCustomJustification = new DraftCustomJustification();
        return draftCustomJustification;
    }

    @BeforeEach
    public void initTest() {
        draftCustomJustification = createEntity(em);
    }

    @Test
    @Transactional
    void createDraftCustomJustification() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftCustomJustification
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(draftCustomJustification);
        var returnedDraftCustomJustificationDTO = om.readValue(
            restDraftCustomJustificationMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftCustomJustificationDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftCustomJustificationDTO.class
        );

        // Validate the DraftCustomJustification in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftCustomJustification = draftCustomJustificationMapper.toEntity(returnedDraftCustomJustificationDTO);
        assertDraftCustomJustificationUpdatableFieldsEquals(
            returnedDraftCustomJustification,
            getPersistedDraftCustomJustification(returnedDraftCustomJustification)
        );
    }

    @Test
    @Transactional
    void createDraftCustomJustificationWithExistingId() throws Exception {
        // Create the DraftCustomJustification with an existing ID
        draftCustomJustification.setId(1L);
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(draftCustomJustification);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftCustomJustificationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftCustomJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftCustomJustifications() throws Exception {
        // Initialize the database
        draftCustomJustificationRepository.saveAndFlush(draftCustomJustification);

        // Get all the draftCustomJustificationList
        restDraftCustomJustificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftCustomJustification.getId().intValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDraftCustomJustificationsWithEagerRelationshipsIsEnabled() throws Exception {
        when(draftCustomJustificationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDraftCustomJustificationMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(draftCustomJustificationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDraftCustomJustificationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(draftCustomJustificationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDraftCustomJustificationMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(draftCustomJustificationRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getDraftCustomJustification() throws Exception {
        // Initialize the database
        draftCustomJustificationRepository.saveAndFlush(draftCustomJustification);

        // Get the draftCustomJustification
        restDraftCustomJustificationMockMvc
            .perform(get(ENTITY_API_URL_ID, draftCustomJustification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftCustomJustification.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDraftCustomJustification() throws Exception {
        // Get the draftCustomJustification
        restDraftCustomJustificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftCustomJustification() throws Exception {
        // Initialize the database
        draftCustomJustificationRepository.saveAndFlush(draftCustomJustification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftCustomJustification
        DraftCustomJustification updatedDraftCustomJustification = draftCustomJustificationRepository
            .findById(draftCustomJustification.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedDraftCustomJustification are not directly saved in db
        em.detach(updatedDraftCustomJustification);
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(updatedDraftCustomJustification);

        restDraftCustomJustificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftCustomJustificationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftCustomJustificationDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftCustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftCustomJustificationToMatchAllProperties(updatedDraftCustomJustification);
    }

    @Test
    @Transactional
    void putNonExistingDraftCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCustomJustification.setId(longCount.incrementAndGet());

        // Create the DraftCustomJustification
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(draftCustomJustification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftCustomJustificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftCustomJustificationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftCustomJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCustomJustification.setId(longCount.incrementAndGet());

        // Create the DraftCustomJustification
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(draftCustomJustification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftCustomJustificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftCustomJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCustomJustification.setId(longCount.incrementAndGet());

        // Create the DraftCustomJustification
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(draftCustomJustification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftCustomJustificationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftCustomJustificationDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftCustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftCustomJustificationWithPatch() throws Exception {
        // Initialize the database
        draftCustomJustificationRepository.saveAndFlush(draftCustomJustification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftCustomJustification using partial update
        DraftCustomJustification partialUpdatedDraftCustomJustification = new DraftCustomJustification();
        partialUpdatedDraftCustomJustification.setId(draftCustomJustification.getId());

        restDraftCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftCustomJustification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftCustomJustification))
            )
            .andExpect(status().isOk());

        // Validate the DraftCustomJustification in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftCustomJustificationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftCustomJustification, draftCustomJustification),
            getPersistedDraftCustomJustification(draftCustomJustification)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftCustomJustificationWithPatch() throws Exception {
        // Initialize the database
        draftCustomJustificationRepository.saveAndFlush(draftCustomJustification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftCustomJustification using partial update
        DraftCustomJustification partialUpdatedDraftCustomJustification = new DraftCustomJustification();
        partialUpdatedDraftCustomJustification.setId(draftCustomJustification.getId());

        restDraftCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftCustomJustification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftCustomJustification))
            )
            .andExpect(status().isOk());

        // Validate the DraftCustomJustification in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftCustomJustificationUpdatableFieldsEquals(
            partialUpdatedDraftCustomJustification,
            getPersistedDraftCustomJustification(partialUpdatedDraftCustomJustification)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCustomJustification.setId(longCount.incrementAndGet());

        // Create the DraftCustomJustification
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(draftCustomJustification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftCustomJustificationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftCustomJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCustomJustification.setId(longCount.incrementAndGet());

        // Create the DraftCustomJustification
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(draftCustomJustification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftCustomJustificationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftCustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftCustomJustification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftCustomJustification.setId(longCount.incrementAndGet());

        // Create the DraftCustomJustification
        DraftCustomJustificationDTO draftCustomJustificationDTO = draftCustomJustificationMapper.toDto(draftCustomJustification);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftCustomJustificationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftCustomJustificationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftCustomJustification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftCustomJustification() throws Exception {
        // Initialize the database
        draftCustomJustificationRepository.saveAndFlush(draftCustomJustification);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftCustomJustification
        restDraftCustomJustificationMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftCustomJustification.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftCustomJustificationRepository.count();
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

    protected DraftCustomJustification getPersistedDraftCustomJustification(DraftCustomJustification draftCustomJustification) {
        return draftCustomJustificationRepository.findById(draftCustomJustification.getId()).orElseThrow();
    }

    protected void assertPersistedDraftCustomJustificationToMatchAllProperties(DraftCustomJustification expectedDraftCustomJustification) {
        assertDraftCustomJustificationAllPropertiesEquals(
            expectedDraftCustomJustification,
            getPersistedDraftCustomJustification(expectedDraftCustomJustification)
        );
    }

    protected void assertPersistedDraftCustomJustificationToMatchUpdatableProperties(
        DraftCustomJustification expectedDraftCustomJustification
    ) {
        assertDraftCustomJustificationAllUpdatablePropertiesEquals(
            expectedDraftCustomJustification,
            getPersistedDraftCustomJustification(expectedDraftCustomJustification)
        );
    }
}
