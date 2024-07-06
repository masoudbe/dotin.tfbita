package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.CreditTypeCondition;
import com.dotin.tfbita.domain.CreditTypeConditionInfo;
import com.dotin.tfbita.domain.ObjectiveCategoryElement;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.CreditTypeConditionDTO;
import com.dotin.tfbita.service.dto.CreditTypeConditionInfoDTO;
import com.dotin.tfbita.service.dto.ObjectiveCategoryElementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreditTypeCondition} and its DTO {@link CreditTypeConditionDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreditTypeConditionMapper extends EntityMapper<CreditTypeConditionDTO, CreditTypeCondition> {
    @Mapping(target = "serviceOrProduct", source = "serviceOrProduct", qualifiedByName = "categoryElementId")
    @Mapping(
        target = "neededIdentificationDocTypes",
        source = "neededIdentificationDocTypes",
        qualifiedByName = "objectiveCategoryElementId"
    )
    @Mapping(target = "productTypes", source = "productTypes", qualifiedByName = "objectiveCategoryElementId")
    @Mapping(target = "assuranceTypes", source = "assuranceTypes", qualifiedByName = "objectiveCategoryElementId")
    @Mapping(target = "creditTypeConditionInfo", source = "creditTypeConditionInfo", qualifiedByName = "creditTypeConditionInfoId")
    CreditTypeConditionDTO toDto(CreditTypeCondition s);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("objectiveCategoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ObjectiveCategoryElementDTO toDtoObjectiveCategoryElementId(ObjectiveCategoryElement objectiveCategoryElement);

    @Named("creditTypeConditionInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CreditTypeConditionInfoDTO toDtoCreditTypeConditionInfoId(CreditTypeConditionInfo creditTypeConditionInfo);
}
