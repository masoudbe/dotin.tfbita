package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.PurchaseFromOtherResources;
import com.dotin.tfbita.repository.PurchaseFromOtherResourcesRepository;
import com.dotin.tfbita.service.PurchaseFromOtherResourcesService;
import com.dotin.tfbita.service.dto.PurchaseFromOtherResourcesDTO;
import com.dotin.tfbita.service.mapper.PurchaseFromOtherResourcesMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.PurchaseFromOtherResources}.
 */
@Service
@Transactional
public class PurchaseFromOtherResourcesServiceImpl implements PurchaseFromOtherResourcesService {

    private static final Logger log = LoggerFactory.getLogger(PurchaseFromOtherResourcesServiceImpl.class);

    private final PurchaseFromOtherResourcesRepository purchaseFromOtherResourcesRepository;

    private final PurchaseFromOtherResourcesMapper purchaseFromOtherResourcesMapper;

    public PurchaseFromOtherResourcesServiceImpl(
        PurchaseFromOtherResourcesRepository purchaseFromOtherResourcesRepository,
        PurchaseFromOtherResourcesMapper purchaseFromOtherResourcesMapper
    ) {
        this.purchaseFromOtherResourcesRepository = purchaseFromOtherResourcesRepository;
        this.purchaseFromOtherResourcesMapper = purchaseFromOtherResourcesMapper;
    }

    @Override
    public PurchaseFromOtherResourcesDTO save(PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO) {
        log.debug("Request to save PurchaseFromOtherResources : {}", purchaseFromOtherResourcesDTO);
        PurchaseFromOtherResources purchaseFromOtherResources = purchaseFromOtherResourcesMapper.toEntity(purchaseFromOtherResourcesDTO);
        purchaseFromOtherResources = purchaseFromOtherResourcesRepository.save(purchaseFromOtherResources);
        return purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);
    }

    @Override
    public PurchaseFromOtherResourcesDTO update(PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO) {
        log.debug("Request to update PurchaseFromOtherResources : {}", purchaseFromOtherResourcesDTO);
        PurchaseFromOtherResources purchaseFromOtherResources = purchaseFromOtherResourcesMapper.toEntity(purchaseFromOtherResourcesDTO);
        purchaseFromOtherResources = purchaseFromOtherResourcesRepository.save(purchaseFromOtherResources);
        return purchaseFromOtherResourcesMapper.toDto(purchaseFromOtherResources);
    }

    @Override
    public Optional<PurchaseFromOtherResourcesDTO> partialUpdate(PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO) {
        log.debug("Request to partially update PurchaseFromOtherResources : {}", purchaseFromOtherResourcesDTO);

        return purchaseFromOtherResourcesRepository
            .findById(purchaseFromOtherResourcesDTO.getId())
            .map(existingPurchaseFromOtherResources -> {
                purchaseFromOtherResourcesMapper.partialUpdate(existingPurchaseFromOtherResources, purchaseFromOtherResourcesDTO);

                return existingPurchaseFromOtherResources;
            })
            .map(purchaseFromOtherResourcesRepository::save)
            .map(purchaseFromOtherResourcesMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseFromOtherResourcesDTO> findAll() {
        log.debug("Request to get all PurchaseFromOtherResources");
        return purchaseFromOtherResourcesRepository
            .findAll()
            .stream()
            .map(purchaseFromOtherResourcesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseFromOtherResourcesDTO> findOne(Long id) {
        log.debug("Request to get PurchaseFromOtherResources : {}", id);
        return purchaseFromOtherResourcesRepository.findById(id).map(purchaseFromOtherResourcesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PurchaseFromOtherResources : {}", id);
        purchaseFromOtherResourcesRepository.deleteById(id);
    }
}
