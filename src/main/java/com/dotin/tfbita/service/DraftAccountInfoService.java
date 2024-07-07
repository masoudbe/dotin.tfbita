package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftAccountInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftAccountInfo}.
 */
public interface DraftAccountInfoService {
    /**
     * Save a draftAccountInfo.
     *
     * @param draftAccountInfoDTO the entity to save.
     * @return the persisted entity.
     */
    DraftAccountInfoDTO save(DraftAccountInfoDTO draftAccountInfoDTO);

    /**
     * Updates a draftAccountInfo.
     *
     * @param draftAccountInfoDTO the entity to update.
     * @return the persisted entity.
     */
    DraftAccountInfoDTO update(DraftAccountInfoDTO draftAccountInfoDTO);

    /**
     * Partially updates a draftAccountInfo.
     *
     * @param draftAccountInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftAccountInfoDTO> partialUpdate(DraftAccountInfoDTO draftAccountInfoDTO);

    /**
     * Get all the draftAccountInfos.
     *
     * @return the list of entities.
     */
    List<DraftAccountInfoDTO> findAll();

    /**
     * Get the "id" draftAccountInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftAccountInfoDTO> findOne(Long id);

    /**
     * Delete the "id" draftAccountInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
