package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftType;
import com.dotin.tfbita.repository.DraftTypeRepository;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
import com.dotin.tfbita.service.mapper.DraftTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftType}.
 */
@Service
@Transactional
public class DraftTypeService {

    private final Logger log = LoggerFactory.getLogger(DraftTypeService.class);

    private final DraftTypeRepository draftTypeRepository;

    private final DraftTypeMapper draftTypeMapper;

    public DraftTypeService(DraftTypeRepository draftTypeRepository, DraftTypeMapper draftTypeMapper) {
        this.draftTypeRepository = draftTypeRepository;
        this.draftTypeMapper = draftTypeMapper;
    }

    /**
     * Save a draftType.
     *
     * @param draftTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftTypeDTO save(DraftTypeDTO draftTypeDTO) {
        log.debug("Request to save DraftType : {}", draftTypeDTO);
        DraftType draftType = draftTypeMapper.toEntity(draftTypeDTO);
        draftType = draftTypeRepository.save(draftType);
        return draftTypeMapper.toDto(draftType);
    }

    /**
     * Update a draftType.
     *
     * @param draftTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftTypeDTO update(DraftTypeDTO draftTypeDTO) {
        log.debug("Request to update DraftType : {}", draftTypeDTO);
        DraftType draftType = draftTypeMapper.toEntity(draftTypeDTO);
        draftType = draftTypeRepository.save(draftType);
        return draftTypeMapper.toDto(draftType);
    }

    /**
     * Partially update a draftType.
     *
     * @param draftTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftTypeDTO> partialUpdate(DraftTypeDTO draftTypeDTO) {
        log.debug("Request to partially update DraftType : {}", draftTypeDTO);

        return draftTypeRepository
            .findById(draftTypeDTO.getId())
            .map(existingDraftType -> {
                draftTypeMapper.partialUpdate(existingDraftType, draftTypeDTO);

                return existingDraftType;
            })
            .map(draftTypeRepository::save)
            .map(draftTypeMapper::toDto);
    }

    /**
     * Get all the draftTypes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftTypeDTO> findAll() {
        log.debug("Request to get all DraftTypes");
        return draftTypeRepository.findAll().stream().map(draftTypeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the draftTypes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DraftTypeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return draftTypeRepository.findAllWithEagerRelationships(pageable).map(draftTypeMapper::toDto);
    }

    /**
     * Get one draftType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftTypeDTO> findOne(Long id) {
        log.debug("Request to get DraftType : {}", id);
        return draftTypeRepository.findOneWithEagerRelationships(id).map(draftTypeMapper::toDto);
    }

    /**
     * Delete the draftType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftType : {}", id);
        draftTypeRepository.deleteById(id);
    }
}
