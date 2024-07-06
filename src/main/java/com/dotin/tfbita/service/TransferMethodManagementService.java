package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.TransferMethodManagement;
import com.dotin.tfbita.repository.TransferMethodManagementRepository;
import com.dotin.tfbita.service.dto.TransferMethodManagementDTO;
import com.dotin.tfbita.service.mapper.TransferMethodManagementMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.TransferMethodManagement}.
 */
@Service
@Transactional
public class TransferMethodManagementService {

    private final Logger log = LoggerFactory.getLogger(TransferMethodManagementService.class);

    private final TransferMethodManagementRepository transferMethodManagementRepository;

    private final TransferMethodManagementMapper transferMethodManagementMapper;

    public TransferMethodManagementService(
        TransferMethodManagementRepository transferMethodManagementRepository,
        TransferMethodManagementMapper transferMethodManagementMapper
    ) {
        this.transferMethodManagementRepository = transferMethodManagementRepository;
        this.transferMethodManagementMapper = transferMethodManagementMapper;
    }

    /**
     * Save a transferMethodManagement.
     *
     * @param transferMethodManagementDTO the entity to save.
     * @return the persisted entity.
     */
    public TransferMethodManagementDTO save(TransferMethodManagementDTO transferMethodManagementDTO) {
        log.debug("Request to save TransferMethodManagement : {}", transferMethodManagementDTO);
        TransferMethodManagement transferMethodManagement = transferMethodManagementMapper.toEntity(transferMethodManagementDTO);
        transferMethodManagement = transferMethodManagementRepository.save(transferMethodManagement);
        return transferMethodManagementMapper.toDto(transferMethodManagement);
    }

    /**
     * Update a transferMethodManagement.
     *
     * @param transferMethodManagementDTO the entity to save.
     * @return the persisted entity.
     */
    public TransferMethodManagementDTO update(TransferMethodManagementDTO transferMethodManagementDTO) {
        log.debug("Request to update TransferMethodManagement : {}", transferMethodManagementDTO);
        TransferMethodManagement transferMethodManagement = transferMethodManagementMapper.toEntity(transferMethodManagementDTO);
        transferMethodManagement = transferMethodManagementRepository.save(transferMethodManagement);
        return transferMethodManagementMapper.toDto(transferMethodManagement);
    }

    /**
     * Partially update a transferMethodManagement.
     *
     * @param transferMethodManagementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TransferMethodManagementDTO> partialUpdate(TransferMethodManagementDTO transferMethodManagementDTO) {
        log.debug("Request to partially update TransferMethodManagement : {}", transferMethodManagementDTO);

        return transferMethodManagementRepository
            .findById(transferMethodManagementDTO.getId())
            .map(existingTransferMethodManagement -> {
                transferMethodManagementMapper.partialUpdate(existingTransferMethodManagement, transferMethodManagementDTO);

                return existingTransferMethodManagement;
            })
            .map(transferMethodManagementRepository::save)
            .map(transferMethodManagementMapper::toDto);
    }

    /**
     * Get all the transferMethodManagements.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TransferMethodManagementDTO> findAll() {
        log.debug("Request to get all TransferMethodManagements");
        return transferMethodManagementRepository
            .findAll()
            .stream()
            .map(transferMethodManagementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one transferMethodManagement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TransferMethodManagementDTO> findOne(Long id) {
        log.debug("Request to get TransferMethodManagement : {}", id);
        return transferMethodManagementRepository.findById(id).map(transferMethodManagementMapper::toDto);
    }

    /**
     * Delete the transferMethodManagement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TransferMethodManagement : {}", id);
        transferMethodManagementRepository.deleteById(id);
    }
}
