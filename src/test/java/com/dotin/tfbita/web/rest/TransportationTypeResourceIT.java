package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.TransportationTypeAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.TransportationType;
import com.dotin.tfbita.repository.TransportationTypeRepository;
import com.dotin.tfbita.service.dto.TransportationTypeDTO;
import com.dotin.tfbita.service.mapper.TransportationTypeMapper;
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
 * Integration tests for the {@link TransportationTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TransportationTypeResourceIT {

    private static final String DEFAULT_LATIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LATIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFICATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFICATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/transportation-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TransportationTypeRepository transportationTypeRepository;

    @Autowired
    private TransportationTypeMapper transportationTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransportationTypeMockMvc;

    private TransportationType transportationType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransportationType createEntity(EntityManager em) {
        TransportationType transportationType = new TransportationType()
            .latinName(DEFAULT_LATIN_NAME)
            .modificationDate(DEFAULT_MODIFICATION_DATE)
            .name(DEFAULT_NAME);
        return transportationType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransportationType createUpdatedEntity(EntityManager em) {
        TransportationType transportationType = new TransportationType()
            .latinName(UPDATED_LATIN_NAME)
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .name(UPDATED_NAME);
        return transportationType;
    }

    @BeforeEach
    public void initTest() {
        transportationType = createEntity(em);
    }

    @Test
    @Transactional
    void createTransportationType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TransportationType
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(transportationType);
        var returnedTransportationTypeDTO = om.readValue(
            restTransportationTypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transportationTypeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TransportationTypeDTO.class
        );

        // Validate the TransportationType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTransportationType = transportationTypeMapper.toEntity(returnedTransportationTypeDTO);
        assertTransportationTypeUpdatableFieldsEquals(
            returnedTransportationType,
            getPersistedTransportationType(returnedTransportationType)
        );
    }

    @Test
    @Transactional
    void createTransportationTypeWithExistingId() throws Exception {
        // Create the TransportationType with an existing ID
        transportationType.setId(1L);
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(transportationType);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransportationTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transportationTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportationType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTransportationTypes() throws Exception {
        // Initialize the database
        transportationTypeRepository.saveAndFlush(transportationType);

        // Get all the transportationTypeList
        restTransportationTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transportationType.getId().intValue())))
            .andExpect(jsonPath("$.[*].latinName").value(hasItem(DEFAULT_LATIN_NAME)))
            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getTransportationType() throws Exception {
        // Initialize the database
        transportationTypeRepository.saveAndFlush(transportationType);

        // Get the transportationType
        restTransportationTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, transportationType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transportationType.getId().intValue()))
            .andExpect(jsonPath("$.latinName").value(DEFAULT_LATIN_NAME))
            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingTransportationType() throws Exception {
        // Get the transportationType
        restTransportationTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTransportationType() throws Exception {
        // Initialize the database
        transportationTypeRepository.saveAndFlush(transportationType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transportationType
        TransportationType updatedTransportationType = transportationTypeRepository.findById(transportationType.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTransportationType are not directly saved in db
        em.detach(updatedTransportationType);
        updatedTransportationType.latinName(UPDATED_LATIN_NAME).modificationDate(UPDATED_MODIFICATION_DATE).name(UPDATED_NAME);
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(updatedTransportationType);

        restTransportationTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transportationTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transportationTypeDTO))
            )
            .andExpect(status().isOk());

        // Validate the TransportationType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTransportationTypeToMatchAllProperties(updatedTransportationType);
    }

    @Test
    @Transactional
    void putNonExistingTransportationType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transportationType.setId(longCount.incrementAndGet());

        // Create the TransportationType
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(transportationType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportationTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transportationTypeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transportationTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransportationType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTransportationType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transportationType.setId(longCount.incrementAndGet());

        // Create the TransportationType
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(transportationType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransportationTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transportationTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransportationType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTransportationType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transportationType.setId(longCount.incrementAndGet());

        // Create the TransportationType
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(transportationType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransportationTypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transportationTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TransportationType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTransportationTypeWithPatch() throws Exception {
        // Initialize the database
        transportationTypeRepository.saveAndFlush(transportationType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transportationType using partial update
        TransportationType partialUpdatedTransportationType = new TransportationType();
        partialUpdatedTransportationType.setId(transportationType.getId());

        partialUpdatedTransportationType.latinName(UPDATED_LATIN_NAME).name(UPDATED_NAME);

        restTransportationTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransportationType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTransportationType))
            )
            .andExpect(status().isOk());

        // Validate the TransportationType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTransportationTypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTransportationType, transportationType),
            getPersistedTransportationType(transportationType)
        );
    }

    @Test
    @Transactional
    void fullUpdateTransportationTypeWithPatch() throws Exception {
        // Initialize the database
        transportationTypeRepository.saveAndFlush(transportationType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transportationType using partial update
        TransportationType partialUpdatedTransportationType = new TransportationType();
        partialUpdatedTransportationType.setId(transportationType.getId());

        partialUpdatedTransportationType.latinName(UPDATED_LATIN_NAME).modificationDate(UPDATED_MODIFICATION_DATE).name(UPDATED_NAME);

        restTransportationTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransportationType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTransportationType))
            )
            .andExpect(status().isOk());

        // Validate the TransportationType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTransportationTypeUpdatableFieldsEquals(
            partialUpdatedTransportationType,
            getPersistedTransportationType(partialUpdatedTransportationType)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTransportationType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transportationType.setId(longCount.incrementAndGet());

        // Create the TransportationType
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(transportationType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportationTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, transportationTypeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(transportationTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransportationType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTransportationType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transportationType.setId(longCount.incrementAndGet());

        // Create the TransportationType
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(transportationType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransportationTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(transportationTypeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransportationType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTransportationType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transportationType.setId(longCount.incrementAndGet());

        // Create the TransportationType
        TransportationTypeDTO transportationTypeDTO = transportationTypeMapper.toDto(transportationType);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransportationTypeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(transportationTypeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TransportationType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTransportationType() throws Exception {
        // Initialize the database
        transportationTypeRepository.saveAndFlush(transportationType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the transportationType
        restTransportationTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, transportationType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return transportationTypeRepository.count();
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

    protected TransportationType getPersistedTransportationType(TransportationType transportationType) {
        return transportationTypeRepository.findById(transportationType.getId()).orElseThrow();
    }

    protected void assertPersistedTransportationTypeToMatchAllProperties(TransportationType expectedTransportationType) {
        assertTransportationTypeAllPropertiesEquals(expectedTransportationType, getPersistedTransportationType(expectedTransportationType));
    }

    protected void assertPersistedTransportationTypeToMatchUpdatableProperties(TransportationType expectedTransportationType) {
        assertTransportationTypeAllUpdatablePropertiesEquals(
            expectedTransportationType,
            getPersistedTransportationType(expectedTransportationType)
        );
    }
}
