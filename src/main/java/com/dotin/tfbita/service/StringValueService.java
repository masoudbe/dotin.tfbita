package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.StringValue;
import com.dotin.tfbita.repository.StringValueRepository;
import com.dotin.tfbita.service.dto.StringValueDTO;
import com.dotin.tfbita.service.mapper.StringValueMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.StringValue}.
 */
@Service
@Transactional
public class StringValueService {

    private final Logger log = LoggerFactory.getLogger(StringValueService.class);

    private final StringValueRepository stringValueRepository;

    private final StringValueMapper stringValueMapper;

    public StringValueService(StringValueRepository stringValueRepository, StringValueMapper stringValueMapper) {
        this.stringValueRepository = stringValueRepository;
        this.stringValueMapper = stringValueMapper;
    }

    /**
     * Save a stringValue.
     *
     * @param stringValueDTO the entity to save.
     * @return the persisted entity.
     */
    public StringValueDTO save(StringValueDTO stringValueDTO) {
        log.debug("Request to save StringValue : {}", stringValueDTO);
        StringValue stringValue = stringValueMapper.toEntity(stringValueDTO);
        stringValue = stringValueRepository.save(stringValue);
        return stringValueMapper.toDto(stringValue);
    }

    /**
     * Update a stringValue.
     *
     * @param stringValueDTO the entity to save.
     * @return the persisted entity.
     */
    public StringValueDTO update(StringValueDTO stringValueDTO) {
        log.debug("Request to update StringValue : {}", stringValueDTO);
        StringValue stringValue = stringValueMapper.toEntity(stringValueDTO);
        stringValue = stringValueRepository.save(stringValue);
        return stringValueMapper.toDto(stringValue);
    }

    /**
     * Partially update a stringValue.
     *
     * @param stringValueDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StringValueDTO> partialUpdate(StringValueDTO stringValueDTO) {
        log.debug("Request to partially update StringValue : {}", stringValueDTO);

        return stringValueRepository
            .findById(stringValueDTO.getId())
            .map(existingStringValue -> {
                stringValueMapper.partialUpdate(existingStringValue, stringValueDTO);

                return existingStringValue;
            })
            .map(stringValueRepository::save)
            .map(stringValueMapper::toDto);
    }

    /**
     * Get all the stringValues.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<StringValueDTO> findAll() {
        log.debug("Request to get all StringValues");
        return stringValueRepository.findAll().stream().map(stringValueMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one stringValue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StringValueDTO> findOne(Long id) {
        log.debug("Request to get StringValue : {}", id);
        return stringValueRepository.findById(id).map(stringValueMapper::toDto);
    }

    /**
     * Delete the stringValue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StringValue : {}", id);
        stringValueRepository.deleteById(id);
    }
}
