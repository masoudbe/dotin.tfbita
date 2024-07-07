package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftAccountInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftAccountInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftAccountInfoRepository extends JpaRepository<DraftAccountInfo, Long> {}
