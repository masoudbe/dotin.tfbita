package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.LicenceInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.LicenceInfo;
import com.dotin.tfbita.repository.LicenceInfoRepository;
import com.dotin.tfbita.service.dto.LicenceInfoDTO;
import com.dotin.tfbita.service.mapper.LicenceInfoMapper;
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
 * Integration tests for the {@link LicenceInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LicenceInfoResourceIT {

    private static final String DEFAULT_ORGANIZATION_LICENCE = "AAAAAAAAAA";
    private static final String UPDATED_ORGANIZATION_LICENCE = "BBBBBBBBBB";

    private static final String DEFAULT_LICENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LICENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LICENCE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_LICENCE_DATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HAVING_PRODUCT = false;
    private static final Boolean UPDATED_HAVING_PRODUCT = true;

    private static final Boolean DEFAULT_HAVING_SERVICE = false;
    private static final Boolean UPDATED_HAVING_SERVICE = true;

    private static final String DEFAULT_CREDIT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_DATE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/licence-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LicenceInfoRepository licenceInfoRepository;

    @Autowired
    private LicenceInfoMapper licenceInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLicenceInfoMockMvc;

    private LicenceInfo licenceInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LicenceInfo createEntity(EntityManager em) {
        LicenceInfo licenceInfo = new LicenceInfo()
            .organizationLicence(DEFAULT_ORGANIZATION_LICENCE)
            .licenceNumber(DEFAULT_LICENCE_NUMBER)
            .licenceDate(DEFAULT_LICENCE_DATE)
            .havingProduct(DEFAULT_HAVING_PRODUCT)
            .havingService(DEFAULT_HAVING_SERVICE)
            .creditDate(DEFAULT_CREDIT_DATE);
        return licenceInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LicenceInfo createUpdatedEntity(EntityManager em) {
        LicenceInfo licenceInfo = new LicenceInfo()
            .organizationLicence(UPDATED_ORGANIZATION_LICENCE)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .licenceDate(UPDATED_LICENCE_DATE)
            .havingProduct(UPDATED_HAVING_PRODUCT)
            .havingService(UPDATED_HAVING_SERVICE)
            .creditDate(UPDATED_CREDIT_DATE);
        return licenceInfo;
    }

    @BeforeEach
    public void initTest() {
        licenceInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createLicenceInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LicenceInfo
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(licenceInfo);
        var returnedLicenceInfoDTO = om.readValue(
            restLicenceInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(licenceInfoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LicenceInfoDTO.class
        );

        // Validate the LicenceInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLicenceInfo = licenceInfoMapper.toEntity(returnedLicenceInfoDTO);
        assertLicenceInfoUpdatableFieldsEquals(returnedLicenceInfo, getPersistedLicenceInfo(returnedLicenceInfo));
    }

    @Test
    @Transactional
    void createLicenceInfoWithExistingId() throws Exception {
        // Create the LicenceInfo with an existing ID
        licenceInfo.setId(1L);
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(licenceInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLicenceInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(licenceInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LicenceInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLicenceInfos() throws Exception {
        // Initialize the database
        licenceInfoRepository.saveAndFlush(licenceInfo);

        // Get all the licenceInfoList
        restLicenceInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(licenceInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].organizationLicence").value(hasItem(DEFAULT_ORGANIZATION_LICENCE)))
            .andExpect(jsonPath("$.[*].licenceNumber").value(hasItem(DEFAULT_LICENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].licenceDate").value(hasItem(DEFAULT_LICENCE_DATE)))
            .andExpect(jsonPath("$.[*].havingProduct").value(hasItem(DEFAULT_HAVING_PRODUCT.booleanValue())))
            .andExpect(jsonPath("$.[*].havingService").value(hasItem(DEFAULT_HAVING_SERVICE.booleanValue())))
            .andExpect(jsonPath("$.[*].creditDate").value(hasItem(DEFAULT_CREDIT_DATE)));
    }

    @Test
    @Transactional
    void getLicenceInfo() throws Exception {
        // Initialize the database
        licenceInfoRepository.saveAndFlush(licenceInfo);

        // Get the licenceInfo
        restLicenceInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, licenceInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(licenceInfo.getId().intValue()))
            .andExpect(jsonPath("$.organizationLicence").value(DEFAULT_ORGANIZATION_LICENCE))
            .andExpect(jsonPath("$.licenceNumber").value(DEFAULT_LICENCE_NUMBER))
            .andExpect(jsonPath("$.licenceDate").value(DEFAULT_LICENCE_DATE))
            .andExpect(jsonPath("$.havingProduct").value(DEFAULT_HAVING_PRODUCT.booleanValue()))
            .andExpect(jsonPath("$.havingService").value(DEFAULT_HAVING_SERVICE.booleanValue()))
            .andExpect(jsonPath("$.creditDate").value(DEFAULT_CREDIT_DATE));
    }

    @Test
    @Transactional
    void getNonExistingLicenceInfo() throws Exception {
        // Get the licenceInfo
        restLicenceInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLicenceInfo() throws Exception {
        // Initialize the database
        licenceInfoRepository.saveAndFlush(licenceInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the licenceInfo
        LicenceInfo updatedLicenceInfo = licenceInfoRepository.findById(licenceInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLicenceInfo are not directly saved in db
        em.detach(updatedLicenceInfo);
        updatedLicenceInfo
            .organizationLicence(UPDATED_ORGANIZATION_LICENCE)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .licenceDate(UPDATED_LICENCE_DATE)
            .havingProduct(UPDATED_HAVING_PRODUCT)
            .havingService(UPDATED_HAVING_SERVICE)
            .creditDate(UPDATED_CREDIT_DATE);
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(updatedLicenceInfo);

        restLicenceInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, licenceInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(licenceInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the LicenceInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLicenceInfoToMatchAllProperties(updatedLicenceInfo);
    }

    @Test
    @Transactional
    void putNonExistingLicenceInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        licenceInfo.setId(longCount.incrementAndGet());

        // Create the LicenceInfo
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(licenceInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLicenceInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, licenceInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(licenceInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LicenceInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLicenceInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        licenceInfo.setId(longCount.incrementAndGet());

        // Create the LicenceInfo
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(licenceInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLicenceInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(licenceInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LicenceInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLicenceInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        licenceInfo.setId(longCount.incrementAndGet());

        // Create the LicenceInfo
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(licenceInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLicenceInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(licenceInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LicenceInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLicenceInfoWithPatch() throws Exception {
        // Initialize the database
        licenceInfoRepository.saveAndFlush(licenceInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the licenceInfo using partial update
        LicenceInfo partialUpdatedLicenceInfo = new LicenceInfo();
        partialUpdatedLicenceInfo.setId(licenceInfo.getId());

        partialUpdatedLicenceInfo
            .organizationLicence(UPDATED_ORGANIZATION_LICENCE)
            .havingProduct(UPDATED_HAVING_PRODUCT)
            .havingService(UPDATED_HAVING_SERVICE)
            .creditDate(UPDATED_CREDIT_DATE);

        restLicenceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLicenceInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLicenceInfo))
            )
            .andExpect(status().isOk());

        // Validate the LicenceInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLicenceInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLicenceInfo, licenceInfo),
            getPersistedLicenceInfo(licenceInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateLicenceInfoWithPatch() throws Exception {
        // Initialize the database
        licenceInfoRepository.saveAndFlush(licenceInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the licenceInfo using partial update
        LicenceInfo partialUpdatedLicenceInfo = new LicenceInfo();
        partialUpdatedLicenceInfo.setId(licenceInfo.getId());

        partialUpdatedLicenceInfo
            .organizationLicence(UPDATED_ORGANIZATION_LICENCE)
            .licenceNumber(UPDATED_LICENCE_NUMBER)
            .licenceDate(UPDATED_LICENCE_DATE)
            .havingProduct(UPDATED_HAVING_PRODUCT)
            .havingService(UPDATED_HAVING_SERVICE)
            .creditDate(UPDATED_CREDIT_DATE);

        restLicenceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLicenceInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLicenceInfo))
            )
            .andExpect(status().isOk());

        // Validate the LicenceInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLicenceInfoUpdatableFieldsEquals(partialUpdatedLicenceInfo, getPersistedLicenceInfo(partialUpdatedLicenceInfo));
    }

    @Test
    @Transactional
    void patchNonExistingLicenceInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        licenceInfo.setId(longCount.incrementAndGet());

        // Create the LicenceInfo
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(licenceInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLicenceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, licenceInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(licenceInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LicenceInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLicenceInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        licenceInfo.setId(longCount.incrementAndGet());

        // Create the LicenceInfo
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(licenceInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLicenceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(licenceInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LicenceInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLicenceInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        licenceInfo.setId(longCount.incrementAndGet());

        // Create the LicenceInfo
        LicenceInfoDTO licenceInfoDTO = licenceInfoMapper.toDto(licenceInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLicenceInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(licenceInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LicenceInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLicenceInfo() throws Exception {
        // Initialize the database
        licenceInfoRepository.saveAndFlush(licenceInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the licenceInfo
        restLicenceInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, licenceInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return licenceInfoRepository.count();
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

    protected LicenceInfo getPersistedLicenceInfo(LicenceInfo licenceInfo) {
        return licenceInfoRepository.findById(licenceInfo.getId()).orElseThrow();
    }

    protected void assertPersistedLicenceInfoToMatchAllProperties(LicenceInfo expectedLicenceInfo) {
        assertLicenceInfoAllPropertiesEquals(expectedLicenceInfo, getPersistedLicenceInfo(expectedLicenceInfo));
    }

    protected void assertPersistedLicenceInfoToMatchUpdatableProperties(LicenceInfo expectedLicenceInfo) {
        assertLicenceInfoAllUpdatablePropertiesEquals(expectedLicenceInfo, getPersistedLicenceInfo(expectedLicenceInfo));
    }
}
