package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.JustificationDeductionAmount;
import com.dotin.tfbita.repository.JustificationDeductionAmountRepository;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountDTO;
import com.dotin.tfbita.service.mapper.JustificationDeductionAmountMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.JustificationDeductionAmount}.
 */
@Service
@Transactional
public class JustificationDeductionAmountService {

    private final Logger log = LoggerFactory.getLogger(JustificationDeductionAmountService.class);

    private final JustificationDeductionAmountRepository justificationDeductionAmountRepository;

    private final JustificationDeductionAmountMapper justificationDeductionAmountMapper;

    public JustificationDeductionAmountService(
        JustificationDeductionAmountRepository justificationDeductionAmountRepository,
        JustificationDeductionAmountMapper justificationDeductionAmountMapper
    ) {
        this.justificationDeductionAmountRepository = justificationDeductionAmountRepository;
        this.justificationDeductionAmountMapper = justificationDeductionAmountMapper;
    }

    /**
     * Save a justificationDeductionAmount.
     *
     * @param justificationDeductionAmountDTO the entity to save.
     * @return the persisted entity.
     */
    public JustificationDeductionAmountDTO save(JustificationDeductionAmountDTO justificationDeductionAmountDTO) {
        log.debug("Request to save JustificationDeductionAmount : {}", justificationDeductionAmountDTO);
        JustificationDeductionAmount justificationDeductionAmount = justificationDeductionAmountMapper.toEntity(
            justificationDeductionAmountDTO
        );
        justificationDeductionAmount = justificationDeductionAmountRepository.save(justificationDeductionAmount);
        return justificationDeductionAmountMapper.toDto(justificationDeductionAmount);
    }

    /**
     * Update a justificationDeductionAmount.
     *
     * @param justificationDeductionAmountDTO the entity to save.
     * @return the persisted entity.
     */
    public JustificationDeductionAmountDTO update(JustificationDeductionAmountDTO justificationDeductionAmountDTO) {
        log.debug("Request to update JustificationDeductionAmount : {}", justificationDeductionAmountDTO);
        JustificationDeductionAmount justificationDeductionAmount = justificationDeductionAmountMapper.toEntity(
            justificationDeductionAmountDTO
        );
        justificationDeductionAmount = justificationDeductionAmountRepository.save(justificationDeductionAmount);
        return justificationDeductionAmountMapper.toDto(justificationDeductionAmount);
    }

    /**
     * Partially update a justificationDeductionAmount.
     *
     * @param justificationDeductionAmountDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JustificationDeductionAmountDTO> partialUpdate(JustificationDeductionAmountDTO justificationDeductionAmountDTO) {
        log.debug("Request to partially update JustificationDeductionAmount : {}", justificationDeductionAmountDTO);

        return justificationDeductionAmountRepository
            .findById(justificationDeductionAmountDTO.getId())
            .map(existingJustificationDeductionAmount -> {
                justificationDeductionAmountMapper.partialUpdate(existingJustificationDeductionAmount, justificationDeductionAmountDTO);

                return existingJustificationDeductionAmount;
            })
            .map(justificationDeductionAmountRepository::save)
            .map(justificationDeductionAmountMapper::toDto);
    }

    /**
     * Get all the justificationDeductionAmounts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<JustificationDeductionAmountDTO> findAll() {
        log.debug("Request to get all JustificationDeductionAmounts");
        return justificationDeductionAmountRepository
            .findAll()
            .stream()
            .map(justificationDeductionAmountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one justificationDeductionAmount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JustificationDeductionAmountDTO> findOne(Long id) {
        log.debug("Request to get JustificationDeductionAmount : {}", id);
        return justificationDeductionAmountRepository.findById(id).map(justificationDeductionAmountMapper::toDto);
    }

    /**
     * Delete the justificationDeductionAmount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete JustificationDeductionAmount : {}", id);
        justificationDeductionAmountRepository.deleteById(id);
    }
}
