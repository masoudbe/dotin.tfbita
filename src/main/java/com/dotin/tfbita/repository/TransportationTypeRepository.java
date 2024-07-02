package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.TransportationType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TransportationType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportationTypeRepository extends JpaRepository<TransportationType, Long> {}
