package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftReceipt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftReceipt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftReceiptRepository extends JpaRepository<DraftReceipt, Long> {}
