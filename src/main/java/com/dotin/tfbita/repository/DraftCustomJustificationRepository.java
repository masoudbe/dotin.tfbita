package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftCustomJustification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DraftCustomJustification entity.
 *
 * When extending this class, extend DraftCustomJustificationRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface DraftCustomJustificationRepository
    extends DraftCustomJustificationRepositoryWithBagRelationships, JpaRepository<DraftCustomJustification, Long> {
    default Optional<DraftCustomJustification> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<DraftCustomJustification> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<DraftCustomJustification> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
