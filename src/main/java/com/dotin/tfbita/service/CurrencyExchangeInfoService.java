package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.CurrencyExchangeInfo;
import com.dotin.tfbita.repository.CurrencyExchangeInfoRepository;
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
public class CurrencyExchangeInfoService {

    private final Logger log = LoggerFactory.getLogger(CurrencyExchangeInfoService.class);

    private final CurrencyExchangeInfoRepository currencyExchangeInfoRepository;

    private final CurrencyExchangeInfoMapper currencyExchangeInfoMapper;

    public CurrencyExchangeInfoService(
        CurrencyExchangeInfoRepository currencyExchangeInfoRepository,
        CurrencyExchangeInfoMapper currencyExchangeInfoMapper
    ) {
        this.currencyExchangeInfoRepository = currencyExchangeInfoRepository;
        this.currencyExchangeInfoMapper = currencyExchangeInfoMapper;
    }

    /**
     * Save a currencyExchangeInfo.
     *
     * @param currencyExchangeInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public CurrencyExchangeInfoDTO save(CurrencyExchangeInfoDTO currencyExchangeInfoDTO) {
        log.debug("Request to save CurrencyExchangeInfo : {}", currencyExchangeInfoDTO);
        CurrencyExchangeInfo currencyExchangeInfo = currencyExchangeInfoMapper.toEntity(currencyExchangeInfoDTO);
        currencyExchangeInfo = currencyExchangeInfoRepository.save(currencyExchangeInfo);
        return currencyExchangeInfoMapper.toDto(currencyExchangeInfo);
    }

    /**
     * Update a currencyExchangeInfo.
     *
     * @param currencyExchangeInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public CurrencyExchangeInfoDTO update(CurrencyExchangeInfoDTO currencyExchangeInfoDTO) {
        log.debug("Request to update CurrencyExchangeInfo : {}", currencyExchangeInfoDTO);
        CurrencyExchangeInfo currencyExchangeInfo = currencyExchangeInfoMapper.toEntity(currencyExchangeInfoDTO);
        currencyExchangeInfo = currencyExchangeInfoRepository.save(currencyExchangeInfo);
        return currencyExchangeInfoMapper.toDto(currencyExchangeInfo);
    }

    /**
     * Partially update a currencyExchangeInfo.
     *
     * @param currencyExchangeInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the currencyExchangeInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CurrencyExchangeInfoDTO> findAll() {
        log.debug("Request to get all CurrencyExchangeInfos");
        return currencyExchangeInfoRepository
            .findAll()
            .stream()
            .map(currencyExchangeInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one currencyExchangeInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CurrencyExchangeInfoDTO> findOne(Long id) {
        log.debug("Request to get CurrencyExchangeInfo : {}", id);
        return currencyExchangeInfoRepository.findById(id).map(currencyExchangeInfoMapper::toDto);
    }

    /**
     * Delete the currencyExchangeInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CurrencyExchangeInfo : {}", id);
        currencyExchangeInfoRepository.deleteById(id);
    }
}
