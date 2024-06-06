package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderRegistrationInfo} and its DTO {@link OrderRegistrationInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderRegistrationInfoMapper extends EntityMapper<OrderRegistrationInfoDTO, OrderRegistrationInfo> {
    @Mapping(target = "customs", source = "customs", qualifiedByName = "customIdSet")
    @Mapping(target = "productInfos", source = "productInfos", qualifiedByName = "productIdSet")
    OrderRegistrationInfoDTO toDto(OrderRegistrationInfo s);

    @Mapping(target = "customs", ignore = true)
    @Mapping(target = "removeCustom", ignore = true)
    @Mapping(target = "productInfos", ignore = true)
    @Mapping(target = "removeProductInfo", ignore = true)
    OrderRegistrationInfo toEntity(OrderRegistrationInfoDTO orderRegistrationInfoDTO);

    @Named("customId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomDTO toDtoCustomId(Custom custom);

    @Named("customIdSet")
    default Set<CustomDTO> toDtoCustomIdSet(Set<Custom> custom) {
        return custom.stream().map(this::toDtoCustomId).collect(Collectors.toSet());
    }

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("productIdSet")
    default Set<ProductDTO> toDtoProductIdSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductId).collect(Collectors.toSet());
    }
}
