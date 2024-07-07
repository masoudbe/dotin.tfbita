package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.CreditTypeConditionInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.CreditTypeConditionInfo;
import com.dotin.tfbita.repository.CreditTypeConditionInfoRepository;
import com.dotin.tfbita.service.dto.CreditTypeConditionInfoDTO;
import com.dotin.tfbita.service.mapper.CreditTypeConditionInfoMapper;
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
 * Integration tests for the {@link CreditTypeConditionInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreditTypeConditionInfoResourceIT {

    private static final Integer DEFAULT_DURATION_FROM = 1;
    private static final Integer UPDATED_DURATION_FROM = 2;

    private static final Integer DEFAULT_DURATION_TO = 1;
    private static final Integer UPDATED_DURATION_TO = 2;

    private static final BigDecimal DEFAULT_PRICE_FROM = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE_FROM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE_TO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE_TO = new BigDecimal(2);

    private static final Long DEFAULT_JUSTIFICATION_DISCIPLINARY_TOPIC = 1L;
    private static final Long UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC = 2L;

    private static final Long DEFAULT_OPEN_DRAFT_DISCIPLINARY_TOPIC = 1L;
    private static final Long UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC = 2L;

    private static final Long DEFAULT_OTHER_COSTS_TOPIC = 1L;
    private static final Long UPDATED_OTHER_COSTS_TOPIC = 2L;

    private static final Long DEFAULT_POST_TELEGRAPH_SWIFT_COSTS_TOPIC = 1L;
    private static final Long UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC = 2L;

    private static final String ENTITY_API_URL = "/api/credit-type-condition-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CreditTypeConditionInfoRepository creditTypeConditionInfoRepository;

    @Autowired
    private CreditTypeConditionInfoMapper creditTypeConditionInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreditTypeConditionInfoMockMvc;

    private CreditTypeConditionInfo creditTypeConditionInfo;

    private CreditTypeConditionInfo insertedCreditTypeConditionInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditTypeConditionInfo createEntity(EntityManager em) {
        CreditTypeConditionInfo creditTypeConditionInfo = new CreditTypeConditionInfo()
            .durationFrom(DEFAULT_DURATION_FROM)
            .durationTo(DEFAULT_DURATION_TO)
            .priceFrom(DEFAULT_PRICE_FROM)
            .priceTo(DEFAULT_PRICE_TO)
            .justificationDisciplinaryTopic(DEFAULT_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(DEFAULT_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(DEFAULT_OTHER_COSTS_TOPIC)
            .postTelegraphSwiftCostsTopic(DEFAULT_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);
        return creditTypeConditionInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditTypeConditionInfo createUpdatedEntity(EntityManager em) {
        CreditTypeConditionInfo creditTypeConditionInfo = new CreditTypeConditionInfo()
            .durationFrom(UPDATED_DURATION_FROM)
            .durationTo(UPDATED_DURATION_TO)
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .justificationDisciplinaryTopic(UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(UPDATED_OTHER_COSTS_TOPIC)
            .postTelegraphSwiftCostsTopic(UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);
        return creditTypeConditionInfo;
    }

    @BeforeEach
    public void initTest() {
        creditTypeConditionInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCreditTypeConditionInfo != null) {
            creditTypeConditionInfoRepository.delete(insertedCreditTypeConditionInfo);
            insertedCreditTypeConditionInfo = null;
        }
    }

    @Test
    @Transactional
    void createCreditTypeConditionInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CreditTypeConditionInfo
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);
        var returnedCreditTypeConditionInfoDTO = om.readValue(
            restCreditTypeConditionInfoMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(creditTypeConditionInfoDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CreditTypeConditionInfoDTO.class
        );

        // Validate the CreditTypeConditionInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCreditTypeConditionInfo = creditTypeConditionInfoMapper.toEntity(returnedCreditTypeConditionInfoDTO);
        assertCreditTypeConditionInfoUpdatableFieldsEquals(
            returnedCreditTypeConditionInfo,
            getPersistedCreditTypeConditionInfo(returnedCreditTypeConditionInfo)
        );

        insertedCreditTypeConditionInfo = returnedCreditTypeConditionInfo;
    }

    @Test
    @Transactional
    void createCreditTypeConditionInfoWithExistingId() throws Exception {
        // Create the CreditTypeConditionInfo with an existing ID
        creditTypeConditionInfo.setId(1L);
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditTypeConditionInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(creditTypeConditionInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeConditionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreditTypeConditionInfos() throws Exception {
        // Initialize the database
        insertedCreditTypeConditionInfo = creditTypeConditionInfoRepository.saveAndFlush(creditTypeConditionInfo);

        // Get all the creditTypeConditionInfoList
        restCreditTypeConditionInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditTypeConditionInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].durationFrom").value(hasItem(DEFAULT_DURATION_FROM)))
            .andExpect(jsonPath("$.[*].durationTo").value(hasItem(DEFAULT_DURATION_TO)))
            .andExpect(jsonPath("$.[*].priceFrom").value(hasItem(sameNumber(DEFAULT_PRICE_FROM))))
            .andExpect(jsonPath("$.[*].priceTo").value(hasItem(sameNumber(DEFAULT_PRICE_TO))))
            .andExpect(jsonPath("$.[*].justificationDisciplinaryTopic").value(hasItem(DEFAULT_JUSTIFICATION_DISCIPLINARY_TOPIC.intValue())))
            .andExpect(jsonPath("$.[*].openDraftDisciplinaryTopic").value(hasItem(DEFAULT_OPEN_DRAFT_DISCIPLINARY_TOPIC.intValue())))
            .andExpect(jsonPath("$.[*].otherCostsTopic").value(hasItem(DEFAULT_OTHER_COSTS_TOPIC.intValue())))
            .andExpect(jsonPath("$.[*].postTelegraphSwiftCostsTopic").value(hasItem(DEFAULT_POST_TELEGRAPH_SWIFT_COSTS_TOPIC.intValue())));
    }

    @Test
    @Transactional
    void getCreditTypeConditionInfo() throws Exception {
        // Initialize the database
        insertedCreditTypeConditionInfo = creditTypeConditionInfoRepository.saveAndFlush(creditTypeConditionInfo);

        // Get the creditTypeConditionInfo
        restCreditTypeConditionInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, creditTypeConditionInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(creditTypeConditionInfo.getId().intValue()))
            .andExpect(jsonPath("$.durationFrom").value(DEFAULT_DURATION_FROM))
            .andExpect(jsonPath("$.durationTo").value(DEFAULT_DURATION_TO))
            .andExpect(jsonPath("$.priceFrom").value(sameNumber(DEFAULT_PRICE_FROM)))
            .andExpect(jsonPath("$.priceTo").value(sameNumber(DEFAULT_PRICE_TO)))
            .andExpect(jsonPath("$.justificationDisciplinaryTopic").value(DEFAULT_JUSTIFICATION_DISCIPLINARY_TOPIC.intValue()))
            .andExpect(jsonPath("$.openDraftDisciplinaryTopic").value(DEFAULT_OPEN_DRAFT_DISCIPLINARY_TOPIC.intValue()))
            .andExpect(jsonPath("$.otherCostsTopic").value(DEFAULT_OTHER_COSTS_TOPIC.intValue()))
            .andExpect(jsonPath("$.postTelegraphSwiftCostsTopic").value(DEFAULT_POST_TELEGRAPH_SWIFT_COSTS_TOPIC.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCreditTypeConditionInfo() throws Exception {
        // Get the creditTypeConditionInfo
        restCreditTypeConditionInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreditTypeConditionInfo() throws Exception {
        // Initialize the database
        insertedCreditTypeConditionInfo = creditTypeConditionInfoRepository.saveAndFlush(creditTypeConditionInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the creditTypeConditionInfo
        CreditTypeConditionInfo updatedCreditTypeConditionInfo = creditTypeConditionInfoRepository
            .findById(creditTypeConditionInfo.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCreditTypeConditionInfo are not directly saved in db
        em.detach(updatedCreditTypeConditionInfo);
        updatedCreditTypeConditionInfo
            .durationFrom(UPDATED_DURATION_FROM)
            .durationTo(UPDATED_DURATION_TO)
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .justificationDisciplinaryTopic(UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(UPDATED_OTHER_COSTS_TOPIC)
            .postTelegraphSwiftCostsTopic(UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(updatedCreditTypeConditionInfo);

        restCreditTypeConditionInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, creditTypeConditionInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(creditTypeConditionInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreditTypeConditionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCreditTypeConditionInfoToMatchAllProperties(updatedCreditTypeConditionInfo);
    }

    @Test
    @Transactional
    void putNonExistingCreditTypeConditionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeConditionInfo.setId(longCount.incrementAndGet());

        // Create the CreditTypeConditionInfo
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditTypeConditionInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, creditTypeConditionInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(creditTypeConditionInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeConditionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreditTypeConditionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeConditionInfo.setId(longCount.incrementAndGet());

        // Create the CreditTypeConditionInfo
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreditTypeConditionInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(creditTypeConditionInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeConditionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreditTypeConditionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeConditionInfo.setId(longCount.incrementAndGet());

        // Create the CreditTypeConditionInfo
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreditTypeConditionInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(creditTypeConditionInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreditTypeConditionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreditTypeConditionInfoWithPatch() throws Exception {
        // Initialize the database
        insertedCreditTypeConditionInfo = creditTypeConditionInfoRepository.saveAndFlush(creditTypeConditionInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the creditTypeConditionInfo using partial update
        CreditTypeConditionInfo partialUpdatedCreditTypeConditionInfo = new CreditTypeConditionInfo();
        partialUpdatedCreditTypeConditionInfo.setId(creditTypeConditionInfo.getId());

        partialUpdatedCreditTypeConditionInfo
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .justificationDisciplinaryTopic(UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .postTelegraphSwiftCostsTopic(UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);

        restCreditTypeConditionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreditTypeConditionInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCreditTypeConditionInfo))
            )
            .andExpect(status().isOk());

        // Validate the CreditTypeConditionInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCreditTypeConditionInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCreditTypeConditionInfo, creditTypeConditionInfo),
            getPersistedCreditTypeConditionInfo(creditTypeConditionInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateCreditTypeConditionInfoWithPatch() throws Exception {
        // Initialize the database
        insertedCreditTypeConditionInfo = creditTypeConditionInfoRepository.saveAndFlush(creditTypeConditionInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the creditTypeConditionInfo using partial update
        CreditTypeConditionInfo partialUpdatedCreditTypeConditionInfo = new CreditTypeConditionInfo();
        partialUpdatedCreditTypeConditionInfo.setId(creditTypeConditionInfo.getId());

        partialUpdatedCreditTypeConditionInfo
            .durationFrom(UPDATED_DURATION_FROM)
            .durationTo(UPDATED_DURATION_TO)
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .justificationDisciplinaryTopic(UPDATED_JUSTIFICATION_DISCIPLINARY_TOPIC)
            .openDraftDisciplinaryTopic(UPDATED_OPEN_DRAFT_DISCIPLINARY_TOPIC)
            .otherCostsTopic(UPDATED_OTHER_COSTS_TOPIC)
            .postTelegraphSwiftCostsTopic(UPDATED_POST_TELEGRAPH_SWIFT_COSTS_TOPIC);

        restCreditTypeConditionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreditTypeConditionInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCreditTypeConditionInfo))
            )
            .andExpect(status().isOk());

        // Validate the CreditTypeConditionInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCreditTypeConditionInfoUpdatableFieldsEquals(
            partialUpdatedCreditTypeConditionInfo,
            getPersistedCreditTypeConditionInfo(partialUpdatedCreditTypeConditionInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCreditTypeConditionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeConditionInfo.setId(longCount.incrementAndGet());

        // Create the CreditTypeConditionInfo
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditTypeConditionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, creditTypeConditionInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(creditTypeConditionInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeConditionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreditTypeConditionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeConditionInfo.setId(longCount.incrementAndGet());

        // Create the CreditTypeConditionInfo
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreditTypeConditionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(creditTypeConditionInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeConditionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreditTypeConditionInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeConditionInfo.setId(longCount.incrementAndGet());

        // Create the CreditTypeConditionInfo
        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreditTypeConditionInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(creditTypeConditionInfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreditTypeConditionInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreditTypeConditionInfo() throws Exception {
        // Initialize the database
        insertedCreditTypeConditionInfo = creditTypeConditionInfoRepository.saveAndFlush(creditTypeConditionInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the creditTypeConditionInfo
        restCreditTypeConditionInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, creditTypeConditionInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return creditTypeConditionInfoRepository.count();
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

    protected CreditTypeConditionInfo getPersistedCreditTypeConditionInfo(CreditTypeConditionInfo creditTypeConditionInfo) {
        return creditTypeConditionInfoRepository.findById(creditTypeConditionInfo.getId()).orElseThrow();
    }

    protected void assertPersistedCreditTypeConditionInfoToMatchAllProperties(CreditTypeConditionInfo expectedCreditTypeConditionInfo) {
        assertCreditTypeConditionInfoAllPropertiesEquals(
            expectedCreditTypeConditionInfo,
            getPersistedCreditTypeConditionInfo(expectedCreditTypeConditionInfo)
        );
    }

    protected void assertPersistedCreditTypeConditionInfoToMatchUpdatableProperties(
        CreditTypeConditionInfo expectedCreditTypeConditionInfo
    ) {
        assertCreditTypeConditionInfoAllUpdatablePropertiesEquals(
            expectedCreditTypeConditionInfo,
            getPersistedCreditTypeConditionInfo(expectedCreditTypeConditionInfo)
        );
    }
}
