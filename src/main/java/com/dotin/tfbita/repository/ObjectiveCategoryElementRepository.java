package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.ObjectiveCategoryElement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ObjectiveCategoryElement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjectiveCategoryElementRepository extends JpaRepository<ObjectiveCategoryElement, Long> {}
