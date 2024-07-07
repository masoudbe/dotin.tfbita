package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftAccountInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftAccountInfo;
import com.dotin.tfbita.repository.DraftAccountInfoRepository;
import com.dotin.tfbita.service.dto.DraftAccountInfoDTO;
import com.dotin.tfbita.service.mapper.DraftAccountInfoMapper;
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
 * Integration tests for the {@link DraftAccountInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftAccountInfoResourceIT {

    private static final Long DEFAULT_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID = 1L;
    private static final Long UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_DRAFT_MAIN_ACCOUNT_ID = 1L;
    private static final Long UPDATED_DRAFT_MAIN_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_INSURANCE_COST_ACCOUNT_ID = 1L;
    private static final Long UPDATED_INSURANCE_COST_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_JUSTIFICATION_DISCIPLINARY_ACCOUNT_ID = 1L;
    private static final Long UPDATED_JUSTIFICATION_DISCIPLINARY_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_OPEN_DRAFT_DISCIPLINARY_ACCOUNT_ID = 1L;
    private static final Long UPDATED_OPEN_DRAFT_DISCIPLINARY_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_OTHER_COSTS_ACCOUNT_ID = 1L;
    private static final Long UPDATED_OTHER_COSTS_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_POST_SWIFT_COSTS_ACCOUNT_ID = 1L;
    private static final Long UPDATED_POST_SWIFT_COSTS_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_AMOUNT_DEDUCTION_ACCOUNT_ID = 1L;
    private static final Long UPDATED_AMOUNT_DEDUCTION_ACCOUNT_ID = 2L;

    private static final String ENTITY_API_URL = "/api/draft-account-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftAccountInfoRepository draftAccountInfoRepository;

    @Autowired
    private DraftAccountInfoMapper draftAccountInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftAccountInfoMockMvc;

    private DraftAccountInfo draftAccountInfo;

    private DraftAccountInfo insertedDraftAccountInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftAccountInfo createEntity(EntityManager em) {
        DraftAccountInfo draftAccountInfo = new DraftAccountInfo()
            .documentReceiptDisciplinaryAccountId(DEFAULT_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID)
            .draftMainAccountId(DEFAULT_DRAFT_MAIN_ACCOUNT_ID)
            .insuranceCostAccountId(DEFAULT_INSURANCE_COST_ACCOUNT_ID)
            .justificationDisciplinaryAccountId(DEFAULT_JUSTIFICATION_DISCIPLINARY_ACCOUNT_ID)
            .openDraftDisciplinaryAccountId(DEFAULT_OPEN_DRAFT_DISCIPLINARY_ACCOUNT_ID)
            .otherCostsAccountId(DEFAULT_OTHER_COSTS_ACCOUNT_ID)
            .postSwiftCostsAccountId(DEFAULT_POST_SWIFT_COSTS_ACCOUNT_ID)
            .amountDeductionAccountId(DEFAULT_AMOUNT_DEDUCTION_ACCOUNT_ID);
        return draftAccountInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftAccountInfo createUpdatedEntity(EntityManager em) {
        DraftAccountInfo draftAccountInfo = new DraftAccountInfo()
            .documentReceiptDisciplinaryAccountId(UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID)
            .draftMainAccountId(UPDATED_DRAFT_MAIN_ACCOUNT_ID)
            .insuranceCostAccountId(UPDATED_INSURANCE_COST_ACCOUNT_ID)
            .justificationDisciplinaryAccountId(UPDATED_JUSTIFICATION_DISCIPLINARY_ACCOUNT_ID)
            .openDraftDisciplinaryAccountId(UPDATED_OPEN_DRAFT_DISCIPLINARY_ACCOUNT_ID)
            .otherCostsAccountId(UPDATED_OTHER_COSTS_ACCOUNT_ID)
            .postSwiftCostsAccountId(UPDATED_POST_SWIFT_COSTS_ACCOUNT_ID)
            .amountDeductionAccountId(UPDATED_AMOUNT_DEDUCTION_ACCOUNT_ID);
        return draftAccountInfo;
    }

    @BeforeEach
    public void initTest() {
        draftAccountInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftAccountInfo != null) {
            draftAccountInfoRepository.delete(insertedDraftAccountInfo);
            insertedDraftAccountInfo = null;
        }
    }

    @Test
    @Transactional
    void createDraftAccountInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftAccountInfo
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(draftAccountInfo);
        var returnedDraftAccountInfoDTO = om.readValue(
            restDraftAccountInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftAccountInfoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftAccountInfoDTO.class
        );

        // Validate the DraftAccountInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftAccountInfo = draftAccountInfoMapper.toEntity(returnedDraftAccountInfoDTO);
        assertDraftAccountInfoUpdatableFieldsEquals(returnedDraftAccountInfo, getPersistedDraftAccountInfo(returnedDraftAccountInfo));

        insertedDraftAccountInfo = returnedDraftAccountInfo;
    }

    @Test
    @Transactional
    void createDraftAccountInfoWithExistingId() throws Exception {
        // Create the DraftAccountInfo with an existing ID
        draftAccountInfo.setId(1L);
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(draftAccountInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftAccountInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftAccountInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftAccountInfos() throws Exception {
        // Initialize the database
        insertedDraftAccountInfo = draftAccountInfoRepository.saveAndFlush(draftAccountInfo);

        // Get all the draftAccountInfoList
        restDraftAccountInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftAccountInfo.getId().intValue())))
            .andExpect(
                jsonPath("$.[*].documentReceiptDisciplinaryAccountId").value(
                    hasItem(DEFAULT_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID.intValue())
                )
            )
            .andExpect(jsonPath("$.[*].draftMainAccountId").value(hasItem(DEFAULT_DRAFT_MAIN_ACCOUNT_ID.intValue())))
            .andExpect(jsonPath("$.[*].insuranceCostAccountId").value(hasItem(DEFAULT_INSURANCE_COST_ACCOUNT_ID.intValue())))
            .andExpect(
                jsonPath("$.[*].justificationDisciplinaryAccountId").value(
                    hasItem(DEFAULT_JUSTIFICATION_DISCIPLINARY_ACCOUNT_ID.intValue())
                )
            )
            .andExpect(
                jsonPath("$.[*].openDraftDisciplinaryAccountId").value(hasItem(DEFAULT_OPEN_DRAFT_DISCIPLINARY_ACCOUNT_ID.intValue()))
            )
            .andExpect(jsonPath("$.[*].otherCostsAccountId").value(hasItem(DEFAULT_OTHER_COSTS_ACCOUNT_ID.intValue())))
            .andExpect(jsonPath("$.[*].postSwiftCostsAccountId").value(hasItem(DEFAULT_POST_SWIFT_COSTS_ACCOUNT_ID.intValue())))
            .andExpect(jsonPath("$.[*].amountDeductionAccountId").value(hasItem(DEFAULT_AMOUNT_DEDUCTION_ACCOUNT_ID.intValue())));
    }

    @Test
    @Transactional
    void getDraftAccountInfo() throws Exception {
        // Initialize the database
        insertedDraftAccountInfo = draftAccountInfoRepository.saveAndFlush(draftAccountInfo);

        // Get the draftAccountInfo
        restDraftAccountInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, draftAccountInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftAccountInfo.getId().intValue()))
            .andExpect(
                jsonPath("$.documentReceiptDisciplinaryAccountId").value(DEFAULT_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID.intValue())
            )
            .andExpect(jsonPath("$.draftMainAccountId").value(DEFAULT_DRAFT_MAIN_ACCOUNT_ID.intValue()))
            .andExpect(jsonPath("$.insuranceCostAccountId").value(DEFAULT_INSURANCE_COST_ACCOUNT_ID.intValue()))
            .andExpect(jsonPath("$.justificationDisciplinaryAccountId").value(DEFAULT_JUSTIFICATION_DISCIPLINARY_ACCOUNT_ID.intValue()))
            .andExpect(jsonPath("$.openDraftDisciplinaryAccountId").value(DEFAULT_OPEN_DRAFT_DISCIPLINARY_ACCOUNT_ID.intValue()))
            .andExpect(jsonPath("$.otherCostsAccountId").value(DEFAULT_OTHER_COSTS_ACCOUNT_ID.intValue()))
            .andExpect(jsonPath("$.postSwiftCostsAccountId").value(DEFAULT_POST_SWIFT_COSTS_ACCOUNT_ID.intValue()))
            .andExpect(jsonPath("$.amountDeductionAccountId").value(DEFAULT_AMOUNT_DEDUCTION_ACCOUNT_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDraftAccountInfo() throws Exception {
        // Get the draftAccountInfo
        restDraftAccountInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftAccountInfo() throws Exception {
        // Initialize the database
        insertedDraftAccountInfo = draftAccountInfoRepository.saveAndFlush(draftAccountInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftAccountInfo
        DraftAccountInfo updatedDraftAccountInfo = draftAccountInfoRepository.findById(draftAccountInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftAccountInfo are not directly saved in db
        em.detach(updatedDraftAccountInfo);
        updatedDraftAccountInfo
            .documentReceiptDisciplinaryAccountId(UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID)
            .draftMainAccountId(UPDATED_DRAFT_MAIN_ACCOUNT_ID)
            .insuranceCostAccountId(UPDATED_INSURANCE_COST_ACCOUNT_ID)
            .justificationDisciplinaryAccountId(UPDATED_JUSTIFICATION_DISCIPLINARY_ACCOUNT_ID)
            .openDraftDisciplinaryAccountId(UPDATED_OPEN_DRAFT_DISCIPLINARY_ACCOUNT_ID)
            .otherCostsAccountId(UPDATED_OTHER_COSTS_ACCOUNT_ID)
            .postSwiftCostsAccountId(UPDATED_POST_SWIFT_COSTS_ACCOUNT_ID)
            .amountDeductionAccountId(UPDATED_AMOUNT_DEDUCTION_ACCOUNT_ID);
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(updatedDraftAccountInfo);

        restDraftAccountInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftAccountInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftAccountInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftAccountInfoToMatchAllProperties(updatedDraftAccountInfo);
    }

    @Test
    @Transactional
    void putNonExistingDraftAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftAccountInfo
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(draftAccountInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftAccountInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftAccountInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftAccountInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftAccountInfo
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(draftAccountInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftAccountInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftAccountInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftAccountInfo
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(draftAccountInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftAccountInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftAccountInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftAccountInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftAccountInfo = draftAccountInfoRepository.saveAndFlush(draftAccountInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftAccountInfo using partial update
        DraftAccountInfo partialUpdatedDraftAccountInfo = new DraftAccountInfo();
        partialUpdatedDraftAccountInfo.setId(draftAccountInfo.getId());

        partialUpdatedDraftAccountInfo
            .documentReceiptDisciplinaryAccountId(UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID)
            .insuranceCostAccountId(UPDATED_INSURANCE_COST_ACCOUNT_ID)
            .postSwiftCostsAccountId(UPDATED_POST_SWIFT_COSTS_ACCOUNT_ID)
            .amountDeductionAccountId(UPDATED_AMOUNT_DEDUCTION_ACCOUNT_ID);

        restDraftAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftAccountInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftAccountInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftAccountInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftAccountInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftAccountInfo, draftAccountInfo),
            getPersistedDraftAccountInfo(draftAccountInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftAccountInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftAccountInfo = draftAccountInfoRepository.saveAndFlush(draftAccountInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftAccountInfo using partial update
        DraftAccountInfo partialUpdatedDraftAccountInfo = new DraftAccountInfo();
        partialUpdatedDraftAccountInfo.setId(draftAccountInfo.getId());

        partialUpdatedDraftAccountInfo
            .documentReceiptDisciplinaryAccountId(UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_ACCOUNT_ID)
            .draftMainAccountId(UPDATED_DRAFT_MAIN_ACCOUNT_ID)
            .insuranceCostAccountId(UPDATED_INSURANCE_COST_ACCOUNT_ID)
            .justificationDisciplinaryAccountId(UPDATED_JUSTIFICATION_DISCIPLINARY_ACCOUNT_ID)
            .openDraftDisciplinaryAccountId(UPDATED_OPEN_DRAFT_DISCIPLINARY_ACCOUNT_ID)
            .otherCostsAccountId(UPDATED_OTHER_COSTS_ACCOUNT_ID)
            .postSwiftCostsAccountId(UPDATED_POST_SWIFT_COSTS_ACCOUNT_ID)
            .amountDeductionAccountId(UPDATED_AMOUNT_DEDUCTION_ACCOUNT_ID);

        restDraftAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftAccountInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftAccountInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftAccountInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftAccountInfoUpdatableFieldsEquals(
            partialUpdatedDraftAccountInfo,
            getPersistedDraftAccountInfo(partialUpdatedDraftAccountInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftAccountInfo
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(draftAccountInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftAccountInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftAccountInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftAccountInfo
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(draftAccountInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftAccountInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftAccountInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftAccountInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftAccountInfo.setId(longCount.incrementAndGet());

        // Create the DraftAccountInfo
        DraftAccountInfoDTO draftAccountInfoDTO = draftAccountInfoMapper.toDto(draftAccountInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftAccountInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftAccountInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftAccountInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftAccountInfo() throws Exception {
        // Initialize the database
        insertedDraftAccountInfo = draftAccountInfoRepository.saveAndFlush(draftAccountInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftAccountInfo
        restDraftAccountInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftAccountInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftAccountInfoRepository.count();
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

    protected DraftAccountInfo getPersistedDraftAccountInfo(DraftAccountInfo draftAccountInfo) {
        return draftAccountInfoRepository.findById(draftAccountInfo.getId()).orElseThrow();
    }

    protected void assertPersistedDraftAccountInfoToMatchAllProperties(DraftAccountInfo expectedDraftAccountInfo) {
        assertDraftAccountInfoAllPropertiesEquals(expectedDraftAccountInfo, getPersistedDraftAccountInfo(expectedDraftAccountInfo));
    }

    protected void assertPersistedDraftAccountInfoToMatchUpdatableProperties(DraftAccountInfo expectedDraftAccountInfo) {
        assertDraftAccountInfoAllUpdatablePropertiesEquals(
            expectedDraftAccountInfo,
            getPersistedDraftAccountInfo(expectedDraftAccountInfo)
        );
    }
}
