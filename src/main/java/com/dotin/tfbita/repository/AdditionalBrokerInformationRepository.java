package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.AdditionalBrokerInformation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AdditionalBrokerInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdditionalBrokerInformationRepository extends JpaRepository<AdditionalBrokerInformation, Long> {}
