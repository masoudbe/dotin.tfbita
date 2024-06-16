package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftCustomJustification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface DraftCustomJustificationRepositoryWithBagRelationships {
    Optional<DraftCustomJustification> fetchBagRelationships(Optional<DraftCustomJustification> draftCustomJustification);

    List<DraftCustomJustification> fetchBagRelationships(List<DraftCustomJustification> draftCustomJustifications);

    Page<DraftCustomJustification> fetchBagRelationships(Page<DraftCustomJustification> draftCustomJustifications);
}
