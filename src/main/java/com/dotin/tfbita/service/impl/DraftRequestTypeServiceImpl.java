package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftRequestType;
import com.dotin.tfbita.repository.DraftRequestTypeRepository;
import com.dotin.tfbita.service.DraftRequestTypeService;
import com.dotin.tfbita.service.dto.DraftRequestTypeDTO;
import com.dotin.tfbita.service.mapper.DraftRequestTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftRequestType}.
 */
@Service
@Transactional
public class DraftRequestTypeServiceImpl implements DraftRequestTypeService {

    private static final Logger log = LoggerFactory.getLogger(DraftRequestTypeServiceImpl.class);

    private final DraftRequestTypeRepository draftRequestTypeRepository;

    private final DraftRequestTypeMapper draftRequestTypeMapper;

    public DraftRequestTypeServiceImpl(
        DraftRequestTypeRepository draftRequestTypeRepository,
        DraftRequestTypeMapper draftRequestTypeMapper
    ) {
        this.draftRequestTypeRepository = draftRequestTypeRepository;
        this.draftRequestTypeMapper = draftRequestTypeMapper;
    }

    @Override
    public DraftRequestTypeDTO save(DraftRequestTypeDTO draftRequestTypeDTO) {
        log.debug("Request to save DraftRequestType : {}", draftRequestTypeDTO);
        DraftRequestType draftRequestType = draftRequestTypeMapper.toEntity(draftRequestTypeDTO);
        draftRequestType = draftRequestTypeRepository.save(draftRequestType);
        return draftRequestTypeMapper.toDto(draftRequestType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftRequestTypeDTO> findAll() {
        log.debug("Request to get all DraftRequestTypes");
        return draftRequestTypeRepository
            .findAll()
            .stream()
            .map(draftRequestTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftRequestTypeDTO> findOne(Long id) {
        log.debug("Request to get DraftRequestType : {}", id);
        return draftRequestTypeRepository.findById(id).map(draftRequestTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftRequestType : {}", id);
        draftRequestTypeRepository.deleteById(id);
    }
}
