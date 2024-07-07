package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.AttributeValueGroup;
import com.dotin.tfbita.repository.AttributeValueGroupRepository;
import com.dotin.tfbita.service.AttributeValueGroupService;
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
public class AttributeValueGroupServiceImpl implements AttributeValueGroupService {

    private static final Logger log = LoggerFactory.getLogger(AttributeValueGroupServiceImpl.class);

    private final AttributeValueGroupRepository attributeValueGroupRepository;

    private final AttributeValueGroupMapper attributeValueGroupMapper;

    public AttributeValueGroupServiceImpl(
        AttributeValueGroupRepository attributeValueGroupRepository,
        AttributeValueGroupMapper attributeValueGroupMapper
    ) {
        this.attributeValueGroupRepository = attributeValueGroupRepository;
        this.attributeValueGroupMapper = attributeValueGroupMapper;
    }

    @Override
    public AttributeValueGroupDTO save(AttributeValueGroupDTO attributeValueGroupDTO) {
        log.debug("Request to save AttributeValueGroup : {}", attributeValueGroupDTO);
        AttributeValueGroup attributeValueGroup = attributeValueGroupMapper.toEntity(attributeValueGroupDTO);
        attributeValueGroup = attributeValueGroupRepository.save(attributeValueGroup);
        return attributeValueGroupMapper.toDto(attributeValueGroup);
    }

    @Override
    public AttributeValueGroupDTO update(AttributeValueGroupDTO attributeValueGroupDTO) {
        log.debug("Request to update AttributeValueGroup : {}", attributeValueGroupDTO);
        AttributeValueGroup attributeValueGroup = attributeValueGroupMapper.toEntity(attributeValueGroupDTO);
        attributeValueGroup = attributeValueGroupRepository.save(attributeValueGroup);
        return attributeValueGroupMapper.toDto(attributeValueGroup);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<AttributeValueGroupDTO> findAll() {
        log.debug("Request to get all AttributeValueGroups");
        return attributeValueGroupRepository
            .findAll()
            .stream()
            .map(attributeValueGroupMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AttributeValueGroupDTO> findOne(Long id) {
        log.debug("Request to get AttributeValueGroup : {}", id);
        return attributeValueGroupRepository.findById(id).map(attributeValueGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttributeValueGroup : {}", id);
        attributeValueGroupRepository.deleteById(id);
    }
}
