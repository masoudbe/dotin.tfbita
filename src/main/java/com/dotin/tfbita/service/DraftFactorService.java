package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftFactor;
import com.dotin.tfbita.repository.DraftFactorRepository;
import com.dotin.tfbita.service.dto.DraftFactorDTO;
import com.dotin.tfbita.service.mapper.DraftFactorMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftFactor}.
 */
@Service
@Transactional
public class DraftFactorService {

    private final Logger log = LoggerFactory.getLogger(DraftFactorService.class);

    private final DraftFactorRepository draftFactorRepository;

    private final DraftFactorMapper draftFactorMapper;

    public DraftFactorService(DraftFactorRepository draftFactorRepository, DraftFactorMapper draftFactorMapper) {
        this.draftFactorRepository = draftFactorRepository;
        this.draftFactorMapper = draftFactorMapper;
    }

    /**
     * Save a draftFactor.
     *
     * @param draftFactorDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftFactorDTO save(DraftFactorDTO draftFactorDTO) {
        log.debug("Request to save DraftFactor : {}", draftFactorDTO);
        DraftFactor draftFactor = draftFactorMapper.toEntity(draftFactorDTO);
        draftFactor = draftFactorRepository.save(draftFactor);
        return draftFactorMapper.toDto(draftFactor);
    }

    /**
     * Update a draftFactor.
     *
     * @param draftFactorDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftFactorDTO update(DraftFactorDTO draftFactorDTO) {
        log.debug("Request to update DraftFactor : {}", draftFactorDTO);
        DraftFactor draftFactor = draftFactorMapper.toEntity(draftFactorDTO);
        draftFactor = draftFactorRepository.save(draftFactor);
        return draftFactorMapper.toDto(draftFactor);
    }

    /**
     * Partially update a draftFactor.
     *
     * @param draftFactorDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftFactorDTO> partialUpdate(DraftFactorDTO draftFactorDTO) {
        log.debug("Request to partially update DraftFactor : {}", draftFactorDTO);

        return draftFactorRepository
            .findById(draftFactorDTO.getId())
            .map(existingDraftFactor -> {
                draftFactorMapper.partialUpdate(existingDraftFactor, draftFactorDTO);

                return existingDraftFactor;
            })
            .map(draftFactorRepository::save)
            .map(draftFactorMapper::toDto);
    }

    /**
     * Get all the draftFactors.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftFactorDTO> findAll() {
        log.debug("Request to get all DraftFactors");
        return draftFactorRepository.findAll().stream().map(draftFactorMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one draftFactor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftFactorDTO> findOne(Long id) {
        log.debug("Request to get DraftFactor : {}", id);
        return draftFactorRepository.findById(id).map(draftFactorMapper::toDto);
    }

    /**
     * Delete the draftFactor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftFactor : {}", id);
        draftFactorRepository.deleteById(id);
    }
}
