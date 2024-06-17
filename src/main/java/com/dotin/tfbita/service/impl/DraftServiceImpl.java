package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftStatusInfo;
import com.dotin.tfbita.repository.DraftRepository;
import com.dotin.tfbita.service.DraftService;
import com.dotin.tfbita.service.DraftStatusInfoService;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
import com.dotin.tfbita.service.mapper.DraftMapper;
import com.dotin.tfbita.service.mapper.DraftStatusInfoMapper;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.Draft}.
 */
@Service
@Transactional
public class DraftServiceImpl implements DraftService {

    private final Logger log = LoggerFactory.getLogger(DraftServiceImpl.class);

    private final DraftRepository draftRepository;

    private final DraftMapper draftMapper;

    private final DraftStatusInfoMapper draftStatusInfoMapper;

    private final DraftStatusInfoService draftStatusInfoService;

    public DraftServiceImpl(
        DraftRepository draftRepository,
        DraftMapper draftMapper,
        DraftStatusInfoMapper draftStatusInfo,
        DraftStatusInfoService draftStatusInfoService
    ) {
        this.draftRepository = draftRepository;
        this.draftMapper = draftMapper;
        this.draftStatusInfoMapper = draftStatusInfo;
        this.draftStatusInfoService = draftStatusInfoService;
    }

    @Override
    public DraftDTO save(DraftDTO draftDTO) {
        log.debug("Request to save Draft : {}", draftDTO);
        Draft draft = draftMapper.toEntity(draftDTO);
        draft = draftRepository.save(draft);
        return draftMapper.toDto(draft);
    }

    @Override
    public DraftDTO update(DraftDTO draftDTO) {
        log.debug("Request to update Draft : {}", draftDTO);
        Draft draft = draftMapper.toEntity(draftDTO);
        draft = draftRepository.save(draft);
        return draftMapper.toDto(draft);
    }

    @Override
    public Optional<DraftDTO> partialUpdate(DraftDTO draftDTO) {
        log.debug("Request to partially update Draft : {}", draftDTO);

        return draftRepository
            .findById(draftDTO.getId())
            .map(existingDraft -> {
                draftMapper.partialUpdate(existingDraft, draftDTO);

                return existingDraft;
            })
            .map(draftRepository::save)
            .map(draftMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftDTO> findAll() {
        log.debug("Request to get all Drafts");
        return draftRepository.findAll().stream().map(draftMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftDTO> findOne(Long id) {
        log.debug("Request to get Draft : {}", id);
        return draftRepository.findById(id).map(draftMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Draft : {}", id);
        draftRepository.deleteById(id);
    }

    @Override
    public void confirm(Long id) {
        log.debug("Request to confirm Draft : {}", id);
        Optional<Draft> draft = draftRepository.findById(id);
        DraftStatusInfoDTO statusInfo = new DraftStatusInfoDTO();
        Set<DraftStatusInfo> statusInfoList = new HashSet<>();
        Draft result = new Draft();
        DraftDTO resultDTO = new DraftDTO();
        if (draft.isPresent()) {
            result = draft.get();
            resultDTO = draftMapper.toDto(result);
            statusInfo.setApproved(Boolean.TRUE);
            statusInfo.setStatusInfo(resultDTO);
            statusInfoList.add((draftStatusInfoMapper.toEntity(statusInfo)));
            result.setDraftStatusInfos(statusInfoList);
        }
        draftStatusInfoService.save(statusInfo);
        update(draftMapper.toDto(result));
        save(resultDTO);
    }
}
