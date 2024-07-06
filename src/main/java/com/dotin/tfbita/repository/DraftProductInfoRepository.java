package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftProductInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftProductInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftProductInfoRepository extends JpaRepository<DraftProductInfo, Long> {}
