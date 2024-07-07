package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.TradeTypeCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TradeTypeCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradeTypeCodeRepository extends JpaRepository<TradeTypeCode, Long> {}
