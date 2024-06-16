package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftTax;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftTax entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftTaxRepository extends JpaRepository<DraftTax, Long> {}
