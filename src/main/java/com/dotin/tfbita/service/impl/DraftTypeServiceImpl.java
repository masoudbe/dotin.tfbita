package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftType;
import com.dotin.tfbita.repository.DraftTypeRepository;
import com.dotin.tfbita.service.DraftTypeService;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
import com.dotin.tfbita.service.mapper.DraftTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftType}.
 */
@Service
@Transactional
public class DraftTypeServiceImpl implements DraftTypeService {

    private final Logger log = LoggerFactory.getLogger(DraftTypeServiceImpl.class);

    private final DraftTypeRepository draftTypeRepository;

    private final DraftTypeMapper draftTypeMapper;

    public DraftTypeServiceImpl(DraftTypeRepository draftTypeRepository, DraftTypeMapper draftTypeMapper) {
        this.draftTypeRepository = draftTypeRepository;
        this.draftTypeMapper = draftTypeMapper;
    }

    @Override
    public DraftTypeDTO save(DraftTypeDTO draftTypeDTO) {
        log.debug("Request to save DraftType : {}", draftTypeDTO);
        DraftType draftType = draftTypeMapper.toEntity(draftTypeDTO);
        draftType = draftTypeRepository.save(draftType);
        return draftTypeMapper.toDto(draftType);
    }

    @Override
    public DraftTypeDTO update(DraftTypeDTO draftTypeDTO) {
        log.debug("Request to update DraftType : {}", draftTypeDTO);
        DraftType draftType = draftTypeMapper.toEntity(draftTypeDTO);
        draftType = draftTypeRepository.save(draftType);
        return draftTypeMapper.toDto(draftType);
    }

    @Override
    public Optional<DraftTypeDTO> partialUpdate(DraftTypeDTO draftTypeDTO) {
        log.debug("Request to partially update DraftType : {}", draftTypeDTO);

        return draftTypeRepository
            .findById(draftTypeDTO.getId())
            .map(existingDraftType -> {
                draftTypeMapper.partialUpdate(existingDraftType, draftTypeDTO);

                return existingDraftType;
            })
            .map(draftTypeRepository::save)
            .map(draftTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftTypeDTO> findAll() {
        log.debug("Request to get all DraftTypes");
        return draftTypeRepository.findAll().stream().map(draftTypeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftTypeDTO> findOne(Long id) {
        log.debug("Request to get DraftType : {}", id);
        return draftTypeRepository.findById(id).map(draftTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftType : {}", id);
        draftTypeRepository.deleteById(id);
    }
}
