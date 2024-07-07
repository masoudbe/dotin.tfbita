package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DraftRequestTypeAsserts.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DraftRequestType;
import com.dotin.tfbita.repository.DraftRequestTypeRepository;
import com.dotin.tfbita.service.dto.DraftRequestTypeDTO;
import com.dotin.tfbita.service.mapper.DraftRequestTypeMapper;
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
 * Integration tests for the {@link DraftRequestTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DraftRequestTypeResourceIT {

    private static final String ENTITY_API_URL = "/api/draft-request-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DraftRequestTypeRepository draftRequestTypeRepository;

    @Autowired
    private DraftRequestTypeMapper draftRequestTypeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDraftRequestTypeMockMvc;

    private DraftRequestType draftRequestType;

    private DraftRequestType insertedDraftRequestType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftRequestType createEntity(EntityManager em) {
        DraftRequestType draftRequestType = new DraftRequestType();
        return draftRequestType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DraftRequestType createUpdatedEntity(EntityManager em) {
        DraftRequestType draftRequestType = new DraftRequestType();
        return draftRequestType;
    }

    @BeforeEach
    public void initTest() {
        draftRequestType = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDraftRequestType != null) {
            draftRequestTypeRepository.delete(insertedDraftRequestType);
            insertedDraftRequestType = null;
        }
    }

    @Test
    @Transactional
    void createDraftRequestType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DraftRequestType
        DraftRequestTypeDTO draftRequestTypeDTO = draftRequestTypeMapper.toDto(draftRequestType);
        var returnedDraftRequestTypeDTO = om.readValue(
            restDraftRequestTypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftRequestTypeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DraftRequestTypeDTO.class
        );

        // Validate the DraftRequestType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDraftRequestType = draftRequestTypeMapper.toEntity(returnedDraftRequestTypeDTO);
        assertDraftRequestTypeUpdatableFieldsEquals(returnedDraftRequestType, getPersistedDraftRequestType(returnedDraftRequestType));

        insertedDraftRequestType = returnedDraftRequestType;
    }

    @Test
    @Transactional
    void createDraftRequestTypeWithExistingId() throws Exception {
        // Create the DraftRequestType with an existing ID
        draftRequestType.setId(1L);
        DraftRequestTypeDTO draftRequestTypeDTO = draftRequestTypeMapper.toDto(draftRequestType);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDraftRequestTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(draftRequestTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DraftRequestType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDraftRequestTypes() throws Exception {
        // Initialize the database
        insertedDraftRequestType = draftRequestTypeRepository.saveAndFlush(draftRequestType);

        // Get all the draftRequestTypeList
        restDraftRequestTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(draftRequestType.getId().intValue())));
    }

    @Test
    @Transactional
    void getDraftRequestType() throws Exception {
        // Initialize the database
        insertedDraftRequestType = draftRequestTypeRepository.saveAndFlush(draftRequestType);

        // Get the draftRequestType
        restDraftRequestTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, draftRequestType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(draftRequestType.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDraftRequestType() throws Exception {
        // Get the draftRequestType
        restDraftRequestTypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void deleteDraftRequestType() throws Exception {
        // Initialize the database
        insertedDraftRequestType = draftRequestTypeRepository.saveAndFlush(draftRequestType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the draftRequestType
        restDraftRequestTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, draftRequestType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return draftRequestTypeRepository.count();
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

    protected DraftRequestType getPersistedDraftRequestType(DraftRequestType draftRequestType) {
        return draftRequestTypeRepository.findById(draftRequestType.getId()).orElseThrow();
    }

    protected void assertPersistedDraftRequestTypeToMatchAllProperties(DraftRequestType expectedDraftRequestType) {
        assertDraftRequestTypeAllPropertiesEquals(expectedDraftRequestType, getPersistedDraftRequestType(expectedDraftRequestType));
    }

    protected void assertPersistedDraftRequestTypeToMatchUpdatableProperties(DraftRequestType expectedDraftRequestType) {
        assertDraftRequestTypeAllUpdatablePropertiesEquals(
            expectedDraftRequestType,
            getPersistedDraftRequestType(expectedDraftRequestType)
        );
    }
}
