package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.AdditionalBrokerInformationDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.AdditionalBrokerInformation}.
 */
public interface AdditionalBrokerInformationService {
    /**
     * Save a additionalBrokerInformation.
     *
     * @param additionalBrokerInformationDTO the entity to save.
     * @return the persisted entity.
     */
    AdditionalBrokerInformationDTO save(AdditionalBrokerInformationDTO additionalBrokerInformationDTO);

    /**
     * Updates a additionalBrokerInformation.
     *
     * @param additionalBrokerInformationDTO the entity to update.
     * @return the persisted entity.
     */
    AdditionalBrokerInformationDTO update(AdditionalBrokerInformationDTO additionalBrokerInformationDTO);

    /**
     * Partially updates a additionalBrokerInformation.
     *
     * @param additionalBrokerInformationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AdditionalBrokerInformationDTO> partialUpdate(AdditionalBrokerInformationDTO additionalBrokerInformationDTO);

    /**
     * Get all the additionalBrokerInformations.
     *
     * @return the list of entities.
     */
    List<AdditionalBrokerInformationDTO> findAll();

    /**
     * Get all the AdditionalBrokerInformationDTO where AdvisorDefinition is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<AdditionalBrokerInformationDTO> findAllWhereAdvisorDefinitionIsNull();

    /**
     * Get the "id" additionalBrokerInformation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdditionalBrokerInformationDTO> findOne(Long id);

    /**
     * Delete the "id" additionalBrokerInformation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
