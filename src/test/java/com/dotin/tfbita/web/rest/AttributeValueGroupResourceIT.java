package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.AttributeValueGroupAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.AttributeValueGroup;
import com.dotin.tfbita.repository.AttributeValueGroupRepository;
import com.dotin.tfbita.service.dto.AttributeValueGroupDTO;
import com.dotin.tfbita.service.mapper.AttributeValueGroupMapper;
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
 * Integration tests for the {@link AttributeValueGroupResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AttributeValueGroupResourceIT {

    private static final Boolean DEFAULT_MANDATORY = false;
    private static final Boolean UPDATED_MANDATORY = true;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/attribute-value-groups";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AttributeValueGroupRepository attributeValueGroupRepository;

    @Autowired
    private AttributeValueGroupMapper attributeValueGroupMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAttributeValueGroupMockMvc;

    private AttributeValueGroup attributeValueGroup;

    private AttributeValueGroup insertedAttributeValueGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeValueGroup createEntity(EntityManager em) {
        AttributeValueGroup attributeValueGroup = new AttributeValueGroup().mandatory(DEFAULT_MANDATORY).name(DEFAULT_NAME);
        return attributeValueGroup;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AttributeValueGroup createUpdatedEntity(EntityManager em) {
        AttributeValueGroup attributeValueGroup = new AttributeValueGroup().mandatory(UPDATED_MANDATORY).name(UPDATED_NAME);
        return attributeValueGroup;
    }

    @BeforeEach
    public void initTest() {
        attributeValueGroup = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedAttributeValueGroup != null) {
            attributeValueGroupRepository.delete(insertedAttributeValueGroup);
            insertedAttributeValueGroup = null;
        }
    }

    @Test
    @Transactional
    void createAttributeValueGroup() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AttributeValueGroup
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(attributeValueGroup);
        var returnedAttributeValueGroupDTO = om.readValue(
            restAttributeValueGroupMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(attributeValueGroupDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AttributeValueGroupDTO.class
        );

        // Validate the AttributeValueGroup in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedAttributeValueGroup = attributeValueGroupMapper.toEntity(returnedAttributeValueGroupDTO);
        assertAttributeValueGroupUpdatableFieldsEquals(
            returnedAttributeValueGroup,
            getPersistedAttributeValueGroup(returnedAttributeValueGroup)
        );

        insertedAttributeValueGroup = returnedAttributeValueGroup;
    }

    @Test
    @Transactional
    void createAttributeValueGroupWithExistingId() throws Exception {
        // Create the AttributeValueGroup with an existing ID
        attributeValueGroup.setId(1L);
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(attributeValueGroup);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttributeValueGroupMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(attributeValueGroupDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AttributeValueGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAttributeValueGroups() throws Exception {
        // Initialize the database
        insertedAttributeValueGroup = attributeValueGroupRepository.saveAndFlush(attributeValueGroup);

        // Get all the attributeValueGroupList
        restAttributeValueGroupMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attributeValueGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].mandatory").value(hasItem(DEFAULT_MANDATORY.booleanValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getAttributeValueGroup() throws Exception {
        // Initialize the database
        insertedAttributeValueGroup = attributeValueGroupRepository.saveAndFlush(attributeValueGroup);

        // Get the attributeValueGroup
        restAttributeValueGroupMockMvc
            .perform(get(ENTITY_API_URL_ID, attributeValueGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(attributeValueGroup.getId().intValue()))
            .andExpect(jsonPath("$.mandatory").value(DEFAULT_MANDATORY.booleanValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingAttributeValueGroup() throws Exception {
        // Get the attributeValueGroup
        restAttributeValueGroupMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAttributeValueGroup() throws Exception {
        // Initialize the database
        insertedAttributeValueGroup = attributeValueGroupRepository.saveAndFlush(attributeValueGroup);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the attributeValueGroup
        AttributeValueGroup updatedAttributeValueGroup = attributeValueGroupRepository.findById(attributeValueGroup.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAttributeValueGroup are not directly saved in db
        em.detach(updatedAttributeValueGroup);
        updatedAttributeValueGroup.mandatory(UPDATED_MANDATORY).name(UPDATED_NAME);
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(updatedAttributeValueGroup);

        restAttributeValueGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, attributeValueGroupDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(attributeValueGroupDTO))
            )
            .andExpect(status().isOk());

        // Validate the AttributeValueGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAttributeValueGroupToMatchAllProperties(updatedAttributeValueGroup);
    }

    @Test
    @Transactional
    void putNonExistingAttributeValueGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValueGroup.setId(longCount.incrementAndGet());

        // Create the AttributeValueGroup
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(attributeValueGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttributeValueGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, attributeValueGroupDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(attributeValueGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AttributeValueGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAttributeValueGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValueGroup.setId(longCount.incrementAndGet());

        // Create the AttributeValueGroup
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(attributeValueGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttributeValueGroupMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(attributeValueGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AttributeValueGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAttributeValueGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValueGroup.setId(longCount.incrementAndGet());

        // Create the AttributeValueGroup
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(attributeValueGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttributeValueGroupMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(attributeValueGroupDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AttributeValueGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAttributeValueGroupWithPatch() throws Exception {
        // Initialize the database
        insertedAttributeValueGroup = attributeValueGroupRepository.saveAndFlush(attributeValueGroup);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the attributeValueGroup using partial update
        AttributeValueGroup partialUpdatedAttributeValueGroup = new AttributeValueGroup();
        partialUpdatedAttributeValueGroup.setId(attributeValueGroup.getId());

        restAttributeValueGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAttributeValueGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAttributeValueGroup))
            )
            .andExpect(status().isOk());

        // Validate the AttributeValueGroup in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAttributeValueGroupUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAttributeValueGroup, attributeValueGroup),
            getPersistedAttributeValueGroup(attributeValueGroup)
        );
    }

    @Test
    @Transactional
    void fullUpdateAttributeValueGroupWithPatch() throws Exception {
        // Initialize the database
        insertedAttributeValueGroup = attributeValueGroupRepository.saveAndFlush(attributeValueGroup);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the attributeValueGroup using partial update
        AttributeValueGroup partialUpdatedAttributeValueGroup = new AttributeValueGroup();
        partialUpdatedAttributeValueGroup.setId(attributeValueGroup.getId());

        partialUpdatedAttributeValueGroup.mandatory(UPDATED_MANDATORY).name(UPDATED_NAME);

        restAttributeValueGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAttributeValueGroup.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAttributeValueGroup))
            )
            .andExpect(status().isOk());

        // Validate the AttributeValueGroup in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAttributeValueGroupUpdatableFieldsEquals(
            partialUpdatedAttributeValueGroup,
            getPersistedAttributeValueGroup(partialUpdatedAttributeValueGroup)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAttributeValueGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValueGroup.setId(longCount.incrementAndGet());

        // Create the AttributeValueGroup
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(attributeValueGroup);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttributeValueGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, attributeValueGroupDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(attributeValueGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AttributeValueGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAttributeValueGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValueGroup.setId(longCount.incrementAndGet());

        // Create the AttributeValueGroup
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(attributeValueGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttributeValueGroupMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(attributeValueGroupDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AttributeValueGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAttributeValueGroup() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        attributeValueGroup.setId(longCount.incrementAndGet());

        // Create the AttributeValueGroup
        AttributeValueGroupDTO attributeValueGroupDTO = attributeValueGroupMapper.toDto(attributeValueGroup);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAttributeValueGroupMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(attributeValueGroupDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AttributeValueGroup in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAttributeValueGroup() throws Exception {
        // Initialize the database
        insertedAttributeValueGroup = attributeValueGroupRepository.saveAndFlush(attributeValueGroup);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the attributeValueGroup
        restAttributeValueGroupMockMvc
            .perform(delete(ENTITY_API_URL_ID, attributeValueGroup.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return attributeValueGroupRepository.count();
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

    protected AttributeValueGroup getPersistedAttributeValueGroup(AttributeValueGroup attributeValueGroup) {
        return attributeValueGroupRepository.findById(attributeValueGroup.getId()).orElseThrow();
    }

    protected void assertPersistedAttributeValueGroupToMatchAllProperties(AttributeValueGroup expectedAttributeValueGroup) {
        assertAttributeValueGroupAllPropertiesEquals(
            expectedAttributeValueGroup,
            getPersistedAttributeValueGroup(expectedAttributeValueGroup)
        );
    }

    protected void assertPersistedAttributeValueGroupToMatchUpdatableProperties(AttributeValueGroup expectedAttributeValueGroup) {
        assertAttributeValueGroupAllUpdatablePropertiesEquals(
            expectedAttributeValueGroup,
            getPersistedAttributeValueGroup(expectedAttributeValueGroup)
        );
    }
}
