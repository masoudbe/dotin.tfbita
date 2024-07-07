package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftReceiptDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftReceipt}.
 */
public interface DraftReceiptService {
    /**
     * Save a draftReceipt.
     *
     * @param draftReceiptDTO the entity to save.
     * @return the persisted entity.
     */
    DraftReceiptDTO save(DraftReceiptDTO draftReceiptDTO);

    /**
     * Updates a draftReceipt.
     *
     * @param draftReceiptDTO the entity to update.
     * @return the persisted entity.
     */
    DraftReceiptDTO update(DraftReceiptDTO draftReceiptDTO);

    /**
     * Partially updates a draftReceipt.
     *
     * @param draftReceiptDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftReceiptDTO> partialUpdate(DraftReceiptDTO draftReceiptDTO);

    /**
     * Get all the draftReceipts.
     *
     * @return the list of entities.
     */
    List<DraftReceiptDTO> findAll();

    /**
     * Get the "id" draftReceipt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftReceiptDTO> findOne(Long id);

    /**
     * Delete the "id" draftReceipt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
