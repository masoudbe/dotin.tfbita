package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.ObjectiveCategoryElement;
import com.dotin.tfbita.repository.ObjectiveCategoryElementRepository;
import com.dotin.tfbita.service.dto.ObjectiveCategoryElementDTO;
import com.dotin.tfbita.service.mapper.ObjectiveCategoryElementMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.ObjectiveCategoryElement}.
 */
@Service
@Transactional
public class ObjectiveCategoryElementService {

    private final Logger log = LoggerFactory.getLogger(ObjectiveCategoryElementService.class);

    private final ObjectiveCategoryElementRepository objectiveCategoryElementRepository;

    private final ObjectiveCategoryElementMapper objectiveCategoryElementMapper;

    public ObjectiveCategoryElementService(
        ObjectiveCategoryElementRepository objectiveCategoryElementRepository,
        ObjectiveCategoryElementMapper objectiveCategoryElementMapper
    ) {
        this.objectiveCategoryElementRepository = objectiveCategoryElementRepository;
        this.objectiveCategoryElementMapper = objectiveCategoryElementMapper;
    }

    /**
     * Save a objectiveCategoryElement.
     *
     * @param objectiveCategoryElementDTO the entity to save.
     * @return the persisted entity.
     */
    public ObjectiveCategoryElementDTO save(ObjectiveCategoryElementDTO objectiveCategoryElementDTO) {
        log.debug("Request to save ObjectiveCategoryElement : {}", objectiveCategoryElementDTO);
        ObjectiveCategoryElement objectiveCategoryElement = objectiveCategoryElementMapper.toEntity(objectiveCategoryElementDTO);
        objectiveCategoryElement = objectiveCategoryElementRepository.save(objectiveCategoryElement);
        return objectiveCategoryElementMapper.toDto(objectiveCategoryElement);
    }

    /**
     * Update a objectiveCategoryElement.
     *
     * @param objectiveCategoryElementDTO the entity to save.
     * @return the persisted entity.
     */
    public ObjectiveCategoryElementDTO update(ObjectiveCategoryElementDTO objectiveCategoryElementDTO) {
        log.debug("Request to update ObjectiveCategoryElement : {}", objectiveCategoryElementDTO);
        ObjectiveCategoryElement objectiveCategoryElement = objectiveCategoryElementMapper.toEntity(objectiveCategoryElementDTO);
        objectiveCategoryElement = objectiveCategoryElementRepository.save(objectiveCategoryElement);
        return objectiveCategoryElementMapper.toDto(objectiveCategoryElement);
    }

    /**
     * Partially update a objectiveCategoryElement.
     *
     * @param objectiveCategoryElementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ObjectiveCategoryElementDTO> partialUpdate(ObjectiveCategoryElementDTO objectiveCategoryElementDTO) {
        log.debug("Request to partially update ObjectiveCategoryElement : {}", objectiveCategoryElementDTO);

        return objectiveCategoryElementRepository
            .findById(objectiveCategoryElementDTO.getId())
            .map(existingObjectiveCategoryElement -> {
                objectiveCategoryElementMapper.partialUpdate(existingObjectiveCategoryElement, objectiveCategoryElementDTO);

                return existingObjectiveCategoryElement;
            })
            .map(objectiveCategoryElementRepository::save)
            .map(objectiveCategoryElementMapper::toDto);
    }

    /**
     * Get all the objectiveCategoryElements.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ObjectiveCategoryElementDTO> findAll() {
        log.debug("Request to get all ObjectiveCategoryElements");
        return objectiveCategoryElementRepository
            .findAll()
            .stream()
            .map(objectiveCategoryElementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one objectiveCategoryElement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ObjectiveCategoryElementDTO> findOne(Long id) {
        log.debug("Request to get ObjectiveCategoryElement : {}", id);
        return objectiveCategoryElementRepository.findById(id).map(objectiveCategoryElementMapper::toDto);
    }

    /**
     * Delete the objectiveCategoryElement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ObjectiveCategoryElement : {}", id);
        objectiveCategoryElementRepository.deleteById(id);
    }
}
