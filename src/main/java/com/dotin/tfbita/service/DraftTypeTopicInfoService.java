package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftTypeTopicInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftTypeTopicInfo}.
 */
public interface DraftTypeTopicInfoService {
    /**
     * Save a draftTypeTopicInfo.
     *
     * @param draftTypeTopicInfoDTO the entity to save.
     * @return the persisted entity.
     */
    DraftTypeTopicInfoDTO save(DraftTypeTopicInfoDTO draftTypeTopicInfoDTO);

    /**
     * Updates a draftTypeTopicInfo.
     *
     * @param draftTypeTopicInfoDTO the entity to update.
     * @return the persisted entity.
     */
    DraftTypeTopicInfoDTO update(DraftTypeTopicInfoDTO draftTypeTopicInfoDTO);

    /**
     * Partially updates a draftTypeTopicInfo.
     *
     * @param draftTypeTopicInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftTypeTopicInfoDTO> partialUpdate(DraftTypeTopicInfoDTO draftTypeTopicInfoDTO);

    /**
     * Get all the draftTypeTopicInfos.
     *
     * @return the list of entities.
     */
    List<DraftTypeTopicInfoDTO> findAll();

    /**
     * Get the "id" draftTypeTopicInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftTypeTopicInfoDTO> findOne(Long id);

    /**
     * Delete the "id" draftTypeTopicInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
