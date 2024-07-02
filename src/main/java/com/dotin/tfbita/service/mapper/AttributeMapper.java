package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Attribute;
import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.service.dto.AttributeDTO;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attribute} and its DTO {@link AttributeDTO}.
 */
@Mapper(componentModel = "spring")
public interface AttributeMapper extends EntityMapper<AttributeDTO, Attribute> {
    @Mapping(target = "format", source = "format", qualifiedByName = "categoryElementId")
    @Mapping(target = "type", source = "type", qualifiedByName = "categoryElementId")
    AttributeDTO toDto(Attribute s);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);
}
