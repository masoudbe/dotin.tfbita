package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.AttributeValueGroup;
import com.dotin.tfbita.repository.AttributeValueGroupRepository;
import com.dotin.tfbita.service.dto.AttributeValueGroupDTO;
import com.dotin.tfbita.service.mapper.AttributeValueGroupMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.AttributeValueGroup}.
 */
@Service
@Transactional
public class AttributeValueGroupService {

    private final Logger log = LoggerFactory.getLogger(AttributeValueGroupService.class);

    private final AttributeValueGroupRepository attributeValueGroupRepository;

    private final AttributeValueGroupMapper attributeValueGroupMapper;

    public AttributeValueGroupService(
        AttributeValueGroupRepository attributeValueGroupRepository,
        AttributeValueGroupMapper attributeValueGroupMapper
    ) {
        this.attributeValueGroupRepository = attributeValueGroupRepository;
        this.attributeValueGroupMapper = attributeValueGroupMapper;
    }

    /**
     * Save a attributeValueGroup.
     *
     * @param attributeValueGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public AttributeValueGroupDTO save(AttributeValueGroupDTO attributeValueGroupDTO) {
        log.debug("Request to save AttributeValueGroup : {}", attributeValueGroupDTO);
        AttributeValueGroup attributeValueGroup = attributeValueGroupMapper.toEntity(attributeValueGroupDTO);
        attributeValueGroup = attributeValueGroupRepository.save(attributeValueGroup);
        return attributeValueGroupMapper.toDto(attributeValueGroup);
    }

    /**
     * Update a attributeValueGroup.
     *
     * @param attributeValueGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public AttributeValueGroupDTO update(AttributeValueGroupDTO attributeValueGroupDTO) {
        log.debug("Request to update AttributeValueGroup : {}", attributeValueGroupDTO);
        AttributeValueGroup attributeValueGroup = attributeValueGroupMapper.toEntity(attributeValueGroupDTO);
        attributeValueGroup = attributeValueGroupRepository.save(attributeValueGroup);
        return attributeValueGroupMapper.toDto(attributeValueGroup);
    }

    /**
     * Partially update a attributeValueGroup.
     *
     * @param attributeValueGroupDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AttributeValueGroupDTO> partialUpdate(AttributeValueGroupDTO attributeValueGroupDTO) {
        log.debug("Request to partially update AttributeValueGroup : {}", attributeValueGroupDTO);

        return attributeValueGroupRepository
            .findById(attributeValueGroupDTO.getId())
            .map(existingAttributeValueGroup -> {
                attributeValueGroupMapper.partialUpdate(existingAttributeValueGroup, attributeValueGroupDTO);

                return existingAttributeValueGroup;
            })
            .map(attributeValueGroupRepository::save)
            .map(attributeValueGroupMapper::toDto);
    }

    /**
     * Get all the attributeValueGroups.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AttributeValueGroupDTO> findAll() {
        log.debug("Request to get all AttributeValueGroups");
        return attributeValueGroupRepository
            .findAll()
            .stream()
            .map(attributeValueGroupMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one attributeValueGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AttributeValueGroupDTO> findOne(Long id) {
        log.debug("Request to get AttributeValueGroup : {}", id);
        return attributeValueGroupRepository.findById(id).map(attributeValueGroupMapper::toDto);
    }

    /**
     * Delete the attributeValueGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AttributeValueGroup : {}", id);
        attributeValueGroupRepository.deleteById(id);
    }
}
