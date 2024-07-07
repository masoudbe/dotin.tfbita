package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.LongValue;
import com.dotin.tfbita.repository.LongValueRepository;
import com.dotin.tfbita.service.dto.LongValueDTO;
import com.dotin.tfbita.service.mapper.LongValueMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.LongValue}.
 */
@Service
@Transactional
public class LongValueService {

    private final Logger log = LoggerFactory.getLogger(LongValueService.class);

    private final LongValueRepository longValueRepository;

    private final LongValueMapper longValueMapper;

    public LongValueService(LongValueRepository longValueRepository, LongValueMapper longValueMapper) {
        this.longValueRepository = longValueRepository;
        this.longValueMapper = longValueMapper;
    }

    /**
     * Save a longValue.
     *
     * @param longValueDTO the entity to save.
     * @return the persisted entity.
     */
    public LongValueDTO save(LongValueDTO longValueDTO) {
        log.debug("Request to save LongValue : {}", longValueDTO);
        LongValue longValue = longValueMapper.toEntity(longValueDTO);
        longValue = longValueRepository.save(longValue);
        return longValueMapper.toDto(longValue);
    }

    /**
     * Update a longValue.
     *
     * @param longValueDTO the entity to save.
     * @return the persisted entity.
     */
    public LongValueDTO update(LongValueDTO longValueDTO) {
        log.debug("Request to update LongValue : {}", longValueDTO);
        LongValue longValue = longValueMapper.toEntity(longValueDTO);
        longValue = longValueRepository.save(longValue);
        return longValueMapper.toDto(longValue);
    }

    /**
     * Partially update a longValue.
     *
     * @param longValueDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LongValueDTO> partialUpdate(LongValueDTO longValueDTO) {
        log.debug("Request to partially update LongValue : {}", longValueDTO);

        return longValueRepository
            .findById(longValueDTO.getId())
            .map(existingLongValue -> {
                longValueMapper.partialUpdate(existingLongValue, longValueDTO);

                return existingLongValue;
            })
            .map(longValueRepository::save)
            .map(longValueMapper::toDto);
    }

    /**
     * Get all the longValues.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LongValueDTO> findAll() {
        log.debug("Request to get all LongValues");
        return longValueRepository.findAll().stream().map(longValueMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one longValue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LongValueDTO> findOne(Long id) {
        log.debug("Request to get LongValue : {}", id);
        return longValueRepository.findById(id).map(longValueMapper::toDto);
    }

    /**
     * Delete the longValue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LongValue : {}", id);
        longValueRepository.deleteById(id);
    }
}
