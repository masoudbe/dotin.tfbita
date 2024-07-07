package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.PaymentItemTypeAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.PaymentItemType;
import com.dotin.tfbita.repository.PaymentItemTypeRepository;
import com.dotin.tfbita.service.dto.PaymentItemTypeDTO;
import com.dotin.tfbita.service.mapper.PaymentItemTypeMapper;
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
 * Integration tests for the {@link PaymentItemTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaymentItemTypeResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/payment-item-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PaymentItemTypeRepository paymentItemTypeRepository;

    @Autowired
    private PaymentItemTypeMapper paymentItemTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentItemTypeMockMvc;

    private PaymentItemType paymentItemType;

    private PaymentItemType insertedPaymentItemType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentItemType createEntity(EntityManager em) {
        PaymentItemType paymentItemType = new PaymentItemType().description(DEFAULT_DESCRIPTION);
        return paymentItemType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentItemType createUpdatedEntity(EntityManager em) {
        PaymentItemType paymentItemType = new PaymentItemType().description(UPDATED_DESCRIPTION);
        return paymentItemType;
    }

    @BeforeEach
    public void initTest() {
        paymentItemType = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedPaymentItemType != null) {
            paymentItemTypeRepository.delete(insertedPaymentItemType);
            insertedPaymentItemType = null;
        }
    }

    @Test
    @Transactional
    void createPaymentItemType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PaymentItemType
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(paymentItemType);
        var returnedPaymentItemTypeDTO = om.readValue(
            restPaymentItemTypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentItemTypeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PaymentItemTypeDTO.class
        );

        // Validate the PaymentItemType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPaymentItemType = paymentItemTypeMapper.toEntity(returnedPaymentItemTypeDTO);
        assertPaymentItemTypeUpdatableFieldsEquals(returnedPaymentItemType, getPersistedPaymentItemType(returnedPaymentItemType));

        insertedPaymentItemType = returnedPaymentItemType;
    }

    @Test
    @Transactional
    void createPaymentItemTypeWithExistingId() throws Exception {
        // Create the PaymentItemType with an existing ID
        paymentItemType.setId(1L);
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(paymentItemType);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentItemTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentItemTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentItemType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPaymentItemTypes() throws Exception {
        // Initialize the database
        insertedPaymentItemType = paymentItemTypeRepository.saveAndFlush(paymentItemType);

        // Get all the paymentItemTypeList
        restPaymentItemTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentItemType.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getPaymentItemType() throws Exception {
        // Initialize the database
        insertedPaymentItemType = paymentItemTypeRepository.saveAndFlush(paymentItemType);

        // Get the paymentItemType
        restPaymentItemTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, paymentItemType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentItemType.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingPaymentItemType() throws Exception {
        // Get the paymentItemType
        restPaymentItemTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPaymentItemType() throws Exception {
        // Initialize the database
        insertedPaymentItemType = paymentItemTypeRepository.saveAndFlush(paymentItemType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentItemType
        PaymentItemType updatedPaymentItemType = paymentItemTypeRepository.findById(paymentItemType.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPaymentItemType are not directly saved in db
        em.detach(updatedPaymentItemType);
        updatedPaymentItemType.description(UPDATED_DESCRIPTION);
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(updatedPaymentItemType);

        restPaymentItemTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentItemTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentItemTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the PaymentItemType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPaymentItemTypeToMatchAllProperties(updatedPaymentItemType);
    }

    @Test
    @Transactional
    void putNonExistingPaymentItemType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentItemType.setId(longCount.incrementAndGet());

        // Create the PaymentItemType
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(paymentItemType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentItemTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentItemTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentItemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentItemType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPaymentItemType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentItemType.setId(longCount.incrementAndGet());

        // Create the PaymentItemType
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(paymentItemType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentItemTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentItemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentItemType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPaymentItemType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentItemType.setId(longCount.incrementAndGet());

        // Create the PaymentItemType
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(paymentItemType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentItemTypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentItemTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentItemType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePaymentItemTypeWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentItemType = paymentItemTypeRepository.saveAndFlush(paymentItemType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentItemType using partial update
        PaymentItemType partialUpdatedPaymentItemType = new PaymentItemType();
        partialUpdatedPaymentItemType.setId(paymentItemType.getId());

        partialUpdatedPaymentItemType.description(UPDATED_DESCRIPTION);

        restPaymentItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentItemType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentItemType))
            )
            .andExpect(status().isOk());

        // Validate the PaymentItemType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentItemTypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPaymentItemType, paymentItemType),
            getPersistedPaymentItemType(paymentItemType)
        );
    }

    @Test
    @Transactional
    void fullUpdatePaymentItemTypeWithPatch() throws Exception {
        // Initialize the database
        insertedPaymentItemType = paymentItemTypeRepository.saveAndFlush(paymentItemType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentItemType using partial update
        PaymentItemType partialUpdatedPaymentItemType = new PaymentItemType();
        partialUpdatedPaymentItemType.setId(paymentItemType.getId());

        partialUpdatedPaymentItemType.description(UPDATED_DESCRIPTION);

        restPaymentItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentItemType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentItemType))
            )
            .andExpect(status().isOk());

        // Validate the PaymentItemType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymentItemTypeUpdatableFieldsEquals(
            partialUpdatedPaymentItemType,
            getPersistedPaymentItemType(partialUpdatedPaymentItemType)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPaymentItemType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentItemType.setId(longCount.incrementAndGet());

        // Create the PaymentItemType
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(paymentItemType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, paymentItemTypeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentItemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentItemType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPaymentItemType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentItemType.setId(longCount.incrementAndGet());

        // Create the PaymentItemType
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(paymentItemType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentItemTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentItemTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PaymentItemType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPaymentItemType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentItemType.setId(longCount.incrementAndGet());

        // Create the PaymentItemType
        PaymentItemTypeDTO paymentItemTypeDTO = paymentItemTypeMapper.toDto(paymentItemType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymentItemTypeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(paymentItemTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PaymentItemType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePaymentItemType() throws Exception {
        // Initialize the database
        insertedPaymentItemType = paymentItemTypeRepository.saveAndFlush(paymentItemType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the paymentItemType
        restPaymentItemTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, paymentItemType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return paymentItemTypeRepository.count();
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

    protected PaymentItemType getPersistedPaymentItemType(PaymentItemType paymentItemType) {
        return paymentItemTypeRepository.findById(paymentItemType.getId()).orElseThrow();
    }

    protected void assertPersistedPaymentItemTypeToMatchAllProperties(PaymentItemType expectedPaymentItemType) {
        assertPaymentItemTypeAllPropertiesEquals(expectedPaymentItemType, getPersistedPaymentItemType(expectedPaymentItemType));
    }

    protected void assertPersistedPaymentItemTypeToMatchUpdatableProperties(PaymentItemType expectedPaymentItemType) {
        assertPaymentItemTypeAllUpdatablePropertiesEquals(expectedPaymentItemType, getPersistedPaymentItemType(expectedPaymentItemType));
    }
}
