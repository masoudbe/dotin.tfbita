package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.SuggestedSanctionInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.SuggestedSanctionInfo;
import com.dotin.tfbita.repository.SuggestedSanctionInfoRepository;
import com.dotin.tfbita.service.dto.SuggestedSanctionInfoDTO;
import com.dotin.tfbita.service.mapper.SuggestedSanctionInfoMapper;
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
 * Integration tests for the {@link SuggestedSanctionInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SuggestedSanctionInfoResourceIT {

    private static final String DEFAULT_SANCTION_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SANCTION_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONNEL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PERSONNEL_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/suggested-sanction-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SuggestedSanctionInfoRepository suggestedSanctionInfoRepository;

    @Autowired
    private SuggestedSanctionInfoMapper suggestedSanctionInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSuggestedSanctionInfoMockMvc;

    private SuggestedSanctionInfo suggestedSanctionInfo;

    private SuggestedSanctionInfo insertedSuggestedSanctionInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SuggestedSanctionInfo createEntity(EntityManager em) {
        SuggestedSanctionInfo suggestedSanctionInfo = new SuggestedSanctionInfo()
            .sanctionSerial(DEFAULT_SANCTION_SERIAL)
            .personnelCode(DEFAULT_PERSONNEL_CODE);
        return suggestedSanctionInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SuggestedSanctionInfo createUpdatedEntity(EntityManager em) {
        SuggestedSanctionInfo suggestedSanctionInfo = new SuggestedSanctionInfo()
            .sanctionSerial(UPDATED_SANCTION_SERIAL)
            .personnelCode(UPDATED_PERSONNEL_CODE);
        return suggestedSanctionInfo;
    }

    @BeforeEach
    public void initTest() {
        suggestedSanctionInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSuggestedSanctionInfo != null) {
            suggestedSanctionInfoRepository.delete(insertedSuggestedSanctionInfo);
            insertedSuggestedSanctionInfo = null;
        }
    }

    @Test
    @Transactional
    void createSuggestedSanctionInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SuggestedSanctionInfo
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);
        var returnedSuggestedSanctionInfoDTO = om.readValue(
            restSuggestedSanctionInfoMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(suggestedSanctionInfoDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SuggestedSanctionInfoDTO.class
        );

        // Validate the SuggestedSanctionInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSuggestedSanctionInfo = suggestedSanctionInfoMapper.toEntity(returnedSuggestedSanctionInfoDTO);
        assertSuggestedSanctionInfoUpdatableFieldsEquals(
            returnedSuggestedSanctionInfo,
            getPersistedSuggestedSanctionInfo(returnedSuggestedSanctionInfo)
        );

        insertedSuggestedSanctionInfo = returnedSuggestedSanctionInfo;
    }

    @Test
    @Transactional
    void createSuggestedSanctionInfoWithExistingId() throws Exception {
        // Create the SuggestedSanctionInfo with an existing ID
        suggestedSanctionInfo.setId(1L);
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuggestedSanctionInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(suggestedSanctionInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SuggestedSanctionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSuggestedSanctionInfos() throws Exception {
        // Initialize the database
        insertedSuggestedSanctionInfo = suggestedSanctionInfoRepository.saveAndFlush(suggestedSanctionInfo);

        // Get all the suggestedSanctionInfoList
        restSuggestedSanctionInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(suggestedSanctionInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].sanctionSerial").value(hasItem(DEFAULT_SANCTION_SERIAL)))
            .andExpect(jsonPath("$.[*].personnelCode").value(hasItem(DEFAULT_PERSONNEL_CODE)));
    }

    @Test
    @Transactional
    void getSuggestedSanctionInfo() throws Exception {
        // Initialize the database
        insertedSuggestedSanctionInfo = suggestedSanctionInfoRepository.saveAndFlush(suggestedSanctionInfo);

        // Get the suggestedSanctionInfo
        restSuggestedSanctionInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, suggestedSanctionInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(suggestedSanctionInfo.getId().intValue()))
            .andExpect(jsonPath("$.sanctionSerial").value(DEFAULT_SANCTION_SERIAL))
            .andExpect(jsonPath("$.personnelCode").value(DEFAULT_PERSONNEL_CODE));
    }

    @Test
    @Transactional
    void getNonExistingSuggestedSanctionInfo() throws Exception {
        // Get the suggestedSanctionInfo
        restSuggestedSanctionInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSuggestedSanctionInfo() throws Exception {
        // Initialize the database
        insertedSuggestedSanctionInfo = suggestedSanctionInfoRepository.saveAndFlush(suggestedSanctionInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the suggestedSanctionInfo
        SuggestedSanctionInfo updatedSuggestedSanctionInfo = suggestedSanctionInfoRepository
            .findById(suggestedSanctionInfo.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedSuggestedSanctionInfo are not directly saved in db
        em.detach(updatedSuggestedSanctionInfo);
        updatedSuggestedSanctionInfo.sanctionSerial(UPDATED_SANCTION_SERIAL).personnelCode(UPDATED_PERSONNEL_CODE);
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(updatedSuggestedSanctionInfo);

        restSuggestedSanctionInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, suggestedSanctionInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(suggestedSanctionInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the SuggestedSanctionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSuggestedSanctionInfoToMatchAllProperties(updatedSuggestedSanctionInfo);
    }

    @Test
    @Transactional
    void putNonExistingSuggestedSanctionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        suggestedSanctionInfo.setId(longCount.incrementAndGet());

        // Create the SuggestedSanctionInfo
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSuggestedSanctionInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, suggestedSanctionInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(suggestedSanctionInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SuggestedSanctionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSuggestedSanctionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        suggestedSanctionInfo.setId(longCount.incrementAndGet());

        // Create the SuggestedSanctionInfo
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSuggestedSanctionInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(suggestedSanctionInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SuggestedSanctionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSuggestedSanctionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        suggestedSanctionInfo.setId(longCount.incrementAndGet());

        // Create the SuggestedSanctionInfo
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSuggestedSanctionInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(suggestedSanctionInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SuggestedSanctionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSuggestedSanctionInfoWithPatch() throws Exception {
        // Initialize the database
        insertedSuggestedSanctionInfo = suggestedSanctionInfoRepository.saveAndFlush(suggestedSanctionInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the suggestedSanctionInfo using partial update
        SuggestedSanctionInfo partialUpdatedSuggestedSanctionInfo = new SuggestedSanctionInfo();
        partialUpdatedSuggestedSanctionInfo.setId(suggestedSanctionInfo.getId());

        partialUpdatedSuggestedSanctionInfo.personnelCode(UPDATED_PERSONNEL_CODE);

        restSuggestedSanctionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSuggestedSanctionInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSuggestedSanctionInfo))
            )
            .andExpect(status().isOk());

        // Validate the SuggestedSanctionInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSuggestedSanctionInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSuggestedSanctionInfo, suggestedSanctionInfo),
            getPersistedSuggestedSanctionInfo(suggestedSanctionInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateSuggestedSanctionInfoWithPatch() throws Exception {
        // Initialize the database
        insertedSuggestedSanctionInfo = suggestedSanctionInfoRepository.saveAndFlush(suggestedSanctionInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the suggestedSanctionInfo using partial update
        SuggestedSanctionInfo partialUpdatedSuggestedSanctionInfo = new SuggestedSanctionInfo();
        partialUpdatedSuggestedSanctionInfo.setId(suggestedSanctionInfo.getId());

        partialUpdatedSuggestedSanctionInfo.sanctionSerial(UPDATED_SANCTION_SERIAL).personnelCode(UPDATED_PERSONNEL_CODE);

        restSuggestedSanctionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSuggestedSanctionInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSuggestedSanctionInfo))
            )
            .andExpect(status().isOk());

        // Validate the SuggestedSanctionInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSuggestedSanctionInfoUpdatableFieldsEquals(
            partialUpdatedSuggestedSanctionInfo,
            getPersistedSuggestedSanctionInfo(partialUpdatedSuggestedSanctionInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSuggestedSanctionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        suggestedSanctionInfo.setId(longCount.incrementAndGet());

        // Create the SuggestedSanctionInfo
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSuggestedSanctionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, suggestedSanctionInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(suggestedSanctionInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SuggestedSanctionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSuggestedSanctionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        suggestedSanctionInfo.setId(longCount.incrementAndGet());

        // Create the SuggestedSanctionInfo
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSuggestedSanctionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(suggestedSanctionInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SuggestedSanctionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSuggestedSanctionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        suggestedSanctionInfo.setId(longCount.incrementAndGet());

        // Create the SuggestedSanctionInfo
        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSuggestedSanctionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(suggestedSanctionInfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SuggestedSanctionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSuggestedSanctionInfo() throws Exception {
        // Initialize the database
        insertedSuggestedSanctionInfo = suggestedSanctionInfoRepository.saveAndFlush(suggestedSanctionInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the suggestedSanctionInfo
        restSuggestedSanctionInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, suggestedSanctionInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return suggestedSanctionInfoRepository.count();
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

    protected SuggestedSanctionInfo getPersistedSuggestedSanctionInfo(SuggestedSanctionInfo suggestedSanctionInfo) {
        return suggestedSanctionInfoRepository.findById(suggestedSanctionInfo.getId()).orElseThrow();
    }

    protected void assertPersistedSuggestedSanctionInfoToMatchAllProperties(SuggestedSanctionInfo expectedSuggestedSanctionInfo) {
        assertSuggestedSanctionInfoAllPropertiesEquals(
            expectedSuggestedSanctionInfo,
            getPersistedSuggestedSanctionInfo(expectedSuggestedSanctionInfo)
        );
    }

    protected void assertPersistedSuggestedSanctionInfoToMatchUpdatableProperties(SuggestedSanctionInfo expectedSuggestedSanctionInfo) {
        assertSuggestedSanctionInfoAllUpdatablePropertiesEquals(
            expectedSuggestedSanctionInfo,
            getPersistedSuggestedSanctionInfo(expectedSuggestedSanctionInfo)
        );
    }
}
