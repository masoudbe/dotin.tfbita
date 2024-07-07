package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.CustomJustificationChildDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.CustomJustificationChild}.
 */
public interface CustomJustificationChildService {
    /**
     * Save a customJustificationChild.
     *
     * @param customJustificationChildDTO the entity to save.
     * @return the persisted entity.
     */
    CustomJustificationChildDTO save(CustomJustificationChildDTO customJustificationChildDTO);

    /**
     * Updates a customJustificationChild.
     *
     * @param customJustificationChildDTO the entity to update.
     * @return the persisted entity.
     */
    CustomJustificationChildDTO update(CustomJustificationChildDTO customJustificationChildDTO);

    /**
     * Partially updates a customJustificationChild.
     *
     * @param customJustificationChildDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CustomJustificationChildDTO> partialUpdate(CustomJustificationChildDTO customJustificationChildDTO);

    /**
     * Get all the customJustificationChildren.
     *
     * @return the list of entities.
     */
    List<CustomJustificationChildDTO> findAll();

    /**
     * Get the "id" customJustificationChild.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomJustificationChildDTO> findOne(Long id);

    /**
     * Delete the "id" customJustificationChild.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
