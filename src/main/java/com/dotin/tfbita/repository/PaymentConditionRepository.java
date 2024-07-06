package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.PaymentCondition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PaymentCondition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentConditionRepository extends JpaRepository<PaymentCondition, Long> {}
