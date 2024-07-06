package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DocumentTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DocumentTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentTransactionRepository extends JpaRepository<DocumentTransaction, Long> {}
