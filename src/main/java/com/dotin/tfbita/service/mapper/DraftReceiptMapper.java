package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftCustomJustification;
import com.dotin.tfbita.domain.DraftReceipt;
import com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer;
import com.dotin.tfbita.domain.PaymentCurrencyRateType;
import com.dotin.tfbita.domain.PaymentItemType;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.DraftCustomJustificationDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftReceiptDTO;
import com.dotin.tfbita.service.dto.DraftReceiptDocumentTransactionContainerDTO;
import com.dotin.tfbita.service.dto.PaymentCurrencyRateTypeDTO;
import com.dotin.tfbita.service.dto.PaymentItemTypeDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftReceipt} and its DTO {@link DraftReceiptDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftReceiptMapper extends EntityMapper<DraftReceiptDTO, DraftReceipt> {
    @Mapping(target = "productDimension", source = "productDimension", qualifiedByName = "categoryElementId")
    @Mapping(target = "stateOfDocuments", source = "stateOfDocuments", qualifiedByName = "categoryElementId")
    @Mapping(target = "currencyProvisionFileType", source = "currencyProvisionFileType", qualifiedByName = "categoryElementId")
    @Mapping(target = "paymentCurrencyRateType", source = "paymentCurrencyRateType", qualifiedByName = "paymentCurrencyRateTypeId")
    @Mapping(target = "paymentItem", source = "paymentItem", qualifiedByName = "paymentItemTypeId")
    @Mapping(
        target = "documentTransactionContainer",
        source = "documentTransactionContainer",
        qualifiedByName = "draftReceiptDocumentTransactionContainerId"
    )
    @Mapping(target = "draft", source = "draft", qualifiedByName = "draftId")
    @Mapping(target = "draftCustomJustifications", source = "draftCustomJustifications", qualifiedByName = "draftCustomJustificationIdSet")
    DraftReceiptDTO toDto(DraftReceipt s);

    @Mapping(target = "draftCustomJustifications", ignore = true)
    @Mapping(target = "removeDraftCustomJustification", ignore = true)
    DraftReceipt toEntity(DraftReceiptDTO draftReceiptDTO);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("paymentCurrencyRateTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PaymentCurrencyRateTypeDTO toDtoPaymentCurrencyRateTypeId(PaymentCurrencyRateType paymentCurrencyRateType);

    @Named("paymentItemTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PaymentItemTypeDTO toDtoPaymentItemTypeId(PaymentItemType paymentItemType);

    @Named("draftReceiptDocumentTransactionContainerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftReceiptDocumentTransactionContainerDTO toDtoDraftReceiptDocumentTransactionContainerId(
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer
    );

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("draftCustomJustificationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftCustomJustificationDTO toDtoDraftCustomJustificationId(DraftCustomJustification draftCustomJustification);

    @Named("draftCustomJustificationIdSet")
    default Set<DraftCustomJustificationDTO> toDtoDraftCustomJustificationIdSet(Set<DraftCustomJustification> draftCustomJustification) {
        return draftCustomJustification.stream().map(this::toDtoDraftCustomJustificationId).collect(Collectors.toSet());
    }
}
