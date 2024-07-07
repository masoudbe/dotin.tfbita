package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.AttributeValueGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AttributeValueGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeValueGroupRepository extends JpaRepository<AttributeValueGroup, Long> {}
