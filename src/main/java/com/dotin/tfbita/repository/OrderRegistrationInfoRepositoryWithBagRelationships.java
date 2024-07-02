package com.dotin.tfbita.repository;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface OrderRegistrationInfoRepositoryWithBagRelationships {
    Optional<OrderRegistrationInfo> fetchBagRelationships(Optional<OrderRegistrationInfo> orderRegistrationInfo);

    List<OrderRegistrationInfo> fetchBagRelationships(List<OrderRegistrationInfo> orderRegistrationInfos);

    Page<OrderRegistrationInfo> fetchBagRelationships(Page<OrderRegistrationInfo> orderRegistrationInfos);
}
