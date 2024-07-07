package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.ObjectiveCategoryElement;
import com.dotin.tfbita.repository.ObjectiveCategoryElementRepository;
import com.dotin.tfbita.service.ObjectiveCategoryElementService;
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
public class ObjectiveCategoryElementServiceImpl implements ObjectiveCategoryElementService {

    private static final Logger log = LoggerFactory.getLogger(ObjectiveCategoryElementServiceImpl.class);

    private final ObjectiveCategoryElementRepository objectiveCategoryElementRepository;

    private final ObjectiveCategoryElementMapper objectiveCategoryElementMapper;

    public ObjectiveCategoryElementServiceImpl(
        ObjectiveCategoryElementRepository objectiveCategoryElementRepository,
        ObjectiveCategoryElementMapper objectiveCategoryElementMapper
    ) {
        this.objectiveCategoryElementRepository = objectiveCategoryElementRepository;
        this.objectiveCategoryElementMapper = objectiveCategoryElementMapper;
    }

    @Override
    public ObjectiveCategoryElementDTO save(ObjectiveCategoryElementDTO objectiveCategoryElementDTO) {
        log.debug("Request to save ObjectiveCategoryElement : {}", objectiveCategoryElementDTO);
        ObjectiveCategoryElement objectiveCategoryElement = objectiveCategoryElementMapper.toEntity(objectiveCategoryElementDTO);
        objectiveCategoryElement = objectiveCategoryElementRepository.save(objectiveCategoryElement);
        return objectiveCategoryElementMapper.toDto(objectiveCategoryElement);
    }

    @Override
    public ObjectiveCategoryElementDTO update(ObjectiveCategoryElementDTO objectiveCategoryElementDTO) {
        log.debug("Request to update ObjectiveCategoryElement : {}", objectiveCategoryElementDTO);
        ObjectiveCategoryElement objectiveCategoryElement = objectiveCategoryElementMapper.toEntity(objectiveCategoryElementDTO);
        objectiveCategoryElement = objectiveCategoryElementRepository.save(objectiveCategoryElement);
        return objectiveCategoryElementMapper.toDto(objectiveCategoryElement);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<ObjectiveCategoryElementDTO> findAll() {
        log.debug("Request to get all ObjectiveCategoryElements");
        return objectiveCategoryElementRepository
            .findAll()
            .stream()
            .map(objectiveCategoryElementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ObjectiveCategoryElementDTO> findOne(Long id) {
        log.debug("Request to get ObjectiveCategoryElement : {}", id);
        return objectiveCategoryElementRepository.findById(id).map(objectiveCategoryElementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ObjectiveCategoryElement : {}", id);
        objectiveCategoryElementRepository.deleteById(id);
    }
}
