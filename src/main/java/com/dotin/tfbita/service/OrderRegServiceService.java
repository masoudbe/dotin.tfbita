package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.OrderRegServiceDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.OrderRegService}.
 */
public interface OrderRegServiceService {
    /**
     * Save a orderRegService.
     *
     * @param orderRegServiceDTO the entity to save.
     * @return the persisted entity.
     */
    OrderRegServiceDTO save(OrderRegServiceDTO orderRegServiceDTO);

    /**
     * Updates a orderRegService.
     *
     * @param orderRegServiceDTO the entity to update.
     * @return the persisted entity.
     */
    OrderRegServiceDTO update(OrderRegServiceDTO orderRegServiceDTO);

    /**
     * Partially updates a orderRegService.
     *
     * @param orderRegServiceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OrderRegServiceDTO> partialUpdate(OrderRegServiceDTO orderRegServiceDTO);

    /**
     * Get all the orderRegServices.
     *
     * @return the list of entities.
     */
    List<OrderRegServiceDTO> findAll();

    /**
     * Get the "id" orderRegService.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderRegServiceDTO> findOne(Long id);

    /**
     * Delete the "id" orderRegService.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
