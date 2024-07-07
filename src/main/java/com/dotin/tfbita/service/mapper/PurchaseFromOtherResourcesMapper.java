package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.PurchaseFromOtherResources;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.PurchaseFromOtherResourcesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PurchaseFromOtherResources} and its DTO {@link PurchaseFromOtherResourcesDTO}.
 */
@Mapper(componentModel = "spring")
public interface PurchaseFromOtherResourcesMapper extends EntityMapper<PurchaseFromOtherResourcesDTO, PurchaseFromOtherResources> {
    @Mapping(target = "currencySupplier", source = "currencySupplier", qualifiedByName = "categoryElementId")
    @Mapping(target = "status", source = "status", qualifiedByName = "categoryElementId")
    @Mapping(target = "orderRegistrationInfo", source = "orderRegistrationInfo", qualifiedByName = "orderRegistrationInfoId")
    PurchaseFromOtherResourcesDTO toDto(PurchaseFromOtherResources s);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);
}
