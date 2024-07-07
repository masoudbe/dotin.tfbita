package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.AdvisorDefinitionDeposit;
import com.dotin.tfbita.repository.AdvisorDefinitionDepositRepository;
import com.dotin.tfbita.service.AdvisorDefinitionDepositService;
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
public class AdvisorDefinitionDepositServiceImpl implements AdvisorDefinitionDepositService {

    private static final Logger log = LoggerFactory.getLogger(AdvisorDefinitionDepositServiceImpl.class);

    private final AdvisorDefinitionDepositRepository advisorDefinitionDepositRepository;

    private final AdvisorDefinitionDepositMapper advisorDefinitionDepositMapper;

    public AdvisorDefinitionDepositServiceImpl(
        AdvisorDefinitionDepositRepository advisorDefinitionDepositRepository,
        AdvisorDefinitionDepositMapper advisorDefinitionDepositMapper
    ) {
        this.advisorDefinitionDepositRepository = advisorDefinitionDepositRepository;
        this.advisorDefinitionDepositMapper = advisorDefinitionDepositMapper;
    }

    @Override
    public AdvisorDefinitionDepositDTO save(AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO) {
        log.debug("Request to save AdvisorDefinitionDeposit : {}", advisorDefinitionDepositDTO);
        AdvisorDefinitionDeposit advisorDefinitionDeposit = advisorDefinitionDepositMapper.toEntity(advisorDefinitionDepositDTO);
        advisorDefinitionDeposit = advisorDefinitionDepositRepository.save(advisorDefinitionDeposit);
        return advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);
    }

    @Override
    public AdvisorDefinitionDepositDTO update(AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO) {
        log.debug("Request to update AdvisorDefinitionDeposit : {}", advisorDefinitionDepositDTO);
        AdvisorDefinitionDeposit advisorDefinitionDeposit = advisorDefinitionDepositMapper.toEntity(advisorDefinitionDepositDTO);
        advisorDefinitionDeposit = advisorDefinitionDepositRepository.save(advisorDefinitionDeposit);
        return advisorDefinitionDepositMapper.toDto(advisorDefinitionDeposit);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<AdvisorDefinitionDepositDTO> findAll() {
        log.debug("Request to get all AdvisorDefinitionDeposits");
        return advisorDefinitionDepositRepository
            .findAll()
            .stream()
            .map(advisorDefinitionDepositMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AdvisorDefinitionDepositDTO> findOne(Long id) {
        log.debug("Request to get AdvisorDefinitionDeposit : {}", id);
        return advisorDefinitionDepositRepository.findById(id).map(advisorDefinitionDepositMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdvisorDefinitionDeposit : {}", id);
        advisorDefinitionDepositRepository.deleteById(id);
    }
}
