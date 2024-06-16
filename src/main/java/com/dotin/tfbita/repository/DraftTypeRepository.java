package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftTypeRepository extends JpaRepository<DraftType, Long> {}
