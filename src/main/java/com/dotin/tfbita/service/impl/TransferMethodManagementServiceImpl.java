package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.TransferMethodManagement;
import com.dotin.tfbita.repository.TransferMethodManagementRepository;
import com.dotin.tfbita.service.TransferMethodManagementService;
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
public class TransferMethodManagementServiceImpl implements TransferMethodManagementService {

    private static final Logger log = LoggerFactory.getLogger(TransferMethodManagementServiceImpl.class);

    private final TransferMethodManagementRepository transferMethodManagementRepository;

    private final TransferMethodManagementMapper transferMethodManagementMapper;

    public TransferMethodManagementServiceImpl(
        TransferMethodManagementRepository transferMethodManagementRepository,
        TransferMethodManagementMapper transferMethodManagementMapper
    ) {
        this.transferMethodManagementRepository = transferMethodManagementRepository;
        this.transferMethodManagementMapper = transferMethodManagementMapper;
    }

    @Override
    public TransferMethodManagementDTO save(TransferMethodManagementDTO transferMethodManagementDTO) {
        log.debug("Request to save TransferMethodManagement : {}", transferMethodManagementDTO);
        TransferMethodManagement transferMethodManagement = transferMethodManagementMapper.toEntity(transferMethodManagementDTO);
        transferMethodManagement = transferMethodManagementRepository.save(transferMethodManagement);
        return transferMethodManagementMapper.toDto(transferMethodManagement);
    }

    @Override
    public TransferMethodManagementDTO update(TransferMethodManagementDTO transferMethodManagementDTO) {
        log.debug("Request to update TransferMethodManagement : {}", transferMethodManagementDTO);
        TransferMethodManagement transferMethodManagement = transferMethodManagementMapper.toEntity(transferMethodManagementDTO);
        transferMethodManagement = transferMethodManagementRepository.save(transferMethodManagement);
        return transferMethodManagementMapper.toDto(transferMethodManagement);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<TransferMethodManagementDTO> findAll() {
        log.debug("Request to get all TransferMethodManagements");
        return transferMethodManagementRepository
            .findAll()
            .stream()
            .map(transferMethodManagementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransferMethodManagementDTO> findOne(Long id) {
        log.debug("Request to get TransferMethodManagement : {}", id);
        return transferMethodManagementRepository.findById(id).map(transferMethodManagementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransferMethodManagement : {}", id);
        transferMethodManagementRepository.deleteById(id);
    }
}
