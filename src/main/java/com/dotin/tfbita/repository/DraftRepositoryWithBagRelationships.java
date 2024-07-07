package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.Draft;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface DraftRepositoryWithBagRelationships {
    Optional<Draft> fetchBagRelationships(Optional<Draft> draft);

    List<Draft> fetchBagRelationships(List<Draft> drafts);

    Page<Draft> fetchBagRelationships(Page<Draft> drafts);
}
