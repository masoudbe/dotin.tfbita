package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.Custom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface CustomRepositoryWithBagRelationships {
    Optional<Custom> fetchBagRelationships(Optional<Custom> custom);

    List<Custom> fetchBagRelationships(List<Custom> customs);

    Page<Custom> fetchBagRelationships(Page<Custom> customs);
}
