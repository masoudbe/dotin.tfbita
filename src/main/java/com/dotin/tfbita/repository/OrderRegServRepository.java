package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.OrderRegServ;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderRegServ entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRegServRepository extends JpaRepository<OrderRegServ, Long> {}
