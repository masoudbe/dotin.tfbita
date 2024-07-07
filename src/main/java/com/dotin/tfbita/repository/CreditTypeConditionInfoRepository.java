package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.CreditTypeConditionInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CreditTypeConditionInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditTypeConditionInfoRepository extends JpaRepository<CreditTypeConditionInfo, Long> {}
