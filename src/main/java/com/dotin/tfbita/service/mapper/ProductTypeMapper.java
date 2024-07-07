package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.ProductType;
import com.dotin.tfbita.domain.TypeAttribute;
import com.dotin.tfbita.service.dto.ProductTypeDTO;
import com.dotin.tfbita.service.dto.TypeAttributeDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductType} and its DTO {@link ProductTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductTypeMapper extends EntityMapper<ProductTypeDTO, ProductType> {
    @Mapping(target = "productTypeAttributes", source = "productTypeAttributes", qualifiedByName = "typeAttributeIdSet")
    ProductTypeDTO toDto(ProductType s);

    @Mapping(target = "removeProductTypeAttributes", ignore = true)
    ProductType toEntity(ProductTypeDTO productTypeDTO);

    @Named("typeAttributeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeAttributeDTO toDtoTypeAttributeId(TypeAttribute typeAttribute);

    @Named("typeAttributeIdSet")
    default Set<TypeAttributeDTO> toDtoTypeAttributeIdSet(Set<TypeAttribute> typeAttribute) {
        return typeAttribute.stream().map(this::toDtoTypeAttributeId).collect(Collectors.toSet());
    }
}
