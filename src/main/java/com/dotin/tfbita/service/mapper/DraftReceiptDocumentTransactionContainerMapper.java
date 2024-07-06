package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.DocumentTransaction;
import com.dotin.tfbita.domain.DraftDocumentTransactionContainer;
import com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer;
import com.dotin.tfbita.service.dto.DocumentTransactionDTO;
import com.dotin.tfbita.service.dto.DraftDocumentTransactionContainerDTO;
import com.dotin.tfbita.service.dto.DraftReceiptDocumentTransactionContainerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftReceiptDocumentTransactionContainer} and its DTO {@link DraftReceiptDocumentTransactionContainerDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftReceiptDocumentTransactionContainerMapper
    extends EntityMapper<DraftReceiptDocumentTransactionContainerDTO, DraftReceiptDocumentTransactionContainer> {
    @Mapping(
        target = "receiptIssueDocumentTransaction",
        source = "receiptIssueDocumentTransaction",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(
        target = "freightLetterStampCostDocumentTransaction",
        source = "freightLetterStampCostDocumentTransaction",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(target = "deliverDocumentTransaction", source = "deliverDocumentTransaction", qualifiedByName = "documentTransactionId")
    @Mapping(
        target = "documentTransactionCanceledDeliver",
        source = "documentTransactionCanceledDeliver",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(
        target = "documentTransactionCanceledReceiptIssue",
        source = "documentTransactionCanceledReceiptIssue",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(
        target = "receiptCommissionDocumentTransactions",
        source = "receiptCommissionDocumentTransactions",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(
        target = "draftDocumentTransactionContainer",
        source = "draftDocumentTransactionContainer",
        qualifiedByName = "draftDocumentTransactionContainerId"
    )
    DraftReceiptDocumentTransactionContainerDTO toDto(DraftReceiptDocumentTransactionContainer s);

    @Named("documentTransactionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DocumentTransactionDTO toDtoDocumentTransactionId(DocumentTransaction documentTransaction);

    @Named("draftDocumentTransactionContainerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDocumentTransactionContainerDTO toDtoDraftDocumentTransactionContainerId(
        DraftDocumentTransactionContainer draftDocumentTransactionContainer
    );
}
