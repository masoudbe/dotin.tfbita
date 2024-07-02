package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.ObjectiveCategoryAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.ObjectiveCategory;
import com.dotin.tfbita.repository.ObjectiveCategoryRepository;
import com.dotin.tfbita.service.dto.ObjectiveCategoryDTO;
import com.dotin.tfbita.service.mapper.ObjectiveCategoryMapper;
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
 * Integration tests for the {@link ObjectiveCategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ObjectiveCategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/objective-categories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ObjectiveCategoryRepository objectiveCategoryRepository;

    @Autowired
    private ObjectiveCategoryMapper objectiveCategoryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjectiveCategoryMockMvc;

    private ObjectiveCategory objectiveCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjectiveCategory createEntity(EntityManager em) {
        ObjectiveCategory objectiveCategory = new ObjectiveCategory().name(DEFAULT_NAME);
        return objectiveCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjectiveCategory createUpdatedEntity(EntityManager em) {
        ObjectiveCategory objectiveCategory = new ObjectiveCategory().name(UPDATED_NAME);
        return objectiveCategory;
    }

    @BeforeEach
    public void initTest() {
        objectiveCategory = createEntity(em);
    }

    @Test
    @Transactional
    void createObjectiveCategory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ObjectiveCategory
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(objectiveCategory);
        var returnedObjectiveCategoryDTO = om.readValue(
            restObjectiveCategoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(objectiveCategoryDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ObjectiveCategoryDTO.class
        );

        // Validate the ObjectiveCategory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedObjectiveCategory = objectiveCategoryMapper.toEntity(returnedObjectiveCategoryDTO);
        assertObjectiveCategoryUpdatableFieldsEquals(returnedObjectiveCategory, getPersistedObjectiveCategory(returnedObjectiveCategory));
    }

    @Test
    @Transactional
    void createObjectiveCategoryWithExistingId() throws Exception {
        // Create the ObjectiveCategory with an existing ID
        objectiveCategory.setId(1L);
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(objectiveCategory);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjectiveCategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(objectiveCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllObjectiveCategories() throws Exception {
        // Initialize the database
        objectiveCategoryRepository.saveAndFlush(objectiveCategory);

        // Get all the objectiveCategoryList
        restObjectiveCategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objectiveCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getObjectiveCategory() throws Exception {
        // Initialize the database
        objectiveCategoryRepository.saveAndFlush(objectiveCategory);

        // Get the objectiveCategory
        restObjectiveCategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, objectiveCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(objectiveCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingObjectiveCategory() throws Exception {
        // Get the objectiveCategory
        restObjectiveCategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingObjectiveCategory() throws Exception {
        // Initialize the database
        objectiveCategoryRepository.saveAndFlush(objectiveCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the objectiveCategory
        ObjectiveCategory updatedObjectiveCategory = objectiveCategoryRepository.findById(objectiveCategory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedObjectiveCategory are not directly saved in db
        em.detach(updatedObjectiveCategory);
        updatedObjectiveCategory.name(UPDATED_NAME);
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(updatedObjectiveCategory);

        restObjectiveCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, objectiveCategoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(objectiveCategoryDTO))
            )
            .andExpect(status().isOk());

        // Validate the ObjectiveCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedObjectiveCategoryToMatchAllProperties(updatedObjectiveCategory);
    }

    @Test
    @Transactional
    void putNonExistingObjectiveCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategory.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategory
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(objectiveCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectiveCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, objectiveCategoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(objectiveCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchObjectiveCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategory.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategory
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(objectiveCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectiveCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(objectiveCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamObjectiveCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategory.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategory
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(objectiveCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectiveCategoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(objectiveCategoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ObjectiveCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateObjectiveCategoryWithPatch() throws Exception {
        // Initialize the database
        objectiveCategoryRepository.saveAndFlush(objectiveCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the objectiveCategory using partial update
        ObjectiveCategory partialUpdatedObjectiveCategory = new ObjectiveCategory();
        partialUpdatedObjectiveCategory.setId(objectiveCategory.getId());

        restObjectiveCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedObjectiveCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedObjectiveCategory))
            )
            .andExpect(status().isOk());

        // Validate the ObjectiveCategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertObjectiveCategoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedObjectiveCategory, objectiveCategory),
            getPersistedObjectiveCategory(objectiveCategory)
        );
    }

    @Test
    @Transactional
    void fullUpdateObjectiveCategoryWithPatch() throws Exception {
        // Initialize the database
        objectiveCategoryRepository.saveAndFlush(objectiveCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the objectiveCategory using partial update
        ObjectiveCategory partialUpdatedObjectiveCategory = new ObjectiveCategory();
        partialUpdatedObjectiveCategory.setId(objectiveCategory.getId());

        partialUpdatedObjectiveCategory.name(UPDATED_NAME);

        restObjectiveCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedObjectiveCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedObjectiveCategory))
            )
            .andExpect(status().isOk());

        // Validate the ObjectiveCategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertObjectiveCategoryUpdatableFieldsEquals(
            partialUpdatedObjectiveCategory,
            getPersistedObjectiveCategory(partialUpdatedObjectiveCategory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingObjectiveCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategory.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategory
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(objectiveCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectiveCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, objectiveCategoryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(objectiveCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchObjectiveCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategory.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategory
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(objectiveCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectiveCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(objectiveCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamObjectiveCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategory.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategory
        ObjectiveCategoryDTO objectiveCategoryDTO = objectiveCategoryMapper.toDto(objectiveCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectiveCategoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(objectiveCategoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ObjectiveCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteObjectiveCategory() throws Exception {
        // Initialize the database
        objectiveCategoryRepository.saveAndFlush(objectiveCategory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the objectiveCategory
        restObjectiveCategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, objectiveCategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return objectiveCategoryRepository.count();
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

    protected ObjectiveCategory getPersistedObjectiveCategory(ObjectiveCategory objectiveCategory) {
        return objectiveCategoryRepository.findById(objectiveCategory.getId()).orElseThrow();
    }

    protected void assertPersistedObjectiveCategoryToMatchAllProperties(ObjectiveCategory expectedObjectiveCategory) {
        assertObjectiveCategoryAllPropertiesEquals(expectedObjectiveCategory, getPersistedObjectiveCategory(expectedObjectiveCategory));
    }

    protected void assertPersistedObjectiveCategoryToMatchUpdatableProperties(ObjectiveCategory expectedObjectiveCategory) {
        assertObjectiveCategoryAllUpdatablePropertiesEquals(
            expectedObjectiveCategory,
            getPersistedObjectiveCategory(expectedObjectiveCategory)
        );
    }
}
