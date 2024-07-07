package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.DocumentTransaction;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftDocumentTransactionContainer;
import com.dotin.tfbita.service.dto.DocumentTransactionDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftDocumentTransactionContainerDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftDocumentTransactionContainer} and its DTO {@link DraftDocumentTransactionContainerDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftDocumentTransactionContainerMapper
    extends EntityMapper<DraftDocumentTransactionContainerDTO, DraftDocumentTransactionContainer> {
    @Mapping(
        target = "issueCommissionDocumentTransaction",
        source = "issueCommissionDocumentTransaction",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(target = "paymentDocumentTransaction", source = "paymentDocumentTransaction", qualifiedByName = "documentTransactionId")
    @Mapping(target = "settleDocumentTransaction", source = "settleDocumentTransaction", qualifiedByName = "documentTransactionId")
    @Mapping(
        target = "settleExcessDocumentTransaction",
        source = "settleExcessDocumentTransaction",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(
        target = "commissionDeleteDraftDocumentTransaction",
        source = "commissionDeleteDraftDocumentTransaction",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(
        target = "commissionDraftExtendDocumentTransaction",
        source = "commissionDraftExtendDocumentTransaction",
        qualifiedByName = "documentTransactionId"
    )
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    DraftDocumentTransactionContainerDTO toDto(DraftDocumentTransactionContainer s);

    @Mapping(target = "drafts", ignore = true)
    @Mapping(target = "removeDraft", ignore = true)
    DraftDocumentTransactionContainer toEntity(DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO);

    @Named("documentTransactionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DocumentTransactionDTO toDtoDocumentTransactionId(DocumentTransaction documentTransaction);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("draftIdSet")
    default Set<DraftDTO> toDtoDraftIdSet(Set<Draft> draft) {
        return draft.stream().map(this::toDtoDraftId).collect(Collectors.toSet());
    }
}
