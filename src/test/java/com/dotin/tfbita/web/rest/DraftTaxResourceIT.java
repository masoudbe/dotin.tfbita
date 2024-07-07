package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftTaxAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftTax;
import com.dotin.tfbita.repository.DraftTaxRepository;
import com.dotin.tfbita.service.dto.DraftTaxDTO;
import com.dotin.tfbita.service.mapper.DraftTaxMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Base64;
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
 * Integration tests for the {@link DraftTaxResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftTaxResourceIT {

    private static final BigDecimal DEFAULT_ORDER_REG_CURRENCY_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORDER_REG_CURRENCY_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAIN_ACCOUNT_CURRENCY_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAIN_ACCOUNT_CURRENCY_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_LETTER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LETTER_NUMBER = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LETTER_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LETTER_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LETTER_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LETTER_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_DATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RETURN_TAXES_AMOUNT = false;
    private static final Boolean UPDATED_RETURN_TAXES_AMOUNT = true;

    private static final BigDecimal DEFAULT_ORDER_REG_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ORDER_REG_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAIN_ACCOUNT_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAIN_ACCOUNT_RATE = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/draft-taxes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftTaxRepository draftTaxRepository;

    @Autowired
    private DraftTaxMapper draftTaxMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftTaxMockMvc;

    private DraftTax draftTax;

    private DraftTax insertedDraftTax;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftTax createEntity(EntityManager em) {
        DraftTax draftTax = new DraftTax()
            .orderRegCurrencyAmount(DEFAULT_ORDER_REG_CURRENCY_AMOUNT)
            .mainAccountCurrencyAmount(DEFAULT_MAIN_ACCOUNT_CURRENCY_AMOUNT)
            .letterNumber(DEFAULT_LETTER_NUMBER)
            .letterImage(DEFAULT_LETTER_IMAGE)
            .letterImageContentType(DEFAULT_LETTER_IMAGE_CONTENT_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .registrationDate(DEFAULT_REGISTRATION_DATE)
            .returnTaxesAmount(DEFAULT_RETURN_TAXES_AMOUNT)
            .orderRegRate(DEFAULT_ORDER_REG_RATE)
            .mainAccountRate(DEFAULT_MAIN_ACCOUNT_RATE);
        return draftTax;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftTax createUpdatedEntity(EntityManager em) {
        DraftTax draftTax = new DraftTax()
            .orderRegCurrencyAmount(UPDATED_ORDER_REG_CURRENCY_AMOUNT)
            .mainAccountCurrencyAmount(UPDATED_MAIN_ACCOUNT_CURRENCY_AMOUNT)
            .letterNumber(UPDATED_LETTER_NUMBER)
            .letterImage(UPDATED_LETTER_IMAGE)
            .letterImageContentType(UPDATED_LETTER_IMAGE_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .returnTaxesAmount(UPDATED_RETURN_TAXES_AMOUNT)
            .orderRegRate(UPDATED_ORDER_REG_RATE)
            .mainAccountRate(UPDATED_MAIN_ACCOUNT_RATE);
        return draftTax;
    }

    @BeforeEach
    public void initTest() {
        draftTax = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftTax != null) {
            draftTaxRepository.delete(insertedDraftTax);
            insertedDraftTax = null;
        }
    }

    @Test
    @Transactional
    void createDraftTax() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftTax
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(draftTax);
        var returnedDraftTaxDTO = om.readValue(
            restDraftTaxMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTaxDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftTaxDTO.class
        );

        // Validate the DraftTax in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftTax = draftTaxMapper.toEntity(returnedDraftTaxDTO);
        assertDraftTaxUpdatableFieldsEquals(returnedDraftTax, getPersistedDraftTax(returnedDraftTax));

        insertedDraftTax = returnedDraftTax;
    }

    @Test
    @Transactional
    void createDraftTaxWithExistingId() throws Exception {
        // Create the DraftTax with an existing ID
        draftTax.setId(1L);
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(draftTax);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftTaxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTaxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftTax in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftTaxes() throws Exception {
        // Initialize the database
        insertedDraftTax = draftTaxRepository.saveAndFlush(draftTax);

        // Get all the draftTaxList
        restDraftTaxMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftTax.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderRegCurrencyAmount").value(hasItem(sameNumber(DEFAULT_ORDER_REG_CURRENCY_AMOUNT))))
            .andExpect(jsonPath("$.[*].mainAccountCurrencyAmount").value(hasItem(sameNumber(DEFAULT_MAIN_ACCOUNT_CURRENCY_AMOUNT))))
            .andExpect(jsonPath("$.[*].letterNumber").value(hasItem(DEFAULT_LETTER_NUMBER)))
            .andExpect(jsonPath("$.[*].letterImageContentType").value(hasItem(DEFAULT_LETTER_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].letterImage").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_LETTER_IMAGE))))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].registrationDate").value(hasItem(DEFAULT_REGISTRATION_DATE)))
            .andExpect(jsonPath("$.[*].returnTaxesAmount").value(hasItem(DEFAULT_RETURN_TAXES_AMOUNT.booleanValue())))
            .andExpect(jsonPath("$.[*].orderRegRate").value(hasItem(sameNumber(DEFAULT_ORDER_REG_RATE))))
            .andExpect(jsonPath("$.[*].mainAccountRate").value(hasItem(sameNumber(DEFAULT_MAIN_ACCOUNT_RATE))));
    }

    @Test
    @Transactional
    void getDraftTax() throws Exception {
        // Initialize the database
        insertedDraftTax = draftTaxRepository.saveAndFlush(draftTax);

        // Get the draftTax
        restDraftTaxMockMvc
            .perform(get(ENTITY_API_URL_ID, draftTax.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftTax.getId().intValue()))
            .andExpect(jsonPath("$.orderRegCurrencyAmount").value(sameNumber(DEFAULT_ORDER_REG_CURRENCY_AMOUNT)))
            .andExpect(jsonPath("$.mainAccountCurrencyAmount").value(sameNumber(DEFAULT_MAIN_ACCOUNT_CURRENCY_AMOUNT)))
            .andExpect(jsonPath("$.letterNumber").value(DEFAULT_LETTER_NUMBER))
            .andExpect(jsonPath("$.letterImageContentType").value(DEFAULT_LETTER_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.letterImage").value(Base64.getEncoder().encodeToString(DEFAULT_LETTER_IMAGE)))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.registrationDate").value(DEFAULT_REGISTRATION_DATE))
            .andExpect(jsonPath("$.returnTaxesAmount").value(DEFAULT_RETURN_TAXES_AMOUNT.booleanValue()))
            .andExpect(jsonPath("$.orderRegRate").value(sameNumber(DEFAULT_ORDER_REG_RATE)))
            .andExpect(jsonPath("$.mainAccountRate").value(sameNumber(DEFAULT_MAIN_ACCOUNT_RATE)));
    }

    @Test
    @Transactional
    void getNonExistingDraftTax() throws Exception {
        // Get the draftTax
        restDraftTaxMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftTax() throws Exception {
        // Initialize the database
        insertedDraftTax = draftTaxRepository.saveAndFlush(draftTax);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTax
        DraftTax updatedDraftTax = draftTaxRepository.findById(draftTax.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftTax are not directly saved in db
        em.detach(updatedDraftTax);
        updatedDraftTax
            .orderRegCurrencyAmount(UPDATED_ORDER_REG_CURRENCY_AMOUNT)
            .mainAccountCurrencyAmount(UPDATED_MAIN_ACCOUNT_CURRENCY_AMOUNT)
            .letterNumber(UPDATED_LETTER_NUMBER)
            .letterImage(UPDATED_LETTER_IMAGE)
            .letterImageContentType(UPDATED_LETTER_IMAGE_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .returnTaxesAmount(UPDATED_RETURN_TAXES_AMOUNT)
            .orderRegRate(UPDATED_ORDER_REG_RATE)
            .mainAccountRate(UPDATED_MAIN_ACCOUNT_RATE);
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(updatedDraftTax);

        restDraftTaxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftTaxDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTaxDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftTax in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftTaxToMatchAllProperties(updatedDraftTax);
    }

    @Test
    @Transactional
    void putNonExistingDraftTax() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTax.setId(longCount.incrementAndGet());

        // Create the DraftTax
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(draftTax);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftTaxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftTaxDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTaxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTax in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftTax() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTax.setId(longCount.incrementAndGet());

        // Create the DraftTax
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(draftTax);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTaxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTaxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTax in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftTax() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTax.setId(longCount.incrementAndGet());

        // Create the DraftTax
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(draftTax);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTaxMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTaxDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftTax in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftTaxWithPatch() throws Exception {
        // Initialize the database
        insertedDraftTax = draftTaxRepository.saveAndFlush(draftTax);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTax using partial update
        DraftTax partialUpdatedDraftTax = new DraftTax();
        partialUpdatedDraftTax.setId(draftTax.getId());

        partialUpdatedDraftTax.orderRegCurrencyAmount(UPDATED_ORDER_REG_CURRENCY_AMOUNT).mainAccountRate(UPDATED_MAIN_ACCOUNT_RATE);

        restDraftTaxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftTax.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftTax))
            )
            .andExpect(status().isOk());

        // Validate the DraftTax in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftTaxUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedDraftTax, draftTax), getPersistedDraftTax(draftTax));
    }

    @Test
    @Transactional
    void fullUpdateDraftTaxWithPatch() throws Exception {
        // Initialize the database
        insertedDraftTax = draftTaxRepository.saveAndFlush(draftTax);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftTax using partial update
        DraftTax partialUpdatedDraftTax = new DraftTax();
        partialUpdatedDraftTax.setId(draftTax.getId());

        partialUpdatedDraftTax
            .orderRegCurrencyAmount(UPDATED_ORDER_REG_CURRENCY_AMOUNT)
            .mainAccountCurrencyAmount(UPDATED_MAIN_ACCOUNT_CURRENCY_AMOUNT)
            .letterNumber(UPDATED_LETTER_NUMBER)
            .letterImage(UPDATED_LETTER_IMAGE)
            .letterImageContentType(UPDATED_LETTER_IMAGE_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .returnTaxesAmount(UPDATED_RETURN_TAXES_AMOUNT)
            .orderRegRate(UPDATED_ORDER_REG_RATE)
            .mainAccountRate(UPDATED_MAIN_ACCOUNT_RATE);

        restDraftTaxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftTax.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftTax))
            )
            .andExpect(status().isOk());

        // Validate the DraftTax in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftTaxUpdatableFieldsEquals(partialUpdatedDraftTax, getPersistedDraftTax(partialUpdatedDraftTax));
    }

    @Test
    @Transactional
    void patchNonExistingDraftTax() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTax.setId(longCount.incrementAndGet());

        // Create the DraftTax
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(draftTax);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftTaxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftTaxDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftTaxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTax in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftTax() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTax.setId(longCount.incrementAndGet());

        // Create the DraftTax
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(draftTax);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTaxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftTaxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftTax in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftTax() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftTax.setId(longCount.incrementAndGet());

        // Create the DraftTax
        DraftTaxDTO draftTaxDTO = draftTaxMapper.toDto(draftTax);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTaxMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftTaxDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftTax in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftTax() throws Exception {
        // Initialize the database
        insertedDraftTax = draftTaxRepository.saveAndFlush(draftTax);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftTax
        restDraftTaxMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftTax.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftTaxRepository.count();
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

    protected DraftTax getPersistedDraftTax(DraftTax draftTax) {
        return draftTaxRepository.findById(draftTax.getId()).orElseThrow();
    }

    protected void assertPersistedDraftTaxToMatchAllProperties(DraftTax expectedDraftTax) {
        assertDraftTaxAllPropertiesEquals(expectedDraftTax, getPersistedDraftTax(expectedDraftTax));
    }

    protected void assertPersistedDraftTaxToMatchUpdatableProperties(DraftTax expectedDraftTax) {
        assertDraftTaxAllUpdatablePropertiesEquals(expectedDraftTax, getPersistedDraftTax(expectedDraftTax));
    }
}
