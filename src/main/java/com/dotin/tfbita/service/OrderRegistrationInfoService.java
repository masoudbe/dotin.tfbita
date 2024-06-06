package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.OrderRegistrationInfo}.
 */
public interface OrderRegistrationInfoService {
    /**
     * Save a orderRegistrationInfo.
     *
     * @param orderRegistrationInfoDTO the entity to save.
     * @return the persisted entity.
     */
    OrderRegistrationInfoDTO save(OrderRegistrationInfoDTO orderRegistrationInfoDTO);

    /**
     * Updates a orderRegistrationInfo.
     *
     * @param orderRegistrationInfoDTO the entity to update.
     * @return the persisted entity.
     */
    OrderRegistrationInfoDTO update(OrderRegistrationInfoDTO orderRegistrationInfoDTO);

    /**
     * Partially updates a orderRegistrationInfo.
     *
     * @param orderRegistrationInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OrderRegistrationInfoDTO> partialUpdate(OrderRegistrationInfoDTO orderRegistrationInfoDTO);

    /**
     * Get all the orderRegistrationInfos.
     *
     * @return the list of entities.
     */
    List<OrderRegistrationInfoDTO> findAll();

    /**
     * Get the "id" orderRegistrationInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderRegistrationInfoDTO> findOne(Long id);

    /**
     * Delete the "id" orderRegistrationInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
