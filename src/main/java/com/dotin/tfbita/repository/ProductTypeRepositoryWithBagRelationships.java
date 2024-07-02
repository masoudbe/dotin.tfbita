package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.ProductType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ProductTypeRepositoryWithBagRelationships {
    Optional<ProductType> fetchBagRelationships(Optional<ProductType> productType);

    List<ProductType> fetchBagRelationships(List<ProductType> productTypes);

    Page<ProductType> fetchBagRelationships(Page<ProductType> productTypes);
}
