package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.SwiftBic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SwiftBic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SwiftBicRepository extends JpaRepository<SwiftBic, Long> {}
