package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftExtend;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftExtendDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftExtend} and its DTO {@link DraftExtendDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftExtendMapper extends EntityMapper<DraftExtendDTO, DraftExtend> {
    @Mapping(target = "draftField", source = "draftField", qualifiedByName = "draftId")
    DraftExtendDTO toDto(DraftExtend s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
