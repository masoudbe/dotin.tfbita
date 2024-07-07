package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.DraftStatusInfo;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftStatusInfo} and its DTO {@link DraftStatusInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftStatusInfoMapper extends EntityMapper<DraftStatusInfoDTO, DraftStatusInfo> {
    @Mapping(target = "status", source = "status", qualifiedByName = "categoryElementId")
    DraftStatusInfoDTO toDto(DraftStatusInfo s);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);
}
