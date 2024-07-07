package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AdvisorDefinition;
import com.dotin.tfbita.domain.AdvisorDefinitionDeposit;
import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDepositDTO;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdvisorDefinitionDeposit} and its DTO {@link AdvisorDefinitionDepositDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdvisorDefinitionDepositMapper extends EntityMapper<AdvisorDefinitionDepositDTO, AdvisorDefinitionDeposit> {
    @Mapping(target = "depositType", source = "depositType", qualifiedByName = "categoryElementId")
    @Mapping(target = "advisorDefinition", source = "advisorDefinition", qualifiedByName = "advisorDefinitionId")
    AdvisorDefinitionDepositDTO toDto(AdvisorDefinitionDeposit s);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("advisorDefinitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdvisorDefinitionDTO toDtoAdvisorDefinitionId(AdvisorDefinition advisorDefinition);
}
