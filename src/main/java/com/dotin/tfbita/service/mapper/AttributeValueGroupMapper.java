package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Attribute;
import com.dotin.tfbita.domain.AttributeValueGroup;
import com.dotin.tfbita.service.dto.AttributeDTO;
import com.dotin.tfbita.service.dto.AttributeValueGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AttributeValueGroup} and its DTO {@link AttributeValueGroupDTO}.
 */
@Mapper(componentModel = "spring")
public interface AttributeValueGroupMapper extends EntityMapper<AttributeValueGroupDTO, AttributeValueGroup> {
    @Mapping(target = "attribute", source = "attribute", qualifiedByName = "attributeId")
    AttributeValueGroupDTO toDto(AttributeValueGroup s);

    @Named("attributeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AttributeDTO toDtoAttributeId(Attribute attribute);
}
