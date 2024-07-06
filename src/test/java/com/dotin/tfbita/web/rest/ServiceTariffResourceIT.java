package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.ServiceTariffAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.ServiceTariff;
import com.dotin.tfbita.repository.ServiceTariffRepository;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import com.dotin.tfbita.service.mapper.ServiceTariffMapper;
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
 * Integration tests for the {@link ServiceTariffResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServiceTariffResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/service-tariffs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ServiceTariffRepository serviceTariffRepository;

    @Autowired
    private ServiceTariffMapper serviceTariffMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceTariffMockMvc;

    private ServiceTariff serviceTariff;

    private ServiceTariff insertedServiceTariff;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceTariff createEntity(EntityManager em) {
        ServiceTariff serviceTariff = new ServiceTariff().code(DEFAULT_CODE).title(DEFAULT_TITLE);
        return serviceTariff;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceTariff createUpdatedEntity(EntityManager em) {
        ServiceTariff serviceTariff = new ServiceTariff().code(UPDATED_CODE).title(UPDATED_TITLE);
        return serviceTariff;
    }

    @BeforeEach
    public void initTest() {
        serviceTariff = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedServiceTariff != null) {
            serviceTariffRepository.delete(insertedServiceTariff);
            insertedServiceTariff = null;
        }
    }

    @Test
    @Transactional
    void createServiceTariff() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ServiceTariff
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(serviceTariff);
        var returnedServiceTariffDTO = om.readValue(
            restServiceTariffMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(serviceTariffDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ServiceTariffDTO.class
        );

        // Validate the ServiceTariff in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedServiceTariff = serviceTariffMapper.toEntity(returnedServiceTariffDTO);
        assertServiceTariffUpdatableFieldsEquals(returnedServiceTariff, getPersistedServiceTariff(returnedServiceTariff));

        insertedServiceTariff = returnedServiceTariff;
    }

    @Test
    @Transactional
    void createServiceTariffWithExistingId() throws Exception {
        // Create the ServiceTariff with an existing ID
        serviceTariff.setId(1L);
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(serviceTariff);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceTariffMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(serviceTariffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceTariff in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllServiceTariffs() throws Exception {
        // Initialize the database
        insertedServiceTariff = serviceTariffRepository.saveAndFlush(serviceTariff);

        // Get all the serviceTariffList
        restServiceTariffMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceTariff.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));
    }

    @Test
    @Transactional
    void getServiceTariff() throws Exception {
        // Initialize the database
        insertedServiceTariff = serviceTariffRepository.saveAndFlush(serviceTariff);

        // Get the serviceTariff
        restServiceTariffMockMvc
            .perform(get(ENTITY_API_URL_ID, serviceTariff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceTariff.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE));
    }

    @Test
    @Transactional
    void getNonExistingServiceTariff() throws Exception {
        // Get the serviceTariff
        restServiceTariffMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingServiceTariff() throws Exception {
        // Initialize the database
        insertedServiceTariff = serviceTariffRepository.saveAndFlush(serviceTariff);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the serviceTariff
        ServiceTariff updatedServiceTariff = serviceTariffRepository.findById(serviceTariff.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedServiceTariff are not directly saved in db
        em.detach(updatedServiceTariff);
        updatedServiceTariff.code(UPDATED_CODE).title(UPDATED_TITLE);
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(updatedServiceTariff);

        restServiceTariffMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceTariffDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(serviceTariffDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceTariff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedServiceTariffToMatchAllProperties(updatedServiceTariff);
    }

    @Test
    @Transactional
    void putNonExistingServiceTariff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceTariff.setId(longCount.incrementAndGet());

        // Create the ServiceTariff
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(serviceTariff);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceTariffMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceTariffDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(serviceTariffDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceTariff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServiceTariff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceTariff.setId(longCount.incrementAndGet());

        // Create the ServiceTariff
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(serviceTariff);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceTariffMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(serviceTariffDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceTariff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServiceTariff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceTariff.setId(longCount.incrementAndGet());

        // Create the ServiceTariff
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(serviceTariff);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceTariffMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(serviceTariffDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceTariff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServiceTariffWithPatch() throws Exception {
        // Initialize the database
        insertedServiceTariff = serviceTariffRepository.saveAndFlush(serviceTariff);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the serviceTariff using partial update
        ServiceTariff partialUpdatedServiceTariff = new ServiceTariff();
        partialUpdatedServiceTariff.setId(serviceTariff.getId());

        partialUpdatedServiceTariff.code(UPDATED_CODE).title(UPDATED_TITLE);

        restServiceTariffMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceTariff.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedServiceTariff))
            )
            .andExpect(status().isOk());

        // Validate the ServiceTariff in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertServiceTariffUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedServiceTariff, serviceTariff),
            getPersistedServiceTariff(serviceTariff)
        );
    }

    @Test
    @Transactional
    void fullUpdateServiceTariffWithPatch() throws Exception {
        // Initialize the database
        insertedServiceTariff = serviceTariffRepository.saveAndFlush(serviceTariff);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the serviceTariff using partial update
        ServiceTariff partialUpdatedServiceTariff = new ServiceTariff();
        partialUpdatedServiceTariff.setId(serviceTariff.getId());

        partialUpdatedServiceTariff.code(UPDATED_CODE).title(UPDATED_TITLE);

        restServiceTariffMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceTariff.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedServiceTariff))
            )
            .andExpect(status().isOk());

        // Validate the ServiceTariff in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertServiceTariffUpdatableFieldsEquals(partialUpdatedServiceTariff, getPersistedServiceTariff(partialUpdatedServiceTariff));
    }

    @Test
    @Transactional
    void patchNonExistingServiceTariff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceTariff.setId(longCount.incrementAndGet());

        // Create the ServiceTariff
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(serviceTariff);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceTariffMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, serviceTariffDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(serviceTariffDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceTariff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServiceTariff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceTariff.setId(longCount.incrementAndGet());

        // Create the ServiceTariff
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(serviceTariff);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceTariffMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(serviceTariffDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceTariff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServiceTariff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        serviceTariff.setId(longCount.incrementAndGet());

        // Create the ServiceTariff
        ServiceTariffDTO serviceTariffDTO = serviceTariffMapper.toDto(serviceTariff);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceTariffMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(serviceTariffDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceTariff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServiceTariff() throws Exception {
        // Initialize the database
        insertedServiceTariff = serviceTariffRepository.saveAndFlush(serviceTariff);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the serviceTariff
        restServiceTariffMockMvc
            .perform(delete(ENTITY_API_URL_ID, serviceTariff.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return serviceTariffRepository.count();
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

    protected ServiceTariff getPersistedServiceTariff(ServiceTariff serviceTariff) {
        return serviceTariffRepository.findById(serviceTariff.getId()).orElseThrow();
    }

    protected void assertPersistedServiceTariffToMatchAllProperties(ServiceTariff expectedServiceTariff) {
        assertServiceTariffAllPropertiesEquals(expectedServiceTariff, getPersistedServiceTariff(expectedServiceTariff));
    }

    protected void assertPersistedServiceTariffToMatchUpdatableProperties(ServiceTariff expectedServiceTariff) {
        assertServiceTariffAllUpdatablePropertiesEquals(expectedServiceTariff, getPersistedServiceTariff(expectedServiceTariff));
    }
}
