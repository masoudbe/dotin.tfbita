package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Attribute;
import com.dotin.tfbita.domain.ProductType;
import com.dotin.tfbita.domain.TypeAttribute;
import com.dotin.tfbita.service.dto.AttributeDTO;
import com.dotin.tfbita.service.dto.ProductTypeDTO;
import com.dotin.tfbita.service.dto.TypeAttributeDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeAttribute} and its DTO {@link TypeAttributeDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeAttributeMapper extends EntityMapper<TypeAttributeDTO, TypeAttribute> {
    @Mapping(target = "attribute", source = "attribute", qualifiedByName = "attributeId")
    @Mapping(target = "productTypes", source = "productTypes", qualifiedByName = "productTypeIdSet")
    TypeAttributeDTO toDto(TypeAttribute s);

    @Mapping(target = "productTypes", ignore = true)
    @Mapping(target = "removeProductType", ignore = true)
    TypeAttribute toEntity(TypeAttributeDTO typeAttributeDTO);

    @Named("attributeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AttributeDTO toDtoAttributeId(Attribute attribute);

    @Named("productTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductTypeDTO toDtoProductTypeId(ProductType productType);

    @Named("productTypeIdSet")
    default Set<ProductTypeDTO> toDtoProductTypeIdSet(Set<ProductType> productType) {
        return productType.stream().map(this::toDtoProductTypeId).collect(Collectors.toSet());
    }
}
