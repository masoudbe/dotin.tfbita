package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.AdvisorDefinitionDeposit;
import com.dotin.tfbita.repository.AdvisorDefinitionDepositRepository;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDepositDTO;
import com.dotin.tfbita.service.mapper.AdvisorDefinitionDepositMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.AdvisorDefinitionDeposit}.
 */
@Service
@Transactional
public class AdvisorDefinitionDepositService {

    private final Logger log = LoggerFactory.getLogger(AdvisorDefinitionDepositService.class);

    private final AdvisorDefinitionDepositRepository advisorDefinitionDepositRepository;

    private final AdvisorDefinitionDepositMapper advisorDefinitionDepositMapper;

    public AdvisorDefinitionDepositService(
        AdvisorDefinitionDepositRepository advisorDefinitionDepositRepository,
        AdvisorDefinitionDepositMapper advisorDefinitionDepositMapper
    ) {
        this.advisorDefinitionDepositRepository = advisorDefinitionDepositRepository;
        this.advisorDefinitionDepositMapper = advisorDefinitionDepositMapper;
    }

    /**
     * Save a advisorDefinitionDeposit.
     *
     * @param advisorDefinitionDepositDTO the entity to save.
     * @return the persisted entity.
     */
    public AdvisorDefinitionDepositDTO save(AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO) {
        log.debug("Request to save AdvisorDefinitionDeposit : {}", advisorDefinitionDepositDTO);
        AdvisorDefinitionDeposit advisorDefinitionDeposit = advisorDefinitionDepositMapper.toEntity(advisorDefinitionDepositDTO);
        advisorDefinitionDeposit = advisorDefinitionDepositRepository.save(advisorDefinitionDeposit);
        return advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);
    }

    /**
     * Update a advisorDefinitionDeposit.
     *
     * @param advisorDefinitionDepositDTO the entity to save.
     * @return the persisted entity.
     */
    public AdvisorDefinitionDepositDTO update(AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO) {
        log.debug("Request to update AdvisorDefinitionDeposit : {}", advisorDefinitionDepositDTO);
        AdvisorDefinitionDeposit advisorDefinitionDeposit = advisorDefinitionDepositMapper.toEntity(advisorDefinitionDepositDTO);
        advisorDefinitionDeposit = advisorDefinitionDepositRepository.save(advisorDefinitionDeposit);
        return advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);
    }

    /**
     * Partially update a advisorDefinitionDeposit.
     *
     * @param advisorDefinitionDepositDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AdvisorDefinitionDepositDTO> partialUpdate(AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO) {
        log.debug("Request to partially update AdvisorDefinitionDeposit : {}", advisorDefinitionDepositDTO);

        return advisorDefinitionDepositRepository
            .findById(advisorDefinitionDepositDTO.getId())
            .map(existingAdvisorDefinitionDeposit -> {
                advisorDefinitionDepositMapper.partialUpdate(existingAdvisorDefinitionDeposit, advisorDefinitionDepositDTO);

                return existingAdvisorDefinitionDeposit;
            })
            .map(advisorDefinitionDepositRepository::save)
            .map(advisorDefinitionDepositMapper::toDto);
    }

    /**
     * Get all the advisorDefinitionDeposits.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AdvisorDefinitionDepositDTO> findAll() {
        log.debug("Request to get all AdvisorDefinitionDeposits");
        return advisorDefinitionDepositRepository
            .findAll()
            .stream()
            .map(advisorDefinitionDepositMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one advisorDefinitionDeposit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdvisorDefinitionDepositDTO> findOne(Long id) {
        log.debug("Request to get AdvisorDefinitionDeposit : {}", id);
        return advisorDefinitionDepositRepository.findById(id).map(advisorDefinitionDepositMapper::toDto);
    }

    /**
     * Delete the advisorDefinitionDeposit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AdvisorDefinitionDeposit : {}", id);
        advisorDefinitionDepositRepository.deleteById(id);
    }
}
