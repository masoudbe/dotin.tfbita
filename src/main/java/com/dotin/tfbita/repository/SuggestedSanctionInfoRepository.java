package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.SuggestedSanctionInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SuggestedSanctionInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuggestedSanctionInfoRepository extends JpaRepository<SuggestedSanctionInfo, Long> {}
