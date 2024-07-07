package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DocumentTransaction;
import com.dotin.tfbita.repository.DocumentTransactionRepository;
import com.dotin.tfbita.service.DocumentTransactionService;
import com.dotin.tfbita.service.dto.DocumentTransactionDTO;
import com.dotin.tfbita.service.mapper.DocumentTransactionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DocumentTransaction}.
 */
@Service
@Transactional
public class DocumentTransactionServiceImpl implements DocumentTransactionService {

    private static final Logger log = LoggerFactory.getLogger(DocumentTransactionServiceImpl.class);

    private final DocumentTransactionRepository documentTransactionRepository;

    private final DocumentTransactionMapper documentTransactionMapper;

    public DocumentTransactionServiceImpl(
        DocumentTransactionRepository documentTransactionRepository,
        DocumentTransactionMapper documentTransactionMapper
    ) {
        this.documentTransactionRepository = documentTransactionRepository;
        this.documentTransactionMapper = documentTransactionMapper;
    }

    @Override
    public DocumentTransactionDTO save(DocumentTransactionDTO documentTransactionDTO) {
        log.debug("Request to save DocumentTransaction : {}", documentTransactionDTO);
        DocumentTransaction documentTransaction = documentTransactionMapper.toEntity(documentTransactionDTO);
        documentTransaction = documentTransactionRepository.save(documentTransaction);
        return documentTransactionMapper.toDto(documentTransaction);
    }

    @Override
    public DocumentTransactionDTO update(DocumentTransactionDTO documentTransactionDTO) {
        log.debug("Request to update DocumentTransaction : {}", documentTransactionDTO);
        DocumentTransaction documentTransaction = documentTransactionMapper.toEntity(documentTransactionDTO);
        documentTransaction = documentTransactionRepository.save(documentTransaction);
        return documentTransactionMapper.toDto(documentTransaction);
    }

    @Override
    public Optional<DocumentTransactionDTO> partialUpdate(DocumentTransactionDTO documentTransactionDTO) {
        log.debug("Request to partially update DocumentTransaction : {}", documentTransactionDTO);

        return documentTransactionRepository
            .findById(documentTransactionDTO.getId())
            .map(existingDocumentTransaction -> {
                documentTransactionMapper.partialUpdate(existingDocumentTransaction, documentTransactionDTO);

                return existingDocumentTransaction;
            })
            .map(documentTransactionRepository::save)
            .map(documentTransactionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentTransactionDTO> findAll() {
        log.debug("Request to get all DocumentTransactions");
        return documentTransactionRepository
            .findAll()
            .stream()
            .map(documentTransactionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentTransactionDTO> findOne(Long id) {
        log.debug("Request to get DocumentTransaction : {}", id);
        return documentTransactionRepository.findById(id).map(documentTransactionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DocumentTransaction : {}", id);
        documentTransactionRepository.deleteById(id);
    }
}
