package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CustomJustification;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.domain.ProductType;
import com.dotin.tfbita.service.dto.CustomJustificationDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import com.dotin.tfbita.service.dto.ProductTypeDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "productType", source = "productType", qualifiedByName = "productTypeId")
    @Mapping(target = "orderRegistrationInfos", source = "orderRegistrationInfos", qualifiedByName = "orderRegistrationInfoIdSet")
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    @Mapping(target = "customJustifications", source = "customJustifications", qualifiedByName = "customJustificationIdSet")
    ProductDTO toDto(Product s);

    @Mapping(target = "orderRegistrationInfos", ignore = true)
    @Mapping(target = "removeOrderRegistrationInfo", ignore = true)
    @Mapping(target = "drafts", ignore = true)
    @Mapping(target = "removeDraft", ignore = true)
    @Mapping(target = "customJustifications", ignore = true)
    @Mapping(target = "removeCustomJustification", ignore = true)
    Product toEntity(ProductDTO productDTO);

    @Named("productTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductTypeDTO toDtoProductTypeId(ProductType productType);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);

    @Named("orderRegistrationInfoIdSet")
    default Set<OrderRegistrationInfoDTO> toDtoOrderRegistrationInfoIdSet(Set<OrderRegistrationInfo> orderRegistrationInfo) {
        return orderRegistrationInfo.stream().map(this::toDtoOrderRegistrationInfoId).collect(Collectors.toSet());
    }

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("draftIdSet")
    default Set<DraftDTO> toDtoDraftIdSet(Set<Draft> draft) {
        return draft.stream().map(this::toDtoDraftId).collect(Collectors.toSet());
    }

    @Named("customJustificationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomJustificationDTO toDtoCustomJustificationId(CustomJustification customJustification);

    @Named("customJustificationIdSet")
    default Set<CustomJustificationDTO> toDtoCustomJustificationIdSet(Set<CustomJustification> customJustification) {
        return customJustification.stream().map(this::toDtoCustomJustificationId).collect(Collectors.toSet());
    }
}
