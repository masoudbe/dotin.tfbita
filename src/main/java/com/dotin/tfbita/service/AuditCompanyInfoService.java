package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.AuditCompanyInfoDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.AuditCompanyInfo}.
 */
public interface AuditCompanyInfoService {
    /**
     * Save a auditCompanyInfo.
     *
     * @param auditCompanyInfoDTO the entity to save.
     * @return the persisted entity.
     */
    AuditCompanyInfoDTO save(AuditCompanyInfoDTO auditCompanyInfoDTO);

    /**
     * Updates a auditCompanyInfo.
     *
     * @param auditCompanyInfoDTO the entity to update.
     * @return the persisted entity.
     */
    AuditCompanyInfoDTO update(AuditCompanyInfoDTO auditCompanyInfoDTO);

    /**
     * Partially updates a auditCompanyInfo.
     *
     * @param auditCompanyInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AuditCompanyInfoDTO> partialUpdate(AuditCompanyInfoDTO auditCompanyInfoDTO);

    /**
     * Get all the auditCompanyInfos.
     *
     * @return the list of entities.
     */
    List<AuditCompanyInfoDTO> findAll();

    /**
     * Get the "id" auditCompanyInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AuditCompanyInfoDTO> findOne(Long id);

    /**
     * Delete the "id" auditCompanyInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
