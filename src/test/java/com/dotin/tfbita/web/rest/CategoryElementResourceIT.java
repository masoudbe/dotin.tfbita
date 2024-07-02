package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.CategoryElementAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.repository.CategoryElementRepository;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.mapper.CategoryElementMapper;
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
 * Integration tests for the {@link CategoryElementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CategoryElementResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/category-elements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CategoryElementRepository categoryElementRepository;

    @Autowired
    private CategoryElementMapper categoryElementMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategoryElementMockMvc;

    private CategoryElement categoryElement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryElement createEntity(EntityManager em) {
        CategoryElement categoryElement = new CategoryElement().value(DEFAULT_VALUE).categoryName(DEFAULT_CATEGORY_NAME).code(DEFAULT_CODE);
        return categoryElement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategoryElement createUpdatedEntity(EntityManager em) {
        CategoryElement categoryElement = new CategoryElement().value(UPDATED_VALUE).categoryName(UPDATED_CATEGORY_NAME).code(UPDATED_CODE);
        return categoryElement;
    }

    @BeforeEach
    public void initTest() {
        categoryElement = createEntity(em);
    }

    @Test
    @Transactional
    void createCategoryElement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CategoryElement
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(categoryElement);
        var returnedCategoryElementDTO = om.readValue(
            restCategoryElementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(categoryElementDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CategoryElementDTO.class
        );

        // Validate the CategoryElement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCategoryElement = categoryElementMapper.toEntity(returnedCategoryElementDTO);
        assertCategoryElementUpdatableFieldsEquals(returnedCategoryElement, getPersistedCategoryElement(returnedCategoryElement));
    }

    @Test
    @Transactional
    void createCategoryElementWithExistingId() throws Exception {
        // Create the CategoryElement with an existing ID
        categoryElement.setId(1L);
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(categoryElement);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategoryElementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(categoryElementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCategoryElements() throws Exception {
        // Initialize the database
        categoryElementRepository.saveAndFlush(categoryElement);

        // Get all the categoryElementList
        restCategoryElementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryElement.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }

    @Test
    @Transactional
    void getCategoryElement() throws Exception {
        // Initialize the database
        categoryElementRepository.saveAndFlush(categoryElement);

        // Get the categoryElement
        restCategoryElementMockMvc
            .perform(get(ENTITY_API_URL_ID, categoryElement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categoryElement.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }

    @Test
    @Transactional
    void getNonExistingCategoryElement() throws Exception {
        // Get the categoryElement
        restCategoryElementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCategoryElement() throws Exception {
        // Initialize the database
        categoryElementRepository.saveAndFlush(categoryElement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the categoryElement
        CategoryElement updatedCategoryElement = categoryElementRepository.findById(categoryElement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCategoryElement are not directly saved in db
        em.detach(updatedCategoryElement);
        updatedCategoryElement.value(UPDATED_VALUE).categoryName(UPDATED_CATEGORY_NAME).code(UPDATED_CODE);
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(updatedCategoryElement);

        restCategoryElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, categoryElementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(categoryElementDTO))
            )
            .andExpect(status().isOk());

        // Validate the CategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCategoryElementToMatchAllProperties(updatedCategoryElement);
    }

    @Test
    @Transactional
    void putNonExistingCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        categoryElement.setId(longCount.incrementAndGet());

        // Create the CategoryElement
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(categoryElement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, categoryElementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(categoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        categoryElement.setId(longCount.incrementAndGet());

        // Create the CategoryElement
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(categoryElement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(categoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        categoryElement.setId(longCount.incrementAndGet());

        // Create the CategoryElement
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(categoryElement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryElementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(categoryElementDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCategoryElementWithPatch() throws Exception {
        // Initialize the database
        categoryElementRepository.saveAndFlush(categoryElement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the categoryElement using partial update
        CategoryElement partialUpdatedCategoryElement = new CategoryElement();
        partialUpdatedCategoryElement.setId(categoryElement.getId());

        partialUpdatedCategoryElement.value(UPDATED_VALUE).code(UPDATED_CODE);

        restCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCategoryElement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCategoryElement))
            )
            .andExpect(status().isOk());

        // Validate the CategoryElement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCategoryElementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCategoryElement, categoryElement),
            getPersistedCategoryElement(categoryElement)
        );
    }

    @Test
    @Transactional
    void fullUpdateCategoryElementWithPatch() throws Exception {
        // Initialize the database
        categoryElementRepository.saveAndFlush(categoryElement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the categoryElement using partial update
        CategoryElement partialUpdatedCategoryElement = new CategoryElement();
        partialUpdatedCategoryElement.setId(categoryElement.getId());

        partialUpdatedCategoryElement.value(UPDATED_VALUE).categoryName(UPDATED_CATEGORY_NAME).code(UPDATED_CODE);

        restCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCategoryElement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCategoryElement))
            )
            .andExpect(status().isOk());

        // Validate the CategoryElement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCategoryElementUpdatableFieldsEquals(
            partialUpdatedCategoryElement,
            getPersistedCategoryElement(partialUpdatedCategoryElement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        categoryElement.setId(longCount.incrementAndGet());

        // Create the CategoryElement
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(categoryElement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, categoryElementDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(categoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        categoryElement.setId(longCount.incrementAndGet());

        // Create the CategoryElement
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(categoryElement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(categoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        categoryElement.setId(longCount.incrementAndGet());

        // Create the CategoryElement
        CategoryElementDTO categoryElementDTO = categoryElementMapper.toDto(categoryElement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCategoryElementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(categoryElementDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCategoryElement() throws Exception {
        // Initialize the database
        categoryElementRepository.saveAndFlush(categoryElement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the categoryElement
        restCategoryElementMockMvc
            .perform(delete(ENTITY_API_URL_ID, categoryElement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return categoryElementRepository.count();
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

    protected CategoryElement getPersistedCategoryElement(CategoryElement categoryElement) {
        return categoryElementRepository.findById(categoryElement.getId()).orElseThrow();
    }

    protected void assertPersistedCategoryElementToMatchAllProperties(CategoryElement expectedCategoryElement) {
        assertCategoryElementAllPropertiesEquals(expectedCategoryElement, getPersistedCategoryElement(expectedCategoryElement));
    }

    protected void assertPersistedCategoryElementToMatchUpdatableProperties(CategoryElement expectedCategoryElement) {
        assertCategoryElementAllUpdatablePropertiesEquals(expectedCategoryElement, getPersistedCategoryElement(expectedCategoryElement));
    }
}
