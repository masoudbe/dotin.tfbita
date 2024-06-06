package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "orderRegistrationInfos", source = "orderRegistrationInfos", qualifiedByName = "orderRegistrationInfoIdSet")
    ProductDTO toDto(Product s);

    @Mapping(target = "removeOrderRegistrationInfo", ignore = true)
    Product toEntity(ProductDTO productDTO);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);

    @Named("orderRegistrationInfoIdSet")
    default Set<OrderRegistrationInfoDTO> toDtoOrderRegistrationInfoIdSet(Set<OrderRegistrationInfo> orderRegistrationInfo) {
        return orderRegistrationInfo.stream().map(this::toDtoOrderRegistrationInfoId).collect(Collectors.toSet());
    }
}
