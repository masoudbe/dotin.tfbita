package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderRegistrationInfo entity.
 *
 * When extending this class, extend OrderRegistrationInfoRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface OrderRegistrationInfoRepository
    extends OrderRegistrationInfoRepositoryWithBagRelationships, JpaRepository<OrderRegistrationInfo, Long> {
    default Optional<OrderRegistrationInfo> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<OrderRegistrationInfo> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<OrderRegistrationInfo> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
