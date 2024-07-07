package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.ObjectiveCategoryDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.ObjectiveCategory}.
 */
public interface ObjectiveCategoryService {
    /**
     * Save a objectiveCategory.
     *
     * @param objectiveCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    ObjectiveCategoryDTO save(ObjectiveCategoryDTO objectiveCategoryDTO);

    /**
     * Updates a objectiveCategory.
     *
     * @param objectiveCategoryDTO the entity to update.
     * @return the persisted entity.
     */
    ObjectiveCategoryDTO update(ObjectiveCategoryDTO objectiveCategoryDTO);

    /**
     * Partially updates a objectiveCategory.
     *
     * @param objectiveCategoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ObjectiveCategoryDTO> partialUpdate(ObjectiveCategoryDTO objectiveCategoryDTO);

    /**
     * Get all the objectiveCategories.
     *
     * @return the list of entities.
     */
    List<ObjectiveCategoryDTO> findAll();

    /**
     * Get the "id" objectiveCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ObjectiveCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" objectiveCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
