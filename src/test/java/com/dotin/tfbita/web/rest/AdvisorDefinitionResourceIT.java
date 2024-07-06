package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.AdvisorDefinitionAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.AdvisorDefinition;
import com.dotin.tfbita.repository.AdvisorDefinitionRepository;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
import com.dotin.tfbita.service.mapper.AdvisorDefinitionMapper;
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
 * Integration tests for the {@link AdvisorDefinitionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdvisorDefinitionResourceIT {

    private static final String DEFAULT_CAPTION = "AAAAAAAAAA";
    private static final String UPDATED_CAPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_ISO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_ISO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPOSIT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_DEPOSIT_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_SWIFT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SWIFT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCIES_CODES = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCIES_CODES = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/advisor-definitions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AdvisorDefinitionRepository advisorDefinitionRepository;

    @Autowired
    private AdvisorDefinitionMapper advisorDefinitionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdvisorDefinitionMockMvc;

    private AdvisorDefinition advisorDefinition;

    private AdvisorDefinition insertedAdvisorDefinition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdvisorDefinition createEntity(EntityManager em) {
        AdvisorDefinition advisorDefinition = new AdvisorDefinition()
            .caption(DEFAULT_CAPTION)
            .code(DEFAULT_CODE)
            .countryIsoCode(DEFAULT_COUNTRY_ISO_CODE)
            .depositNum(DEFAULT_DEPOSIT_NUM)
            .swiftCode(DEFAULT_SWIFT_CODE)
            .defaultCurrencyCode(DEFAULT_DEFAULT_CURRENCY_CODE)
            .currenciesCodes(DEFAULT_CURRENCIES_CODES)
            .countryCode(DEFAULT_COUNTRY_CODE)
            .bankCode(DEFAULT_BANK_CODE)
            .branchCode(DEFAULT_BRANCH_CODE);
        return advisorDefinition;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdvisorDefinition createUpdatedEntity(EntityManager em) {
        AdvisorDefinition advisorDefinition = new AdvisorDefinition()
            .caption(UPDATED_CAPTION)
            .code(UPDATED_CODE)
            .countryIsoCode(UPDATED_COUNTRY_ISO_CODE)
            .depositNum(UPDATED_DEPOSIT_NUM)
            .swiftCode(UPDATED_SWIFT_CODE)
            .defaultCurrencyCode(UPDATED_DEFAULT_CURRENCY_CODE)
            .currenciesCodes(UPDATED_CURRENCIES_CODES)
            .countryCode(UPDATED_COUNTRY_CODE)
            .bankCode(UPDATED_BANK_CODE)
            .branchCode(UPDATED_BRANCH_CODE);
        return advisorDefinition;
    }

    @BeforeEach
    public void initTest() {
        advisorDefinition = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedAdvisorDefinition != null) {
            advisorDefinitionRepository.delete(insertedAdvisorDefinition);
            insertedAdvisorDefinition = null;
        }
    }

    @Test
    @Transactional
    void createAdvisorDefinition() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AdvisorDefinition
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(advisorDefinition);
        var returnedAdvisorDefinitionDTO = om.readValue(
            restAdvisorDefinitionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(advisorDefinitionDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AdvisorDefinitionDTO.class
        );

        // Validate the AdvisorDefinition in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedAdvisorDefinition = advisorDefinitionMapper.toEntity(returnedAdvisorDefinitionDTO);
        assertAdvisorDefinitionUpdatableFieldsEquals(returnedAdvisorDefinition, getPersistedAdvisorDefinition(returnedAdvisorDefinition));

        insertedAdvisorDefinition = returnedAdvisorDefinition;
    }

    @Test
    @Transactional
    void createAdvisorDefinitionWithExistingId() throws Exception {
        // Create the AdvisorDefinition with an existing ID
        advisorDefinition.setId(1L);
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(advisorDefinition);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdvisorDefinitionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(advisorDefinitionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinition in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAdvisorDefinitions() throws Exception {
        // Initialize the database
        insertedAdvisorDefinition = advisorDefinitionRepository.saveAndFlush(advisorDefinition);

        // Get all the advisorDefinitionList
        restAdvisorDefinitionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(advisorDefinition.getId().intValue())))
            .andExpect(jsonPath("$.[*].caption").value(hasItem(DEFAULT_CAPTION)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].countryIsoCode").value(hasItem(DEFAULT_COUNTRY_ISO_CODE)))
            .andExpect(jsonPath("$.[*].depositNum").value(hasItem(DEFAULT_DEPOSIT_NUM)))
            .andExpect(jsonPath("$.[*].swiftCode").value(hasItem(DEFAULT_SWIFT_CODE)))
            .andExpect(jsonPath("$.[*].defaultCurrencyCode").value(hasItem(DEFAULT_DEFAULT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].currenciesCodes").value(hasItem(DEFAULT_CURRENCIES_CODES)))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].bankCode").value(hasItem(DEFAULT_BANK_CODE)))
            .andExpect(jsonPath("$.[*].branchCode").value(hasItem(DEFAULT_BRANCH_CODE)));
    }

    @Test
    @Transactional
    void getAdvisorDefinition() throws Exception {
        // Initialize the database
        insertedAdvisorDefinition = advisorDefinitionRepository.saveAndFlush(advisorDefinition);

        // Get the advisorDefinition
        restAdvisorDefinitionMockMvc
            .perform(get(ENTITY_API_URL_ID, advisorDefinition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(advisorDefinition.getId().intValue()))
            .andExpect(jsonPath("$.caption").value(DEFAULT_CAPTION))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.countryIsoCode").value(DEFAULT_COUNTRY_ISO_CODE))
            .andExpect(jsonPath("$.depositNum").value(DEFAULT_DEPOSIT_NUM))
            .andExpect(jsonPath("$.swiftCode").value(DEFAULT_SWIFT_CODE))
            .andExpect(jsonPath("$.defaultCurrencyCode").value(DEFAULT_DEFAULT_CURRENCY_CODE))
            .andExpect(jsonPath("$.currenciesCodes").value(DEFAULT_CURRENCIES_CODES))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.bankCode").value(DEFAULT_BANK_CODE))
            .andExpect(jsonPath("$.branchCode").value(DEFAULT_BRANCH_CODE));
    }

    @Test
    @Transactional
    void getNonExistingAdvisorDefinition() throws Exception {
        // Get the advisorDefinition
        restAdvisorDefinitionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAdvisorDefinition() throws Exception {
        // Initialize the database
        insertedAdvisorDefinition = advisorDefinitionRepository.saveAndFlush(advisorDefinition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the advisorDefinition
        AdvisorDefinition updatedAdvisorDefinition = advisorDefinitionRepository.findById(advisorDefinition.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAdvisorDefinition are not directly saved in db
        em.detach(updatedAdvisorDefinition);
        updatedAdvisorDefinition
            .caption(UPDATED_CAPTION)
            .code(UPDATED_CODE)
            .countryIsoCode(UPDATED_COUNTRY_ISO_CODE)
            .depositNum(UPDATED_DEPOSIT_NUM)
            .swiftCode(UPDATED_SWIFT_CODE)
            .defaultCurrencyCode(UPDATED_DEFAULT_CURRENCY_CODE)
            .currenciesCodes(UPDATED_CURRENCIES_CODES)
            .countryCode(UPDATED_COUNTRY_CODE)
            .bankCode(UPDATED_BANK_CODE)
            .branchCode(UPDATED_BRANCH_CODE);
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(updatedAdvisorDefinition);

        restAdvisorDefinitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, advisorDefinitionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(advisorDefinitionDTO))
            )
            .andExpect(status().isOk());

        // Validate the AdvisorDefinition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAdvisorDefinitionToMatchAllProperties(updatedAdvisorDefinition);
    }

    @Test
    @Transactional
    void putNonExistingAdvisorDefinition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinition.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinition
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(advisorDefinition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdvisorDefinitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, advisorDefinitionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(advisorDefinitionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdvisorDefinition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinition.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinition
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(advisorDefinition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdvisorDefinitionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(advisorDefinitionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdvisorDefinition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinition.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinition
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(advisorDefinition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdvisorDefinitionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(advisorDefinitionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdvisorDefinition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdvisorDefinitionWithPatch() throws Exception {
        // Initialize the database
        insertedAdvisorDefinition = advisorDefinitionRepository.saveAndFlush(advisorDefinition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the advisorDefinition using partial update
        AdvisorDefinition partialUpdatedAdvisorDefinition = new AdvisorDefinition();
        partialUpdatedAdvisorDefinition.setId(advisorDefinition.getId());

        partialUpdatedAdvisorDefinition
            .countryIsoCode(UPDATED_COUNTRY_ISO_CODE)
            .currenciesCodes(UPDATED_CURRENCIES_CODES)
            .countryCode(UPDATED_COUNTRY_CODE)
            .bankCode(UPDATED_BANK_CODE)
            .branchCode(UPDATED_BRANCH_CODE);

        restAdvisorDefinitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdvisorDefinition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAdvisorDefinition))
            )
            .andExpect(status().isOk());

        // Validate the AdvisorDefinition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAdvisorDefinitionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAdvisorDefinition, advisorDefinition),
            getPersistedAdvisorDefinition(advisorDefinition)
        );
    }

    @Test
    @Transactional
    void fullUpdateAdvisorDefinitionWithPatch() throws Exception {
        // Initialize the database
        insertedAdvisorDefinition = advisorDefinitionRepository.saveAndFlush(advisorDefinition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the advisorDefinition using partial update
        AdvisorDefinition partialUpdatedAdvisorDefinition = new AdvisorDefinition();
        partialUpdatedAdvisorDefinition.setId(advisorDefinition.getId());

        partialUpdatedAdvisorDefinition
            .caption(UPDATED_CAPTION)
            .code(UPDATED_CODE)
            .countryIsoCode(UPDATED_COUNTRY_ISO_CODE)
            .depositNum(UPDATED_DEPOSIT_NUM)
            .swiftCode(UPDATED_SWIFT_CODE)
            .defaultCurrencyCode(UPDATED_DEFAULT_CURRENCY_CODE)
            .currenciesCodes(UPDATED_CURRENCIES_CODES)
            .countryCode(UPDATED_COUNTRY_CODE)
            .bankCode(UPDATED_BANK_CODE)
            .branchCode(UPDATED_BRANCH_CODE);

        restAdvisorDefinitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdvisorDefinition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAdvisorDefinition))
            )
            .andExpect(status().isOk());

        // Validate the AdvisorDefinition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAdvisorDefinitionUpdatableFieldsEquals(
            partialUpdatedAdvisorDefinition,
            getPersistedAdvisorDefinition(partialUpdatedAdvisorDefinition)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAdvisorDefinition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinition.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinition
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(advisorDefinition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdvisorDefinitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, advisorDefinitionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(advisorDefinitionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdvisorDefinition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinition.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinition
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(advisorDefinition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdvisorDefinitionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(advisorDefinitionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdvisorDefinition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdvisorDefinition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        advisorDefinition.setId(longCount.incrementAndGet());

        // Create the AdvisorDefinition
        AdvisorDefinitionDTO advisorDefinitionDTO = advisorDefinitionMapper.toDto(advisorDefinition);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdvisorDefinitionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(advisorDefinitionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdvisorDefinition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdvisorDefinition() throws Exception {
        // Initialize the database
        insertedAdvisorDefinition = advisorDefinitionRepository.saveAndFlush(advisorDefinition);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the advisorDefinition
        restAdvisorDefinitionMockMvc
            .perform(delete(ENTITY_API_URL_ID, advisorDefinition.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return advisorDefinitionRepository.count();
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

    protected AdvisorDefinition getPersistedAdvisorDefinition(AdvisorDefinition advisorDefinition) {
        return advisorDefinitionRepository.findById(advisorDefinition.getId()).orElseThrow();
    }

    protected void assertPersistedAdvisorDefinitionToMatchAllProperties(AdvisorDefinition expectedAdvisorDefinition) {
        assertAdvisorDefinitionAllPropertiesEquals(expectedAdvisorDefinition, getPersistedAdvisorDefinition(expectedAdvisorDefinition));
    }

    protected void assertPersistedAdvisorDefinitionToMatchUpdatableProperties(AdvisorDefinition expectedAdvisorDefinition) {
        assertAdvisorDefinitionAllUpdatablePropertiesEquals(
            expectedAdvisorDefinition,
            getPersistedAdvisorDefinition(expectedAdvisorDefinition)
        );
    }
}
