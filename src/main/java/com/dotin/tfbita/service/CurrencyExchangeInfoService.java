package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.CurrencyExchangeInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.CurrencyExchangeInfo}.
 */
public interface CurrencyExchangeInfoService {
    /**
     * Save a currencyExchangeInfo.
     *
     * @param currencyExchangeInfoDTO the entity to save.
     * @return the persisted entity.
     */
    CurrencyExchangeInfoDTO save(CurrencyExchangeInfoDTO currencyExchangeInfoDTO);

    /**
     * Updates a currencyExchangeInfo.
     *
     * @param currencyExchangeInfoDTO the entity to update.
     * @return the persisted entity.
     */
    CurrencyExchangeInfoDTO update(CurrencyExchangeInfoDTO currencyExchangeInfoDTO);

    /**
     * Partially updates a currencyExchangeInfo.
     *
     * @param currencyExchangeInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CurrencyExchangeInfoDTO> partialUpdate(CurrencyExchangeInfoDTO currencyExchangeInfoDTO);

    /**
     * Get all the currencyExchangeInfos.
     *
     * @return the list of entities.
     */
    List<CurrencyExchangeInfoDTO> findAll();

    /**
     * Get the "id" currencyExchangeInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CurrencyExchangeInfoDTO> findOne(Long id);

    /**
     * Delete the "id" currencyExchangeInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
