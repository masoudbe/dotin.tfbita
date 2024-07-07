package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.AttributeValue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AttributeValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {}
