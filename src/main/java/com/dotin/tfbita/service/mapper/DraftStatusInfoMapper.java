package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftStatusInfo;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftStatusInfo} and its DTO {@link DraftStatusInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftStatusInfoMapper extends EntityMapper<DraftStatusInfoDTO, DraftStatusInfo> {
    @Mapping(target = "statusInfo", source = "statusInfo", qualifiedByName = "draftId")
    DraftStatusInfoDTO toDto(DraftStatusInfo s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
