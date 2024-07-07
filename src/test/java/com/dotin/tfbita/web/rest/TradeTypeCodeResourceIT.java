package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.TradeTypeCodeAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.TradeTypeCode;
import com.dotin.tfbita.repository.TradeTypeCodeRepository;
import com.dotin.tfbita.service.dto.TradeTypeCodeDTO;
import com.dotin.tfbita.service.mapper.TradeTypeCodeMapper;
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
 * Integration tests for the {@link TradeTypeCodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TradeTypeCodeResourceIT {

    private static final String DEFAULT_LATIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LATIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/trade-type-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TradeTypeCodeRepository tradeTypeCodeRepository;

    @Autowired
    private TradeTypeCodeMapper tradeTypeCodeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTradeTypeCodeMockMvc;

    private TradeTypeCode tradeTypeCode;

    private TradeTypeCode insertedTradeTypeCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeTypeCode createEntity(EntityManager em) {
        TradeTypeCode tradeTypeCode = new TradeTypeCode().latinName(DEFAULT_LATIN_NAME).name(DEFAULT_NAME);
        return tradeTypeCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeTypeCode createUpdatedEntity(EntityManager em) {
        TradeTypeCode tradeTypeCode = new TradeTypeCode().latinName(UPDATED_LATIN_NAME).name(UPDATED_NAME);
        return tradeTypeCode;
    }

    @BeforeEach
    public void initTest() {
        tradeTypeCode = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTradeTypeCode != null) {
            tradeTypeCodeRepository.delete(insertedTradeTypeCode);
            insertedTradeTypeCode = null;
        }
    }

    @Test
    @Transactional
    void createTradeTypeCode() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TradeTypeCode
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(tradeTypeCode);
        var returnedTradeTypeCodeDTO = om.readValue(
            restTradeTypeCodeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tradeTypeCodeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TradeTypeCodeDTO.class
        );

        // Validate the TradeTypeCode in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTradeTypeCode = tradeTypeCodeMapper.toEntity(returnedTradeTypeCodeDTO);
        assertTradeTypeCodeUpdatableFieldsEquals(returnedTradeTypeCode, getPersistedTradeTypeCode(returnedTradeTypeCode));

        insertedTradeTypeCode = returnedTradeTypeCode;
    }

    @Test
    @Transactional
    void createTradeTypeCodeWithExistingId() throws Exception {
        // Create the TradeTypeCode with an existing ID
        tradeTypeCode.setId(1L);
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(tradeTypeCode);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTradeTypeCodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tradeTypeCodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TradeTypeCode in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTradeTypeCodes() throws Exception {
        // Initialize the database
        insertedTradeTypeCode = tradeTypeCodeRepository.saveAndFlush(tradeTypeCode);

        // Get all the tradeTypeCodeList
        restTradeTypeCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tradeTypeCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].latinName").value(hasItem(DEFAULT_LATIN_NAME)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getTradeTypeCode() throws Exception {
        // Initialize the database
        insertedTradeTypeCode = tradeTypeCodeRepository.saveAndFlush(tradeTypeCode);

        // Get the tradeTypeCode
        restTradeTypeCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, tradeTypeCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tradeTypeCode.getId().intValue()))
            .andExpect(jsonPath("$.latinName").value(DEFAULT_LATIN_NAME))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingTradeTypeCode() throws Exception {
        // Get the tradeTypeCode
        restTradeTypeCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTradeTypeCode() throws Exception {
        // Initialize the database
        insertedTradeTypeCode = tradeTypeCodeRepository.saveAndFlush(tradeTypeCode);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tradeTypeCode
        TradeTypeCode updatedTradeTypeCode = tradeTypeCodeRepository.findById(tradeTypeCode.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTradeTypeCode are not directly saved in db
        em.detach(updatedTradeTypeCode);
        updatedTradeTypeCode.latinName(UPDATED_LATIN_NAME).name(UPDATED_NAME);
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(updatedTradeTypeCode);

        restTradeTypeCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tradeTypeCodeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tradeTypeCodeDTO))
            )
            .andExpect(status().isOk());

        // Validate the TradeTypeCode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTradeTypeCodeToMatchAllProperties(updatedTradeTypeCode);
    }

    @Test
    @Transactional
    void putNonExistingTradeTypeCode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tradeTypeCode.setId(longCount.incrementAndGet());

        // Create the TradeTypeCode
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(tradeTypeCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTradeTypeCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tradeTypeCodeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tradeTypeCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeTypeCode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTradeTypeCode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tradeTypeCode.setId(longCount.incrementAndGet());

        // Create the TradeTypeCode
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(tradeTypeCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeTypeCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(tradeTypeCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeTypeCode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTradeTypeCode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tradeTypeCode.setId(longCount.incrementAndGet());

        // Create the TradeTypeCode
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(tradeTypeCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeTypeCodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(tradeTypeCodeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TradeTypeCode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTradeTypeCodeWithPatch() throws Exception {
        // Initialize the database
        insertedTradeTypeCode = tradeTypeCodeRepository.saveAndFlush(tradeTypeCode);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tradeTypeCode using partial update
        TradeTypeCode partialUpdatedTradeTypeCode = new TradeTypeCode();
        partialUpdatedTradeTypeCode.setId(tradeTypeCode.getId());

        partialUpdatedTradeTypeCode.latinName(UPDATED_LATIN_NAME);

        restTradeTypeCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTradeTypeCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTradeTypeCode))
            )
            .andExpect(status().isOk());

        // Validate the TradeTypeCode in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTradeTypeCodeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTradeTypeCode, tradeTypeCode),
            getPersistedTradeTypeCode(tradeTypeCode)
        );
    }

    @Test
    @Transactional
    void fullUpdateTradeTypeCodeWithPatch() throws Exception {
        // Initialize the database
        insertedTradeTypeCode = tradeTypeCodeRepository.saveAndFlush(tradeTypeCode);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the tradeTypeCode using partial update
        TradeTypeCode partialUpdatedTradeTypeCode = new TradeTypeCode();
        partialUpdatedTradeTypeCode.setId(tradeTypeCode.getId());

        partialUpdatedTradeTypeCode.latinName(UPDATED_LATIN_NAME).name(UPDATED_NAME);

        restTradeTypeCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTradeTypeCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTradeTypeCode))
            )
            .andExpect(status().isOk());

        // Validate the TradeTypeCode in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTradeTypeCodeUpdatableFieldsEquals(partialUpdatedTradeTypeCode, getPersistedTradeTypeCode(partialUpdatedTradeTypeCode));
    }

    @Test
    @Transactional
    void patchNonExistingTradeTypeCode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tradeTypeCode.setId(longCount.incrementAndGet());

        // Create the TradeTypeCode
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(tradeTypeCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTradeTypeCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tradeTypeCodeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tradeTypeCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeTypeCode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTradeTypeCode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tradeTypeCode.setId(longCount.incrementAndGet());

        // Create the TradeTypeCode
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(tradeTypeCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeTypeCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(tradeTypeCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeTypeCode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTradeTypeCode() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        tradeTypeCode.setId(longCount.incrementAndGet());

        // Create the TradeTypeCode
        TradeTypeCodeDTO tradeTypeCodeDTO = tradeTypeCodeMapper.toDto(tradeTypeCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeTypeCodeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(tradeTypeCodeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TradeTypeCode in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTradeTypeCode() throws Exception {
        // Initialize the database
        insertedTradeTypeCode = tradeTypeCodeRepository.saveAndFlush(tradeTypeCode);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the tradeTypeCode
        restTradeTypeCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, tradeTypeCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return tradeTypeCodeRepository.count();
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

    protected TradeTypeCode getPersistedTradeTypeCode(TradeTypeCode tradeTypeCode) {
        return tradeTypeCodeRepository.findById(tradeTypeCode.getId()).orElseThrow();
    }

    protected void assertPersistedTradeTypeCodeToMatchAllProperties(TradeTypeCode expectedTradeTypeCode) {
        assertTradeTypeCodeAllPropertiesEquals(expectedTradeTypeCode, getPersistedTradeTypeCode(expectedTradeTypeCode));
    }

    protected void assertPersistedTradeTypeCodeToMatchUpdatableProperties(TradeTypeCode expectedTradeTypeCode) {
        assertTradeTypeCodeAllUpdatablePropertiesEquals(expectedTradeTypeCode, getPersistedTradeTypeCode(expectedTradeTypeCode));
    }
}
