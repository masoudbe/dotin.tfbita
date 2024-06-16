package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Custom} and its DTO {@link CustomDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomMapper extends EntityMapper<CustomDTO, Custom> {
    @Mapping(target = "loadSwitchPlace", source = "loadSwitchPlace", qualifiedByName = "draftId")
    @Mapping(target = "orderRegistrationInfos", source = "orderRegistrationInfos", qualifiedByName = "orderRegistrationInfoIdSet")
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    CustomDTO toDto(Custom s);

    @Mapping(target = "removeOrderRegistrationInfo", ignore = true)
    @Mapping(target = "removeDraft", ignore = true)
    Custom toEntity(CustomDTO customDTO);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("draftIdSet")
    default Set<DraftDTO> toDtoDraftIdSet(Set<Draft> draft) {
        return draft.stream().map(this::toDtoDraftId).collect(Collectors.toSet());
    }

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);

    @Named("orderRegistrationInfoIdSet")
    default Set<OrderRegistrationInfoDTO> toDtoOrderRegistrationInfoIdSet(Set<OrderRegistrationInfo> orderRegistrationInfo) {
        return orderRegistrationInfo.stream().map(this::toDtoOrderRegistrationInfoId).collect(Collectors.toSet());
    }
}
