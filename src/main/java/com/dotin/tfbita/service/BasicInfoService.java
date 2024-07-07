package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.BasicInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.BasicInfo}.
 */
public interface BasicInfoService {
    /**
     * Save a basicInfo.
     *
     * @param basicInfoDTO the entity to save.
     * @return the persisted entity.
     */
    BasicInfoDTO save(BasicInfoDTO basicInfoDTO);

    /**
     * Updates a basicInfo.
     *
     * @param basicInfoDTO the entity to update.
     * @return the persisted entity.
     */
    BasicInfoDTO update(BasicInfoDTO basicInfoDTO);

    /**
     * Partially updates a basicInfo.
     *
     * @param basicInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BasicInfoDTO> partialUpdate(BasicInfoDTO basicInfoDTO);

    /**
     * Get all the basicInfos.
     *
     * @return the list of entities.
     */
    List<BasicInfoDTO> findAll();

    /**
     * Get the "id" basicInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BasicInfoDTO> findOne(Long id);

    /**
     * Delete the "id" basicInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
