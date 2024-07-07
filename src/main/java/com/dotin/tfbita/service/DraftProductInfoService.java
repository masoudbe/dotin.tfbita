package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftProductInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftProductInfo}.
 */
public interface DraftProductInfoService {
    /**
     * Save a draftProductInfo.
     *
     * @param draftProductInfoDTO the entity to save.
     * @return the persisted entity.
     */
    DraftProductInfoDTO save(DraftProductInfoDTO draftProductInfoDTO);

    /**
     * Updates a draftProductInfo.
     *
     * @param draftProductInfoDTO the entity to update.
     * @return the persisted entity.
     */
    DraftProductInfoDTO update(DraftProductInfoDTO draftProductInfoDTO);

    /**
     * Partially updates a draftProductInfo.
     *
     * @param draftProductInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftProductInfoDTO> partialUpdate(DraftProductInfoDTO draftProductInfoDTO);

    /**
     * Get all the draftProductInfos.
     *
     * @return the list of entities.
     */
    List<DraftProductInfoDTO> findAll();

    /**
     * Get the "id" draftProductInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftProductInfoDTO> findOne(Long id);

    /**
     * Delete the "id" draftProductInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
