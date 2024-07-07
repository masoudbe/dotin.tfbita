package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.Draft;
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
public class DraftRepositoryWithBagRelationshipsImpl implements DraftRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String DRAFTS_PARAMETER = "drafts";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Draft> fetchBagRelationships(Optional<Draft> draft) {
        return draft
            .map(this::fetchServices)
            .map(this::fetchProducts)
            .map(this::fetchSanctionSerials)
            .map(this::fetchCustomerNumbers)
            .map(this::fetchSuggestedSanctions)
            .map(this::fetchDocumentTransactionContainers);
    }

    @Override
    public Page<Draft> fetchBagRelationships(Page<Draft> drafts) {
        return new PageImpl<>(fetchBagRelationships(drafts.getContent()), drafts.getPageable(), drafts.getTotalElements());
    }

    @Override
    public List<Draft> fetchBagRelationships(List<Draft> drafts) {
        return Optional.of(drafts)
            .map(this::fetchServices)
            .map(this::fetchProducts)
            .map(this::fetchSanctionSerials)
            .map(this::fetchCustomerNumbers)
            .map(this::fetchSuggestedSanctions)
            .map(this::fetchDocumentTransactionContainers)
            .orElse(Collections.emptyList());
    }

    Draft fetchServices(Draft result) {
        return entityManager
            .createQuery("select draft from Draft draft left join fetch draft.services where draft.id = :id", Draft.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Draft> fetchServices(List<Draft> drafts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, drafts.size()).forEach(index -> order.put(drafts.get(index).getId(), index));
        List<Draft> result = entityManager
            .createQuery("select draft from Draft draft left join fetch draft.services where draft in :drafts", Draft.class)
            .setParameter(DRAFTS_PARAMETER, drafts)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Draft fetchProducts(Draft result) {
        return entityManager
            .createQuery("select draft from Draft draft left join fetch draft.products where draft.id = :id", Draft.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Draft> fetchProducts(List<Draft> drafts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, drafts.size()).forEach(index -> order.put(drafts.get(index).getId(), index));
        List<Draft> result = entityManager
            .createQuery("select draft from Draft draft left join fetch draft.products where draft in :drafts", Draft.class)
            .setParameter(DRAFTS_PARAMETER, drafts)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Draft fetchSanctionSerials(Draft result) {
        return entityManager
            .createQuery("select draft from Draft draft left join fetch draft.sanctionSerials where draft.id = :id", Draft.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Draft> fetchSanctionSerials(List<Draft> drafts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, drafts.size()).forEach(index -> order.put(drafts.get(index).getId(), index));
        List<Draft> result = entityManager
            .createQuery("select draft from Draft draft left join fetch draft.sanctionSerials where draft in :drafts", Draft.class)
            .setParameter(DRAFTS_PARAMETER, drafts)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Draft fetchCustomerNumbers(Draft result) {
        return entityManager
            .createQuery("select draft from Draft draft left join fetch draft.customerNumbers where draft.id = :id", Draft.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Draft> fetchCustomerNumbers(List<Draft> drafts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, drafts.size()).forEach(index -> order.put(drafts.get(index).getId(), index));
        List<Draft> result = entityManager
            .createQuery("select draft from Draft draft left join fetch draft.customerNumbers where draft in :drafts", Draft.class)
            .setParameter(DRAFTS_PARAMETER, drafts)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Draft fetchSuggestedSanctions(Draft result) {
        return entityManager
            .createQuery("select draft from Draft draft left join fetch draft.suggestedSanctions where draft.id = :id", Draft.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Draft> fetchSuggestedSanctions(List<Draft> drafts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, drafts.size()).forEach(index -> order.put(drafts.get(index).getId(), index));
        List<Draft> result = entityManager
            .createQuery("select draft from Draft draft left join fetch draft.suggestedSanctions where draft in :drafts", Draft.class)
            .setParameter(DRAFTS_PARAMETER, drafts)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Draft fetchDocumentTransactionContainers(Draft result) {
        return entityManager
            .createQuery(
                "select draft from Draft draft left join fetch draft.documentTransactionContainers where draft.id = :id",
                Draft.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Draft> fetchDocumentTransactionContainers(List<Draft> drafts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, drafts.size()).forEach(index -> order.put(drafts.get(index).getId(), index));
        List<Draft> result = entityManager
            .createQuery(
                "select draft from Draft draft left join fetch draft.documentTransactionContainers where draft in :drafts",
                Draft.class
            )
            .setParameter(DRAFTS_PARAMETER, drafts)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
