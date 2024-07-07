package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.CustomJustificationChild;
import com.dotin.tfbita.repository.CustomJustificationChildRepository;
import com.dotin.tfbita.service.CustomJustificationChildService;
import com.dotin.tfbita.service.dto.CustomJustificationChildDTO;
import com.dotin.tfbita.service.mapper.CustomJustificationChildMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.CustomJustificationChild}.
 */
@Service
@Transactional
public class CustomJustificationChildServiceImpl implements CustomJustificationChildService {

    private static final Logger log = LoggerFactory.getLogger(CustomJustificationChildServiceImpl.class);

    private final CustomJustificationChildRepository customJustificationChildRepository;

    private final CustomJustificationChildMapper customJustificationChildMapper;

    public CustomJustificationChildServiceImpl(
        CustomJustificationChildRepository customJustificationChildRepository,
        CustomJustificationChildMapper customJustificationChildMapper
    ) {
        this.customJustificationChildRepository = customJustificationChildRepository;
        this.customJustificationChildMapper = customJustificationChildMapper;
    }

    @Override
    public CustomJustificationChildDTO save(CustomJustificationChildDTO customJustificationChildDTO) {
        log.debug("Request to save CustomJustificationChild : {}", customJustificationChildDTO);
        CustomJustificationChild customJustificationChild = customJustificationChildMapper.toEntity(customJustificationChildDTO);
        customJustificationChild = customJustificationChildRepository.save(customJustificationChild);
        return customJustificationChildMapper.toDto(customJustificationChild);
    }

    @Override
    public CustomJustificationChildDTO update(CustomJustificationChildDTO customJustificationChildDTO) {
        log.debug("Request to update CustomJustificationChild : {}", customJustificationChildDTO);
        CustomJustificationChild customJustificationChild = customJustificationChildMapper.toEntity(customJustificationChildDTO);
        customJustificationChild = customJustificationChildRepository.save(customJustificationChild);
        return customJustificationChildMapper.toDto(customJustificationChild);
    }

    @Override
    public Optional<CustomJustificationChildDTO> partialUpdate(CustomJustificationChildDTO customJustificationChildDTO) {
        log.debug("Request to partially update CustomJustificationChild : {}", customJustificationChildDTO);

        return customJustificationChildRepository
            .findById(customJustificationChildDTO.getId())
            .map(existingCustomJustificationChild -> {
                customJustificationChildMapper.partialUpdate(existingCustomJustificationChild, customJustificationChildDTO);

                return existingCustomJustificationChild;
            })
            .map(customJustificationChildRepository::save)
            .map(customJustificationChildMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomJustificationChildDTO> findAll() {
        log.debug("Request to get all CustomJustificationChildren");
        return customJustificationChildRepository
            .findAll()
            .stream()
            .map(customJustificationChildMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomJustificationChildDTO> findOne(Long id) {
        log.debug("Request to get CustomJustificationChild : {}", id);
        return customJustificationChildRepository.findById(id).map(customJustificationChildMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomJustificationChild : {}", id);
        customJustificationChildRepository.deleteById(id);
    }
}
