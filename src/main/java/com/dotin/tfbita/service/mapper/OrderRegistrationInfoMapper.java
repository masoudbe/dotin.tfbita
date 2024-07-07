package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.domain.StringValue;
import com.dotin.tfbita.domain.TransportationType;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import com.dotin.tfbita.service.dto.StringValueDTO;
import com.dotin.tfbita.service.dto.TransportationTypeDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderRegistrationInfo} and its DTO {@link OrderRegistrationInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderRegistrationInfoMapper extends EntityMapper<OrderRegistrationInfoDTO, OrderRegistrationInfo> {
    @Mapping(target = "orderRegType", source = "orderRegType", qualifiedByName = "categoryElementId")
    @Mapping(target = "requestType", source = "requestType", qualifiedByName = "categoryElementId")
    @Mapping(target = "importType", source = "importType", qualifiedByName = "categoryElementId")
    @Mapping(target = "operationType", source = "operationType", qualifiedByName = "categoryElementId")
    @Mapping(target = "currencyProvisionType", source = "currencyProvisionType", qualifiedByName = "categoryElementId")
    @Mapping(target = "paymentTool", source = "paymentTool", qualifiedByName = "categoryElementId")
    @Mapping(target = "activityType", source = "activityType", qualifiedByName = "categoryElementId")
    @Mapping(target = "ownerType", source = "ownerType", qualifiedByName = "categoryElementId")
    @Mapping(target = "status", source = "status", qualifiedByName = "categoryElementId")
    @Mapping(target = "externalCustomerType", source = "externalCustomerType", qualifiedByName = "categoryElementId")
    @Mapping(target = "transportVehicleType", source = "transportVehicleType", qualifiedByName = "categoryElementId")
    @Mapping(target = "transportType", source = "transportType", qualifiedByName = "transportationTypeId")
    @Mapping(target = "destCoustomers", source = "destCoustomers", qualifiedByName = "customId")
    @Mapping(target = "cargoPlaceCustoms", source = "cargoPlaceCustoms", qualifiedByName = "customId")
    @Mapping(target = "entranceBorders", source = "entranceBorders", qualifiedByName = "customId")
    @Mapping(target = "productInfos", source = "productInfos", qualifiedByName = "productIdSet")
    @Mapping(target = "commissionTransactionNumbers", source = "commissionTransactionNumbers", qualifiedByName = "stringValueIdSet")
    OrderRegistrationInfoDTO toDto(OrderRegistrationInfo s);

    @Mapping(target = "removeProductInfo", ignore = true)
    @Mapping(target = "removeCommissionTransactionNumber", ignore = true)
    OrderRegistrationInfo toEntity(OrderRegistrationInfoDTO orderRegistrationInfoDTO);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("transportationTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransportationTypeDTO toDtoTransportationTypeId(TransportationType transportationType);

    @Named("customId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomDTO toDtoCustomId(Custom custom);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("productIdSet")
    default Set<ProductDTO> toDtoProductIdSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductId).collect(Collectors.toSet());
    }

    @Named("stringValueId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StringValueDTO toDtoStringValueId(StringValue stringValue);

    @Named("stringValueIdSet")
    default Set<StringValueDTO> toDtoStringValueIdSet(Set<StringValue> stringValue) {
        return stringValue.stream().map(this::toDtoStringValueId).collect(Collectors.toSet());
    }
}
