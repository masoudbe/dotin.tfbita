package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftTypeAccountInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftTypeAccountInfo}.
 */
public interface DraftTypeAccountInfoService {
    /**
     * Save a draftTypeAccountInfo.
     *
     * @param draftTypeAccountInfoDTO the entity to save.
     * @return the persisted entity.
     */
    DraftTypeAccountInfoDTO save(DraftTypeAccountInfoDTO draftTypeAccountInfoDTO);

    /**
     * Updates a draftTypeAccountInfo.
     *
     * @param draftTypeAccountInfoDTO the entity to update.
     * @return the persisted entity.
     */
    DraftTypeAccountInfoDTO update(DraftTypeAccountInfoDTO draftTypeAccountInfoDTO);

    /**
     * Partially updates a draftTypeAccountInfo.
     *
     * @param draftTypeAccountInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftTypeAccountInfoDTO> partialUpdate(DraftTypeAccountInfoDTO draftTypeAccountInfoDTO);

    /**
     * Get all the draftTypeAccountInfos.
     *
     * @return the list of entities.
     */
    List<DraftTypeAccountInfoDTO> findAll();

    /**
     * Get the "id" draftTypeAccountInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftTypeAccountInfoDTO> findOne(Long id);

    /**
     * Delete the "id" draftTypeAccountInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
