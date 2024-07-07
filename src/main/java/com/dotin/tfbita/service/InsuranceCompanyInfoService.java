package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.InsuranceCompanyInfo;
import com.dotin.tfbita.repository.InsuranceCompanyInfoRepository;
import com.dotin.tfbita.service.dto.InsuranceCompanyInfoDTO;
import com.dotin.tfbita.service.mapper.InsuranceCompanyInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.InsuranceCompanyInfo}.
 */
@Service
@Transactional
public class InsuranceCompanyInfoService {

    private final Logger log = LoggerFactory.getLogger(InsuranceCompanyInfoService.class);

    private final InsuranceCompanyInfoRepository insuranceCompanyInfoRepository;

    private final InsuranceCompanyInfoMapper insuranceCompanyInfoMapper;

    public InsuranceCompanyInfoService(
        InsuranceCompanyInfoRepository insuranceCompanyInfoRepository,
        InsuranceCompanyInfoMapper insuranceCompanyInfoMapper
    ) {
        this.insuranceCompanyInfoRepository = insuranceCompanyInfoRepository;
        this.insuranceCompanyInfoMapper = insuranceCompanyInfoMapper;
    }

    /**
     * Save a insuranceCompanyInfo.
     *
     * @param insuranceCompanyInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public InsuranceCompanyInfoDTO save(InsuranceCompanyInfoDTO insuranceCompanyInfoDTO) {
        log.debug("Request to save InsuranceCompanyInfo : {}", insuranceCompanyInfoDTO);
        InsuranceCompanyInfo insuranceCompanyInfo = insuranceCompanyInfoMapper.toEntity(insuranceCompanyInfoDTO);
        insuranceCompanyInfo = insuranceCompanyInfoRepository.save(insuranceCompanyInfo);
        return insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);
    }

    /**
     * Update a insuranceCompanyInfo.
     *
     * @param insuranceCompanyInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public InsuranceCompanyInfoDTO update(InsuranceCompanyInfoDTO insuranceCompanyInfoDTO) {
        log.debug("Request to update InsuranceCompanyInfo : {}", insuranceCompanyInfoDTO);
        InsuranceCompanyInfo insuranceCompanyInfo = insuranceCompanyInfoMapper.toEntity(insuranceCompanyInfoDTO);
        insuranceCompanyInfo = insuranceCompanyInfoRepository.save(insuranceCompanyInfo);
        return insuranceCompanyInfoMapper.toDto(insuranceCompanyInfo);
    }

    /**
     * Partially update a insuranceCompanyInfo.
     *
     * @param insuranceCompanyInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InsuranceCompanyInfoDTO> partialUpdate(InsuranceCompanyInfoDTO insuranceCompanyInfoDTO) {
        log.debug("Request to partially update InsuranceCompanyInfo : {}", insuranceCompanyInfoDTO);

        return insuranceCompanyInfoRepository
            .findById(insuranceCompanyInfoDTO.getId())
            .map(existingInsuranceCompanyInfo -> {
                insuranceCompanyInfoMapper.partialUpdate(existingInsuranceCompanyInfo, insuranceCompanyInfoDTO);

                return existingInsuranceCompanyInfo;
            })
            .map(insuranceCompanyInfoRepository::save)
            .map(insuranceCompanyInfoMapper::toDto);
    }

    /**
     * Get all the insuranceCompanyInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InsuranceCompanyInfoDTO> findAll() {
        log.debug("Request to get all InsuranceCompanyInfos");
        return insuranceCompanyInfoRepository
            .findAll()
            .stream()
            .map(insuranceCompanyInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one insuranceCompanyInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InsuranceCompanyInfoDTO> findOne(Long id) {
        log.debug("Request to get InsuranceCompanyInfo : {}", id);
        return insuranceCompanyInfoRepository.findById(id).map(insuranceCompanyInfoMapper::toDto);
    }

    /**
     * Delete the insuranceCompanyInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InsuranceCompanyInfo : {}", id);
        insuranceCompanyInfoRepository.deleteById(id);
    }
}
