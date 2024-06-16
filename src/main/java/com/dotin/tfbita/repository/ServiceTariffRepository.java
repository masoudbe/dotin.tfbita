package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.ServiceTariff;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ServiceTariff entity.
 *
 * When extending this class, extend ServiceTariffRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface ServiceTariffRepository extends ServiceTariffRepositoryWithBagRelationships, JpaRepository<ServiceTariff, Long> {
    default Optional<ServiceTariff> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<ServiceTariff> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<ServiceTariff> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
