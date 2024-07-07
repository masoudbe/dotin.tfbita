package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.TradeTypeCode;
import com.dotin.tfbita.repository.TradeTypeCodeRepository;
import com.dotin.tfbita.service.dto.TradeTypeCodeDTO;
import com.dotin.tfbita.service.mapper.TradeTypeCodeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.TradeTypeCode}.
 */
@Service
@Transactional
public class TradeTypeCodeService {

    private final Logger log = LoggerFactory.getLogger(TradeTypeCodeService.class);

    private final TradeTypeCodeRepository tradeTypeCodeRepository;

    private final TradeTypeCodeMapper tradeTypeCodeMapper;

    public TradeTypeCodeService(TradeTypeCodeRepository tradeTypeCodeRepository, TradeTypeCodeMapper tradeTypeCodeMapper) {
        this.tradeTypeCodeRepository = tradeTypeCodeRepository;
        this.tradeTypeCodeMapper = tradeTypeCodeMapper;
    }

    /**
     * Save a tradeTypeCode.
     *
     * @param tradeTypeCodeDTO the entity to save.
     * @return the persisted entity.
     */
    public TradeTypeCodeDTO save(TradeTypeCodeDTO tradeTypeCodeDTO) {
        log.debug("Request to save TradeTypeCode : {}", tradeTypeCodeDTO);
        TradeTypeCode tradeTypeCode = tradeTypeCodeMapper.toEntity(tradeTypeCodeDTO);
        tradeTypeCode = tradeTypeCodeRepository.save(tradeTypeCode);
        return tradeTypeCodeMapper.toDto(tradeTypeCode);
    }

    /**
     * Update a tradeTypeCode.
     *
     * @param tradeTypeCodeDTO the entity to save.
     * @return the persisted entity.
     */
    public TradeTypeCodeDTO update(TradeTypeCodeDTO tradeTypeCodeDTO) {
        log.debug("Request to update TradeTypeCode : {}", tradeTypeCodeDTO);
        TradeTypeCode tradeTypeCode = tradeTypeCodeMapper.toEntity(tradeTypeCodeDTO);
        tradeTypeCode = tradeTypeCodeRepository.save(tradeTypeCode);
        return tradeTypeCodeMapper.toDto(tradeTypeCode);
    }

    /**
     * Partially update a tradeTypeCode.
     *
     * @param tradeTypeCodeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TradeTypeCodeDTO> partialUpdate(TradeTypeCodeDTO tradeTypeCodeDTO) {
        log.debug("Request to partially update TradeTypeCode : {}", tradeTypeCodeDTO);

        return tradeTypeCodeRepository
            .findById(tradeTypeCodeDTO.getId())
            .map(existingTradeTypeCode -> {
                tradeTypeCodeMapper.partialUpdate(existingTradeTypeCode, tradeTypeCodeDTO);

                return existingTradeTypeCode;
            })
            .map(tradeTypeCodeRepository::save)
            .map(tradeTypeCodeMapper::toDto);
    }

    /**
     * Get all the tradeTypeCodes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TradeTypeCodeDTO> findAll() {
        log.debug("Request to get all TradeTypeCodes");
        return tradeTypeCodeRepository.findAll().stream().map(tradeTypeCodeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tradeTypeCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TradeTypeCodeDTO> findOne(Long id) {
        log.debug("Request to get TradeTypeCode : {}", id);
        return tradeTypeCodeRepository.findById(id).map(tradeTypeCodeMapper::toDto);
    }

    /**
     * Delete the tradeTypeCode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TradeTypeCode : {}", id);
        tradeTypeCodeRepository.deleteById(id);
    }
}
