package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.AdvisorDefinition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AdvisorDefinition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdvisorDefinitionRepository extends JpaRepository<AdvisorDefinition, Long> {}
