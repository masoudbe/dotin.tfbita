package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer;
import com.dotin.tfbita.repository.DraftReceiptDocumentTransactionContainerRepository;
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
public class DraftReceiptDocumentTransactionContainerService {

    private final Logger log = LoggerFactory.getLogger(DraftReceiptDocumentTransactionContainerService.class);

    private final DraftReceiptDocumentTransactionContainerRepository draftReceiptDocumentTransactionContainerRepository;

    private final DraftReceiptDocumentTransactionContainerMapper draftReceiptDocumentTransactionContainerMapper;

    public DraftReceiptDocumentTransactionContainerService(
        DraftReceiptDocumentTransactionContainerRepository draftReceiptDocumentTransactionContainerRepository,
        DraftReceiptDocumentTransactionContainerMapper draftReceiptDocumentTransactionContainerMapper
    ) {
        this.draftReceiptDocumentTransactionContainerRepository = draftReceiptDocumentTransactionContainerRepository;
        this.draftReceiptDocumentTransactionContainerMapper = draftReceiptDocumentTransactionContainerMapper;
    }

    /**
     * Save a draftReceiptDocumentTransactionContainer.
     *
     * @param draftReceiptDocumentTransactionContainerDTO the entity to save.
     * @return the persisted entity.
     */
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

    /**
     * Update a draftReceiptDocumentTransactionContainer.
     *
     * @param draftReceiptDocumentTransactionContainerDTO the entity to save.
     * @return the persisted entity.
     */
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

    /**
     * Partially update a draftReceiptDocumentTransactionContainer.
     *
     * @param draftReceiptDocumentTransactionContainerDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the draftReceiptDocumentTransactionContainers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftReceiptDocumentTransactionContainerDTO> findAll() {
        log.debug("Request to get all DraftReceiptDocumentTransactionContainers");
        return draftReceiptDocumentTransactionContainerRepository
            .findAll()
            .stream()
            .map(draftReceiptDocumentTransactionContainerMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one draftReceiptDocumentTransactionContainer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftReceiptDocumentTransactionContainerDTO> findOne(Long id) {
        log.debug("Request to get DraftReceiptDocumentTransactionContainer : {}", id);
        return draftReceiptDocumentTransactionContainerRepository.findById(id).map(draftReceiptDocumentTransactionContainerMapper::toDto);
    }

    /**
     * Delete the draftReceiptDocumentTransactionContainer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftReceiptDocumentTransactionContainer : {}", id);
        draftReceiptDocumentTransactionContainerRepository.deleteById(id);
    }
}
