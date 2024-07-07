package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.CreditTypeConditionAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.CreditTypeCondition;
import com.dotin.tfbita.repository.CreditTypeConditionRepository;
import com.dotin.tfbita.service.dto.CreditTypeConditionDTO;
import com.dotin.tfbita.service.mapper.CreditTypeConditionMapper;
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
 * Integration tests for the {@link CreditTypeConditionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CreditTypeConditionResourceIT {

    private static final BigDecimal DEFAULT_ASSURANCE_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ASSURANCE_PERCENTAGE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_COMMISSION_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_COMMISSION_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CUSTOMER_PREPAYMENT_RATE_FROM = new BigDecimal(1);
    private static final BigDecimal UPDATED_CUSTOMER_PREPAYMENT_RATE_FROM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CUSTOMER_PREPAYMENT_RATE_TO = new BigDecimal(1);
    private static final BigDecimal UPDATED_CUSTOMER_PREPAYMENT_RATE_TO = new BigDecimal(2);

    private static final Integer DEFAULT_DURATION_FROM = 1;
    private static final Integer UPDATED_DURATION_FROM = 2;

    private static final Integer DEFAULT_DURATION_TO = 1;
    private static final Integer UPDATED_DURATION_TO = 2;

    private static final BigDecimal DEFAULT_ORDER_REGISTRATION_RIGHT_FROM = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORDER_REGISTRATION_RIGHT_FROM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ORDER_REGISTRATION_RIGHT_TO = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORDER_REGISTRATION_RIGHT_TO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_POST_SUSPENSION_PERIOD_PENALTY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_POST_SUSPENSION_PERIOD_PENALTY_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE_FROM = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE_FROM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE_TO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE_TO = new BigDecimal(2);

    private static final Integer DEFAULT_SUSPENSION_DURATION_FROM = 1;
    private static final Integer UPDATED_SUSPENSION_DURATION_FROM = 2;

    private static final Integer DEFAULT_SUSPENSION_DURATION_TO = 1;
    private static final Integer UPDATED_SUSPENSION_DURATION_TO = 2;

    private static final BigDecimal DEFAULT_SUSPENSION_PERIOD_INTEREST_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUSPENSION_PERIOD_INTEREST_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_UPDATE_COMMISSION_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UPDATE_COMMISSION_RATE = new BigDecimal(2);

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/credit-type-conditions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CreditTypeConditionRepository creditTypeConditionRepository;

    @Autowired
    private CreditTypeConditionMapper creditTypeConditionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCreditTypeConditionMockMvc;

    private CreditTypeCondition creditTypeCondition;

    private CreditTypeCondition insertedCreditTypeCondition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditTypeCondition createEntity(EntityManager em) {
        CreditTypeCondition creditTypeCondition = new CreditTypeCondition()
            .assurancePercentage(DEFAULT_ASSURANCE_PERCENTAGE)
            .commissionRate(DEFAULT_COMMISSION_RATE)
            .customerPrepaymentRateFrom(DEFAULT_CUSTOMER_PREPAYMENT_RATE_FROM)
            .customerPrepaymentRateTo(DEFAULT_CUSTOMER_PREPAYMENT_RATE_TO)
            .durationFrom(DEFAULT_DURATION_FROM)
            .durationTo(DEFAULT_DURATION_TO)
            .orderRegistrationRightFrom(DEFAULT_ORDER_REGISTRATION_RIGHT_FROM)
            .orderRegistrationRightTo(DEFAULT_ORDER_REGISTRATION_RIGHT_TO)
            .postSuspensionPeriodPenaltyRate(DEFAULT_POST_SUSPENSION_PERIOD_PENALTY_RATE)
            .priceFrom(DEFAULT_PRICE_FROM)
            .priceTo(DEFAULT_PRICE_TO)
            .suspensionDurationFrom(DEFAULT_SUSPENSION_DURATION_FROM)
            .suspensionDurationTo(DEFAULT_SUSPENSION_DURATION_TO)
            .suspensionPeriodInterestRate(DEFAULT_SUSPENSION_PERIOD_INTEREST_RATE)
            .updateCommissionRate(DEFAULT_UPDATE_COMMISSION_RATE)
            .currencyCode(DEFAULT_CURRENCY_CODE);
        return creditTypeCondition;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditTypeCondition createUpdatedEntity(EntityManager em) {
        CreditTypeCondition creditTypeCondition = new CreditTypeCondition()
            .assurancePercentage(UPDATED_ASSURANCE_PERCENTAGE)
            .commissionRate(UPDATED_COMMISSION_RATE)
            .customerPrepaymentRateFrom(UPDATED_CUSTOMER_PREPAYMENT_RATE_FROM)
            .customerPrepaymentRateTo(UPDATED_CUSTOMER_PREPAYMENT_RATE_TO)
            .durationFrom(UPDATED_DURATION_FROM)
            .durationTo(UPDATED_DURATION_TO)
            .orderRegistrationRightFrom(UPDATED_ORDER_REGISTRATION_RIGHT_FROM)
            .orderRegistrationRightTo(UPDATED_ORDER_REGISTRATION_RIGHT_TO)
            .postSuspensionPeriodPenaltyRate(UPDATED_POST_SUSPENSION_PERIOD_PENALTY_RATE)
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .suspensionDurationFrom(UPDATED_SUSPENSION_DURATION_FROM)
            .suspensionDurationTo(UPDATED_SUSPENSION_DURATION_TO)
            .suspensionPeriodInterestRate(UPDATED_SUSPENSION_PERIOD_INTEREST_RATE)
            .updateCommissionRate(UPDATED_UPDATE_COMMISSION_RATE)
            .currencyCode(UPDATED_CURRENCY_CODE);
        return creditTypeCondition;
    }

    @BeforeEach
    public void initTest() {
        creditTypeCondition = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCreditTypeCondition != null) {
            creditTypeConditionRepository.delete(insertedCreditTypeCondition);
            insertedCreditTypeCondition = null;
        }
    }

    @Test
    @Transactional
    void createCreditTypeCondition() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CreditTypeCondition
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(creditTypeCondition);
        var returnedCreditTypeConditionDTO = om.readValue(
            restCreditTypeConditionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(creditTypeConditionDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CreditTypeConditionDTO.class
        );

        // Validate the CreditTypeCondition in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCreditTypeCondition = creditTypeConditionMapper.toEntity(returnedCreditTypeConditionDTO);
        assertCreditTypeConditionUpdatableFieldsEquals(
            returnedCreditTypeCondition,
            getPersistedCreditTypeCondition(returnedCreditTypeCondition)
        );

        insertedCreditTypeCondition = returnedCreditTypeCondition;
    }

    @Test
    @Transactional
    void createCreditTypeConditionWithExistingId() throws Exception {
        // Create the CreditTypeCondition with an existing ID
        creditTypeCondition.setId(1L);
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(creditTypeCondition);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCreditTypeConditionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(creditTypeConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCreditTypeConditions() throws Exception {
        // Initialize the database
        insertedCreditTypeCondition = creditTypeConditionRepository.saveAndFlush(creditTypeCondition);

        // Get all the creditTypeConditionList
        restCreditTypeConditionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(creditTypeCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].assurancePercentage").value(hasItem(sameNumber(DEFAULT_ASSURANCE_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].commissionRate").value(hasItem(sameNumber(DEFAULT_COMMISSION_RATE))))
            .andExpect(jsonPath("$.[*].customerPrepaymentRateFrom").value(hasItem(sameNumber(DEFAULT_CUSTOMER_PREPAYMENT_RATE_FROM))))
            .andExpect(jsonPath("$.[*].customerPrepaymentRateTo").value(hasItem(sameNumber(DEFAULT_CUSTOMER_PREPAYMENT_RATE_TO))))
            .andExpect(jsonPath("$.[*].durationFrom").value(hasItem(DEFAULT_DURATION_FROM)))
            .andExpect(jsonPath("$.[*].durationTo").value(hasItem(DEFAULT_DURATION_TO)))
            .andExpect(jsonPath("$.[*].orderRegistrationRightFrom").value(hasItem(sameNumber(DEFAULT_ORDER_REGISTRATION_RIGHT_FROM))))
            .andExpect(jsonPath("$.[*].orderRegistrationRightTo").value(hasItem(sameNumber(DEFAULT_ORDER_REGISTRATION_RIGHT_TO))))
            .andExpect(
                jsonPath("$.[*].postSuspensionPeriodPenaltyRate").value(hasItem(sameNumber(DEFAULT_POST_SUSPENSION_PERIOD_PENALTY_RATE)))
            )
            .andExpect(jsonPath("$.[*].priceFrom").value(hasItem(sameNumber(DEFAULT_PRICE_FROM))))
            .andExpect(jsonPath("$.[*].priceTo").value(hasItem(sameNumber(DEFAULT_PRICE_TO))))
            .andExpect(jsonPath("$.[*].suspensionDurationFrom").value(hasItem(DEFAULT_SUSPENSION_DURATION_FROM)))
            .andExpect(jsonPath("$.[*].suspensionDurationTo").value(hasItem(DEFAULT_SUSPENSION_DURATION_TO)))
            .andExpect(jsonPath("$.[*].suspensionPeriodInterestRate").value(hasItem(sameNumber(DEFAULT_SUSPENSION_PERIOD_INTEREST_RATE))))
            .andExpect(jsonPath("$.[*].updateCommissionRate").value(hasItem(sameNumber(DEFAULT_UPDATE_COMMISSION_RATE))))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)));
    }

    @Test
    @Transactional
    void getCreditTypeCondition() throws Exception {
        // Initialize the database
        insertedCreditTypeCondition = creditTypeConditionRepository.saveAndFlush(creditTypeCondition);

        // Get the creditTypeCondition
        restCreditTypeConditionMockMvc
            .perform(get(ENTITY_API_URL_ID, creditTypeCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(creditTypeCondition.getId().intValue()))
            .andExpect(jsonPath("$.assurancePercentage").value(sameNumber(DEFAULT_ASSURANCE_PERCENTAGE)))
            .andExpect(jsonPath("$.commissionRate").value(sameNumber(DEFAULT_COMMISSION_RATE)))
            .andExpect(jsonPath("$.customerPrepaymentRateFrom").value(sameNumber(DEFAULT_CUSTOMER_PREPAYMENT_RATE_FROM)))
            .andExpect(jsonPath("$.customerPrepaymentRateTo").value(sameNumber(DEFAULT_CUSTOMER_PREPAYMENT_RATE_TO)))
            .andExpect(jsonPath("$.durationFrom").value(DEFAULT_DURATION_FROM))
            .andExpect(jsonPath("$.durationTo").value(DEFAULT_DURATION_TO))
            .andExpect(jsonPath("$.orderRegistrationRightFrom").value(sameNumber(DEFAULT_ORDER_REGISTRATION_RIGHT_FROM)))
            .andExpect(jsonPath("$.orderRegistrationRightTo").value(sameNumber(DEFAULT_ORDER_REGISTRATION_RIGHT_TO)))
            .andExpect(jsonPath("$.postSuspensionPeriodPenaltyRate").value(sameNumber(DEFAULT_POST_SUSPENSION_PERIOD_PENALTY_RATE)))
            .andExpect(jsonPath("$.priceFrom").value(sameNumber(DEFAULT_PRICE_FROM)))
            .andExpect(jsonPath("$.priceTo").value(sameNumber(DEFAULT_PRICE_TO)))
            .andExpect(jsonPath("$.suspensionDurationFrom").value(DEFAULT_SUSPENSION_DURATION_FROM))
            .andExpect(jsonPath("$.suspensionDurationTo").value(DEFAULT_SUSPENSION_DURATION_TO))
            .andExpect(jsonPath("$.suspensionPeriodInterestRate").value(sameNumber(DEFAULT_SUSPENSION_PERIOD_INTEREST_RATE)))
            .andExpect(jsonPath("$.updateCommissionRate").value(sameNumber(DEFAULT_UPDATE_COMMISSION_RATE)))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE));
    }

    @Test
    @Transactional
    void getNonExistingCreditTypeCondition() throws Exception {
        // Get the creditTypeCondition
        restCreditTypeConditionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCreditTypeCondition() throws Exception {
        // Initialize the database
        insertedCreditTypeCondition = creditTypeConditionRepository.saveAndFlush(creditTypeCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the creditTypeCondition
        CreditTypeCondition updatedCreditTypeCondition = creditTypeConditionRepository.findById(creditTypeCondition.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCreditTypeCondition are not directly saved in db
        em.detach(updatedCreditTypeCondition);
        updatedCreditTypeCondition
            .assurancePercentage(UPDATED_ASSURANCE_PERCENTAGE)
            .commissionRate(UPDATED_COMMISSION_RATE)
            .customerPrepaymentRateFrom(UPDATED_CUSTOMER_PREPAYMENT_RATE_FROM)
            .customerPrepaymentRateTo(UPDATED_CUSTOMER_PREPAYMENT_RATE_TO)
            .durationFrom(UPDATED_DURATION_FROM)
            .durationTo(UPDATED_DURATION_TO)
            .orderRegistrationRightFrom(UPDATED_ORDER_REGISTRATION_RIGHT_FROM)
            .orderRegistrationRightTo(UPDATED_ORDER_REGISTRATION_RIGHT_TO)
            .postSuspensionPeriodPenaltyRate(UPDATED_POST_SUSPENSION_PERIOD_PENALTY_RATE)
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .suspensionDurationFrom(UPDATED_SUSPENSION_DURATION_FROM)
            .suspensionDurationTo(UPDATED_SUSPENSION_DURATION_TO)
            .suspensionPeriodInterestRate(UPDATED_SUSPENSION_PERIOD_INTEREST_RATE)
            .updateCommissionRate(UPDATED_UPDATE_COMMISSION_RATE)
            .currencyCode(UPDATED_CURRENCY_CODE);
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(updatedCreditTypeCondition);

        restCreditTypeConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, creditTypeConditionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(creditTypeConditionDTO))
            )
            .andExpect(status().isOk());

        // Validate the CreditTypeCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCreditTypeConditionToMatchAllProperties(updatedCreditTypeCondition);
    }

    @Test
    @Transactional
    void putNonExistingCreditTypeCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeCondition.setId(longCount.incrementAndGet());

        // Create the CreditTypeCondition
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(creditTypeCondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditTypeConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, creditTypeConditionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(creditTypeConditionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCreditTypeCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeCondition.setId(longCount.incrementAndGet());

        // Create the CreditTypeCondition
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(creditTypeCondition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreditTypeConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(creditTypeConditionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCreditTypeCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeCondition.setId(longCount.incrementAndGet());

        // Create the CreditTypeCondition
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(creditTypeCondition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreditTypeConditionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(creditTypeConditionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreditTypeCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCreditTypeConditionWithPatch() throws Exception {
        // Initialize the database
        insertedCreditTypeCondition = creditTypeConditionRepository.saveAndFlush(creditTypeCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the creditTypeCondition using partial update
        CreditTypeCondition partialUpdatedCreditTypeCondition = new CreditTypeCondition();
        partialUpdatedCreditTypeCondition.setId(creditTypeCondition.getId());

        partialUpdatedCreditTypeCondition
            .commissionRate(UPDATED_COMMISSION_RATE)
            .customerPrepaymentRateTo(UPDATED_CUSTOMER_PREPAYMENT_RATE_TO)
            .durationFrom(UPDATED_DURATION_FROM)
            .durationTo(UPDATED_DURATION_TO)
            .postSuspensionPeriodPenaltyRate(UPDATED_POST_SUSPENSION_PERIOD_PENALTY_RATE)
            .priceTo(UPDATED_PRICE_TO)
            .suspensionDurationFrom(UPDATED_SUSPENSION_DURATION_FROM)
            .suspensionDurationTo(UPDATED_SUSPENSION_DURATION_TO)
            .suspensionPeriodInterestRate(UPDATED_SUSPENSION_PERIOD_INTEREST_RATE);

        restCreditTypeConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreditTypeCondition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCreditTypeCondition))
            )
            .andExpect(status().isOk());

        // Validate the CreditTypeCondition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCreditTypeConditionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCreditTypeCondition, creditTypeCondition),
            getPersistedCreditTypeCondition(creditTypeCondition)
        );
    }

    @Test
    @Transactional
    void fullUpdateCreditTypeConditionWithPatch() throws Exception {
        // Initialize the database
        insertedCreditTypeCondition = creditTypeConditionRepository.saveAndFlush(creditTypeCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the creditTypeCondition using partial update
        CreditTypeCondition partialUpdatedCreditTypeCondition = new CreditTypeCondition();
        partialUpdatedCreditTypeCondition.setId(creditTypeCondition.getId());

        partialUpdatedCreditTypeCondition
            .assurancePercentage(UPDATED_ASSURANCE_PERCENTAGE)
            .commissionRate(UPDATED_COMMISSION_RATE)
            .customerPrepaymentRateFrom(UPDATED_CUSTOMER_PREPAYMENT_RATE_FROM)
            .customerPrepaymentRateTo(UPDATED_CUSTOMER_PREPAYMENT_RATE_TO)
            .durationFrom(UPDATED_DURATION_FROM)
            .durationTo(UPDATED_DURATION_TO)
            .orderRegistrationRightFrom(UPDATED_ORDER_REGISTRATION_RIGHT_FROM)
            .orderRegistrationRightTo(UPDATED_ORDER_REGISTRATION_RIGHT_TO)
            .postSuspensionPeriodPenaltyRate(UPDATED_POST_SUSPENSION_PERIOD_PENALTY_RATE)
            .priceFrom(UPDATED_PRICE_FROM)
            .priceTo(UPDATED_PRICE_TO)
            .suspensionDurationFrom(UPDATED_SUSPENSION_DURATION_FROM)
            .suspensionDurationTo(UPDATED_SUSPENSION_DURATION_TO)
            .suspensionPeriodInterestRate(UPDATED_SUSPENSION_PERIOD_INTEREST_RATE)
            .updateCommissionRate(UPDATED_UPDATE_COMMISSION_RATE)
            .currencyCode(UPDATED_CURRENCY_CODE);

        restCreditTypeConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCreditTypeCondition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCreditTypeCondition))
            )
            .andExpect(status().isOk());

        // Validate the CreditTypeCondition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCreditTypeConditionUpdatableFieldsEquals(
            partialUpdatedCreditTypeCondition,
            getPersistedCreditTypeCondition(partialUpdatedCreditTypeCondition)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCreditTypeCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeCondition.setId(longCount.incrementAndGet());

        // Create the CreditTypeCondition
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(creditTypeCondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCreditTypeConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, creditTypeConditionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(creditTypeConditionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCreditTypeCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeCondition.setId(longCount.incrementAndGet());

        // Create the CreditTypeCondition
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(creditTypeCondition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreditTypeConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(creditTypeConditionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CreditTypeCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCreditTypeCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        creditTypeCondition.setId(longCount.incrementAndGet());

        // Create the CreditTypeCondition
        CreditTypeConditionDTO creditTypeConditionDTO = creditTypeConditionMapper.toDto(creditTypeCondition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCreditTypeConditionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(creditTypeConditionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CreditTypeCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCreditTypeCondition() throws Exception {
        // Initialize the database
        insertedCreditTypeCondition = creditTypeConditionRepository.saveAndFlush(creditTypeCondition);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the creditTypeCondition
        restCreditTypeConditionMockMvc
            .perform(delete(ENTITY_API_URL_ID, creditTypeCondition.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return creditTypeConditionRepository.count();
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

    protected CreditTypeCondition getPersistedCreditTypeCondition(CreditTypeCondition creditTypeCondition) {
        return creditTypeConditionRepository.findById(creditTypeCondition.getId()).orElseThrow();
    }

    protected void assertPersistedCreditTypeConditionToMatchAllProperties(CreditTypeCondition expectedCreditTypeCondition) {
        assertCreditTypeConditionAllPropertiesEquals(
            expectedCreditTypeCondition,
            getPersistedCreditTypeCondition(expectedCreditTypeCondition)
        );
    }

    protected void assertPersistedCreditTypeConditionToMatchUpdatableProperties(CreditTypeCondition expectedCreditTypeCondition) {
        assertCreditTypeConditionAllUpdatablePropertiesEquals(
            expectedCreditTypeCondition,
            getPersistedCreditTypeCondition(expectedCreditTypeCondition)
        );
    }
}
