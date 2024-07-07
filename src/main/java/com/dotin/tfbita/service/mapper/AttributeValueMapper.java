package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AttributeValue;
import com.dotin.tfbita.domain.AttributeValueGroup;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.domain.TypeAttribute;
import com.dotin.tfbita.service.dto.AttributeValueDTO;
import com.dotin.tfbita.service.dto.AttributeValueGroupDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import com.dotin.tfbita.service.dto.TypeAttributeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AttributeValue} and its DTO {@link AttributeValueDTO}.
 */
@Mapper(componentModel = "spring")
public interface AttributeValueMapper extends EntityMapper<AttributeValueDTO, AttributeValue> {
    @Mapping(target = "typeAttribute", source = "typeAttribute", qualifiedByName = "typeAttributeId")
    @Mapping(target = "attributeValueGroup", source = "attributeValueGroup", qualifiedByName = "attributeValueGroupId")
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    AttributeValueDTO toDto(AttributeValue s);

    @Named("typeAttributeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeAttributeDTO toDtoTypeAttributeId(TypeAttribute typeAttribute);

    @Named("attributeValueGroupId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AttributeValueGroupDTO toDtoAttributeValueGroupId(AttributeValueGroup attributeValueGroup);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);
}
