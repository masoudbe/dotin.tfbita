package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.JustificationDeductionAmountAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.JustificationDeductionAmount;
import com.dotin.tfbita.repository.JustificationDeductionAmountRepository;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountDTO;
import com.dotin.tfbita.service.mapper.JustificationDeductionAmountMapper;
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
 * Integration tests for the {@link JustificationDeductionAmountResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JustificationDeductionAmountResourceIT {

    private static final BigDecimal DEFAULT_DEDUCTION_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCTION_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REMAINING_DEDUCTION_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_REMAINING_DEDUCTION_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_RECEIVED_DEDUCTION_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_RECEIVED_DEDUCTION_AMOUNT = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/justification-deduction-amounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private JustificationDeductionAmountRepository justificationDeductionAmountRepository;

    @Autowired
    private JustificationDeductionAmountMapper justificationDeductionAmountMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJustificationDeductionAmountMockMvc;

    private JustificationDeductionAmount justificationDeductionAmount;

    private JustificationDeductionAmount insertedJustificationDeductionAmount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JustificationDeductionAmount createEntity(EntityManager em) {
        JustificationDeductionAmount justificationDeductionAmount = new JustificationDeductionAmount()
            .deductionAmount(DEFAULT_DEDUCTION_AMOUNT)
            .remainingDeductionAmount(DEFAULT_REMAINING_DEDUCTION_AMOUNT)
            .receivedDeductionAmount(DEFAULT_RECEIVED_DEDUCTION_AMOUNT);
        return justificationDeductionAmount;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JustificationDeductionAmount createUpdatedEntity(EntityManager em) {
        JustificationDeductionAmount justificationDeductionAmount = new JustificationDeductionAmount()
            .deductionAmount(UPDATED_DEDUCTION_AMOUNT)
            .remainingDeductionAmount(UPDATED_REMAINING_DEDUCTION_AMOUNT)
            .receivedDeductionAmount(UPDATED_RECEIVED_DEDUCTION_AMOUNT);
        return justificationDeductionAmount;
    }

    @BeforeEach
    public void initTest() {
        justificationDeductionAmount = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedJustificationDeductionAmount != null) {
            justificationDeductionAmountRepository.delete(insertedJustificationDeductionAmount);
            insertedJustificationDeductionAmount = null;
        }
    }

    @Test
    @Transactional
    void createJustificationDeductionAmount() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the JustificationDeductionAmount
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            justificationDeductionAmount
        );
        var returnedJustificationDeductionAmountDTO = om.readValue(
            restJustificationDeductionAmountMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(justificationDeductionAmountDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            JustificationDeductionAmountDTO.class
        );

        // Validate the JustificationDeductionAmount in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedJustificationDeductionAmount = justificationDeductionAmountMapper.toEntity(returnedJustificationDeductionAmountDTO);
        assertJustificationDeductionAmountUpdatableFieldsEquals(
            returnedJustificationDeductionAmount,
            getPersistedJustificationDeductionAmount(returnedJustificationDeductionAmount)
        );

        insertedJustificationDeductionAmount = returnedJustificationDeductionAmount;
    }

    @Test
    @Transactional
    void createJustificationDeductionAmountWithExistingId() throws Exception {
        // Create the JustificationDeductionAmount with an existing ID
        justificationDeductionAmount.setId(1L);
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            justificationDeductionAmount
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJustificationDeductionAmountMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(justificationDeductionAmountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmount in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJustificationDeductionAmounts() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmount = justificationDeductionAmountRepository.saveAndFlush(justificationDeductionAmount);

        // Get all the justificationDeductionAmountList
        restJustificationDeductionAmountMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(justificationDeductionAmount.getId().intValue())))
            .andExpect(jsonPath("$.[*].deductionAmount").value(hasItem(sameNumber(DEFAULT_DEDUCTION_AMOUNT))))
            .andExpect(jsonPath("$.[*].remainingDeductionAmount").value(hasItem(sameNumber(DEFAULT_REMAINING_DEDUCTION_AMOUNT))))
            .andExpect(jsonPath("$.[*].receivedDeductionAmount").value(hasItem(sameNumber(DEFAULT_RECEIVED_DEDUCTION_AMOUNT))));
    }

    @Test
    @Transactional
    void getJustificationDeductionAmount() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmount = justificationDeductionAmountRepository.saveAndFlush(justificationDeductionAmount);

        // Get the justificationDeductionAmount
        restJustificationDeductionAmountMockMvc
            .perform(get(ENTITY_API_URL_ID, justificationDeductionAmount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(justificationDeductionAmount.getId().intValue()))
            .andExpect(jsonPath("$.deductionAmount").value(sameNumber(DEFAULT_DEDUCTION_AMOUNT)))
            .andExpect(jsonPath("$.remainingDeductionAmount").value(sameNumber(DEFAULT_REMAINING_DEDUCTION_AMOUNT)))
            .andExpect(jsonPath("$.receivedDeductionAmount").value(sameNumber(DEFAULT_RECEIVED_DEDUCTION_AMOUNT)));
    }

    @Test
    @Transactional
    void getNonExistingJustificationDeductionAmount() throws Exception {
        // Get the justificationDeductionAmount
        restJustificationDeductionAmountMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingJustificationDeductionAmount() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmount = justificationDeductionAmountRepository.saveAndFlush(justificationDeductionAmount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionAmount
        JustificationDeductionAmount updatedJustificationDeductionAmount = justificationDeductionAmountRepository
            .findById(justificationDeductionAmount.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedJustificationDeductionAmount are not directly saved in db
        em.detach(updatedJustificationDeductionAmount);
        updatedJustificationDeductionAmount
            .deductionAmount(UPDATED_DEDUCTION_AMOUNT)
            .remainingDeductionAmount(UPDATED_REMAINING_DEDUCTION_AMOUNT)
            .receivedDeductionAmount(UPDATED_RECEIVED_DEDUCTION_AMOUNT);
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            updatedJustificationDeductionAmount
        );

        restJustificationDeductionAmountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, justificationDeductionAmountDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionAmountDTO))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionAmount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedJustificationDeductionAmountToMatchAllProperties(updatedJustificationDeductionAmount);
    }

    @Test
    @Transactional
    void putNonExistingJustificationDeductionAmount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmount.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmount
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            justificationDeductionAmount
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, justificationDeductionAmountDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionAmountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJustificationDeductionAmount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmount.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmount
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            justificationDeductionAmount
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionAmountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJustificationDeductionAmount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmount.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmount
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            justificationDeductionAmount
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(justificationDeductionAmountDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JustificationDeductionAmount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJustificationDeductionAmountWithPatch() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmount = justificationDeductionAmountRepository.saveAndFlush(justificationDeductionAmount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionAmount using partial update
        JustificationDeductionAmount partialUpdatedJustificationDeductionAmount = new JustificationDeductionAmount();
        partialUpdatedJustificationDeductionAmount.setId(justificationDeductionAmount.getId());

        partialUpdatedJustificationDeductionAmount
            .deductionAmount(UPDATED_DEDUCTION_AMOUNT)
            .remainingDeductionAmount(UPDATED_REMAINING_DEDUCTION_AMOUNT);

        restJustificationDeductionAmountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJustificationDeductionAmount.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedJustificationDeductionAmount))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionAmount in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertJustificationDeductionAmountUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedJustificationDeductionAmount, justificationDeductionAmount),
            getPersistedJustificationDeductionAmount(justificationDeductionAmount)
        );
    }

    @Test
    @Transactional
    void fullUpdateJustificationDeductionAmountWithPatch() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmount = justificationDeductionAmountRepository.saveAndFlush(justificationDeductionAmount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionAmount using partial update
        JustificationDeductionAmount partialUpdatedJustificationDeductionAmount = new JustificationDeductionAmount();
        partialUpdatedJustificationDeductionAmount.setId(justificationDeductionAmount.getId());

        partialUpdatedJustificationDeductionAmount
            .deductionAmount(UPDATED_DEDUCTION_AMOUNT)
            .remainingDeductionAmount(UPDATED_REMAINING_DEDUCTION_AMOUNT)
            .receivedDeductionAmount(UPDATED_RECEIVED_DEDUCTION_AMOUNT);

        restJustificationDeductionAmountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJustificationDeductionAmount.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedJustificationDeductionAmount))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionAmount in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertJustificationDeductionAmountUpdatableFieldsEquals(
            partialUpdatedJustificationDeductionAmount,
            getPersistedJustificationDeductionAmount(partialUpdatedJustificationDeductionAmount)
        );
    }

    @Test
    @Transactional
    void patchNonExistingJustificationDeductionAmount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmount.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmount
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            justificationDeductionAmount
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, justificationDeductionAmountDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionAmountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJustificationDeductionAmount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmount.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmount
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            justificationDeductionAmount
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionAmountDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionAmount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJustificationDeductionAmount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionAmount.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionAmount
        JustificationDeductionAmountDTO justificationDeductionAmountDTO = justificationDeductionAmountMapper.toDto(
            justificationDeductionAmount
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionAmountMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionAmountDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JustificationDeductionAmount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJustificationDeductionAmount() throws Exception {
        // Initialize the database
        insertedJustificationDeductionAmount = justificationDeductionAmountRepository.saveAndFlush(justificationDeductionAmount);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the justificationDeductionAmount
        restJustificationDeductionAmountMockMvc
            .perform(delete(ENTITY_API_URL_ID, justificationDeductionAmount.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return justificationDeductionAmountRepository.count();
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

    protected JustificationDeductionAmount getPersistedJustificationDeductionAmount(
        JustificationDeductionAmount justificationDeductionAmount
    ) {
        return justificationDeductionAmountRepository.findById(justificationDeductionAmount.getId()).orElseThrow();
    }

    protected void assertPersistedJustificationDeductionAmountToMatchAllProperties(
        JustificationDeductionAmount expectedJustificationDeductionAmount
    ) {
        assertJustificationDeductionAmountAllPropertiesEquals(
            expectedJustificationDeductionAmount,
            getPersistedJustificationDeductionAmount(expectedJustificationDeductionAmount)
        );
    }

    protected void assertPersistedJustificationDeductionAmountToMatchUpdatableProperties(
        JustificationDeductionAmount expectedJustificationDeductionAmount
    ) {
        assertJustificationDeductionAmountAllUpdatablePropertiesEquals(
            expectedJustificationDeductionAmount,
            getPersistedJustificationDeductionAmount(expectedJustificationDeductionAmount)
        );
    }
}
