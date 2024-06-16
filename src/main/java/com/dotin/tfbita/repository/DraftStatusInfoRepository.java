package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftStatusInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftStatusInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftStatusInfoRepository extends JpaRepository<DraftStatusInfo, Long> {}
