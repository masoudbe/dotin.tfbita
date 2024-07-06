package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.CreditTypeConditionInfo;
import com.dotin.tfbita.repository.CreditTypeConditionInfoRepository;
import com.dotin.tfbita.service.dto.CreditTypeConditionInfoDTO;
import com.dotin.tfbita.service.mapper.CreditTypeConditionInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.CreditTypeConditionInfo}.
 */
@Service
@Transactional
public class CreditTypeConditionInfoService {

    private final Logger log = LoggerFactory.getLogger(CreditTypeConditionInfoService.class);

    private final CreditTypeConditionInfoRepository creditTypeConditionInfoRepository;

    private final CreditTypeConditionInfoMapper creditTypeConditionInfoMapper;

    public CreditTypeConditionInfoService(
        CreditTypeConditionInfoRepository creditTypeConditionInfoRepository,
        CreditTypeConditionInfoMapper creditTypeConditionInfoMapper
    ) {
        this.creditTypeConditionInfoRepository = creditTypeConditionInfoRepository;
        this.creditTypeConditionInfoMapper = creditTypeConditionInfoMapper;
    }

    /**
     * Save a creditTypeConditionInfo.
     *
     * @param creditTypeConditionInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public CreditTypeConditionInfoDTO save(CreditTypeConditionInfoDTO creditTypeConditionInfoDTO) {
        log.debug("Request to save CreditTypeConditionInfo : {}", creditTypeConditionInfoDTO);
        CreditTypeConditionInfo creditTypeConditionInfo = creditTypeConditionInfoMapper.toEntity(creditTypeConditionInfoDTO);
        creditTypeConditionInfo = creditTypeConditionInfoRepository.save(creditTypeConditionInfo);
        return creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);
    }

    /**
     * Update a creditTypeConditionInfo.
     *
     * @param creditTypeConditionInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public CreditTypeConditionInfoDTO update(CreditTypeConditionInfoDTO creditTypeConditionInfoDTO) {
        log.debug("Request to update CreditTypeConditionInfo : {}", creditTypeConditionInfoDTO);
        CreditTypeConditionInfo creditTypeConditionInfo = creditTypeConditionInfoMapper.toEntity(creditTypeConditionInfoDTO);
        creditTypeConditionInfo = creditTypeConditionInfoRepository.save(creditTypeConditionInfo);
        return creditTypeConditionInfoMapper.toDto(creditTypeConditionInfo);
    }

    /**
     * Partially update a creditTypeConditionInfo.
     *
     * @param creditTypeConditionInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreditTypeConditionInfoDTO> partialUpdate(CreditTypeConditionInfoDTO creditTypeConditionInfoDTO) {
        log.debug("Request to partially update CreditTypeConditionInfo : {}", creditTypeConditionInfoDTO);

        return creditTypeConditionInfoRepository
            .findById(creditTypeConditionInfoDTO.getId())
            .map(existingCreditTypeConditionInfo -> {
                creditTypeConditionInfoMapper.partialUpdate(existingCreditTypeConditionInfo, creditTypeConditionInfoDTO);

                return existingCreditTypeConditionInfo;
            })
            .map(creditTypeConditionInfoRepository::save)
            .map(creditTypeConditionInfoMapper::toDto);
    }

    /**
     * Get all the creditTypeConditionInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreditTypeConditionInfoDTO> findAll() {
        log.debug("Request to get all CreditTypeConditionInfos");
        return creditTypeConditionInfoRepository
            .findAll()
            .stream()
            .map(creditTypeConditionInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one creditTypeConditionInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreditTypeConditionInfoDTO> findOne(Long id) {
        log.debug("Request to get CreditTypeConditionInfo : {}", id);
        return creditTypeConditionInfoRepository.findById(id).map(creditTypeConditionInfoMapper::toDto);
    }

    /**
     * Delete the creditTypeConditionInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreditTypeConditionInfo : {}", id);
        creditTypeConditionInfoRepository.deleteById(id);
    }
}
