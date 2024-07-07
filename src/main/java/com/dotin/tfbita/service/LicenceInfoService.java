package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.LicenceInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.LicenceInfo}.
 */
public interface LicenceInfoService {
    /**
     * Save a licenceInfo.
     *
     * @param licenceInfoDTO the entity to save.
     * @return the persisted entity.
     */
    LicenceInfoDTO save(LicenceInfoDTO licenceInfoDTO);

    /**
     * Updates a licenceInfo.
     *
     * @param licenceInfoDTO the entity to update.
     * @return the persisted entity.
     */
    LicenceInfoDTO update(LicenceInfoDTO licenceInfoDTO);

    /**
     * Partially updates a licenceInfo.
     *
     * @param licenceInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LicenceInfoDTO> partialUpdate(LicenceInfoDTO licenceInfoDTO);

    /**
     * Get all the licenceInfos.
     *
     * @return the list of entities.
     */
    List<LicenceInfoDTO> findAll();

    /**
     * Get the "id" licenceInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LicenceInfoDTO> findOne(Long id);

    /**
     * Delete the "id" licenceInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
