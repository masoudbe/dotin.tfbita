package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.JustificationDeductionAmount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the JustificationDeductionAmount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JustificationDeductionAmountRepository extends JpaRepository<JustificationDeductionAmount, Long> {}
