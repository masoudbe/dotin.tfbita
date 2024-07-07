package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.PaymentItemType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PaymentItemType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentItemTypeRepository extends JpaRepository<PaymentItemType, Long> {}
