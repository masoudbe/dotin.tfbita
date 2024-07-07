package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.TransferMethodManagementDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.TransferMethodManagement}.
 */
public interface TransferMethodManagementService {
    /**
     * Save a transferMethodManagement.
     *
     * @param transferMethodManagementDTO the entity to save.
     * @return the persisted entity.
     */
    TransferMethodManagementDTO save(TransferMethodManagementDTO transferMethodManagementDTO);

    /**
     * Updates a transferMethodManagement.
     *
     * @param transferMethodManagementDTO the entity to update.
     * @return the persisted entity.
     */
    TransferMethodManagementDTO update(TransferMethodManagementDTO transferMethodManagementDTO);

    /**
     * Partially updates a transferMethodManagement.
     *
     * @param transferMethodManagementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TransferMethodManagementDTO> partialUpdate(TransferMethodManagementDTO transferMethodManagementDTO);

    /**
     * Get all the transferMethodManagements.
     *
     * @return the list of entities.
     */
    List<TransferMethodManagementDTO> findAll();

    /**
     * Get the "id" transferMethodManagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransferMethodManagementDTO> findOne(Long id);

    /**
     * Delete the "id" transferMethodManagement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
