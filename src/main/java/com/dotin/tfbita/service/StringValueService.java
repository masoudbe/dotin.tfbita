package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.StringValueDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.StringValue}.
 */
public interface StringValueService {
    /**
     * Save a stringValue.
     *
     * @param stringValueDTO the entity to save.
     * @return the persisted entity.
     */
    StringValueDTO save(StringValueDTO stringValueDTO);

    /**
     * Updates a stringValue.
     *
     * @param stringValueDTO the entity to update.
     * @return the persisted entity.
     */
    StringValueDTO update(StringValueDTO stringValueDTO);

    /**
     * Partially updates a stringValue.
     *
     * @param stringValueDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StringValueDTO> partialUpdate(StringValueDTO stringValueDTO);

    /**
     * Get all the stringValues.
     *
     * @return the list of entities.
     */
    List<StringValueDTO> findAll();

    /**
     * Get the "id" stringValue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StringValueDTO> findOne(Long id);

    /**
     * Delete the "id" stringValue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
