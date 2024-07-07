package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftExtend;
import com.dotin.tfbita.repository.DraftExtendRepository;
import com.dotin.tfbita.service.DraftExtendService;
import com.dotin.tfbita.service.dto.DraftExtendDTO;
import com.dotin.tfbita.service.mapper.DraftExtendMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftExtend}.
 */
@Service
@Transactional
public class DraftExtendServiceImpl implements DraftExtendService {

    private static final Logger log = LoggerFactory.getLogger(DraftExtendServiceImpl.class);

    private final DraftExtendRepository draftExtendRepository;

    private final DraftExtendMapper draftExtendMapper;

    public DraftExtendServiceImpl(DraftExtendRepository draftExtendRepository, DraftExtendMapper draftExtendMapper) {
        this.draftExtendRepository = draftExtendRepository;
        this.draftExtendMapper = draftExtendMapper;
    }

    @Override
    public DraftExtendDTO save(DraftExtendDTO draftExtendDTO) {
        log.debug("Request to save DraftExtend : {}", draftExtendDTO);
        DraftExtend draftExtend = draftExtendMapper.toEntity(draftExtendDTO);
        draftExtend = draftExtendRepository.save(draftExtend);
        return draftExtendMapper.toDto(draftExtend);
    }

    @Override
    public DraftExtendDTO update(DraftExtendDTO draftExtendDTO) {
        log.debug("Request to update DraftExtend : {}", draftExtendDTO);
        DraftExtend draftExtend = draftExtendMapper.toEntity(draftExtendDTO);
        draftExtend = draftExtendRepository.save(draftExtend);
        return draftExtendMapper.toDto(draftExtend);
    }

    @Override
    public Optional<DraftExtendDTO> partialUpdate(DraftExtendDTO draftExtendDTO) {
        log.debug("Request to partially update DraftExtend : {}", draftExtendDTO);

        return draftExtendRepository
            .findById(draftExtendDTO.getId())
            .map(existingDraftExtend -> {
                draftExtendMapper.partialUpdate(existingDraftExtend, draftExtendDTO);

                return existingDraftExtend;
            })
            .map(draftExtendRepository::save)
            .map(draftExtendMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftExtendDTO> findAll() {
        log.debug("Request to get all DraftExtends");
        return draftExtendRepository.findAll().stream().map(draftExtendMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftExtendDTO> findOne(Long id) {
        log.debug("Request to get DraftExtend : {}", id);
        return draftExtendRepository.findById(id).map(draftExtendMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftExtend : {}", id);
        draftExtendRepository.deleteById(id);
    }
}
