package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftStatusInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftStatusInfo;
import com.dotin.tfbita.repository.DraftStatusInfoRepository;
import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
import com.dotin.tfbita.service.mapper.DraftStatusInfoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link DraftStatusInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftStatusInfoResourceIT {

    private static final Boolean DEFAULT_APPROVED = false;
    private static final Boolean UPDATED_APPROVED = true;

    private static final Boolean DEFAULT_DRAFT_REQUEST_ACCEPTED = false;
    private static final Boolean UPDATED_DRAFT_REQUEST_ACCEPTED = true;

    private static final Boolean DEFAULT_INSURANCE_COST_PAID = false;
    private static final Boolean UPDATED_INSURANCE_COST_PAID = true;

    private static final Boolean DEFAULT_ISSUED = false;
    private static final Boolean UPDATED_ISSUED = true;

    private static final Boolean DEFAULT_OTHER_COSTS_PAID = false;
    private static final Boolean UPDATED_OTHER_COSTS_PAID = true;

    private static final Boolean DEFAULT_POST_SWIFT_COST_PAIED = false;
    private static final Boolean UPDATED_POST_SWIFT_COST_PAIED = true;

    private static final String DEFAULT_REJECT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_REJECT_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_REMAIN_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_REMAIN_AMOUNT = new BigDecimal(2);

    private static final Boolean DEFAULT_STAMP_COST_PAID = false;
    private static final Boolean UPDATED_STAMP_COST_PAID = true;

    private static final String ENTITY_API_URL = "/api/draft-status-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftStatusInfoRepository draftStatusInfoRepository;

    @Autowired
    private DraftStatusInfoMapper draftStatusInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftStatusInfoMockMvc;

    private DraftStatusInfo draftStatusInfo;

    private DraftStatusInfo insertedDraftStatusInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftStatusInfo createEntity(EntityManager em) {
        DraftStatusInfo draftStatusInfo = new DraftStatusInfo()
            .approved(DEFAULT_APPROVED)
            .draftRequestAccepted(DEFAULT_DRAFT_REQUEST_ACCEPTED)
            .insuranceCostPaid(DEFAULT_INSURANCE_COST_PAID)
            .issued(DEFAULT_ISSUED)
            .otherCostsPaid(DEFAULT_OTHER_COSTS_PAID)
            .postSwiftCostPaied(DEFAULT_POST_SWIFT_COST_PAIED)
            .rejectDescription(DEFAULT_REJECT_DESCRIPTION)
            .remainAmount(DEFAULT_REMAIN_AMOUNT)
            .stampCostPaid(DEFAULT_STAMP_COST_PAID);
        return draftStatusInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftStatusInfo createUpdatedEntity(EntityManager em) {
        DraftStatusInfo draftStatusInfo = new DraftStatusInfo()
            .approved(UPDATED_APPROVED)
            .draftRequestAccepted(UPDATED_DRAFT_REQUEST_ACCEPTED)
            .insuranceCostPaid(UPDATED_INSURANCE_COST_PAID)
            .issued(UPDATED_ISSUED)
            .otherCostsPaid(UPDATED_OTHER_COSTS_PAID)
            .postSwiftCostPaied(UPDATED_POST_SWIFT_COST_PAIED)
            .rejectDescription(UPDATED_REJECT_DESCRIPTION)
            .remainAmount(UPDATED_REMAIN_AMOUNT)
            .stampCostPaid(UPDATED_STAMP_COST_PAID);
        return draftStatusInfo;
    }

    @BeforeEach
    public void initTest() {
        draftStatusInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftStatusInfo != null) {
            draftStatusInfoRepository.delete(insertedDraftStatusInfo);
            insertedDraftStatusInfo = null;
        }
    }

    @Test
    @Transactional
    void createDraftStatusInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftStatusInfo
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(draftStatusInfo);
        var returnedDraftStatusInfoDTO = om.readValue(
            restDraftStatusInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftStatusInfoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftStatusInfoDTO.class
        );

        // Validate the DraftStatusInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftStatusInfo = draftStatusInfoMapper.toEntity(returnedDraftStatusInfoDTO);
        assertDraftStatusInfoUpdatableFieldsEquals(returnedDraftStatusInfo, getPersistedDraftStatusInfo(returnedDraftStatusInfo));

        insertedDraftStatusInfo = returnedDraftStatusInfo;
    }

    @Test
    @Transactional
    void createDraftStatusInfoWithExistingId() throws Exception {
        // Create the DraftStatusInfo with an existing ID
        draftStatusInfo.setId(1L);
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(draftStatusInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftStatusInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftStatusInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftStatusInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftStatusInfos() throws Exception {
        // Initialize the database
        insertedDraftStatusInfo = draftStatusInfoRepository.saveAndFlush(draftStatusInfo);

        // Get all the draftStatusInfoList
        restDraftStatusInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftStatusInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].approved").value(hasItem(DEFAULT_APPROVED.booleanValue())))
            .andExpect(jsonPath("$.[*].draftRequestAccepted").value(hasItem(DEFAULT_DRAFT_REQUEST_ACCEPTED.booleanValue())))
            .andExpect(jsonPath("$.[*].insuranceCostPaid").value(hasItem(DEFAULT_INSURANCE_COST_PAID.booleanValue())))
            .andExpect(jsonPath("$.[*].issued").value(hasItem(DEFAULT_ISSUED.booleanValue())))
            .andExpect(jsonPath("$.[*].otherCostsPaid").value(hasItem(DEFAULT_OTHER_COSTS_PAID.booleanValue())))
            .andExpect(jsonPath("$.[*].postSwiftCostPaied").value(hasItem(DEFAULT_POST_SWIFT_COST_PAIED.booleanValue())))
            .andExpect(jsonPath("$.[*].rejectDescription").value(hasItem(DEFAULT_REJECT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].remainAmount").value(hasItem(sameNumber(DEFAULT_REMAIN_AMOUNT))))
            .andExpect(jsonPath("$.[*].stampCostPaid").value(hasItem(DEFAULT_STAMP_COST_PAID.booleanValue())));
    }

    @Test
    @Transactional
    void getDraftStatusInfo() throws Exception {
        // Initialize the database
        insertedDraftStatusInfo = draftStatusInfoRepository.saveAndFlush(draftStatusInfo);

        // Get the draftStatusInfo
        restDraftStatusInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, draftStatusInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftStatusInfo.getId().intValue()))
            .andExpect(jsonPath("$.approved").value(DEFAULT_APPROVED.booleanValue()))
            .andExpect(jsonPath("$.draftRequestAccepted").value(DEFAULT_DRAFT_REQUEST_ACCEPTED.booleanValue()))
            .andExpect(jsonPath("$.insuranceCostPaid").value(DEFAULT_INSURANCE_COST_PAID.booleanValue()))
            .andExpect(jsonPath("$.issued").value(DEFAULT_ISSUED.booleanValue()))
            .andExpect(jsonPath("$.otherCostsPaid").value(DEFAULT_OTHER_COSTS_PAID.booleanValue()))
            .andExpect(jsonPath("$.postSwiftCostPaied").value(DEFAULT_POST_SWIFT_COST_PAIED.booleanValue()))
            .andExpect(jsonPath("$.rejectDescription").value(DEFAULT_REJECT_DESCRIPTION))
            .andExpect(jsonPath("$.remainAmount").value(sameNumber(DEFAULT_REMAIN_AMOUNT)))
            .andExpect(jsonPath("$.stampCostPaid").value(DEFAULT_STAMP_COST_PAID.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingDraftStatusInfo() throws Exception {
        // Get the draftStatusInfo
        restDraftStatusInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftStatusInfo() throws Exception {
        // Initialize the database
        insertedDraftStatusInfo = draftStatusInfoRepository.saveAndFlush(draftStatusInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftStatusInfo
        DraftStatusInfo updatedDraftStatusInfo = draftStatusInfoRepository.findById(draftStatusInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftStatusInfo are not directly saved in db
        em.detach(updatedDraftStatusInfo);
        updatedDraftStatusInfo
            .approved(UPDATED_APPROVED)
            .draftRequestAccepted(UPDATED_DRAFT_REQUEST_ACCEPTED)
            .insuranceCostPaid(UPDATED_INSURANCE_COST_PAID)
            .issued(UPDATED_ISSUED)
            .otherCostsPaid(UPDATED_OTHER_COSTS_PAID)
            .postSwiftCostPaied(UPDATED_POST_SWIFT_COST_PAIED)
            .rejectDescription(UPDATED_REJECT_DESCRIPTION)
            .remainAmount(UPDATED_REMAIN_AMOUNT)
            .stampCostPaid(UPDATED_STAMP_COST_PAID);
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(updatedDraftStatusInfo);

        restDraftStatusInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftStatusInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftStatusInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftStatusInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftStatusInfoToMatchAllProperties(updatedDraftStatusInfo);
    }

    @Test
    @Transactional
    void putNonExistingDraftStatusInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftStatusInfo.setId(longCount.incrementAndGet());

        // Create the DraftStatusInfo
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(draftStatusInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftStatusInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftStatusInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftStatusInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftStatusInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftStatusInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftStatusInfo.setId(longCount.incrementAndGet());

        // Create the DraftStatusInfo
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(draftStatusInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftStatusInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftStatusInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftStatusInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftStatusInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftStatusInfo.setId(longCount.incrementAndGet());

        // Create the DraftStatusInfo
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(draftStatusInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftStatusInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftStatusInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftStatusInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftStatusInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftStatusInfo = draftStatusInfoRepository.saveAndFlush(draftStatusInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftStatusInfo using partial update
        DraftStatusInfo partialUpdatedDraftStatusInfo = new DraftStatusInfo();
        partialUpdatedDraftStatusInfo.setId(draftStatusInfo.getId());

        partialUpdatedDraftStatusInfo
            .draftRequestAccepted(UPDATED_DRAFT_REQUEST_ACCEPTED)
            .issued(UPDATED_ISSUED)
            .otherCostsPaid(UPDATED_OTHER_COSTS_PAID)
            .postSwiftCostPaied(UPDATED_POST_SWIFT_COST_PAIED)
            .stampCostPaid(UPDATED_STAMP_COST_PAID);

        restDraftStatusInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftStatusInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftStatusInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftStatusInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftStatusInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftStatusInfo, draftStatusInfo),
            getPersistedDraftStatusInfo(draftStatusInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftStatusInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftStatusInfo = draftStatusInfoRepository.saveAndFlush(draftStatusInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftStatusInfo using partial update
        DraftStatusInfo partialUpdatedDraftStatusInfo = new DraftStatusInfo();
        partialUpdatedDraftStatusInfo.setId(draftStatusInfo.getId());

        partialUpdatedDraftStatusInfo
            .approved(UPDATED_APPROVED)
            .draftRequestAccepted(UPDATED_DRAFT_REQUEST_ACCEPTED)
            .insuranceCostPaid(UPDATED_INSURANCE_COST_PAID)
            .issued(UPDATED_ISSUED)
            .otherCostsPaid(UPDATED_OTHER_COSTS_PAID)
            .postSwiftCostPaied(UPDATED_POST_SWIFT_COST_PAIED)
            .rejectDescription(UPDATED_REJECT_DESCRIPTION)
            .remainAmount(UPDATED_REMAIN_AMOUNT)
            .stampCostPaid(UPDATED_STAMP_COST_PAID);

        restDraftStatusInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftStatusInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftStatusInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftStatusInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftStatusInfoUpdatableFieldsEquals(
            partialUpdatedDraftStatusInfo,
            getPersistedDraftStatusInfo(partialUpdatedDraftStatusInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftStatusInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftStatusInfo.setId(longCount.incrementAndGet());

        // Create the DraftStatusInfo
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(draftStatusInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftStatusInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftStatusInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftStatusInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftStatusInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftStatusInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftStatusInfo.setId(longCount.incrementAndGet());

        // Create the DraftStatusInfo
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(draftStatusInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftStatusInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftStatusInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftStatusInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftStatusInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftStatusInfo.setId(longCount.incrementAndGet());

        // Create the DraftStatusInfo
        DraftStatusInfoDTO draftStatusInfoDTO = draftStatusInfoMapper.toDto(draftStatusInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftStatusInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftStatusInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftStatusInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftStatusInfo() throws Exception {
        // Initialize the database
        insertedDraftStatusInfo = draftStatusInfoRepository.saveAndFlush(draftStatusInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftStatusInfo
        restDraftStatusInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftStatusInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftStatusInfoRepository.count();
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

    protected DraftStatusInfo getPersistedDraftStatusInfo(DraftStatusInfo draftStatusInfo) {
        return draftStatusInfoRepository.findById(draftStatusInfo.getId()).orElseThrow();
    }

    protected void assertPersistedDraftStatusInfoToMatchAllProperties(DraftStatusInfo expectedDraftStatusInfo) {
        assertDraftStatusInfoAllPropertiesEquals(expectedDraftStatusInfo, getPersistedDraftStatusInfo(expectedDraftStatusInfo));
    }

    protected void assertPersistedDraftStatusInfoToMatchUpdatableProperties(DraftStatusInfo expectedDraftStatusInfo) {
        assertDraftStatusInfoAllUpdatablePropertiesEquals(expectedDraftStatusInfo, getPersistedDraftStatusInfo(expectedDraftStatusInfo));
    }
}
