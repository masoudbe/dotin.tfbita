package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DocumentTransactionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DocumentTransaction}.
 */
public interface DocumentTransactionService {
    /**
     * Save a documentTransaction.
     *
     * @param documentTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    DocumentTransactionDTO save(DocumentTransactionDTO documentTransactionDTO);

    /**
     * Updates a documentTransaction.
     *
     * @param documentTransactionDTO the entity to update.
     * @return the persisted entity.
     */
    DocumentTransactionDTO update(DocumentTransactionDTO documentTransactionDTO);

    /**
     * Partially updates a documentTransaction.
     *
     * @param documentTransactionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DocumentTransactionDTO> partialUpdate(DocumentTransactionDTO documentTransactionDTO);

    /**
     * Get all the documentTransactions.
     *
     * @return the list of entities.
     */
    List<DocumentTransactionDTO> findAll();

    /**
     * Get the "id" documentTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DocumentTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" documentTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
