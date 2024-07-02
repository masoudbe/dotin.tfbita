package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Custom} and its DTO {@link CustomDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomMapper extends EntityMapper<CustomDTO, Custom> {
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    CustomDTO toDto(Custom s);

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
}
