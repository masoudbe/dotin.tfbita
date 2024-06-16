package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftReceipt;
import com.dotin.tfbita.repository.DraftReceiptRepository;
import com.dotin.tfbita.service.DraftReceiptService;
import com.dotin.tfbita.service.dto.DraftReceiptDTO;
import com.dotin.tfbita.service.mapper.DraftReceiptMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftReceipt}.
 */
@Service
@Transactional
public class DraftReceiptServiceImpl implements DraftReceiptService {

    private final Logger log = LoggerFactory.getLogger(DraftReceiptServiceImpl.class);

    private final DraftReceiptRepository draftReceiptRepository;

    private final DraftReceiptMapper draftReceiptMapper;

    public DraftReceiptServiceImpl(DraftReceiptRepository draftReceiptRepository, DraftReceiptMapper draftReceiptMapper) {
        this.draftReceiptRepository = draftReceiptRepository;
        this.draftReceiptMapper = draftReceiptMapper;
    }

    @Override
    public DraftReceiptDTO save(DraftReceiptDTO draftReceiptDTO) {
        log.debug("Request to save DraftReceipt : {}", draftReceiptDTO);
        DraftReceipt draftReceipt = draftReceiptMapper.toEntity(draftReceiptDTO);
        draftReceipt = draftReceiptRepository.save(draftReceipt);
        return draftReceiptMapper.toDto(draftReceipt);
    }

    @Override
    public DraftReceiptDTO update(DraftReceiptDTO draftReceiptDTO) {
        log.debug("Request to update DraftReceipt : {}", draftReceiptDTO);
        DraftReceipt draftReceipt = draftReceiptMapper.toEntity(draftReceiptDTO);
        draftReceipt = draftReceiptRepository.save(draftReceipt);
        return draftReceiptMapper.toDto(draftReceipt);
    }

    @Override
    public Optional<DraftReceiptDTO> partialUpdate(DraftReceiptDTO draftReceiptDTO) {
        log.debug("Request to partially update DraftReceipt : {}", draftReceiptDTO);

        return draftReceiptRepository
            .findById(draftReceiptDTO.getId())
            .map(existingDraftReceipt -> {
                draftReceiptMapper.partialUpdate(existingDraftReceipt, draftReceiptDTO);

                return existingDraftReceipt;
            })
            .map(draftReceiptRepository::save)
            .map(draftReceiptMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftReceiptDTO> findAll() {
        log.debug("Request to get all DraftReceipts");
        return draftReceiptRepository.findAll().stream().map(draftReceiptMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftReceiptDTO> findOne(Long id) {
        log.debug("Request to get DraftReceipt : {}", id);
        return draftReceiptRepository.findById(id).map(draftReceiptMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftReceipt : {}", id);
        draftReceiptRepository.deleteById(id);
    }
}
