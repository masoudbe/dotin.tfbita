package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.SuggestedSanctionInfo;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.SuggestedSanctionInfoDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SuggestedSanctionInfo} and its DTO {@link SuggestedSanctionInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface SuggestedSanctionInfoMapper extends EntityMapper<SuggestedSanctionInfoDTO, SuggestedSanctionInfo> {
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    SuggestedSanctionInfoDTO toDto(SuggestedSanctionInfo s);

    @Mapping(target = "drafts", ignore = true)
    @Mapping(target = "removeDraft", ignore = true)
    SuggestedSanctionInfo toEntity(SuggestedSanctionInfoDTO suggestedSanctionInfoDTO);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("draftIdSet")
    default Set<DraftDTO> toDtoDraftIdSet(Set<Draft> draft) {
        return draft.stream().map(this::toDtoDraftId).collect(Collectors.toSet());
    }
}
