package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.AttributeValue;
import com.dotin.tfbita.repository.AttributeValueRepository;
import com.dotin.tfbita.service.dto.AttributeValueDTO;
import com.dotin.tfbita.service.mapper.AttributeValueMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.AttributeValue}.
 */
@Service
@Transactional
public class AttributeValueService {

    private final Logger log = LoggerFactory.getLogger(AttributeValueService.class);

    private final AttributeValueRepository attributeValueRepository;

    private final AttributeValueMapper attributeValueMapper;

    public AttributeValueService(AttributeValueRepository attributeValueRepository, AttributeValueMapper attributeValueMapper) {
        this.attributeValueRepository = attributeValueRepository;
        this.attributeValueMapper = attributeValueMapper;
    }

    /**
     * Save a attributeValue.
     *
     * @param attributeValueDTO the entity to save.
     * @return the persisted entity.
     */
    public AttributeValueDTO save(AttributeValueDTO attributeValueDTO) {
        log.debug("Request to save AttributeValue : {}", attributeValueDTO);
        AttributeValue attributeValue = attributeValueMapper.toEntity(attributeValueDTO);
        attributeValue = attributeValueRepository.save(attributeValue);
        return attributeValueMapper.toDto(attributeValue);
    }

    /**
     * Update a attributeValue.
     *
     * @param attributeValueDTO the entity to save.
     * @return the persisted entity.
     */
    public AttributeValueDTO update(AttributeValueDTO attributeValueDTO) {
        log.debug("Request to update AttributeValue : {}", attributeValueDTO);
        AttributeValue attributeValue = attributeValueMapper.toEntity(attributeValueDTO);
        attributeValue = attributeValueRepository.save(attributeValue);
        return attributeValueMapper.toDto(attributeValue);
    }

    /**
     * Partially update a attributeValue.
     *
     * @param attributeValueDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AttributeValueDTO> partialUpdate(AttributeValueDTO attributeValueDTO) {
        log.debug("Request to partially update AttributeValue : {}", attributeValueDTO);

        return attributeValueRepository
            .findById(attributeValueDTO.getId())
            .map(existingAttributeValue -> {
                attributeValueMapper.partialUpdate(existingAttributeValue, attributeValueDTO);

                return existingAttributeValue;
            })
            .map(attributeValueRepository::save)
            .map(attributeValueMapper::toDto);
    }

    /**
     * Get all the attributeValues.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AttributeValueDTO> findAll() {
        log.debug("Request to get all AttributeValues");
        return attributeValueRepository
            .findAll()
            .stream()
            .map(attributeValueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one attributeValue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AttributeValueDTO> findOne(Long id) {
        log.debug("Request to get AttributeValue : {}", id);
        return attributeValueRepository.findById(id).map(attributeValueMapper::toDto);
    }

    /**
     * Delete the attributeValue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AttributeValue : {}", id);
        attributeValueRepository.deleteById(id);
    }
}
