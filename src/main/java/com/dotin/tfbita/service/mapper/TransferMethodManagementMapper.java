package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.TransferMethodManagement;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.TransferMethodManagementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransferMethodManagement} and its DTO {@link TransferMethodManagementDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransferMethodManagementMapper extends EntityMapper<TransferMethodManagementDTO, TransferMethodManagement> {
    @Mapping(target = "type", source = "type", qualifiedByName = "categoryElementId")
    TransferMethodManagementDTO toDto(TransferMethodManagement s);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);
}
