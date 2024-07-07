package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.JustificationDeductionDetailDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.JustificationDeductionDetail}.
 */
public interface JustificationDeductionDetailService {
    /**
     * Save a justificationDeductionDetail.
     *
     * @param justificationDeductionDetailDTO the entity to save.
     * @return the persisted entity.
     */
    JustificationDeductionDetailDTO save(JustificationDeductionDetailDTO justificationDeductionDetailDTO);

    /**
     * Updates a justificationDeductionDetail.
     *
     * @param justificationDeductionDetailDTO the entity to update.
     * @return the persisted entity.
     */
    JustificationDeductionDetailDTO update(JustificationDeductionDetailDTO justificationDeductionDetailDTO);

    /**
     * Partially updates a justificationDeductionDetail.
     *
     * @param justificationDeductionDetailDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JustificationDeductionDetailDTO> partialUpdate(JustificationDeductionDetailDTO justificationDeductionDetailDTO);

    /**
     * Get all the justificationDeductionDetails.
     *
     * @return the list of entities.
     */
    List<JustificationDeductionDetailDTO> findAll();

    /**
     * Get the "id" justificationDeductionDetail.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JustificationDeductionDetailDTO> findOne(Long id);

    /**
     * Delete the "id" justificationDeductionDetail.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
