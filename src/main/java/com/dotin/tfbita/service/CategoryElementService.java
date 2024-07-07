package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.CategoryElementDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.CategoryElement}.
 */
public interface CategoryElementService {
    /**
     * Save a categoryElement.
     *
     * @param categoryElementDTO the entity to save.
     * @return the persisted entity.
     */
    CategoryElementDTO save(CategoryElementDTO categoryElementDTO);

    /**
     * Updates a categoryElement.
     *
     * @param categoryElementDTO the entity to update.
     * @return the persisted entity.
     */
    CategoryElementDTO update(CategoryElementDTO categoryElementDTO);

    /**
     * Partially updates a categoryElement.
     *
     * @param categoryElementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CategoryElementDTO> partialUpdate(CategoryElementDTO categoryElementDTO);

    /**
     * Get all the categoryElements.
     *
     * @return the list of entities.
     */
    List<CategoryElementDTO> findAll();

    /**
     * Get the "id" categoryElement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategoryElementDTO> findOne(Long id);

    /**
     * Delete the "id" categoryElement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
