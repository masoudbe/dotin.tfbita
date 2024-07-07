package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.TradeTypeCode;
import com.dotin.tfbita.repository.TradeTypeCodeRepository;
import com.dotin.tfbita.service.TradeTypeCodeService;
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
public class TradeTypeCodeServiceImpl implements TradeTypeCodeService {

    private static final Logger log = LoggerFactory.getLogger(TradeTypeCodeServiceImpl.class);

    private final TradeTypeCodeRepository tradeTypeCodeRepository;

    private final TradeTypeCodeMapper tradeTypeCodeMapper;

    public TradeTypeCodeServiceImpl(TradeTypeCodeRepository tradeTypeCodeRepository, TradeTypeCodeMapper tradeTypeCodeMapper) {
        this.tradeTypeCodeRepository = tradeTypeCodeRepository;
        this.tradeTypeCodeMapper = tradeTypeCodeMapper;
    }

    @Override
    public TradeTypeCodeDTO save(TradeTypeCodeDTO tradeTypeCodeDTO) {
        log.debug("Request to save TradeTypeCode : {}", tradeTypeCodeDTO);
        TradeTypeCode tradeTypeCode = tradeTypeCodeMapper.toEntity(tradeTypeCodeDTO);
        tradeTypeCode = tradeTypeCodeRepository.save(tradeTypeCode);
        return tradeTypeCodeMapper.toDto(tradeTypeCode);
    }

    @Override
    public TradeTypeCodeDTO update(TradeTypeCodeDTO tradeTypeCodeDTO) {
        log.debug("Request to update TradeTypeCode : {}", tradeTypeCodeDTO);
        TradeTypeCode tradeTypeCode = tradeTypeCodeMapper.toEntity(tradeTypeCodeDTO);
        tradeTypeCode = tradeTypeCodeRepository.save(tradeTypeCode);
        return tradeTypeCodeMapper.toDto(tradeTypeCode);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<TradeTypeCodeDTO> findAll() {
        log.debug("Request to get all TradeTypeCodes");
        return tradeTypeCodeRepository.findAll().stream().map(tradeTypeCodeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TradeTypeCodeDTO> findOne(Long id) {
        log.debug("Request to get TradeTypeCode : {}", id);
        return tradeTypeCodeRepository.findById(id).map(tradeTypeCodeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TradeTypeCode : {}", id);
        tradeTypeCodeRepository.deleteById(id);
    }
}
