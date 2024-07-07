package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.PaymentCurrencyRateTypeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.PaymentCurrencyRateType}.
 */
public interface PaymentCurrencyRateTypeService {
    /**
     * Save a paymentCurrencyRateType.
     *
     * @param paymentCurrencyRateTypeDTO the entity to save.
     * @return the persisted entity.
     */
    PaymentCurrencyRateTypeDTO save(PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO);

    /**
     * Updates a paymentCurrencyRateType.
     *
     * @param paymentCurrencyRateTypeDTO the entity to update.
     * @return the persisted entity.
     */
    PaymentCurrencyRateTypeDTO update(PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO);

    /**
     * Partially updates a paymentCurrencyRateType.
     *
     * @param paymentCurrencyRateTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PaymentCurrencyRateTypeDTO> partialUpdate(PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO);

    /**
     * Get all the paymentCurrencyRateTypes.
     *
     * @return the list of entities.
     */
    List<PaymentCurrencyRateTypeDTO> findAll();

    /**
     * Get the "id" paymentCurrencyRateType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymentCurrencyRateTypeDTO> findOne(Long id);

    /**
     * Delete the "id" paymentCurrencyRateType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
