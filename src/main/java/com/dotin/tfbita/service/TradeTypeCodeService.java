package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.TradeTypeCodeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.TradeTypeCode}.
 */
public interface TradeTypeCodeService {
    /**
     * Save a tradeTypeCode.
     *
     * @param tradeTypeCodeDTO the entity to save.
     * @return the persisted entity.
     */
    TradeTypeCodeDTO save(TradeTypeCodeDTO tradeTypeCodeDTO);

    /**
     * Updates a tradeTypeCode.
     *
     * @param tradeTypeCodeDTO the entity to update.
     * @return the persisted entity.
     */
    TradeTypeCodeDTO update(TradeTypeCodeDTO tradeTypeCodeDTO);

    /**
     * Partially updates a tradeTypeCode.
     *
     * @param tradeTypeCodeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TradeTypeCodeDTO> partialUpdate(TradeTypeCodeDTO tradeTypeCodeDTO);

    /**
     * Get all the tradeTypeCodes.
     *
     * @return the list of entities.
     */
    List<TradeTypeCodeDTO> findAll();

    /**
     * Get the "id" tradeTypeCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TradeTypeCodeDTO> findOne(Long id);

    /**
     * Delete the "id" tradeTypeCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
