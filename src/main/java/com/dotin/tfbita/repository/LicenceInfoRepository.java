package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.LicenceInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LicenceInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LicenceInfoRepository extends JpaRepository<LicenceInfo, Long> {}
