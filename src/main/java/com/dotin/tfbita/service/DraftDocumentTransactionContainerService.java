package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftDocumentTransactionContainerDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftDocumentTransactionContainer}.
 */
public interface DraftDocumentTransactionContainerService {
    /**
     * Save a draftDocumentTransactionContainer.
     *
     * @param draftDocumentTransactionContainerDTO the entity to save.
     * @return the persisted entity.
     */
    DraftDocumentTransactionContainerDTO save(DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO);

    /**
     * Updates a draftDocumentTransactionContainer.
     *
     * @param draftDocumentTransactionContainerDTO the entity to update.
     * @return the persisted entity.
     */
    DraftDocumentTransactionContainerDTO update(DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO);

    /**
     * Partially updates a draftDocumentTransactionContainer.
     *
     * @param draftDocumentTransactionContainerDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftDocumentTransactionContainerDTO> partialUpdate(DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO);

    /**
     * Get all the draftDocumentTransactionContainers.
     *
     * @return the list of entities.
     */
    List<DraftDocumentTransactionContainerDTO> findAll();

    /**
     * Get the "id" draftDocumentTransactionContainer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftDocumentTransactionContainerDTO> findOne(Long id);

    /**
     * Delete the "id" draftDocumentTransactionContainer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
