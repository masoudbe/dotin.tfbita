package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.Draft}.
 */
public interface DraftService {
    /**
     * Save a draft.
     *
     * @param draftDTO the entity to save.
     * @return the persisted entity.
     */
    DraftDTO save(DraftDTO draftDTO);

    /**
     * Updates a draft.
     *
     * @param draftDTO the entity to update.
     * @return the persisted entity.
     */
    DraftDTO update(DraftDTO draftDTO);

    /**
     * Partially updates a draft.
     *
     * @param draftDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftDTO> partialUpdate(DraftDTO draftDTO);

    /**
     * Get all the drafts.
     *
     * @return the list of entities.
     */
    List<DraftDTO> findAll();

    /**
     * Get the "id" draft.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftDTO> findOne(Long id);

    /**
     * Delete the "id" draft.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
