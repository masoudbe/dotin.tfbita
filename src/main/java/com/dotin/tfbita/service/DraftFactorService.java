package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftFactorDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftFactor}.
 */
public interface DraftFactorService {
    /**
     * Save a draftFactor.
     *
     * @param draftFactorDTO the entity to save.
     * @return the persisted entity.
     */
    DraftFactorDTO save(DraftFactorDTO draftFactorDTO);

    /**
     * Updates a draftFactor.
     *
     * @param draftFactorDTO the entity to update.
     * @return the persisted entity.
     */
    DraftFactorDTO update(DraftFactorDTO draftFactorDTO);

    /**
     * Partially updates a draftFactor.
     *
     * @param draftFactorDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftFactorDTO> partialUpdate(DraftFactorDTO draftFactorDTO);

    /**
     * Get all the draftFactors.
     *
     * @return the list of entities.
     */
    List<DraftFactorDTO> findAll();

    /**
     * Get the "id" draftFactor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftFactorDTO> findOne(Long id);

    /**
     * Delete the "id" draftFactor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
