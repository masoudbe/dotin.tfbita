package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.TransportationType;
import com.dotin.tfbita.repository.TransportationTypeRepository;
import com.dotin.tfbita.service.TransportationTypeService;
import com.dotin.tfbita.service.dto.TransportationTypeDTO;
import com.dotin.tfbita.service.mapper.TransportationTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.TransportationType}.
 */
@Service
@Transactional
public class TransportationTypeServiceImpl implements TransportationTypeService {

    private static final Logger log = LoggerFactory.getLogger(TransportationTypeServiceImpl.class);

    private final TransportationTypeRepository transportationTypeRepository;

    private final TransportationTypeMapper transportationTypeMapper;

    public TransportationTypeServiceImpl(
        TransportationTypeRepository transportationTypeRepository,
        TransportationTypeMapper transportationTypeMapper
    ) {
        this.transportationTypeRepository = transportationTypeRepository;
        this.transportationTypeMapper = transportationTypeMapper;
    }

    @Override
    public TransportationTypeDTO save(TransportationTypeDTO transportationTypeDTO) {
        log.debug("Request to save TransportationType : {}", transportationTypeDTO);
        TransportationType transportationType = transportationTypeMapper.toEntity(transportationTypeDTO);
        transportationType = transportationTypeRepository.save(transportationType);
        return transportationTypeMapper.toDto(transportationType);
    }

    @Override
    public TransportationTypeDTO update(TransportationTypeDTO transportationTypeDTO) {
        log.debug("Request to update TransportationType : {}", transportationTypeDTO);
        TransportationType transportationType = transportationTypeMapper.toEntity(transportationTypeDTO);
        transportationType = transportationTypeRepository.save(transportationType);
        return transportationTypeMapper.toDto(transportationType);
    }

    @Override
    public Optional<TransportationTypeDTO> partialUpdate(TransportationTypeDTO transportationTypeDTO) {
        log.debug("Request to partially update TransportationType : {}", transportationTypeDTO);

        return transportationTypeRepository
            .findById(transportationTypeDTO.getId())
            .map(existingTransportationType -> {
                transportationTypeMapper.partialUpdate(existingTransportationType, transportationTypeDTO);

                return existingTransportationType;
            })
            .map(transportationTypeRepository::save)
            .map(transportationTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransportationTypeDTO> findAll() {
        log.debug("Request to get all TransportationTypes");
        return transportationTypeRepository
            .findAll()
            .stream()
            .map(transportationTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransportationTypeDTO> findOne(Long id) {
        log.debug("Request to get TransportationType : {}", id);
        return transportationTypeRepository.findById(id).map(transportationTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransportationType : {}", id);
        transportationTypeRepository.deleteById(id);
    }
}
