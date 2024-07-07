package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.LongValueAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.LongValue;
import com.dotin.tfbita.repository.LongValueRepository;
import com.dotin.tfbita.service.dto.LongValueDTO;
import com.dotin.tfbita.service.mapper.LongValueMapper;
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
 * Integration tests for the {@link LongValueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LongValueResourceIT {

    private static final Long DEFAULT_VAL = 1L;
    private static final Long UPDATED_VAL = 2L;

    private static final String ENTITY_API_URL = "/api/long-values";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LongValueRepository longValueRepository;

    @Autowired
    private LongValueMapper longValueMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLongValueMockMvc;

    private LongValue longValue;

    private LongValue insertedLongValue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LongValue createEntity(EntityManager em) {
        LongValue longValue = new LongValue().val(DEFAULT_VAL);
        return longValue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LongValue createUpdatedEntity(EntityManager em) {
        LongValue longValue = new LongValue().val(UPDATED_VAL);
        return longValue;
    }

    @BeforeEach
    public void initTest() {
        longValue = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedLongValue != null) {
            longValueRepository.delete(insertedLongValue);
            insertedLongValue = null;
        }
    }

    @Test
    @Transactional
    void createLongValue() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LongValue
        LongValueDTO longValueDTO = longValueMapper.toDto(longValue);
        var returnedLongValueDTO = om.readValue(
            restLongValueMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(longValueDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LongValueDTO.class
        );

        // Validate the LongValue in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLongValue = longValueMapper.toEntity(returnedLongValueDTO);
        assertLongValueUpdatableFieldsEquals(returnedLongValue, getPersistedLongValue(returnedLongValue));

        insertedLongValue = returnedLongValue;
    }

    @Test
    @Transactional
    void createLongValueWithExistingId() throws Exception {
        // Create the LongValue with an existing ID
        longValue.setId(1L);
        LongValueDTO longValueDTO = longValueMapper.toDto(longValue);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLongValueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(longValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LongValue in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLongValues() throws Exception {
        // Initialize the database
        insertedLongValue = longValueRepository.saveAndFlush(longValue);

        // Get all the longValueList
        restLongValueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(longValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].val").value(hasItem(DEFAULT_VAL.intValue())));
    }

    @Test
    @Transactional
    void getLongValue() throws Exception {
        // Initialize the database
        insertedLongValue = longValueRepository.saveAndFlush(longValue);

        // Get the longValue
        restLongValueMockMvc
            .perform(get(ENTITY_API_URL_ID, longValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(longValue.getId().intValue()))
            .andExpect(jsonPath("$.val").value(DEFAULT_VAL.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingLongValue() throws Exception {
        // Get the longValue
        restLongValueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLongValue() throws Exception {
        // Initialize the database
        insertedLongValue = longValueRepository.saveAndFlush(longValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the longValue
        LongValue updatedLongValue = longValueRepository.findById(longValue.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLongValue are not directly saved in db
        em.detach(updatedLongValue);
        updatedLongValue.val(UPDATED_VAL);
        LongValueDTO longValueDTO = longValueMapper.toDto(updatedLongValue);

        restLongValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longValueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(longValueDTO))
            )
            .andExpect(status().isOk());

        // Validate the LongValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLongValueToMatchAllProperties(updatedLongValue);
    }

    @Test
    @Transactional
    void putNonExistingLongValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        longValue.setId(longCount.incrementAndGet());

        // Create the LongValue
        LongValueDTO longValueDTO = longValueMapper.toDto(longValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLongValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longValueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(longValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LongValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLongValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        longValue.setId(longCount.incrementAndGet());

        // Create the LongValue
        LongValueDTO longValueDTO = longValueMapper.toDto(longValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLongValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(longValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LongValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLongValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        longValue.setId(longCount.incrementAndGet());

        // Create the LongValue
        LongValueDTO longValueDTO = longValueMapper.toDto(longValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLongValueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(longValueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LongValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLongValueWithPatch() throws Exception {
        // Initialize the database
        insertedLongValue = longValueRepository.saveAndFlush(longValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the longValue using partial update
        LongValue partialUpdatedLongValue = new LongValue();
        partialUpdatedLongValue.setId(longValue.getId());

        restLongValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLongValue.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLongValue))
            )
            .andExpect(status().isOk());

        // Validate the LongValue in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLongValueUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLongValue, longValue),
            getPersistedLongValue(longValue)
        );
    }

    @Test
    @Transactional
    void fullUpdateLongValueWithPatch() throws Exception {
        // Initialize the database
        insertedLongValue = longValueRepository.saveAndFlush(longValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the longValue using partial update
        LongValue partialUpdatedLongValue = new LongValue();
        partialUpdatedLongValue.setId(longValue.getId());

        partialUpdatedLongValue.val(UPDATED_VAL);

        restLongValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLongValue.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLongValue))
            )
            .andExpect(status().isOk());

        // Validate the LongValue in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLongValueUpdatableFieldsEquals(partialUpdatedLongValue, getPersistedLongValue(partialUpdatedLongValue));
    }

    @Test
    @Transactional
    void patchNonExistingLongValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        longValue.setId(longCount.incrementAndGet());

        // Create the LongValue
        LongValueDTO longValueDTO = longValueMapper.toDto(longValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLongValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longValueDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(longValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LongValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLongValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        longValue.setId(longCount.incrementAndGet());

        // Create the LongValue
        LongValueDTO longValueDTO = longValueMapper.toDto(longValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLongValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(longValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LongValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLongValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        longValue.setId(longCount.incrementAndGet());

        // Create the LongValue
        LongValueDTO longValueDTO = longValueMapper.toDto(longValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLongValueMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(longValueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LongValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLongValue() throws Exception {
        // Initialize the database
        insertedLongValue = longValueRepository.saveAndFlush(longValue);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the longValue
        restLongValueMockMvc
            .perform(delete(ENTITY_API_URL_ID, longValue.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return longValueRepository.count();
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

    protected LongValue getPersistedLongValue(LongValue longValue) {
        return longValueRepository.findById(longValue.getId()).orElseThrow();
    }

    protected void assertPersistedLongValueToMatchAllProperties(LongValue expectedLongValue) {
        assertLongValueAllPropertiesEquals(expectedLongValue, getPersistedLongValue(expectedLongValue));
    }

    protected void assertPersistedLongValueToMatchUpdatableProperties(LongValue expectedLongValue) {
        assertLongValueAllUpdatablePropertiesEquals(expectedLongValue, getPersistedLongValue(expectedLongValue));
    }
}
