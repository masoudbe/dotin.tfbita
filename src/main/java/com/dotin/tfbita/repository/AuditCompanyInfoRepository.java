package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.AuditCompanyInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AuditCompanyInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditCompanyInfoRepository extends JpaRepository<AuditCompanyInfo, Long> {}
