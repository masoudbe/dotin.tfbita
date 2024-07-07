package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface DraftTypeRepositoryWithBagRelationships {
    Optional<DraftType> fetchBagRelationships(Optional<DraftType> draftType);

    List<DraftType> fetchBagRelationships(List<DraftType> draftTypes);

    Page<DraftType> fetchBagRelationships(Page<DraftType> draftTypes);
}
