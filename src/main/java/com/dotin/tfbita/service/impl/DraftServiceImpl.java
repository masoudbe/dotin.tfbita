package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.repository.DraftRepository;
import com.dotin.tfbita.service.DraftService;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.mapper.DraftMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.Draft}.
 */
@Service
@Transactional
public class DraftServiceImpl implements DraftService {

    private final Logger log = LoggerFactory.getLogger(DraftServiceImpl.class);

    private final DraftRepository draftRepository;

    private final DraftMapper draftMapper;

    public DraftServiceImpl(DraftRepository draftRepository, DraftMapper draftMapper) {
        this.draftRepository = draftRepository;
        this.draftMapper = draftMapper;
    }

    @Override
    public DraftDTO save(DraftDTO draftDTO) {
        log.debug("Request to save Draft : {}", draftDTO);
        Draft draft = draftMapper.toEntity(draftDTO);
        draft = draftRepository.save(draft);
        return draftMapper.toDto(draft);
    }

    @Override
    public DraftDTO update(DraftDTO draftDTO) {
        log.debug("Request to update Draft : {}", draftDTO);
        Draft draft = draftMapper.toEntity(draftDTO);
        draft = draftRepository.save(draft);
        return draftMapper.toDto(draft);
    }

    @Override
    public Optional<DraftDTO> partialUpdate(DraftDTO draftDTO) {
        log.debug("Request to partially update Draft : {}", draftDTO);

        return draftRepository
            .findById(draftDTO.getId())
            .map(existingDraft -> {
                draftMapper.partialUpdate(existingDraft, draftDTO);

                return existingDraft;
            })
            .map(draftRepository::save)
            .map(draftMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftDTO> findAll() {
        log.debug("Request to get all Drafts");
        return draftRepository.findAll().stream().map(draftMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftDTO> findOne(Long id) {
        log.debug("Request to get Draft : {}", id);
        return draftRepository.findById(id).map(draftMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Draft : {}", id);
        draftRepository.deleteById(id);
    }
}
