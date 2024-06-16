package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.InsuranceCompanyInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InsuranceCompanyInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceCompanyInfoRepository extends JpaRepository<InsuranceCompanyInfo, Long> {}
