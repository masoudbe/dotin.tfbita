package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.PurchaseFromOtherResourcesDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.PurchaseFromOtherResources}.
 */
public interface PurchaseFromOtherResourcesService {
    /**
     * Save a purchaseFromOtherResources.
     *
     * @param purchaseFromOtherResourcesDTO the entity to save.
     * @return the persisted entity.
     */
    PurchaseFromOtherResourcesDTO save(PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO);

    /**
     * Updates a purchaseFromOtherResources.
     *
     * @param purchaseFromOtherResourcesDTO the entity to update.
     * @return the persisted entity.
     */
    PurchaseFromOtherResourcesDTO update(PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO);

    /**
     * Partially updates a purchaseFromOtherResources.
     *
     * @param purchaseFromOtherResourcesDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PurchaseFromOtherResourcesDTO> partialUpdate(PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO);

    /**
     * Get all the purchaseFromOtherResources.
     *
     * @return the list of entities.
     */
    List<PurchaseFromOtherResourcesDTO> findAll();

    /**
     * Get the "id" purchaseFromOtherResources.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseFromOtherResourcesDTO> findOne(Long id);

    /**
     * Delete the "id" purchaseFromOtherResources.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
