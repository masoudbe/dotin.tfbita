package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AdvisorDefinition;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdvisorDefinition} and its DTO {@link AdvisorDefinitionDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdvisorDefinitionMapper extends EntityMapper<AdvisorDefinitionDTO, AdvisorDefinition> {
    @Mapping(target = "advisingBank", source = "advisingBank", qualifiedByName = "draftId")
    @Mapping(target = "interfaceAdvisingBank", source = "interfaceAdvisingBank", qualifiedByName = "draftId")
    @Mapping(target = "coveringBank", source = "coveringBank", qualifiedByName = "draftId")
    AdvisorDefinitionDTO toDto(AdvisorDefinition s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
