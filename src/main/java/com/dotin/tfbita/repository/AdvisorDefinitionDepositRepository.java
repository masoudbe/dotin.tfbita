package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.AdvisorDefinitionDeposit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AdvisorDefinitionDeposit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdvisorDefinitionDepositRepository extends JpaRepository<AdvisorDefinitionDeposit, Long> {}
