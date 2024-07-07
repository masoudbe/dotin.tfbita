package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftUsedAssurance;
import com.dotin.tfbita.repository.DraftUsedAssuranceRepository;
import com.dotin.tfbita.service.DraftUsedAssuranceService;
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
public class DraftUsedAssuranceServiceImpl implements DraftUsedAssuranceService {

    private static final Logger log = LoggerFactory.getLogger(DraftUsedAssuranceServiceImpl.class);

    private final DraftUsedAssuranceRepository draftUsedAssuranceRepository;

    private final DraftUsedAssuranceMapper draftUsedAssuranceMapper;

    public DraftUsedAssuranceServiceImpl(
        DraftUsedAssuranceRepository draftUsedAssuranceRepository,
        DraftUsedAssuranceMapper draftUsedAssuranceMapper
    ) {
        this.draftUsedAssuranceRepository = draftUsedAssuranceRepository;
        this.draftUsedAssuranceMapper = draftUsedAssuranceMapper;
    }

    @Override
    public DraftUsedAssuranceDTO save(DraftUsedAssuranceDTO draftUsedAssuranceDTO) {
        log.debug("Request to save DraftUsedAssurance : {}", draftUsedAssuranceDTO);
        DraftUsedAssurance draftUsedAssurance = draftUsedAssuranceMapper.toEntity(draftUsedAssuranceDTO);
        draftUsedAssurance = draftUsedAssuranceRepository.save(draftUsedAssurance);
        return draftUsedAssuranceMapper.toDto(draftUsedAssurance);
    }

    @Override
    public DraftUsedAssuranceDTO update(DraftUsedAssuranceDTO draftUsedAssuranceDTO) {
        log.debug("Request to update DraftUsedAssurance : {}", draftUsedAssuranceDTO);
        DraftUsedAssurance draftUsedAssurance = draftUsedAssuranceMapper.toEntity(draftUsedAssuranceDTO);
        draftUsedAssurance = draftUsedAssuranceRepository.save(draftUsedAssurance);
        return draftUsedAssuranceMapper.toDto(draftUsedAssurance);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<DraftUsedAssuranceDTO> findAll() {
        log.debug("Request to get all DraftUsedAssurances");
        return draftUsedAssuranceRepository
            .findAll()
            .stream()
            .map(draftUsedAssuranceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftUsedAssuranceDTO> findOne(Long id) {
        log.debug("Request to get DraftUsedAssurance : {}", id);
        return draftUsedAssuranceRepository.findById(id).map(draftUsedAssuranceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftUsedAssurance : {}", id);
        draftUsedAssuranceRepository.deleteById(id);
    }
}
