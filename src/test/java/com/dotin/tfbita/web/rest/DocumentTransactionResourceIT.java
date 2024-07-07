package com.dotin.tfbita.web.rest;

import static com.dotin.tfbita.domain.DocumentTransactionAsserts.*;
import static com.dotin.tfbita.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dotin.tfbita.IntegrationTest;
import com.dotin.tfbita.domain.DocumentTransaction;
import com.dotin.tfbita.repository.DocumentTransactionRepository;
import com.dotin.tfbita.service.dto.DocumentTransactionDTO;
import com.dotin.tfbita.service.mapper.DocumentTransactionMapper;
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
 * Integration tests for the {@link DocumentTransactionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DocumentTransactionResourceIT {

    private static final String DEFAULT_CURRENCY_EXCHANGE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_EXCHANGE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/document-transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DocumentTransactionRepository documentTransactionRepository;

    @Autowired
    private DocumentTransactionMapper documentTransactionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentTransactionMockMvc;

    private DocumentTransaction documentTransaction;

    private DocumentTransaction insertedDocumentTransaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentTransaction createEntity(EntityManager em) {
        DocumentTransaction documentTransaction = new DocumentTransaction()
            .currencyExchangeCode(DEFAULT_CURRENCY_EXCHANGE_CODE)
            .transactionCode(DEFAULT_TRANSACTION_CODE);
        return documentTransaction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentTransaction createUpdatedEntity(EntityManager em) {
        DocumentTransaction documentTransaction = new DocumentTransaction()
            .currencyExchangeCode(UPDATED_CURRENCY_EXCHANGE_CODE)
            .transactionCode(UPDATED_TRANSACTION_CODE);
        return documentTransaction;
    }

    @BeforeEach
    public void initTest() {
        documentTransaction = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDocumentTransaction != null) {
            documentTransactionRepository.delete(insertedDocumentTransaction);
            insertedDocumentTransaction = null;
        }
    }

    @Test
    @Transactional
    void createDocumentTransaction() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DocumentTransaction
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(documentTransaction);
        var returnedDocumentTransactionDTO = om.readValue(
            restDocumentTransactionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(documentTransactionDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DocumentTransactionDTO.class
        );

        // Validate the DocumentTransaction in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDocumentTransaction = documentTransactionMapper.toEntity(returnedDocumentTransactionDTO);
        assertDocumentTransactionUpdatableFieldsEquals(
            returnedDocumentTransaction,
            getPersistedDocumentTransaction(returnedDocumentTransaction)
        );

        insertedDocumentTransaction = returnedDocumentTransaction;
    }

    @Test
    @Transactional
    void createDocumentTransactionWithExistingId() throws Exception {
        // Create the DocumentTransaction with an existing ID
        documentTransaction.setId(1L);
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(documentTransaction);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentTransactionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(documentTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DocumentTransaction in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDocumentTransactions() throws Exception {
        // Initialize the database
        insertedDocumentTransaction = documentTransactionRepository.saveAndFlush(documentTransaction);

        // Get all the documentTransactionList
        restDocumentTransactionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].currencyExchangeCode").value(hasItem(DEFAULT_CURRENCY_EXCHANGE_CODE)))
            .andExpect(jsonPath("$.[*].transactionCode").value(hasItem(DEFAULT_TRANSACTION_CODE)));
    }

    @Test
    @Transactional
    void getDocumentTransaction() throws Exception {
        // Initialize the database
        insertedDocumentTransaction = documentTransactionRepository.saveAndFlush(documentTransaction);

        // Get the documentTransaction
        restDocumentTransactionMockMvc
            .perform(get(ENTITY_API_URL_ID, documentTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(documentTransaction.getId().intValue()))
            .andExpect(jsonPath("$.currencyExchangeCode").value(DEFAULT_CURRENCY_EXCHANGE_CODE))
            .andExpect(jsonPath("$.transactionCode").value(DEFAULT_TRANSACTION_CODE));
    }

    @Test
    @Transactional
    void getNonExistingDocumentTransaction() throws Exception {
        // Get the documentTransaction
        restDocumentTransactionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDocumentTransaction() throws Exception {
        // Initialize the database
        insertedDocumentTransaction = documentTransactionRepository.saveAndFlush(documentTransaction);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the documentTransaction
        DocumentTransaction updatedDocumentTransaction = documentTransactionRepository.findById(documentTransaction.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDocumentTransaction are not directly saved in db
        em.detach(updatedDocumentTransaction);
        updatedDocumentTransaction.currencyExchangeCode(UPDATED_CURRENCY_EXCHANGE_CODE).transactionCode(UPDATED_TRANSACTION_CODE);
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(updatedDocumentTransaction);

        restDocumentTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentTransactionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(documentTransactionDTO))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTransaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDocumentTransactionToMatchAllProperties(updatedDocumentTransaction);
    }

    @Test
    @Transactional
    void putNonExistingDocumentTransaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentTransaction.setId(longCount.incrementAndGet());

        // Create the DocumentTransaction
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(documentTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentTransactionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(documentTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTransaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDocumentTransaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentTransaction.setId(longCount.incrementAndGet());

        // Create the DocumentTransaction
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(documentTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(documentTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTransaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDocumentTransaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentTransaction.setId(longCount.incrementAndGet());

        // Create the DocumentTransaction
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(documentTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTransactionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(documentTransactionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DocumentTransaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDocumentTransactionWithPatch() throws Exception {
        // Initialize the database
        insertedDocumentTransaction = documentTransactionRepository.saveAndFlush(documentTransaction);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the documentTransaction using partial update
        DocumentTransaction partialUpdatedDocumentTransaction = new DocumentTransaction();
        partialUpdatedDocumentTransaction.setId(documentTransaction.getId());

        partialUpdatedDocumentTransaction.currencyExchangeCode(UPDATED_CURRENCY_EXCHANGE_CODE).transactionCode(UPDATED_TRANSACTION_CODE);

        restDocumentTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentTransaction.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDocumentTransaction))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTransaction in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDocumentTransactionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDocumentTransaction, documentTransaction),
            getPersistedDocumentTransaction(documentTransaction)
        );
    }

    @Test
    @Transactional
    void fullUpdateDocumentTransactionWithPatch() throws Exception {
        // Initialize the database
        insertedDocumentTransaction = documentTransactionRepository.saveAndFlush(documentTransaction);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the documentTransaction using partial update
        DocumentTransaction partialUpdatedDocumentTransaction = new DocumentTransaction();
        partialUpdatedDocumentTransaction.setId(documentTransaction.getId());

        partialUpdatedDocumentTransaction.currencyExchangeCode(UPDATED_CURRENCY_EXCHANGE_CODE).transactionCode(UPDATED_TRANSACTION_CODE);

        restDocumentTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentTransaction.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDocumentTransaction))
            )
            .andExpect(status().isOk());

        // Validate the DocumentTransaction in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDocumentTransactionUpdatableFieldsEquals(
            partialUpdatedDocumentTransaction,
            getPersistedDocumentTransaction(partialUpdatedDocumentTransaction)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDocumentTransaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentTransaction.setId(longCount.incrementAndGet());

        // Create the DocumentTransaction
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(documentTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, documentTransactionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(documentTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTransaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDocumentTransaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentTransaction.setId(longCount.incrementAndGet());

        // Create the DocumentTransaction
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(documentTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(documentTransactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DocumentTransaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDocumentTransaction() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        documentTransaction.setId(longCount.incrementAndGet());

        // Create the DocumentTransaction
        DocumentTransactionDTO documentTransactionDTO = documentTransactionMapper.toDto(documentTransaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(documentTransactionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DocumentTransaction in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDocumentTransaction() throws Exception {
        // Initialize the database
        insertedDocumentTransaction = documentTransactionRepository.saveAndFlush(documentTransaction);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the documentTransaction
        restDocumentTransactionMockMvc
            .perform(delete(ENTITY_API_URL_ID, documentTransaction.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return documentTransactionRepository.count();
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

    protected DocumentTransaction getPersistedDocumentTransaction(DocumentTransaction documentTransaction) {
        return documentTransactionRepository.findById(documentTransaction.getId()).orElseThrow();
    }

    protected void assertPersistedDocumentTransactionToMatchAllProperties(DocumentTransaction expectedDocumentTransaction) {
        assertDocumentTransactionAllPropertiesEquals(
            expectedDocumentTransaction,
            getPersistedDocumentTransaction(expectedDocumentTransaction)
        );
    }

    protected void assertPersistedDocumentTransactionToMatchUpdatableProperties(DocumentTransaction expectedDocumentTransaction) {
        assertDocumentTransactionAllUpdatablePropertiesEquals(
            expectedDocumentTransaction,
            getPersistedDocumentTransaction(expectedDocumentTransaction)
        );
    }
}
