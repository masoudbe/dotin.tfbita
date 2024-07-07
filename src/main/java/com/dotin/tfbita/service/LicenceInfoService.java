package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.LicenceInfo;
import com.dotin.tfbita.repository.LicenceInfoRepository;
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
public class LicenceInfoService {

    private final Logger log = LoggerFactory.getLogger(LicenceInfoService.class);

    private final LicenceInfoRepository licenceInfoRepository;

    private final LicenceInfoMapper licenceInfoMapper;

    public LicenceInfoService(LicenceInfoRepository licenceInfoRepository, LicenceInfoMapper licenceInfoMapper) {
        this.licenceInfoRepository = licenceInfoRepository;
        this.licenceInfoMapper = licenceInfoMapper;
    }

    /**
     * Save a licenceInfo.
     *
     * @param licenceInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public LicenceInfoDTO save(LicenceInfoDTO licenceInfoDTO) {
        log.debug("Request to save LicenceInfo : {}", licenceInfoDTO);
        LicenceInfo licenceInfo = licenceInfoMapper.toEntity(licenceInfoDTO);
        licenceInfo = licenceInfoRepository.save(licenceInfo);
        return licenceInfoMapper.toDto(licenceInfo);
    }

    /**
     * Update a licenceInfo.
     *
     * @param licenceInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public LicenceInfoDTO update(LicenceInfoDTO licenceInfoDTO) {
        log.debug("Request to update LicenceInfo : {}", licenceInfoDTO);
        LicenceInfo licenceInfo = licenceInfoMapper.toEntity(licenceInfoDTO);
        licenceInfo = licenceInfoRepository.save(licenceInfo);
        return licenceInfoMapper.toDto(licenceInfo);
    }

    /**
     * Partially update a licenceInfo.
     *
     * @param licenceInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the licenceInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LicenceInfoDTO> findAll() {
        log.debug("Request to get all LicenceInfos");
        return licenceInfoRepository.findAll().stream().map(licenceInfoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one licenceInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LicenceInfoDTO> findOne(Long id) {
        log.debug("Request to get LicenceInfo : {}", id);
        return licenceInfoRepository.findById(id).map(licenceInfoMapper::toDto);
    }

    /**
     * Delete the licenceInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LicenceInfo : {}", id);
        licenceInfoRepository.deleteById(id);
    }
}
