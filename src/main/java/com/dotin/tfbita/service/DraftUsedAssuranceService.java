package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftUsedAssuranceDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftUsedAssurance}.
 */
public interface DraftUsedAssuranceService {
    /**
     * Save a draftUsedAssurance.
     *
     * @param draftUsedAssuranceDTO the entity to save.
     * @return the persisted entity.
     */
    DraftUsedAssuranceDTO save(DraftUsedAssuranceDTO draftUsedAssuranceDTO);

    /**
     * Updates a draftUsedAssurance.
     *
     * @param draftUsedAssuranceDTO the entity to update.
     * @return the persisted entity.
     */
    DraftUsedAssuranceDTO update(DraftUsedAssuranceDTO draftUsedAssuranceDTO);

    /**
     * Partially updates a draftUsedAssurance.
     *
     * @param draftUsedAssuranceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftUsedAssuranceDTO> partialUpdate(DraftUsedAssuranceDTO draftUsedAssuranceDTO);

    /**
     * Get all the draftUsedAssurances.
     *
     * @return the list of entities.
     */
    List<DraftUsedAssuranceDTO> findAll();

    /**
     * Get the "id" draftUsedAssurance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftUsedAssuranceDTO> findOne(Long id);

    /**
     * Delete the "id" draftUsedAssurance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
