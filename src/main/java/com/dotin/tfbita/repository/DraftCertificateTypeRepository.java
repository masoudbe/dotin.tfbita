package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftCertificateType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftCertificateType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftCertificateTypeRepository extends JpaRepository<DraftCertificateType, Long> {}
