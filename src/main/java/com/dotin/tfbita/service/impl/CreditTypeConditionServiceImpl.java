package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.CreditTypeCondition;
import com.dotin.tfbita.repository.CreditTypeConditionRepository;
import com.dotin.tfbita.service.CreditTypeConditionService;
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
public class CreditTypeConditionServiceImpl implements CreditTypeConditionService {

    private static final Logger log = LoggerFactory.getLogger(CreditTypeConditionServiceImpl.class);

    private final CreditTypeConditionRepository creditTypeConditionRepository;

    private final CreditTypeConditionMapper creditTypeConditionMapper;

    public CreditTypeConditionServiceImpl(
        CreditTypeConditionRepository creditTypeConditionRepository,
        CreditTypeConditionMapper creditTypeConditionMapper
    ) {
        this.creditTypeConditionRepository = creditTypeConditionRepository;
        this.creditTypeConditionMapper = creditTypeConditionMapper;
    }

    @Override
    public CreditTypeConditionDTO save(CreditTypeConditionDTO creditTypeConditionDTO) {
        log.debug("Request to save CreditTypeCondition : {}", creditTypeConditionDTO);
        CreditTypeCondition creditTypeCondition = creditTypeConditionMapper.toEntity(creditTypeConditionDTO);
        creditTypeCondition = creditTypeConditionRepository.save(creditTypeCondition);
        return creditTypeConditionMapper.toDto(creditTypeCondition);
    }

    @Override
    public CreditTypeConditionDTO update(CreditTypeConditionDTO creditTypeConditionDTO) {
        log.debug("Request to update CreditTypeCondition : {}", creditTypeConditionDTO);
        CreditTypeCondition creditTypeCondition = creditTypeConditionMapper.toEntity(creditTypeConditionDTO);
        creditTypeCondition = creditTypeConditionRepository.save(creditTypeCondition);
        return creditTypeConditionMapper.toDto(creditTypeCondition);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<CreditTypeConditionDTO> findAll() {
        log.debug("Request to get all CreditTypeConditions");
        return creditTypeConditionRepository
            .findAll()
            .stream()
            .map(creditTypeConditionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CreditTypeConditionDTO> findOne(Long id) {
        log.debug("Request to get CreditTypeCondition : {}", id);
        return creditTypeConditionRepository.findById(id).map(creditTypeConditionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CreditTypeCondition : {}", id);
        creditTypeConditionRepository.deleteById(id);
    }
}
