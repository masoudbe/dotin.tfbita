package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftUsedAssuranceAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftUsedAssurance;
import com.dotin.tfbita.repository.DraftUsedAssuranceRepository;
import com.dotin.tfbita.service.dto.DraftUsedAssuranceDTO;
import com.dotin.tfbita.service.mapper.DraftUsedAssuranceMapper;
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
 * Integration tests for the {@link DraftUsedAssuranceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftUsedAssuranceResourceIT {

    private static final String DEFAULT_ASSURANCE_RATE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSURANCE_RATE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSURANCE_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_ASSURANCE_SERIAL = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_EXCHANGE_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXCHANGE_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEFAULT_CURRENCY_USED_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEFAULT_CURRENCY_USED_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_USED_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_USED_COST = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/draft-used-assurances";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftUsedAssuranceRepository draftUsedAssuranceRepository;

    @Autowired
    private DraftUsedAssuranceMapper draftUsedAssuranceMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftUsedAssuranceMockMvc;

    private DraftUsedAssurance draftUsedAssurance;

    private DraftUsedAssurance insertedDraftUsedAssurance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftUsedAssurance createEntity(EntityManager em) {
        DraftUsedAssurance draftUsedAssurance = new DraftUsedAssurance()
            .assuranceRateId(DEFAULT_ASSURANCE_RATE_ID)
            .assuranceSerial(DEFAULT_ASSURANCE_SERIAL)
            .exchangeRate(DEFAULT_EXCHANGE_RATE)
            .defaultCurrencyUsedCost(DEFAULT_DEFAULT_CURRENCY_USED_COST)
            .usedCost(DEFAULT_USED_COST);
        return draftUsedAssurance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftUsedAssurance createUpdatedEntity(EntityManager em) {
        DraftUsedAssurance draftUsedAssurance = new DraftUsedAssurance()
            .assuranceRateId(UPDATED_ASSURANCE_RATE_ID)
            .assuranceSerial(UPDATED_ASSURANCE_SERIAL)
            .exchangeRate(UPDATED_EXCHANGE_RATE)
            .defaultCurrencyUsedCost(UPDATED_DEFAULT_CURRENCY_USED_COST)
            .usedCost(UPDATED_USED_COST);
        return draftUsedAssurance;
    }

    @BeforeEach
    public void initTest() {
        draftUsedAssurance = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftUsedAssurance != null) {
            draftUsedAssuranceRepository.delete(insertedDraftUsedAssurance);
            insertedDraftUsedAssurance = null;
        }
    }

    @Test
    @Transactional
    void createDraftUsedAssurance() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftUsedAssurance
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(draftUsedAssurance);
        var returnedDraftUsedAssuranceDTO = om.readValue(
            restDraftUsedAssuranceMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftUsedAssuranceDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftUsedAssuranceDTO.class
        );

        // Validate the DraftUsedAssurance in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftUsedAssurance = draftUsedAssuranceMapper.toEntity(returnedDraftUsedAssuranceDTO);
        assertDraftUsedAssuranceUpdatableFieldsEquals(
            returnedDraftUsedAssurance,
            getPersistedDraftUsedAssurance(returnedDraftUsedAssurance)
        );

        insertedDraftUsedAssurance = returnedDraftUsedAssurance;
    }

    @Test
    @Transactional
    void createDraftUsedAssuranceWithExistingId() throws Exception {
        // Create the DraftUsedAssurance with an existing ID
        draftUsedAssurance.setId(1L);
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(draftUsedAssurance);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftUsedAssuranceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftUsedAssuranceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftUsedAssurance in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftUsedAssurances() throws Exception {
        // Initialize the database
        insertedDraftUsedAssurance = draftUsedAssuranceRepository.saveAndFlush(draftUsedAssurance);

        // Get all the draftUsedAssuranceList
        restDraftUsedAssuranceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftUsedAssurance.getId().intValue())))
            .andExpect(jsonPath("$.[*].assuranceRateId").value(hasItem(DEFAULT_ASSURANCE_RATE_ID)))
            .andExpect(jsonPath("$.[*].assuranceSerial").value(hasItem(DEFAULT_ASSURANCE_SERIAL)))
            .andExpect(jsonPath("$.[*].exchangeRate").value(hasItem(sameNumber(DEFAULT_EXCHANGE_RATE))))
            .andExpect(jsonPath("$.[*].defaultCurrencyUsedCost").value(hasItem(sameNumber(DEFAULT_DEFAULT_CURRENCY_USED_COST))))
            .andExpect(jsonPath("$.[*].usedCost").value(hasItem(sameNumber(DEFAULT_USED_COST))));
    }

    @Test
    @Transactional
    void getDraftUsedAssurance() throws Exception {
        // Initialize the database
        insertedDraftUsedAssurance = draftUsedAssuranceRepository.saveAndFlush(draftUsedAssurance);

        // Get the draftUsedAssurance
        restDraftUsedAssuranceMockMvc
            .perform(get(ENTITY_API_URL_ID, draftUsedAssurance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftUsedAssurance.getId().intValue()))
            .andExpect(jsonPath("$.assuranceRateId").value(DEFAULT_ASSURANCE_RATE_ID))
            .andExpect(jsonPath("$.assuranceSerial").value(DEFAULT_ASSURANCE_SERIAL))
            .andExpect(jsonPath("$.exchangeRate").value(sameNumber(DEFAULT_EXCHANGE_RATE)))
            .andExpect(jsonPath("$.defaultCurrencyUsedCost").value(sameNumber(DEFAULT_DEFAULT_CURRENCY_USED_COST)))
            .andExpect(jsonPath("$.usedCost").value(sameNumber(DEFAULT_USED_COST)));
    }

    @Test
    @Transactional
    void getNonExistingDraftUsedAssurance() throws Exception {
        // Get the draftUsedAssurance
        restDraftUsedAssuranceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftUsedAssurance() throws Exception {
        // Initialize the database
        insertedDraftUsedAssurance = draftUsedAssuranceRepository.saveAndFlush(draftUsedAssurance);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftUsedAssurance
        DraftUsedAssurance updatedDraftUsedAssurance = draftUsedAssuranceRepository.findById(draftUsedAssurance.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftUsedAssurance are not directly saved in db
        em.detach(updatedDraftUsedAssurance);
        updatedDraftUsedAssurance
            .assuranceRateId(UPDATED_ASSURANCE_RATE_ID)
            .assuranceSerial(UPDATED_ASSURANCE_SERIAL)
            .exchangeRate(UPDATED_EXCHANGE_RATE)
            .defaultCurrencyUsedCost(UPDATED_DEFAULT_CURRENCY_USED_COST)
            .usedCost(UPDATED_USED_COST);
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(updatedDraftUsedAssurance);

        restDraftUsedAssuranceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftUsedAssuranceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftUsedAssuranceDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftUsedAssurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftUsedAssuranceToMatchAllProperties(updatedDraftUsedAssurance);
    }

    @Test
    @Transactional
    void putNonExistingDraftUsedAssurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftUsedAssurance.setId(longCount.incrementAndGet());

        // Create the DraftUsedAssurance
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(draftUsedAssurance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftUsedAssuranceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftUsedAssuranceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftUsedAssuranceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftUsedAssurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftUsedAssurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftUsedAssurance.setId(longCount.incrementAndGet());

        // Create the DraftUsedAssurance
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(draftUsedAssurance);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftUsedAssuranceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftUsedAssuranceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftUsedAssurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftUsedAssurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftUsedAssurance.setId(longCount.incrementAndGet());

        // Create the DraftUsedAssurance
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(draftUsedAssurance);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftUsedAssuranceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftUsedAssuranceDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftUsedAssurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftUsedAssuranceWithPatch() throws Exception {
        // Initialize the database
        insertedDraftUsedAssurance = draftUsedAssuranceRepository.saveAndFlush(draftUsedAssurance);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftUsedAssurance using partial update
        DraftUsedAssurance partialUpdatedDraftUsedAssurance = new DraftUsedAssurance();
        partialUpdatedDraftUsedAssurance.setId(draftUsedAssurance.getId());

        partialUpdatedDraftUsedAssurance
            .assuranceSerial(UPDATED_ASSURANCE_SERIAL)
            .defaultCurrencyUsedCost(UPDATED_DEFAULT_CURRENCY_USED_COST);

        restDraftUsedAssuranceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftUsedAssurance.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftUsedAssurance))
            )
            .andExpect(status().isOk());

        // Validate the DraftUsedAssurance in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftUsedAssuranceUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftUsedAssurance, draftUsedAssurance),
            getPersistedDraftUsedAssurance(draftUsedAssurance)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftUsedAssuranceWithPatch() throws Exception {
        // Initialize the database
        insertedDraftUsedAssurance = draftUsedAssuranceRepository.saveAndFlush(draftUsedAssurance);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftUsedAssurance using partial update
        DraftUsedAssurance partialUpdatedDraftUsedAssurance = new DraftUsedAssurance();
        partialUpdatedDraftUsedAssurance.setId(draftUsedAssurance.getId());

        partialUpdatedDraftUsedAssurance
            .assuranceRateId(UPDATED_ASSURANCE_RATE_ID)
            .assuranceSerial(UPDATED_ASSURANCE_SERIAL)
            .exchangeRate(UPDATED_EXCHANGE_RATE)
            .defaultCurrencyUsedCost(UPDATED_DEFAULT_CURRENCY_USED_COST)
            .usedCost(UPDATED_USED_COST);

        restDraftUsedAssuranceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftUsedAssurance.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftUsedAssurance))
            )
            .andExpect(status().isOk());

        // Validate the DraftUsedAssurance in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftUsedAssuranceUpdatableFieldsEquals(
            partialUpdatedDraftUsedAssurance,
            getPersistedDraftUsedAssurance(partialUpdatedDraftUsedAssurance)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftUsedAssurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftUsedAssurance.setId(longCount.incrementAndGet());

        // Create the DraftUsedAssurance
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(draftUsedAssurance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftUsedAssuranceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftUsedAssuranceDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftUsedAssuranceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftUsedAssurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftUsedAssurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftUsedAssurance.setId(longCount.incrementAndGet());

        // Create the DraftUsedAssurance
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(draftUsedAssurance);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftUsedAssuranceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftUsedAssuranceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftUsedAssurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftUsedAssurance() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftUsedAssurance.setId(longCount.incrementAndGet());

        // Create the DraftUsedAssurance
        DraftUsedAssuranceDTO draftUsedAssuranceDTO = draftUsedAssuranceMapper.toDto(draftUsedAssurance);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftUsedAssuranceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftUsedAssuranceDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftUsedAssurance in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftUsedAssurance() throws Exception {
        // Initialize the database
        insertedDraftUsedAssurance = draftUsedAssuranceRepository.saveAndFlush(draftUsedAssurance);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftUsedAssurance
        restDraftUsedAssuranceMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftUsedAssurance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftUsedAssuranceRepository.count();
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

    protected DraftUsedAssurance getPersistedDraftUsedAssurance(DraftUsedAssurance draftUsedAssurance) {
        return draftUsedAssuranceRepository.findById(draftUsedAssurance.getId()).orElseThrow();
    }

    protected void assertPersistedDraftUsedAssuranceToMatchAllProperties(DraftUsedAssurance expectedDraftUsedAssurance) {
        assertDraftUsedAssuranceAllPropertiesEquals(expectedDraftUsedAssurance, getPersistedDraftUsedAssurance(expectedDraftUsedAssurance));
    }

    protected void assertPersistedDraftUsedAssuranceToMatchUpdatableProperties(DraftUsedAssurance expectedDraftUsedAssurance) {
        assertDraftUsedAssuranceAllUpdatablePropertiesEquals(
            expectedDraftUsedAssurance,
            getPersistedDraftUsedAssurance(expectedDraftUsedAssurance)
        );
    }
}
