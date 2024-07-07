package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.BasicInfo;
import com.dotin.tfbita.repository.BasicInfoRepository;
import com.dotin.tfbita.service.BasicInfoService;
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
public class BasicInfoServiceImpl implements BasicInfoService {

    private static final Logger log = LoggerFactory.getLogger(BasicInfoServiceImpl.class);

    private final BasicInfoRepository basicInfoRepository;

    private final BasicInfoMapper basicInfoMapper;

    public BasicInfoServiceImpl(BasicInfoRepository basicInfoRepository, BasicInfoMapper basicInfoMapper) {
        this.basicInfoRepository = basicInfoRepository;
        this.basicInfoMapper = basicInfoMapper;
    }

    @Override
    public BasicInfoDTO save(BasicInfoDTO basicInfoDTO) {
        log.debug("Request to save BasicInfo : {}", basicInfoDTO);
        BasicInfo basicInfo = basicInfoMapper.toEntity(basicInfoDTO);
        basicInfo = basicInfoRepository.save(basicInfo);
        return basicInfoMapper.toDto(basicInfo);
    }

    @Override
    public BasicInfoDTO update(BasicInfoDTO basicInfoDTO) {
        log.debug("Request to update BasicInfo : {}", basicInfoDTO);
        BasicInfo basicInfo = basicInfoMapper.toEntity(basicInfoDTO);
        basicInfo = basicInfoRepository.save(basicInfo);
        return basicInfoMapper.toDto(basicInfo);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<BasicInfoDTO> findAll() {
        log.debug("Request to get all BasicInfos");
        return basicInfoRepository.findAll().stream().map(basicInfoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BasicInfoDTO> findOne(Long id) {
        log.debug("Request to get BasicInfo : {}", id);
        return basicInfoRepository.findById(id).map(basicInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BasicInfo : {}", id);
        basicInfoRepository.deleteById(id);
    }
}
