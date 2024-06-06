package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.CategoryElement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CategoryElement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryElementRepository extends JpaRepository<CategoryElement, Long> {}
