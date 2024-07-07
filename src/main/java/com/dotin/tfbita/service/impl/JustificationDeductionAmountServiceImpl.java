package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.JustificationDeductionAmount;
import com.dotin.tfbita.repository.JustificationDeductionAmountRepository;
import com.dotin.tfbita.service.JustificationDeductionAmountService;
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
public class JustificationDeductionAmountServiceImpl implements JustificationDeductionAmountService {

    private static final Logger log = LoggerFactory.getLogger(JustificationDeductionAmountServiceImpl.class);

    private final JustificationDeductionAmountRepository justificationDeductionAmountRepository;

    private final JustificationDeductionAmountMapper justificationDeductionAmountMapper;

    public JustificationDeductionAmountServiceImpl(
        JustificationDeductionAmountRepository justificationDeductionAmountRepository,
        JustificationDeductionAmountMapper justificationDeductionAmountMapper
    ) {
        this.justificationDeductionAmountRepository = justificationDeductionAmountRepository;
        this.justificationDeductionAmountMapper = justificationDeductionAmountMapper;
    }

    @Override
    public JustificationDeductionAmountDTO save(JustificationDeductionAmountDTO justificationDeductionAmountDTO) {
        log.debug("Request to save JustificationDeductionAmount : {}", justificationDeductionAmountDTO);
        JustificationDeductionAmount justificationDeductionAmount = justificationDeductionAmountMapper.toEntity(
            justificationDeductionAmountDTO
        );
        justificationDeductionAmount = justificationDeductionAmountRepository.save(justificationDeductionAmount);
        return justificationDeductionAmountMapper.toDto(justificationDeductionAmount);
    }

    @Override
    public JustificationDeductionAmountDTO update(JustificationDeductionAmountDTO justificationDeductionAmountDTO) {
        log.debug("Request to update JustificationDeductionAmount : {}", justificationDeductionAmountDTO);
        JustificationDeductionAmount justificationDeductionAmount = justificationDeductionAmountMapper.toEntity(
            justificationDeductionAmountDTO
        );
        justificationDeductionAmount = justificationDeductionAmountRepository.save(justificationDeductionAmount);
        return justificationDeductionAmountMapper.toDto(justificationDeductionAmount);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<JustificationDeductionAmountDTO> findAll() {
        log.debug("Request to get all JustificationDeductionAmounts");
        return justificationDeductionAmountRepository
            .findAll()
            .stream()
            .map(justificationDeductionAmountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JustificationDeductionAmountDTO> findOne(Long id) {
        log.debug("Request to get JustificationDeductionAmount : {}", id);
        return justificationDeductionAmountRepository.findById(id).map(justificationDeductionAmountMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete JustificationDeductionAmount : {}", id);
        justificationDeductionAmountRepository.deleteById(id);
    }
}
