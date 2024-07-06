package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.SuggestedSanctionInfo;
import com.dotin.tfbita.repository.SuggestedSanctionInfoRepository;
import com.dotin.tfbita.service.dto.SuggestedSanctionInfoDTO;
import com.dotin.tfbita.service.mapper.SuggestedSanctionInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.SuggestedSanctionInfo}.
 */
@Service
@Transactional
public class SuggestedSanctionInfoService {

    private final Logger log = LoggerFactory.getLogger(SuggestedSanctionInfoService.class);

    private final SuggestedSanctionInfoRepository suggestedSanctionInfoRepository;

    private final SuggestedSanctionInfoMapper suggestedSanctionInfoMapper;

    public SuggestedSanctionInfoService(
        SuggestedSanctionInfoRepository suggestedSanctionInfoRepository,
        SuggestedSanctionInfoMapper suggestedSanctionInfoMapper
    ) {
        this.suggestedSanctionInfoRepository = suggestedSanctionInfoRepository;
        this.suggestedSanctionInfoMapper = suggestedSanctionInfoMapper;
    }

    /**
     * Save a suggestedSanctionInfo.
     *
     * @param suggestedSanctionInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public SuggestedSanctionInfoDTO save(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO) {
        log.debug("Request to save SuggestedSanctionInfo : {}", suggestedSanctionInfoDTO);
        SuggestedSanctionInfo suggestedSanctionInfo = suggestedSanctionInfoMapper.toEntity(suggestedSanctionInfoDTO);
        suggestedSanctionInfo = suggestedSanctionInfoRepository.save(suggestedSanctionInfo);
        return suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);
    }

    /**
     * Update a suggestedSanctionInfo.
     *
     * @param suggestedSanctionInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public SuggestedSanctionInfoDTO update(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO) {
        log.debug("Request to update SuggestedSanctionInfo : {}", suggestedSanctionInfoDTO);
        SuggestedSanctionInfo suggestedSanctionInfo = suggestedSanctionInfoMapper.toEntity(suggestedSanctionInfoDTO);
        suggestedSanctionInfo = suggestedSanctionInfoRepository.save(suggestedSanctionInfo);
        return suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);
    }

    /**
     * Partially update a suggestedSanctionInfo.
     *
     * @param suggestedSanctionInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SuggestedSanctionInfoDTO> partialUpdate(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO) {
        log.debug("Request to partially update SuggestedSanctionInfo : {}", suggestedSanctionInfoDTO);

        return suggestedSanctionInfoRepository
            .findById(suggestedSanctionInfoDTO.getId())
            .map(existingSuggestedSanctionInfo -> {
                suggestedSanctionInfoMapper.partialUpdate(existingSuggestedSanctionInfo, suggestedSanctionInfoDTO);

                return existingSuggestedSanctionInfo;
            })
            .map(suggestedSanctionInfoRepository::save)
            .map(suggestedSanctionInfoMapper::toDto);
    }

    /**
     * Get all the suggestedSanctionInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SuggestedSanctionInfoDTO> findAll() {
        log.debug("Request to get all SuggestedSanctionInfos");
        return suggestedSanctionInfoRepository
            .findAll()
            .stream()
            .map(suggestedSanctionInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one suggestedSanctionInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SuggestedSanctionInfoDTO> findOne(Long id) {
        log.debug("Request to get SuggestedSanctionInfo : {}", id);
        return suggestedSanctionInfoRepository.findById(id).map(suggestedSanctionInfoMapper::toDto);
    }

    /**
     * Delete the suggestedSanctionInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SuggestedSanctionInfo : {}", id);
        suggestedSanctionInfoRepository.deleteById(id);
    }
}
