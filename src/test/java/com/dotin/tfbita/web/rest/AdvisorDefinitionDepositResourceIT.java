package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.AdvisorDefinitionDepositAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.AdvisorDefinitionDeposit;
import com.dotin.tfbita.repository.AdvisorDefinitionDepositRepository;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDepositDTO;
import com.dotin.tfbita.service.mapper.AdvisorDefinitionDepositMapper;
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
 * Integration tests for the {@link AdvisorDefinitionDepositResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdvisorDefinitionDepositResourceIT {

    private static final String DEFAULT_ADVISOR_DEPOSIT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ADVISOR_DEPOSIT_NUMBER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DEFAULT_ADVISOR_DEPOSIT = false;
    private static final Boolean UPDATED_DEFAULT_ADVISOR_DEPOSIT = true;

    private static final String DEFAULT_DEPOSIT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_SWIFT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SWIFT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/advisor-definition-deposits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AdvisorDefinitionDepositRepository advisorDefinitionDepositRepository;

    @Autowired
    private AdvisorDefinitionDepositMapper advisorDefinitionDepositMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdvisorDefinitionDepositMockMvc;

    private AdvisorDefinitionDeposit advisorDefinitionDeposit;

    private AdvisorDefinitionDeposit insertedAdvisorDefinitionDeposit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdvisorDefinitionDeposit createEntity(EntityManager em) {
        AdvisorDefinitionDeposit advisorDefinitionDeposit = new AdvisorDefinitionDeposit()
            .advisorDepositNumber(DEFAULT_ADVISOR_DEPOSIT_NUMBER)
            .defaultAdvisorDeposit(DEFAULT_DEFAULT_ADVISOR_DEPOSIT)
            .depositNum(DEFAULT_DEPOSIT_NUM)
            .swiftCode(DEFAULT_SWIFT_CODE)
            .currencyCode(DEFAULT_CURRENCY_CODE);
        return advisorDefinitionDeposit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdvisorDefinitionDeposit createUpdatedEntity(EntityManager em) {
        AdvisorDefinitionDeposit advisorDefinitionDeposit = new AdvisorDefinitionDeposit()
            .advisorDepositNumber(UPDATED_ADVISOR_DEPOSIT_NUMBER)
            .defaultAdvisorDeposit(UPDATED_DEFAULT_ADVISOR_DEPOSIT)
            .depositNum(UPDATED_DEPOSIT_NUM)
            .swiftCode(UPDATED_SWIFT_CODE)
            .currencyCode(UPDATED_CURRENCY_CODE);
        return advisorDefinitionDeposit;
    }

    @BeforeEach
    public void initTest() {
        advisorDefinitionDeposit = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedAdvisorDefinitionDeposit != null) {
            advisorDefinitionDepositRepository.delete(insertedAdvisorDefinitionDeposit);
            insertedAdvisorDefinitionDeposit = null;
        }
    }

    @Test
    @Transactional
    void createAdvisorDefinitionDeposit() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AdvisorDefinitionDeposit
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);
        var returnedAdvisorDefinitionDepositDTO = om.readValue(
            restAdvisorDefinitionDepositMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(advisorDefinitionDepositDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AdvisorDefinitionDepositDTO.class
        );

        // Validate the AdvisorDefinitionDeposit in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedAdvisorDefinitionDeposit = advisorDefinitionDepositMapper.toEntity(returnedAdvisorDefinitionDepositDTO);
        assertAdvisorDefinitionDepositUpdatableFieldsEquals(
            returnedAdvisorDefinitionDeposit,
            getPersistedAdvisorDefinitionDeposit(returnedAdvisorDefinitionDeposit)
        );

        insertedAdvisorDefinitionDeposit = returnedAdvisorDefinitionDeposit;
    }

    @Test
    @Transactional
    void createAdvisorDefinitionDepositWithExistingId() throws Exception {
        // Create the AdvisorDefinitionDeposit with an existing ID
        advisorDefinitionDeposit.setId(1L);
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdvisorDefinitionDepositMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(advisorDefinitionDepositDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinitionDeposit in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAdvisorDefinitionDeposits() throws Exception {
        // Initialize the database
        insertedAdvisorDefinitionDeposit = advisorDefinitionDepositRepository.saveAndFlush(advisorDefinitionDeposit);

        // Get all the advisorDefinitionDepositList
        restAdvisorDefinitionDepositMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(advisorDefinitionDeposit.getId().intValue())))
            .andExpect(jsonPath("$.[*].advisorDepositNumber").value(hasItem(DEFAULT_ADVISOR_DEPOSIT_NUMBER)))
            .andExpect(jsonPath("$.[*].defaultAdvisorDeposit").value(hasItem(DEFAULT_DEFAULT_ADVISOR_DEPOSIT.booleanValue())))
            .andExpect(jsonPath("$.[*].depositNum").value(hasItem(DEFAULT_DEPOSIT_NUM)))
            .andExpect(jsonPath("$.[*].swiftCode").value(hasItem(DEFAULT_SWIFT_CODE)))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)));
    }

    @Test
    @Transactional
    void getAdvisorDefinitionDeposit() throws Exception {
        // Initialize the database
        insertedAdvisorDefinitionDeposit = advisorDefinitionDepositRepository.saveAndFlush(advisorDefinitionDeposit);

        // Get the advisorDefinitionDeposit
        restAdvisorDefinitionDepositMockMvc
            .perform(get(ENTITY_API_URL_ID, advisorDefinitionDeposit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(advisorDefinitionDeposit.getId().intValue()))
            .andExpect(jsonPath("$.advisorDepositNumber").value(DEFAULT_ADVISOR_DEPOSIT_NUMBER))
            .andExpect(jsonPath("$.defaultAdvisorDeposit").value(DEFAULT_DEFAULT_ADVISOR_DEPOSIT.booleanValue()))
            .andExpect(jsonPath("$.depositNum").value(DEFAULT_DEPOSIT_NUM))
            .andExpect(jsonPath("$.swiftCode").value(DEFAULT_SWIFT_CODE))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE));
    }

    @Test
    @Transactional
    void getNonExistingAdvisorDefinitionDeposit() throws Exception {
        // Get the advisorDefinitionDeposit
        restAdvisorDefinitionDepositMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAdvisorDefinitionDeposit() throws Exception {
        // Initialize the database
        insertedAdvisorDefinitionDeposit = advisorDefinitionDepositRepository.saveAndFlush(advisorDefinitionDeposit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the advisorDefinitionDeposit
        AdvisorDefinitionDeposit updatedAdvisorDefinitionDeposit = advisorDefinitionDepositRepository
            .findById(advisorDefinitionDeposit.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedAdvisorDefinitionDeposit are not directly saved in db
        em.detach(updatedAdvisorDefinitionDeposit);
        updatedAdvisorDefinitionDeposit
            .advisorDepositNumber(UPDATED_ADVISOR_DEPOSIT_NUMBER)
            .defaultAdvisorDeposit(UPDATED_DEFAULT_ADVISOR_DEPOSIT)
            .depositNum(UPDATED_DEPOSIT_NUM)
            .swiftCode(UPDATED_SWIFT_CODE)
            .currencyCode(UPDATED_CURRENCY_CODE);
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(updatedAdvisorDefinitionDeposit);

        restAdvisorDefinitionDepositMockMvc
            .perform(
                put(ENTITY_API_URL_ID, advisorDefinitionDepositDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(advisorDefinitionDepositDTO))
            )
            .andExpect(status().isOk());

        // Validate the AdvisorDefinitionDeposit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAdvisorDefinitionDepositToMatchAllProperties(updatedAdvisorDefinitionDeposit);
    }

    @Test
    @Transactional
    void putNonExistingAdvisorDefinitionDeposit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinitionDeposit.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinitionDeposit
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdvisorDefinitionDepositMockMvc
            .perform(
                put(ENTITY_API_URL_ID, advisorDefinitionDepositDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(advisorDefinitionDepositDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinitionDeposit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdvisorDefinitionDeposit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinitionDeposit.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinitionDeposit
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdvisorDefinitionDepositMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(advisorDefinitionDepositDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinitionDeposit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdvisorDefinitionDeposit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinitionDeposit.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinitionDeposit
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdvisorDefinitionDepositMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(advisorDefinitionDepositDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdvisorDefinitionDeposit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdvisorDefinitionDepositWithPatch() throws Exception {
        // Initialize the database
        insertedAdvisorDefinitionDeposit = advisorDefinitionDepositRepository.saveAndFlush(advisorDefinitionDeposit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the advisorDefinitionDeposit using partial update
        AdvisorDefinitionDeposit partialUpdatedAdvisorDefinitionDeposit = new AdvisorDefinitionDeposit();
        partialUpdatedAdvisorDefinitionDeposit.setId(advisorDefinitionDeposit.getId());

        partialUpdatedAdvisorDefinitionDeposit
            .advisorDepositNumber(UPDATED_ADVISOR_DEPOSIT_NUMBER)
            .depositNum(UPDATED_DEPOSIT_NUM)
            .swiftCode(UPDATED_SWIFT_CODE);

        restAdvisorDefinitionDepositMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdvisorDefinitionDeposit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAdvisorDefinitionDeposit))
            )
            .andExpect(status().isOk());

        // Validate the AdvisorDefinitionDeposit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAdvisorDefinitionDepositUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAdvisorDefinitionDeposit, advisorDefinitionDeposit),
            getPersistedAdvisorDefinitionDeposit(advisorDefinitionDeposit)
        );
    }

    @Test
    @Transactional
    void fullUpdateAdvisorDefinitionDepositWithPatch() throws Exception {
        // Initialize the database
        insertedAdvisorDefinitionDeposit = advisorDefinitionDepositRepository.saveAndFlush(advisorDefinitionDeposit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the advisorDefinitionDeposit using partial update
        AdvisorDefinitionDeposit partialUpdatedAdvisorDefinitionDeposit = new AdvisorDefinitionDeposit();
        partialUpdatedAdvisorDefinitionDeposit.setId(advisorDefinitionDeposit.getId());

        partialUpdatedAdvisorDefinitionDeposit
            .advisorDepositNumber(UPDATED_ADVISOR_DEPOSIT_NUMBER)
            .defaultAdvisorDeposit(UPDATED_DEFAULT_ADVISOR_DEPOSIT)
            .depositNum(UPDATED_DEPOSIT_NUM)
            .swiftCode(UPDATED_SWIFT_CODE)
            .currencyCode(UPDATED_CURRENCY_CODE);

        restAdvisorDefinitionDepositMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdvisorDefinitionDeposit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAdvisorDefinitionDeposit))
            )
            .andExpect(status().isOk());

        // Validate the AdvisorDefinitionDeposit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAdvisorDefinitionDepositUpdatableFieldsEquals(
            partialUpdatedAdvisorDefinitionDeposit,
            getPersistedAdvisorDefinitionDeposit(partialUpdatedAdvisorDefinitionDeposit)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAdvisorDefinitionDeposit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinitionDeposit.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinitionDeposit
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdvisorDefinitionDepositMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, advisorDefinitionDepositDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(advisorDefinitionDepositDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinitionDeposit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdvisorDefinitionDeposit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinitionDeposit.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinitionDeposit
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdvisorDefinitionDepositMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(advisorDefinitionDepositDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinitionDeposit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdvisorDefinitionDeposit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinitionDeposit.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinitionDeposit
        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdvisorDefinitionDepositMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(advisorDefinitionDepositDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdvisorDefinitionDeposit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdvisorDefinitionDeposit() throws Exception {
        // Initialize the database
        insertedAdvisorDefinitionDeposit = advisorDefinitionDepositRepository.saveAndFlush(advisorDefinitionDeposit);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the advisorDefinitionDeposit
        restAdvisorDefinitionDepositMockMvc
            .perform(delete(ENTITY_API_URL_ID, advisorDefinitionDeposit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return advisorDefinitionDepositRepository.count();
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

    protected AdvisorDefinitionDeposit getPersistedAdvisorDefinitionDeposit(AdvisorDefinitionDeposit advisorDefinitionDeposit) {
        return advisorDefinitionDepositRepository.findById(advisorDefinitionDeposit.getId()).orElseThrow();
    }

    protected void assertPersistedAdvisorDefinitionDepositToMatchAllProperties(AdvisorDefinitionDeposit expectedAdvisorDefinitionDeposit) {
        assertAdvisorDefinitionDepositAllPropertiesEquals(
            expectedAdvisorDefinitionDeposit,
            getPersistedAdvisorDefinitionDeposit(expectedAdvisorDefinitionDeposit)
        );
    }

    protected void assertPersistedAdvisorDefinitionDepositToMatchUpdatableProperties(
        AdvisorDefinitionDeposit expectedAdvisorDefinitionDeposit
    ) {
        assertAdvisorDefinitionDepositAllUpdatablePropertiesEquals(
            expectedAdvisorDefinitionDeposit,
            getPersistedAdvisorDefinitionDeposit(expectedAdvisorDefinitionDeposit)
        );
    }
}
