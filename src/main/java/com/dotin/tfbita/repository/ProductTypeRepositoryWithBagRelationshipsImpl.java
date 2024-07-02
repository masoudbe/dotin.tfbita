package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ProductTypeRepositoryWithBagRelationshipsImpl implements ProductTypeRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String PRODUCTTYPES_PARAMETER = "productTypes";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ProductType> fetchBagRelationships(Optional<ProductType> productType) {
        return productType.map(this::fetchProductTypeAttributes);
    }

    @Override
    public Page<ProductType> fetchBagRelationships(Page<ProductType> productTypes) {
        return new PageImpl<>(
            fetchBagRelationships(productTypes.getContent()),
            productTypes.getPageable(),
            productTypes.getTotalElements()
        );
    }

    @Override
    public List<ProductType> fetchBagRelationships(List<ProductType> productTypes) {
        return Optional.of(productTypes).map(this::fetchProductTypeAttributes).orElse(Collections.emptyList());
    }

    ProductType fetchProductTypeAttributes(ProductType result) {
        return entityManager
            .createQuery(
                "select productType from ProductType productType left join fetch productType.productTypeAttributes where productType.id = :id",
                ProductType.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ProductType> fetchProductTypeAttributes(List<ProductType> productTypes) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, productTypes.size()).forEach(index -> order.put(productTypes.get(index).getId(), index));
        List<ProductType> result = entityManager
            .createQuery(
                "select productType from ProductType productType left join fetch productType.productTypeAttributes where productType in :productTypes",
                ProductType.class
            )
            .setParameter(PRODUCTTYPES_PARAMETER, productTypes)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
