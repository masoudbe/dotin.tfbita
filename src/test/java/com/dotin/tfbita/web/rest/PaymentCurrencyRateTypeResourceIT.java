package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.PaymentCurrencyRateTypeAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.PaymentCurrencyRateType;
import com.dotin.tfbita.repository.PaymentCurrencyRateTypeRepository;
import com.dotin.tfbita.service.dto.PaymentCurrencyRateTypeDTO;
import com.dotin.tfbita.service.mapper.PaymentCurrencyRateTypeMapper;
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
 * Integration tests for the {@link PaymentCurrencyRateTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaymentCurrencyRateTypeResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/payment-currency-rate-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PaymentCurrencyRateTypeRepository paymentCurrencyRateTypeRepository;

    @Autowired
    private PaymentCurrencyRateTypeMapper paymentCurrencyRateTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentCurrencyRateTypeMockMvc;

    private PaymentCurrencyRateType paymentCurrencyRateType;

    private PaymentCurrencyRateType insertedPaymentCurrencyRateType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentCurrencyRateType createEntity(EntityManager em) {
        PaymentCurrencyRateType paymentCurrencyRateType = new PaymentCurrencyRateType().description(DEFAULT_DESCRIPTION);
        return paymentCurrencyRateType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentCurrencyRateType createUpdatedEntity(EntityManager em) {
        PaymentCurrencyRateType paymentCurrencyRateType = new PaymentCurrencyRateType().description(UPDATED_DESCRIPTION);
        return paymentCurrencyRateType;
    }

    @BeforeEach
    public void initTest() {
        paymentCurrencyRateType = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedPaymentCurrencyRateType != null) {
            paymentCurrencyRateTypeRepository.delete(insertedPaymentCurrencyRateType);
            insertedPaymentCurrencyRateType = null;
        }
    }

    @Test
    @Transactional
    void createPaymentCurrencyRateType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PaymentCurrencyRateType
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);
        var returnedPaymentCurrencyRateTypeDTO = om.readValue(
            restPaymentCurrencyRateTypeMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PaymentCurrencyRateTypeDTO.class
        );

        // Validate the PaymentCurrencyRateType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPaymentCurrencyRateType = paymentCurrencyRateTypeMapper.toEntity(returnedPaymentCurrencyRateTypeDTO);
        assertPaymentCurrencyRateTypeUpdatableFieldsEquals(
            returnedPaymentCurrencyRateType,
            getPersistedPaymentCurrencyRateType(returnedPaymentCurrencyRateType)
        );

        insertedPaymentCurrencyRateType = returnedPaymentCurrencyRateType;
    }

    @Test
    @Transactional
    void createPaymentCurrencyRateTypeWithExistingId() throws Exception {
        // Create the PaymentCurrencyRateType with an existing ID
        paymentCurrencyRateType.setId(1L);
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentCurrencyRateTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentCurrencyRateType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPaymentCurrencyRateTypes() throws Exception {
        // Initialize the database
        insertedPaymentCurrencyRateType = paymentCurrencyRateTypeRepository.saveAndFlush(paymentCurrencyRateType);

        // Get all the paymentCurrencyRateTypeList
        restPaymentCurrencyRateTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentCurrencyRateType.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getPaymentCurrencyRateType() throws Exception {
        // Initialize the database
        insertedPaymentCurrencyRateType = paymentCurrencyRateTypeRepository.saveAndFlush(paymentCurrencyRateType);

        // Get the paymentCurrencyRateType
        restPaymentCurrencyRateTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, paymentCurrencyRateType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentCurrencyRateType.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingPaymentCurrencyRateType() throws Exception {
        // Get the paymentCurrencyRateType
        restPaymentCurrencyRateTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPaymentCurrencyRateType() throws Exception {
        // Initialize the database
        insertedPaymentCurrencyRateType = paymentCurrencyRateTypeRepository.saveAndFlush(paymentCurrencyRateType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCurrencyRateType
        PaymentCurrencyRateType updatedPaymentCurrencyRateType = paymentCurrencyRateTypeRepository
            .findById(paymentCurrencyRateType.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedPaymentCurrencyRateType are not directly saved in db
        em.detach(updatedPaymentCurrencyRateType);
        updatedPaymentCurrencyRateType.description(UPDATED_DESCRIPTION);
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(updatedPaymentCurrencyRateType);

        restPaymentCurrencyRateTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentCurrencyRateTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCurrencyRateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPaymentCurrencyRateTypeToMatchAllProperties(updatedPaymentCurrencyRateType);
    }

    @Test
    @Transactional
    void putNonExistingPaymentCurrencyRateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCurrencyRateType.setId(longCount.incrementAndGet());

        // Create the PaymentCurrencyRateType
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentCurrencyRateTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentCurrencyRateTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCurrencyRateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPaymentCurrencyRateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCurrencyRateType.setId(longCount.incrementAndGet());

        // Create the PaymentCurrencyRateType
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentCurrencyRateTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCurrencyRateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPaymentCurrencyRateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCurrencyRateType.setId(longCount.incrementAndGet());

        // Create the PaymentCurrencyRateType
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentCurrencyRateTypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentCurrencyRateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePaymentCurrencyRateTypeWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentCurrencyRateType = paymentCurrencyRateTypeRepository.saveAndFlush(paymentCurrencyRateType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCurrencyRateType using partial update
        PaymentCurrencyRateType partialUpdatedPaymentCurrencyRateType = new PaymentCurrencyRateType();
        partialUpdatedPaymentCurrencyRateType.setId(paymentCurrencyRateType.getId());

        restPaymentCurrencyRateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentCurrencyRateType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentCurrencyRateType))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCurrencyRateType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentCurrencyRateTypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPaymentCurrencyRateType, paymentCurrencyRateType),
            getPersistedPaymentCurrencyRateType(paymentCurrencyRateType)
        );
    }

    @Test
    @Transactional
    void fullUpdatePaymentCurrencyRateTypeWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentCurrencyRateType = paymentCurrencyRateTypeRepository.saveAndFlush(paymentCurrencyRateType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentCurrencyRateType using partial update
        PaymentCurrencyRateType partialUpdatedPaymentCurrencyRateType = new PaymentCurrencyRateType();
        partialUpdatedPaymentCurrencyRateType.setId(paymentCurrencyRateType.getId());

        partialUpdatedPaymentCurrencyRateType.description(UPDATED_DESCRIPTION);

        restPaymentCurrencyRateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentCurrencyRateType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentCurrencyRateType))
            )
            .andExpect(status().isOk());

        // Validate the PaymentCurrencyRateType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentCurrencyRateTypeUpdatableFieldsEquals(
            partialUpdatedPaymentCurrencyRateType,
            getPersistedPaymentCurrencyRateType(partialUpdatedPaymentCurrencyRateType)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPaymentCurrencyRateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCurrencyRateType.setId(longCount.incrementAndGet());

        // Create the PaymentCurrencyRateType
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentCurrencyRateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, paymentCurrencyRateTypeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCurrencyRateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPaymentCurrencyRateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCurrencyRateType.setId(longCount.incrementAndGet());

        // Create the PaymentCurrencyRateType
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentCurrencyRateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentCurrencyRateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPaymentCurrencyRateType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentCurrencyRateType.setId(longCount.incrementAndGet());

        // Create the PaymentCurrencyRateType
        PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO = paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentCurrencyRateTypeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(paymentCurrencyRateTypeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentCurrencyRateType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePaymentCurrencyRateType() throws Exception {
        // Initialize the database
        insertedPaymentCurrencyRateType = paymentCurrencyRateTypeRepository.saveAndFlush(paymentCurrencyRateType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the paymentCurrencyRateType
        restPaymentCurrencyRateTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, paymentCurrencyRateType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return paymentCurrencyRateTypeRepository.count();
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

    protected PaymentCurrencyRateType getPersistedPaymentCurrencyRateType(PaymentCurrencyRateType paymentCurrencyRateType) {
        return paymentCurrencyRateTypeRepository.findById(paymentCurrencyRateType.getId()).orElseThrow();
    }

    protected void assertPersistedPaymentCurrencyRateTypeToMatchAllProperties(PaymentCurrencyRateType expectedPaymentCurrencyRateType) {
        assertPaymentCurrencyRateTypeAllPropertiesEquals(
            expectedPaymentCurrencyRateType,
            getPersistedPaymentCurrencyRateType(expectedPaymentCurrencyRateType)
        );
    }

    protected void assertPersistedPaymentCurrencyRateTypeToMatchUpdatableProperties(
        PaymentCurrencyRateType expectedPaymentCurrencyRateType
    ) {
        assertPaymentCurrencyRateTypeAllUpdatablePropertiesEquals(
            expectedPaymentCurrencyRateType,
            getPersistedPaymentCurrencyRateType(expectedPaymentCurrencyRateType)
        );
    }
}
