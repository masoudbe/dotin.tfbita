package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.AdvisorDefinitionDepositDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.AdvisorDefinitionDeposit}.
 */
public interface AdvisorDefinitionDepositService {
    /**
     * Save a advisorDefinitionDeposit.
     *
     * @param advisorDefinitionDepositDTO the entity to save.
     * @return the persisted entity.
     */
    AdvisorDefinitionDepositDTO save(AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO);

    /**
     * Updates a advisorDefinitionDeposit.
     *
     * @param advisorDefinitionDepositDTO the entity to update.
     * @return the persisted entity.
     */
    AdvisorDefinitionDepositDTO update(AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO);

    /**
     * Partially updates a advisorDefinitionDeposit.
     *
     * @param advisorDefinitionDepositDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AdvisorDefinitionDepositDTO> partialUpdate(AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO);

    /**
     * Get all the advisorDefinitionDeposits.
     *
     * @return the list of entities.
     */
    List<AdvisorDefinitionDepositDTO> findAll();

    /**
     * Get the "id" advisorDefinitionDeposit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdvisorDefinitionDepositDTO> findOne(Long id);

    /**
     * Delete the "id" advisorDefinitionDeposit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
