package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.domain.ServiceTariff;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Draft} and its DTO {@link DraftDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftMapper extends EntityMapper<DraftDTO, Draft> {
    @Mapping(target = "customs", source = "customs", qualifiedByName = "customIdSet")
    @Mapping(target = "products", source = "products", qualifiedByName = "productIdSet")
    @Mapping(target = "services", source = "services", qualifiedByName = "serviceTariffIdSet")
    DraftDTO toDto(Draft s);

    @Mapping(target = "customs", ignore = true)
    @Mapping(target = "removeCustom", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "removeProducts", ignore = true)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "removeServices", ignore = true)
    Draft toEntity(DraftDTO draftDTO);

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

    @Named("serviceTariffId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServiceTariffDTO toDtoServiceTariffId(ServiceTariff serviceTariff);

    @Named("serviceTariffIdSet")
    default Set<ServiceTariffDTO> toDtoServiceTariffIdSet(Set<ServiceTariff> serviceTariff) {
        return serviceTariff.stream().map(this::toDtoServiceTariffId).collect(Collectors.toSet());
    }
}
