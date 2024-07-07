package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.CustomJustificationDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.CustomJustification}.
 */
public interface CustomJustificationService {
    /**
     * Save a customJustification.
     *
     * @param customJustificationDTO the entity to save.
     * @return the persisted entity.
     */
    CustomJustificationDTO save(CustomJustificationDTO customJustificationDTO);

    /**
     * Updates a customJustification.
     *
     * @param customJustificationDTO the entity to update.
     * @return the persisted entity.
     */
    CustomJustificationDTO update(CustomJustificationDTO customJustificationDTO);

    /**
     * Partially updates a customJustification.
     *
     * @param customJustificationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CustomJustificationDTO> partialUpdate(CustomJustificationDTO customJustificationDTO);

    /**
     * Get all the customJustifications.
     *
     * @return the list of entities.
     */
    List<CustomJustificationDTO> findAll();

    /**
     * Get all the customJustifications with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomJustificationDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" customJustification.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomJustificationDTO> findOne(Long id);

    /**
     * Delete the "id" customJustification.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
