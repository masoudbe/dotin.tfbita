package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.ServiceTariff;
import com.dotin.tfbita.repository.ServiceTariffRepository;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import com.dotin.tfbita.service.mapper.ServiceTariffMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.ServiceTariff}.
 */
@Service
@Transactional
public class ServiceTariffService {

    private final Logger log = LoggerFactory.getLogger(ServiceTariffService.class);

    private final ServiceTariffRepository serviceTariffRepository;

    private final ServiceTariffMapper serviceTariffMapper;

    public ServiceTariffService(ServiceTariffRepository serviceTariffRepository, ServiceTariffMapper serviceTariffMapper) {
        this.serviceTariffRepository = serviceTariffRepository;
        this.serviceTariffMapper = serviceTariffMapper;
    }

    /**
     * Save a serviceTariff.
     *
     * @param serviceTariffDTO the entity to save.
     * @return the persisted entity.
     */
    public ServiceTariffDTO save(ServiceTariffDTO serviceTariffDTO) {
        log.debug("Request to save ServiceTariff : {}", serviceTariffDTO);
        ServiceTariff serviceTariff = serviceTariffMapper.toEntity(serviceTariffDTO);
        serviceTariff = serviceTariffRepository.save(serviceTariff);
        return serviceTariffMapper.toDto(serviceTariff);
    }

    /**
     * Update a serviceTariff.
     *
     * @param serviceTariffDTO the entity to save.
     * @return the persisted entity.
     */
    public ServiceTariffDTO update(ServiceTariffDTO serviceTariffDTO) {
        log.debug("Request to update ServiceTariff : {}", serviceTariffDTO);
        ServiceTariff serviceTariff = serviceTariffMapper.toEntity(serviceTariffDTO);
        serviceTariff = serviceTariffRepository.save(serviceTariff);
        return serviceTariffMapper.toDto(serviceTariff);
    }

    /**
     * Partially update a serviceTariff.
     *
     * @param serviceTariffDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ServiceTariffDTO> partialUpdate(ServiceTariffDTO serviceTariffDTO) {
        log.debug("Request to partially update ServiceTariff : {}", serviceTariffDTO);

        return serviceTariffRepository
            .findById(serviceTariffDTO.getId())
            .map(existingServiceTariff -> {
                serviceTariffMapper.partialUpdate(existingServiceTariff, serviceTariffDTO);

                return existingServiceTariff;
            })
            .map(serviceTariffRepository::save)
            .map(serviceTariffMapper::toDto);
    }

    /**
     * Get all the serviceTariffs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ServiceTariffDTO> findAll() {
        log.debug("Request to get all ServiceTariffs");
        return serviceTariffRepository.findAll().stream().map(serviceTariffMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the serviceTariffs with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ServiceTariffDTO> findAllWithEagerRelationships(Pageable pageable) {
        return serviceTariffRepository.findAllWithEagerRelationships(pageable).map(serviceTariffMapper::toDto);
    }

    /**
     * Get one serviceTariff by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ServiceTariffDTO> findOne(Long id) {
        log.debug("Request to get ServiceTariff : {}", id);
        return serviceTariffRepository.findOneWithEagerRelationships(id).map(serviceTariffMapper::toDto);
    }

    /**
     * Delete the serviceTariff by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ServiceTariff : {}", id);
        serviceTariffRepository.deleteById(id);
    }
}
