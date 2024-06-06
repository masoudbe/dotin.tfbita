package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.OrderRegServ;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.service.dto.OrderRegServDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderRegServ} and its DTO {@link OrderRegServDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderRegServMapper extends EntityMapper<OrderRegServDTO, OrderRegServ> {
    @Mapping(target = "orderRegistrationInfo", source = "orderRegistrationInfo", qualifiedByName = "orderRegistrationInfoId")
    OrderRegServDTO toDto(OrderRegServ s);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);
}
