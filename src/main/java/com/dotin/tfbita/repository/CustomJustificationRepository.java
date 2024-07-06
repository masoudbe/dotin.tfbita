package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.CustomJustification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CustomJustification entity.
 *
 * When extending this class, extend CustomJustificationRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface CustomJustificationRepository
    extends CustomJustificationRepositoryWithBagRelationships, JpaRepository<CustomJustification, Long> {
    default Optional<CustomJustification> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<CustomJustification> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<CustomJustification> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
