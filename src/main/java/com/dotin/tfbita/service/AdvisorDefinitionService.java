package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.AdvisorDefinition;
import com.dotin.tfbita.repository.AdvisorDefinitionRepository;
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
public class AdvisorDefinitionService {

    private final Logger log = LoggerFactory.getLogger(AdvisorDefinitionService.class);

    private final AdvisorDefinitionRepository advisorDefinitionRepository;

    private final AdvisorDefinitionMapper advisorDefinitionMapper;

    public AdvisorDefinitionService(
        AdvisorDefinitionRepository advisorDefinitionRepository,
        AdvisorDefinitionMapper advisorDefinitionMapper
    ) {
        this.advisorDefinitionRepository = advisorDefinitionRepository;
        this.advisorDefinitionMapper = advisorDefinitionMapper;
    }

    /**
     * Save a advisorDefinition.
     *
     * @param advisorDefinitionDTO the entity to save.
     * @return the persisted entity.
     */
    public AdvisorDefinitionDTO save(AdvisorDefinitionDTO advisorDefinitionDTO) {
        log.debug("Request to save AdvisorDefinition : {}", advisorDefinitionDTO);
        AdvisorDefinition advisorDefinition = advisorDefinitionMapper.toEntity(advisorDefinitionDTO);
        advisorDefinition = advisorDefinitionRepository.save(advisorDefinition);
        return advisorDefinitionMapper.toDto(advisorDefinition);
    }

    /**
     * Update a advisorDefinition.
     *
     * @param advisorDefinitionDTO the entity to save.
     * @return the persisted entity.
     */
    public AdvisorDefinitionDTO update(AdvisorDefinitionDTO advisorDefinitionDTO) {
        log.debug("Request to update AdvisorDefinition : {}", advisorDefinitionDTO);
        AdvisorDefinition advisorDefinition = advisorDefinitionMapper.toEntity(advisorDefinitionDTO);
        advisorDefinition = advisorDefinitionRepository.save(advisorDefinition);
        return advisorDefinitionMapper.toDto(advisorDefinition);
    }

    /**
     * Partially update a advisorDefinition.
     *
     * @param advisorDefinitionDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the advisorDefinitions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdvisorDefinitionDTO> findAll() {
        log.debug("Request to get all AdvisorDefinitions");
        return advisorDefinitionRepository
            .findAll()
            .stream()
            .map(advisorDefinitionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one advisorDefinition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdvisorDefinitionDTO> findOne(Long id) {
        log.debug("Request to get AdvisorDefinition : {}", id);
        return advisorDefinitionRepository.findById(id).map(advisorDefinitionMapper::toDto);
    }

    /**
     * Delete the advisorDefinition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdvisorDefinition : {}", id);
        advisorDefinitionRepository.deleteById(id);
    }
}
