package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer;
import com.dotin.tfbita.repository.DraftReceiptDocumentTransactionContainerRepository;
import com.dotin.tfbita.service.DraftReceiptDocumentTransactionContainerService;
import com.dotin.tfbita.service.dto.DraftReceiptDocumentTransactionContainerDTO;
import com.dotin.tfbita.service.mapper.DraftReceiptDocumentTransactionContainerMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer}.
 */
@Service
@Transactional
public class DraftReceiptDocumentTransactionContainerServiceImpl implements DraftReceiptDocumentTransactionContainerService {

    private static final Logger log = LoggerFactory.getLogger(DraftReceiptDocumentTransactionContainerServiceImpl.class);

    private final DraftReceiptDocumentTransactionContainerRepository draftReceiptDocumentTransactionContainerRepository;

    private final DraftReceiptDocumentTransactionContainerMapper draftReceiptDocumentTransactionContainerMapper;

    public DraftReceiptDocumentTransactionContainerServiceImpl(
        DraftReceiptDocumentTransactionContainerRepository draftReceiptDocumentTransactionContainerRepository,
        DraftReceiptDocumentTransactionContainerMapper draftReceiptDocumentTransactionContainerMapper
    ) {
        this.draftReceiptDocumentTransactionContainerRepository = draftReceiptDocumentTransactionContainerRepository;
        this.draftReceiptDocumentTransactionContainerMapper = draftReceiptDocumentTransactionContainerMapper;
    }

    @Override
    public DraftReceiptDocumentTransactionContainerDTO save(
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    ) {
        log.debug("Request to save DraftReceiptDocumentTransactionContainer : {}", draftReceiptDocumentTransactionContainerDTO);
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            draftReceiptDocumentTransactionContainerMapper.toEntity(draftReceiptDocumentTransactionContainerDTO);
        draftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerRepository.save(
            draftReceiptDocumentTransactionContainer
        );
        return draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);
    }

    @Override
    public DraftReceiptDocumentTransactionContainerDTO update(
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    ) {
        log.debug("Request to update DraftReceiptDocumentTransactionContainer : {}", draftReceiptDocumentTransactionContainerDTO);
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            draftReceiptDocumentTransactionContainerMapper.toEntity(draftReceiptDocumentTransactionContainerDTO);
        draftReceiptDocumentTransactionContainer = draftReceiptDocumentTransactionContainerRepository.save(
            draftReceiptDocumentTransactionContainer
        );
        return draftReceiptDocumentTransactionContainerMapper.toDto(draftReceiptDocumentTransactionContainer);
    }

    @Override
    public Optional<DraftReceiptDocumentTransactionContainerDTO> partialUpdate(
        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO
    ) {
        log.debug("Request to partially update DraftReceiptDocumentTransactionContainer : {}", draftReceiptDocumentTransactionContainerDTO);

        return draftReceiptDocumentTransactionContainerRepository
            .findById(draftReceiptDocumentTransactionContainerDTO.getId())
            .map(existingDraftReceiptDocumentTransactionContainer -> {
                draftReceiptDocumentTransactionContainerMapper.partialUpdate(
                    existingDraftReceiptDocumentTransactionContainer,
                    draftReceiptDocumentTransactionContainerDTO
                );

                return existingDraftReceiptDocumentTransactionContainer;
            })
            .map(draftReceiptDocumentTransactionContainerRepository::save)
            .map(draftReceiptDocumentTransactionContainerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftReceiptDocumentTransactionContainerDTO> findAll() {
        log.debug("Request to get all DraftReceiptDocumentTransactionContainers");
        return draftReceiptDocumentTransactionContainerRepository
            .findAll()
            .stream()
            .map(draftReceiptDocumentTransactionContainerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftReceiptDocumentTransactionContainerDTO> findOne(Long id) {
        log.debug("Request to get DraftReceiptDocumentTransactionContainer : {}", id);
        return draftReceiptDocumentTransactionContainerRepository.findById(id).map(draftReceiptDocumentTransactionContainerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftReceiptDocumentTransactionContainer : {}", id);
        draftReceiptDocumentTransactionContainerRepository.deleteById(id);
    }
}
