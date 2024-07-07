package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.repository.CustomRepository;
import com.dotin.tfbita.service.CustomService;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.mapper.CustomMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.Custom}.
 */
@Service
@Transactional
public class CustomServiceImpl implements CustomService {

    private static final Logger log = LoggerFactory.getLogger(CustomServiceImpl.class);

    private final CustomRepository customRepository;

    private final CustomMapper customMapper;

    public CustomServiceImpl(CustomRepository customRepository, CustomMapper customMapper) {
        this.customRepository = customRepository;
        this.customMapper = customMapper;
    }

    @Override
    public CustomDTO save(CustomDTO customDTO) {
        log.debug("Request to save Custom : {}", customDTO);
        Custom custom = customMapper.toEntity(customDTO);
        custom = customRepository.save(custom);
        return customMapper.toDto(custom);
    }

    @Override
    public CustomDTO update(CustomDTO customDTO) {
        log.debug("Request to update Custom : {}", customDTO);
        Custom custom = customMapper.toEntity(customDTO);
        custom = customRepository.save(custom);
        return customMapper.toDto(custom);
    }

    @Override
    public Optional<CustomDTO> partialUpdate(CustomDTO customDTO) {
        log.debug("Request to partially update Custom : {}", customDTO);

        return customRepository
            .findById(customDTO.getId())
            .map(existingCustom -> {
                customMapper.partialUpdate(existingCustom, customDTO);

                return existingCustom;
            })
            .map(customRepository::save)
            .map(customMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomDTO> findAll() {
        log.debug("Request to get all Customs");
        return customRepository.findAll().stream().map(customMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomDTO> findOne(Long id) {
        log.debug("Request to get Custom : {}", id);
        return customRepository.findById(id).map(customMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Custom : {}", id);
        customRepository.deleteById(id);
    }
}
