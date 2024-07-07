package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftTypeAccountInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftTypeAccountInfo;
import com.dotin.tfbita.repository.DraftTypeAccountInfoRepository;
import com.dotin.tfbita.service.dto.DraftTypeAccountInfoDTO;
import com.dotin.tfbita.service.mapper.DraftTypeAccountInfoMapper;
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
 * Integration tests for the {@link DraftTypeAccountInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftTypeAccountInfoResourceIT {

    private static final String DEFAULT_SELL_CURRENCY_COMMISSION_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_SELL_CURRENCY_COMMISSION_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_INCOME_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INCOME_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/draft-type-account-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftTypeAccountInfoRepository draftTypeAccountInfoRepository;

    @Autowired
    private DraftTypeAccountInfoMapper draftTypeAccountInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftTypeAccountInfoMockMvc;

    private DraftTypeAccountInfo draftTypeAccountInfo;

    private DraftTypeAccountInfo insertedDraftTypeAccountInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftTypeAccountInfo createEntity(EntityManager em) {
        DraftTypeAccountInfo draftTypeAccountInfo = new DraftTypeAccountInfo()
            .sellCurrencyCommissionAccount(DEFAULT_SELL_CURRENCY_COMMISSION_ACCOUNT)
            .incomeAccountNumber(DEFAULT_INCOME_ACCOUNT_NUMBER);
        return draftTypeAccountInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftTypeAccountInfo createUpdatedEntity(EntityManager em) {
        DraftTypeAccountInfo draftTypeAccountInfo = new DraftTypeAccountInfo()
            .sellCurrencyCommissionAccount(UPDATED_SELL_CURRENCY_COMMISSION_ACCOUNT)
            .incomeAccountNumber(UPDATED_INCOME_ACCOUNT_NUMBER);
        return draftTypeAccountInfo;
    }

    @BeforeEach
    public void initTest() {
        draftTypeAccountInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftTypeAccountInfo != null) {
            draftTypeAccountInfoRepository.delete(insertedDraftTypeAccountInfo);
            insertedDraftTypeAccountInfo = null;
        }
    }

    @Test
    @Transactional
    void createDraftTypeAccountInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftTypeAccountInfo
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);
        var returnedDraftTypeAccountInfoDTO = om.readValue(
            restDraftTypeAccountInfoMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeAccountInfoDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftTypeAccountInfoDTO.class
        );

        // Validate the DraftTypeAccountInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftTypeAccountInfo = draftTypeAccountInfoMapper.toEntity(returnedDraftTypeAccountInfoDTO);
        assertDraftTypeAccountInfoUpdatableFieldsEquals(
            returnedDraftTypeAccountInfo,
            getPersistedDraftTypeAccountInfo(returnedDraftTypeAccountInfo)
        );

        insertedDraftTypeAccountInfo = returnedDraftTypeAccountInfo;
    }

    @Test
    @Transactional
    void createDraftTypeAccountInfoWithExistingId() throws Exception {
        // Create the DraftTypeAccountInfo with an existing ID
        draftTypeAccountInfo.setId(1L);
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftTypeAccountInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeAccountInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftTypeAccountInfos() throws Exception {
        // Initialize the database
        insertedDraftTypeAccountInfo = draftTypeAccountInfoRepository.saveAndFlush(draftTypeAccountInfo);

        // Get all the draftTypeAccountInfoList
        restDraftTypeAccountInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftTypeAccountInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].sellCurrencyCommissionAccount").value(hasItem(DEFAULT_SELL_CURRENCY_COMMISSION_ACCOUNT)))
            .andExpect(jsonPath("$.[*].incomeAccountNumber").value(hasItem(DEFAULT_INCOME_ACCOUNT_NUMBER)));
    }

    @Test
    @Transactional
    void getDraftTypeAccountInfo() throws Exception {
        // Initialize the database
        insertedDraftTypeAccountInfo = draftTypeAccountInfoRepository.saveAndFlush(draftTypeAccountInfo);

        // Get the draftTypeAccountInfo
        restDraftTypeAccountInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, draftTypeAccountInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftTypeAccountInfo.getId().intValue()))
            .andExpect(jsonPath("$.sellCurrencyCommissionAccount").value(DEFAULT_SELL_CURRENCY_COMMISSION_ACCOUNT))
            .andExpect(jsonPath("$.incomeAccountNumber").value(DEFAULT_INCOME_ACCOUNT_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingDraftTypeAccountInfo() throws Exception {
        // Get the draftTypeAccountInfo
        restDraftTypeAccountInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftTypeAccountInfo() throws Exception {
        // Initialize the database
        insertedDraftTypeAccountInfo = draftTypeAccountInfoRepository.saveAndFlush(draftTypeAccountInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTypeAccountInfo
        DraftTypeAccountInfo updatedDraftTypeAccountInfo = draftTypeAccountInfoRepository
            .findById(draftTypeAccountInfo.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedDraftTypeAccountInfo are not directly saved in db
        em.detach(updatedDraftTypeAccountInfo);
        updatedDraftTypeAccountInfo
            .sellCurrencyCommissionAccount(UPDATED_SELL_CURRENCY_COMMISSION_ACCOUNT)
            .incomeAccountNumber(UPDATED_INCOME_ACCOUNT_NUMBER);
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(updatedDraftTypeAccountInfo);

        restDraftTypeAccountInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftTypeAccountInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeAccountInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftTypeAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftTypeAccountInfoToMatchAllProperties(updatedDraftTypeAccountInfo);
    }

    @Test
    @Transactional
    void putNonExistingDraftTypeAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeAccountInfo
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftTypeAccountInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftTypeAccountInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeAccountInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftTypeAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeAccountInfo
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeAccountInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeAccountInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftTypeAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeAccountInfo
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeAccountInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeAccountInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftTypeAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftTypeAccountInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftTypeAccountInfo = draftTypeAccountInfoRepository.saveAndFlush(draftTypeAccountInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTypeAccountInfo using partial update
        DraftTypeAccountInfo partialUpdatedDraftTypeAccountInfo = new DraftTypeAccountInfo();
        partialUpdatedDraftTypeAccountInfo.setId(draftTypeAccountInfo.getId());

        partialUpdatedDraftTypeAccountInfo.sellCurrencyCommissionAccount(UPDATED_SELL_CURRENCY_COMMISSION_ACCOUNT);

        restDraftTypeAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftTypeAccountInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftTypeAccountInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftTypeAccountInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftTypeAccountInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftTypeAccountInfo, draftTypeAccountInfo),
            getPersistedDraftTypeAccountInfo(draftTypeAccountInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftTypeAccountInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftTypeAccountInfo = draftTypeAccountInfoRepository.saveAndFlush(draftTypeAccountInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTypeAccountInfo using partial update
        DraftTypeAccountInfo partialUpdatedDraftTypeAccountInfo = new DraftTypeAccountInfo();
        partialUpdatedDraftTypeAccountInfo.setId(draftTypeAccountInfo.getId());

        partialUpdatedDraftTypeAccountInfo
            .sellCurrencyCommissionAccount(UPDATED_SELL_CURRENCY_COMMISSION_ACCOUNT)
            .incomeAccountNumber(UPDATED_INCOME_ACCOUNT_NUMBER);

        restDraftTypeAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftTypeAccountInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftTypeAccountInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftTypeAccountInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftTypeAccountInfoUpdatableFieldsEquals(
            partialUpdatedDraftTypeAccountInfo,
            getPersistedDraftTypeAccountInfo(partialUpdatedDraftTypeAccountInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftTypeAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeAccountInfo
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftTypeAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftTypeAccountInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftTypeAccountInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftTypeAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeAccountInfo
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftTypeAccountInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftTypeAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeAccountInfo
        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftTypeAccountInfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftTypeAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftTypeAccountInfo() throws Exception {
        // Initialize the database
        insertedDraftTypeAccountInfo = draftTypeAccountInfoRepository.saveAndFlush(draftTypeAccountInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftTypeAccountInfo
        restDraftTypeAccountInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftTypeAccountInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftTypeAccountInfoRepository.count();
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

    protected DraftTypeAccountInfo getPersistedDraftTypeAccountInfo(DraftTypeAccountInfo draftTypeAccountInfo) {
        return draftTypeAccountInfoRepository.findById(draftTypeAccountInfo.getId()).orElseThrow();
    }

    protected void assertPersistedDraftTypeAccountInfoToMatchAllProperties(DraftTypeAccountInfo expectedDraftTypeAccountInfo) {
        assertDraftTypeAccountInfoAllPropertiesEquals(
            expectedDraftTypeAccountInfo,
            getPersistedDraftTypeAccountInfo(expectedDraftTypeAccountInfo)
        );
    }

    protected void assertPersistedDraftTypeAccountInfoToMatchUpdatableProperties(DraftTypeAccountInfo expectedDraftTypeAccountInfo) {
        assertDraftTypeAccountInfoAllUpdatablePropertiesEquals(
            expectedDraftTypeAccountInfo,
            getPersistedDraftTypeAccountInfo(expectedDraftTypeAccountInfo)
        );
    }
}
