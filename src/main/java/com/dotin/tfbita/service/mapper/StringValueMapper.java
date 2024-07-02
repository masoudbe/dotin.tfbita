package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.StringValue;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.StringValueDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link StringValue} and its DTO {@link StringValueDTO}.
 */
@Mapper(componentModel = "spring")
public interface StringValueMapper extends EntityMapper<StringValueDTO, StringValue> {
    @Mapping(target = "orderRegistrationInfos", source = "orderRegistrationInfos", qualifiedByName = "orderRegistrationInfoIdSet")
    StringValueDTO toDto(StringValue s);

    @Mapping(target = "orderRegistrationInfos", ignore = true)
    @Mapping(target = "removeOrderRegistrationInfo", ignore = true)
    StringValue toEntity(StringValueDTO stringValueDTO);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);

    @Named("orderRegistrationInfoIdSet")
    default Set<OrderRegistrationInfoDTO> toDtoOrderRegistrationInfoIdSet(Set<OrderRegistrationInfo> orderRegistrationInfo) {
        return orderRegistrationInfo.stream().map(this::toDtoOrderRegistrationInfoId).collect(Collectors.toSet());
    }
}
