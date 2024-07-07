package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.Custom;
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
public class CustomRepositoryWithBagRelationshipsImpl implements CustomRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String CUSTOMS_PARAMETER = "customs";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Custom> fetchBagRelationships(Optional<Custom> custom) {
        return custom.map(this::fetchDrafts);
    }

    @Override
    public Page<Custom> fetchBagRelationships(Page<Custom> customs) {
        return new PageImpl<>(fetchBagRelationships(customs.getContent()), customs.getPageable(), customs.getTotalElements());
    }

    @Override
    public List<Custom> fetchBagRelationships(List<Custom> customs) {
        return Optional.of(customs).map(this::fetchDrafts).orElse(Collections.emptyList());
    }

    Custom fetchDrafts(Custom result) {
        return entityManager
            .createQuery("select custom from Custom custom left join fetch custom.drafts where custom.id = :id", Custom.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Custom> fetchDrafts(List<Custom> customs) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, customs.size()).forEach(index -> order.put(customs.get(index).getId(), index));
        List<Custom> result = entityManager
            .createQuery("select custom from Custom custom left join fetch custom.drafts where custom in :customs", Custom.class)
            .setParameter(CUSTOMS_PARAMETER, customs)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
