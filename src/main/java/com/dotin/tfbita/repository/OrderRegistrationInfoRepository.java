package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderRegistrationInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRegistrationInfoRepository extends JpaRepository<OrderRegistrationInfo, Long> {}
