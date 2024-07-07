package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.CurrencyExchangeInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.CurrencyExchangeInfo;
import com.dotin.tfbita.repository.CurrencyExchangeInfoRepository;
import com.dotin.tfbita.service.dto.CurrencyExchangeInfoDTO;
import com.dotin.tfbita.service.mapper.CurrencyExchangeInfoMapper;
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
 * Integration tests for the {@link CurrencyExchangeInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CurrencyExchangeInfoResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/currency-exchange-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CurrencyExchangeInfoRepository currencyExchangeInfoRepository;

    @Autowired
    private CurrencyExchangeInfoMapper currencyExchangeInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCurrencyExchangeInfoMockMvc;

    private CurrencyExchangeInfo currencyExchangeInfo;

    private CurrencyExchangeInfo insertedCurrencyExchangeInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CurrencyExchangeInfo createEntity(EntityManager em) {
        CurrencyExchangeInfo currencyExchangeInfo = new CurrencyExchangeInfo().title(DEFAULT_TITLE);
        return currencyExchangeInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CurrencyExchangeInfo createUpdatedEntity(EntityManager em) {
        CurrencyExchangeInfo currencyExchangeInfo = new CurrencyExchangeInfo().title(UPDATED_TITLE);
        return currencyExchangeInfo;
    }

    @BeforeEach
    public void initTest() {
        currencyExchangeInfo = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCurrencyExchangeInfo != null) {
            currencyExchangeInfoRepository.delete(insertedCurrencyExchangeInfo);
            insertedCurrencyExchangeInfo = null;
        }
    }

    @Test
    @Transactional
    void createCurrencyExchangeInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CurrencyExchangeInfo
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(currencyExchangeInfo);
        var returnedCurrencyExchangeInfoDTO = om.readValue(
            restCurrencyExchangeInfoMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(currencyExchangeInfoDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CurrencyExchangeInfoDTO.class
        );

        // Validate the CurrencyExchangeInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCurrencyExchangeInfo = currencyExchangeInfoMapper.toEntity(returnedCurrencyExchangeInfoDTO);
        assertCurrencyExchangeInfoUpdatableFieldsEquals(
            returnedCurrencyExchangeInfo,
            getPersistedCurrencyExchangeInfo(returnedCurrencyExchangeInfo)
        );

        insertedCurrencyExchangeInfo = returnedCurrencyExchangeInfo;
    }

    @Test
    @Transactional
    void createCurrencyExchangeInfoWithExistingId() throws Exception {
        // Create the CurrencyExchangeInfo with an existing ID
        currencyExchangeInfo.setId(1L);
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(currencyExchangeInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCurrencyExchangeInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(currencyExchangeInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchangeInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCurrencyExchangeInfos() throws Exception {
        // Initialize the database
        insertedCurrencyExchangeInfo = currencyExchangeInfoRepository.saveAndFlush(currencyExchangeInfo);

        // Get all the currencyExchangeInfoList
        restCurrencyExchangeInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(currencyExchangeInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));
    }

    @Test
    @Transactional
    void getCurrencyExchangeInfo() throws Exception {
        // Initialize the database
        insertedCurrencyExchangeInfo = currencyExchangeInfoRepository.saveAndFlush(currencyExchangeInfo);

        // Get the currencyExchangeInfo
        restCurrencyExchangeInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, currencyExchangeInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(currencyExchangeInfo.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE));
    }

    @Test
    @Transactional
    void getNonExistingCurrencyExchangeInfo() throws Exception {
        // Get the currencyExchangeInfo
        restCurrencyExchangeInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCurrencyExchangeInfo() throws Exception {
        // Initialize the database
        insertedCurrencyExchangeInfo = currencyExchangeInfoRepository.saveAndFlush(currencyExchangeInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the currencyExchangeInfo
        CurrencyExchangeInfo updatedCurrencyExchangeInfo = currencyExchangeInfoRepository
            .findById(currencyExchangeInfo.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCurrencyExchangeInfo are not directly saved in db
        em.detach(updatedCurrencyExchangeInfo);
        updatedCurrencyExchangeInfo.title(UPDATED_TITLE);
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(updatedCurrencyExchangeInfo);

        restCurrencyExchangeInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, currencyExchangeInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(currencyExchangeInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the CurrencyExchangeInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCurrencyExchangeInfoToMatchAllProperties(updatedCurrencyExchangeInfo);
    }

    @Test
    @Transactional
    void putNonExistingCurrencyExchangeInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        currencyExchangeInfo.setId(longCount.incrementAndGet());

        // Create the CurrencyExchangeInfo
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(currencyExchangeInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCurrencyExchangeInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, currencyExchangeInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(currencyExchangeInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchangeInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCurrencyExchangeInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        currencyExchangeInfo.setId(longCount.incrementAndGet());

        // Create the CurrencyExchangeInfo
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(currencyExchangeInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCurrencyExchangeInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(currencyExchangeInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchangeInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCurrencyExchangeInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        currencyExchangeInfo.setId(longCount.incrementAndGet());

        // Create the CurrencyExchangeInfo
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(currencyExchangeInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCurrencyExchangeInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(currencyExchangeInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CurrencyExchangeInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCurrencyExchangeInfoWithPatch() throws Exception {
        // Initialize the database
        insertedCurrencyExchangeInfo = currencyExchangeInfoRepository.saveAndFlush(currencyExchangeInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the currencyExchangeInfo using partial update
        CurrencyExchangeInfo partialUpdatedCurrencyExchangeInfo = new CurrencyExchangeInfo();
        partialUpdatedCurrencyExchangeInfo.setId(currencyExchangeInfo.getId());

        partialUpdatedCurrencyExchangeInfo.title(UPDATED_TITLE);

        restCurrencyExchangeInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCurrencyExchangeInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCurrencyExchangeInfo))
            )
            .andExpect(status().isOk());

        // Validate the CurrencyExchangeInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCurrencyExchangeInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCurrencyExchangeInfo, currencyExchangeInfo),
            getPersistedCurrencyExchangeInfo(currencyExchangeInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateCurrencyExchangeInfoWithPatch() throws Exception {
        // Initialize the database
        insertedCurrencyExchangeInfo = currencyExchangeInfoRepository.saveAndFlush(currencyExchangeInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the currencyExchangeInfo using partial update
        CurrencyExchangeInfo partialUpdatedCurrencyExchangeInfo = new CurrencyExchangeInfo();
        partialUpdatedCurrencyExchangeInfo.setId(currencyExchangeInfo.getId());

        partialUpdatedCurrencyExchangeInfo.title(UPDATED_TITLE);

        restCurrencyExchangeInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCurrencyExchangeInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCurrencyExchangeInfo))
            )
            .andExpect(status().isOk());

        // Validate the CurrencyExchangeInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCurrencyExchangeInfoUpdatableFieldsEquals(
            partialUpdatedCurrencyExchangeInfo,
            getPersistedCurrencyExchangeInfo(partialUpdatedCurrencyExchangeInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCurrencyExchangeInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        currencyExchangeInfo.setId(longCount.incrementAndGet());

        // Create the CurrencyExchangeInfo
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(currencyExchangeInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCurrencyExchangeInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, currencyExchangeInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(currencyExchangeInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchangeInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCurrencyExchangeInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        currencyExchangeInfo.setId(longCount.incrementAndGet());

        // Create the CurrencyExchangeInfo
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(currencyExchangeInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCurrencyExchangeInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(currencyExchangeInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchangeInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCurrencyExchangeInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        currencyExchangeInfo.setId(longCount.incrementAndGet());

        // Create the CurrencyExchangeInfo
        CurrencyExchangeInfoDTO currencyExchangeInfoDTO = currencyExchangeInfoMapper.toDto(currencyExchangeInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCurrencyExchangeInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(currencyExchangeInfoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CurrencyExchangeInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCurrencyExchangeInfo() throws Exception {
        // Initialize the database
        insertedCurrencyExchangeInfo = currencyExchangeInfoRepository.saveAndFlush(currencyExchangeInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the currencyExchangeInfo
        restCurrencyExchangeInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, currencyExchangeInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return currencyExchangeInfoRepository.count();
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

    protected CurrencyExchangeInfo getPersistedCurrencyExchangeInfo(CurrencyExchangeInfo currencyExchangeInfo) {
        return currencyExchangeInfoRepository.findById(currencyExchangeInfo.getId()).orElseThrow();
    }

    protected void assertPersistedCurrencyExchangeInfoToMatchAllProperties(CurrencyExchangeInfo expectedCurrencyExchangeInfo) {
        assertCurrencyExchangeInfoAllPropertiesEquals(
            expectedCurrencyExchangeInfo,
            getPersistedCurrencyExchangeInfo(expectedCurrencyExchangeInfo)
        );
    }

    protected void assertPersistedCurrencyExchangeInfoToMatchUpdatableProperties(CurrencyExchangeInfo expectedCurrencyExchangeInfo) {
        assertCurrencyExchangeInfoAllUpdatablePropertiesEquals(
            expectedCurrencyExchangeInfo,
            getPersistedCurrencyExchangeInfo(expectedCurrencyExchangeInfo)
        );
    }
}
