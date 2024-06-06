package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.PurchaseFromOtherResourcesAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.PurchaseFromOtherResources;
import com.dotin.tfbita.repository.PurchaseFromOtherResourcesRepository;
import com.dotin.tfbita.service.dto.PurchaseFromOtherResourcesDTO;
import com.dotin.tfbita.service.mapper.PurchaseFromOtherResourcesMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PurchaseFromOtherResourcesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseFromOtherResourcesResourceIT {

    private static final String DEFAULT_EVIDENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EVIDENCE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_SUPPLIER_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_SUPPLIER_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PURCHASE_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PURCHASE_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ORDER_REGISTRATION_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORDER_REGISTRATION_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_REQUEST_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIRMATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRMATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PURCHASE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PURCHASE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PURCHASE_CURRENCY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PURCHASE_CURRENCY_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/purchase-from-other-resources";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PurchaseFromOtherResourcesRepository purchaseFromOtherResourcesRepository;

    @Autowired
    private PurchaseFromOtherResourcesMapper purchaseFromOtherResourcesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseFromOtherResourcesMockMvc;

    private PurchaseFromOtherResources purchaseFromOtherResources;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseFromOtherResources createEntity(EntityManager em) {
        PurchaseFromOtherResources purchaseFromOtherResources = new PurchaseFromOtherResources()
            .evidenceCode(DEFAULT_EVIDENCE_CODE)
            .currencySupplierDescription(DEFAULT_CURRENCY_SUPPLIER_DESCRIPTION)
            .amount(DEFAULT_AMOUNT)
            .purchaseRate(DEFAULT_PURCHASE_RATE)
            .orderRegistrationAmount(DEFAULT_ORDER_REGISTRATION_AMOUNT)
            .requestDate(DEFAULT_REQUEST_DATE)
            .confirmationDate(DEFAULT_CONFIRMATION_DATE)
            .description(DEFAULT_DESCRIPTION)
            .purchaseNumber(DEFAULT_PURCHASE_NUMBER)
            .purchaseCurrencyName(DEFAULT_PURCHASE_CURRENCY_NAME);
        return purchaseFromOtherResources;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseFromOtherResources createUpdatedEntity(EntityManager em) {
        PurchaseFromOtherResources purchaseFromOtherResources = new PurchaseFromOtherResources()
            .evidenceCode(UPDATED_EVIDENCE_CODE)
            .currencySupplierDescription(UPDATED_CURRENCY_SUPPLIER_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .purchaseRate(UPDATED_PURCHASE_RATE)
            .orderRegistrationAmount(UPDATED_ORDER_REGISTRATION_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .confirmationDate(UPDATED_CONFIRMATION_DATE)
            .description(UPDATED_DESCRIPTION)
            .purchaseNumber(UPDATED_PURCHASE_NUMBER)
            .purchaseCurrencyName(UPDATED_PURCHASE_CURRENCY_NAME);
        return purchaseFromOtherResources;
    }

    @BeforeEach
    public void initTest() {
        purchaseFromOtherResources = createEntity(em);
    }

    @Test
    @Transactional
    void createPurchaseFromOtherResources() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PurchaseFromOtherResources
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);
        var returnedPurchaseFromOtherResourcesDTO = om.readValue(
            restPurchaseFromOtherResourcesMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PurchaseFromOtherResourcesDTO.class
        );

        // Validate the PurchaseFromOtherResources in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPurchaseFromOtherResources = purchaseFromOtherResourcesMapper.toEntity(returnedPurchaseFromOtherResourcesDTO);
        assertPurchaseFromOtherResourcesUpdatableFieldsEquals(
            returnedPurchaseFromOtherResources,
            getPersistedPurchaseFromOtherResources(returnedPurchaseFromOtherResources)
        );
    }

    @Test
    @Transactional
    void createPurchaseFromOtherResourcesWithExistingId() throws Exception {
        // Create the PurchaseFromOtherResources with an existing ID
        purchaseFromOtherResources.setId(1L);
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseFromOtherResourcesMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseFromOtherResources in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPurchaseFromOtherResources() throws Exception {
        // Initialize the database
        purchaseFromOtherResourcesRepository.saveAndFlush(purchaseFromOtherResources);

        // Get all the purchaseFromOtherResourcesList
        restPurchaseFromOtherResourcesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseFromOtherResources.getId().intValue())))
            .andExpect(jsonPath("$.[*].evidenceCode").value(hasItem(DEFAULT_EVIDENCE_CODE)))
            .andExpect(jsonPath("$.[*].currencySupplierDescription").value(hasItem(DEFAULT_CURRENCY_SUPPLIER_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))))
            .andExpect(jsonPath("$.[*].purchaseRate").value(hasItem(sameNumber(DEFAULT_PURCHASE_RATE))))
            .andExpect(jsonPath("$.[*].orderRegistrationAmount").value(hasItem(sameNumber(DEFAULT_ORDER_REGISTRATION_AMOUNT))))
            .andExpect(jsonPath("$.[*].requestDate").value(hasItem(DEFAULT_REQUEST_DATE)))
            .andExpect(jsonPath("$.[*].confirmationDate").value(hasItem(DEFAULT_CONFIRMATION_DATE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].purchaseNumber").value(hasItem(DEFAULT_PURCHASE_NUMBER)))
            .andExpect(jsonPath("$.[*].purchaseCurrencyName").value(hasItem(DEFAULT_PURCHASE_CURRENCY_NAME)));
    }

    @Test
    @Transactional
    void getPurchaseFromOtherResources() throws Exception {
        // Initialize the database
        purchaseFromOtherResourcesRepository.saveAndFlush(purchaseFromOtherResources);

        // Get the purchaseFromOtherResources
        restPurchaseFromOtherResourcesMockMvc
            .perform(get(ENTITY_API_URL_ID, purchaseFromOtherResources.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseFromOtherResources.getId().intValue()))
            .andExpect(jsonPath("$.evidenceCode").value(DEFAULT_EVIDENCE_CODE))
            .andExpect(jsonPath("$.currencySupplierDescription").value(DEFAULT_CURRENCY_SUPPLIER_DESCRIPTION))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.purchaseRate").value(sameNumber(DEFAULT_PURCHASE_RATE)))
            .andExpect(jsonPath("$.orderRegistrationAmount").value(sameNumber(DEFAULT_ORDER_REGISTRATION_AMOUNT)))
            .andExpect(jsonPath("$.requestDate").value(DEFAULT_REQUEST_DATE))
            .andExpect(jsonPath("$.confirmationDate").value(DEFAULT_CONFIRMATION_DATE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.purchaseNumber").value(DEFAULT_PURCHASE_NUMBER))
            .andExpect(jsonPath("$.purchaseCurrencyName").value(DEFAULT_PURCHASE_CURRENCY_NAME));
    }

    @Test
    @Transactional
    void getNonExistingPurchaseFromOtherResources() throws Exception {
        // Get the purchaseFromOtherResources
        restPurchaseFromOtherResourcesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPurchaseFromOtherResources() throws Exception {
        // Initialize the database
        purchaseFromOtherResourcesRepository.saveAndFlush(purchaseFromOtherResources);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the purchaseFromOtherResources
        PurchaseFromOtherResources updatedPurchaseFromOtherResources = purchaseFromOtherResourcesRepository
            .findById(purchaseFromOtherResources.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedPurchaseFromOtherResources are not directly saved in db
        em.detach(updatedPurchaseFromOtherResources);
        updatedPurchaseFromOtherResources
            .evidenceCode(UPDATED_EVIDENCE_CODE)
            .currencySupplierDescription(UPDATED_CURRENCY_SUPPLIER_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .purchaseRate(UPDATED_PURCHASE_RATE)
            .orderRegistrationAmount(UPDATED_ORDER_REGISTRATION_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .confirmationDate(UPDATED_CONFIRMATION_DATE)
            .description(UPDATED_DESCRIPTION)
            .purchaseNumber(UPDATED_PURCHASE_NUMBER)
            .purchaseCurrencyName(UPDATED_PURCHASE_CURRENCY_NAME);
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(
            updatedPurchaseFromOtherResources
        );

        restPurchaseFromOtherResourcesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseFromOtherResourcesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseFromOtherResources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPurchaseFromOtherResourcesToMatchAllProperties(updatedPurchaseFromOtherResources);
    }

    @Test
    @Transactional
    void putNonExistingPurchaseFromOtherResources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        purchaseFromOtherResources.setId(longCount.incrementAndGet());

        // Create the PurchaseFromOtherResources
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseFromOtherResourcesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, purchaseFromOtherResourcesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseFromOtherResources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPurchaseFromOtherResources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        purchaseFromOtherResources.setId(longCount.incrementAndGet());

        // Create the PurchaseFromOtherResources
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseFromOtherResourcesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseFromOtherResources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPurchaseFromOtherResources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        purchaseFromOtherResources.setId(longCount.incrementAndGet());

        // Create the PurchaseFromOtherResources
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseFromOtherResourcesMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseFromOtherResources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePurchaseFromOtherResourcesWithPatch() throws Exception {
        // Initialize the database
        purchaseFromOtherResourcesRepository.saveAndFlush(purchaseFromOtherResources);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the purchaseFromOtherResources using partial update
        PurchaseFromOtherResources partialUpdatedPurchaseFromOtherResources = new PurchaseFromOtherResources();
        partialUpdatedPurchaseFromOtherResources.setId(purchaseFromOtherResources.getId());

        partialUpdatedPurchaseFromOtherResources
            .purchaseRate(UPDATED_PURCHASE_RATE)
            .requestDate(UPDATED_REQUEST_DATE)
            .purchaseCurrencyName(UPDATED_PURCHASE_CURRENCY_NAME);

        restPurchaseFromOtherResourcesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseFromOtherResources.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPurchaseFromOtherResources))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseFromOtherResources in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPurchaseFromOtherResourcesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPurchaseFromOtherResources, purchaseFromOtherResources),
            getPersistedPurchaseFromOtherResources(purchaseFromOtherResources)
        );
    }

    @Test
    @Transactional
    void fullUpdatePurchaseFromOtherResourcesWithPatch() throws Exception {
        // Initialize the database
        purchaseFromOtherResourcesRepository.saveAndFlush(purchaseFromOtherResources);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the purchaseFromOtherResources using partial update
        PurchaseFromOtherResources partialUpdatedPurchaseFromOtherResources = new PurchaseFromOtherResources();
        partialUpdatedPurchaseFromOtherResources.setId(purchaseFromOtherResources.getId());

        partialUpdatedPurchaseFromOtherResources
            .evidenceCode(UPDATED_EVIDENCE_CODE)
            .currencySupplierDescription(UPDATED_CURRENCY_SUPPLIER_DESCRIPTION)
            .amount(UPDATED_AMOUNT)
            .purchaseRate(UPDATED_PURCHASE_RATE)
            .orderRegistrationAmount(UPDATED_ORDER_REGISTRATION_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .confirmationDate(UPDATED_CONFIRMATION_DATE)
            .description(UPDATED_DESCRIPTION)
            .purchaseNumber(UPDATED_PURCHASE_NUMBER)
            .purchaseCurrencyName(UPDATED_PURCHASE_CURRENCY_NAME);

        restPurchaseFromOtherResourcesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPurchaseFromOtherResources.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPurchaseFromOtherResources))
            )
            .andExpect(status().isOk());

        // Validate the PurchaseFromOtherResources in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPurchaseFromOtherResourcesUpdatableFieldsEquals(
            partialUpdatedPurchaseFromOtherResources,
            getPersistedPurchaseFromOtherResources(partialUpdatedPurchaseFromOtherResources)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPurchaseFromOtherResources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        purchaseFromOtherResources.setId(longCount.incrementAndGet());

        // Create the PurchaseFromOtherResources
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseFromOtherResourcesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, purchaseFromOtherResourcesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseFromOtherResources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPurchaseFromOtherResources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        purchaseFromOtherResources.setId(longCount.incrementAndGet());

        // Create the PurchaseFromOtherResources
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseFromOtherResourcesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PurchaseFromOtherResources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPurchaseFromOtherResources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        purchaseFromOtherResources.setId(longCount.incrementAndGet());

        // Create the PurchaseFromOtherResources
        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPurchaseFromOtherResourcesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(purchaseFromOtherResourcesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PurchaseFromOtherResources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePurchaseFromOtherResources() throws Exception {
        // Initialize the database
        purchaseFromOtherResourcesRepository.saveAndFlush(purchaseFromOtherResources);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the purchaseFromOtherResources
        restPurchaseFromOtherResourcesMockMvc
            .perform(delete(ENTITY_API_URL_ID, purchaseFromOtherResources.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return purchaseFromOtherResourcesRepository.count();
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

    protected PurchaseFromOtherResources getPersistedPurchaseFromOtherResources(PurchaseFromOtherResources purchaseFromOtherResources) {
        return purchaseFromOtherResourcesRepository.findById(purchaseFromOtherResources.getId()).orElseThrow();
    }

    protected void assertPersistedPurchaseFromOtherResourcesToMatchAllProperties(
        PurchaseFromOtherResources expectedPurchaseFromOtherResources
    ) {
        assertPurchaseFromOtherResourcesAllPropertiesEquals(
            expectedPurchaseFromOtherResources,
            getPersistedPurchaseFromOtherResources(expectedPurchaseFromOtherResources)
        );
    }

    protected void assertPersistedPurchaseFromOtherResourcesToMatchUpdatableProperties(
        PurchaseFromOtherResources expectedPurchaseFromOtherResources
    ) {
        assertPurchaseFromOtherResourcesAllUpdatablePropertiesEquals(
            expectedPurchaseFromOtherResources,
            getPersistedPurchaseFromOtherResources(expectedPurchaseFromOtherResources)
        );
    }
}
