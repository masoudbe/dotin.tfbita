package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CreditTypeCondition;
import com.dotin.tfbita.domain.CreditTypeConditionInfo;
import com.dotin.tfbita.service.dto.CreditTypeConditionDTO;
import com.dotin.tfbita.service.dto.CreditTypeConditionInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreditTypeConditionInfo} and its DTO {@link CreditTypeConditionInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreditTypeConditionInfoMapper extends EntityMapper<CreditTypeConditionInfoDTO, CreditTypeConditionInfo> {
    @Mapping(target = "defaultCondition", source = "defaultCondition", qualifiedByName = "creditTypeConditionId")
    CreditTypeConditionInfoDTO toDto(CreditTypeConditionInfo s);

    @Named("creditTypeConditionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CreditTypeConditionDTO toDtoCreditTypeConditionId(CreditTypeCondition creditTypeCondition);
}
