package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.AttributeValueAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.AttributeValue;
import com.dotin.tfbita.repository.AttributeValueRepository;
import com.dotin.tfbita.service.dto.AttributeValueDTO;
import com.dotin.tfbita.service.mapper.AttributeValueMapper;
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
 * Integration tests for the {@link AttributeValueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AttributeValueResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_ATTRIBUTE_VALUE_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ATTRIBUTE_VALUE_GROUP_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/attribute-values";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @Autowired
    private AttributeValueMapper attributeValueMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAttributeValueMockMvc;

    private AttributeValue attributeValue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeValue createEntity(EntityManager em) {
        AttributeValue attributeValue = new AttributeValue()
            .value(DEFAULT_VALUE)
            .customValue(DEFAULT_CUSTOM_VALUE)
            .attributeValueGroupName(DEFAULT_ATTRIBUTE_VALUE_GROUP_NAME);
        return attributeValue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeValue createUpdatedEntity(EntityManager em) {
        AttributeValue attributeValue = new AttributeValue()
            .value(UPDATED_VALUE)
            .customValue(UPDATED_CUSTOM_VALUE)
            .attributeValueGroupName(UPDATED_ATTRIBUTE_VALUE_GROUP_NAME);
        return attributeValue;
    }

    @BeforeEach
    public void initTest() {
        attributeValue = createEntity(em);
    }

    @Test
    @Transactional
    void createAttributeValue() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AttributeValue
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(attributeValue);
        var returnedAttributeValueDTO = om.readValue(
            restAttributeValueMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(attributeValueDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AttributeValueDTO.class
        );

        // Validate the AttributeValue in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedAttributeValue = attributeValueMapper.toEntity(returnedAttributeValueDTO);
        assertAttributeValueUpdatableFieldsEquals(returnedAttributeValue, getPersistedAttributeValue(returnedAttributeValue));
    }

    @Test
    @Transactional
    void createAttributeValueWithExistingId() throws Exception {
        // Create the AttributeValue with an existing ID
        attributeValue.setId(1L);
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(attributeValue);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttributeValueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(attributeValueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AttributeValue in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAttributeValues() throws Exception {
        // Initialize the database
        attributeValueRepository.saveAndFlush(attributeValue);

        // Get all the attributeValueList
        restAttributeValueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attributeValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].customValue").value(hasItem(DEFAULT_CUSTOM_VALUE)))
            .andExpect(jsonPath("$.[*].attributeValueGroupName").value(hasItem(DEFAULT_ATTRIBUTE_VALUE_GROUP_NAME)));
    }

    @Test
    @Transactional
    void getAttributeValue() throws Exception {
        // Initialize the database
        attributeValueRepository.saveAndFlush(attributeValue);

        // Get the attributeValue
        restAttributeValueMockMvc
            .perform(get(ENTITY_API_URL_ID, attributeValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(attributeValue.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.customValue").value(DEFAULT_CUSTOM_VALUE))
            .andExpect(jsonPath("$.attributeValueGroupName").value(DEFAULT_ATTRIBUTE_VALUE_GROUP_NAME));
    }

    @Test
    @Transactional
    void getNonExistingAttributeValue() throws Exception {
        // Get the attributeValue
        restAttributeValueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAttributeValue() throws Exception {
        // Initialize the database
        attributeValueRepository.saveAndFlush(attributeValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the attributeValue
        AttributeValue updatedAttributeValue = attributeValueRepository.findById(attributeValue.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAttributeValue are not directly saved in db
        em.detach(updatedAttributeValue);
        updatedAttributeValue
            .value(UPDATED_VALUE)
            .customValue(UPDATED_CUSTOM_VALUE)
            .attributeValueGroupName(UPDATED_ATTRIBUTE_VALUE_GROUP_NAME);
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(updatedAttributeValue);

        restAttributeValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, attributeValueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(attributeValueDTO))
            )
            .andExpect(status().isOk());

        // Validate the AttributeValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAttributeValueToMatchAllProperties(updatedAttributeValue);
    }

    @Test
    @Transactional
    void putNonExistingAttributeValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValue.setId(longCount.incrementAndGet());

        // Create the AttributeValue
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(attributeValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttributeValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, attributeValueDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(attributeValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AttributeValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAttributeValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValue.setId(longCount.incrementAndGet());

        // Create the AttributeValue
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(attributeValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttributeValueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(attributeValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AttributeValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAttributeValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValue.setId(longCount.incrementAndGet());

        // Create the AttributeValue
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(attributeValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttributeValueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(attributeValueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AttributeValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAttributeValueWithPatch() throws Exception {
        // Initialize the database
        attributeValueRepository.saveAndFlush(attributeValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the attributeValue using partial update
        AttributeValue partialUpdatedAttributeValue = new AttributeValue();
        partialUpdatedAttributeValue.setId(attributeValue.getId());

        partialUpdatedAttributeValue
            .value(UPDATED_VALUE)
            .customValue(UPDATED_CUSTOM_VALUE)
            .attributeValueGroupName(UPDATED_ATTRIBUTE_VALUE_GROUP_NAME);

        restAttributeValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAttributeValue.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAttributeValue))
            )
            .andExpect(status().isOk());

        // Validate the AttributeValue in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAttributeValueUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAttributeValue, attributeValue),
            getPersistedAttributeValue(attributeValue)
        );
    }

    @Test
    @Transactional
    void fullUpdateAttributeValueWithPatch() throws Exception {
        // Initialize the database
        attributeValueRepository.saveAndFlush(attributeValue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the attributeValue using partial update
        AttributeValue partialUpdatedAttributeValue = new AttributeValue();
        partialUpdatedAttributeValue.setId(attributeValue.getId());

        partialUpdatedAttributeValue
            .value(UPDATED_VALUE)
            .customValue(UPDATED_CUSTOM_VALUE)
            .attributeValueGroupName(UPDATED_ATTRIBUTE_VALUE_GROUP_NAME);

        restAttributeValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAttributeValue.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAttributeValue))
            )
            .andExpect(status().isOk());

        // Validate the AttributeValue in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAttributeValueUpdatableFieldsEquals(partialUpdatedAttributeValue, getPersistedAttributeValue(partialUpdatedAttributeValue));
    }

    @Test
    @Transactional
    void patchNonExistingAttributeValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValue.setId(longCount.incrementAndGet());

        // Create the AttributeValue
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(attributeValue);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttributeValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, attributeValueDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(attributeValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AttributeValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAttributeValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValue.setId(longCount.incrementAndGet());

        // Create the AttributeValue
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(attributeValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttributeValueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(attributeValueDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AttributeValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAttributeValue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValue.setId(longCount.incrementAndGet());

        // Create the AttributeValue
        AttributeValueDTO attributeValueDTO = attributeValueMapper.toDto(attributeValue);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttributeValueMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(attributeValueDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AttributeValue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAttributeValue() throws Exception {
        // Initialize the database
        attributeValueRepository.saveAndFlush(attributeValue);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the attributeValue
        restAttributeValueMockMvc
            .perform(delete(ENTITY_API_URL_ID, attributeValue.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return attributeValueRepository.count();
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

    protected AttributeValue getPersistedAttributeValue(AttributeValue attributeValue) {
        return attributeValueRepository.findById(attributeValue.getId()).orElseThrow();
    }

    protected void assertPersistedAttributeValueToMatchAllProperties(AttributeValue expectedAttributeValue) {
        assertAttributeValueAllPropertiesEquals(expectedAttributeValue, getPersistedAttributeValue(expectedAttributeValue));
    }

    protected void assertPersistedAttributeValueToMatchUpdatableProperties(AttributeValue expectedAttributeValue) {
        assertAttributeValueAllUpdatablePropertiesEquals(expectedAttributeValue, getPersistedAttributeValue(expectedAttributeValue));
    }
}
