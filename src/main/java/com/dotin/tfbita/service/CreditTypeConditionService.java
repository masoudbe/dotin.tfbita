package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.CreditTypeConditionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.CreditTypeCondition}.
 */
public interface CreditTypeConditionService {
    /**
     * Save a creditTypeCondition.
     *
     * @param creditTypeConditionDTO the entity to save.
     * @return the persisted entity.
     */
    CreditTypeConditionDTO save(CreditTypeConditionDTO creditTypeConditionDTO);

    /**
     * Updates a creditTypeCondition.
     *
     * @param creditTypeConditionDTO the entity to update.
     * @return the persisted entity.
     */
    CreditTypeConditionDTO update(CreditTypeConditionDTO creditTypeConditionDTO);

    /**
     * Partially updates a creditTypeCondition.
     *
     * @param creditTypeConditionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CreditTypeConditionDTO> partialUpdate(CreditTypeConditionDTO creditTypeConditionDTO);

    /**
     * Get all the creditTypeConditions.
     *
     * @return the list of entities.
     */
    List<CreditTypeConditionDTO> findAll();

    /**
     * Get the "id" creditTypeCondition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CreditTypeConditionDTO> findOne(Long id);

    /**
     * Delete the "id" creditTypeCondition.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
