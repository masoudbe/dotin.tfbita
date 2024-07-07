package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.ObjectiveCategory;
import com.dotin.tfbita.repository.ObjectiveCategoryRepository;
import com.dotin.tfbita.service.dto.ObjectiveCategoryDTO;
import com.dotin.tfbita.service.mapper.ObjectiveCategoryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.ObjectiveCategory}.
 */
@Service
@Transactional
public class ObjectiveCategoryService {

    private final Logger log = LoggerFactory.getLogger(ObjectiveCategoryService.class);

    private final ObjectiveCategoryRepository objectiveCategoryRepository;

    private final ObjectiveCategoryMapper objectiveCategoryMapper;

    public ObjectiveCategoryService(
        ObjectiveCategoryRepository objectiveCategoryRepository,
        ObjectiveCategoryMapper objectiveCategoryMapper
    ) {
        this.objectiveCategoryRepository = objectiveCategoryRepository;
        this.objectiveCategoryMapper = objectiveCategoryMapper;
    }

    /**
     * Save a objectiveCategory.
     *
     * @param objectiveCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public ObjectiveCategoryDTO save(ObjectiveCategoryDTO objectiveCategoryDTO) {
        log.debug("Request to save ObjectiveCategory : {}", objectiveCategoryDTO);
        ObjectiveCategory objectiveCategory = objectiveCategoryMapper.toEntity(objectiveCategoryDTO);
        objectiveCategory = objectiveCategoryRepository.save(objectiveCategory);
        return objectiveCategoryMapper.toDto(objectiveCategory);
    }

    /**
     * Update a objectiveCategory.
     *
     * @param objectiveCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public ObjectiveCategoryDTO update(ObjectiveCategoryDTO objectiveCategoryDTO) {
        log.debug("Request to update ObjectiveCategory : {}", objectiveCategoryDTO);
        ObjectiveCategory objectiveCategory = objectiveCategoryMapper.toEntity(objectiveCategoryDTO);
        objectiveCategory = objectiveCategoryRepository.save(objectiveCategory);
        return objectiveCategoryMapper.toDto(objectiveCategory);
    }

    /**
     * Partially update a objectiveCategory.
     *
     * @param objectiveCategoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ObjectiveCategoryDTO> partialUpdate(ObjectiveCategoryDTO objectiveCategoryDTO) {
        log.debug("Request to partially update ObjectiveCategory : {}", objectiveCategoryDTO);

        return objectiveCategoryRepository
            .findById(objectiveCategoryDTO.getId())
            .map(existingObjectiveCategory -> {
                objectiveCategoryMapper.partialUpdate(existingObjectiveCategory, objectiveCategoryDTO);

                return existingObjectiveCategory;
            })
            .map(objectiveCategoryRepository::save)
            .map(objectiveCategoryMapper::toDto);
    }

    /**
     * Get all the objectiveCategories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ObjectiveCategoryDTO> findAll() {
        log.debug("Request to get all ObjectiveCategories");
        return objectiveCategoryRepository
            .findAll()
            .stream()
            .map(objectiveCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one objectiveCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ObjectiveCategoryDTO> findOne(Long id) {
        log.debug("Request to get ObjectiveCategory : {}", id);
        return objectiveCategoryRepository.findById(id).map(objectiveCategoryMapper::toDto);
    }

    /**
     * Delete the objectiveCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ObjectiveCategory : {}", id);
        objectiveCategoryRepository.deleteById(id);
    }
}
