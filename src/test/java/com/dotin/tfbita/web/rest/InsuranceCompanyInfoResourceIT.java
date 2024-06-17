package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.InsuranceCompanyInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.InsuranceCompanyInfo;
import com.dotin.tfbita.repository.InsuranceCompanyInfoRepository;
import com.dotin.tfbita.service.dto.InsuranceCompanyInfoDTO;
import com.dotin.tfbita.service.mapper.InsuranceCompanyInfoMapper;
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
 * Integration tests for the {@link InsuranceCompanyInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InsuranceCompanyInfoResourceIT {

    private static final String DEFAULT_MODIFICATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_MODIFICATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/insurance-company-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InsuranceCompanyInfoRepository insuranceCompanyInfoRepository;

    @Autowired
    private InsuranceCompanyInfoMapper insuranceCompanyInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInsuranceCompanyInfoMockMvc;

    private InsuranceCompanyInfo insuranceCompanyInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceCompanyInfo createEntity(EntityManager em) {
        InsuranceCompanyInfo insuranceCompanyInfo = new InsuranceCompanyInfo()
            .modificationDate(DEFAULT_MODIFICATION_DATE)
            .name(DEFAULT_NAME);
        return insuranceCompanyInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InsuranceCompanyInfo createUpdatedEntity(EntityManager em) {
        InsuranceCompanyInfo insuranceCompanyInfo = new InsuranceCompanyInfo()
            .modificationDate(UPDATED_MODIFICATION_DATE)
            .name(UPDATED_NAME);
        return insuranceCompanyInfo;
    }

    @BeforeEach
    public void initTest() {
        insuranceCompanyInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createInsuranceCompanyInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the InsuranceCompanyInfo
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);
        var returnedInsuranceCompanyInfoDTO = om.readValue(
            restInsuranceCompanyInfoMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(insuranceCompanyInfoDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            InsuranceCompanyInfoDTO.class
        );

        // Validate the InsuranceCompanyInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedInsuranceCompanyInfo = insuranceCompanyInfoMapper.toEntity(returnedInsuranceCompanyInfoDTO);
        assertInsuranceCompanyInfoUpdatableFieldsEquals(
            returnedInsuranceCompanyInfo,
            getPersistedInsuranceCompanyInfo(returnedInsuranceCompanyInfo)
        );
    }

    @Test
    @Transactional
    void createInsuranceCompanyInfoWithExistingId() throws Exception {
        // Create the InsuranceCompanyInfo with an existing ID
        insuranceCompanyInfo.setId(1L);
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceCompanyInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(insuranceCompanyInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InsuranceCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInsuranceCompanyInfos() throws Exception {
        // Initialize the database
        insuranceCompanyInfoRepository.saveAndFlush(insuranceCompanyInfo);

        // Get all the insuranceCompanyInfoList
        restInsuranceCompanyInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insuranceCompanyInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getInsuranceCompanyInfo() throws Exception {
        // Initialize the database
        insuranceCompanyInfoRepository.saveAndFlush(insuranceCompanyInfo);

        // Get the insuranceCompanyInfo
        restInsuranceCompanyInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, insuranceCompanyInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insuranceCompanyInfo.getId().intValue()))
            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingInsuranceCompanyInfo() throws Exception {
        // Get the insuranceCompanyInfo
        restInsuranceCompanyInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInsuranceCompanyInfo() throws Exception {
        // Initialize the database
        insuranceCompanyInfoRepository.saveAndFlush(insuranceCompanyInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the insuranceCompanyInfo
        InsuranceCompanyInfo updatedInsuranceCompanyInfo = insuranceCompanyInfoRepository
            .findById(insuranceCompanyInfo.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedInsuranceCompanyInfo are not directly saved in db
        em.detach(updatedInsuranceCompanyInfo);
        updatedInsuranceCompanyInfo.modificationDate(UPDATED_MODIFICATION_DATE).name(UPDATED_NAME);
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(updatedInsuranceCompanyInfo);

        restInsuranceCompanyInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceCompanyInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(insuranceCompanyInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInsuranceCompanyInfoToMatchAllProperties(updatedInsuranceCompanyInfo);
    }

    @Test
    @Transactional
    void putNonExistingInsuranceCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insuranceCompanyInfo.setId(longCount.incrementAndGet());

        // Create the InsuranceCompanyInfo
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceCompanyInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, insuranceCompanyInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(insuranceCompanyInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInsuranceCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insuranceCompanyInfo.setId(longCount.incrementAndGet());

        // Create the InsuranceCompanyInfo
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceCompanyInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(insuranceCompanyInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInsuranceCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insuranceCompanyInfo.setId(longCount.incrementAndGet());

        // Create the InsuranceCompanyInfo
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceCompanyInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(insuranceCompanyInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInsuranceCompanyInfoWithPatch() throws Exception {
        // Initialize the database
        insuranceCompanyInfoRepository.saveAndFlush(insuranceCompanyInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the insuranceCompanyInfo using partial update
        InsuranceCompanyInfo partialUpdatedInsuranceCompanyInfo = new InsuranceCompanyInfo();
        partialUpdatedInsuranceCompanyInfo.setId(insuranceCompanyInfo.getId());

        partialUpdatedInsuranceCompanyInfo.modificationDate(UPDATED_MODIFICATION_DATE).name(UPDATED_NAME);

        restInsuranceCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceCompanyInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInsuranceCompanyInfo))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceCompanyInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInsuranceCompanyInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedInsuranceCompanyInfo, insuranceCompanyInfo),
            getPersistedInsuranceCompanyInfo(insuranceCompanyInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateInsuranceCompanyInfoWithPatch() throws Exception {
        // Initialize the database
        insuranceCompanyInfoRepository.saveAndFlush(insuranceCompanyInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the insuranceCompanyInfo using partial update
        InsuranceCompanyInfo partialUpdatedInsuranceCompanyInfo = new InsuranceCompanyInfo();
        partialUpdatedInsuranceCompanyInfo.setId(insuranceCompanyInfo.getId());

        partialUpdatedInsuranceCompanyInfo.modificationDate(UPDATED_MODIFICATION_DATE).name(UPDATED_NAME);

        restInsuranceCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInsuranceCompanyInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInsuranceCompanyInfo))
            )
            .andExpect(status().isOk());

        // Validate the InsuranceCompanyInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInsuranceCompanyInfoUpdatableFieldsEquals(
            partialUpdatedInsuranceCompanyInfo,
            getPersistedInsuranceCompanyInfo(partialUpdatedInsuranceCompanyInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingInsuranceCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insuranceCompanyInfo.setId(longCount.incrementAndGet());

        // Create the InsuranceCompanyInfo
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, insuranceCompanyInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(insuranceCompanyInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInsuranceCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insuranceCompanyInfo.setId(longCount.incrementAndGet());

        // Create the InsuranceCompanyInfo
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(insuranceCompanyInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the InsuranceCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInsuranceCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        insuranceCompanyInfo.setId(longCount.incrementAndGet());

        // Create the InsuranceCompanyInfo
        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInsuranceCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(insuranceCompanyInfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InsuranceCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInsuranceCompanyInfo() throws Exception {
        // Initialize the database
        insuranceCompanyInfoRepository.saveAndFlush(insuranceCompanyInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the insuranceCompanyInfo
        restInsuranceCompanyInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, insuranceCompanyInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return insuranceCompanyInfoRepository.count();
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

    protected InsuranceCompanyInfo getPersistedInsuranceCompanyInfo(InsuranceCompanyInfo insuranceCompanyInfo) {
        return insuranceCompanyInfoRepository.findById(insuranceCompanyInfo.getId()).orElseThrow();
    }

    protected void assertPersistedInsuranceCompanyInfoToMatchAllProperties(InsuranceCompanyInfo expectedInsuranceCompanyInfo) {
        assertInsuranceCompanyInfoAllPropertiesEquals(
            expectedInsuranceCompanyInfo,
            getPersistedInsuranceCompanyInfo(expectedInsuranceCompanyInfo)
        );
    }

    protected void assertPersistedInsuranceCompanyInfoToMatchUpdatableProperties(InsuranceCompanyInfo expectedInsuranceCompanyInfo) {
        assertInsuranceCompanyInfoAllUpdatablePropertiesEquals(
            expectedInsuranceCompanyInfo,
            getPersistedInsuranceCompanyInfo(expectedInsuranceCompanyInfo)
        );
    }
}
