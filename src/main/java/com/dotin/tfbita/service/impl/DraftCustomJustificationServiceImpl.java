package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftCustomJustification;
import com.dotin.tfbita.repository.DraftCustomJustificationRepository;
import com.dotin.tfbita.service.DraftCustomJustificationService;
import com.dotin.tfbita.service.dto.DraftCustomJustificationDTO;
import com.dotin.tfbita.service.mapper.DraftCustomJustificationMapper;
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
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftCustomJustification}.
 */
@Service
@Transactional
public class DraftCustomJustificationServiceImpl implements DraftCustomJustificationService {

    private final Logger log = LoggerFactory.getLogger(DraftCustomJustificationServiceImpl.class);

    private final DraftCustomJustificationRepository draftCustomJustificationRepository;

    private final DraftCustomJustificationMapper draftCustomJustificationMapper;

    public DraftCustomJustificationServiceImpl(
        DraftCustomJustificationRepository draftCustomJustificationRepository,
        DraftCustomJustificationMapper draftCustomJustificationMapper
    ) {
        this.draftCustomJustificationRepository = draftCustomJustificationRepository;
        this.draftCustomJustificationMapper = draftCustomJustificationMapper;
    }

    @Override
    public DraftCustomJustificationDTO save(DraftCustomJustificationDTO draftCustomJustificationDTO) {
        log.debug("Request to save DraftCustomJustification : {}", draftCustomJustificationDTO);
        DraftCustomJustification draftCustomJustification = draftCustomJustificationMapper.toEntity(draftCustomJustificationDTO);
        draftCustomJustification = draftCustomJustificationRepository.save(draftCustomJustification);
        return draftCustomJustificationMapper.toDto(draftCustomJustification);
    }

    @Override
    public DraftCustomJustificationDTO update(DraftCustomJustificationDTO draftCustomJustificationDTO) {
        log.debug("Request to update DraftCustomJustification : {}", draftCustomJustificationDTO);
        DraftCustomJustification draftCustomJustification = draftCustomJustificationMapper.toEntity(draftCustomJustificationDTO);
        draftCustomJustification = draftCustomJustificationRepository.save(draftCustomJustification);
        return draftCustomJustificationMapper.toDto(draftCustomJustification);
    }

    @Override
    public Optional<DraftCustomJustificationDTO> partialUpdate(DraftCustomJustificationDTO draftCustomJustificationDTO) {
        log.debug("Request to partially update DraftCustomJustification : {}", draftCustomJustificationDTO);

        return draftCustomJustificationRepository
            .findById(draftCustomJustificationDTO.getId())
            .map(existingDraftCustomJustification -> {
                draftCustomJustificationMapper.partialUpdate(existingDraftCustomJustification, draftCustomJustificationDTO);

                return existingDraftCustomJustification;
            })
            .map(draftCustomJustificationRepository::save)
            .map(draftCustomJustificationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftCustomJustificationDTO> findAll() {
        log.debug("Request to get all DraftCustomJustifications");
        return draftCustomJustificationRepository
            .findAll()
            .stream()
            .map(draftCustomJustificationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<DraftCustomJustificationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return draftCustomJustificationRepository.findAllWithEagerRelationships(pageable).map(draftCustomJustificationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftCustomJustificationDTO> findOne(Long id) {
        log.debug("Request to get DraftCustomJustification : {}", id);
        return draftCustomJustificationRepository.findOneWithEagerRelationships(id).map(draftCustomJustificationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftCustomJustification : {}", id);
        draftCustomJustificationRepository.deleteById(id);
    }
}
