package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.LongValue;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.LongValueDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LongValue} and its DTO {@link LongValueDTO}.
 */
@Mapper(componentModel = "spring")
public interface LongValueMapper extends EntityMapper<LongValueDTO, LongValue> {
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    LongValueDTO toDto(LongValue s);

    @Mapping(target = "drafts", ignore = true)
    @Mapping(target = "removeDraft", ignore = true)
    LongValue toEntity(LongValueDTO longValueDTO);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("draftIdSet")
    default Set<DraftDTO> toDtoDraftIdSet(Set<Draft> draft) {
        return draft.stream().map(this::toDtoDraftId).collect(Collectors.toSet());
    }
}
