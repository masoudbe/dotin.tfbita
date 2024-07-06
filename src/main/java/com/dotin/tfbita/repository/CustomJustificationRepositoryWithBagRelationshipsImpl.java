package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.CustomJustification;
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
public class CustomJustificationRepositoryWithBagRelationshipsImpl implements CustomJustificationRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String CUSTOMJUSTIFICATIONS_PARAMETER = "customJustifications";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CustomJustification> fetchBagRelationships(Optional<CustomJustification> customJustification) {
        return customJustification.map(this::fetchProducts).map(this::fetchReverseOfJustificationDocumentTransactions);
    }

    @Override
    public Page<CustomJustification> fetchBagRelationships(Page<CustomJustification> customJustifications) {
        return new PageImpl<>(
            fetchBagRelationships(customJustifications.getContent()),
            customJustifications.getPageable(),
            customJustifications.getTotalElements()
        );
    }

    @Override
    public List<CustomJustification> fetchBagRelationships(List<CustomJustification> customJustifications) {
        return Optional.of(customJustifications)
            .map(this::fetchProducts)
            .map(this::fetchReverseOfJustificationDocumentTransactions)
            .orElse(Collections.emptyList());
    }

    CustomJustification fetchProducts(CustomJustification result) {
        return entityManager
            .createQuery(
                "select customJustification from CustomJustification customJustification left join fetch customJustification.products where customJustification.id = :id",
                CustomJustification.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<CustomJustification> fetchProducts(List<CustomJustification> customJustifications) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, customJustifications.size()).forEach(index -> order.put(customJustifications.get(index).getId(), index));
        List<CustomJustification> result = entityManager
            .createQuery(
                "select customJustification from CustomJustification customJustification left join fetch customJustification.products where customJustification in :customJustifications",
                CustomJustification.class
            )
            .setParameter(CUSTOMJUSTIFICATIONS_PARAMETER, customJustifications)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    CustomJustification fetchReverseOfJustificationDocumentTransactions(CustomJustification result) {
        return entityManager
            .createQuery(
                "select customJustification from CustomJustification customJustification left join fetch customJustification.reverseOfJustificationDocumentTransactions where customJustification.id = :id",
                CustomJustification.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<CustomJustification> fetchReverseOfJustificationDocumentTransactions(List<CustomJustification> customJustifications) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, customJustifications.size()).forEach(index -> order.put(customJustifications.get(index).getId(), index));
        List<CustomJustification> result = entityManager
            .createQuery(
                "select customJustification from CustomJustification customJustification left join fetch customJustification.reverseOfJustificationDocumentTransactions where customJustification in :customJustifications",
                CustomJustification.class
            )
            .setParameter(CUSTOMJUSTIFICATIONS_PARAMETER, customJustifications)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
