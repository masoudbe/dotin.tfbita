package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.AdditionalBrokerInformationAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.AdditionalBrokerInformation;
import com.dotin.tfbita.repository.AdditionalBrokerInformationRepository;
import com.dotin.tfbita.service.dto.AdditionalBrokerInformationDTO;
import com.dotin.tfbita.service.mapper.AdditionalBrokerInformationMapper;
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
 * Integration tests for the {@link AdditionalBrokerInformationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AdditionalBrokerInformationResourceIT {

    private static final String DEFAULT_DATE_OF_START_RELATION = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_START_RELATION = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_LIMIT = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_LIMIT = "BBBBBBBBBB";

    private static final String DEFAULT_REVOKED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REVOKED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_REVOKED_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_REVOKED_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIDENTIAL = "AAAAAAAAAA";
    private static final String UPDATED_CONFIDENTIAL = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_BROKER_SERVICES = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_BROKER_SERVICES = "BBBBBBBBBB";

    private static final String DEFAULT_SANCTIONED_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SANCTIONED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CONSIDERATIONS = "AAAAAAAAAA";
    private static final String UPDATED_CONSIDERATIONS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_WAYS_OF_COMMUNICATION = "AAAAAAAAAA";
    private static final String UPDATED_WAYS_OF_COMMUNICATION = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICES_AVAILABLE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICES_AVAILABLE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ACCEPTANCE_POLICY = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ACCEPTANCE_POLICY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/additional-broker-informations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AdditionalBrokerInformationRepository additionalBrokerInformationRepository;

    @Autowired
    private AdditionalBrokerInformationMapper additionalBrokerInformationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdditionalBrokerInformationMockMvc;

    private AdditionalBrokerInformation additionalBrokerInformation;

    private AdditionalBrokerInformation insertedAdditionalBrokerInformation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdditionalBrokerInformation createEntity(EntityManager em) {
        AdditionalBrokerInformation additionalBrokerInformation = new AdditionalBrokerInformation()
            .dateOfStartRelation(DEFAULT_DATE_OF_START_RELATION)
            .creditLimit(DEFAULT_CREDIT_LIMIT)
            .revokedDate(DEFAULT_REVOKED_DATE)
            .revokedNote(DEFAULT_REVOKED_NOTE)
            .confidential(DEFAULT_CONFIDENTIAL)
            .otherBrokerServices(DEFAULT_OTHER_BROKER_SERVICES)
            .sanctionedStatus(DEFAULT_SANCTIONED_STATUS)
            .considerations(DEFAULT_CONSIDERATIONS)
            .description(DEFAULT_DESCRIPTION)
            .waysOfCommunication(DEFAULT_WAYS_OF_COMMUNICATION)
            .servicesAvailable(DEFAULT_SERVICES_AVAILABLE)
            .customerAcceptancePolicy(DEFAULT_CUSTOMER_ACCEPTANCE_POLICY);
        return additionalBrokerInformation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdditionalBrokerInformation createUpdatedEntity(EntityManager em) {
        AdditionalBrokerInformation additionalBrokerInformation = new AdditionalBrokerInformation()
            .dateOfStartRelation(UPDATED_DATE_OF_START_RELATION)
            .creditLimit(UPDATED_CREDIT_LIMIT)
            .revokedDate(UPDATED_REVOKED_DATE)
            .revokedNote(UPDATED_REVOKED_NOTE)
            .confidential(UPDATED_CONFIDENTIAL)
            .otherBrokerServices(UPDATED_OTHER_BROKER_SERVICES)
            .sanctionedStatus(UPDATED_SANCTIONED_STATUS)
            .considerations(UPDATED_CONSIDERATIONS)
            .description(UPDATED_DESCRIPTION)
            .waysOfCommunication(UPDATED_WAYS_OF_COMMUNICATION)
            .servicesAvailable(UPDATED_SERVICES_AVAILABLE)
            .customerAcceptancePolicy(UPDATED_CUSTOMER_ACCEPTANCE_POLICY);
        return additionalBrokerInformation;
    }

    @BeforeEach
    public void initTest() {
        additionalBrokerInformation = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedAdditionalBrokerInformation != null) {
            additionalBrokerInformationRepository.delete(insertedAdditionalBrokerInformation);
            insertedAdditionalBrokerInformation = null;
        }
    }

    @Test
    @Transactional
    void createAdditionalBrokerInformation() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AdditionalBrokerInformation
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            additionalBrokerInformation
        );
        var returnedAdditionalBrokerInformationDTO = om.readValue(
            restAdditionalBrokerInformationMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(additionalBrokerInformationDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AdditionalBrokerInformationDTO.class
        );

        // Validate the AdditionalBrokerInformation in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedAdditionalBrokerInformation = additionalBrokerInformationMapper.toEntity(returnedAdditionalBrokerInformationDTO);
        assertAdditionalBrokerInformationUpdatableFieldsEquals(
            returnedAdditionalBrokerInformation,
            getPersistedAdditionalBrokerInformation(returnedAdditionalBrokerInformation)
        );

        insertedAdditionalBrokerInformation = returnedAdditionalBrokerInformation;
    }

    @Test
    @Transactional
    void createAdditionalBrokerInformationWithExistingId() throws Exception {
        // Create the AdditionalBrokerInformation with an existing ID
        additionalBrokerInformation.setId(1L);
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            additionalBrokerInformation
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdditionalBrokerInformationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(additionalBrokerInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdditionalBrokerInformation in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAdditionalBrokerInformations() throws Exception {
        // Initialize the database
        insertedAdditionalBrokerInformation = additionalBrokerInformationRepository.saveAndFlush(additionalBrokerInformation);

        // Get all the additionalBrokerInformationList
        restAdditionalBrokerInformationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(additionalBrokerInformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateOfStartRelation").value(hasItem(DEFAULT_DATE_OF_START_RELATION)))
            .andExpect(jsonPath("$.[*].creditLimit").value(hasItem(DEFAULT_CREDIT_LIMIT)))
            .andExpect(jsonPath("$.[*].revokedDate").value(hasItem(DEFAULT_REVOKED_DATE)))
            .andExpect(jsonPath("$.[*].revokedNote").value(hasItem(DEFAULT_REVOKED_NOTE)))
            .andExpect(jsonPath("$.[*].confidential").value(hasItem(DEFAULT_CONFIDENTIAL)))
            .andExpect(jsonPath("$.[*].otherBrokerServices").value(hasItem(DEFAULT_OTHER_BROKER_SERVICES)))
            .andExpect(jsonPath("$.[*].sanctionedStatus").value(hasItem(DEFAULT_SANCTIONED_STATUS)))
            .andExpect(jsonPath("$.[*].considerations").value(hasItem(DEFAULT_CONSIDERATIONS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].waysOfCommunication").value(hasItem(DEFAULT_WAYS_OF_COMMUNICATION)))
            .andExpect(jsonPath("$.[*].servicesAvailable").value(hasItem(DEFAULT_SERVICES_AVAILABLE)))
            .andExpect(jsonPath("$.[*].customerAcceptancePolicy").value(hasItem(DEFAULT_CUSTOMER_ACCEPTANCE_POLICY)));
    }

    @Test
    @Transactional
    void getAdditionalBrokerInformation() throws Exception {
        // Initialize the database
        insertedAdditionalBrokerInformation = additionalBrokerInformationRepository.saveAndFlush(additionalBrokerInformation);

        // Get the additionalBrokerInformation
        restAdditionalBrokerInformationMockMvc
            .perform(get(ENTITY_API_URL_ID, additionalBrokerInformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(additionalBrokerInformation.getId().intValue()))
            .andExpect(jsonPath("$.dateOfStartRelation").value(DEFAULT_DATE_OF_START_RELATION))
            .andExpect(jsonPath("$.creditLimit").value(DEFAULT_CREDIT_LIMIT))
            .andExpect(jsonPath("$.revokedDate").value(DEFAULT_REVOKED_DATE))
            .andExpect(jsonPath("$.revokedNote").value(DEFAULT_REVOKED_NOTE))
            .andExpect(jsonPath("$.confidential").value(DEFAULT_CONFIDENTIAL))
            .andExpect(jsonPath("$.otherBrokerServices").value(DEFAULT_OTHER_BROKER_SERVICES))
            .andExpect(jsonPath("$.sanctionedStatus").value(DEFAULT_SANCTIONED_STATUS))
            .andExpect(jsonPath("$.considerations").value(DEFAULT_CONSIDERATIONS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.waysOfCommunication").value(DEFAULT_WAYS_OF_COMMUNICATION))
            .andExpect(jsonPath("$.servicesAvailable").value(DEFAULT_SERVICES_AVAILABLE))
            .andExpect(jsonPath("$.customerAcceptancePolicy").value(DEFAULT_CUSTOMER_ACCEPTANCE_POLICY));
    }

    @Test
    @Transactional
    void getNonExistingAdditionalBrokerInformation() throws Exception {
        // Get the additionalBrokerInformation
        restAdditionalBrokerInformationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAdditionalBrokerInformation() throws Exception {
        // Initialize the database
        insertedAdditionalBrokerInformation = additionalBrokerInformationRepository.saveAndFlush(additionalBrokerInformation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the additionalBrokerInformation
        AdditionalBrokerInformation updatedAdditionalBrokerInformation = additionalBrokerInformationRepository
            .findById(additionalBrokerInformation.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedAdditionalBrokerInformation are not directly saved in db
        em.detach(updatedAdditionalBrokerInformation);
        updatedAdditionalBrokerInformation
            .dateOfStartRelation(UPDATED_DATE_OF_START_RELATION)
            .creditLimit(UPDATED_CREDIT_LIMIT)
            .revokedDate(UPDATED_REVOKED_DATE)
            .revokedNote(UPDATED_REVOKED_NOTE)
            .confidential(UPDATED_CONFIDENTIAL)
            .otherBrokerServices(UPDATED_OTHER_BROKER_SERVICES)
            .sanctionedStatus(UPDATED_SANCTIONED_STATUS)
            .considerations(UPDATED_CONSIDERATIONS)
            .description(UPDATED_DESCRIPTION)
            .waysOfCommunication(UPDATED_WAYS_OF_COMMUNICATION)
            .servicesAvailable(UPDATED_SERVICES_AVAILABLE)
            .customerAcceptancePolicy(UPDATED_CUSTOMER_ACCEPTANCE_POLICY);
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            updatedAdditionalBrokerInformation
        );

        restAdditionalBrokerInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, additionalBrokerInformationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(additionalBrokerInformationDTO))
            )
            .andExpect(status().isOk());

        // Validate the AdditionalBrokerInformation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAdditionalBrokerInformationToMatchAllProperties(updatedAdditionalBrokerInformation);
    }

    @Test
    @Transactional
    void putNonExistingAdditionalBrokerInformation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        additionalBrokerInformation.setId(longCount.incrementAndGet());

        // Create the AdditionalBrokerInformation
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            additionalBrokerInformation
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdditionalBrokerInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, additionalBrokerInformationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(additionalBrokerInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdditionalBrokerInformation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAdditionalBrokerInformation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        additionalBrokerInformation.setId(longCount.incrementAndGet());

        // Create the AdditionalBrokerInformation
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            additionalBrokerInformation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdditionalBrokerInformationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(additionalBrokerInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdditionalBrokerInformation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAdditionalBrokerInformation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        additionalBrokerInformation.setId(longCount.incrementAndGet());

        // Create the AdditionalBrokerInformation
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            additionalBrokerInformation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdditionalBrokerInformationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(additionalBrokerInformationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdditionalBrokerInformation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAdditionalBrokerInformationWithPatch() throws Exception {
        // Initialize the database
        insertedAdditionalBrokerInformation = additionalBrokerInformationRepository.saveAndFlush(additionalBrokerInformation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the additionalBrokerInformation using partial update
        AdditionalBrokerInformation partialUpdatedAdditionalBrokerInformation = new AdditionalBrokerInformation();
        partialUpdatedAdditionalBrokerInformation.setId(additionalBrokerInformation.getId());

        partialUpdatedAdditionalBrokerInformation
            .dateOfStartRelation(UPDATED_DATE_OF_START_RELATION)
            .revokedNote(UPDATED_REVOKED_NOTE)
            .otherBrokerServices(UPDATED_OTHER_BROKER_SERVICES)
            .sanctionedStatus(UPDATED_SANCTIONED_STATUS)
            .considerations(UPDATED_CONSIDERATIONS)
            .description(UPDATED_DESCRIPTION)
            .servicesAvailable(UPDATED_SERVICES_AVAILABLE)
            .customerAcceptancePolicy(UPDATED_CUSTOMER_ACCEPTANCE_POLICY);

        restAdditionalBrokerInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdditionalBrokerInformation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAdditionalBrokerInformation))
            )
            .andExpect(status().isOk());

        // Validate the AdditionalBrokerInformation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAdditionalBrokerInformationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAdditionalBrokerInformation, additionalBrokerInformation),
            getPersistedAdditionalBrokerInformation(additionalBrokerInformation)
        );
    }

    @Test
    @Transactional
    void fullUpdateAdditionalBrokerInformationWithPatch() throws Exception {
        // Initialize the database
        insertedAdditionalBrokerInformation = additionalBrokerInformationRepository.saveAndFlush(additionalBrokerInformation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the additionalBrokerInformation using partial update
        AdditionalBrokerInformation partialUpdatedAdditionalBrokerInformation = new AdditionalBrokerInformation();
        partialUpdatedAdditionalBrokerInformation.setId(additionalBrokerInformation.getId());

        partialUpdatedAdditionalBrokerInformation
            .dateOfStartRelation(UPDATED_DATE_OF_START_RELATION)
            .creditLimit(UPDATED_CREDIT_LIMIT)
            .revokedDate(UPDATED_REVOKED_DATE)
            .revokedNote(UPDATED_REVOKED_NOTE)
            .confidential(UPDATED_CONFIDENTIAL)
            .otherBrokerServices(UPDATED_OTHER_BROKER_SERVICES)
            .sanctionedStatus(UPDATED_SANCTIONED_STATUS)
            .considerations(UPDATED_CONSIDERATIONS)
            .description(UPDATED_DESCRIPTION)
            .waysOfCommunication(UPDATED_WAYS_OF_COMMUNICATION)
            .servicesAvailable(UPDATED_SERVICES_AVAILABLE)
            .customerAcceptancePolicy(UPDATED_CUSTOMER_ACCEPTANCE_POLICY);

        restAdditionalBrokerInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAdditionalBrokerInformation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAdditionalBrokerInformation))
            )
            .andExpect(status().isOk());

        // Validate the AdditionalBrokerInformation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAdditionalBrokerInformationUpdatableFieldsEquals(
            partialUpdatedAdditionalBrokerInformation,
            getPersistedAdditionalBrokerInformation(partialUpdatedAdditionalBrokerInformation)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAdditionalBrokerInformation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        additionalBrokerInformation.setId(longCount.incrementAndGet());

        // Create the AdditionalBrokerInformation
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            additionalBrokerInformation
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdditionalBrokerInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, additionalBrokerInformationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(additionalBrokerInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdditionalBrokerInformation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAdditionalBrokerInformation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        additionalBrokerInformation.setId(longCount.incrementAndGet());

        // Create the AdditionalBrokerInformation
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            additionalBrokerInformation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdditionalBrokerInformationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(additionalBrokerInformationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AdditionalBrokerInformation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAdditionalBrokerInformation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        additionalBrokerInformation.setId(longCount.incrementAndGet());

        // Create the AdditionalBrokerInformation
        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = additionalBrokerInformationMapper.toDto(
            additionalBrokerInformation
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAdditionalBrokerInformationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(additionalBrokerInformationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AdditionalBrokerInformation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAdditionalBrokerInformation() throws Exception {
        // Initialize the database
        insertedAdditionalBrokerInformation = additionalBrokerInformationRepository.saveAndFlush(additionalBrokerInformation);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the additionalBrokerInformation
        restAdditionalBrokerInformationMockMvc
            .perform(delete(ENTITY_API_URL_ID, additionalBrokerInformation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return additionalBrokerInformationRepository.count();
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

    protected AdditionalBrokerInformation getPersistedAdditionalBrokerInformation(AdditionalBrokerInformation additionalBrokerInformation) {
        return additionalBrokerInformationRepository.findById(additionalBrokerInformation.getId()).orElseThrow();
    }

    protected void assertPersistedAdditionalBrokerInformationToMatchAllProperties(
        AdditionalBrokerInformation expectedAdditionalBrokerInformation
    ) {
        assertAdditionalBrokerInformationAllPropertiesEquals(
            expectedAdditionalBrokerInformation,
            getPersistedAdditionalBrokerInformation(expectedAdditionalBrokerInformation)
        );
    }

    protected void assertPersistedAdditionalBrokerInformationToMatchUpdatableProperties(
        AdditionalBrokerInformation expectedAdditionalBrokerInformation
    ) {
        assertAdditionalBrokerInformationAllUpdatablePropertiesEquals(
            expectedAdditionalBrokerInformation,
            getPersistedAdditionalBrokerInformation(expectedAdditionalBrokerInformation)
        );
    }
}
