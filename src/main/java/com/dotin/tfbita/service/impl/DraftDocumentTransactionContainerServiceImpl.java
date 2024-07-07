package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftDocumentTransactionContainer;
import com.dotin.tfbita.repository.DraftDocumentTransactionContainerRepository;
import com.dotin.tfbita.service.DraftDocumentTransactionContainerService;
import com.dotin.tfbita.service.dto.DraftDocumentTransactionContainerDTO;
import com.dotin.tfbita.service.mapper.DraftDocumentTransactionContainerMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftDocumentTransactionContainer}.
 */
@Service
@Transactional
public class DraftDocumentTransactionContainerServiceImpl implements DraftDocumentTransactionContainerService {

    private static final Logger log = LoggerFactory.getLogger(DraftDocumentTransactionContainerServiceImpl.class);

    private final DraftDocumentTransactionContainerRepository draftDocumentTransactionContainerRepository;

    private final DraftDocumentTransactionContainerMapper draftDocumentTransactionContainerMapper;

    public DraftDocumentTransactionContainerServiceImpl(
        DraftDocumentTransactionContainerRepository draftDocumentTransactionContainerRepository,
        DraftDocumentTransactionContainerMapper draftDocumentTransactionContainerMapper
    ) {
        this.draftDocumentTransactionContainerRepository = draftDocumentTransactionContainerRepository;
        this.draftDocumentTransactionContainerMapper = draftDocumentTransactionContainerMapper;
    }

    @Override
    public DraftDocumentTransactionContainerDTO save(DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO) {
        log.debug("Request to save DraftDocumentTransactionContainer : {}", draftDocumentTransactionContainerDTO);
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = draftDocumentTransactionContainerMapper.toEntity(
            draftDocumentTransactionContainerDTO
        );
        draftDocumentTransactionContainer = draftDocumentTransactionContainerRepository.save(draftDocumentTransactionContainer);
        return draftDocumentTransactionContainerMapper.toDto(draftDocumentTransactionContainer);
    }

    @Override
    public DraftDocumentTransactionContainerDTO update(DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO) {
        log.debug("Request to update DraftDocumentTransactionContainer : {}", draftDocumentTransactionContainerDTO);
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = draftDocumentTransactionContainerMapper.toEntity(
            draftDocumentTransactionContainerDTO
        );
        draftDocumentTransactionContainer = draftDocumentTransactionContainerRepository.save(draftDocumentTransactionContainer);
        return draftDocumentTransactionContainerMapper.toDto(draftDocumentTransactionContainer);
    }

    @Override
    public Optional<DraftDocumentTransactionContainerDTO> partialUpdate(
        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO
    ) {
        log.debug("Request to partially update DraftDocumentTransactionContainer : {}", draftDocumentTransactionContainerDTO);

        return draftDocumentTransactionContainerRepository
            .findById(draftDocumentTransactionContainerDTO.getId())
            .map(existingDraftDocumentTransactionContainer -> {
                draftDocumentTransactionContainerMapper.partialUpdate(
                    existingDraftDocumentTransactionContainer,
                    draftDocumentTransactionContainerDTO
                );

                return existingDraftDocumentTransactionContainer;
            })
            .map(draftDocumentTransactionContainerRepository::save)
            .map(draftDocumentTransactionContainerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftDocumentTransactionContainerDTO> findAll() {
        log.debug("Request to get all DraftDocumentTransactionContainers");
        return draftDocumentTransactionContainerRepository
            .findAll()
            .stream()
            .map(draftDocumentTransactionContainerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftDocumentTransactionContainerDTO> findOne(Long id) {
        log.debug("Request to get DraftDocumentTransactionContainer : {}", id);
        return draftDocumentTransactionContainerRepository.findById(id).map(draftDocumentTransactionContainerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftDocumentTransactionContainer : {}", id);
        draftDocumentTransactionContainerRepository.deleteById(id);
    }
}
