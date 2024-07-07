package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.TypeAttribute;
import com.dotin.tfbita.repository.TypeAttributeRepository;
import com.dotin.tfbita.service.TypeAttributeService;
import com.dotin.tfbita.service.dto.TypeAttributeDTO;
import com.dotin.tfbita.service.mapper.TypeAttributeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.TypeAttribute}.
 */
@Service
@Transactional
public class TypeAttributeServiceImpl implements TypeAttributeService {

    private static final Logger log = LoggerFactory.getLogger(TypeAttributeServiceImpl.class);

    private final TypeAttributeRepository typeAttributeRepository;

    private final TypeAttributeMapper typeAttributeMapper;

    public TypeAttributeServiceImpl(TypeAttributeRepository typeAttributeRepository, TypeAttributeMapper typeAttributeMapper) {
        this.typeAttributeRepository = typeAttributeRepository;
        this.typeAttributeMapper = typeAttributeMapper;
    }

    @Override
    public TypeAttributeDTO save(TypeAttributeDTO typeAttributeDTO) {
        log.debug("Request to save TypeAttribute : {}", typeAttributeDTO);
        TypeAttribute typeAttribute = typeAttributeMapper.toEntity(typeAttributeDTO);
        typeAttribute = typeAttributeRepository.save(typeAttribute);
        return typeAttributeMapper.toDto(typeAttribute);
    }

    @Override
    public TypeAttributeDTO update(TypeAttributeDTO typeAttributeDTO) {
        log.debug("Request to update TypeAttribute : {}", typeAttributeDTO);
        TypeAttribute typeAttribute = typeAttributeMapper.toEntity(typeAttributeDTO);
        typeAttribute = typeAttributeRepository.save(typeAttribute);
        return typeAttributeMapper.toDto(typeAttribute);
    }

    @Override
    public Optional<TypeAttributeDTO> partialUpdate(TypeAttributeDTO typeAttributeDTO) {
        log.debug("Request to partially update TypeAttribute : {}", typeAttributeDTO);

        return typeAttributeRepository
            .findById(typeAttributeDTO.getId())
            .map(existingTypeAttribute -> {
                typeAttributeMapper.partialUpdate(existingTypeAttribute, typeAttributeDTO);

                return existingTypeAttribute;
            })
            .map(typeAttributeRepository::save)
            .map(typeAttributeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeAttributeDTO> findAll() {
        log.debug("Request to get all TypeAttributes");
        return typeAttributeRepository.findAll().stream().map(typeAttributeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeAttributeDTO> findOne(Long id) {
        log.debug("Request to get TypeAttribute : {}", id);
        return typeAttributeRepository.findById(id).map(typeAttributeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeAttribute : {}", id);
        typeAttributeRepository.deleteById(id);
    }
}
