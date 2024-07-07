package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftAccountInfo;
import com.dotin.tfbita.repository.DraftAccountInfoRepository;
import com.dotin.tfbita.service.DraftAccountInfoService;
import com.dotin.tfbita.service.dto.DraftAccountInfoDTO;
import com.dotin.tfbita.service.mapper.DraftAccountInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftAccountInfo}.
 */
@Service
@Transactional
public class DraftAccountInfoServiceImpl implements DraftAccountInfoService {

    private static final Logger log = LoggerFactory.getLogger(DraftAccountInfoServiceImpl.class);

    private final DraftAccountInfoRepository draftAccountInfoRepository;

    private final DraftAccountInfoMapper draftAccountInfoMapper;

    public DraftAccountInfoServiceImpl(
        DraftAccountInfoRepository draftAccountInfoRepository,
        DraftAccountInfoMapper draftAccountInfoMapper
    ) {
        this.draftAccountInfoRepository = draftAccountInfoRepository;
        this.draftAccountInfoMapper = draftAccountInfoMapper;
    }

    @Override
    public DraftAccountInfoDTO save(DraftAccountInfoDTO draftAccountInfoDTO) {
        log.debug("Request to save DraftAccountInfo : {}", draftAccountInfoDTO);
        DraftAccountInfo draftAccountInfo = draftAccountInfoMapper.toEntity(draftAccountInfoDTO);
        draftAccountInfo = draftAccountInfoRepository.save(draftAccountInfo);
        return draftAccountInfoMapper.toDto(draftAccountInfo);
    }

    @Override
    public DraftAccountInfoDTO update(DraftAccountInfoDTO draftAccountInfoDTO) {
        log.debug("Request to update DraftAccountInfo : {}", draftAccountInfoDTO);
        DraftAccountInfo draftAccountInfo = draftAccountInfoMapper.toEntity(draftAccountInfoDTO);
        draftAccountInfo = draftAccountInfoRepository.save(draftAccountInfo);
        return draftAccountInfoMapper.toDto(draftAccountInfo);
    }

    @Override
    public Optional<DraftAccountInfoDTO> partialUpdate(DraftAccountInfoDTO draftAccountInfoDTO) {
        log.debug("Request to partially update DraftAccountInfo : {}", draftAccountInfoDTO);

        return draftAccountInfoRepository
            .findById(draftAccountInfoDTO.getId())
            .map(existingDraftAccountInfo -> {
                draftAccountInfoMapper.partialUpdate(existingDraftAccountInfo, draftAccountInfoDTO);

                return existingDraftAccountInfo;
            })
            .map(draftAccountInfoRepository::save)
            .map(draftAccountInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftAccountInfoDTO> findAll() {
        log.debug("Request to get all DraftAccountInfos");
        return draftAccountInfoRepository
            .findAll()
            .stream()
            .map(draftAccountInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftAccountInfoDTO> findOne(Long id) {
        log.debug("Request to get DraftAccountInfo : {}", id);
        return draftAccountInfoRepository.findById(id).map(draftAccountInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftAccountInfo : {}", id);
        draftAccountInfoRepository.deleteById(id);
    }
}
