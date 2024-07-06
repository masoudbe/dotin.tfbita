package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.DraftProductInfo;
import com.dotin.tfbita.domain.DraftReceipt;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.service.dto.DraftProductInfoDTO;
import com.dotin.tfbita.service.dto.DraftReceiptDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftProductInfo} and its DTO {@link DraftProductInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftProductInfoMapper extends EntityMapper<DraftProductInfoDTO, DraftProductInfo> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    @Mapping(target = "draftReceipt", source = "draftReceipt", qualifiedByName = "draftReceiptId")
    DraftProductInfoDTO toDto(DraftProductInfo s);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("draftReceiptId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftReceiptDTO toDtoDraftReceiptId(DraftReceipt draftReceipt);
}
