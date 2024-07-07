package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.InsuranceCompanyInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.InsuranceCompanyInfo}.
 */
public interface InsuranceCompanyInfoService {
    /**
     * Save a insuranceCompanyInfo.
     *
     * @param insuranceCompanyInfoDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceCompanyInfoDTO save(InsuranceCompanyInfoDTO insuranceCompanyInfoDTO);

    /**
     * Updates a insuranceCompanyInfo.
     *
     * @param insuranceCompanyInfoDTO the entity to update.
     * @return the persisted entity.
     */
    InsuranceCompanyInfoDTO update(InsuranceCompanyInfoDTO insuranceCompanyInfoDTO);

    /**
     * Partially updates a insuranceCompanyInfo.
     *
     * @param insuranceCompanyInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InsuranceCompanyInfoDTO> partialUpdate(InsuranceCompanyInfoDTO insuranceCompanyInfoDTO);

    /**
     * Get all the insuranceCompanyInfos.
     *
     * @return the list of entities.
     */
    List<InsuranceCompanyInfoDTO> findAll();

    /**
     * Get the "id" insuranceCompanyInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceCompanyInfoDTO> findOne(Long id);

    /**
     * Delete the "id" insuranceCompanyInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
