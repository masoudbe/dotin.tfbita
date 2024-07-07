package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.PaymentItemTypeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.PaymentItemType}.
 */
public interface PaymentItemTypeService {
    /**
     * Save a paymentItemType.
     *
     * @param paymentItemTypeDTO the entity to save.
     * @return the persisted entity.
     */
    PaymentItemTypeDTO save(PaymentItemTypeDTO paymentItemTypeDTO);

    /**
     * Updates a paymentItemType.
     *
     * @param paymentItemTypeDTO the entity to update.
     * @return the persisted entity.
     */
    PaymentItemTypeDTO update(PaymentItemTypeDTO paymentItemTypeDTO);

    /**
     * Partially updates a paymentItemType.
     *
     * @param paymentItemTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PaymentItemTypeDTO> partialUpdate(PaymentItemTypeDTO paymentItemTypeDTO);

    /**
     * Get all the paymentItemTypes.
     *
     * @return the list of entities.
     */
    List<PaymentItemTypeDTO> findAll();

    /**
     * Get the "id" paymentItemType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymentItemTypeDTO> findOne(Long id);

    /**
     * Delete the "id" paymentItemType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
