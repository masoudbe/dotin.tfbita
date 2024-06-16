package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.AuditCompanyInfoAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.AuditCompanyInfo;
import com.dotin.tfbita.repository.AuditCompanyInfoRepository;
import com.dotin.tfbita.service.dto.AuditCompanyInfoDTO;
import com.dotin.tfbita.service.mapper.AuditCompanyInfoMapper;
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
 * Integration tests for the {@link AuditCompanyInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AuditCompanyInfoResourceIT {

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_BAR_CODES = "AAAAAAAAAA";
    private static final String UPDATED_BAR_CODES = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_REGISTRATION = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_REGISTRATION = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_FLOOR = "AAAAAAAAAA";
    private static final String UPDATED_FLOOR = "BBBBBBBBBB";

    private static final String DEFAULT_INERNATIONALOBSERVIATION = "AAAAAAAAAA";
    private static final String UPDATED_INERNATIONALOBSERVIATION = "BBBBBBBBBB";

    private static final String DEFAULT_MAIN_STREET = "AAAAAAAAAA";
    private static final String UPDATED_MAIN_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_PLAQUE = "AAAAAAAAAA";
    private static final String UPDATED_PLAQUE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final Long DEFAULT_TEMP_ID = 1L;
    private static final Long UPDATED_TEMP_ID = 2L;

    private static final String DEFAULT_THE_SIDE_STREET = "AAAAAAAAAA";
    private static final String UPDATED_THE_SIDE_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/audit-company-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AuditCompanyInfoRepository auditCompanyInfoRepository;

    @Autowired
    private AuditCompanyInfoMapper auditCompanyInfoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuditCompanyInfoMockMvc;

    private AuditCompanyInfo auditCompanyInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditCompanyInfo createEntity(EntityManager em) {
        AuditCompanyInfo auditCompanyInfo = new AuditCompanyInfo()
            .address(DEFAULT_ADDRESS)
            .barCodes(DEFAULT_BAR_CODES)
            .dateOfRegistration(DEFAULT_DATE_OF_REGISTRATION)
            .fax(DEFAULT_FAX)
            .floor(DEFAULT_FLOOR)
            .inernationalobserviation(DEFAULT_INERNATIONALOBSERVIATION)
            .mainStreet(DEFAULT_MAIN_STREET)
            .mobile(DEFAULT_MOBILE)
            .plaque(DEFAULT_PLAQUE)
            .postalCode(DEFAULT_POSTAL_CODE)
            .registrationNumber(DEFAULT_REGISTRATION_NUMBER)
            .telephone(DEFAULT_TELEPHONE)
            .tempId(DEFAULT_TEMP_ID)
            .theSideStreet(DEFAULT_THE_SIDE_STREET)
            .title(DEFAULT_TITLE)
            .unit(DEFAULT_UNIT)
            .city(DEFAULT_CITY);
        return auditCompanyInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditCompanyInfo createUpdatedEntity(EntityManager em) {
        AuditCompanyInfo auditCompanyInfo = new AuditCompanyInfo()
            .address(UPDATED_ADDRESS)
            .barCodes(UPDATED_BAR_CODES)
            .dateOfRegistration(UPDATED_DATE_OF_REGISTRATION)
            .fax(UPDATED_FAX)
            .floor(UPDATED_FLOOR)
            .inernationalobserviation(UPDATED_INERNATIONALOBSERVIATION)
            .mainStreet(UPDATED_MAIN_STREET)
            .mobile(UPDATED_MOBILE)
            .plaque(UPDATED_PLAQUE)
            .postalCode(UPDATED_POSTAL_CODE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .telephone(UPDATED_TELEPHONE)
            .tempId(UPDATED_TEMP_ID)
            .theSideStreet(UPDATED_THE_SIDE_STREET)
            .title(UPDATED_TITLE)
            .unit(UPDATED_UNIT)
            .city(UPDATED_CITY);
        return auditCompanyInfo;
    }

    @BeforeEach
    public void initTest() {
        auditCompanyInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createAuditCompanyInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AuditCompanyInfo
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(auditCompanyInfo);
        var returnedAuditCompanyInfoDTO = om.readValue(
            restAuditCompanyInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(auditCompanyInfoDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AuditCompanyInfoDTO.class
        );

        // Validate the AuditCompanyInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedAuditCompanyInfo = auditCompanyInfoMapper.toEntity(returnedAuditCompanyInfoDTO);
        assertAuditCompanyInfoUpdatableFieldsEquals(returnedAuditCompanyInfo, getPersistedAuditCompanyInfo(returnedAuditCompanyInfo));
    }

    @Test
    @Transactional
    void createAuditCompanyInfoWithExistingId() throws Exception {
        // Create the AuditCompanyInfo with an existing ID
        auditCompanyInfo.setId(1L);
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(auditCompanyInfo);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuditCompanyInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(auditCompanyInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AuditCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAuditCompanyInfos() throws Exception {
        // Initialize the database
        auditCompanyInfoRepository.saveAndFlush(auditCompanyInfo);

        // Get all the auditCompanyInfoList
        restAuditCompanyInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auditCompanyInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].barCodes").value(hasItem(DEFAULT_BAR_CODES)))
            .andExpect(jsonPath("$.[*].dateOfRegistration").value(hasItem(DEFAULT_DATE_OF_REGISTRATION)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].floor").value(hasItem(DEFAULT_FLOOR)))
            .andExpect(jsonPath("$.[*].inernationalobserviation").value(hasItem(DEFAULT_INERNATIONALOBSERVIATION)))
            .andExpect(jsonPath("$.[*].mainStreet").value(hasItem(DEFAULT_MAIN_STREET)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].plaque").value(hasItem(DEFAULT_PLAQUE)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].registrationNumber").value(hasItem(DEFAULT_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].tempId").value(hasItem(DEFAULT_TEMP_ID.intValue())))
            .andExpect(jsonPath("$.[*].theSideStreet").value(hasItem(DEFAULT_THE_SIDE_STREET)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)));
    }

    @Test
    @Transactional
    void getAuditCompanyInfo() throws Exception {
        // Initialize the database
        auditCompanyInfoRepository.saveAndFlush(auditCompanyInfo);

        // Get the auditCompanyInfo
        restAuditCompanyInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, auditCompanyInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(auditCompanyInfo.getId().intValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.barCodes").value(DEFAULT_BAR_CODES))
            .andExpect(jsonPath("$.dateOfRegistration").value(DEFAULT_DATE_OF_REGISTRATION))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.floor").value(DEFAULT_FLOOR))
            .andExpect(jsonPath("$.inernationalobserviation").value(DEFAULT_INERNATIONALOBSERVIATION))
            .andExpect(jsonPath("$.mainStreet").value(DEFAULT_MAIN_STREET))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.plaque").value(DEFAULT_PLAQUE))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.registrationNumber").value(DEFAULT_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.tempId").value(DEFAULT_TEMP_ID.intValue()))
            .andExpect(jsonPath("$.theSideStreet").value(DEFAULT_THE_SIDE_STREET))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY));
    }

    @Test
    @Transactional
    void getNonExistingAuditCompanyInfo() throws Exception {
        // Get the auditCompanyInfo
        restAuditCompanyInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAuditCompanyInfo() throws Exception {
        // Initialize the database
        auditCompanyInfoRepository.saveAndFlush(auditCompanyInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the auditCompanyInfo
        AuditCompanyInfo updatedAuditCompanyInfo = auditCompanyInfoRepository.findById(auditCompanyInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAuditCompanyInfo are not directly saved in db
        em.detach(updatedAuditCompanyInfo);
        updatedAuditCompanyInfo
            .address(UPDATED_ADDRESS)
            .barCodes(UPDATED_BAR_CODES)
            .dateOfRegistration(UPDATED_DATE_OF_REGISTRATION)
            .fax(UPDATED_FAX)
            .floor(UPDATED_FLOOR)
            .inernationalobserviation(UPDATED_INERNATIONALOBSERVIATION)
            .mainStreet(UPDATED_MAIN_STREET)
            .mobile(UPDATED_MOBILE)
            .plaque(UPDATED_PLAQUE)
            .postalCode(UPDATED_POSTAL_CODE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .telephone(UPDATED_TELEPHONE)
            .tempId(UPDATED_TEMP_ID)
            .theSideStreet(UPDATED_THE_SIDE_STREET)
            .title(UPDATED_TITLE)
            .unit(UPDATED_UNIT)
            .city(UPDATED_CITY);
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(updatedAuditCompanyInfo);

        restAuditCompanyInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, auditCompanyInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(auditCompanyInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the AuditCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAuditCompanyInfoToMatchAllProperties(updatedAuditCompanyInfo);
    }

    @Test
    @Transactional
    void putNonExistingAuditCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditCompanyInfo.setId(longCount.incrementAndGet());

        // Create the AuditCompanyInfo
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(auditCompanyInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditCompanyInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, auditCompanyInfoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(auditCompanyInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAuditCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditCompanyInfo.setId(longCount.incrementAndGet());

        // Create the AuditCompanyInfo
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(auditCompanyInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditCompanyInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(auditCompanyInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAuditCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditCompanyInfo.setId(longCount.incrementAndGet());

        // Create the AuditCompanyInfo
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(auditCompanyInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditCompanyInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(auditCompanyInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AuditCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAuditCompanyInfoWithPatch() throws Exception {
        // Initialize the database
        auditCompanyInfoRepository.saveAndFlush(auditCompanyInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the auditCompanyInfo using partial update
        AuditCompanyInfo partialUpdatedAuditCompanyInfo = new AuditCompanyInfo();
        partialUpdatedAuditCompanyInfo.setId(auditCompanyInfo.getId());

        partialUpdatedAuditCompanyInfo
            .barCodes(UPDATED_BAR_CODES)
            .dateOfRegistration(UPDATED_DATE_OF_REGISTRATION)
            .fax(UPDATED_FAX)
            .inernationalobserviation(UPDATED_INERNATIONALOBSERVIATION)
            .mainStreet(UPDATED_MAIN_STREET)
            .mobile(UPDATED_MOBILE)
            .plaque(UPDATED_PLAQUE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .telephone(UPDATED_TELEPHONE)
            .title(UPDATED_TITLE)
            .unit(UPDATED_UNIT)
            .city(UPDATED_CITY);

        restAuditCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAuditCompanyInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAuditCompanyInfo))
            )
            .andExpect(status().isOk());

        // Validate the AuditCompanyInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAuditCompanyInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAuditCompanyInfo, auditCompanyInfo),
            getPersistedAuditCompanyInfo(auditCompanyInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateAuditCompanyInfoWithPatch() throws Exception {
        // Initialize the database
        auditCompanyInfoRepository.saveAndFlush(auditCompanyInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the auditCompanyInfo using partial update
        AuditCompanyInfo partialUpdatedAuditCompanyInfo = new AuditCompanyInfo();
        partialUpdatedAuditCompanyInfo.setId(auditCompanyInfo.getId());

        partialUpdatedAuditCompanyInfo
            .address(UPDATED_ADDRESS)
            .barCodes(UPDATED_BAR_CODES)
            .dateOfRegistration(UPDATED_DATE_OF_REGISTRATION)
            .fax(UPDATED_FAX)
            .floor(UPDATED_FLOOR)
            .inernationalobserviation(UPDATED_INERNATIONALOBSERVIATION)
            .mainStreet(UPDATED_MAIN_STREET)
            .mobile(UPDATED_MOBILE)
            .plaque(UPDATED_PLAQUE)
            .postalCode(UPDATED_POSTAL_CODE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .telephone(UPDATED_TELEPHONE)
            .tempId(UPDATED_TEMP_ID)
            .theSideStreet(UPDATED_THE_SIDE_STREET)
            .title(UPDATED_TITLE)
            .unit(UPDATED_UNIT)
            .city(UPDATED_CITY);

        restAuditCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAuditCompanyInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAuditCompanyInfo))
            )
            .andExpect(status().isOk());

        // Validate the AuditCompanyInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAuditCompanyInfoUpdatableFieldsEquals(
            partialUpdatedAuditCompanyInfo,
            getPersistedAuditCompanyInfo(partialUpdatedAuditCompanyInfo)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAuditCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditCompanyInfo.setId(longCount.incrementAndGet());

        // Create the AuditCompanyInfo
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(auditCompanyInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, auditCompanyInfoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(auditCompanyInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAuditCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditCompanyInfo.setId(longCount.incrementAndGet());

        // Create the AuditCompanyInfo
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(auditCompanyInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditCompanyInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(auditCompanyInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAuditCompanyInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditCompanyInfo.setId(longCount.incrementAndGet());

        // Create the AuditCompanyInfo
        AuditCompanyInfoDTO auditCompanyInfoDTO = auditCompanyInfoMapper.toDto(auditCompanyInfo);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditCompanyInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(auditCompanyInfoDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AuditCompanyInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAuditCompanyInfo() throws Exception {
        // Initialize the database
        auditCompanyInfoRepository.saveAndFlush(auditCompanyInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the auditCompanyInfo
        restAuditCompanyInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, auditCompanyInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return auditCompanyInfoRepository.count();
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

    protected AuditCompanyInfo getPersistedAuditCompanyInfo(AuditCompanyInfo auditCompanyInfo) {
        return auditCompanyInfoRepository.findById(auditCompanyInfo.getId()).orElseThrow();
    }

    protected void assertPersistedAuditCompanyInfoToMatchAllProperties(AuditCompanyInfo expectedAuditCompanyInfo) {
        assertAuditCompanyInfoAllPropertiesEquals(expectedAuditCompanyInfo, getPersistedAuditCompanyInfo(expectedAuditCompanyInfo));
    }

    protected void assertPersistedAuditCompanyInfoToMatchUpdatableProperties(AuditCompanyInfo expectedAuditCompanyInfo) {
        assertAuditCompanyInfoAllUpdatablePropertiesEquals(
            expectedAuditCompanyInfo,
            getPersistedAuditCompanyInfo(expectedAuditCompanyInfo)
        );
    }
}
