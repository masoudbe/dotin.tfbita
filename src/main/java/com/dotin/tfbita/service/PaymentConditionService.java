package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.PaymentConditionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.PaymentCondition}.
 */
public interface PaymentConditionService {
    /**
     * Save a paymentCondition.
     *
     * @param paymentConditionDTO the entity to save.
     * @return the persisted entity.
     */
    PaymentConditionDTO save(PaymentConditionDTO paymentConditionDTO);

    /**
     * Updates a paymentCondition.
     *
     * @param paymentConditionDTO the entity to update.
     * @return the persisted entity.
     */
    PaymentConditionDTO update(PaymentConditionDTO paymentConditionDTO);

    /**
     * Partially updates a paymentCondition.
     *
     * @param paymentConditionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PaymentConditionDTO> partialUpdate(PaymentConditionDTO paymentConditionDTO);

    /**
     * Get all the paymentConditions.
     *
     * @return the list of entities.
     */
    List<PaymentConditionDTO> findAll();

    /**
     * Get the "id" paymentCondition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymentConditionDTO> findOne(Long id);

    /**
     * Delete the "id" paymentCondition.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
