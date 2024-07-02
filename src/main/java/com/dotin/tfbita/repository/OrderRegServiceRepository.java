package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.OrderRegService;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderRegService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRegServiceRepository extends JpaRepository<OrderRegService, Long> {}
