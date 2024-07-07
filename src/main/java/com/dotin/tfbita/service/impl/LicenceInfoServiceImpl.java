package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.LicenceInfo;
import com.dotin.tfbita.repository.LicenceInfoRepository;
import com.dotin.tfbita.service.LicenceInfoService;
import com.dotin.tfbita.service.dto.LicenceInfoDTO;
import com.dotin.tfbita.service.mapper.LicenceInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.LicenceInfo}.
 */
@Service
@Transactional
public class LicenceInfoServiceImpl implements LicenceInfoService {

    private static final Logger log = LoggerFactory.getLogger(LicenceInfoServiceImpl.class);

    private final LicenceInfoRepository licenceInfoRepository;

    private final LicenceInfoMapper licenceInfoMapper;

    public LicenceInfoServiceImpl(LicenceInfoRepository licenceInfoRepository, LicenceInfoMapper licenceInfoMapper) {
        this.licenceInfoRepository = licenceInfoRepository;
        this.licenceInfoMapper = licenceInfoMapper;
    }

    @Override
    public LicenceInfoDTO save(LicenceInfoDTO licenceInfoDTO) {
        log.debug("Request to save LicenceInfo : {}", licenceInfoDTO);
        LicenceInfo licenceInfo = licenceInfoMapper.toEntity(licenceInfoDTO);
        licenceInfo = licenceInfoRepository.save(licenceInfo);
        return licenceInfoMapper.toDto(licenceInfo);
    }

    @Override
    public LicenceInfoDTO update(LicenceInfoDTO licenceInfoDTO) {
        log.debug("Request to update LicenceInfo : {}", licenceInfoDTO);
        LicenceInfo licenceInfo = licenceInfoMapper.toEntity(licenceInfoDTO);
        licenceInfo = licenceInfoRepository.save(licenceInfo);
        return licenceInfoMapper.toDto(licenceInfo);
    }

    @Override
    public Optional<LicenceInfoDTO> partialUpdate(LicenceInfoDTO licenceInfoDTO) {
        log.debug("Request to partially update LicenceInfo : {}", licenceInfoDTO);

        return licenceInfoRepository
            .findById(licenceInfoDTO.getId())
            .map(existingLicenceInfo -> {
                licenceInfoMapper.partialUpdate(existingLicenceInfo, licenceInfoDTO);

                return existingLicenceInfo;
            })
            .map(licenceInfoRepository::save)
            .map(licenceInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenceInfoDTO> findAll() {
        log.debug("Request to get all LicenceInfos");
        return licenceInfoRepository.findAll().stream().map(licenceInfoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LicenceInfoDTO> findOne(Long id) {
        log.debug("Request to get LicenceInfo : {}", id);
        return licenceInfoRepository.findById(id).map(licenceInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LicenceInfo : {}", id);
        licenceInfoRepository.deleteById(id);
    }
}
