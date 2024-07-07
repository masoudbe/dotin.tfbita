package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.TypeAttributeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.TypeAttribute}.
 */
public interface TypeAttributeService {
    /**
     * Save a typeAttribute.
     *
     * @param typeAttributeDTO the entity to save.
     * @return the persisted entity.
     */
    TypeAttributeDTO save(TypeAttributeDTO typeAttributeDTO);

    /**
     * Updates a typeAttribute.
     *
     * @param typeAttributeDTO the entity to update.
     * @return the persisted entity.
     */
    TypeAttributeDTO update(TypeAttributeDTO typeAttributeDTO);

    /**
     * Partially updates a typeAttribute.
     *
     * @param typeAttributeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypeAttributeDTO> partialUpdate(TypeAttributeDTO typeAttributeDTO);

    /**
     * Get all the typeAttributes.
     *
     * @return the list of entities.
     */
    List<TypeAttributeDTO> findAll();

    /**
     * Get the "id" typeAttribute.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeAttributeDTO> findOne(Long id);

    /**
     * Delete the "id" typeAttribute.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
