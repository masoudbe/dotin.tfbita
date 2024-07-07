package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.JustificationDeductionAmountPartDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.JustificationDeductionAmountPart}.
 */
public interface JustificationDeductionAmountPartService {
    /**
     * Save a justificationDeductionAmountPart.
     *
     * @param justificationDeductionAmountPartDTO the entity to save.
     * @return the persisted entity.
     */
    JustificationDeductionAmountPartDTO save(JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO);

    /**
     * Updates a justificationDeductionAmountPart.
     *
     * @param justificationDeductionAmountPartDTO the entity to update.
     * @return the persisted entity.
     */
    JustificationDeductionAmountPartDTO update(JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO);

    /**
     * Partially updates a justificationDeductionAmountPart.
     *
     * @param justificationDeductionAmountPartDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JustificationDeductionAmountPartDTO> partialUpdate(JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO);

    /**
     * Get all the justificationDeductionAmountParts.
     *
     * @return the list of entities.
     */
    List<JustificationDeductionAmountPartDTO> findAll();

    /**
     * Get the "id" justificationDeductionAmountPart.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JustificationDeductionAmountPartDTO> findOne(Long id);

    /**
     * Delete the "id" justificationDeductionAmountPart.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
