package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.ObjectiveCategoryElementAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.ObjectiveCategoryElement;
import com.dotin.tfbita.repository.ObjectiveCategoryElementRepository;
import com.dotin.tfbita.service.dto.ObjectiveCategoryElementDTO;
import com.dotin.tfbita.service.mapper.ObjectiveCategoryElementMapper;
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
 * Integration tests for the {@link ObjectiveCategoryElementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ObjectiveCategoryElementResourceIT {

    private static final String DEFAULT_ENTITY_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_CLASS = "BBBBBBBBBB";

    private static final Long DEFAULT_ENTITY_ID = 1L;
    private static final Long UPDATED_ENTITY_ID = 2L;

    private static final String ENTITY_API_URL = "/api/objective-category-elements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ObjectiveCategoryElementRepository objectiveCategoryElementRepository;

    @Autowired
    private ObjectiveCategoryElementMapper objectiveCategoryElementMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restObjectiveCategoryElementMockMvc;

    private ObjectiveCategoryElement objectiveCategoryElement;

    private ObjectiveCategoryElement insertedObjectiveCategoryElement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjectiveCategoryElement createEntity(EntityManager em) {
        ObjectiveCategoryElement objectiveCategoryElement = new ObjectiveCategoryElement()
            .entityClass(DEFAULT_ENTITY_CLASS)
            .entityId(DEFAULT_ENTITY_ID);
        return objectiveCategoryElement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ObjectiveCategoryElement createUpdatedEntity(EntityManager em) {
        ObjectiveCategoryElement objectiveCategoryElement = new ObjectiveCategoryElement()
            .entityClass(UPDATED_ENTITY_CLASS)
            .entityId(UPDATED_ENTITY_ID);
        return objectiveCategoryElement;
    }

    @BeforeEach
    public void initTest() {
        objectiveCategoryElement = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedObjectiveCategoryElement != null) {
            objectiveCategoryElementRepository.delete(insertedObjectiveCategoryElement);
            insertedObjectiveCategoryElement = null;
        }
    }

    @Test
    @Transactional
    void createObjectiveCategoryElement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ObjectiveCategoryElement
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(objectiveCategoryElement);
        var returnedObjectiveCategoryElementDTO = om.readValue(
            restObjectiveCategoryElementMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(objectiveCategoryElementDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ObjectiveCategoryElementDTO.class
        );

        // Validate the ObjectiveCategoryElement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedObjectiveCategoryElement = objectiveCategoryElementMapper.toEntity(returnedObjectiveCategoryElementDTO);
        assertObjectiveCategoryElementUpdatableFieldsEquals(
            returnedObjectiveCategoryElement,
            getPersistedObjectiveCategoryElement(returnedObjectiveCategoryElement)
        );

        insertedObjectiveCategoryElement = returnedObjectiveCategoryElement;
    }

    @Test
    @Transactional
    void createObjectiveCategoryElementWithExistingId() throws Exception {
        // Create the ObjectiveCategoryElement with an existing ID
        objectiveCategoryElement.setId(1L);
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(objectiveCategoryElement);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjectiveCategoryElementMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(objectiveCategoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllObjectiveCategoryElements() throws Exception {
        // Initialize the database
        insertedObjectiveCategoryElement = objectiveCategoryElementRepository.saveAndFlush(objectiveCategoryElement);

        // Get all the objectiveCategoryElementList
        restObjectiveCategoryElementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objectiveCategoryElement.getId().intValue())))
            .andExpect(jsonPath("$.[*].entityClass").value(hasItem(DEFAULT_ENTITY_CLASS)))
            .andExpect(jsonPath("$.[*].entityId").value(hasItem(DEFAULT_ENTITY_ID.intValue())));
    }

    @Test
    @Transactional
    void getObjectiveCategoryElement() throws Exception {
        // Initialize the database
        insertedObjectiveCategoryElement = objectiveCategoryElementRepository.saveAndFlush(objectiveCategoryElement);

        // Get the objectiveCategoryElement
        restObjectiveCategoryElementMockMvc
            .perform(get(ENTITY_API_URL_ID, objectiveCategoryElement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(objectiveCategoryElement.getId().intValue()))
            .andExpect(jsonPath("$.entityClass").value(DEFAULT_ENTITY_CLASS))
            .andExpect(jsonPath("$.entityId").value(DEFAULT_ENTITY_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingObjectiveCategoryElement() throws Exception {
        // Get the objectiveCategoryElement
        restObjectiveCategoryElementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingObjectiveCategoryElement() throws Exception {
        // Initialize the database
        insertedObjectiveCategoryElement = objectiveCategoryElementRepository.saveAndFlush(objectiveCategoryElement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the objectiveCategoryElement
        ObjectiveCategoryElement updatedObjectiveCategoryElement = objectiveCategoryElementRepository
            .findById(objectiveCategoryElement.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedObjectiveCategoryElement are not directly saved in db
        em.detach(updatedObjectiveCategoryElement);
        updatedObjectiveCategoryElement.entityClass(UPDATED_ENTITY_CLASS).entityId(UPDATED_ENTITY_ID);
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(updatedObjectiveCategoryElement);

        restObjectiveCategoryElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, objectiveCategoryElementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(objectiveCategoryElementDTO))
            )
            .andExpect(status().isOk());

        // Validate the ObjectiveCategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedObjectiveCategoryElementToMatchAllProperties(updatedObjectiveCategoryElement);
    }

    @Test
    @Transactional
    void putNonExistingObjectiveCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategoryElement.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategoryElement
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(objectiveCategoryElement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectiveCategoryElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, objectiveCategoryElementDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(objectiveCategoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchObjectiveCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategoryElement.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategoryElement
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(objectiveCategoryElement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectiveCategoryElementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(objectiveCategoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamObjectiveCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategoryElement.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategoryElement
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(objectiveCategoryElement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectiveCategoryElementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(objectiveCategoryElementDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ObjectiveCategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateObjectiveCategoryElementWithPatch() throws Exception {
        // Initialize the database
        insertedObjectiveCategoryElement = objectiveCategoryElementRepository.saveAndFlush(objectiveCategoryElement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the objectiveCategoryElement using partial update
        ObjectiveCategoryElement partialUpdatedObjectiveCategoryElement = new ObjectiveCategoryElement();
        partialUpdatedObjectiveCategoryElement.setId(objectiveCategoryElement.getId());

        restObjectiveCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedObjectiveCategoryElement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedObjectiveCategoryElement))
            )
            .andExpect(status().isOk());

        // Validate the ObjectiveCategoryElement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertObjectiveCategoryElementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedObjectiveCategoryElement, objectiveCategoryElement),
            getPersistedObjectiveCategoryElement(objectiveCategoryElement)
        );
    }

    @Test
    @Transactional
    void fullUpdateObjectiveCategoryElementWithPatch() throws Exception {
        // Initialize the database
        insertedObjectiveCategoryElement = objectiveCategoryElementRepository.saveAndFlush(objectiveCategoryElement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the objectiveCategoryElement using partial update
        ObjectiveCategoryElement partialUpdatedObjectiveCategoryElement = new ObjectiveCategoryElement();
        partialUpdatedObjectiveCategoryElement.setId(objectiveCategoryElement.getId());

        partialUpdatedObjectiveCategoryElement.entityClass(UPDATED_ENTITY_CLASS).entityId(UPDATED_ENTITY_ID);

        restObjectiveCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedObjectiveCategoryElement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedObjectiveCategoryElement))
            )
            .andExpect(status().isOk());

        // Validate the ObjectiveCategoryElement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertObjectiveCategoryElementUpdatableFieldsEquals(
            partialUpdatedObjectiveCategoryElement,
            getPersistedObjectiveCategoryElement(partialUpdatedObjectiveCategoryElement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingObjectiveCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategoryElement.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategoryElement
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(objectiveCategoryElement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjectiveCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, objectiveCategoryElementDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(objectiveCategoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchObjectiveCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategoryElement.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategoryElement
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(objectiveCategoryElement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectiveCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(objectiveCategoryElementDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ObjectiveCategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamObjectiveCategoryElement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        objectiveCategoryElement.setId(longCount.incrementAndGet());

        // Create the ObjectiveCategoryElement
        ObjectiveCategoryElementDTO objectiveCategoryElementDTO = objectiveCategoryElementMapper.toDto(objectiveCategoryElement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restObjectiveCategoryElementMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(objectiveCategoryElementDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ObjectiveCategoryElement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteObjectiveCategoryElement() throws Exception {
        // Initialize the database
        insertedObjectiveCategoryElement = objectiveCategoryElementRepository.saveAndFlush(objectiveCategoryElement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the objectiveCategoryElement
        restObjectiveCategoryElementMockMvc
            .perform(delete(ENTITY_API_URL_ID, objectiveCategoryElement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return objectiveCategoryElementRepository.count();
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

    protected ObjectiveCategoryElement getPersistedObjectiveCategoryElement(ObjectiveCategoryElement objectiveCategoryElement) {
        return objectiveCategoryElementRepository.findById(objectiveCategoryElement.getId()).orElseThrow();
    }

    protected void assertPersistedObjectiveCategoryElementToMatchAllProperties(ObjectiveCategoryElement expectedObjectiveCategoryElement) {
        assertObjectiveCategoryElementAllPropertiesEquals(
            expectedObjectiveCategoryElement,
            getPersistedObjectiveCategoryElement(expectedObjectiveCategoryElement)
        );
    }

    protected void assertPersistedObjectiveCategoryElementToMatchUpdatableProperties(
        ObjectiveCategoryElement expectedObjectiveCategoryElement
    ) {
        assertObjectiveCategoryElementAllUpdatablePropertiesEquals(
            expectedObjectiveCategoryElement,
            getPersistedObjectiveCategoryElement(expectedObjectiveCategoryElement)
        );
    }
}
