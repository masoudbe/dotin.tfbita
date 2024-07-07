package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftUsedAssurance;
import com.dotin.tfbita.repository.DraftUsedAssuranceRepository;
import com.dotin.tfbita.service.dto.DraftUsedAssuranceDTO;
import com.dotin.tfbita.service.mapper.DraftUsedAssuranceMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftUsedAssurance}.
 */
@Service
@Transactional
public class DraftUsedAssuranceService {

    private final Logger log = LoggerFactory.getLogger(DraftUsedAssuranceService.class);

    private final DraftUsedAssuranceRepository draftUsedAssuranceRepository;

    private final DraftUsedAssuranceMapper draftUsedAssuranceMapper;

    public DraftUsedAssuranceService(
        DraftUsedAssuranceRepository draftUsedAssuranceRepository,
        DraftUsedAssuranceMapper draftUsedAssuranceMapper
    ) {
        this.draftUsedAssuranceRepository = draftUsedAssuranceRepository;
        this.draftUsedAssuranceMapper = draftUsedAssuranceMapper;
    }

    /**
     * Save a draftUsedAssurance.
     *
     * @param draftUsedAssuranceDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftUsedAssuranceDTO save(DraftUsedAssuranceDTO draftUsedAssuranceDTO) {
        log.debug("Request to save DraftUsedAssurance : {}", draftUsedAssuranceDTO);
        DraftUsedAssurance draftUsedAssurance = draftUsedAssuranceMapper.toEntity(draftUsedAssuranceDTO);
        draftUsedAssurance = draftUsedAssuranceRepository.save(draftUsedAssurance);
        return draftUsedAssuranceMapper.toDto(draftUsedAssurance);
    }

    /**
     * Update a draftUsedAssurance.
     *
     * @param draftUsedAssuranceDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftUsedAssuranceDTO update(DraftUsedAssuranceDTO draftUsedAssuranceDTO) {
        log.debug("Request to update DraftUsedAssurance : {}", draftUsedAssuranceDTO);
        DraftUsedAssurance draftUsedAssurance = draftUsedAssuranceMapper.toEntity(draftUsedAssuranceDTO);
        draftUsedAssurance = draftUsedAssuranceRepository.save(draftUsedAssurance);
        return draftUsedAssuranceMapper.toDto(draftUsedAssurance);
    }

    /**
     * Partially update a draftUsedAssurance.
     *
     * @param draftUsedAssuranceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftUsedAssuranceDTO> partialUpdate(DraftUsedAssuranceDTO draftUsedAssuranceDTO) {
        log.debug("Request to partially update DraftUsedAssurance : {}", draftUsedAssuranceDTO);

        return draftUsedAssuranceRepository
            .findById(draftUsedAssuranceDTO.getId())
            .map(existingDraftUsedAssurance -> {
                draftUsedAssuranceMapper.partialUpdate(existingDraftUsedAssurance, draftUsedAssuranceDTO);

                return existingDraftUsedAssurance;
            })
            .map(draftUsedAssuranceRepository::save)
            .map(draftUsedAssuranceMapper::toDto);
    }

    /**
     * Get all the draftUsedAssurances.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftUsedAssuranceDTO> findAll() {
        log.debug("Request to get all DraftUsedAssurances");
        return draftUsedAssuranceRepository
            .findAll()
            .stream()
            .map(draftUsedAssuranceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one draftUsedAssurance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftUsedAssuranceDTO> findOne(Long id) {
        log.debug("Request to get DraftUsedAssurance : {}", id);
        return draftUsedAssuranceRepository.findById(id).map(draftUsedAssuranceMapper::toDto);
    }

    /**
     * Delete the draftUsedAssurance by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftUsedAssurance : {}", id);
        draftUsedAssuranceRepository.deleteById(id);
    }
}
