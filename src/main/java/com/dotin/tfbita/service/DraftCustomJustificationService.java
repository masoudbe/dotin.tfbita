package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftCustomJustificationDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftCustomJustification}.
 */
public interface DraftCustomJustificationService {
    /**
     * Save a draftCustomJustification.
     *
     * @param draftCustomJustificationDTO the entity to save.
     * @return the persisted entity.
     */
    DraftCustomJustificationDTO save(DraftCustomJustificationDTO draftCustomJustificationDTO);

    /**
     * Updates a draftCustomJustification.
     *
     * @param draftCustomJustificationDTO the entity to update.
     * @return the persisted entity.
     */
    DraftCustomJustificationDTO update(DraftCustomJustificationDTO draftCustomJustificationDTO);

    /**
     * Partially updates a draftCustomJustification.
     *
     * @param draftCustomJustificationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftCustomJustificationDTO> partialUpdate(DraftCustomJustificationDTO draftCustomJustificationDTO);

    /**
     * Get all the draftCustomJustifications.
     *
     * @return the list of entities.
     */
    List<DraftCustomJustificationDTO> findAll();

    /**
     * Get all the draftCustomJustifications with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DraftCustomJustificationDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" draftCustomJustification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftCustomJustificationDTO> findOne(Long id);

    /**
     * Delete the "id" draftCustomJustification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
