package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.CurrencyExchangeInfo;
import com.dotin.tfbita.repository.CurrencyExchangeInfoRepository;
import com.dotin.tfbita.service.CurrencyExchangeInfoService;
import com.dotin.tfbita.service.dto.CurrencyExchangeInfoDTO;
import com.dotin.tfbita.service.mapper.CurrencyExchangeInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.CurrencyExchangeInfo}.
 */
@Service
@Transactional
public class CurrencyExchangeInfoServiceImpl implements CurrencyExchangeInfoService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeInfoServiceImpl.class);

    private final CurrencyExchangeInfoRepository currencyExchangeInfoRepository;

    private final CurrencyExchangeInfoMapper currencyExchangeInfoMapper;

    public CurrencyExchangeInfoServiceImpl(
        CurrencyExchangeInfoRepository currencyExchangeInfoRepository,
        CurrencyExchangeInfoMapper currencyExchangeInfoMapper
    ) {
        this.currencyExchangeInfoRepository = currencyExchangeInfoRepository;
        this.currencyExchangeInfoMapper = currencyExchangeInfoMapper;
    }

    @Override
    public CurrencyExchangeInfoDTO save(CurrencyExchangeInfoDTO currencyExchangeInfoDTO) {
        log.debug("Request to save CurrencyExchangeInfo : {}", currencyExchangeInfoDTO);
        CurrencyExchangeInfo currencyExchangeInfo = currencyExchangeInfoMapper.toEntity(currencyExchangeInfoDTO);
        currencyExchangeInfo = currencyExchangeInfoRepository.save(currencyExchangeInfo);
        return currencyExchangeInfoMapper.toDto(currencyExchangeInfo);
    }

    @Override
    public CurrencyExchangeInfoDTO update(CurrencyExchangeInfoDTO currencyExchangeInfoDTO) {
        log.debug("Request to update CurrencyExchangeInfo : {}", currencyExchangeInfoDTO);
        CurrencyExchangeInfo currencyExchangeInfo = currencyExchangeInfoMapper.toEntity(currencyExchangeInfoDTO);
        currencyExchangeInfo = currencyExchangeInfoRepository.save(currencyExchangeInfo);
        return currencyExchangeInfoMapper.toDto(currencyExchangeInfo);
    }

    @Override
    public Optional<CurrencyExchangeInfoDTO> partialUpdate(CurrencyExchangeInfoDTO currencyExchangeInfoDTO) {
        log.debug("Request to partially update CurrencyExchangeInfo : {}", currencyExchangeInfoDTO);

        return currencyExchangeInfoRepository
            .findById(currencyExchangeInfoDTO.getId())
            .map(existingCurrencyExchangeInfo -> {
                currencyExchangeInfoMapper.partialUpdate(existingCurrencyExchangeInfo, currencyExchangeInfoDTO);

                return existingCurrencyExchangeInfo;
            })
            .map(currencyExchangeInfoRepository::save)
            .map(currencyExchangeInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyExchangeInfoDTO> findAll() {
        log.debug("Request to get all CurrencyExchangeInfos");
        return currencyExchangeInfoRepository
            .findAll()
            .stream()
            .map(currencyExchangeInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CurrencyExchangeInfoDTO> findOne(Long id) {
        log.debug("Request to get CurrencyExchangeInfo : {}", id);
        return currencyExchangeInfoRepository.findById(id).map(currencyExchangeInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CurrencyExchangeInfo : {}", id);
        currencyExchangeInfoRepository.deleteById(id);
    }
}
