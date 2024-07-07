package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftReceiptDocumentTransactionContainerDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer}.
 */
public interface DraftReceiptDocumentTransactionContainerService {
    /**
     * Save a draftReceiptDocumentTransactionContainer.
     *
     * @param draftReceiptDocumentTransactionContainerDTO the entity to save.
     * @return the persisted entity.
     */
    DraftReceiptDocumentTransactionContainerDTO save(
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    );

    /**
     * Updates a draftReceiptDocumentTransactionContainer.
     *
     * @param draftReceiptDocumentTransactionContainerDTO the entity to update.
     * @return the persisted entity.
     */
    DraftReceiptDocumentTransactionContainerDTO update(
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    );

    /**
     * Partially updates a draftReceiptDocumentTransactionContainer.
     *
     * @param draftReceiptDocumentTransactionContainerDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftReceiptDocumentTransactionContainerDTO> partialUpdate(
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    );

    /**
     * Get all the draftReceiptDocumentTransactionContainers.
     *
     * @return the list of entities.
     */
    List<DraftReceiptDocumentTransactionContainerDTO> findAll();

    /**
     * Get the "id" draftReceiptDocumentTransactionContainer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftReceiptDocumentTransactionContainerDTO> findOne(Long id);

    /**
     * Delete the "id" draftReceiptDocumentTransactionContainer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
