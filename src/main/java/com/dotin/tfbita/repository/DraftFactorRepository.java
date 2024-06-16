package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftFactor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftFactor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftFactorRepository extends JpaRepository<DraftFactor, Long> {}
