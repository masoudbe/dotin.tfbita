package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.TransferMethodManagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TransferMethodManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransferMethodManagementRepository extends JpaRepository<TransferMethodManagement, Long> {}
