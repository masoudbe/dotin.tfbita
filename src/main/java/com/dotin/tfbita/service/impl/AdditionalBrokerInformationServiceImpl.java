package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.AdditionalBrokerInformation;
import com.dotin.tfbita.repository.AdditionalBrokerInformationRepository;
import com.dotin.tfbita.service.AdditionalBrokerInformationService;
import com.dotin.tfbita.service.dto.AdditionalBrokerInformationDTO;
import com.dotin.tfbita.service.mapper.AdditionalBrokerInformationMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.AdditionalBrokerInformation}.
 */
@Service
@Transactional
public class AdditionalBrokerInformationServiceImpl implements AdditionalBrokerInformationService {

    private static final Logger log = LoggerFactory.getLogger(AdditionalBrokerInformationServiceImpl.class);

    private final AdditionalBrokerInformationRepository additionalBrokerInformationRepository;

    private final AdditionalBrokerInformationMapper additionalBrokerInformationMapper;

    public AdditionalBrokerInformationServiceImpl(
        AdditionalBrokerInformationRepository additionalBrokerInformationRepository,
        AdditionalBrokerInformationMapper additionalBrokerInformationMapper
    ) {
        this.additionalBrokerInformationRepository = additionalBrokerInformationRepository;
        this.additionalBrokerInformationMapper = additionalBrokerInformationMapper;
    }

    @Override
    public AdditionalBrokerInformationDTO save(AdditionalBrokerInformationDTO additionalBrokerInformationDTO) {
        log.debug("Request to save AdditionalBrokerInformation : {}", additionalBrokerInformationDTO);
        AdditionalBrokerInformation additionalBrokerInformation = additionalBrokerInformationMapper.toEntity(
            additionalBrokerInformationDTO
        );
        additionalBrokerInformation = additionalBrokerInformationRepository.save(additionalBrokerInformation);
        return additionalBrokerInformationMapper.toDto(additionalBrokerInformation);
    }

    @Override
    public AdditionalBrokerInformationDTO update(AdditionalBrokerInformationDTO additionalBrokerInformationDTO) {
        log.debug("Request to update AdditionalBrokerInformation : {}", additionalBrokerInformationDTO);
        AdditionalBrokerInformation additionalBrokerInformation = additionalBrokerInformationMapper.toEntity(
            additionalBrokerInformationDTO
        );
        additionalBrokerInformation = additionalBrokerInformationRepository.save(additionalBrokerInformation);
        return additionalBrokerInformationMapper.toDto(additionalBrokerInformation);
    }

    @Override
    public Optional<AdditionalBrokerInformationDTO> partialUpdate(AdditionalBrokerInformationDTO additionalBrokerInformationDTO) {
        log.debug("Request to partially update AdditionalBrokerInformation : {}", additionalBrokerInformationDTO);

        return additionalBrokerInformationRepository
            .findById(additionalBrokerInformationDTO.getId())
            .map(existingAdditionalBrokerInformation -> {
                additionalBrokerInformationMapper.partialUpdate(existingAdditionalBrokerInformation, additionalBrokerInformationDTO);

                return existingAdditionalBrokerInformation;
            })
            .map(additionalBrokerInformationRepository::save)
            .map(additionalBrokerInformationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdditionalBrokerInformationDTO> findAll() {
        log.debug("Request to get all AdditionalBrokerInformations");
        return additionalBrokerInformationRepository
            .findAll()
            .stream()
            .map(additionalBrokerInformationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get all the additionalBrokerInformations where AdvisorDefinition is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdditionalBrokerInformationDTO> findAllWhereAdvisorDefinitionIsNull() {
        log.debug("Request to get all additionalBrokerInformations where AdvisorDefinition is null");
        return StreamSupport.stream(additionalBrokerInformationRepository.findAll().spliterator(), false)
            .filter(additionalBrokerInformation -> additionalBrokerInformation.getAdvisorDefinition() == null)
            .map(additionalBrokerInformationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AdditionalBrokerInformationDTO> findOne(Long id) {
        log.debug("Request to get AdditionalBrokerInformation : {}", id);
        return additionalBrokerInformationRepository.findById(id).map(additionalBrokerInformationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdditionalBrokerInformation : {}", id);
        additionalBrokerInformationRepository.deleteById(id);
    }
}
