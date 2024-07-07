package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.TypeAttribute;
import com.dotin.tfbita.repository.TypeAttributeRepository;
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
public class TypeAttributeService {

    private final Logger log = LoggerFactory.getLogger(TypeAttributeService.class);

    private final TypeAttributeRepository typeAttributeRepository;

    private final TypeAttributeMapper typeAttributeMapper;

    public TypeAttributeService(TypeAttributeRepository typeAttributeRepository, TypeAttributeMapper typeAttributeMapper) {
        this.typeAttributeRepository = typeAttributeRepository;
        this.typeAttributeMapper = typeAttributeMapper;
    }

    /**
     * Save a typeAttribute.
     *
     * @param typeAttributeDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeAttributeDTO save(TypeAttributeDTO typeAttributeDTO) {
        log.debug("Request to save TypeAttribute : {}", typeAttributeDTO);
        TypeAttribute typeAttribute = typeAttributeMapper.toEntity(typeAttributeDTO);
        typeAttribute = typeAttributeRepository.save(typeAttribute);
        return typeAttributeMapper.toDto(typeAttribute);
    }

    /**
     * Update a typeAttribute.
     *
     * @param typeAttributeDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeAttributeDTO update(TypeAttributeDTO typeAttributeDTO) {
        log.debug("Request to update TypeAttribute : {}", typeAttributeDTO);
        TypeAttribute typeAttribute = typeAttributeMapper.toEntity(typeAttributeDTO);
        typeAttribute = typeAttributeRepository.save(typeAttribute);
        return typeAttributeMapper.toDto(typeAttribute);
    }

    /**
     * Partially update a typeAttribute.
     *
     * @param typeAttributeDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the typeAttributes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TypeAttributeDTO> findAll() {
        log.debug("Request to get all TypeAttributes");
        return typeAttributeRepository.findAll().stream().map(typeAttributeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one typeAttribute by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeAttributeDTO> findOne(Long id) {
        log.debug("Request to get TypeAttribute : {}", id);
        return typeAttributeRepository.findById(id).map(typeAttributeMapper::toDto);
    }

    /**
     * Delete the typeAttribute by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeAttribute : {}", id);
        typeAttributeRepository.deleteById(id);
    }
}
