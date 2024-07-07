package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.JustificationDeductionAmountPart;
import com.dotin.tfbita.repository.JustificationDeductionAmountPartRepository;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountPartDTO;
import com.dotin.tfbita.service.mapper.JustificationDeductionAmountPartMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.JustificationDeductionAmountPart}.
 */
@Service
@Transactional
public class JustificationDeductionAmountPartService {

    private final Logger log = LoggerFactory.getLogger(JustificationDeductionAmountPartService.class);

    private final JustificationDeductionAmountPartRepository justificationDeductionAmountPartRepository;

    private final JustificationDeductionAmountPartMapper justificationDeductionAmountPartMapper;

    public JustificationDeductionAmountPartService(
        JustificationDeductionAmountPartRepository justificationDeductionAmountPartRepository,
        JustificationDeductionAmountPartMapper justificationDeductionAmountPartMapper
    ) {
        this.justificationDeductionAmountPartRepository = justificationDeductionAmountPartRepository;
        this.justificationDeductionAmountPartMapper = justificationDeductionAmountPartMapper;
    }

    /**
     * Save a justificationDeductionAmountPart.
     *
     * @param justificationDeductionAmountPartDTO the entity to save.
     * @return the persisted entity.
     */
    public JustificationDeductionAmountPartDTO save(JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO) {
        log.debug("Request to save JustificationDeductionAmountPart : {}", justificationDeductionAmountPartDTO);
        JustificationDeductionAmountPart justificationDeductionAmountPart = justificationDeductionAmountPartMapper.toEntity(
            justificationDeductionAmountPartDTO
        );
        justificationDeductionAmountPart = justificationDeductionAmountPartRepository.save(justificationDeductionAmountPart);
        return justificationDeductionAmountPartMapper.toDto(justificationDeductionAmountPart);
    }

    /**
     * Update a justificationDeductionAmountPart.
     *
     * @param justificationDeductionAmountPartDTO the entity to save.
     * @return the persisted entity.
     */
    public JustificationDeductionAmountPartDTO update(JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO) {
        log.debug("Request to update JustificationDeductionAmountPart : {}", justificationDeductionAmountPartDTO);
        JustificationDeductionAmountPart justificationDeductionAmountPart = justificationDeductionAmountPartMapper.toEntity(
            justificationDeductionAmountPartDTO
        );
        justificationDeductionAmountPart = justificationDeductionAmountPartRepository.save(justificationDeductionAmountPart);
        return justificationDeductionAmountPartMapper.toDto(justificationDeductionAmountPart);
    }

    /**
     * Partially update a justificationDeductionAmountPart.
     *
     * @param justificationDeductionAmountPartDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JustificationDeductionAmountPartDTO> partialUpdate(
        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO
    ) {
        log.debug("Request to partially update JustificationDeductionAmountPart : {}", justificationDeductionAmountPartDTO);

        return justificationDeductionAmountPartRepository
            .findById(justificationDeductionAmountPartDTO.getId())
            .map(existingJustificationDeductionAmountPart -> {
                justificationDeductionAmountPartMapper.partialUpdate(
                    existingJustificationDeductionAmountPart,
                    justificationDeductionAmountPartDTO
                );

                return existingJustificationDeductionAmountPart;
            })
            .map(justificationDeductionAmountPartRepository::save)
            .map(justificationDeductionAmountPartMapper::toDto);
    }

    /**
     * Get all the justificationDeductionAmountParts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<JustificationDeductionAmountPartDTO> findAll() {
        log.debug("Request to get all JustificationDeductionAmountParts");
        return justificationDeductionAmountPartRepository
            .findAll()
            .stream()
            .map(justificationDeductionAmountPartMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one justificationDeductionAmountPart by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JustificationDeductionAmountPartDTO> findOne(Long id) {
        log.debug("Request to get JustificationDeductionAmountPart : {}", id);
        return justificationDeductionAmountPartRepository.findById(id).map(justificationDeductionAmountPartMapper::toDto);
    }

    /**
     * Delete the justificationDeductionAmountPart by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete JustificationDeductionAmountPart : {}", id);
        justificationDeductionAmountPartRepository.deleteById(id);
    }
}
