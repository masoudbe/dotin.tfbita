package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftProductInfo;
import com.dotin.tfbita.repository.DraftProductInfoRepository;
import com.dotin.tfbita.service.DraftProductInfoService;
import com.dotin.tfbita.service.dto.DraftProductInfoDTO;
import com.dotin.tfbita.service.mapper.DraftProductInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftProductInfo}.
 */
@Service
@Transactional
public class DraftProductInfoServiceImpl implements DraftProductInfoService {

    private static final Logger log = LoggerFactory.getLogger(DraftProductInfoServiceImpl.class);

    private final DraftProductInfoRepository draftProductInfoRepository;

    private final DraftProductInfoMapper draftProductInfoMapper;

    public DraftProductInfoServiceImpl(
        DraftProductInfoRepository draftProductInfoRepository,
        DraftProductInfoMapper draftProductInfoMapper
    ) {
        this.draftProductInfoRepository = draftProductInfoRepository;
        this.draftProductInfoMapper = draftProductInfoMapper;
    }

    @Override
    public DraftProductInfoDTO save(DraftProductInfoDTO draftProductInfoDTO) {
        log.debug("Request to save DraftProductInfo : {}", draftProductInfoDTO);
        DraftProductInfo draftProductInfo = draftProductInfoMapper.toEntity(draftProductInfoDTO);
        draftProductInfo = draftProductInfoRepository.save(draftProductInfo);
        return draftProductInfoMapper.toDto(draftProductInfo);
    }

    @Override
    public DraftProductInfoDTO update(DraftProductInfoDTO draftProductInfoDTO) {
        log.debug("Request to update DraftProductInfo : {}", draftProductInfoDTO);
        DraftProductInfo draftProductInfo = draftProductInfoMapper.toEntity(draftProductInfoDTO);
        draftProductInfo = draftProductInfoRepository.save(draftProductInfo);
        return draftProductInfoMapper.toDto(draftProductInfo);
    }

    @Override
    public Optional<DraftProductInfoDTO> partialUpdate(DraftProductInfoDTO draftProductInfoDTO) {
        log.debug("Request to partially update DraftProductInfo : {}", draftProductInfoDTO);

        return draftProductInfoRepository
            .findById(draftProductInfoDTO.getId())
            .map(existingDraftProductInfo -> {
                draftProductInfoMapper.partialUpdate(existingDraftProductInfo, draftProductInfoDTO);

                return existingDraftProductInfo;
            })
            .map(draftProductInfoRepository::save)
            .map(draftProductInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftProductInfoDTO> findAll() {
        log.debug("Request to get all DraftProductInfos");
        return draftProductInfoRepository
            .findAll()
            .stream()
            .map(draftProductInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftProductInfoDTO> findOne(Long id) {
        log.debug("Request to get DraftProductInfo : {}", id);
        return draftProductInfoRepository.findById(id).map(draftProductInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftProductInfo : {}", id);
        draftProductInfoRepository.deleteById(id);
    }
}
