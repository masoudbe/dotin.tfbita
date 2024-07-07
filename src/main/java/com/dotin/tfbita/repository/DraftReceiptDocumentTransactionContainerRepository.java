package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftReceiptDocumentTransactionContainer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftReceiptDocumentTransactionContainerRepository extends JpaRepository<DraftReceiptDocumentTransactionContainer, Long> {}
