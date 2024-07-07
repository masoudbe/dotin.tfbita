package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftType;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.StringValue;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
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
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    @Mapping(target = "draftTypes", source = "draftTypes", qualifiedByName = "draftTypeIdSet")
    @Mapping(target = "orderRegistrationInfos", source = "orderRegistrationInfos", qualifiedByName = "orderRegistrationInfoIdSet")
    StringValueDTO toDto(StringValue s);

    @Mapping(target = "drafts", ignore = true)
    @Mapping(target = "removeDraft", ignore = true)
    @Mapping(target = "draftTypes", ignore = true)
    @Mapping(target = "removeDraftType", ignore = true)
    @Mapping(target = "orderRegistrationInfos", ignore = true)
    @Mapping(target = "removeOrderRegistrationInfo", ignore = true)
    StringValue toEntity(StringValueDTO stringValueDTO);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("draftIdSet")
    default Set<DraftDTO> toDtoDraftIdSet(Set<Draft> draft) {
        return draft.stream().map(this::toDtoDraftId).collect(Collectors.toSet());
    }

    @Named("draftTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftTypeDTO toDtoDraftTypeId(DraftType draftType);

    @Named("draftTypeIdSet")
    default Set<DraftTypeDTO> toDtoDraftTypeIdSet(Set<DraftType> draftType) {
        return draftType.stream().map(this::toDtoDraftTypeId).collect(Collectors.toSet());
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
