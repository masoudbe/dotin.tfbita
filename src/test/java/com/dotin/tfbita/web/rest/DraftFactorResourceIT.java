package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftFactorAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftFactor;
import com.dotin.tfbita.repository.DraftFactorRepository;
import com.dotin.tfbita.service.dto.DraftFactorDTO;
import com.dotin.tfbita.service.mapper.DraftFactorMapper;
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
 * Integration tests for the {@link DraftFactorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftFactorResourceIT {

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_EQ_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_EQ_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_FACTOR_DATE = "AAAAAAAAAA";
    private static final String UPDATED_FACTOR_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ISSUE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/draft-factors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftFactorRepository draftFactorRepository;

    @Autowired
    private DraftFactorMapper draftFactorMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftFactorMockMvc;

    private DraftFactor draftFactor;

    private DraftFactor insertedDraftFactor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftFactor createEntity(EntityManager em) {
        DraftFactor draftFactor = new DraftFactor()
            .amount(DEFAULT_AMOUNT)
            .description(DEFAULT_DESCRIPTION)
            .eqAmount(DEFAULT_EQ_AMOUNT)
            .factorDate(DEFAULT_FACTOR_DATE)
            .issueDate(DEFAULT_ISSUE_DATE)
            .serial(DEFAULT_SERIAL)
            .currencyCode(DEFAULT_CURRENCY_CODE);
        return draftFactor;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftFactor createUpdatedEntity(EntityManager em) {
        DraftFactor draftFactor = new DraftFactor()
            .amount(UPDATED_AMOUNT)
            .description(UPDATED_DESCRIPTION)
            .eqAmount(UPDATED_EQ_AMOUNT)
            .factorDate(UPDATED_FACTOR_DATE)
            .issueDate(UPDATED_ISSUE_DATE)
            .serial(UPDATED_SERIAL)
            .currencyCode(UPDATED_CURRENCY_CODE);
        return draftFactor;
    }

    @BeforeEach
    public void initTest() {
        draftFactor = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftFactor != null) {
            draftFactorRepository.delete(insertedDraftFactor);
            insertedDraftFactor = null;
        }
    }

    @Test
    @Transactional
    void createDraftFactor() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftFactor
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(draftFactor);
        var returnedDraftFactorDTO = om.readValue(
            restDraftFactorMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftFactorDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftFactorDTO.class
        );

        // Validate the DraftFactor in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftFactor = draftFactorMapper.toEntity(returnedDraftFactorDTO);
        assertDraftFactorUpdatableFieldsEquals(returnedDraftFactor, getPersistedDraftFactor(returnedDraftFactor));

        insertedDraftFactor = returnedDraftFactor;
    }

    @Test
    @Transactional
    void createDraftFactorWithExistingId() throws Exception {
        // Create the DraftFactor with an existing ID
        draftFactor.setId(1L);
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(draftFactor);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftFactorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftFactorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftFactor in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftFactors() throws Exception {
        // Initialize the database
        insertedDraftFactor = draftFactorRepository.saveAndFlush(draftFactor);

        // Get all the draftFactorList
        restDraftFactorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftFactor.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].eqAmount").value(hasItem(sameNumber(DEFAULT_EQ_AMOUNT))))
            .andExpect(jsonPath("$.[*].factorDate").value(hasItem(DEFAULT_FACTOR_DATE)))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL)))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)));
    }

    @Test
    @Transactional
    void getDraftFactor() throws Exception {
        // Initialize the database
        insertedDraftFactor = draftFactorRepository.saveAndFlush(draftFactor);

        // Get the draftFactor
        restDraftFactorMockMvc
            .perform(get(ENTITY_API_URL_ID, draftFactor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftFactor.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.eqAmount").value(sameNumber(DEFAULT_EQ_AMOUNT)))
            .andExpect(jsonPath("$.factorDate").value(DEFAULT_FACTOR_DATE))
            .andExpect(jsonPath("$.issueDate").value(DEFAULT_ISSUE_DATE))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE));
    }

    @Test
    @Transactional
    void getNonExistingDraftFactor() throws Exception {
        // Get the draftFactor
        restDraftFactorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftFactor() throws Exception {
        // Initialize the database
        insertedDraftFactor = draftFactorRepository.saveAndFlush(draftFactor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftFactor
        DraftFactor updatedDraftFactor = draftFactorRepository.findById(draftFactor.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftFactor are not directly saved in db
        em.detach(updatedDraftFactor);
        updatedDraftFactor
            .amount(UPDATED_AMOUNT)
            .description(UPDATED_DESCRIPTION)
            .eqAmount(UPDATED_EQ_AMOUNT)
            .factorDate(UPDATED_FACTOR_DATE)
            .issueDate(UPDATED_ISSUE_DATE)
            .serial(UPDATED_SERIAL)
            .currencyCode(UPDATED_CURRENCY_CODE);
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(updatedDraftFactor);

        restDraftFactorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftFactorDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftFactorDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftFactor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftFactorToMatchAllProperties(updatedDraftFactor);
    }

    @Test
    @Transactional
    void putNonExistingDraftFactor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftFactor.setId(longCount.incrementAndGet());

        // Create the DraftFactor
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(draftFactor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftFactorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftFactorDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftFactorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftFactor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftFactor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftFactor.setId(longCount.incrementAndGet());

        // Create the DraftFactor
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(draftFactor);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftFactorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftFactorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftFactor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftFactor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftFactor.setId(longCount.incrementAndGet());

        // Create the DraftFactor
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(draftFactor);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftFactorMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftFactorDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftFactor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftFactorWithPatch() throws Exception {
        // Initialize the database
        insertedDraftFactor = draftFactorRepository.saveAndFlush(draftFactor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftFactor using partial update
        DraftFactor partialUpdatedDraftFactor = new DraftFactor();
        partialUpdatedDraftFactor.setId(draftFactor.getId());

        partialUpdatedDraftFactor
            .amount(UPDATED_AMOUNT)
            .description(UPDATED_DESCRIPTION)
            .eqAmount(UPDATED_EQ_AMOUNT)
            .factorDate(UPDATED_FACTOR_DATE);

        restDraftFactorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftFactor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftFactor))
            )
            .andExpect(status().isOk());

        // Validate the DraftFactor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftFactorUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftFactor, draftFactor),
            getPersistedDraftFactor(draftFactor)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftFactorWithPatch() throws Exception {
        // Initialize the database
        insertedDraftFactor = draftFactorRepository.saveAndFlush(draftFactor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftFactor using partial update
        DraftFactor partialUpdatedDraftFactor = new DraftFactor();
        partialUpdatedDraftFactor.setId(draftFactor.getId());

        partialUpdatedDraftFactor
            .amount(UPDATED_AMOUNT)
            .description(UPDATED_DESCRIPTION)
            .eqAmount(UPDATED_EQ_AMOUNT)
            .factorDate(UPDATED_FACTOR_DATE)
            .issueDate(UPDATED_ISSUE_DATE)
            .serial(UPDATED_SERIAL)
            .currencyCode(UPDATED_CURRENCY_CODE);

        restDraftFactorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftFactor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftFactor))
            )
            .andExpect(status().isOk());

        // Validate the DraftFactor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftFactorUpdatableFieldsEquals(partialUpdatedDraftFactor, getPersistedDraftFactor(partialUpdatedDraftFactor));
    }

    @Test
    @Transactional
    void patchNonExistingDraftFactor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftFactor.setId(longCount.incrementAndGet());

        // Create the DraftFactor
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(draftFactor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftFactorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftFactorDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftFactorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftFactor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftFactor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftFactor.setId(longCount.incrementAndGet());

        // Create the DraftFactor
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(draftFactor);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftFactorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftFactorDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftFactor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftFactor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftFactor.setId(longCount.incrementAndGet());

        // Create the DraftFactor
        DraftFactorDTO draftFactorDTO = draftFactorMapper.toDto(draftFactor);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftFactorMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftFactorDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftFactor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftFactor() throws Exception {
        // Initialize the database
        insertedDraftFactor = draftFactorRepository.saveAndFlush(draftFactor);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftFactor
        restDraftFactorMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftFactor.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftFactorRepository.count();
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

    protected DraftFactor getPersistedDraftFactor(DraftFactor draftFactor) {
        return draftFactorRepository.findById(draftFactor.getId()).orElseThrow();
    }

    protected void assertPersistedDraftFactorToMatchAllProperties(DraftFactor expectedDraftFactor) {
        assertDraftFactorAllPropertiesEquals(expectedDraftFactor, getPersistedDraftFactor(expectedDraftFactor));
    }

    protected void assertPersistedDraftFactorToMatchUpdatableProperties(DraftFactor expectedDraftFactor) {
        assertDraftFactorAllUpdatablePropertiesEquals(expectedDraftFactor, getPersistedDraftFactor(expectedDraftFactor));
    }
}
