package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.ServiceTariff;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ServiceTariffRepositoryWithBagRelationships {
    Optional<ServiceTariff> fetchBagRelationships(Optional<ServiceTariff> serviceTariff);

    List<ServiceTariff> fetchBagRelationships(List<ServiceTariff> serviceTariffs);

    Page<ServiceTariff> fetchBagRelationships(Page<ServiceTariff> serviceTariffs);
}
