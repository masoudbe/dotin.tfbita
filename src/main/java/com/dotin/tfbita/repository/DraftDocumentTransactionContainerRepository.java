package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftDocumentTransactionContainer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftDocumentTransactionContainer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftDocumentTransactionContainerRepository extends JpaRepository<DraftDocumentTransactionContainer, Long> {}
