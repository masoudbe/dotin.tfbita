package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.JustificationDeductionAmountPartAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.JustificationDeductionAmountPart;
import com.dotin.tfbita.repository.JustificationDeductionAmountPartRepository;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountPartDTO;
import com.dotin.tfbita.service.mapper.JustificationDeductionAmountPartMapper;
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
 * Integration tests for the {@link JustificationDeductionAmountPartResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JustificationDeductionAmountPartResourceIT {

    private static final String DEFAULT_RECEIVE_TRANSACTION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_TRANSACTION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RETURN_TRANSACTION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RETURN_TRANSACTION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT_BASED_ON_RIAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT_BASED_ON_RIAL = new BigDecimal(2);

    private static final String DEFAULT_DEPOSIT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVE_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_RATE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_RATE_DATE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_SELL_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SELL_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BUY_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUY_RATE = new BigDecimal(2);

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/justification-deduction-amount-parts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private JustificationDeductionAmountPartRepository justificationDeductionAmountPartRepository;

    @Autowired
    private JustificationDeductionAmountPartMapper justificationDeductionAmountPartMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJustificationDeductionAmountPartMockMvc;

    private JustificationDeductionAmountPart justificationDeductionAmountPart;

    private JustificationDeductionAmountPart insertedJustificationDeductionAmountPart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JustificationDeductionAmountPart createEntity(EntityManager em) {
        JustificationDeductionAmountPart justificationDeductionAmountPart = new JustificationDeductionAmountPart()
            .receiveTransactionCode(DEFAULT_RECEIVE_TRANSACTION_CODE)
            .returnTransactionCode(DEFAULT_RETURN_TRANSACTION_CODE)
            .date(DEFAULT_DATE)
            .amount(DEFAULT_AMOUNT)
            .amountBasedOnRial(DEFAULT_AMOUNT_BASED_ON_RIAL)
            .depositNumber(DEFAULT_DEPOSIT_NUMBER)
            .receiveCurrencyCode(DEFAULT_RECEIVE_CURRENCY_CODE)
            .currencyRateDate(DEFAULT_CURRENCY_RATE_DATE)
            .sellRate(DEFAULT_SELL_RATE)
            .buyRate(DEFAULT_BUY_RATE)
            .comment(DEFAULT_COMMENT);
        return justificationDeductionAmountPart;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JustificationDeductionAmountPart createUpdatedEntity(EntityManager em) {
        JustificationDeductionAmountPart justificationDeductionAmountPart = new JustificationDeductionAmountPart()
            .receiveTransactionCode(UPDATED_RECEIVE_TRANSACTION_CODE)
            .returnTransactionCode(UPDATED_RETURN_TRANSACTION_CODE)
            .date(UPDATED_DATE)
            .amount(UPDATED_AMOUNT)
            .amountBasedOnRial(UPDATED_AMOUNT_BASED_ON_RIAL)
            .depositNumber(UPDATED_DEPOSIT_NUMBER)
            .receiveCurrencyCode(UPDATED_RECEIVE_CURRENCY_CODE)
            .currencyRateDate(UPDATED_CURRENCY_RATE_DATE)
            .sellRate(UPDATED_SELL_RATE)
            .buyRate(UPDATED_BUY_RATE)
            .comment(UPDATED_COMMENT);
        return justificationDeductionAmountPart;
    }

    @BeforeEach
    public void initTest() {
        justificationDeductionAmountPart = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedJustificationDeductionAmountPart != null) {
            justificationDeductionAmountPartRepository.delete(insertedJustificationDeductionAmountPart);
            insertedJustificationDeductionAmountPart = null;
        }
    }

    @Test
    @Transactional
    void createJustificationDeductionAmountPart() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the JustificationDeductionAmountPart
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            justificationDeductionAmountPart
        );
        var returnedJustificationDeductionAmountPartDTO = om.readValue(
            restJustificationDeductionAmountPartMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            JustificationDeductionAmountPartDTO.class
        );

        // Validate the JustificationDeductionAmountPart in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedJustificationDeductionAmountPart = justificationDeductionAmountPartMapper.toEntity(
            returnedJustificationDeductionAmountPartDTO
        );
        assertJustificationDeductionAmountPartUpdatableFieldsEquals(
            returnedJustificationDeductionAmountPart,
            getPersistedJustificationDeductionAmountPart(returnedJustificationDeductionAmountPart)
        );

        insertedJustificationDeductionAmountPart = returnedJustificationDeductionAmountPart;
    }

    @Test
    @Transactional
    void createJustificationDeductionAmountPartWithExistingId() throws Exception {
        // Create the JustificationDeductionAmountPart with an existing ID
        justificationDeductionAmountPart.setId(1L);
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            justificationDeductionAmountPart
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJustificationDeductionAmountPartMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmountPart in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJustificationDeductionAmountParts() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmountPart = justificationDeductionAmountPartRepository.saveAndFlush(
            justificationDeductionAmountPart
        );

        // Get all the justificationDeductionAmountPartList
        restJustificationDeductionAmountPartMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(justificationDeductionAmountPart.getId().intValue())))
            .andExpect(jsonPath("$.[*].receiveTransactionCode").value(hasItem(DEFAULT_RECEIVE_TRANSACTION_CODE)))
            .andExpect(jsonPath("$.[*].returnTransactionCode").value(hasItem(DEFAULT_RETURN_TRANSACTION_CODE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))))
            .andExpect(jsonPath("$.[*].amountBasedOnRial").value(hasItem(sameNumber(DEFAULT_AMOUNT_BASED_ON_RIAL))))
            .andExpect(jsonPath("$.[*].depositNumber").value(hasItem(DEFAULT_DEPOSIT_NUMBER)))
            .andExpect(jsonPath("$.[*].receiveCurrencyCode").value(hasItem(DEFAULT_RECEIVE_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].currencyRateDate").value(hasItem(DEFAULT_CURRENCY_RATE_DATE)))
            .andExpect(jsonPath("$.[*].sellRate").value(hasItem(sameNumber(DEFAULT_SELL_RATE))))
            .andExpect(jsonPath("$.[*].buyRate").value(hasItem(sameNumber(DEFAULT_BUY_RATE))))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
    }

    @Test
    @Transactional
    void getJustificationDeductionAmountPart() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmountPart = justificationDeductionAmountPartRepository.saveAndFlush(
            justificationDeductionAmountPart
        );

        // Get the justificationDeductionAmountPart
        restJustificationDeductionAmountPartMockMvc
            .perform(get(ENTITY_API_URL_ID, justificationDeductionAmountPart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(justificationDeductionAmountPart.getId().intValue()))
            .andExpect(jsonPath("$.receiveTransactionCode").value(DEFAULT_RECEIVE_TRANSACTION_CODE))
            .andExpect(jsonPath("$.returnTransactionCode").value(DEFAULT_RETURN_TRANSACTION_CODE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.amountBasedOnRial").value(sameNumber(DEFAULT_AMOUNT_BASED_ON_RIAL)))
            .andExpect(jsonPath("$.depositNumber").value(DEFAULT_DEPOSIT_NUMBER))
            .andExpect(jsonPath("$.receiveCurrencyCode").value(DEFAULT_RECEIVE_CURRENCY_CODE))
            .andExpect(jsonPath("$.currencyRateDate").value(DEFAULT_CURRENCY_RATE_DATE))
            .andExpect(jsonPath("$.sellRate").value(sameNumber(DEFAULT_SELL_RATE)))
            .andExpect(jsonPath("$.buyRate").value(sameNumber(DEFAULT_BUY_RATE)))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingJustificationDeductionAmountPart() throws Exception {
        // Get the justificationDeductionAmountPart
        restJustificationDeductionAmountPartMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingJustificationDeductionAmountPart() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmountPart = justificationDeductionAmountPartRepository.saveAndFlush(
            justificationDeductionAmountPart
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionAmountPart
        JustificationDeductionAmountPart updatedJustificationDeductionAmountPart = justificationDeductionAmountPartRepository
            .findById(justificationDeductionAmountPart.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedJustificationDeductionAmountPart are not directly saved in db
        em.detach(updatedJustificationDeductionAmountPart);
        updatedJustificationDeductionAmountPart
            .receiveTransactionCode(UPDATED_RECEIVE_TRANSACTION_CODE)
            .returnTransactionCode(UPDATED_RETURN_TRANSACTION_CODE)
            .date(UPDATED_DATE)
            .amount(UPDATED_AMOUNT)
            .amountBasedOnRial(UPDATED_AMOUNT_BASED_ON_RIAL)
            .depositNumber(UPDATED_DEPOSIT_NUMBER)
            .receiveCurrencyCode(UPDATED_RECEIVE_CURRENCY_CODE)
            .currencyRateDate(UPDATED_CURRENCY_RATE_DATE)
            .sellRate(UPDATED_SELL_RATE)
            .buyRate(UPDATED_BUY_RATE)
            .comment(UPDATED_COMMENT);
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            updatedJustificationDeductionAmountPart
        );

        restJustificationDeductionAmountPartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, justificationDeductionAmountPartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionAmountPart in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedJustificationDeductionAmountPartToMatchAllProperties(updatedJustificationDeductionAmountPart);
    }

    @Test
    @Transactional
    void putNonExistingJustificationDeductionAmountPart() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmountPart.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmountPart
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            justificationDeductionAmountPart
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountPartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, justificationDeductionAmountPartDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmountPart in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJustificationDeductionAmountPart() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmountPart.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmountPart
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            justificationDeductionAmountPart
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountPartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmountPart in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJustificationDeductionAmountPart() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmountPart.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmountPart
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            justificationDeductionAmountPart
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountPartMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JustificationDeductionAmountPart in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJustificationDeductionAmountPartWithPatch() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmountPart = justificationDeductionAmountPartRepository.saveAndFlush(
            justificationDeductionAmountPart
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionAmountPart using partial update
        JustificationDeductionAmountPart partialUpdatedJustificationDeductionAmountPart = new JustificationDeductionAmountPart();
        partialUpdatedJustificationDeductionAmountPart.setId(justificationDeductionAmountPart.getId());

        partialUpdatedJustificationDeductionAmountPart
            .receiveTransactionCode(UPDATED_RECEIVE_TRANSACTION_CODE)
            .amount(UPDATED_AMOUNT)
            .amountBasedOnRial(UPDATED_AMOUNT_BASED_ON_RIAL)
            .receiveCurrencyCode(UPDATED_RECEIVE_CURRENCY_CODE)
            .currencyRateDate(UPDATED_CURRENCY_RATE_DATE)
            .buyRate(UPDATED_BUY_RATE)
            .comment(UPDATED_COMMENT);

        restJustificationDeductionAmountPartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJustificationDeductionAmountPart.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedJustificationDeductionAmountPart))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionAmountPart in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertJustificationDeductionAmountPartUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedJustificationDeductionAmountPart, justificationDeductionAmountPart),
            getPersistedJustificationDeductionAmountPart(justificationDeductionAmountPart)
        );
    }

    @Test
    @Transactional
    void fullUpdateJustificationDeductionAmountPartWithPatch() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmountPart = justificationDeductionAmountPartRepository.saveAndFlush(
            justificationDeductionAmountPart
        );

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionAmountPart using partial update
        JustificationDeductionAmountPart partialUpdatedJustificationDeductionAmountPart = new JustificationDeductionAmountPart();
        partialUpdatedJustificationDeductionAmountPart.setId(justificationDeductionAmountPart.getId());

        partialUpdatedJustificationDeductionAmountPart
            .receiveTransactionCode(UPDATED_RECEIVE_TRANSACTION_CODE)
            .returnTransactionCode(UPDATED_RETURN_TRANSACTION_CODE)
            .date(UPDATED_DATE)
            .amount(UPDATED_AMOUNT)
            .amountBasedOnRial(UPDATED_AMOUNT_BASED_ON_RIAL)
            .depositNumber(UPDATED_DEPOSIT_NUMBER)
            .receiveCurrencyCode(UPDATED_RECEIVE_CURRENCY_CODE)
            .currencyRateDate(UPDATED_CURRENCY_RATE_DATE)
            .sellRate(UPDATED_SELL_RATE)
            .buyRate(UPDATED_BUY_RATE)
            .comment(UPDATED_COMMENT);

        restJustificationDeductionAmountPartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJustificationDeductionAmountPart.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedJustificationDeductionAmountPart))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionAmountPart in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertJustificationDeductionAmountPartUpdatableFieldsEquals(
            partialUpdatedJustificationDeductionAmountPart,
            getPersistedJustificationDeductionAmountPart(partialUpdatedJustificationDeductionAmountPart)
        );
    }

    @Test
    @Transactional
    void patchNonExistingJustificationDeductionAmountPart() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmountPart.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmountPart
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            justificationDeductionAmountPart
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountPartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, justificationDeductionAmountPartDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmountPart in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJustificationDeductionAmountPart() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmountPart.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmountPart
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            justificationDeductionAmountPart
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountPartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmountPart in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJustificationDeductionAmountPart() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmountPart.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmountPart
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = justificationDeductionAmountPartMapper.toDto(
            justificationDeductionAmountPart
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountPartMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionAmountPartDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JustificationDeductionAmountPart in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJustificationDeductionAmountPart() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmountPart = justificationDeductionAmountPartRepository.saveAndFlush(
            justificationDeductionAmountPart
        );

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the justificationDeductionAmountPart
        restJustificationDeductionAmountPartMockMvc
            .perform(delete(ENTITY_API_URL_ID, justificationDeductionAmountPart.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return justificationDeductionAmountPartRepository.count();
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

    protected JustificationDeductionAmountPart getPersistedJustificationDeductionAmountPart(
        JustificationDeductionAmountPart justificationDeductionAmountPart
    ) {
        return justificationDeductionAmountPartRepository.findById(justificationDeductionAmountPart.getId()).orElseThrow();
    }

    protected void assertPersistedJustificationDeductionAmountPartToMatchAllProperties(
        JustificationDeductionAmountPart expectedJustificationDeductionAmountPart
    ) {
        assertJustificationDeductionAmountPartAllPropertiesEquals(
            expectedJustificationDeductionAmountPart,
            getPersistedJustificationDeductionAmountPart(expectedJustificationDeductionAmountPart)
        );
    }

    protected void assertPersistedJustificationDeductionAmountPartToMatchUpdatableProperties(
        JustificationDeductionAmountPart expectedJustificationDeductionAmountPart
    ) {
        assertJustificationDeductionAmountPartAllUpdatablePropertiesEquals(
            expectedJustificationDeductionAmountPart,
            getPersistedJustificationDeductionAmountPart(expectedJustificationDeductionAmountPart)
        );
    }
}
