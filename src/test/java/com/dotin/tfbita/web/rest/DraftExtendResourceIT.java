package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftExtendAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftExtend;
import com.dotin.tfbita.repository.DraftExtendRepository;
import com.dotin.tfbita.service.dto.DraftExtendDTO;
import com.dotin.tfbita.service.mapper.DraftExtendMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DraftExtendResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftExtendResourceIT {

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final String DEFAULT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TIME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/draft-extends";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftExtendRepository draftExtendRepository;

    @Autowired
    private DraftExtendMapper draftExtendMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftExtendMockMvc;

    private DraftExtend draftExtend;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftExtend createEntity(EntityManager em) {
        DraftExtend draftExtend = new DraftExtend().date(DEFAULT_DATE).duration(DEFAULT_DURATION).time(DEFAULT_TIME);
        return draftExtend;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftExtend createUpdatedEntity(EntityManager em) {
        DraftExtend draftExtend = new DraftExtend().date(UPDATED_DATE).duration(UPDATED_DURATION).time(UPDATED_TIME);
        return draftExtend;
    }

    @BeforeEach
    public void initTest() {
        draftExtend = createEntity(em);
    }

    @Test
    @Transactional
    void createDraftExtend() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftExtend
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(draftExtend);
        var returnedDraftExtendDTO = om.readValue(
            restDraftExtendMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftExtendDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftExtendDTO.class
        );

        // Validate the DraftExtend in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftExtend = draftExtendMapper.toEntity(returnedDraftExtendDTO);
        assertDraftExtendUpdatableFieldsEquals(returnedDraftExtend, getPersistedDraftExtend(returnedDraftExtend));
    }

    @Test
    @Transactional
    void createDraftExtendWithExistingId() throws Exception {
        // Create the DraftExtend with an existing ID
        draftExtend.setId(1L);
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(draftExtend);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftExtendMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftExtendDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftExtend in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftExtends() throws Exception {
        // Initialize the database
        draftExtendRepository.saveAndFlush(draftExtend);

        // Get all the draftExtendList
        restDraftExtendMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftExtend.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME)));
    }

    @Test
    @Transactional
    void getDraftExtend() throws Exception {
        // Initialize the database
        draftExtendRepository.saveAndFlush(draftExtend);

        // Get the draftExtend
        restDraftExtendMockMvc
            .perform(get(ENTITY_API_URL_ID, draftExtend.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftExtend.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME));
    }

    @Test
    @Transactional
    void getNonExistingDraftExtend() throws Exception {
        // Get the draftExtend
        restDraftExtendMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftExtend() throws Exception {
        // Initialize the database
        draftExtendRepository.saveAndFlush(draftExtend);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftExtend
        DraftExtend updatedDraftExtend = draftExtendRepository.findById(draftExtend.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftExtend are not directly saved in db
        em.detach(updatedDraftExtend);
        updatedDraftExtend.date(UPDATED_DATE).duration(UPDATED_DURATION).time(UPDATED_TIME);
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(updatedDraftExtend);

        restDraftExtendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftExtendDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftExtendDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftExtend in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftExtendToMatchAllProperties(updatedDraftExtend);
    }

    @Test
    @Transactional
    void putNonExistingDraftExtend() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftExtend.setId(longCount.incrementAndGet());

        // Create the DraftExtend
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(draftExtend);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftExtendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftExtendDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftExtendDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftExtend in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftExtend() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftExtend.setId(longCount.incrementAndGet());

        // Create the DraftExtend
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(draftExtend);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftExtendMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftExtendDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftExtend in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftExtend() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftExtend.setId(longCount.incrementAndGet());

        // Create the DraftExtend
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(draftExtend);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftExtendMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftExtendDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftExtend in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftExtendWithPatch() throws Exception {
        // Initialize the database
        draftExtendRepository.saveAndFlush(draftExtend);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftExtend using partial update
        DraftExtend partialUpdatedDraftExtend = new DraftExtend();
        partialUpdatedDraftExtend.setId(draftExtend.getId());

        restDraftExtendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftExtend.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftExtend))
            )
            .andExpect(status().isOk());

        // Validate the DraftExtend in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftExtendUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftExtend, draftExtend),
            getPersistedDraftExtend(draftExtend)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftExtendWithPatch() throws Exception {
        // Initialize the database
        draftExtendRepository.saveAndFlush(draftExtend);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftExtend using partial update
        DraftExtend partialUpdatedDraftExtend = new DraftExtend();
        partialUpdatedDraftExtend.setId(draftExtend.getId());

        partialUpdatedDraftExtend.date(UPDATED_DATE).duration(UPDATED_DURATION).time(UPDATED_TIME);

        restDraftExtendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftExtend.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftExtend))
            )
            .andExpect(status().isOk());

        // Validate the DraftExtend in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftExtendUpdatableFieldsEquals(partialUpdatedDraftExtend, getPersistedDraftExtend(partialUpdatedDraftExtend));
    }

    @Test
    @Transactional
    void patchNonExistingDraftExtend() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftExtend.setId(longCount.incrementAndGet());

        // Create the DraftExtend
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(draftExtend);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftExtendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftExtendDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftExtendDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftExtend in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftExtend() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftExtend.setId(longCount.incrementAndGet());

        // Create the DraftExtend
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(draftExtend);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftExtendMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftExtendDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftExtend in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftExtend() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftExtend.setId(longCount.incrementAndGet());

        // Create the DraftExtend
        DraftExtendDTO draftExtendDTO = draftExtendMapper.toDto(draftExtend);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftExtendMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftExtendDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftExtend in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftExtend() throws Exception {
        // Initialize the database
        draftExtendRepository.saveAndFlush(draftExtend);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftExtend
        restDraftExtendMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftExtend.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftExtendRepository.count();
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

    protected DraftExtend getPersistedDraftExtend(DraftExtend draftExtend) {
        return draftExtendRepository.findById(draftExtend.getId()).orElseThrow();
    }

    protected void assertPersistedDraftExtendToMatchAllProperties(DraftExtend expectedDraftExtend) {
        assertDraftExtendAllPropertiesEquals(expectedDraftExtend, getPersistedDraftExtend(expectedDraftExtend));
    }

    protected void assertPersistedDraftExtendToMatchUpdatableProperties(DraftExtend expectedDraftExtend) {
        assertDraftExtendAllUpdatablePropertiesEquals(expectedDraftExtend, getPersistedDraftExtend(expectedDraftExtend));
    }
}
