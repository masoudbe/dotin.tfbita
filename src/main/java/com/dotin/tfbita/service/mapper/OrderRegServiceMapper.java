package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.OrderRegService;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.ServiceTariff;
import com.dotin.tfbita.service.dto.OrderRegServiceDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderRegService} and its DTO {@link OrderRegServiceDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderRegServiceMapper extends EntityMapper<OrderRegServiceDTO, OrderRegService> {
    @Mapping(target = "serviceTariff", source = "serviceTariff", qualifiedByName = "serviceTariffId")
    @Mapping(target = "orderRegistrationInfo", source = "orderRegistrationInfo", qualifiedByName = "orderRegistrationInfoId")
    OrderRegServiceDTO toDto(OrderRegService s);

    @Named("serviceTariffId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServiceTariffDTO toDtoServiceTariffId(ServiceTariff serviceTariff);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);
}
