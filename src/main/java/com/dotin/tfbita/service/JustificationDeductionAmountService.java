package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.JustificationDeductionAmountDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.JustificationDeductionAmount}.
 */
public interface JustificationDeductionAmountService {
    /**
     * Save a justificationDeductionAmount.
     *
     * @param justificationDeductionAmountDTO the entity to save.
     * @return the persisted entity.
     */
    JustificationDeductionAmountDTO save(JustificationDeductionAmountDTO justificationDeductionAmountDTO);

    /**
     * Updates a justificationDeductionAmount.
     *
     * @param justificationDeductionAmountDTO the entity to update.
     * @return the persisted entity.
     */
    JustificationDeductionAmountDTO update(JustificationDeductionAmountDTO justificationDeductionAmountDTO);

    /**
     * Partially updates a justificationDeductionAmount.
     *
     * @param justificationDeductionAmountDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JustificationDeductionAmountDTO> partialUpdate(JustificationDeductionAmountDTO justificationDeductionAmountDTO);

    /**
     * Get all the justificationDeductionAmounts.
     *
     * @return the list of entities.
     */
    List<JustificationDeductionAmountDTO> findAll();

    /**
     * Get the "id" justificationDeductionAmount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JustificationDeductionAmountDTO> findOne(Long id);

    /**
     * Delete the "id" justificationDeductionAmount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
