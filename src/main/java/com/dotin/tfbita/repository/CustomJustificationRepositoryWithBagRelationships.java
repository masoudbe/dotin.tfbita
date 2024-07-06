package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.CustomJustification;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface CustomJustificationRepositoryWithBagRelationships {
    Optional<CustomJustification> fetchBagRelationships(Optional<CustomJustification> customJustification);

    List<CustomJustification> fetchBagRelationships(List<CustomJustification> customJustifications);

    Page<CustomJustification> fetchBagRelationships(Page<CustomJustification> customJustifications);
}
