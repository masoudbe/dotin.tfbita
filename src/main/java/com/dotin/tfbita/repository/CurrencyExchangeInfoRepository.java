package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.CurrencyExchangeInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CurrencyExchangeInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrencyExchangeInfoRepository extends JpaRepository<CurrencyExchangeInfo, Long> {}
