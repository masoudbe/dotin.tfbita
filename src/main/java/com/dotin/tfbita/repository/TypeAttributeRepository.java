package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.TypeAttribute;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TypeAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeAttributeRepository extends JpaRepository<TypeAttribute, Long> {}
