package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftTypeTopicInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftTypeTopicInfo;
import com.dotin.tfbita.repository.DraftTypeTopicInfoRepository;
import com.dotin.tfbita.service.dto.DraftTypeTopicInfoDTO;
import com.dotin.tfbita.service.mapper.DraftTypeTopicInfoMapper;
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
 * Integration tests for the {@link DraftTypeTopicInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftTypeTopicInfoResourceIT {

    private static final Long DEFAULT_CURRENCY_SELL_COMMISSION_TOPIC = 1L;
    private static final Long UPDATED_CURRENCY_SELL_COMMISSION_TOPIC = 2L;

    private static final Long DEFAULT_DOCUMENT_RECEIPT_DISCIPLINARY_TOPIC = 1L;
    private static final Long UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_TOPIC = 2L;

    private static final Long DEFAULT_DRAFT_MAIN_TOPIC = 1L;
    private static final Long UPDATED_DRAFT_MAIN_TOPIC = 2L;

    private static final Long DEFAULT_INSURANCE_COST_TOPIC = 1L;
    private static final Long UPDATED_INSURANCE_COST_TOPIC = 2L;

    private static final Long DEFAULT_JUSTIFICATION_DISCIPLINARY_TOPIC = 1L;
    private static final Long UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC = 2L;

    private static final Long DEFAULT_OPEN_DRAFT_DISCIPLINARY_TOPIC = 1L;
    private static final Long UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC = 2L;

    private static final Long DEFAULT_OTHER_COSTS_TOPIC = 1L;
    private static final Long UPDATED_OTHER_COSTS_TOPIC = 2L;

    private static final Long DEFAULT_POST_TELEGRAPH_SWIFT_COSTS_TOPIC = 1L;
    private static final Long UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC = 2L;

    private static final String ENTITY_API_URL = "/api/draft-type-topic-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftTypeTopicInfoRepository draftTypeTopicInfoRepository;

    @Autowired
    private DraftTypeTopicInfoMapper draftTypeTopicInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftTypeTopicInfoMockMvc;

    private DraftTypeTopicInfo draftTypeTopicInfo;

    private DraftTypeTopicInfo insertedDraftTypeTopicInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftTypeTopicInfo createEntity(EntityManager em) {
        DraftTypeTopicInfo draftTypeTopicInfo = new DraftTypeTopicInfo()
            .currencySellCommissionTopic(DEFAULT_CURRENCY_SELL_COMMISSION_TOPIC)
            .documentReceiptDisciplinaryTopic(DEFAULT_DOCUMENT_RECEIPT_DISCIPLINARY_TOPIC)
            .draftMainTopic(DEFAULT_DRAFT_MAIN_TOPIC)
            .insuranceCostTopic(DEFAULT_INSURANCE_COST_TOPIC)
            .justificationDisciplinaryTopic(DEFAULT_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(DEFAULT_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(DEFAULT_OTHER_COSTS_TOPIC)
            .postTelegraphSwiftCostsTopic(DEFAULT_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);
        return draftTypeTopicInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftTypeTopicInfo createUpdatedEntity(EntityManager em) {
        DraftTypeTopicInfo draftTypeTopicInfo = new DraftTypeTopicInfo()
            .currencySellCommissionTopic(UPDATED_CURRENCY_SELL_COMMISSION_TOPIC)
            .documentReceiptDisciplinaryTopic(UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_TOPIC)
            .draftMainTopic(UPDATED_DRAFT_MAIN_TOPIC)
            .insuranceCostTopic(UPDATED_INSURANCE_COST_TOPIC)
            .justificationDisciplinaryTopic(UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(UPDATED_OTHER_COSTS_TOPIC)
            .postTelegraphSwiftCostsTopic(UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);
        return draftTypeTopicInfo;
    }

    @BeforeEach
    public void initTest() {
        draftTypeTopicInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftTypeTopicInfo != null) {
            draftTypeTopicInfoRepository.delete(insertedDraftTypeTopicInfo);
            insertedDraftTypeTopicInfo = null;
        }
    }

    @Test
    @Transactional
    void createDraftTypeTopicInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftTypeTopicInfo
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);
        var returnedDraftTypeTopicInfoDTO = om.readValue(
            restDraftTypeTopicInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeTopicInfoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftTypeTopicInfoDTO.class
        );

        // Validate the DraftTypeTopicInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftTypeTopicInfo = draftTypeTopicInfoMapper.toEntity(returnedDraftTypeTopicInfoDTO);
        assertDraftTypeTopicInfoUpdatableFieldsEquals(
            returnedDraftTypeTopicInfo,
            getPersistedDraftTypeTopicInfo(returnedDraftTypeTopicInfo)
        );

        insertedDraftTypeTopicInfo = returnedDraftTypeTopicInfo;
    }

    @Test
    @Transactional
    void createDraftTypeTopicInfoWithExistingId() throws Exception {
        // Create the DraftTypeTopicInfo with an existing ID
        draftTypeTopicInfo.setId(1L);
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftTypeTopicInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeTopicInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeTopicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftTypeTopicInfos() throws Exception {
        // Initialize the database
        insertedDraftTypeTopicInfo = draftTypeTopicInfoRepository.saveAndFlush(draftTypeTopicInfo);

        // Get all the draftTypeTopicInfoList
        restDraftTypeTopicInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftTypeTopicInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].currencySellCommissionTopic").value(hasItem(DEFAULT_CURRENCY_SELL_COMMISSION_TOPIC.intValue())))
            .andExpect(
                jsonPath("$.[*].documentReceiptDisciplinaryTopic").value(hasItem(DEFAULT_DOCUMENT_RECEIPT_DISCIPLINARY_TOPIC.intValue()))
            )
            .andExpect(jsonPath("$.[*].draftMainTopic").value(hasItem(DEFAULT_DRAFT_MAIN_TOPIC.intValue())))
            .andExpect(jsonPath("$.[*].insuranceCostTopic").value(hasItem(DEFAULT_INSURANCE_COST_TOPIC.intValue())))
            .andExpect(jsonPath("$.[*].justificationDisciplinaryTopic").value(hasItem(DEFAULT_JUSTIFICATION_DISCIPLINARY_TOPIC.intValue())))
            .andExpect(jsonPath("$.[*].openDraftDisciplinaryTopic").value(hasItem(DEFAULT_OPEN_DRAFT_DISCIPLINARY_TOPIC.intValue())))
            .andExpect(jsonPath("$.[*].otherCostsTopic").value(hasItem(DEFAULT_OTHER_COSTS_TOPIC.intValue())))
            .andExpect(jsonPath("$.[*].postTelegraphSwiftCostsTopic").value(hasItem(DEFAULT_POST_TELEGRAPH_SWIFT_COSTS_TOPIC.intValue())));
    }

    @Test
    @Transactional
    void getDraftTypeTopicInfo() throws Exception {
        // Initialize the database
        insertedDraftTypeTopicInfo = draftTypeTopicInfoRepository.saveAndFlush(draftTypeTopicInfo);

        // Get the draftTypeTopicInfo
        restDraftTypeTopicInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, draftTypeTopicInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftTypeTopicInfo.getId().intValue()))
            .andExpect(jsonPath("$.currencySellCommissionTopic").value(DEFAULT_CURRENCY_SELL_COMMISSION_TOPIC.intValue()))
            .andExpect(jsonPath("$.documentReceiptDisciplinaryTopic").value(DEFAULT_DOCUMENT_RECEIPT_DISCIPLINARY_TOPIC.intValue()))
            .andExpect(jsonPath("$.draftMainTopic").value(DEFAULT_DRAFT_MAIN_TOPIC.intValue()))
            .andExpect(jsonPath("$.insuranceCostTopic").value(DEFAULT_INSURANCE_COST_TOPIC.intValue()))
            .andExpect(jsonPath("$.justificationDisciplinaryTopic").value(DEFAULT_JUSTIFICATION_DISCIPLINARY_TOPIC.intValue()))
            .andExpect(jsonPath("$.openDraftDisciplinaryTopic").value(DEFAULT_OPEN_DRAFT_DISCIPLINARY_TOPIC.intValue()))
            .andExpect(jsonPath("$.otherCostsTopic").value(DEFAULT_OTHER_COSTS_TOPIC.intValue()))
            .andExpect(jsonPath("$.postTelegraphSwiftCostsTopic").value(DEFAULT_POST_TELEGRAPH_SWIFT_COSTS_TOPIC.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDraftTypeTopicInfo() throws Exception {
        // Get the draftTypeTopicInfo
        restDraftTypeTopicInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftTypeTopicInfo() throws Exception {
        // Initialize the database
        insertedDraftTypeTopicInfo = draftTypeTopicInfoRepository.saveAndFlush(draftTypeTopicInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTypeTopicInfo
        DraftTypeTopicInfo updatedDraftTypeTopicInfo = draftTypeTopicInfoRepository.findById(draftTypeTopicInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftTypeTopicInfo are not directly saved in db
        em.detach(updatedDraftTypeTopicInfo);
        updatedDraftTypeTopicInfo
            .currencySellCommissionTopic(UPDATED_CURRENCY_SELL_COMMISSION_TOPIC)
            .documentReceiptDisciplinaryTopic(UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_TOPIC)
            .draftMainTopic(UPDATED_DRAFT_MAIN_TOPIC)
            .insuranceCostTopic(UPDATED_INSURANCE_COST_TOPIC)
            .justificationDisciplinaryTopic(UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(UPDATED_OTHER_COSTS_TOPIC)
            .postTelegraphSwiftCostsTopic(UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(updatedDraftTypeTopicInfo);

        restDraftTypeTopicInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftTypeTopicInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeTopicInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftTypeTopicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftTypeTopicInfoToMatchAllProperties(updatedDraftTypeTopicInfo);
    }

    @Test
    @Transactional
    void putNonExistingDraftTypeTopicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeTopicInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeTopicInfo
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftTypeTopicInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftTypeTopicInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeTopicInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeTopicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftTypeTopicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeTopicInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeTopicInfo
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeTopicInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeTopicInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeTopicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftTypeTopicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeTopicInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeTopicInfo
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeTopicInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeTopicInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftTypeTopicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftTypeTopicInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftTypeTopicInfo = draftTypeTopicInfoRepository.saveAndFlush(draftTypeTopicInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTypeTopicInfo using partial update
        DraftTypeTopicInfo partialUpdatedDraftTypeTopicInfo = new DraftTypeTopicInfo();
        partialUpdatedDraftTypeTopicInfo.setId(draftTypeTopicInfo.getId());

        partialUpdatedDraftTypeTopicInfo
            .draftMainTopic(UPDATED_DRAFT_MAIN_TOPIC)
            .insuranceCostTopic(UPDATED_INSURANCE_COST_TOPIC)
            .justificationDisciplinaryTopic(UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(UPDATED_OTHER_COSTS_TOPIC);

        restDraftTypeTopicInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftTypeTopicInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftTypeTopicInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftTypeTopicInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftTypeTopicInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftTypeTopicInfo, draftTypeTopicInfo),
            getPersistedDraftTypeTopicInfo(draftTypeTopicInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftTypeTopicInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftTypeTopicInfo = draftTypeTopicInfoRepository.saveAndFlush(draftTypeTopicInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTypeTopicInfo using partial update
        DraftTypeTopicInfo partialUpdatedDraftTypeTopicInfo = new DraftTypeTopicInfo();
        partialUpdatedDraftTypeTopicInfo.setId(draftTypeTopicInfo.getId());

        partialUpdatedDraftTypeTopicInfo
            .currencySellCommissionTopic(UPDATED_CURRENCY_SELL_COMMISSION_TOPIC)
            .documentReceiptDisciplinaryTopic(UPDATED_DOCUMENT_RECEIPT_DISCIPLINARY_TOPIC)
            .draftMainTopic(UPDATED_DRAFT_MAIN_TOPIC)
            .insuranceCostTopic(UPDATED_INSURANCE_COST_TOPIC)
            .justificationDisciplinaryTopic(UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(UPDATED_OTHER_COSTS_TOPIC)
            .postTelegraphSwiftCostsTopic(UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);

        restDraftTypeTopicInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftTypeTopicInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftTypeTopicInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftTypeTopicInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftTypeTopicInfoUpdatableFieldsEquals(
            partialUpdatedDraftTypeTopicInfo,
            getPersistedDraftTypeTopicInfo(partialUpdatedDraftTypeTopicInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftTypeTopicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeTopicInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeTopicInfo
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftTypeTopicInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftTypeTopicInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftTypeTopicInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeTopicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftTypeTopicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeTopicInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeTopicInfo
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeTopicInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftTypeTopicInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTypeTopicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftTypeTopicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTypeTopicInfo.setId(longCount.incrementAndGet());

        // Create the DraftTypeTopicInfo
        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeTopicInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftTypeTopicInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftTypeTopicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftTypeTopicInfo() throws Exception {
        // Initialize the database
        insertedDraftTypeTopicInfo = draftTypeTopicInfoRepository.saveAndFlush(draftTypeTopicInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftTypeTopicInfo
        restDraftTypeTopicInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftTypeTopicInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftTypeTopicInfoRepository.count();
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

    protected DraftTypeTopicInfo getPersistedDraftTypeTopicInfo(DraftTypeTopicInfo draftTypeTopicInfo) {
        return draftTypeTopicInfoRepository.findById(draftTypeTopicInfo.getId()).orElseThrow();
    }

    protected void assertPersistedDraftTypeTopicInfoToMatchAllProperties(DraftTypeTopicInfo expectedDraftTypeTopicInfo) {
        assertDraftTypeTopicInfoAllPropertiesEquals(expectedDraftTypeTopicInfo, getPersistedDraftTypeTopicInfo(expectedDraftTypeTopicInfo));
    }

    protected void assertPersistedDraftTypeTopicInfoToMatchUpdatableProperties(DraftTypeTopicInfo expectedDraftTypeTopicInfo) {
        assertDraftTypeTopicInfoAllUpdatablePropertiesEquals(
            expectedDraftTypeTopicInfo,
            getPersistedDraftTypeTopicInfo(expectedDraftTypeTopicInfo)
        );
    }
}
