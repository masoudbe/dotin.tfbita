package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Custom} and its DTO {@link CustomDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomMapper extends EntityMapper<CustomDTO, Custom> {
    @Mapping(target = "orderRegistrationInfos", source = "orderRegistrationInfos", qualifiedByName = "orderRegistrationInfoIdSet")
    CustomDTO toDto(Custom s);

    @Mapping(target = "removeOrderRegistrationInfo", ignore = true)
    Custom toEntity(CustomDTO customDTO);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);

    @Named("orderRegistrationInfoIdSet")
    default Set<OrderRegistrationInfoDTO> toDtoOrderRegistrationInfoIdSet(Set<OrderRegistrationInfo> orderRegistrationInfo) {
        return orderRegistrationInfo.stream().map(this::toDtoOrderRegistrationInfoId).collect(Collectors.toSet());
    }
}
