package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.AuditCompanyInfo;
import com.dotin.tfbita.repository.AuditCompanyInfoRepository;
import com.dotin.tfbita.service.AuditCompanyInfoService;
import com.dotin.tfbita.service.dto.AuditCompanyInfoDTO;
import com.dotin.tfbita.service.mapper.AuditCompanyInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.AuditCompanyInfo}.
 */
@Service
@Transactional
public class AuditCompanyInfoServiceImpl implements AuditCompanyInfoService {

    private static final Logger log = LoggerFactory.getLogger(AuditCompanyInfoServiceImpl.class);

    private final AuditCompanyInfoRepository auditCompanyInfoRepository;

    private final AuditCompanyInfoMapper auditCompanyInfoMapper;

    public AuditCompanyInfoServiceImpl(
        AuditCompanyInfoRepository auditCompanyInfoRepository,
        AuditCompanyInfoMapper auditCompanyInfoMapper
    ) {
        this.auditCompanyInfoRepository = auditCompanyInfoRepository;
        this.auditCompanyInfoMapper = auditCompanyInfoMapper;
    }

    @Override
    public AuditCompanyInfoDTO save(AuditCompanyInfoDTO auditCompanyInfoDTO) {
        log.debug("Request to save AuditCompanyInfo : {}", auditCompanyInfoDTO);
        AuditCompanyInfo auditCompanyInfo = auditCompanyInfoMapper.toEntity(auditCompanyInfoDTO);
        auditCompanyInfo = auditCompanyInfoRepository.save(auditCompanyInfo);
        return auditCompanyInfoMapper.toDto(auditCompanyInfo);
    }

    @Override
    public AuditCompanyInfoDTO update(AuditCompanyInfoDTO auditCompanyInfoDTO) {
        log.debug("Request to update AuditCompanyInfo : {}", auditCompanyInfoDTO);
        AuditCompanyInfo auditCompanyInfo = auditCompanyInfoMapper.toEntity(auditCompanyInfoDTO);
        auditCompanyInfo = auditCompanyInfoRepository.save(auditCompanyInfo);
        return auditCompanyInfoMapper.toDto(auditCompanyInfo);
    }

    @Override
    public Optional<AuditCompanyInfoDTO> partialUpdate(AuditCompanyInfoDTO auditCompanyInfoDTO) {
        log.debug("Request to partially update AuditCompanyInfo : {}", auditCompanyInfoDTO);

        return auditCompanyInfoRepository
            .findById(auditCompanyInfoDTO.getId())
            .map(existingAuditCompanyInfo -> {
                auditCompanyInfoMapper.partialUpdate(existingAuditCompanyInfo, auditCompanyInfoDTO);

                return existingAuditCompanyInfo;
            })
            .map(auditCompanyInfoRepository::save)
            .map(auditCompanyInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditCompanyInfoDTO> findAll() {
        log.debug("Request to get all AuditCompanyInfos");
        return auditCompanyInfoRepository
            .findAll()
            .stream()
            .map(auditCompanyInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuditCompanyInfoDTO> findOne(Long id) {
        log.debug("Request to get AuditCompanyInfo : {}", id);
        return auditCompanyInfoRepository.findById(id).map(auditCompanyInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuditCompanyInfo : {}", id);
        auditCompanyInfoRepository.deleteById(id);
    }
}
