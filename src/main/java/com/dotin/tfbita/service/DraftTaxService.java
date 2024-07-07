package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftTaxDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftTax}.
 */
public interface DraftTaxService {
    /**
     * Save a draftTax.
     *
     * @param draftTaxDTO the entity to save.
     * @return the persisted entity.
     */
    DraftTaxDTO save(DraftTaxDTO draftTaxDTO);

    /**
     * Updates a draftTax.
     *
     * @param draftTaxDTO the entity to update.
     * @return the persisted entity.
     */
    DraftTaxDTO update(DraftTaxDTO draftTaxDTO);

    /**
     * Partially updates a draftTax.
     *
     * @param draftTaxDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftTaxDTO> partialUpdate(DraftTaxDTO draftTaxDTO);

    /**
     * Get all the draftTaxes.
     *
     * @return the list of entities.
     */
    List<DraftTaxDTO> findAll();

    /**
     * Get the "id" draftTax.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftTaxDTO> findOne(Long id);

    /**
     * Delete the "id" draftTax.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
