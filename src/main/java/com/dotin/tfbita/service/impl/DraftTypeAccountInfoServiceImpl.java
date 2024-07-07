package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftTypeAccountInfo;
import com.dotin.tfbita.repository.DraftTypeAccountInfoRepository;
import com.dotin.tfbita.service.DraftTypeAccountInfoService;
import com.dotin.tfbita.service.dto.DraftTypeAccountInfoDTO;
import com.dotin.tfbita.service.mapper.DraftTypeAccountInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftTypeAccountInfo}.
 */
@Service
@Transactional
public class DraftTypeAccountInfoServiceImpl implements DraftTypeAccountInfoService {

    private static final Logger log = LoggerFactory.getLogger(DraftTypeAccountInfoServiceImpl.class);

    private final DraftTypeAccountInfoRepository draftTypeAccountInfoRepository;

    private final DraftTypeAccountInfoMapper draftTypeAccountInfoMapper;

    public DraftTypeAccountInfoServiceImpl(
        DraftTypeAccountInfoRepository draftTypeAccountInfoRepository,
        DraftTypeAccountInfoMapper draftTypeAccountInfoMapper
    ) {
        this.draftTypeAccountInfoRepository = draftTypeAccountInfoRepository;
        this.draftTypeAccountInfoMapper = draftTypeAccountInfoMapper;
    }

    @Override
    public DraftTypeAccountInfoDTO save(DraftTypeAccountInfoDTO draftTypeAccountInfoDTO) {
        log.debug("Request to save DraftTypeAccountInfo : {}", draftTypeAccountInfoDTO);
        DraftTypeAccountInfo draftTypeAccountInfo = draftTypeAccountInfoMapper.toEntity(draftTypeAccountInfoDTO);
        draftTypeAccountInfo = draftTypeAccountInfoRepository.save(draftTypeAccountInfo);
        return draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);
    }

    @Override
    public DraftTypeAccountInfoDTO update(DraftTypeAccountInfoDTO draftTypeAccountInfoDTO) {
        log.debug("Request to update DraftTypeAccountInfo : {}", draftTypeAccountInfoDTO);
        DraftTypeAccountInfo draftTypeAccountInfo = draftTypeAccountInfoMapper.toEntity(draftTypeAccountInfoDTO);
        draftTypeAccountInfo = draftTypeAccountInfoRepository.save(draftTypeAccountInfo);
        return draftTypeAccountInfoMapper.toDto(draftTypeAccountInfo);
    }

    @Override
    public Optional<DraftTypeAccountInfoDTO> partialUpdate(DraftTypeAccountInfoDTO draftTypeAccountInfoDTO) {
        log.debug("Request to partially update DraftTypeAccountInfo : {}", draftTypeAccountInfoDTO);

        return draftTypeAccountInfoRepository
            .findById(draftTypeAccountInfoDTO.getId())
            .map(existingDraftTypeAccountInfo -> {
                draftTypeAccountInfoMapper.partialUpdate(existingDraftTypeAccountInfo, draftTypeAccountInfoDTO);

                return existingDraftTypeAccountInfo;
            })
            .map(draftTypeAccountInfoRepository::save)
            .map(draftTypeAccountInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftTypeAccountInfoDTO> findAll() {
        log.debug("Request to get all DraftTypeAccountInfos");
        return draftTypeAccountInfoRepository
            .findAll()
            .stream()
            .map(draftTypeAccountInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftTypeAccountInfoDTO> findOne(Long id) {
        log.debug("Request to get DraftTypeAccountInfo : {}", id);
        return draftTypeAccountInfoRepository.findById(id).map(draftTypeAccountInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftTypeAccountInfo : {}", id);
        draftTypeAccountInfoRepository.deleteById(id);
    }
}
