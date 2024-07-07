package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.AdvisorDefinition;
import com.dotin.tfbita.repository.AdvisorDefinitionRepository;
import com.dotin.tfbita.service.AdvisorDefinitionService;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
import com.dotin.tfbita.service.mapper.AdvisorDefinitionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.AdvisorDefinition}.
 */
@Service
@Transactional
public class AdvisorDefinitionServiceImpl implements AdvisorDefinitionService {

    private static final Logger log = LoggerFactory.getLogger(AdvisorDefinitionServiceImpl.class);

    private final AdvisorDefinitionRepository advisorDefinitionRepository;

    private final AdvisorDefinitionMapper advisorDefinitionMapper;

    public AdvisorDefinitionServiceImpl(
        AdvisorDefinitionRepository advisorDefinitionRepository,
        AdvisorDefinitionMapper advisorDefinitionMapper
    ) {
        this.advisorDefinitionRepository = advisorDefinitionRepository;
        this.advisorDefinitionMapper = advisorDefinitionMapper;
    }

    @Override
    public AdvisorDefinitionDTO save(AdvisorDefinitionDTO advisorDefinitionDTO) {
        log.debug("Request to save AdvisorDefinition : {}", advisorDefinitionDTO);
        AdvisorDefinition advisorDefinition = advisorDefinitionMapper.toEntity(advisorDefinitionDTO);
        advisorDefinition = advisorDefinitionRepository.save(advisorDefinition);
        return advisorDefinitionMapper.toDto(advisorDefinition);
    }

    @Override
    public AdvisorDefinitionDTO update(AdvisorDefinitionDTO advisorDefinitionDTO) {
        log.debug("Request to update AdvisorDefinition : {}", advisorDefinitionDTO);
        AdvisorDefinition advisorDefinition = advisorDefinitionMapper.toEntity(advisorDefinitionDTO);
        advisorDefinition = advisorDefinitionRepository.save(advisorDefinition);
        return advisorDefinitionMapper.toDto(advisorDefinition);
    }

    @Override
    public Optional<AdvisorDefinitionDTO> partialUpdate(AdvisorDefinitionDTO advisorDefinitionDTO) {
        log.debug("Request to partially update AdvisorDefinition : {}", advisorDefinitionDTO);

        return advisorDefinitionRepository
            .findById(advisorDefinitionDTO.getId())
            .map(existingAdvisorDefinition -> {
                advisorDefinitionMapper.partialUpdate(existingAdvisorDefinition, advisorDefinitionDTO);

                return existingAdvisorDefinition;
            })
            .map(advisorDefinitionRepository::save)
            .map(advisorDefinitionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdvisorDefinitionDTO> findAll() {
        log.debug("Request to get all AdvisorDefinitions");
        return advisorDefinitionRepository
            .findAll()
            .stream()
            .map(advisorDefinitionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AdvisorDefinitionDTO> findOne(Long id) {
        log.debug("Request to get AdvisorDefinition : {}", id);
        return advisorDefinitionRepository.findById(id).map(advisorDefinitionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdvisorDefinition : {}", id);
        advisorDefinitionRepository.deleteById(id);
    }
}
