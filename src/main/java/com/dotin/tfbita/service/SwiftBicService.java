package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.SwiftBicDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.SwiftBic}.
 */
public interface SwiftBicService {
    /**
     * Save a swiftBic.
     *
     * @param swiftBicDTO the entity to save.
     * @return the persisted entity.
     */
    SwiftBicDTO save(SwiftBicDTO swiftBicDTO);

    /**
     * Updates a swiftBic.
     *
     * @param swiftBicDTO the entity to update.
     * @return the persisted entity.
     */
    SwiftBicDTO update(SwiftBicDTO swiftBicDTO);

    /**
     * Partially updates a swiftBic.
     *
     * @param swiftBicDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SwiftBicDTO> partialUpdate(SwiftBicDTO swiftBicDTO);

    /**
     * Get all the swiftBics.
     *
     * @return the list of entities.
     */
    List<SwiftBicDTO> findAll();

    /**
     * Get the "id" swiftBic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SwiftBicDTO> findOne(Long id);

    /**
     * Delete the "id" swiftBic.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
