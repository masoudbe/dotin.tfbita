package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftExtend;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftExtend entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftExtendRepository extends JpaRepository<DraftExtend, Long> {}
