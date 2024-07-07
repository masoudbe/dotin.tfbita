package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftTypeAccountInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftTypeAccountInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftTypeAccountInfoRepository extends JpaRepository<DraftTypeAccountInfo, Long> {}
