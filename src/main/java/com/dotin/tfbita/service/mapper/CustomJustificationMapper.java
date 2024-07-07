package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.domain.CustomJustification;
import com.dotin.tfbita.domain.DocumentTransaction;
import com.dotin.tfbita.domain.JustificationDeductionAmount;
import com.dotin.tfbita.domain.PaymentCondition;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.domain.TradeTypeCode;
import com.dotin.tfbita.domain.TransportationType;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.dto.CustomJustificationDTO;
import com.dotin.tfbita.service.dto.DocumentTransactionDTO;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountDTO;
import com.dotin.tfbita.service.dto.PaymentConditionDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import com.dotin.tfbita.service.dto.TradeTypeCodeDTO;
import com.dotin.tfbita.service.dto.TransportationTypeDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomJustification} and its DTO {@link CustomJustificationDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomJustificationMapper extends EntityMapper<CustomJustificationDTO, CustomJustification> {
    @Mapping(target = "vehicleEnterNationality", source = "vehicleEnterNationality", qualifiedByName = "categoryElementId")
    @Mapping(target = "container", source = "container", qualifiedByName = "categoryElementId")
    @Mapping(target = "vehicleCrossNationality", source = "vehicleCrossNationality", qualifiedByName = "categoryElementId")
    @Mapping(target = "exportCustom", source = "exportCustom", qualifiedByName = "customId")
    @Mapping(target = "entranceCustom", source = "entranceCustom", qualifiedByName = "customId")
    @Mapping(target = "transportConditions", source = "transportConditions", qualifiedByName = "transportationTypeId")
    @Mapping(target = "tradeTypeCode", source = "tradeTypeCode", qualifiedByName = "tradeTypeCodeId")
    @Mapping(target = "newPaymentConditions", source = "newPaymentConditions", qualifiedByName = "paymentConditionId")
    @Mapping(
        target = "justificationDeductionAmount",
        source = "justificationDeductionAmount",
        qualifiedByName = "justificationDeductionAmountId"
    )
    @Mapping(target = "products", source = "products", qualifiedByName = "productIdSet")
    @Mapping(
        target = "reverseOfJustificationDocumentTransactions",
        source = "reverseOfJustificationDocumentTransactions",
        qualifiedByName = "documentTransactionIdSet"
    )
    CustomJustificationDTO toDto(CustomJustification s);

    @Mapping(target = "removeProducts", ignore = true)
    @Mapping(target = "removeReverseOfJustificationDocumentTransactions", ignore = true)
    CustomJustification toEntity(CustomJustificationDTO customJustificationDTO);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("customId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomDTO toDtoCustomId(Custom custom);

    @Named("transportationTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransportationTypeDTO toDtoTransportationTypeId(TransportationType transportationType);

    @Named("tradeTypeCodeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TradeTypeCodeDTO toDtoTradeTypeCodeId(TradeTypeCode tradeTypeCode);

    @Named("paymentConditionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PaymentConditionDTO toDtoPaymentConditionId(PaymentCondition paymentCondition);

    @Named("justificationDeductionAmountId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JustificationDeductionAmountDTO toDtoJustificationDeductionAmountId(JustificationDeductionAmount justificationDeductionAmount);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("productIdSet")
    default Set<ProductDTO> toDtoProductIdSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductId).collect(Collectors.toSet());
    }

    @Named("documentTransactionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DocumentTransactionDTO toDtoDocumentTransactionId(DocumentTransaction documentTransaction);

    @Named("documentTransactionIdSet")
    default Set<DocumentTransactionDTO> toDtoDocumentTransactionIdSet(Set<DocumentTransaction> documentTransaction) {
        return documentTransaction.stream().map(this::toDtoDocumentTransactionId).collect(Collectors.toSet());
    }
}
