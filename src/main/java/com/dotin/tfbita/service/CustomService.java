package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.CustomDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.Custom}.
 */
public interface CustomService {
    /**
     * Save a custom.
     *
     * @param customDTO the entity to save.
     * @return the persisted entity.
     */
    CustomDTO save(CustomDTO customDTO);

    /**
     * Updates a custom.
     *
     * @param customDTO the entity to update.
     * @return the persisted entity.
     */
    CustomDTO update(CustomDTO customDTO);

    /**
     * Partially updates a custom.
     *
     * @param customDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CustomDTO> partialUpdate(CustomDTO customDTO);

    /**
     * Get all the customs.
     *
     * @return the list of entities.
     */
    List<CustomDTO> findAll();

    /**
     * Get the "id" custom.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomDTO> findOne(Long id);

    /**
     * Delete the "id" custom.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
