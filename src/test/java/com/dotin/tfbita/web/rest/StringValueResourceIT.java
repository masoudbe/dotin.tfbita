package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.StringValueAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.StringValue;
import com.dotin.tfbita.repository.StringValueRepository;
import com.dotin.tfbita.service.dto.StringValueDTO;
import com.dotin.tfbita.service.mapper.StringValueMapper;
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
 * Integration tests for the {@link StringValueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StringValueResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/string-values";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private StringValueRepository stringValueRepository;

    @Autowired
    private StringValueMapper stringValueMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStringValueMockMvc;

    private StringValue stringValue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StringValue createEntity(EntityManager em) {
        StringValue stringValue = new StringValue().value(DEFAULT_VALUE);
        return stringValue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StringValue createUpdatedEntity(EntityManager em) {
        StringValue stringValue = new StringValue().value(UPDATED_VALUE);
        return stringValue;
    }

    @BeforeEach
    public void initTest() {
        stringValue = createEntity(em);
    }

    @Test
    @Transactional
    void createStringValue() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the StringValue
        StringValueDTO stringValueDTO = stringValueMapper.toDto(stringValue);
        var returnedStringValueDTO = om.readValue(
            restStringValueMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(stringValueDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            StringValueDTO.class
        );

        // Validate the StringValue in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedStringValue = stringValueMapper.toEntity(returnedStringValueDTO);
        assertStringValueUpdatableFieldsEquals(returnedStringValue, getPersistedStringValue(returnedStringValue));
    }

    @Test
    @Transactional
    void createStringValueWithExistingId() throws Exception {
        // Create the StringValue with an existing ID
        stringValue.setId(1L);
        StringValueDTO stringValueDTO = stringValueMapper.toDto(stringValue);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStringValueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(stringValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StringValue in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStringValues() throws Exception {
        // Initialize the database
        stringValueRepository.saveAndFlush(stringValue);

        // Get all the stringValueList
        restStringValueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stringValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }

    @Test
    @Transactional
    void getStringValue() throws Exception {
        // Initialize the database
        stringValueRepository.saveAndFlush(stringValue);

        // Get the stringValue
        restStringValueMockMvc
            .perform(get(ENTITY_API_URL_ID, stringValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stringValue.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    void getNonExistingStringValue() throws Exception {
        // Get the stringValue
        restStringValueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStringValue() throws Exception {
        // Initialize the database
        stringValueRepository.saveAndFlush(stringValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the stringValue
        StringValue updatedStringValue = stringValueRepository.findById(stringValue.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedStringValue are not directly saved in db
        em.detach(updatedStringValue);
        updatedStringValue.value(UPDATED_VALUE);
        StringValueDTO stringValueDTO = stringValueMapper.toDto(updatedStringValue);

        restStringValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stringValueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(stringValueDTO))
            )
            .andExpect(status().isOk());

        // Validate the StringValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedStringValueToMatchAllProperties(updatedStringValue);
    }

    @Test
    @Transactional
    void putNonExistingStringValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stringValue.setId(longCount.incrementAndGet());

        // Create the StringValue
        StringValueDTO stringValueDTO = stringValueMapper.toDto(stringValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStringValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stringValueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(stringValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StringValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStringValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stringValue.setId(longCount.incrementAndGet());

        // Create the StringValue
        StringValueDTO stringValueDTO = stringValueMapper.toDto(stringValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStringValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(stringValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StringValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStringValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stringValue.setId(longCount.incrementAndGet());

        // Create the StringValue
        StringValueDTO stringValueDTO = stringValueMapper.toDto(stringValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStringValueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(stringValueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the StringValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStringValueWithPatch() throws Exception {
        // Initialize the database
        stringValueRepository.saveAndFlush(stringValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the stringValue using partial update
        StringValue partialUpdatedStringValue = new StringValue();
        partialUpdatedStringValue.setId(stringValue.getId());

        restStringValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStringValue.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedStringValue))
            )
            .andExpect(status().isOk());

        // Validate the StringValue in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertStringValueUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedStringValue, stringValue),
            getPersistedStringValue(stringValue)
        );
    }

    @Test
    @Transactional
    void fullUpdateStringValueWithPatch() throws Exception {
        // Initialize the database
        stringValueRepository.saveAndFlush(stringValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the stringValue using partial update
        StringValue partialUpdatedStringValue = new StringValue();
        partialUpdatedStringValue.setId(stringValue.getId());

        partialUpdatedStringValue.value(UPDATED_VALUE);

        restStringValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStringValue.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedStringValue))
            )
            .andExpect(status().isOk());

        // Validate the StringValue in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertStringValueUpdatableFieldsEquals(partialUpdatedStringValue, getPersistedStringValue(partialUpdatedStringValue));
    }

    @Test
    @Transactional
    void patchNonExistingStringValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stringValue.setId(longCount.incrementAndGet());

        // Create the StringValue
        StringValueDTO stringValueDTO = stringValueMapper.toDto(stringValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStringValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stringValueDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(stringValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StringValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStringValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stringValue.setId(longCount.incrementAndGet());

        // Create the StringValue
        StringValueDTO stringValueDTO = stringValueMapper.toDto(stringValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStringValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(stringValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the StringValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStringValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stringValue.setId(longCount.incrementAndGet());

        // Create the StringValue
        StringValueDTO stringValueDTO = stringValueMapper.toDto(stringValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStringValueMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(stringValueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the StringValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStringValue() throws Exception {
        // Initialize the database
        stringValueRepository.saveAndFlush(stringValue);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the stringValue
        restStringValueMockMvc
            .perform(delete(ENTITY_API_URL_ID, stringValue.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return stringValueRepository.count();
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

    protected StringValue getPersistedStringValue(StringValue stringValue) {
        return stringValueRepository.findById(stringValue.getId()).orElseThrow();
    }

    protected void assertPersistedStringValueToMatchAllProperties(StringValue expectedStringValue) {
        assertStringValueAllPropertiesEquals(expectedStringValue, getPersistedStringValue(expectedStringValue));
    }

    protected void assertPersistedStringValueToMatchUpdatableProperties(StringValue expectedStringValue) {
        assertStringValueAllUpdatablePropertiesEquals(expectedStringValue, getPersistedStringValue(expectedStringValue));
    }
}
