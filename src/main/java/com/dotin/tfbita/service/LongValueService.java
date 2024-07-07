package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.LongValueDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.LongValue}.
 */
public interface LongValueService {
    /**
     * Save a longValue.
     *
     * @param longValueDTO the entity to save.
     * @return the persisted entity.
     */
    LongValueDTO save(LongValueDTO longValueDTO);

    /**
     * Updates a longValue.
     *
     * @param longValueDTO the entity to update.
     * @return the persisted entity.
     */
    LongValueDTO update(LongValueDTO longValueDTO);

    /**
     * Partially updates a longValue.
     *
     * @param longValueDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LongValueDTO> partialUpdate(LongValueDTO longValueDTO);

    /**
     * Get all the longValues.
     *
     * @return the list of entities.
     */
    List<LongValueDTO> findAll();

    /**
     * Get the "id" longValue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LongValueDTO> findOne(Long id);

    /**
     * Delete the "id" longValue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
