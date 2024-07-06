package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.PaymentConditionAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.PaymentCondition;
import com.dotin.tfbita.repository.PaymentConditionRepository;
import com.dotin.tfbita.service.dto.PaymentConditionDTO;
import com.dotin.tfbita.service.mapper.PaymentConditionMapper;
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
 * Integration tests for the {@link PaymentConditionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaymentConditionResourceIT {

    private static final String DEFAULT_LATIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LATIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/payment-conditions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PaymentConditionRepository paymentConditionRepository;

    @Autowired
    private PaymentConditionMapper paymentConditionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentConditionMockMvc;

    private PaymentCondition paymentCondition;

    private PaymentCondition insertedPaymentCondition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentCondition createEntity(EntityManager em) {
        PaymentCondition paymentCondition = new PaymentCondition().latinName(DEFAULT_LATIN_NAME).name(DEFAULT_NAME);
        return paymentCondition;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentCondition createUpdatedEntity(EntityManager em) {
        PaymentCondition paymentCondition = new PaymentCondition().latinName(UPDATED_LATIN_NAME).name(UPDATED_NAME);
        return paymentCondition;
    }

    @BeforeEach
    public void initTest() {
        paymentCondition = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedPaymentCondition != null) {
            paymentConditionRepository.delete(insertedPaymentCondition);
            insertedPaymentCondition = null;
        }
    }

    @Test
    @Transactional
    void createPaymentCondition() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PaymentCondition
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(paymentCondition);
        var returnedPaymentConditionDTO = om.readValue(
            restPaymentConditionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentConditionDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PaymentConditionDTO.class
        );

        // Validate the PaymentCondition in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPaymentCondition = paymentConditionMapper.toEntity(returnedPaymentConditionDTO);
        assertPaymentConditionUpdatableFieldsEquals(returnedPaymentCondition, getPersistedPaymentCondition(returnedPaymentCondition));

        insertedPaymentCondition = returnedPaymentCondition;
    }

    @Test
    @Transactional
    void createPaymentConditionWithExistingId() throws Exception {
        // Create the PaymentCondition with an existing ID
        paymentCondition.setId(1L);
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(paymentCondition);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentConditionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentConditionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPaymentConditions() throws Exception {
        // Initialize the database
        insertedPaymentCondition = paymentConditionRepository.saveAndFlush(paymentCondition);

        // Get all the paymentConditionList
        restPaymentConditionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].latinName").value(hasItem(DEFAULT_LATIN_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getPaymentCondition() throws Exception {
        // Initialize the database
        insertedPaymentCondition = paymentConditionRepository.saveAndFlush(paymentCondition);

        // Get the paymentCondition
        restPaymentConditionMockMvc
            .perform(get(ENTITY_API_URL_ID, paymentCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentCondition.getId().intValue()))
            .andExpect(jsonPath("$.latinName").value(DEFAULT_LATIN_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingPaymentCondition() throws Exception {
        // Get the paymentCondition
        restPaymentConditionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPaymentCondition() throws Exception {
        // Initialize the database
        insertedPaymentCondition = paymentConditionRepository.saveAndFlush(paymentCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCondition
        PaymentCondition updatedPaymentCondition = paymentConditionRepository.findById(paymentCondition.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPaymentCondition are not directly saved in db
        em.detach(updatedPaymentCondition);
        updatedPaymentCondition.latinName(UPDATED_LATIN_NAME).name(UPDATED_NAME);
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(updatedPaymentCondition);

        restPaymentConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentConditionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentConditionDTO))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPaymentConditionToMatchAllProperties(updatedPaymentCondition);
    }

    @Test
    @Transactional
    void putNonExistingPaymentCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCondition.setId(longCount.incrementAndGet());

        // Create the PaymentCondition
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(paymentCondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentConditionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentConditionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPaymentCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCondition.setId(longCount.incrementAndGet());

        // Create the PaymentCondition
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(paymentCondition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentConditionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPaymentCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCondition.setId(longCount.incrementAndGet());

        // Create the PaymentCondition
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(paymentCondition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentConditionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentConditionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePaymentConditionWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentCondition = paymentConditionRepository.saveAndFlush(paymentCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCondition using partial update
        PaymentCondition partialUpdatedPaymentCondition = new PaymentCondition();
        partialUpdatedPaymentCondition.setId(paymentCondition.getId());

        partialUpdatedPaymentCondition.latinName(UPDATED_LATIN_NAME);

        restPaymentConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentCondition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentCondition))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCondition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentConditionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPaymentCondition, paymentCondition),
            getPersistedPaymentCondition(paymentCondition)
        );
    }

    @Test
    @Transactional
    void fullUpdatePaymentConditionWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentCondition = paymentConditionRepository.saveAndFlush(paymentCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCondition using partial update
        PaymentCondition partialUpdatedPaymentCondition = new PaymentCondition();
        partialUpdatedPaymentCondition.setId(paymentCondition.getId());

        partialUpdatedPaymentCondition.latinName(UPDATED_LATIN_NAME).name(UPDATED_NAME);

        restPaymentConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentCondition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentCondition))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCondition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentConditionUpdatableFieldsEquals(
            partialUpdatedPaymentCondition,
            getPersistedPaymentCondition(partialUpdatedPaymentCondition)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPaymentCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCondition.setId(longCount.incrementAndGet());

        // Create the PaymentCondition
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(paymentCondition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, paymentConditionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentConditionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPaymentCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCondition.setId(longCount.incrementAndGet());

        // Create the PaymentCondition
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(paymentCondition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentConditionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPaymentCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCondition.setId(longCount.incrementAndGet());

        // Create the PaymentCondition
        PaymentConditionDTO paymentConditionDTO = paymentConditionMapper.toDto(paymentCondition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentConditionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(paymentConditionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePaymentCondition() throws Exception {
        // Initialize the database
        insertedPaymentCondition = paymentConditionRepository.saveAndFlush(paymentCondition);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the paymentCondition
        restPaymentConditionMockMvc
            .perform(delete(ENTITY_API_URL_ID, paymentCondition.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return paymentConditionRepository.count();
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

    protected PaymentCondition getPersistedPaymentCondition(PaymentCondition paymentCondition) {
        return paymentConditionRepository.findById(paymentCondition.getId()).orElseThrow();
    }

    protected void assertPersistedPaymentConditionToMatchAllProperties(PaymentCondition expectedPaymentCondition) {
        assertPaymentConditionAllPropertiesEquals(expectedPaymentCondition, getPersistedPaymentCondition(expectedPaymentCondition));
    }

    protected void assertPersistedPaymentConditionToMatchUpdatableProperties(PaymentCondition expectedPaymentCondition) {
        assertPaymentConditionAllUpdatablePropertiesEquals(
            expectedPaymentCondition,
            getPersistedPaymentCondition(expectedPaymentCondition)
        );
    }
}
