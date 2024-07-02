package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.StringValue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the StringValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StringValueRepository extends JpaRepository<StringValue, Long> {}
