package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.CustomJustification;
import com.dotin.tfbita.repository.CustomJustificationRepository;
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
public class CustomJustificationService {

    private final Logger log = LoggerFactory.getLogger(CustomJustificationService.class);

    private final CustomJustificationRepository customJustificationRepository;

    private final CustomJustificationMapper customJustificationMapper;

    public CustomJustificationService(
        CustomJustificationRepository customJustificationRepository,
        CustomJustificationMapper customJustificationMapper
    ) {
        this.customJustificationRepository = customJustificationRepository;
        this.customJustificationMapper = customJustificationMapper;
    }

    /**
     * Save a customJustification.
     *
     * @param customJustificationDTO the entity to save.
     * @return the persisted entity.
     */
    public CustomJustificationDTO save(CustomJustificationDTO customJustificationDTO) {
        log.debug("Request to save CustomJustification : {}", customJustificationDTO);
        CustomJustification customJustification = customJustificationMapper.toEntity(customJustificationDTO);
        customJustification = customJustificationRepository.save(customJustification);
        return customJustificationMapper.toDto(customJustification);
    }

    /**
     * Update a customJustification.
     *
     * @param customJustificationDTO the entity to save.
     * @return the persisted entity.
     */
    public CustomJustificationDTO update(CustomJustificationDTO customJustificationDTO) {
        log.debug("Request to update CustomJustification : {}", customJustificationDTO);
        CustomJustification customJustification = customJustificationMapper.toEntity(customJustificationDTO);
        customJustification = customJustificationRepository.save(customJustification);
        return customJustificationMapper.toDto(customJustification);
    }

    /**
     * Partially update a customJustification.
     *
     * @param customJustificationDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the customJustifications.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CustomJustificationDTO> findAll() {
        log.debug("Request to get all CustomJustifications");
        return customJustificationRepository
            .findAll()
            .stream()
            .map(customJustificationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the customJustifications with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<CustomJustificationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return customJustificationRepository.findAllWithEagerRelationships(pageable).map(customJustificationMapper::toDto);
    }

    /**
     * Get one customJustification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CustomJustificationDTO> findOne(Long id) {
        log.debug("Request to get CustomJustification : {}", id);
        return customJustificationRepository.findOneWithEagerRelationships(id).map(customJustificationMapper::toDto);
    }

    /**
     * Delete the customJustification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CustomJustification : {}", id);
        customJustificationRepository.deleteById(id);
    }
}
