package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.ObjectiveCategory;
import com.dotin.tfbita.repository.ObjectiveCategoryRepository;
import com.dotin.tfbita.service.ObjectiveCategoryService;
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
public class ObjectiveCategoryServiceImpl implements ObjectiveCategoryService {

    private static final Logger log = LoggerFactory.getLogger(ObjectiveCategoryServiceImpl.class);

    private final ObjectiveCategoryRepository objectiveCategoryRepository;

    private final ObjectiveCategoryMapper objectiveCategoryMapper;

    public ObjectiveCategoryServiceImpl(
        ObjectiveCategoryRepository objectiveCategoryRepository,
        ObjectiveCategoryMapper objectiveCategoryMapper
    ) {
        this.objectiveCategoryRepository = objectiveCategoryRepository;
        this.objectiveCategoryMapper = objectiveCategoryMapper;
    }

    @Override
    public ObjectiveCategoryDTO save(ObjectiveCategoryDTO objectiveCategoryDTO) {
        log.debug("Request to save ObjectiveCategory : {}", objectiveCategoryDTO);
        ObjectiveCategory objectiveCategory = objectiveCategoryMapper.toEntity(objectiveCategoryDTO);
        objectiveCategory = objectiveCategoryRepository.save(objectiveCategory);
        return objectiveCategoryMapper.toDto(objectiveCategory);
    }

    @Override
    public ObjectiveCategoryDTO update(ObjectiveCategoryDTO objectiveCategoryDTO) {
        log.debug("Request to update ObjectiveCategory : {}", objectiveCategoryDTO);
        ObjectiveCategory objectiveCategory = objectiveCategoryMapper.toEntity(objectiveCategoryDTO);
        objectiveCategory = objectiveCategoryRepository.save(objectiveCategory);
        return objectiveCategoryMapper.toDto(objectiveCategory);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<ObjectiveCategoryDTO> findAll() {
        log.debug("Request to get all ObjectiveCategories");
        return objectiveCategoryRepository
            .findAll()
            .stream()
            .map(objectiveCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ObjectiveCategoryDTO> findOne(Long id) {
        log.debug("Request to get ObjectiveCategory : {}", id);
        return objectiveCategoryRepository.findById(id).map(objectiveCategoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObjectiveCategory : {}", id);
        objectiveCategoryRepository.deleteById(id);
    }
}
