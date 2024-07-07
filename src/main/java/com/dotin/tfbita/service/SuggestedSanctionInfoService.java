package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.SuggestedSanctionInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.SuggestedSanctionInfo}.
 */
public interface SuggestedSanctionInfoService {
    /**
     * Save a suggestedSanctionInfo.
     *
     * @param suggestedSanctionInfoDTO the entity to save.
     * @return the persisted entity.
     */
    SuggestedSanctionInfoDTO save(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO);

    /**
     * Updates a suggestedSanctionInfo.
     *
     * @param suggestedSanctionInfoDTO the entity to update.
     * @return the persisted entity.
     */
    SuggestedSanctionInfoDTO update(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO);

    /**
     * Partially updates a suggestedSanctionInfo.
     *
     * @param suggestedSanctionInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SuggestedSanctionInfoDTO> partialUpdate(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO);

    /**
     * Get all the suggestedSanctionInfos.
     *
     * @return the list of entities.
     */
    List<SuggestedSanctionInfoDTO> findAll();

    /**
     * Get the "id" suggestedSanctionInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SuggestedSanctionInfoDTO> findOne(Long id);

    /**
     * Delete the "id" suggestedSanctionInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
