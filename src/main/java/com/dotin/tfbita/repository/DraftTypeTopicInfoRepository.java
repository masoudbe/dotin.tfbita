package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftTypeTopicInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftTypeTopicInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DraftTypeTopicInfoRepository extends JpaRepository<DraftTypeTopicInfo, Long> {}
