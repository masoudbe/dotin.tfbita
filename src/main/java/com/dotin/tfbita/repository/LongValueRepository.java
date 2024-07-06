package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.LongValue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LongValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LongValueRepository extends JpaRepository<LongValue, Long> {}
