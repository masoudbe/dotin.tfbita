package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.PaymentCurrencyRateType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PaymentCurrencyRateType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentCurrencyRateTypeRepository extends JpaRepository<PaymentCurrencyRateType, Long> {}
