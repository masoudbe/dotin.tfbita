package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.ObjectiveCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ObjectiveCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjectiveCategoryRepository extends JpaRepository<ObjectiveCategory, Long> {}
