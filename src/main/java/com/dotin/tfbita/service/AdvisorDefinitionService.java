package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.AdvisorDefinition}.
 */
public interface AdvisorDefinitionService {
    /**
     * Save a advisorDefinition.
     *
     * @param advisorDefinitionDTO the entity to save.
     * @return the persisted entity.
     */
    AdvisorDefinitionDTO save(AdvisorDefinitionDTO advisorDefinitionDTO);

    /**
     * Updates a advisorDefinition.
     *
     * @param advisorDefinitionDTO the entity to update.
     * @return the persisted entity.
     */
    AdvisorDefinitionDTO update(AdvisorDefinitionDTO advisorDefinitionDTO);

    /**
     * Partially updates a advisorDefinition.
     *
     * @param advisorDefinitionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AdvisorDefinitionDTO> partialUpdate(AdvisorDefinitionDTO advisorDefinitionDTO);

    /**
     * Get all the advisorDefinitions.
     *
     * @return the list of entities.
     */
    List<AdvisorDefinitionDTO> findAll();

    /**
     * Get the "id" advisorDefinition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdvisorDefinitionDTO> findOne(Long id);

    /**
     * Delete the "id" advisorDefinition.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
