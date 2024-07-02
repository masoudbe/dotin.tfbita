package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
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
public class OrderRegistrationInfoRepositoryWithBagRelationshipsImpl implements OrderRegistrationInfoRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String ORDERREGISTRATIONINFOS_PARAMETER = "orderRegistrationInfos";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<OrderRegistrationInfo> fetchBagRelationships(Optional<OrderRegistrationInfo> orderRegistrationInfo) {
        return orderRegistrationInfo
            .map(this::fetchTransportVehicleTypes)
            .map(this::fetchProductInfos)
            .map(this::fetchCommissionTransactionNumbers);
    }

    @Override
    public Page<OrderRegistrationInfo> fetchBagRelationships(Page<OrderRegistrationInfo> orderRegistrationInfos) {
        return new PageImpl<>(
            fetchBagRelationships(orderRegistrationInfos.getContent()),
            orderRegistrationInfos.getPageable(),
            orderRegistrationInfos.getTotalElements()
        );
    }

    @Override
    public List<OrderRegistrationInfo> fetchBagRelationships(List<OrderRegistrationInfo> orderRegistrationInfos) {
        return Optional.of(orderRegistrationInfos)
            .map(this::fetchTransportVehicleTypes)
            .map(this::fetchProductInfos)
            .map(this::fetchCommissionTransactionNumbers)
            .orElse(Collections.emptyList());
    }

    OrderRegistrationInfo fetchTransportVehicleTypes(OrderRegistrationInfo result) {
        return entityManager
            .createQuery(
                "select orderRegistrationInfo from OrderRegistrationInfo orderRegistrationInfo left join fetch orderRegistrationInfo.transportVehicleTypes where orderRegistrationInfo.id = :id",
                OrderRegistrationInfo.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<OrderRegistrationInfo> fetchTransportVehicleTypes(List<OrderRegistrationInfo> orderRegistrationInfos) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, orderRegistrationInfos.size()).forEach(index -> order.put(orderRegistrationInfos.get(index).getId(), index));
        List<OrderRegistrationInfo> result = entityManager
            .createQuery(
                "select orderRegistrationInfo from OrderRegistrationInfo orderRegistrationInfo left join fetch orderRegistrationInfo.transportVehicleTypes where orderRegistrationInfo in :orderRegistrationInfos",
                OrderRegistrationInfo.class
            )
            .setParameter(ORDERREGISTRATIONINFOS_PARAMETER, orderRegistrationInfos)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    OrderRegistrationInfo fetchProductInfos(OrderRegistrationInfo result) {
        return entityManager
            .createQuery(
                "select orderRegistrationInfo from OrderRegistrationInfo orderRegistrationInfo left join fetch orderRegistrationInfo.productInfos where orderRegistrationInfo.id = :id",
                OrderRegistrationInfo.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<OrderRegistrationInfo> fetchProductInfos(List<OrderRegistrationInfo> orderRegistrationInfos) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, orderRegistrationInfos.size()).forEach(index -> order.put(orderRegistrationInfos.get(index).getId(), index));
        List<OrderRegistrationInfo> result = entityManager
            .createQuery(
                "select orderRegistrationInfo from OrderRegistrationInfo orderRegistrationInfo left join fetch orderRegistrationInfo.productInfos where orderRegistrationInfo in :orderRegistrationInfos",
                OrderRegistrationInfo.class
            )
            .setParameter(ORDERREGISTRATIONINFOS_PARAMETER, orderRegistrationInfos)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    OrderRegistrationInfo fetchCommissionTransactionNumbers(OrderRegistrationInfo result) {
        return entityManager
            .createQuery(
                "select orderRegistrationInfo from OrderRegistrationInfo orderRegistrationInfo left join fetch orderRegistrationInfo.commissionTransactionNumbers where orderRegistrationInfo.id = :id",
                OrderRegistrationInfo.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<OrderRegistrationInfo> fetchCommissionTransactionNumbers(List<OrderRegistrationInfo> orderRegistrationInfos) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, orderRegistrationInfos.size()).forEach(index -> order.put(orderRegistrationInfos.get(index).getId(), index));
        List<OrderRegistrationInfo> result = entityManager
            .createQuery(
                "select orderRegistrationInfo from OrderRegistrationInfo orderRegistrationInfo left join fetch orderRegistrationInfo.commissionTransactionNumbers where orderRegistrationInfo in :orderRegistrationInfos",
                OrderRegistrationInfo.class
            )
            .setParameter(ORDERREGISTRATIONINFOS_PARAMETER, orderRegistrationInfos)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
