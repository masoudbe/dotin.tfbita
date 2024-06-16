package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.Draft;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Draft entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftRepository extends JpaRepository<Draft, Long> {}
