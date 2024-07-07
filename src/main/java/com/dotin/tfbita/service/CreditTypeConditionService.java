package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.CreditTypeCondition;
import com.dotin.tfbita.repository.CreditTypeConditionRepository;
import com.dotin.tfbita.service.dto.CreditTypeConditionDTO;
import com.dotin.tfbita.service.mapper.CreditTypeConditionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.CreditTypeCondition}.
 */
@Service
@Transactional
public class CreditTypeConditionService {

    private final Logger log = LoggerFactory.getLogger(CreditTypeConditionService.class);

    private final CreditTypeConditionRepository creditTypeConditionRepository;

    private final CreditTypeConditionMapper creditTypeConditionMapper;

    public CreditTypeConditionService(
        CreditTypeConditionRepository creditTypeConditionRepository,
        CreditTypeConditionMapper creditTypeConditionMapper
    ) {
        this.creditTypeConditionRepository = creditTypeConditionRepository;
        this.creditTypeConditionMapper = creditTypeConditionMapper;
    }

    /**
     * Save a creditTypeCondition.
     *
     * @param creditTypeConditionDTO the entity to save.
     * @return the persisted entity.
     */
    public CreditTypeConditionDTO save(CreditTypeConditionDTO creditTypeConditionDTO) {
        log.debug("Request to save CreditTypeCondition : {}", creditTypeConditionDTO);
        CreditTypeCondition creditTypeCondition = creditTypeConditionMapper.toEntity(creditTypeConditionDTO);
        creditTypeCondition = creditTypeConditionRepository.save(creditTypeCondition);
        return creditTypeConditionMapper.toDto(creditTypeCondition);
    }

    /**
     * Update a creditTypeCondition.
     *
     * @param creditTypeConditionDTO the entity to save.
     * @return the persisted entity.
     */
    public CreditTypeConditionDTO update(CreditTypeConditionDTO creditTypeConditionDTO) {
        log.debug("Request to update CreditTypeCondition : {}", creditTypeConditionDTO);
        CreditTypeCondition creditTypeCondition = creditTypeConditionMapper.toEntity(creditTypeConditionDTO);
        creditTypeCondition = creditTypeConditionRepository.save(creditTypeCondition);
        return creditTypeConditionMapper.toDto(creditTypeCondition);
    }

    /**
     * Partially update a creditTypeCondition.
     *
     * @param creditTypeConditionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CreditTypeConditionDTO> partialUpdate(CreditTypeConditionDTO creditTypeConditionDTO) {
        log.debug("Request to partially update CreditTypeCondition : {}", creditTypeConditionDTO);

        return creditTypeConditionRepository
            .findById(creditTypeConditionDTO.getId())
            .map(existingCreditTypeCondition -> {
                creditTypeConditionMapper.partialUpdate(existingCreditTypeCondition, creditTypeConditionDTO);

                return existingCreditTypeCondition;
            })
            .map(creditTypeConditionRepository::save)
            .map(creditTypeConditionMapper::toDto);
    }

    /**
     * Get all the creditTypeConditions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CreditTypeConditionDTO> findAll() {
        log.debug("Request to get all CreditTypeConditions");
        return creditTypeConditionRepository
            .findAll()
            .stream()
            .map(creditTypeConditionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one creditTypeCondition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CreditTypeConditionDTO> findOne(Long id) {
        log.debug("Request to get CreditTypeCondition : {}", id);
        return creditTypeConditionRepository.findById(id).map(creditTypeConditionMapper::toDto);
    }

    /**
     * Delete the creditTypeCondition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CreditTypeCondition : {}", id);
        creditTypeConditionRepository.deleteById(id);
    }
}
