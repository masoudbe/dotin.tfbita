package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.JustificationDeductionDetailAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static com.dotin.tfbita.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.JustificationDeductionDetail;
import com.dotin.tfbita.repository.JustificationDeductionDetailRepository;
import com.dotin.tfbita.service.dto.JustificationDeductionDetailDTO;
import com.dotin.tfbita.service.mapper.JustificationDeductionDetailMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link JustificationDeductionDetailResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JustificationDeductionDetailResourceIT {

    private static final BigDecimal DEFAULT_DEDUCTION_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEDUCTION_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EQUIVALENT_DEDUCTION_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_EQUIVALENT_DEDUCTION_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_RECEIVE_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVE_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/justification-deduction-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private JustificationDeductionDetailRepository justificationDeductionDetailRepository;

    @Autowired
    private JustificationDeductionDetailMapper justificationDeductionDetailMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJustificationDeductionDetailMockMvc;

    private JustificationDeductionDetail justificationDeductionDetail;

    private JustificationDeductionDetail insertedJustificationDeductionDetail;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JustificationDeductionDetail createEntity(EntityManager em) {
        JustificationDeductionDetail justificationDeductionDetail = new JustificationDeductionDetail()
            .deductionAmount(DEFAULT_DEDUCTION_AMOUNT)
            .equivalentDeductionAmount(DEFAULT_EQUIVALENT_DEDUCTION_AMOUNT)
            .receiveCurrencyCode(DEFAULT_RECEIVE_CURRENCY_CODE)
            .comment(DEFAULT_COMMENT);
        return justificationDeductionDetail;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JustificationDeductionDetail createUpdatedEntity(EntityManager em) {
        JustificationDeductionDetail justificationDeductionDetail = new JustificationDeductionDetail()
            .deductionAmount(UPDATED_DEDUCTION_AMOUNT)
            .equivalentDeductionAmount(UPDATED_EQUIVALENT_DEDUCTION_AMOUNT)
            .receiveCurrencyCode(UPDATED_RECEIVE_CURRENCY_CODE)
            .comment(UPDATED_COMMENT);
        return justificationDeductionDetail;
    }

    @BeforeEach
    public void initTest() {
        justificationDeductionDetail = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedJustificationDeductionDetail != null) {
            justificationDeductionDetailRepository.delete(insertedJustificationDeductionDetail);
            insertedJustificationDeductionDetail = null;
        }
    }

    @Test
    @Transactional
    void createJustificationDeductionDetail() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the JustificationDeductionDetail
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            justificationDeductionDetail
        );
        var returnedJustificationDeductionDetailDTO = om.readValue(
            restJustificationDeductionDetailMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(justificationDeductionDetailDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            JustificationDeductionDetailDTO.class
        );

        // Validate the JustificationDeductionDetail in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedJustificationDeductionDetail = justificationDeductionDetailMapper.toEntity(returnedJustificationDeductionDetailDTO);
        assertJustificationDeductionDetailUpdatableFieldsEquals(
            returnedJustificationDeductionDetail,
            getPersistedJustificationDeductionDetail(returnedJustificationDeductionDetail)
        );

        insertedJustificationDeductionDetail = returnedJustificationDeductionDetail;
    }

    @Test
    @Transactional
    void createJustificationDeductionDetailWithExistingId() throws Exception {
        // Create the JustificationDeductionDetail with an existing ID
        justificationDeductionDetail.setId(1L);
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            justificationDeductionDetail
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJustificationDeductionDetailMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(justificationDeductionDetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionDetail in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJustificationDeductionDetails() throws Exception {
        // Initialize the database
        insertedJustificationDeductionDetail = justificationDeductionDetailRepository.saveAndFlush(justificationDeductionDetail);

        // Get all the justificationDeductionDetailList
        restJustificationDeductionDetailMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(justificationDeductionDetail.getId().intValue())))
            .andExpect(jsonPath("$.[*].deductionAmount").value(hasItem(sameNumber(DEFAULT_DEDUCTION_AMOUNT))))
            .andExpect(jsonPath("$.[*].equivalentDeductionAmount").value(hasItem(sameNumber(DEFAULT_EQUIVALENT_DEDUCTION_AMOUNT))))
            .andExpect(jsonPath("$.[*].receiveCurrencyCode").value(hasItem(DEFAULT_RECEIVE_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
    }

    @Test
    @Transactional
    void getJustificationDeductionDetail() throws Exception {
        // Initialize the database
        insertedJustificationDeductionDetail = justificationDeductionDetailRepository.saveAndFlush(justificationDeductionDetail);

        // Get the justificationDeductionDetail
        restJustificationDeductionDetailMockMvc
            .perform(get(ENTITY_API_URL_ID, justificationDeductionDetail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(justificationDeductionDetail.getId().intValue()))
            .andExpect(jsonPath("$.deductionAmount").value(sameNumber(DEFAULT_DEDUCTION_AMOUNT)))
            .andExpect(jsonPath("$.equivalentDeductionAmount").value(sameNumber(DEFAULT_EQUIVALENT_DEDUCTION_AMOUNT)))
            .andExpect(jsonPath("$.receiveCurrencyCode").value(DEFAULT_RECEIVE_CURRENCY_CODE))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingJustificationDeductionDetail() throws Exception {
        // Get the justificationDeductionDetail
        restJustificationDeductionDetailMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingJustificationDeductionDetail() throws Exception {
        // Initialize the database
        insertedJustificationDeductionDetail = justificationDeductionDetailRepository.saveAndFlush(justificationDeductionDetail);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionDetail
        JustificationDeductionDetail updatedJustificationDeductionDetail = justificationDeductionDetailRepository
            .findById(justificationDeductionDetail.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedJustificationDeductionDetail are not directly saved in db
        em.detach(updatedJustificationDeductionDetail);
        updatedJustificationDeductionDetail
            .deductionAmount(UPDATED_DEDUCTION_AMOUNT)
            .equivalentDeductionAmount(UPDATED_EQUIVALENT_DEDUCTION_AMOUNT)
            .receiveCurrencyCode(UPDATED_RECEIVE_CURRENCY_CODE)
            .comment(UPDATED_COMMENT);
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            updatedJustificationDeductionDetail
        );

        restJustificationDeductionDetailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, justificationDeductionDetailDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionDetailDTO))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionDetail in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedJustificationDeductionDetailToMatchAllProperties(updatedJustificationDeductionDetail);
    }

    @Test
    @Transactional
    void putNonExistingJustificationDeductionDetail() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionDetail.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionDetail
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            justificationDeductionDetail
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJustificationDeductionDetailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, justificationDeductionDetailDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionDetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionDetail in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJustificationDeductionDetail() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionDetail.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionDetail
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            justificationDeductionDetail
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionDetailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(justificationDeductionDetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionDetail in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJustificationDeductionDetail() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionDetail.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionDetail
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            justificationDeductionDetail
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionDetailMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(justificationDeductionDetailDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JustificationDeductionDetail in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJustificationDeductionDetailWithPatch() throws Exception {
        // Initialize the database
        insertedJustificationDeductionDetail = justificationDeductionDetailRepository.saveAndFlush(justificationDeductionDetail);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionDetail using partial update
        JustificationDeductionDetail partialUpdatedJustificationDeductionDetail = new JustificationDeductionDetail();
        partialUpdatedJustificationDeductionDetail.setId(justificationDeductionDetail.getId());

        partialUpdatedJustificationDeductionDetail.equivalentDeductionAmount(UPDATED_EQUIVALENT_DEDUCTION_AMOUNT);

        restJustificationDeductionDetailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJustificationDeductionDetail.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedJustificationDeductionDetail))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionDetail in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertJustificationDeductionDetailUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedJustificationDeductionDetail, justificationDeductionDetail),
            getPersistedJustificationDeductionDetail(justificationDeductionDetail)
        );
    }

    @Test
    @Transactional
    void fullUpdateJustificationDeductionDetailWithPatch() throws Exception {
        // Initialize the database
        insertedJustificationDeductionDetail = justificationDeductionDetailRepository.saveAndFlush(justificationDeductionDetail);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the justificationDeductionDetail using partial update
        JustificationDeductionDetail partialUpdatedJustificationDeductionDetail = new JustificationDeductionDetail();
        partialUpdatedJustificationDeductionDetail.setId(justificationDeductionDetail.getId());

        partialUpdatedJustificationDeductionDetail
            .deductionAmount(UPDATED_DEDUCTION_AMOUNT)
            .equivalentDeductionAmount(UPDATED_EQUIVALENT_DEDUCTION_AMOUNT)
            .receiveCurrencyCode(UPDATED_RECEIVE_CURRENCY_CODE)
            .comment(UPDATED_COMMENT);

        restJustificationDeductionDetailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJustificationDeductionDetail.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedJustificationDeductionDetail))
            )
            .andExpect(status().isOk());

        // Validate the JustificationDeductionDetail in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertJustificationDeductionDetailUpdatableFieldsEquals(
            partialUpdatedJustificationDeductionDetail,
            getPersistedJustificationDeductionDetail(partialUpdatedJustificationDeductionDetail)
        );
    }

    @Test
    @Transactional
    void patchNonExistingJustificationDeductionDetail() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionDetail.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionDetail
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            justificationDeductionDetail
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJustificationDeductionDetailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, justificationDeductionDetailDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionDetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionDetail in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJustificationDeductionDetail() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionDetail.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionDetail
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            justificationDeductionDetail
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionDetailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionDetailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the JustificationDeductionDetail in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJustificationDeductionDetail() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        justificationDeductionDetail.setId(longCount.incrementAndGet());

        // Create the JustificationDeductionDetail
        JustificationDeductionDetailDTO justificationDeductionDetailDTO = justificationDeductionDetailMapper.toDto(
            justificationDeductionDetail
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJustificationDeductionDetailMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(justificationDeductionDetailDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JustificationDeductionDetail in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJustificationDeductionDetail() throws Exception {
        // Initialize the database
        insertedJustificationDeductionDetail = justificationDeductionDetailRepository.saveAndFlush(justificationDeductionDetail);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the justificationDeductionDetail
        restJustificationDeductionDetailMockMvc
            .perform(delete(ENTITY_API_URL_ID, justificationDeductionDetail.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return justificationDeductionDetailRepository.count();
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

    protected JustificationDeductionDetail getPersistedJustificationDeductionDetail(
        JustificationDeductionDetail justificationDeductionDetail
    ) {
        return justificationDeductionDetailRepository.findById(justificationDeductionDetail.getId()).orElseThrow();
    }

    protected void assertPersistedJustificationDeductionDetailToMatchAllProperties(
        JustificationDeductionDetail expectedJustificationDeductionDetail
    ) {
        assertJustificationDeductionDetailAllPropertiesEquals(
            expectedJustificationDeductionDetail,
            getPersistedJustificationDeductionDetail(expectedJustificationDeductionDetail)
        );
    }

    protected void assertPersistedJustificationDeductionDetailToMatchUpdatableProperties(
        JustificationDeductionDetail expectedJustificationDeductionDetail
    ) {
        assertJustificationDeductionDetailAllUpdatablePropertiesEquals(
            expectedJustificationDeductionDetail,
            getPersistedJustificationDeductionDetail(expectedJustificationDeductionDetail)
        );
    }
}
