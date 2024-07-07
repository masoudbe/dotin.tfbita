package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.SwiftBicAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.SwiftBic;
import com.dotin.tfbita.repository.SwiftBicRepository;
import com.dotin.tfbita.service.dto.SwiftBicDTO;
import com.dotin.tfbita.service.mapper.SwiftBicMapper;
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
 * Integration tests for the {@link SwiftBicResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SwiftBicResourceIT {

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_4 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK = "AAAAAAAAAA";
    private static final String UPDATED_BANK = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME_2 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME_3 = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME_3 = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH_NAME_2 = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH_NAME_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_TYPE_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_SUB_TYPE_INDICATOR = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP = "AAAAAAAAAA";
    private static final String UPDATED_ZIP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/swift-bics";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SwiftBicRepository swiftBicRepository;

    @Autowired
    private SwiftBicMapper swiftBicMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSwiftBicMockMvc;

    private SwiftBic swiftBic;

    private SwiftBic insertedSwiftBic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SwiftBic createEntity(EntityManager em) {
        SwiftBic swiftBic = new SwiftBic()
            .address(DEFAULT_ADDRESS)
            .address2(DEFAULT_ADDRESS_2)
            .address3(DEFAULT_ADDRESS_3)
            .address4(DEFAULT_ADDRESS_4)
            .bank(DEFAULT_BANK)
            .bankName(DEFAULT_BANK_NAME)
            .bankName2(DEFAULT_BANK_NAME_2)
            .bankName3(DEFAULT_BANK_NAME_3)
            .branch(DEFAULT_BRANCH)
            .branchName(DEFAULT_BRANCH_NAME)
            .branchName2(DEFAULT_BRANCH_NAME_2)
            .city(DEFAULT_CITY)
            .country(DEFAULT_COUNTRY)
            .location(DEFAULT_LOCATION)
            .subTypeIndicator(DEFAULT_SUB_TYPE_INDICATOR)
            .zip(DEFAULT_ZIP);
        return swiftBic;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SwiftBic createUpdatedEntity(EntityManager em) {
        SwiftBic swiftBic = new SwiftBic()
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .address4(UPDATED_ADDRESS_4)
            .bank(UPDATED_BANK)
            .bankName(UPDATED_BANK_NAME)
            .bankName2(UPDATED_BANK_NAME_2)
            .bankName3(UPDATED_BANK_NAME_3)
            .branch(UPDATED_BRANCH)
            .branchName(UPDATED_BRANCH_NAME)
            .branchName2(UPDATED_BRANCH_NAME_2)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .location(UPDATED_LOCATION)
            .subTypeIndicator(UPDATED_SUB_TYPE_INDICATOR)
            .zip(UPDATED_ZIP);
        return swiftBic;
    }

    @BeforeEach
    public void initTest() {
        swiftBic = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSwiftBic != null) {
            swiftBicRepository.delete(insertedSwiftBic);
            insertedSwiftBic = null;
        }
    }

    @Test
    @Transactional
    void createSwiftBic() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SwiftBic
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(swiftBic);
        var returnedSwiftBicDTO = om.readValue(
            restSwiftBicMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(swiftBicDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SwiftBicDTO.class
        );

        // Validate the SwiftBic in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSwiftBic = swiftBicMapper.toEntity(returnedSwiftBicDTO);
        assertSwiftBicUpdatableFieldsEquals(returnedSwiftBic, getPersistedSwiftBic(returnedSwiftBic));

        insertedSwiftBic = returnedSwiftBic;
    }

    @Test
    @Transactional
    void createSwiftBicWithExistingId() throws Exception {
        // Create the SwiftBic with an existing ID
        swiftBic.setId(1L);
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(swiftBic);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSwiftBicMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(swiftBicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SwiftBic in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSwiftBics() throws Exception {
        // Initialize the database
        insertedSwiftBic = swiftBicRepository.saveAndFlush(swiftBic);

        // Get all the swiftBicList
        restSwiftBicMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(swiftBic.getId().intValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].address3").value(hasItem(DEFAULT_ADDRESS_3)))
            .andExpect(jsonPath("$.[*].address4").value(hasItem(DEFAULT_ADDRESS_4)))
            .andExpect(jsonPath("$.[*].bank").value(hasItem(DEFAULT_BANK)))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME)))
            .andExpect(jsonPath("$.[*].bankName2").value(hasItem(DEFAULT_BANK_NAME_2)))
            .andExpect(jsonPath("$.[*].bankName3").value(hasItem(DEFAULT_BANK_NAME_3)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)))
            .andExpect(jsonPath("$.[*].branchName").value(hasItem(DEFAULT_BRANCH_NAME)))
            .andExpect(jsonPath("$.[*].branchName2").value(hasItem(DEFAULT_BRANCH_NAME_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].subTypeIndicator").value(hasItem(DEFAULT_SUB_TYPE_INDICATOR)))
            .andExpect(jsonPath("$.[*].zip").value(hasItem(DEFAULT_ZIP)));
    }

    @Test
    @Transactional
    void getSwiftBic() throws Exception {
        // Initialize the database
        insertedSwiftBic = swiftBicRepository.saveAndFlush(swiftBic);

        // Get the swiftBic
        restSwiftBicMockMvc
            .perform(get(ENTITY_API_URL_ID, swiftBic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(swiftBic.getId().intValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.address3").value(DEFAULT_ADDRESS_3))
            .andExpect(jsonPath("$.address4").value(DEFAULT_ADDRESS_4))
            .andExpect(jsonPath("$.bank").value(DEFAULT_BANK))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME))
            .andExpect(jsonPath("$.bankName2").value(DEFAULT_BANK_NAME_2))
            .andExpect(jsonPath("$.bankName3").value(DEFAULT_BANK_NAME_3))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH))
            .andExpect(jsonPath("$.branchName").value(DEFAULT_BRANCH_NAME))
            .andExpect(jsonPath("$.branchName2").value(DEFAULT_BRANCH_NAME_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.subTypeIndicator").value(DEFAULT_SUB_TYPE_INDICATOR))
            .andExpect(jsonPath("$.zip").value(DEFAULT_ZIP));
    }

    @Test
    @Transactional
    void getNonExistingSwiftBic() throws Exception {
        // Get the swiftBic
        restSwiftBicMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSwiftBic() throws Exception {
        // Initialize the database
        insertedSwiftBic = swiftBicRepository.saveAndFlush(swiftBic);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the swiftBic
        SwiftBic updatedSwiftBic = swiftBicRepository.findById(swiftBic.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSwiftBic are not directly saved in db
        em.detach(updatedSwiftBic);
        updatedSwiftBic
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .address4(UPDATED_ADDRESS_4)
            .bank(UPDATED_BANK)
            .bankName(UPDATED_BANK_NAME)
            .bankName2(UPDATED_BANK_NAME_2)
            .bankName3(UPDATED_BANK_NAME_3)
            .branch(UPDATED_BRANCH)
            .branchName(UPDATED_BRANCH_NAME)
            .branchName2(UPDATED_BRANCH_NAME_2)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .location(UPDATED_LOCATION)
            .subTypeIndicator(UPDATED_SUB_TYPE_INDICATOR)
            .zip(UPDATED_ZIP);
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(updatedSwiftBic);

        restSwiftBicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, swiftBicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(swiftBicDTO))
            )
            .andExpect(status().isOk());

        // Validate the SwiftBic in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSwiftBicToMatchAllProperties(updatedSwiftBic);
    }

    @Test
    @Transactional
    void putNonExistingSwiftBic() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        swiftBic.setId(longCount.incrementAndGet());

        // Create the SwiftBic
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(swiftBic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSwiftBicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, swiftBicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(swiftBicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SwiftBic in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSwiftBic() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        swiftBic.setId(longCount.incrementAndGet());

        // Create the SwiftBic
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(swiftBic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSwiftBicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(swiftBicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SwiftBic in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSwiftBic() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        swiftBic.setId(longCount.incrementAndGet());

        // Create the SwiftBic
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(swiftBic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSwiftBicMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(swiftBicDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SwiftBic in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSwiftBicWithPatch() throws Exception {
        // Initialize the database
        insertedSwiftBic = swiftBicRepository.saveAndFlush(swiftBic);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the swiftBic using partial update
        SwiftBic partialUpdatedSwiftBic = new SwiftBic();
        partialUpdatedSwiftBic.setId(swiftBic.getId());

        partialUpdatedSwiftBic
            .address(UPDATED_ADDRESS)
            .address3(UPDATED_ADDRESS_3)
            .address4(UPDATED_ADDRESS_4)
            .bankName(UPDATED_BANK_NAME)
            .bankName2(UPDATED_BANK_NAME_2)
            .bankName3(UPDATED_BANK_NAME_3)
            .branchName2(UPDATED_BRANCH_NAME_2)
            .country(UPDATED_COUNTRY)
            .subTypeIndicator(UPDATED_SUB_TYPE_INDICATOR)
            .zip(UPDATED_ZIP);

        restSwiftBicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSwiftBic.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSwiftBic))
            )
            .andExpect(status().isOk());

        // Validate the SwiftBic in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSwiftBicUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedSwiftBic, swiftBic), getPersistedSwiftBic(swiftBic));
    }

    @Test
    @Transactional
    void fullUpdateSwiftBicWithPatch() throws Exception {
        // Initialize the database
        insertedSwiftBic = swiftBicRepository.saveAndFlush(swiftBic);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the swiftBic using partial update
        SwiftBic partialUpdatedSwiftBic = new SwiftBic();
        partialUpdatedSwiftBic.setId(swiftBic.getId());

        partialUpdatedSwiftBic
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .address4(UPDATED_ADDRESS_4)
            .bank(UPDATED_BANK)
            .bankName(UPDATED_BANK_NAME)
            .bankName2(UPDATED_BANK_NAME_2)
            .bankName3(UPDATED_BANK_NAME_3)
            .branch(UPDATED_BRANCH)
            .branchName(UPDATED_BRANCH_NAME)
            .branchName2(UPDATED_BRANCH_NAME_2)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .location(UPDATED_LOCATION)
            .subTypeIndicator(UPDATED_SUB_TYPE_INDICATOR)
            .zip(UPDATED_ZIP);

        restSwiftBicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSwiftBic.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSwiftBic))
            )
            .andExpect(status().isOk());

        // Validate the SwiftBic in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSwiftBicUpdatableFieldsEquals(partialUpdatedSwiftBic, getPersistedSwiftBic(partialUpdatedSwiftBic));
    }

    @Test
    @Transactional
    void patchNonExistingSwiftBic() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        swiftBic.setId(longCount.incrementAndGet());

        // Create the SwiftBic
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(swiftBic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSwiftBicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, swiftBicDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(swiftBicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SwiftBic in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSwiftBic() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        swiftBic.setId(longCount.incrementAndGet());

        // Create the SwiftBic
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(swiftBic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSwiftBicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(swiftBicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SwiftBic in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSwiftBic() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        swiftBic.setId(longCount.incrementAndGet());

        // Create the SwiftBic
        SwiftBicDTO swiftBicDTO = swiftBicMapper.toDto(swiftBic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSwiftBicMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(swiftBicDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SwiftBic in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSwiftBic() throws Exception {
        // Initialize the database
        insertedSwiftBic = swiftBicRepository.saveAndFlush(swiftBic);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the swiftBic
        restSwiftBicMockMvc
            .perform(delete(ENTITY_API_URL_ID, swiftBic.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return swiftBicRepository.count();
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

    protected SwiftBic getPersistedSwiftBic(SwiftBic swiftBic) {
        return swiftBicRepository.findById(swiftBic.getId()).orElseThrow();
    }

    protected void assertPersistedSwiftBicToMatchAllProperties(SwiftBic expectedSwiftBic) {
        assertSwiftBicAllPropertiesEquals(expectedSwiftBic, getPersistedSwiftBic(expectedSwiftBic));
    }

    protected void assertPersistedSwiftBicToMatchUpdatableProperties(SwiftBic expectedSwiftBic) {
        assertSwiftBicAllUpdatablePropertiesEquals(expectedSwiftBic, getPersistedSwiftBic(expectedSwiftBic));
    }
}
