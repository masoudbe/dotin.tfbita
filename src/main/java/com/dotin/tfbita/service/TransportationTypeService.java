package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.TransportationTypeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.TransportationType}.
 */
public interface TransportationTypeService {
    /**
     * Save a transportationType.
     *
     * @param transportationTypeDTO the entity to save.
     * @return the persisted entity.
     */
    TransportationTypeDTO save(TransportationTypeDTO transportationTypeDTO);

    /**
     * Updates a transportationType.
     *
     * @param transportationTypeDTO the entity to update.
     * @return the persisted entity.
     */
    TransportationTypeDTO update(TransportationTypeDTO transportationTypeDTO);

    /**
     * Partially updates a transportationType.
     *
     * @param transportationTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TransportationTypeDTO> partialUpdate(TransportationTypeDTO transportationTypeDTO);

    /**
     * Get all the transportationTypes.
     *
     * @return the list of entities.
     */
    List<TransportationTypeDTO> findAll();

    /**
     * Get the "id" transportationType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransportationTypeDTO> findOne(Long id);

    /**
     * Delete the "id" transportationType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
