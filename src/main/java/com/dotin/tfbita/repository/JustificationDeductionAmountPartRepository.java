package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.JustificationDeductionAmountPart;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the JustificationDeductionAmountPart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JustificationDeductionAmountPartRepository extends JpaRepository<JustificationDeductionAmountPart, Long> {}
