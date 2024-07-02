package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.TypeAttributeAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.TypeAttribute;
import com.dotin.tfbita.repository.TypeAttributeRepository;
import com.dotin.tfbita.service.dto.TypeAttributeDTO;
import com.dotin.tfbita.service.mapper.TypeAttributeMapper;
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
 * Integration tests for the {@link TypeAttributeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TypeAttributeResourceIT {

    private static final Boolean DEFAULT_NECESSARY = false;
    private static final Boolean UPDATED_NECESSARY = true;

    private static final Boolean DEFAULT_IS_UNIQUE = false;
    private static final Boolean UPDATED_IS_UNIQUE = true;

    private static final String ENTITY_API_URL = "/api/type-attributes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TypeAttributeRepository typeAttributeRepository;

    @Autowired
    private TypeAttributeMapper typeAttributeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeAttributeMockMvc;

    private TypeAttribute typeAttribute;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAttribute createEntity(EntityManager em) {
        TypeAttribute typeAttribute = new TypeAttribute().necessary(DEFAULT_NECESSARY).isUnique(DEFAULT_IS_UNIQUE);
        return typeAttribute;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeAttribute createUpdatedEntity(EntityManager em) {
        TypeAttribute typeAttribute = new TypeAttribute().necessary(UPDATED_NECESSARY).isUnique(UPDATED_IS_UNIQUE);
        return typeAttribute;
    }

    @BeforeEach
    public void initTest() {
        typeAttribute = createEntity(em);
    }

    @Test
    @Transactional
    void createTypeAttribute() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TypeAttribute
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(typeAttribute);
        var returnedTypeAttributeDTO = om.readValue(
            restTypeAttributeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(typeAttributeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TypeAttributeDTO.class
        );

        // Validate the TypeAttribute in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedTypeAttribute = typeAttributeMapper.toEntity(returnedTypeAttributeDTO);
        assertTypeAttributeUpdatableFieldsEquals(returnedTypeAttribute, getPersistedTypeAttribute(returnedTypeAttribute));
    }

    @Test
    @Transactional
    void createTypeAttributeWithExistingId() throws Exception {
        // Create the TypeAttribute with an existing ID
        typeAttribute.setId(1L);
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(typeAttribute);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeAttributeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(typeAttributeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeAttribute in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTypeAttributes() throws Exception {
        // Initialize the database
        typeAttributeRepository.saveAndFlush(typeAttribute);

        // Get all the typeAttributeList
        restTypeAttributeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeAttribute.getId().intValue())))
            .andExpect(jsonPath("$.[*].necessary").value(hasItem(DEFAULT_NECESSARY.booleanValue())))
            .andExpect(jsonPath("$.[*].isUnique").value(hasItem(DEFAULT_IS_UNIQUE.booleanValue())));
    }

    @Test
    @Transactional
    void getTypeAttribute() throws Exception {
        // Initialize the database
        typeAttributeRepository.saveAndFlush(typeAttribute);

        // Get the typeAttribute
        restTypeAttributeMockMvc
            .perform(get(ENTITY_API_URL_ID, typeAttribute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeAttribute.getId().intValue()))
            .andExpect(jsonPath("$.necessary").value(DEFAULT_NECESSARY.booleanValue()))
            .andExpect(jsonPath("$.isUnique").value(DEFAULT_IS_UNIQUE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTypeAttribute() throws Exception {
        // Get the typeAttribute
        restTypeAttributeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTypeAttribute() throws Exception {
        // Initialize the database
        typeAttributeRepository.saveAndFlush(typeAttribute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the typeAttribute
        TypeAttribute updatedTypeAttribute = typeAttributeRepository.findById(typeAttribute.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTypeAttribute are not directly saved in db
        em.detach(updatedTypeAttribute);
        updatedTypeAttribute.necessary(UPDATED_NECESSARY).isUnique(UPDATED_IS_UNIQUE);
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(updatedTypeAttribute);

        restTypeAttributeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, typeAttributeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(typeAttributeDTO))
            )
            .andExpect(status().isOk());

        // Validate the TypeAttribute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTypeAttributeToMatchAllProperties(updatedTypeAttribute);
    }

    @Test
    @Transactional
    void putNonExistingTypeAttribute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        typeAttribute.setId(longCount.incrementAndGet());

        // Create the TypeAttribute
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(typeAttribute);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeAttributeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, typeAttributeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(typeAttributeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypeAttribute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTypeAttribute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        typeAttribute.setId(longCount.incrementAndGet());

        // Create the TypeAttribute
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(typeAttribute);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTypeAttributeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(typeAttributeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypeAttribute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTypeAttribute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        typeAttribute.setId(longCount.incrementAndGet());

        // Create the TypeAttribute
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(typeAttribute);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTypeAttributeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(typeAttributeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TypeAttribute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTypeAttributeWithPatch() throws Exception {
        // Initialize the database
        typeAttributeRepository.saveAndFlush(typeAttribute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the typeAttribute using partial update
        TypeAttribute partialUpdatedTypeAttribute = new TypeAttribute();
        partialUpdatedTypeAttribute.setId(typeAttribute.getId());

        partialUpdatedTypeAttribute.necessary(UPDATED_NECESSARY).isUnique(UPDATED_IS_UNIQUE);

        restTypeAttributeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTypeAttribute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTypeAttribute))
            )
            .andExpect(status().isOk());

        // Validate the TypeAttribute in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTypeAttributeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTypeAttribute, typeAttribute),
            getPersistedTypeAttribute(typeAttribute)
        );
    }

    @Test
    @Transactional
    void fullUpdateTypeAttributeWithPatch() throws Exception {
        // Initialize the database
        typeAttributeRepository.saveAndFlush(typeAttribute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the typeAttribute using partial update
        TypeAttribute partialUpdatedTypeAttribute = new TypeAttribute();
        partialUpdatedTypeAttribute.setId(typeAttribute.getId());

        partialUpdatedTypeAttribute.necessary(UPDATED_NECESSARY).isUnique(UPDATED_IS_UNIQUE);

        restTypeAttributeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTypeAttribute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTypeAttribute))
            )
            .andExpect(status().isOk());

        // Validate the TypeAttribute in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTypeAttributeUpdatableFieldsEquals(partialUpdatedTypeAttribute, getPersistedTypeAttribute(partialUpdatedTypeAttribute));
    }

    @Test
    @Transactional
    void patchNonExistingTypeAttribute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        typeAttribute.setId(longCount.incrementAndGet());

        // Create the TypeAttribute
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(typeAttribute);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeAttributeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, typeAttributeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(typeAttributeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypeAttribute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTypeAttribute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        typeAttribute.setId(longCount.incrementAndGet());

        // Create the TypeAttribute
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(typeAttribute);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTypeAttributeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(typeAttributeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypeAttribute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTypeAttribute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        typeAttribute.setId(longCount.incrementAndGet());

        // Create the TypeAttribute
        TypeAttributeDTO typeAttributeDTO = typeAttributeMapper.toDto(typeAttribute);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTypeAttributeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(typeAttributeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TypeAttribute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTypeAttribute() throws Exception {
        // Initialize the database
        typeAttributeRepository.saveAndFlush(typeAttribute);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the typeAttribute
        restTypeAttributeMockMvc
            .perform(delete(ENTITY_API_URL_ID, typeAttribute.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return typeAttributeRepository.count();
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

    protected TypeAttribute getPersistedTypeAttribute(TypeAttribute typeAttribute) {
        return typeAttributeRepository.findById(typeAttribute.getId()).orElseThrow();
    }

    protected void assertPersistedTypeAttributeToMatchAllProperties(TypeAttribute expectedTypeAttribute) {
        assertTypeAttributeAllPropertiesEquals(expectedTypeAttribute, getPersistedTypeAttribute(expectedTypeAttribute));
    }

    protected void assertPersistedTypeAttributeToMatchUpdatableProperties(TypeAttribute expectedTypeAttribute) {
        assertTypeAttributeAllUpdatablePropertiesEquals(expectedTypeAttribute, getPersistedTypeAttribute(expectedTypeAttribute));
    }
}
