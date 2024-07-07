package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.CustomJustification;
import com.dotin.tfbita.repository.CustomJustificationRepository;
import com.dotin.tfbita.service.CustomJustificationService;
import com.dotin.tfbita.service.dto.CustomJustificationDTO;
import com.dotin.tfbita.service.mapper.CustomJustificationMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.CustomJustification}.
 */
@Service
@Transactional
public class CustomJustificationServiceImpl implements CustomJustificationService {

    private static final Logger log = LoggerFactory.getLogger(CustomJustificationServiceImpl.class);

    private final CustomJustificationRepository customJustificationRepository;

    private final CustomJustificationMapper customJustificationMapper;

    public CustomJustificationServiceImpl(
        CustomJustificationRepository customJustificationRepository,
        CustomJustificationMapper customJustificationMapper
    ) {
        this.customJustificationRepository = customJustificationRepository;
        this.customJustificationMapper = customJustificationMapper;
    }

    @Override
    public CustomJustificationDTO save(CustomJustificationDTO customJustificationDTO) {
        log.debug("Request to save CustomJustification : {}", customJustificationDTO);
        CustomJustification customJustification = customJustificationMapper.toEntity(customJustificationDTO);
        customJustification = customJustificationRepository.save(customJustification);
        return customJustificationMapper.toDto(customJustification);
    }

    @Override
    public CustomJustificationDTO update(CustomJustificationDTO customJustificationDTO) {
        log.debug("Request to update CustomJustification : {}", customJustificationDTO);
        CustomJustification customJustification = customJustificationMapper.toEntity(customJustificationDTO);
        customJustification = customJustificationRepository.save(customJustification);
        return customJustificationMapper.toDto(customJustification);
    }

    @Override
    public Optional<CustomJustificationDTO> partialUpdate(CustomJustificationDTO customJustificationDTO) {
        log.debug("Request to partially update CustomJustification : {}", customJustificationDTO);

        return customJustificationRepository
            .findById(customJustificationDTO.getId())
            .map(existingCustomJustification -> {
                customJustificationMapper.partialUpdate(existingCustomJustification, customJustificationDTO);

                return existingCustomJustification;
            })
            .map(customJustificationRepository::save)
            .map(customJustificationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomJustificationDTO> findAll() {
        log.debug("Request to get all CustomJustifications");
        return customJustificationRepository
            .findAll()
            .stream()
            .map(customJustificationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<CustomJustificationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return customJustificationRepository.findAllWithEagerRelationships(pageable).map(customJustificationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomJustificationDTO> findOne(Long id) {
        log.debug("Request to get CustomJustification : {}", id);
        return customJustificationRepository.findOneWithEagerRelationships(id).map(customJustificationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomJustification : {}", id);
        customJustificationRepository.deleteById(id);
    }
}
