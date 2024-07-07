package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftTypeDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftType}.
 */
public interface DraftTypeService {
    /**
     * Save a draftType.
     *
     * @param draftTypeDTO the entity to save.
     * @return the persisted entity.
     */
    DraftTypeDTO save(DraftTypeDTO draftTypeDTO);

    /**
     * Updates a draftType.
     *
     * @param draftTypeDTO the entity to update.
     * @return the persisted entity.
     */
    DraftTypeDTO update(DraftTypeDTO draftTypeDTO);

    /**
     * Partially updates a draftType.
     *
     * @param draftTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftTypeDTO> partialUpdate(DraftTypeDTO draftTypeDTO);

    /**
     * Get all the draftTypes.
     *
     * @return the list of entities.
     */
    List<DraftTypeDTO> findAll();

    /**
     * Get all the draftTypes with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DraftTypeDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" draftType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftTypeDTO> findOne(Long id);

    /**
     * Delete the "id" draftType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
