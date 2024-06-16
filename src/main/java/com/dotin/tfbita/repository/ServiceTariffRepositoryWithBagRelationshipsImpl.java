package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.ServiceTariff;
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
public class ServiceTariffRepositoryWithBagRelationshipsImpl implements ServiceTariffRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String SERVICETARIFFS_PARAMETER = "serviceTariffs";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ServiceTariff> fetchBagRelationships(Optional<ServiceTariff> serviceTariff) {
        return serviceTariff.map(this::fetchDrafts);
    }

    @Override
    public Page<ServiceTariff> fetchBagRelationships(Page<ServiceTariff> serviceTariffs) {
        return new PageImpl<>(
            fetchBagRelationships(serviceTariffs.getContent()),
            serviceTariffs.getPageable(),
            serviceTariffs.getTotalElements()
        );
    }

    @Override
    public List<ServiceTariff> fetchBagRelationships(List<ServiceTariff> serviceTariffs) {
        return Optional.of(serviceTariffs).map(this::fetchDrafts).orElse(Collections.emptyList());
    }

    ServiceTariff fetchDrafts(ServiceTariff result) {
        return entityManager
            .createQuery(
                "select serviceTariff from ServiceTariff serviceTariff left join fetch serviceTariff.drafts where serviceTariff.id = :id",
                ServiceTariff.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ServiceTariff> fetchDrafts(List<ServiceTariff> serviceTariffs) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, serviceTariffs.size()).forEach(index -> order.put(serviceTariffs.get(index).getId(), index));
        List<ServiceTariff> result = entityManager
            .createQuery(
                "select serviceTariff from ServiceTariff serviceTariff left join fetch serviceTariff.drafts where serviceTariff in :serviceTariffs",
                ServiceTariff.class
            )
            .setParameter(SERVICETARIFFS_PARAMETER, serviceTariffs)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
