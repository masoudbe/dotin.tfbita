package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftReceipt;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftReceiptDTO;
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
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    @Mapping(target = "draftProductInfos", source = "draftProductInfos", qualifiedByName = "draftReceiptId")
    ProductDTO toDto(Product s);

    @Mapping(target = "removeOrderRegistrationInfo", ignore = true)
    @Mapping(target = "removeDraft", ignore = true)
    Product toEntity(ProductDTO productDTO);

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

    @Named("draftReceiptId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftReceiptDTO toDtoDraftReceiptId(DraftReceipt draftReceipt);
}
