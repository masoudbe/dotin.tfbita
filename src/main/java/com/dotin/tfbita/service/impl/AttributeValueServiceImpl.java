package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.AttributeValue;
import com.dotin.tfbita.repository.AttributeValueRepository;
import com.dotin.tfbita.service.AttributeValueService;
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
public class AttributeValueServiceImpl implements AttributeValueService {

    private static final Logger log = LoggerFactory.getLogger(AttributeValueServiceImpl.class);

    private final AttributeValueRepository attributeValueRepository;

    private final AttributeValueMapper attributeValueMapper;

    public AttributeValueServiceImpl(AttributeValueRepository attributeValueRepository, AttributeValueMapper attributeValueMapper) {
        this.attributeValueRepository = attributeValueRepository;
        this.attributeValueMapper = attributeValueMapper;
    }

    @Override
    public AttributeValueDTO save(AttributeValueDTO attributeValueDTO) {
        log.debug("Request to save AttributeValue : {}", attributeValueDTO);
        AttributeValue attributeValue = attributeValueMapper.toEntity(attributeValueDTO);
        attributeValue = attributeValueRepository.save(attributeValue);
        return attributeValueMapper.toDto(attributeValue);
    }

    @Override
    public AttributeValueDTO update(AttributeValueDTO attributeValueDTO) {
        log.debug("Request to update AttributeValue : {}", attributeValueDTO);
        AttributeValue attributeValue = attributeValueMapper.toEntity(attributeValueDTO);
        attributeValue = attributeValueRepository.save(attributeValue);
        return attributeValueMapper.toDto(attributeValue);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<AttributeValueDTO> findAll() {
        log.debug("Request to get all AttributeValues");
        return attributeValueRepository
            .findAll()
            .stream()
            .map(attributeValueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AttributeValueDTO> findOne(Long id) {
        log.debug("Request to get AttributeValue : {}", id);
        return attributeValueRepository.findById(id).map(attributeValueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttributeValue : {}", id);
        attributeValueRepository.deleteById(id);
    }
}
