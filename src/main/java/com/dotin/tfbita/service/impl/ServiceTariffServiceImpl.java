package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.ServiceTariff;
import com.dotin.tfbita.repository.ServiceTariffRepository;
import com.dotin.tfbita.service.ServiceTariffService;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import com.dotin.tfbita.service.mapper.ServiceTariffMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.ServiceTariff}.
 */
@Service
@Transactional
public class ServiceTariffServiceImpl implements ServiceTariffService {

    private static final Logger log = LoggerFactory.getLogger(ServiceTariffServiceImpl.class);

    private final ServiceTariffRepository serviceTariffRepository;

    private final ServiceTariffMapper serviceTariffMapper;

    public ServiceTariffServiceImpl(ServiceTariffRepository serviceTariffRepository, ServiceTariffMapper serviceTariffMapper) {
        this.serviceTariffRepository = serviceTariffRepository;
        this.serviceTariffMapper = serviceTariffMapper;
    }

    @Override
    public ServiceTariffDTO save(ServiceTariffDTO serviceTariffDTO) {
        log.debug("Request to save ServiceTariff : {}", serviceTariffDTO);
        ServiceTariff serviceTariff = serviceTariffMapper.toEntity(serviceTariffDTO);
        serviceTariff = serviceTariffRepository.save(serviceTariff);
        return serviceTariffMapper.toDto(serviceTariff);
    }

    @Override
    public ServiceTariffDTO update(ServiceTariffDTO serviceTariffDTO) {
        log.debug("Request to update ServiceTariff : {}", serviceTariffDTO);
        ServiceTariff serviceTariff = serviceTariffMapper.toEntity(serviceTariffDTO);
        serviceTariff = serviceTariffRepository.save(serviceTariff);
        return serviceTariffMapper.toDto(serviceTariff);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<ServiceTariffDTO> findAll() {
        log.debug("Request to get all ServiceTariffs");
        return serviceTariffRepository.findAll().stream().map(serviceTariffMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceTariffDTO> findOne(Long id) {
        log.debug("Request to get ServiceTariff : {}", id);
        return serviceTariffRepository.findById(id).map(serviceTariffMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceTariff : {}", id);
        serviceTariffRepository.deleteById(id);
    }
}
