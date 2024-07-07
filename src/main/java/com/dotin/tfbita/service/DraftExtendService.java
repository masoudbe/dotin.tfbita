package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftExtend;
import com.dotin.tfbita.repository.DraftExtendRepository;
import com.dotin.tfbita.service.dto.DraftExtendDTO;
import com.dotin.tfbita.service.mapper.DraftExtendMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftExtend}.
 */
@Service
@Transactional
public class DraftExtendService {

    private final Logger log = LoggerFactory.getLogger(DraftExtendService.class);

    private final DraftExtendRepository draftExtendRepository;

    private final DraftExtendMapper draftExtendMapper;

    public DraftExtendService(DraftExtendRepository draftExtendRepository, DraftExtendMapper draftExtendMapper) {
        this.draftExtendRepository = draftExtendRepository;
        this.draftExtendMapper = draftExtendMapper;
    }

    /**
     * Save a draftExtend.
     *
     * @param draftExtendDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftExtendDTO save(DraftExtendDTO draftExtendDTO) {
        log.debug("Request to save DraftExtend : {}", draftExtendDTO);
        DraftExtend draftExtend = draftExtendMapper.toEntity(draftExtendDTO);
        draftExtend = draftExtendRepository.save(draftExtend);
        return draftExtendMapper.toDto(draftExtend);
    }

    /**
     * Update a draftExtend.
     *
     * @param draftExtendDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftExtendDTO update(DraftExtendDTO draftExtendDTO) {
        log.debug("Request to update DraftExtend : {}", draftExtendDTO);
        DraftExtend draftExtend = draftExtendMapper.toEntity(draftExtendDTO);
        draftExtend = draftExtendRepository.save(draftExtend);
        return draftExtendMapper.toDto(draftExtend);
    }

    /**
     * Partially update a draftExtend.
     *
     * @param draftExtendDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftExtendDTO> partialUpdate(DraftExtendDTO draftExtendDTO) {
        log.debug("Request to partially update DraftExtend : {}", draftExtendDTO);

        return draftExtendRepository
            .findById(draftExtendDTO.getId())
            .map(existingDraftExtend -> {
                draftExtendMapper.partialUpdate(existingDraftExtend, draftExtendDTO);

                return existingDraftExtend;
            })
            .map(draftExtendRepository::save)
            .map(draftExtendMapper::toDto);
    }

    /**
     * Get all the draftExtends.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftExtendDTO> findAll() {
        log.debug("Request to get all DraftExtends");
        return draftExtendRepository.findAll().stream().map(draftExtendMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one draftExtend by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftExtendDTO> findOne(Long id) {
        log.debug("Request to get DraftExtend : {}", id);
        return draftExtendRepository.findById(id).map(draftExtendMapper::toDto);
    }

    /**
     * Delete the draftExtend by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftExtend : {}", id);
        draftExtendRepository.deleteById(id);
    }
}
