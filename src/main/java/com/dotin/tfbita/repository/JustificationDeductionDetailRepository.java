package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.JustificationDeductionDetail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the JustificationDeductionDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JustificationDeductionDetailRepository extends JpaRepository<JustificationDeductionDetail, Long> {}
