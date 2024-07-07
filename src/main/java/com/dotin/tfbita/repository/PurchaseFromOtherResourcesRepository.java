package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.PurchaseFromOtherResources;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PurchaseFromOtherResources entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseFromOtherResourcesRepository extends JpaRepository<PurchaseFromOtherResources, Long> {}
