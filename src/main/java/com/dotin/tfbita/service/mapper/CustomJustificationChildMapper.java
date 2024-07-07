package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CustomJustification;
import com.dotin.tfbita.domain.CustomJustificationChild;
import com.dotin.tfbita.service.dto.CustomJustificationChildDTO;
import com.dotin.tfbita.service.dto.CustomJustificationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomJustificationChild} and its DTO {@link CustomJustificationChildDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomJustificationChildMapper extends EntityMapper<CustomJustificationChildDTO, CustomJustificationChild> {
    @Mapping(target = "customJustification", source = "customJustification", qualifiedByName = "customJustificationId")
    CustomJustificationChildDTO toDto(CustomJustificationChild s);

    @Named("customJustificationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomJustificationDTO toDtoCustomJustificationId(CustomJustification customJustification);
}
