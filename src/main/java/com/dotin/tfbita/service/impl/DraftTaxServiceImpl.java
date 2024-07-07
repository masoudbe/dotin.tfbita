package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.DraftTax;
import com.dotin.tfbita.repository.DraftTaxRepository;
import com.dotin.tfbita.service.DraftTaxService;
import com.dotin.tfbita.service.dto.DraftTaxDTO;
import com.dotin.tfbita.service.mapper.DraftTaxMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftTax}.
 */
@Service
@Transactional
public class DraftTaxServiceImpl implements DraftTaxService {

    private static final Logger log = LoggerFactory.getLogger(DraftTaxServiceImpl.class);

    private final DraftTaxRepository draftTaxRepository;

    private final DraftTaxMapper draftTaxMapper;

    public DraftTaxServiceImpl(DraftTaxRepository draftTaxRepository, DraftTaxMapper draftTaxMapper) {
        this.draftTaxRepository = draftTaxRepository;
        this.draftTaxMapper = draftTaxMapper;
    }

    @Override
    public DraftTaxDTO save(DraftTaxDTO draftTaxDTO) {
        log.debug("Request to save DraftTax : {}", draftTaxDTO);
        DraftTax draftTax = draftTaxMapper.toEntity(draftTaxDTO);
        draftTax = draftTaxRepository.save(draftTax);
        return draftTaxMapper.toDto(draftTax);
    }

    @Override
    public DraftTaxDTO update(DraftTaxDTO draftTaxDTO) {
        log.debug("Request to update DraftTax : {}", draftTaxDTO);
        DraftTax draftTax = draftTaxMapper.toEntity(draftTaxDTO);
        draftTax = draftTaxRepository.save(draftTax);
        return draftTaxMapper.toDto(draftTax);
    }

    @Override
    public Optional<DraftTaxDTO> partialUpdate(DraftTaxDTO draftTaxDTO) {
        log.debug("Request to partially update DraftTax : {}", draftTaxDTO);

        return draftTaxRepository
            .findById(draftTaxDTO.getId())
            .map(existingDraftTax -> {
                draftTaxMapper.partialUpdate(existingDraftTax, draftTaxDTO);

                return existingDraftTax;
            })
            .map(draftTaxRepository::save)
            .map(draftTaxMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DraftTaxDTO> findAll() {
        log.debug("Request to get all DraftTaxes");
        return draftTaxRepository.findAll().stream().map(draftTaxMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DraftTaxDTO> findOne(Long id) {
        log.debug("Request to get DraftTax : {}", id);
        return draftTaxRepository.findById(id).map(draftTaxMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DraftTax : {}", id);
        draftTaxRepository.deleteById(id);
    }
}
