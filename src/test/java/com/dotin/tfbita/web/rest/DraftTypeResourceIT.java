package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftTypeAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftType;
import com.dotin.tfbita.repository.DraftTypeRepository;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
import com.dotin.tfbita.service.mapper.DraftTypeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link DraftTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftTypeResourceIT {

    private static final Integer DEFAULT_ALARM_TIME = 1;
    private static final Integer UPDATED_ALARM_TIME = 2;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DISABLE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DISABLE_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final Boolean DEFAULT_HAS_ASSURANCE = false;
    private static final Boolean UPDATED_HAS_ASSURANCE = true;

    private static final Boolean DEFAULT_HAS_SANCTION = false;
    private static final Boolean UPDATED_HAS_SANCTION = true;

    private static final Long DEFAULT_LATEST_CREDIT_SERIAL = 1L;
    private static final Long UPDATED_LATEST_CREDIT_SERIAL = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PORTAL = false;
    private static final Boolean UPDATED_PORTAL = true;

    private static final Boolean DEFAULT_USABLE = false;
    private static final Boolean UPDATED_USABLE = true;

    private static final String DEFAULT_DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_INFO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_INFO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TOPIC_INFO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TOPIC_INFO_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/draft-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftTypeRepository draftTypeRepository;

    @Autowired
    private DraftTypeMapper draftTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftTypeMockMvc;

    private DraftType draftType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftType createEntity(EntityManager em) {
        DraftType draftType = new DraftType()
            .alarmTime(DEFAULT_ALARM_TIME)
            .code(DEFAULT_CODE)
            .disableDate(DEFAULT_DISABLE_DATE)
            .duration(DEFAULT_DURATION)
            .hasAssurance(DEFAULT_HAS_ASSURANCE)
            .hasSanction(DEFAULT_HAS_SANCTION)
            .latestCreditSerial(DEFAULT_LATEST_CREDIT_SERIAL)
            .name(DEFAULT_NAME)
            .portal(DEFAULT_PORTAL)
            .usable(DEFAULT_USABLE)
            .defaultCurrencyCode(DEFAULT_DEFAULT_CURRENCY_CODE)
            .accountInfoCode(DEFAULT_ACCOUNT_INFO_CODE)
            .topicInfoCode(DEFAULT_TOPIC_INFO_CODE);
        return draftType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftType createUpdatedEntity(EntityManager em) {
        DraftType draftType = new DraftType()
            .alarmTime(UPDATED_ALARM_TIME)
            .code(UPDATED_CODE)
            .disableDate(UPDATED_DISABLE_DATE)
            .duration(UPDATED_DURATION)
            .hasAssurance(UPDATED_HAS_ASSURANCE)
            .hasSanction(UPDATED_HAS_SANCTION)
            .latestCreditSerial(UPDATED_LATEST_CREDIT_SERIAL)
            .name(UPDATED_NAME)
            .portal(UPDATED_PORTAL)
            .usable(UPDATED_USABLE)
            .defaultCurrencyCode(UPDATED_DEFAULT_CURRENCY_CODE)
            .accountInfoCode(UPDATED_ACCOUNT_INFO_CODE)
            .topicInfoCode(UPDATED_TOPIC_INFO_CODE);
        return draftType;
    }

    @BeforeEach
    public void initTest() {
        draftType = createEntity(em);
    }

    @Test
    @Transactional
    void createDraftType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftType
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(draftType);
        var returnedDraftTypeDTO = om.readValue(
            restDraftTypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftTypeDTO.class
        );

        // Validate the DraftType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftType = draftTypeMapper.toEntity(returnedDraftTypeDTO);
        assertDraftTypeUpdatableFieldsEquals(returnedDraftType, getPersistedDraftType(returnedDraftType));
    }

    @Test
    @Transactional
    void createDraftTypeWithExistingId() throws Exception {
        // Create the DraftType with an existing ID
        draftType.setId(1L);
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(draftType);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftTypes() throws Exception {
        // Initialize the database
        draftTypeRepository.saveAndFlush(draftType);

        // Get all the draftTypeList
        restDraftTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftType.getId().intValue())))
            .andExpect(jsonPath("$.[*].alarmTime").value(hasItem(DEFAULT_ALARM_TIME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].disableDate").value(hasItem(DEFAULT_DISABLE_DATE)))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].hasAssurance").value(hasItem(DEFAULT_HAS_ASSURANCE.booleanValue())))
            .andExpect(jsonPath("$.[*].hasSanction").value(hasItem(DEFAULT_HAS_SANCTION.booleanValue())))
            .andExpect(jsonPath("$.[*].latestCreditSerial").value(hasItem(DEFAULT_LATEST_CREDIT_SERIAL.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].portal").value(hasItem(DEFAULT_PORTAL.booleanValue())))
            .andExpect(jsonPath("$.[*].usable").value(hasItem(DEFAULT_USABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].defaultCurrencyCode").value(hasItem(DEFAULT_DEFAULT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].accountInfoCode").value(hasItem(DEFAULT_ACCOUNT_INFO_CODE)))
            .andExpect(jsonPath("$.[*].topicInfoCode").value(hasItem(DEFAULT_TOPIC_INFO_CODE)));
    }

    @Test
    @Transactional
    void getDraftType() throws Exception {
        // Initialize the database
        draftTypeRepository.saveAndFlush(draftType);

        // Get the draftType
        restDraftTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, draftType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftType.getId().intValue()))
            .andExpect(jsonPath("$.alarmTime").value(DEFAULT_ALARM_TIME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.disableDate").value(DEFAULT_DISABLE_DATE))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.hasAssurance").value(DEFAULT_HAS_ASSURANCE.booleanValue()))
            .andExpect(jsonPath("$.hasSanction").value(DEFAULT_HAS_SANCTION.booleanValue()))
            .andExpect(jsonPath("$.latestCreditSerial").value(DEFAULT_LATEST_CREDIT_SERIAL.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.portal").value(DEFAULT_PORTAL.booleanValue()))
            .andExpect(jsonPath("$.usable").value(DEFAULT_USABLE.booleanValue()))
            .andExpect(jsonPath("$.defaultCurrencyCode").value(DEFAULT_DEFAULT_CURRENCY_CODE))
            .andExpect(jsonPath("$.accountInfoCode").value(DEFAULT_ACCOUNT_INFO_CODE))
            .andExpect(jsonPath("$.topicInfoCode").value(DEFAULT_TOPIC_INFO_CODE));
    }

    @Test
    @Transactional
    void getNonExistingDraftType() throws Exception {
        // Get the draftType
        restDraftTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftType() throws Exception {
        // Initialize the database
        draftTypeRepository.saveAndFlush(draftType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftType
        DraftType updatedDraftType = draftTypeRepository.findById(draftType.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftType are not directly saved in db
        em.detach(updatedDraftType);
        updatedDraftType
            .alarmTime(UPDATED_ALARM_TIME)
            .code(UPDATED_CODE)
            .disableDate(UPDATED_DISABLE_DATE)
            .duration(UPDATED_DURATION)
            .hasAssurance(UPDATED_HAS_ASSURANCE)
            .hasSanction(UPDATED_HAS_SANCTION)
            .latestCreditSerial(UPDATED_LATEST_CREDIT_SERIAL)
            .name(UPDATED_NAME)
            .portal(UPDATED_PORTAL)
            .usable(UPDATED_USABLE)
            .defaultCurrencyCode(UPDATED_DEFAULT_CURRENCY_CODE)
            .accountInfoCode(UPDATED_ACCOUNT_INFO_CODE)
            .topicInfoCode(UPDATED_TOPIC_INFO_CODE);
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(updatedDraftType);

        restDraftTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftTypeToMatchAllProperties(updatedDraftType);
    }

    @Test
    @Transactional
    void putNonExistingDraftType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftType.setId(longCount.incrementAndGet());

        // Create the DraftType
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(draftType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftType.setId(longCount.incrementAndGet());

        // Create the DraftType
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(draftType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftType.setId(longCount.incrementAndGet());

        // Create the DraftType
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(draftType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftTypeWithPatch() throws Exception {
        // Initialize the database
        draftTypeRepository.saveAndFlush(draftType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftType using partial update
        DraftType partialUpdatedDraftType = new DraftType();
        partialUpdatedDraftType.setId(draftType.getId());

        partialUpdatedDraftType
            .alarmTime(UPDATED_ALARM_TIME)
            .duration(UPDATED_DURATION)
            .hasAssurance(UPDATED_HAS_ASSURANCE)
            .hasSanction(UPDATED_HAS_SANCTION)
            .portal(UPDATED_PORTAL)
            .usable(UPDATED_USABLE)
            .defaultCurrencyCode(UPDATED_DEFAULT_CURRENCY_CODE);

        restDraftTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftType))
            )
            .andExpect(status().isOk());

        // Validate the DraftType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftTypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftType, draftType),
            getPersistedDraftType(draftType)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftTypeWithPatch() throws Exception {
        // Initialize the database
        draftTypeRepository.saveAndFlush(draftType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftType using partial update
        DraftType partialUpdatedDraftType = new DraftType();
        partialUpdatedDraftType.setId(draftType.getId());

        partialUpdatedDraftType
            .alarmTime(UPDATED_ALARM_TIME)
            .code(UPDATED_CODE)
            .disableDate(UPDATED_DISABLE_DATE)
            .duration(UPDATED_DURATION)
            .hasAssurance(UPDATED_HAS_ASSURANCE)
            .hasSanction(UPDATED_HAS_SANCTION)
            .latestCreditSerial(UPDATED_LATEST_CREDIT_SERIAL)
            .name(UPDATED_NAME)
            .portal(UPDATED_PORTAL)
            .usable(UPDATED_USABLE)
            .defaultCurrencyCode(UPDATED_DEFAULT_CURRENCY_CODE)
            .accountInfoCode(UPDATED_ACCOUNT_INFO_CODE)
            .topicInfoCode(UPDATED_TOPIC_INFO_CODE);

        restDraftTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftType))
            )
            .andExpect(status().isOk());

        // Validate the DraftType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftTypeUpdatableFieldsEquals(partialUpdatedDraftType, getPersistedDraftType(partialUpdatedDraftType));
    }

    @Test
    @Transactional
    void patchNonExistingDraftType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftType.setId(longCount.incrementAndGet());

        // Create the DraftType
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(draftType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftTypeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftType.setId(longCount.incrementAndGet());

        // Create the DraftType
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(draftType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftType.setId(longCount.incrementAndGet());

        // Create the DraftType
        DraftTypeDTO draftTypeDTO = draftTypeMapper.toDto(draftType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftTypeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftType() throws Exception {
        // Initialize the database
        draftTypeRepository.saveAndFlush(draftType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftType
        restDraftTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftTypeRepository.count();
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

    protected DraftType getPersistedDraftType(DraftType draftType) {
        return draftTypeRepository.findById(draftType.getId()).orElseThrow();
    }

    protected void assertPersistedDraftTypeToMatchAllProperties(DraftType expectedDraftType) {
        assertDraftTypeAllPropertiesEquals(expectedDraftType, getPersistedDraftType(expectedDraftType));
    }

    protected void assertPersistedDraftTypeToMatchUpdatableProperties(DraftType expectedDraftType) {
        assertDraftTypeAllUpdatablePropertiesEquals(expectedDraftType, getPersistedDraftType(expectedDraftType));
    }
}
