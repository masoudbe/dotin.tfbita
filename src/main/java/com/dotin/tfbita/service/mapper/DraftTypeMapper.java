package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.CreditTypeConditionInfo;
import com.dotin.tfbita.domain.DraftRequestType;
import com.dotin.tfbita.domain.DraftType;
import com.dotin.tfbita.domain.DraftTypeAccountInfo;
import com.dotin.tfbita.domain.DraftTypeTopicInfo;
import com.dotin.tfbita.domain.ObjectiveCategoryElement;
import com.dotin.tfbita.domain.StringValue;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.CreditTypeConditionInfoDTO;
import com.dotin.tfbita.service.dto.DraftRequestTypeDTO;
import com.dotin.tfbita.service.dto.DraftTypeAccountInfoDTO;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
import com.dotin.tfbita.service.dto.DraftTypeTopicInfoDTO;
import com.dotin.tfbita.service.dto.ObjectiveCategoryElementDTO;
import com.dotin.tfbita.service.dto.StringValueDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftType} and its DTO {@link DraftTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftTypeMapper extends EntityMapper<DraftTypeDTO, DraftType> {
    @Mapping(target = "type", source = "type", qualifiedByName = "categoryElementId")
    @Mapping(target = "secondaryType", source = "secondaryType", qualifiedByName = "categoryElementId")
    @Mapping(target = "division", source = "division", qualifiedByName = "categoryElementId")
    @Mapping(target = "topicInfo", source = "topicInfo", qualifiedByName = "draftTypeTopicInfoId")
    @Mapping(target = "conditionInfo", source = "conditionInfo", qualifiedByName = "creditTypeConditionInfoId")
    @Mapping(target = "accountInfo", source = "accountInfo", qualifiedByName = "draftTypeAccountInfoId")
    @Mapping(target = "requestType", source = "requestType", qualifiedByName = "draftRequestTypeId")
    @Mapping(target = "acceptableProductTypes", source = "acceptableProductTypes", qualifiedByName = "objectiveCategoryElementId")
    @Mapping(target = "userGroups", source = "userGroups", qualifiedByName = "stringValueIdSet")
    DraftTypeDTO toDto(DraftType s);

    @Mapping(target = "removeUserGroups", ignore = true)
    DraftType toEntity(DraftTypeDTO draftTypeDTO);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("draftTypeTopicInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftTypeTopicInfoDTO toDtoDraftTypeTopicInfoId(DraftTypeTopicInfo draftTypeTopicInfo);

    @Named("creditTypeConditionInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CreditTypeConditionInfoDTO toDtoCreditTypeConditionInfoId(CreditTypeConditionInfo creditTypeConditionInfo);

    @Named("draftTypeAccountInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftTypeAccountInfoDTO toDtoDraftTypeAccountInfoId(DraftTypeAccountInfo draftTypeAccountInfo);

    @Named("draftRequestTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftRequestTypeDTO toDtoDraftRequestTypeId(DraftRequestType draftRequestType);

    @Named("objectiveCategoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ObjectiveCategoryElementDTO toDtoObjectiveCategoryElementId(ObjectiveCategoryElement objectiveCategoryElement);

    @Named("stringValueId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StringValueDTO toDtoStringValueId(StringValue stringValue);

    @Named("stringValueIdSet")
    default Set<StringValueDTO> toDtoStringValueIdSet(Set<StringValue> stringValue) {
        return stringValue.stream().map(this::toDtoStringValueId).collect(Collectors.toSet());
    }
}
