package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.OrderRegistrationInfo}.
 */
public interface OrderRegistrationInfoService {
    /**
     * Save a orderRegistrationInfo.
     *
     * @param orderRegistrationInfo the entity to save.
     * @return the persisted entity.
     */
    OrderRegistrationInfo save(OrderRegistrationInfo orderRegistrationInfo);

    /**
     * Updates a orderRegistrationInfo.
     *
     * @param orderRegistrationInfo the entity to update.
     * @return the persisted entity.
     */
    OrderRegistrationInfo update(OrderRegistrationInfo orderRegistrationInfo);

    /**
     * Partially updates a orderRegistrationInfo.
     *
     * @param orderRegistrationInfo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OrderRegistrationInfo> partialUpdate(OrderRegistrationInfo orderRegistrationInfo);

    /**
     * Get all the orderRegistrationInfos.
     *
     * @return the list of entities.
     */
    List<OrderRegistrationInfo> findAll();

    /**
     * Get the "id" orderRegistrationInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderRegistrationInfo> findOne(Long id);

    /**
     * Delete the "id" orderRegistrationInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
