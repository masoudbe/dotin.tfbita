package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftExtendDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftExtend}.
 */
public interface DraftExtendService {
    /**
     * Save a draftExtend.
     *
     * @param draftExtendDTO the entity to save.
     * @return the persisted entity.
     */
    DraftExtendDTO save(DraftExtendDTO draftExtendDTO);

    /**
     * Updates a draftExtend.
     *
     * @param draftExtendDTO the entity to update.
     * @return the persisted entity.
     */
    DraftExtendDTO update(DraftExtendDTO draftExtendDTO);

    /**
     * Partially updates a draftExtend.
     *
     * @param draftExtendDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftExtendDTO> partialUpdate(DraftExtendDTO draftExtendDTO);

    /**
     * Get all the draftExtends.
     *
     * @return the list of entities.
     */
    List<DraftExtendDTO> findAll();

    /**
     * Get the "id" draftExtend.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftExtendDTO> findOne(Long id);

    /**
     * Delete the "id" draftExtend.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
