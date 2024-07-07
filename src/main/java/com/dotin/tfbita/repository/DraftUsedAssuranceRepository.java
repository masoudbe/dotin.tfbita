package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftUsedAssurance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftUsedAssurance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftUsedAssuranceRepository extends JpaRepository<DraftUsedAssurance, Long> {}
