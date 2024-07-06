package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftRequestType;
import com.dotin.tfbita.repository.DraftRequestTypeRepository;
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
public class DraftRequestTypeService {

    private final Logger log = LoggerFactory.getLogger(DraftRequestTypeService.class);

    private final DraftRequestTypeRepository draftRequestTypeRepository;

    private final DraftRequestTypeMapper draftRequestTypeMapper;

    public DraftRequestTypeService(DraftRequestTypeRepository draftRequestTypeRepository, DraftRequestTypeMapper draftRequestTypeMapper) {
        this.draftRequestTypeRepository = draftRequestTypeRepository;
        this.draftRequestTypeMapper = draftRequestTypeMapper;
    }

    /**
     * Save a draftRequestType.
     *
     * @param draftRequestTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftRequestTypeDTO save(DraftRequestTypeDTO draftRequestTypeDTO) {
        log.debug("Request to save DraftRequestType : {}", draftRequestTypeDTO);
        DraftRequestType draftRequestType = draftRequestTypeMapper.toEntity(draftRequestTypeDTO);
        draftRequestType = draftRequestTypeRepository.save(draftRequestType);
        return draftRequestTypeMapper.toDto(draftRequestType);
    }

    /**
     * Get all the draftRequestTypes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftRequestTypeDTO> findAll() {
        log.debug("Request to get all DraftRequestTypes");
        return draftRequestTypeRepository
            .findAll()
            .stream()
            .map(draftRequestTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one draftRequestType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftRequestTypeDTO> findOne(Long id) {
        log.debug("Request to get DraftRequestType : {}", id);
        return draftRequestTypeRepository.findById(id).map(draftRequestTypeMapper::toDto);
    }

    /**
     * Delete the draftRequestType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftRequestType : {}", id);
        draftRequestTypeRepository.deleteById(id);
    }
}
