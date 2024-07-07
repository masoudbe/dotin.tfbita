package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.BasicInfo;
import com.dotin.tfbita.repository.BasicInfoRepository;
import com.dotin.tfbita.service.dto.BasicInfoDTO;
import com.dotin.tfbita.service.mapper.BasicInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.BasicInfo}.
 */
@Service
@Transactional
public class BasicInfoService {

    private final Logger log = LoggerFactory.getLogger(BasicInfoService.class);

    private final BasicInfoRepository basicInfoRepository;

    private final BasicInfoMapper basicInfoMapper;

    public BasicInfoService(BasicInfoRepository basicInfoRepository, BasicInfoMapper basicInfoMapper) {
        this.basicInfoRepository = basicInfoRepository;
        this.basicInfoMapper = basicInfoMapper;
    }

    /**
     * Save a basicInfo.
     *
     * @param basicInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public BasicInfoDTO save(BasicInfoDTO basicInfoDTO) {
        log.debug("Request to save BasicInfo : {}", basicInfoDTO);
        BasicInfo basicInfo = basicInfoMapper.toEntity(basicInfoDTO);
        basicInfo = basicInfoRepository.save(basicInfo);
        return basicInfoMapper.toDto(basicInfo);
    }

    /**
     * Update a basicInfo.
     *
     * @param basicInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public BasicInfoDTO update(BasicInfoDTO basicInfoDTO) {
        log.debug("Request to update BasicInfo : {}", basicInfoDTO);
        BasicInfo basicInfo = basicInfoMapper.toEntity(basicInfoDTO);
        basicInfo = basicInfoRepository.save(basicInfo);
        return basicInfoMapper.toDto(basicInfo);
    }

    /**
     * Partially update a basicInfo.
     *
     * @param basicInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BasicInfoDTO> partialUpdate(BasicInfoDTO basicInfoDTO) {
        log.debug("Request to partially update BasicInfo : {}", basicInfoDTO);

        return basicInfoRepository
            .findById(basicInfoDTO.getId())
            .map(existingBasicInfo -> {
                basicInfoMapper.partialUpdate(existingBasicInfo, basicInfoDTO);

                return existingBasicInfo;
            })
            .map(basicInfoRepository::save)
            .map(basicInfoMapper::toDto);
    }

    /**
     * Get all the basicInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BasicInfoDTO> findAll() {
        log.debug("Request to get all BasicInfos");
        return basicInfoRepository.findAll().stream().map(basicInfoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one basicInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BasicInfoDTO> findOne(Long id) {
        log.debug("Request to get BasicInfo : {}", id);
        return basicInfoRepository.findById(id).map(basicInfoMapper::toDto);
    }

    /**
     * Delete the basicInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BasicInfo : {}", id);
        basicInfoRepository.deleteById(id);
    }
}
