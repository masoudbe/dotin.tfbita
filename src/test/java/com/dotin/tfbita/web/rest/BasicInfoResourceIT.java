package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.BasicInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.BasicInfo;
import com.dotin.tfbita.repository.BasicInfoRepository;
import com.dotin.tfbita.service.dto.BasicInfoDTO;
import com.dotin.tfbita.service.mapper.BasicInfoMapper;
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
 * Integration tests for the {@link BasicInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BasicInfoResourceIT {

    private static final String DEFAULT_APPLY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_APPLY_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISABLED = false;
    private static final Boolean UPDATED_DISABLED = true;

    private static final String ENTITY_API_URL = "/api/basic-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BasicInfoRepository basicInfoRepository;

    @Autowired
    private BasicInfoMapper basicInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBasicInfoMockMvc;

    private BasicInfo basicInfo;

    private BasicInfo insertedBasicInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BasicInfo createEntity(EntityManager em) {
        BasicInfo basicInfo = new BasicInfo().applyDate(DEFAULT_APPLY_DATE).code(DEFAULT_CODE).disabled(DEFAULT_DISABLED);
        return basicInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BasicInfo createUpdatedEntity(EntityManager em) {
        BasicInfo basicInfo = new BasicInfo().applyDate(UPDATED_APPLY_DATE).code(UPDATED_CODE).disabled(UPDATED_DISABLED);
        return basicInfo;
    }

    @BeforeEach
    public void initTest() {
        basicInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedBasicInfo != null) {
            basicInfoRepository.delete(insertedBasicInfo);
            insertedBasicInfo = null;
        }
    }

    @Test
    @Transactional
    void createBasicInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the BasicInfo
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(basicInfo);
        var returnedBasicInfoDTO = om.readValue(
            restBasicInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(basicInfoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            BasicInfoDTO.class
        );

        // Validate the BasicInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedBasicInfo = basicInfoMapper.toEntity(returnedBasicInfoDTO);
        assertBasicInfoUpdatableFieldsEquals(returnedBasicInfo, getPersistedBasicInfo(returnedBasicInfo));

        insertedBasicInfo = returnedBasicInfo;
    }

    @Test
    @Transactional
    void createBasicInfoWithExistingId() throws Exception {
        // Create the BasicInfo with an existing ID
        basicInfo.setId(1L);
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(basicInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBasicInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(basicInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BasicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBasicInfos() throws Exception {
        // Initialize the database
        insertedBasicInfo = basicInfoRepository.saveAndFlush(basicInfo);

        // Get all the basicInfoList
        restBasicInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(basicInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].applyDate").value(hasItem(DEFAULT_APPLY_DATE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].disabled").value(hasItem(DEFAULT_DISABLED.booleanValue())));
    }

    @Test
    @Transactional
    void getBasicInfo() throws Exception {
        // Initialize the database
        insertedBasicInfo = basicInfoRepository.saveAndFlush(basicInfo);

        // Get the basicInfo
        restBasicInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, basicInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(basicInfo.getId().intValue()))
            .andExpect(jsonPath("$.applyDate").value(DEFAULT_APPLY_DATE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.disabled").value(DEFAULT_DISABLED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingBasicInfo() throws Exception {
        // Get the basicInfo
        restBasicInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBasicInfo() throws Exception {
        // Initialize the database
        insertedBasicInfo = basicInfoRepository.saveAndFlush(basicInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the basicInfo
        BasicInfo updatedBasicInfo = basicInfoRepository.findById(basicInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBasicInfo are not directly saved in db
        em.detach(updatedBasicInfo);
        updatedBasicInfo.applyDate(UPDATED_APPLY_DATE).code(UPDATED_CODE).disabled(UPDATED_DISABLED);
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(updatedBasicInfo);

        restBasicInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, basicInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(basicInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the BasicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBasicInfoToMatchAllProperties(updatedBasicInfo);
    }

    @Test
    @Transactional
    void putNonExistingBasicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        basicInfo.setId(longCount.incrementAndGet());

        // Create the BasicInfo
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(basicInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBasicInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, basicInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(basicInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BasicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBasicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        basicInfo.setId(longCount.incrementAndGet());

        // Create the BasicInfo
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(basicInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBasicInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(basicInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BasicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBasicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        basicInfo.setId(longCount.incrementAndGet());

        // Create the BasicInfo
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(basicInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBasicInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(basicInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BasicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBasicInfoWithPatch() throws Exception {
        // Initialize the database
        insertedBasicInfo = basicInfoRepository.saveAndFlush(basicInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the basicInfo using partial update
        BasicInfo partialUpdatedBasicInfo = new BasicInfo();
        partialUpdatedBasicInfo.setId(basicInfo.getId());

        partialUpdatedBasicInfo.code(UPDATED_CODE);

        restBasicInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBasicInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBasicInfo))
            )
            .andExpect(status().isOk());

        // Validate the BasicInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBasicInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedBasicInfo, basicInfo),
            getPersistedBasicInfo(basicInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateBasicInfoWithPatch() throws Exception {
        // Initialize the database
        insertedBasicInfo = basicInfoRepository.saveAndFlush(basicInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the basicInfo using partial update
        BasicInfo partialUpdatedBasicInfo = new BasicInfo();
        partialUpdatedBasicInfo.setId(basicInfo.getId());

        partialUpdatedBasicInfo.applyDate(UPDATED_APPLY_DATE).code(UPDATED_CODE).disabled(UPDATED_DISABLED);

        restBasicInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBasicInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBasicInfo))
            )
            .andExpect(status().isOk());

        // Validate the BasicInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBasicInfoUpdatableFieldsEquals(partialUpdatedBasicInfo, getPersistedBasicInfo(partialUpdatedBasicInfo));
    }

    @Test
    @Transactional
    void patchNonExistingBasicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        basicInfo.setId(longCount.incrementAndGet());

        // Create the BasicInfo
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(basicInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBasicInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, basicInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(basicInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BasicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBasicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        basicInfo.setId(longCount.incrementAndGet());

        // Create the BasicInfo
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(basicInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBasicInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(basicInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BasicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBasicInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        basicInfo.setId(longCount.incrementAndGet());

        // Create the BasicInfo
        BasicInfoDTO basicInfoDTO = basicInfoMapper.toDto(basicInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBasicInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(basicInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BasicInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBasicInfo() throws Exception {
        // Initialize the database
        insertedBasicInfo = basicInfoRepository.saveAndFlush(basicInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the basicInfo
        restBasicInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, basicInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return basicInfoRepository.count();
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

    protected BasicInfo getPersistedBasicInfo(BasicInfo basicInfo) {
        return basicInfoRepository.findById(basicInfo.getId()).orElseThrow();
    }

    protected void assertPersistedBasicInfoToMatchAllProperties(BasicInfo expectedBasicInfo) {
        assertBasicInfoAllPropertiesEquals(expectedBasicInfo, getPersistedBasicInfo(expectedBasicInfo));
    }

    protected void assertPersistedBasicInfoToMatchUpdatableProperties(BasicInfo expectedBasicInfo) {
        assertBasicInfoAllUpdatablePropertiesEquals(expectedBasicInfo, getPersistedBasicInfo(expectedBasicInfo));
    }
}
