package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.BasicInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BasicInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BasicInfoRepository extends JpaRepository<BasicInfo, Long> {}
