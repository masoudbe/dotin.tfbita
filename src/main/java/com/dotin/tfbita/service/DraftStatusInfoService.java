package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftStatusInfo}.
 */
public interface DraftStatusInfoService {
    /**
     * Save a draftStatusInfo.
     *
     * @param draftStatusInfoDTO the entity to save.
     * @return the persisted entity.
     */
    DraftStatusInfoDTO save(DraftStatusInfoDTO draftStatusInfoDTO);

    /**
     * Updates a draftStatusInfo.
     *
     * @param draftStatusInfoDTO the entity to update.
     * @return the persisted entity.
     */
    DraftStatusInfoDTO update(DraftStatusInfoDTO draftStatusInfoDTO);

    /**
     * Partially updates a draftStatusInfo.
     *
     * @param draftStatusInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftStatusInfoDTO> partialUpdate(DraftStatusInfoDTO draftStatusInfoDTO);

    /**
     * Get all the draftStatusInfos.
     *
     * @return the list of entities.
     */
    List<DraftStatusInfoDTO> findAll();

    /**
     * Get the "id" draftStatusInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftStatusInfoDTO> findOne(Long id);

    /**
     * Delete the "id" draftStatusInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
