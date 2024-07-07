package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftType;
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
public class DraftTypeRepositoryWithBagRelationshipsImpl implements DraftTypeRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String DRAFTTYPES_PARAMETER = "draftTypes";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<DraftType> fetchBagRelationships(Optional<DraftType> draftType) {
        return draftType.map(this::fetchUserGroups);
    }

    @Override
    public Page<DraftType> fetchBagRelationships(Page<DraftType> draftTypes) {
        return new PageImpl<>(fetchBagRelationships(draftTypes.getContent()), draftTypes.getPageable(), draftTypes.getTotalElements());
    }

    @Override
    public List<DraftType> fetchBagRelationships(List<DraftType> draftTypes) {
        return Optional.of(draftTypes).map(this::fetchUserGroups).orElse(Collections.emptyList());
    }

    DraftType fetchUserGroups(DraftType result) {
        return entityManager
            .createQuery(
                "select draftType from DraftType draftType left join fetch draftType.userGroups where draftType.id = :id",
                DraftType.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<DraftType> fetchUserGroups(List<DraftType> draftTypes) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, draftTypes.size()).forEach(index -> order.put(draftTypes.get(index).getId(), index));
        List<DraftType> result = entityManager
            .createQuery(
                "select draftType from DraftType draftType left join fetch draftType.userGroups where draftType in :draftTypes",
                DraftType.class
            )
            .setParameter(DRAFTTYPES_PARAMETER, draftTypes)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
