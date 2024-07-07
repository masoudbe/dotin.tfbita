package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftRequestType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftRequestType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftRequestTypeRepository extends JpaRepository<DraftRequestType, Long> {}
