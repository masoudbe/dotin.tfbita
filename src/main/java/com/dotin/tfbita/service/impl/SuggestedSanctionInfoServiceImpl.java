package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.SuggestedSanctionInfo;
import com.dotin.tfbita.repository.SuggestedSanctionInfoRepository;
import com.dotin.tfbita.service.SuggestedSanctionInfoService;
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
public class SuggestedSanctionInfoServiceImpl implements SuggestedSanctionInfoService {

    private static final Logger log = LoggerFactory.getLogger(SuggestedSanctionInfoServiceImpl.class);

    private final SuggestedSanctionInfoRepository suggestedSanctionInfoRepository;

    private final SuggestedSanctionInfoMapper suggestedSanctionInfoMapper;

    public SuggestedSanctionInfoServiceImpl(
        SuggestedSanctionInfoRepository suggestedSanctionInfoRepository,
        SuggestedSanctionInfoMapper suggestedSanctionInfoMapper
    ) {
        this.suggestedSanctionInfoRepository = suggestedSanctionInfoRepository;
        this.suggestedSanctionInfoMapper = suggestedSanctionInfoMapper;
    }

    @Override
    public SuggestedSanctionInfoDTO save(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO) {
        log.debug("Request to save SuggestedSanctionInfo : {}", suggestedSanctionInfoDTO);
        SuggestedSanctionInfo suggestedSanctionInfo = suggestedSanctionInfoMapper.toEntity(suggestedSanctionInfoDTO);
        suggestedSanctionInfo = suggestedSanctionInfoRepository.save(suggestedSanctionInfo);
        return suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);
    }

    @Override
    public SuggestedSanctionInfoDTO update(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO) {
        log.debug("Request to update SuggestedSanctionInfo : {}", suggestedSanctionInfoDTO);
        SuggestedSanctionInfo suggestedSanctionInfo = suggestedSanctionInfoMapper.toEntity(suggestedSanctionInfoDTO);
        suggestedSanctionInfo = suggestedSanctionInfoRepository.save(suggestedSanctionInfo);
        return suggestedSanctionInfoMapper.toDto(suggestedSanctionInfo);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<SuggestedSanctionInfoDTO> findAll() {
        log.debug("Request to get all SuggestedSanctionInfos");
        return suggestedSanctionInfoRepository
            .findAll()
            .stream()
            .map(suggestedSanctionInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SuggestedSanctionInfoDTO> findOne(Long id) {
        log.debug("Request to get SuggestedSanctionInfo : {}", id);
        return suggestedSanctionInfoRepository.findById(id).map(suggestedSanctionInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SuggestedSanctionInfo : {}", id);
        suggestedSanctionInfoRepository.deleteById(id);
    }
}
