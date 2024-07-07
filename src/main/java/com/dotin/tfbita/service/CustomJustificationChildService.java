package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.CustomJustificationChild;
import com.dotin.tfbita.repository.CustomJustificationChildRepository;
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
public class CustomJustificationChildService {

    private final Logger log = LoggerFactory.getLogger(CustomJustificationChildService.class);

    private final CustomJustificationChildRepository customJustificationChildRepository;

    private final CustomJustificationChildMapper customJustificationChildMapper;

    public CustomJustificationChildService(
        CustomJustificationChildRepository customJustificationChildRepository,
        CustomJustificationChildMapper customJustificationChildMapper
    ) {
        this.customJustificationChildRepository = customJustificationChildRepository;
        this.customJustificationChildMapper = customJustificationChildMapper;
    }

    /**
     * Save a customJustificationChild.
     *
     * @param customJustificationChildDTO the entity to save.
     * @return the persisted entity.
     */
    public CustomJustificationChildDTO save(CustomJustificationChildDTO customJustificationChildDTO) {
        log.debug("Request to save CustomJustificationChild : {}", customJustificationChildDTO);
        CustomJustificationChild customJustificationChild = customJustificationChildMapper.toEntity(customJustificationChildDTO);
        customJustificationChild = customJustificationChildRepository.save(customJustificationChild);
        return customJustificationChildMapper.toDto(customJustificationChild);
    }

    /**
     * Update a customJustificationChild.
     *
     * @param customJustificationChildDTO the entity to save.
     * @return the persisted entity.
     */
    public CustomJustificationChildDTO update(CustomJustificationChildDTO customJustificationChildDTO) {
        log.debug("Request to update CustomJustificationChild : {}", customJustificationChildDTO);
        CustomJustificationChild customJustificationChild = customJustificationChildMapper.toEntity(customJustificationChildDTO);
        customJustificationChild = customJustificationChildRepository.save(customJustificationChild);
        return customJustificationChildMapper.toDto(customJustificationChild);
    }

    /**
     * Partially update a customJustificationChild.
     *
     * @param customJustificationChildDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the customJustificationChildren.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CustomJustificationChildDTO> findAll() {
        log.debug("Request to get all CustomJustificationChildren");
        return customJustificationChildRepository
            .findAll()
            .stream()
            .map(customJustificationChildMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one customJustificationChild by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CustomJustificationChildDTO> findOne(Long id) {
        log.debug("Request to get CustomJustificationChild : {}", id);
        return customJustificationChildRepository.findById(id).map(customJustificationChildMapper::toDto);
    }

    /**
     * Delete the customJustificationChild by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CustomJustificationChild : {}", id);
        customJustificationChildRepository.deleteById(id);
    }
}
