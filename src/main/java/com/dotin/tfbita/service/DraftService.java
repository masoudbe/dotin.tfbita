package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.repository.DraftRepository;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.mapper.DraftMapper;
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
 * Service Implementation for managing {@link com.dotin.tfbita.domain.Draft}.
 */
@Service
@Transactional
public class DraftService {

    private final Logger log = LoggerFactory.getLogger(DraftService.class);

    private final DraftRepository draftRepository;

    private final DraftMapper draftMapper;

    public DraftService(DraftRepository draftRepository, DraftMapper draftMapper) {
        this.draftRepository = draftRepository;
        this.draftMapper = draftMapper;
    }

    /**
     * Save a draft.
     *
     * @param draftDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftDTO save(DraftDTO draftDTO) {
        log.debug("Request to save Draft : {}", draftDTO);
        Draft draft = draftMapper.toEntity(draftDTO);
        draft = draftRepository.save(draft);
        return draftMapper.toDto(draft);
    }

    /**
     * Update a draft.
     *
     * @param draftDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftDTO update(DraftDTO draftDTO) {
        log.debug("Request to update Draft : {}", draftDTO);
        Draft draft = draftMapper.toEntity(draftDTO);
        draft = draftRepository.save(draft);
        return draftMapper.toDto(draft);
    }

    /**
     * Partially update a draft.
     *
     * @param draftDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftDTO> partialUpdate(DraftDTO draftDTO) {
        log.debug("Request to partially update Draft : {}", draftDTO);

        return draftRepository
            .findById(draftDTO.getId())
            .map(existingDraft -> {
                draftMapper.partialUpdate(existingDraft, draftDTO);

                return existingDraft;
            })
            .map(draftRepository::save)
            .map(draftMapper::toDto);
    }

    /**
     * Get all the drafts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftDTO> findAll() {
        log.debug("Request to get all Drafts");
        return draftRepository.findAll().stream().map(draftMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the drafts with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DraftDTO> findAllWithEagerRelationships(Pageable pageable) {
        return draftRepository.findAllWithEagerRelationships(pageable).map(draftMapper::toDto);
    }

    /**
     * Get one draft by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftDTO> findOne(Long id) {
        log.debug("Request to get Draft : {}", id);
        return draftRepository.findOneWithEagerRelationships(id).map(draftMapper::toDto);
    }

    /**
     * Delete the draft by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Draft : {}", id);
        draftRepository.deleteById(id);
    }
}
