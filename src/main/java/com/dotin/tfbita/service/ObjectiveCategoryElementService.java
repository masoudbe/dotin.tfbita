package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.ObjectiveCategoryElementDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.ObjectiveCategoryElement}.
 */
public interface ObjectiveCategoryElementService {
    /**
     * Save a objectiveCategoryElement.
     *
     * @param objectiveCategoryElementDTO the entity to save.
     * @return the persisted entity.
     */
    ObjectiveCategoryElementDTO save(ObjectiveCategoryElementDTO objectiveCategoryElementDTO);

    /**
     * Updates a objectiveCategoryElement.
     *
     * @param objectiveCategoryElementDTO the entity to update.
     * @return the persisted entity.
     */
    ObjectiveCategoryElementDTO update(ObjectiveCategoryElementDTO objectiveCategoryElementDTO);

    /**
     * Partially updates a objectiveCategoryElement.
     *
     * @param objectiveCategoryElementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ObjectiveCategoryElementDTO> partialUpdate(ObjectiveCategoryElementDTO objectiveCategoryElementDTO);

    /**
     * Get all the objectiveCategoryElements.
     *
     * @return the list of entities.
     */
    List<ObjectiveCategoryElementDTO> findAll();

    /**
     * Get the "id" objectiveCategoryElement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ObjectiveCategoryElementDTO> findOne(Long id);

    /**
     * Delete the "id" objectiveCategoryElement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
