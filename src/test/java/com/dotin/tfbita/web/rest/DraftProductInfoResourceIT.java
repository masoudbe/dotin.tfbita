package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftProductInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftProductInfo;
import com.dotin.tfbita.repository.DraftProductInfoRepository;
import com.dotin.tfbita.service.dto.DraftProductInfoDTO;
import com.dotin.tfbita.service.mapper.DraftProductInfoMapper;
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
 * Integration tests for the {@link DraftProductInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftProductInfoResourceIT {

    private static final String DEFAULT_PRODUCT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DIMENSION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DIMENSION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/draft-product-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftProductInfoRepository draftProductInfoRepository;

    @Autowired
    private DraftProductInfoMapper draftProductInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftProductInfoMockMvc;

    private DraftProductInfo draftProductInfo;

    private DraftProductInfo insertedDraftProductInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftProductInfo createEntity(EntityManager em) {
        DraftProductInfo draftProductInfo = new DraftProductInfo()
            .productAmount(DEFAULT_PRODUCT_AMOUNT)
            .productDimension(DEFAULT_PRODUCT_DIMENSION);
        return draftProductInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftProductInfo createUpdatedEntity(EntityManager em) {
        DraftProductInfo draftProductInfo = new DraftProductInfo()
            .productAmount(UPDATED_PRODUCT_AMOUNT)
            .productDimension(UPDATED_PRODUCT_DIMENSION);
        return draftProductInfo;
    }

    @BeforeEach
    public void initTest() {
        draftProductInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftProductInfo != null) {
            draftProductInfoRepository.delete(insertedDraftProductInfo);
            insertedDraftProductInfo = null;
        }
    }

    @Test
    @Transactional
    void createDraftProductInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftProductInfo
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(draftProductInfo);
        var returnedDraftProductInfoDTO = om.readValue(
            restDraftProductInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftProductInfoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftProductInfoDTO.class
        );

        // Validate the DraftProductInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftProductInfo = draftProductInfoMapper.toEntity(returnedDraftProductInfoDTO);
        assertDraftProductInfoUpdatableFieldsEquals(returnedDraftProductInfo, getPersistedDraftProductInfo(returnedDraftProductInfo));

        insertedDraftProductInfo = returnedDraftProductInfo;
    }

    @Test
    @Transactional
    void createDraftProductInfoWithExistingId() throws Exception {
        // Create the DraftProductInfo with an existing ID
        draftProductInfo.setId(1L);
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(draftProductInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftProductInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftProductInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftProductInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftProductInfos() throws Exception {
        // Initialize the database
        insertedDraftProductInfo = draftProductInfoRepository.saveAndFlush(draftProductInfo);

        // Get all the draftProductInfoList
        restDraftProductInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftProductInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].productAmount").value(hasItem(DEFAULT_PRODUCT_AMOUNT)))
            .andExpect(jsonPath("$.[*].productDimension").value(hasItem(DEFAULT_PRODUCT_DIMENSION)));
    }

    @Test
    @Transactional
    void getDraftProductInfo() throws Exception {
        // Initialize the database
        insertedDraftProductInfo = draftProductInfoRepository.saveAndFlush(draftProductInfo);

        // Get the draftProductInfo
        restDraftProductInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, draftProductInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftProductInfo.getId().intValue()))
            .andExpect(jsonPath("$.productAmount").value(DEFAULT_PRODUCT_AMOUNT))
            .andExpect(jsonPath("$.productDimension").value(DEFAULT_PRODUCT_DIMENSION));
    }

    @Test
    @Transactional
    void getNonExistingDraftProductInfo() throws Exception {
        // Get the draftProductInfo
        restDraftProductInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDraftProductInfo() throws Exception {
        // Initialize the database
        insertedDraftProductInfo = draftProductInfoRepository.saveAndFlush(draftProductInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftProductInfo
        DraftProductInfo updatedDraftProductInfo = draftProductInfoRepository.findById(draftProductInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDraftProductInfo are not directly saved in db
        em.detach(updatedDraftProductInfo);
        updatedDraftProductInfo.productAmount(UPDATED_PRODUCT_AMOUNT).productDimension(UPDATED_PRODUCT_DIMENSION);
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(updatedDraftProductInfo);

        restDraftProductInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftProductInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftProductInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the DraftProductInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDraftProductInfoToMatchAllProperties(updatedDraftProductInfo);
    }

    @Test
    @Transactional
    void putNonExistingDraftProductInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftProductInfo.setId(longCount.incrementAndGet());

        // Create the DraftProductInfo
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(draftProductInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftProductInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, draftProductInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftProductInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftProductInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDraftProductInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftProductInfo.setId(longCount.incrementAndGet());

        // Create the DraftProductInfo
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(draftProductInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftProductInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(draftProductInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftProductInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDraftProductInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftProductInfo.setId(longCount.incrementAndGet());

        // Create the DraftProductInfo
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(draftProductInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftProductInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftProductInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftProductInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDraftProductInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftProductInfo = draftProductInfoRepository.saveAndFlush(draftProductInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftProductInfo using partial update
        DraftProductInfo partialUpdatedDraftProductInfo = new DraftProductInfo();
        partialUpdatedDraftProductInfo.setId(draftProductInfo.getId());

        partialUpdatedDraftProductInfo.productAmount(UPDATED_PRODUCT_AMOUNT).productDimension(UPDATED_PRODUCT_DIMENSION);

        restDraftProductInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftProductInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftProductInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftProductInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftProductInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDraftProductInfo, draftProductInfo),
            getPersistedDraftProductInfo(draftProductInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateDraftProductInfoWithPatch() throws Exception {
        // Initialize the database
        insertedDraftProductInfo = draftProductInfoRepository.saveAndFlush(draftProductInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the draftProductInfo using partial update
        DraftProductInfo partialUpdatedDraftProductInfo = new DraftProductInfo();
        partialUpdatedDraftProductInfo.setId(draftProductInfo.getId());

        partialUpdatedDraftProductInfo.productAmount(UPDATED_PRODUCT_AMOUNT).productDimension(UPDATED_PRODUCT_DIMENSION);

        restDraftProductInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDraftProductInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDraftProductInfo))
            )
            .andExpect(status().isOk());

        // Validate the DraftProductInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDraftProductInfoUpdatableFieldsEquals(
            partialUpdatedDraftProductInfo,
            getPersistedDraftProductInfo(partialUpdatedDraftProductInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDraftProductInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftProductInfo.setId(longCount.incrementAndGet());

        // Create the DraftProductInfo
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(draftProductInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDraftProductInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, draftProductInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftProductInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftProductInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDraftProductInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftProductInfo.setId(longCount.incrementAndGet());

        // Create the DraftProductInfo
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(draftProductInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftProductInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(draftProductInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DraftProductInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDraftProductInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        draftProductInfo.setId(longCount.incrementAndGet());

        // Create the DraftProductInfo
        DraftProductInfoDTO draftProductInfoDTO = draftProductInfoMapper.toDto(draftProductInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDraftProductInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(draftProductInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DraftProductInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDraftProductInfo() throws Exception {
        // Initialize the database
        insertedDraftProductInfo = draftProductInfoRepository.saveAndFlush(draftProductInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftProductInfo
        restDraftProductInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftProductInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftProductInfoRepository.count();
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

    protected DraftProductInfo getPersistedDraftProductInfo(DraftProductInfo draftProductInfo) {
        return draftProductInfoRepository.findById(draftProductInfo.getId()).orElseThrow();
    }

    protected void assertPersistedDraftProductInfoToMatchAllProperties(DraftProductInfo expectedDraftProductInfo) {
        assertDraftProductInfoAllPropertiesEquals(expectedDraftProductInfo, getPersistedDraftProductInfo(expectedDraftProductInfo));
    }

    protected void assertPersistedDraftProductInfoToMatchUpdatableProperties(DraftProductInfo expectedDraftProductInfo) {
        assertDraftProductInfoAllUpdatablePropertiesEquals(
            expectedDraftProductInfo,
            getPersistedDraftProductInfo(expectedDraftProductInfo)
        );
    }
}
