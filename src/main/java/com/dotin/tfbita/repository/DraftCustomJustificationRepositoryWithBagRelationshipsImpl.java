package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.DraftCustomJustification;
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
public class DraftCustomJustificationRepositoryWithBagRelationshipsImpl implements DraftCustomJustificationRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String DRAFTCUSTOMJUSTIFICATIONS_PARAMETER = "draftCustomJustifications";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<DraftCustomJustification> fetchBagRelationships(Optional<DraftCustomJustification> draftCustomJustification) {
        return draftCustomJustification.map(this::fetchDraftReceipts);
    }

    @Override
    public Page<DraftCustomJustification> fetchBagRelationships(Page<DraftCustomJustification> draftCustomJustifications) {
        return new PageImpl<>(
            fetchBagRelationships(draftCustomJustifications.getContent()),
            draftCustomJustifications.getPageable(),
            draftCustomJustifications.getTotalElements()
        );
    }

    @Override
    public List<DraftCustomJustification> fetchBagRelationships(List<DraftCustomJustification> draftCustomJustifications) {
        return Optional.of(draftCustomJustifications).map(this::fetchDraftReceipts).orElse(Collections.emptyList());
    }

    DraftCustomJustification fetchDraftReceipts(DraftCustomJustification result) {
        return entityManager
            .createQuery(
                "select draftCustomJustification from DraftCustomJustification draftCustomJustification left join fetch draftCustomJustification.draftReceipts where draftCustomJustification.id = :id",
                DraftCustomJustification.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<DraftCustomJustification> fetchDraftReceipts(List<DraftCustomJustification> draftCustomJustifications) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, draftCustomJustifications.size()).forEach(
            index -> order.put(draftCustomJustifications.get(index).getId(), index)
        );
        List<DraftCustomJustification> result = entityManager
            .createQuery(
                "select draftCustomJustification from DraftCustomJustification draftCustomJustification left join fetch draftCustomJustification.draftReceipts where draftCustomJustification in :draftCustomJustifications",
                DraftCustomJustification.class
            )
            .setParameter(DRAFTCUSTOMJUSTIFICATIONS_PARAMETER, draftCustomJustifications)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
