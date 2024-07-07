package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.AttributeValueGroupDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.AttributeValueGroup}.
 */
public interface AttributeValueGroupService {
    /**
     * Save a attributeValueGroup.
     *
     * @param attributeValueGroupDTO the entity to save.
     * @return the persisted entity.
     */
    AttributeValueGroupDTO save(AttributeValueGroupDTO attributeValueGroupDTO);

    /**
     * Updates a attributeValueGroup.
     *
     * @param attributeValueGroupDTO the entity to update.
     * @return the persisted entity.
     */
    AttributeValueGroupDTO update(AttributeValueGroupDTO attributeValueGroupDTO);

    /**
     * Partially updates a attributeValueGroup.
     *
     * @param attributeValueGroupDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AttributeValueGroupDTO> partialUpdate(AttributeValueGroupDTO attributeValueGroupDTO);

    /**
     * Get all the attributeValueGroups.
     *
     * @return the list of entities.
     */
    List<AttributeValueGroupDTO> findAll();

    /**
     * Get the "id" attributeValueGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AttributeValueGroupDTO> findOne(Long id);

    /**
     * Delete the "id" attributeValueGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
