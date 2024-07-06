package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftCustomJustification;
import com.dotin.tfbita.repository.DraftCustomJustificationRepository;
import com.dotin.tfbita.service.dto.DraftCustomJustificationDTO;
import com.dotin.tfbita.service.mapper.DraftCustomJustificationMapper;
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
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftCustomJustification}.
 */
@Service
@Transactional
public class DraftCustomJustificationService {

    private final Logger log = LoggerFactory.getLogger(DraftCustomJustificationService.class);

    private final DraftCustomJustificationRepository draftCustomJustificationRepository;

    private final DraftCustomJustificationMapper draftCustomJustificationMapper;

    public DraftCustomJustificationService(
        DraftCustomJustificationRepository draftCustomJustificationRepository,
        DraftCustomJustificationMapper draftCustomJustificationMapper
    ) {
        this.draftCustomJustificationRepository = draftCustomJustificationRepository;
        this.draftCustomJustificationMapper = draftCustomJustificationMapper;
    }

    /**
     * Save a draftCustomJustification.
     *
     * @param draftCustomJustificationDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftCustomJustificationDTO save(DraftCustomJustificationDTO draftCustomJustificationDTO) {
        log.debug("Request to save DraftCustomJustification : {}", draftCustomJustificationDTO);
        DraftCustomJustification draftCustomJustification = draftCustomJustificationMapper.toEntity(draftCustomJustificationDTO);
        draftCustomJustification = draftCustomJustificationRepository.save(draftCustomJustification);
        return draftCustomJustificationMapper.toDto(draftCustomJustification);
    }

    /**
     * Update a draftCustomJustification.
     *
     * @param draftCustomJustificationDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftCustomJustificationDTO update(DraftCustomJustificationDTO draftCustomJustificationDTO) {
        log.debug("Request to update DraftCustomJustification : {}", draftCustomJustificationDTO);
        DraftCustomJustification draftCustomJustification = draftCustomJustificationMapper.toEntity(draftCustomJustificationDTO);
        draftCustomJustification = draftCustomJustificationRepository.save(draftCustomJustification);
        return draftCustomJustificationMapper.toDto(draftCustomJustification);
    }

    /**
     * Partially update a draftCustomJustification.
     *
     * @param draftCustomJustificationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftCustomJustificationDTO> partialUpdate(DraftCustomJustificationDTO draftCustomJustificationDTO) {
        log.debug("Request to partially update DraftCustomJustification : {}", draftCustomJustificationDTO);

        return draftCustomJustificationRepository
            .findById(draftCustomJustificationDTO.getId())
            .map(existingDraftCustomJustification -> {
                draftCustomJustificationMapper.partialUpdate(existingDraftCustomJustification, draftCustomJustificationDTO);

                return existingDraftCustomJustification;
            })
            .map(draftCustomJustificationRepository::save)
            .map(draftCustomJustificationMapper::toDto);
    }

    /**
     * Get all the draftCustomJustifications.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftCustomJustificationDTO> findAll() {
        log.debug("Request to get all DraftCustomJustifications");
        return draftCustomJustificationRepository
            .findAll()
            .stream()
            .map(draftCustomJustificationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the draftCustomJustifications with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DraftCustomJustificationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return draftCustomJustificationRepository.findAllWithEagerRelationships(pageable).map(draftCustomJustificationMapper::toDto);
    }

    /**
     * Get one draftCustomJustification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftCustomJustificationDTO> findOne(Long id) {
        log.debug("Request to get DraftCustomJustification : {}", id);
        return draftCustomJustificationRepository.findOneWithEagerRelationships(id).map(draftCustomJustificationMapper::toDto);
    }

    /**
     * Delete the draftCustomJustification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftCustomJustification : {}", id);
        draftCustomJustificationRepository.deleteById(id);
    }
}
