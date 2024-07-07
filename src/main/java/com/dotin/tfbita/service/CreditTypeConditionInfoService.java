package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.CreditTypeConditionInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.CreditTypeConditionInfo}.
 */
public interface CreditTypeConditionInfoService {
    /**
     * Save a creditTypeConditionInfo.
     *
     * @param creditTypeConditionInfoDTO the entity to save.
     * @return the persisted entity.
     */
    CreditTypeConditionInfoDTO save(CreditTypeConditionInfoDTO creditTypeConditionInfoDTO);

    /**
     * Updates a creditTypeConditionInfo.
     *
     * @param creditTypeConditionInfoDTO the entity to update.
     * @return the persisted entity.
     */
    CreditTypeConditionInfoDTO update(CreditTypeConditionInfoDTO creditTypeConditionInfoDTO);

    /**
     * Partially updates a creditTypeConditionInfo.
     *
     * @param creditTypeConditionInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CreditTypeConditionInfoDTO> partialUpdate(CreditTypeConditionInfoDTO creditTypeConditionInfoDTO);

    /**
     * Get all the creditTypeConditionInfos.
     *
     * @return the list of entities.
     */
    List<CreditTypeConditionInfoDTO> findAll();

    /**
     * Get the "id" creditTypeConditionInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CreditTypeConditionInfoDTO> findOne(Long id);

    /**
     * Delete the "id" creditTypeConditionInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
