package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.JustificationDeductionDetail;
import com.dotin.tfbita.repository.JustificationDeductionDetailRepository;
import com.dotin.tfbita.service.JustificationDeductionDetailService;
import com.dotin.tfbita.service.dto.JustificationDeductionDetailDTO;
import com.dotin.tfbita.service.mapper.JustificationDeductionDetailMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.JustificationDeductionDetail}.
 */
@Service
@Transactional
public class JustificationDeductionDetailServiceImpl implements JustificationDeductionDetailService {

    private static final Logger log = LoggerFactory.getLogger(JustificationDeductionDetailServiceImpl.class);

    private final JustificationDeductionDetailRepository justificationDeductionDetailRepository;

    private final JustificationDeductionDetailMapper justificationDeductionDetailMapper;

    public JustificationDeductionDetailServiceImpl(
        JustificationDeductionDetailRepository justificationDeductionDetailRepository,
        JustificationDeductionDetailMapper justificationDeductionDetailMapper
    ) {
        this.justificationDeductionDetailRepository = justificationDeductionDetailRepository;
        this.justificationDeductionDetailMapper = justificationDeductionDetailMapper;
    }

    @Override
    public JustificationDeductionDetailDTO save(JustificationDeductionDetailDTO justificationDeductionDetailDTO) {
        log.debug("Request to save JustificationDeductionDetail : {}", justificationDeductionDetailDTO);
        JustificationDeductionDetail justificationDeductionDetail = justificationDeductionDetailMapper.toEntity(
            justificationDeductionDetailDTO
        );
        justificationDeductionDetail = justificationDeductionDetailRepository.save(justificationDeductionDetail);
        return justificationDeductionDetailMapper.toDto(justificationDeductionDetail);
    }

    @Override
    public JustificationDeductionDetailDTO update(JustificationDeductionDetailDTO justificationDeductionDetailDTO) {
        log.debug("Request to update JustificationDeductionDetail : {}", justificationDeductionDetailDTO);
        JustificationDeductionDetail justificationDeductionDetail = justificationDeductionDetailMapper.toEntity(
            justificationDeductionDetailDTO
        );
        justificationDeductionDetail = justificationDeductionDetailRepository.save(justificationDeductionDetail);
        return justificationDeductionDetailMapper.toDto(justificationDeductionDetail);
    }

    @Override
    public Optional<JustificationDeductionDetailDTO> partialUpdate(JustificationDeductionDetailDTO justificationDeductionDetailDTO) {
        log.debug("Request to partially update JustificationDeductionDetail : {}", justificationDeductionDetailDTO);

        return justificationDeductionDetailRepository
            .findById(justificationDeductionDetailDTO.getId())
            .map(existingJustificationDeductionDetail -> {
                justificationDeductionDetailMapper.partialUpdate(existingJustificationDeductionDetail, justificationDeductionDetailDTO);

                return existingJustificationDeductionDetail;
            })
            .map(justificationDeductionDetailRepository::save)
            .map(justificationDeductionDetailMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JustificationDeductionDetailDTO> findAll() {
        log.debug("Request to get all JustificationDeductionDetails");
        return justificationDeductionDetailRepository
            .findAll()
            .stream()
            .map(justificationDeductionDetailMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<JustificationDeductionDetailDTO> findOne(Long id) {
        log.debug("Request to get JustificationDeductionDetail : {}", id);
        return justificationDeductionDetailRepository.findById(id).map(justificationDeductionDetailMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete JustificationDeductionDetail : {}", id);
        justificationDeductionDetailRepository.deleteById(id);
    }
}
