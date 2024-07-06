package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.ServiceTariff;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ServiceTariff entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceTariffRepository extends JpaRepository<ServiceTariff, Long> {}
