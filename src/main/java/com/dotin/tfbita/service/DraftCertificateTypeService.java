package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.DraftCertificateTypeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.DraftCertificateType}.
 */
public interface DraftCertificateTypeService {
    /**
     * Save a draftCertificateType.
     *
     * @param draftCertificateTypeDTO the entity to save.
     * @return the persisted entity.
     */
    DraftCertificateTypeDTO save(DraftCertificateTypeDTO draftCertificateTypeDTO);

    /**
     * Updates a draftCertificateType.
     *
     * @param draftCertificateTypeDTO the entity to update.
     * @return the persisted entity.
     */
    DraftCertificateTypeDTO update(DraftCertificateTypeDTO draftCertificateTypeDTO);

    /**
     * Partially updates a draftCertificateType.
     *
     * @param draftCertificateTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DraftCertificateTypeDTO> partialUpdate(DraftCertificateTypeDTO draftCertificateTypeDTO);

    /**
     * Get all the draftCertificateTypes.
     *
     * @return the list of entities.
     */
    List<DraftCertificateTypeDTO> findAll();

    /**
     * Get the "id" draftCertificateType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DraftCertificateTypeDTO> findOne(Long id);

    /**
     * Delete the "id" draftCertificateType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
