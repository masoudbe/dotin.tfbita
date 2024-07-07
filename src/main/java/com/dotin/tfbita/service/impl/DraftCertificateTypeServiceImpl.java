package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftCertificateType;
import com.dotin.tfbita.repository.DraftCertificateTypeRepository;
import com.dotin.tfbita.service.DraftCertificateTypeService;
import com.dotin.tfbita.service.dto.DraftCertificateTypeDTO;
import com.dotin.tfbita.service.mapper.DraftCertificateTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftCertificateType}.
 */
@Service
@Transactional
public class DraftCertificateTypeServiceImpl implements DraftCertificateTypeService {

    private static final Logger log = LoggerFactory.getLogger(DraftCertificateTypeServiceImpl.class);

    private final DraftCertificateTypeRepository draftCertificateTypeRepository;

    private final DraftCertificateTypeMapper draftCertificateTypeMapper;

    public DraftCertificateTypeServiceImpl(
        DraftCertificateTypeRepository draftCertificateTypeRepository,
        DraftCertificateTypeMapper draftCertificateTypeMapper
    ) {
        this.draftCertificateTypeRepository = draftCertificateTypeRepository;
        this.draftCertificateTypeMapper = draftCertificateTypeMapper;
    }

    @Override
    public DraftCertificateTypeDTO save(DraftCertificateTypeDTO draftCertificateTypeDTO) {
        log.debug("Request to save DraftCertificateType : {}", draftCertificateTypeDTO);
        DraftCertificateType draftCertificateType = draftCertificateTypeMapper.toEntity(draftCertificateTypeDTO);
        draftCertificateType = draftCertificateTypeRepository.save(draftCertificateType);
        return draftCertificateTypeMapper.toDto(draftCertificateType);
    }

    @Override
    public DraftCertificateTypeDTO update(DraftCertificateTypeDTO draftCertificateTypeDTO) {
        log.debug("Request to update DraftCertificateType : {}", draftCertificateTypeDTO);
        DraftCertificateType draftCertificateType = draftCertificateTypeMapper.toEntity(draftCertificateTypeDTO);
        draftCertificateType = draftCertificateTypeRepository.save(draftCertificateType);
        return draftCertificateTypeMapper.toDto(draftCertificateType);
    }

    @Override
    public Optional<DraftCertificateTypeDTO> partialUpdate(DraftCertificateTypeDTO draftCertificateTypeDTO) {
        log.debug("Request to partially update DraftCertificateType : {}", draftCertificateTypeDTO);

        return draftCertificateTypeRepository
            .findById(draftCertificateTypeDTO.getId())
            .map(existingDraftCertificateType -> {
                draftCertificateTypeMapper.partialUpdate(existingDraftCertificateType, draftCertificateTypeDTO);

                return existingDraftCertificateType;
            })
            .map(draftCertificateTypeRepository::save)
            .map(draftCertificateTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftCertificateTypeDTO> findAll() {
        log.debug("Request to get all DraftCertificateTypes");
        return draftCertificateTypeRepository
            .findAll()
            .stream()
            .map(draftCertificateTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftCertificateTypeDTO> findOne(Long id) {
        log.debug("Request to get DraftCertificateType : {}", id);
        return draftCertificateTypeRepository.findById(id).map(draftCertificateTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftCertificateType : {}", id);
        draftCertificateTypeRepository.deleteById(id);
    }
}
