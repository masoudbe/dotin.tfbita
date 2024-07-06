package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.CustomJustificationChild;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CustomJustificationChild entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomJustificationChildRepository extends JpaRepository<CustomJustificationChild, Long> {}
