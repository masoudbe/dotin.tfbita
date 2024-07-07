package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftRequestTypeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftRequestType}.
 */
public interface DraftRequestTypeService {
    /**
     * Save a draftRequestType.
     *
     * @param draftRequestTypeDTO the entity to save.
     * @return the persisted entity.
     */
    DraftRequestTypeDTO save(DraftRequestTypeDTO draftRequestTypeDTO);

    /**
     * Updates a draftRequestType.
     *
     * @param draftRequestTypeDTO the entity to update.
     * @return the persisted entity.
     */
    DraftRequestTypeDTO update(DraftRequestTypeDTO draftRequestTypeDTO);

    /**
     * Partially updates a draftRequestType.
     *
     * @param draftRequestTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftRequestTypeDTO> partialUpdate(DraftRequestTypeDTO draftRequestTypeDTO);

    /**
     * Get all the draftRequestTypes.
     *
     * @return the list of entities.
     */
    List<DraftRequestTypeDTO> findAll();

    /**
     * Get the "id" draftRequestType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftRequestTypeDTO> findOne(Long id);

    /**
     * Delete the "id" draftRequestType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
