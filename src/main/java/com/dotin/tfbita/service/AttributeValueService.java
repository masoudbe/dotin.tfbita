package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.AttributeValueDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.AttributeValue}.
 */
public interface AttributeValueService {
    /**
     * Save a attributeValue.
     *
     * @param attributeValueDTO the entity to save.
     * @return the persisted entity.
     */
    AttributeValueDTO save(AttributeValueDTO attributeValueDTO);

    /**
     * Updates a attributeValue.
     *
     * @param attributeValueDTO the entity to update.
     * @return the persisted entity.
     */
    AttributeValueDTO update(AttributeValueDTO attributeValueDTO);

    /**
     * Partially updates a attributeValue.
     *
     * @param attributeValueDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AttributeValueDTO> partialUpdate(AttributeValueDTO attributeValueDTO);

    /**
     * Get all the attributeValues.
     *
     * @return the list of entities.
     */
    List<AttributeValueDTO> findAll();

    /**
     * Get the "id" attributeValue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AttributeValueDTO> findOne(Long id);

    /**
     * Delete the "id" attributeValue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
