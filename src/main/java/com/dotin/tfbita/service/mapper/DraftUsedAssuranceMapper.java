package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftUsedAssurance;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftUsedAssuranceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftUsedAssurance} and its DTO {@link DraftUsedAssuranceDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftUsedAssuranceMapper extends EntityMapper<DraftUsedAssuranceDTO, DraftUsedAssurance> {
    @Mapping(target = "draft", source = "draft", qualifiedByName = "draftId")
    DraftUsedAssuranceDTO toDto(DraftUsedAssurance s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
