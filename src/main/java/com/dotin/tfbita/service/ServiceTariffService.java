package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.ServiceTariff}.
 */
public interface ServiceTariffService {
    /**
     * Save a serviceTariff.
     *
     * @param serviceTariffDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceTariffDTO save(ServiceTariffDTO serviceTariffDTO);

    /**
     * Updates a serviceTariff.
     *
     * @param serviceTariffDTO the entity to update.
     * @return the persisted entity.
     */
    ServiceTariffDTO update(ServiceTariffDTO serviceTariffDTO);

    /**
     * Partially updates a serviceTariff.
     *
     * @param serviceTariffDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ServiceTariffDTO> partialUpdate(ServiceTariffDTO serviceTariffDTO);

    /**
     * Get all the serviceTariffs.
     *
     * @return the list of entities.
     */
    List<ServiceTariffDTO> findAll();

    /**
     * Get the "id" serviceTariff.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceTariffDTO> findOne(Long id);

    /**
     * Delete the "id" serviceTariff.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
