package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftType;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftType} and its DTO {@link DraftTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftTypeMapper extends EntityMapper<DraftTypeDTO, DraftType> {
    @Mapping(target = "draftType", source = "draftType", qualifiedByName = "draftId")
    DraftTypeDTO toDto(DraftType s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
