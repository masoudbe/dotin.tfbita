package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.CreditTypeCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CreditTypeCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditTypeConditionRepository extends JpaRepository<CreditTypeCondition, Long> {}
