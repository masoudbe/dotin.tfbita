package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CustomJustification;
import com.dotin.tfbita.domain.DocumentTransaction;
import com.dotin.tfbita.domain.DraftDocumentTransactionContainer;
import com.dotin.tfbita.service.dto.CustomJustificationDTO;
import com.dotin.tfbita.service.dto.DocumentTransactionDTO;
import com.dotin.tfbita.service.dto.DraftDocumentTransactionContainerDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocumentTransaction} and its DTO {@link DocumentTransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentTransactionMapper extends EntityMapper<DocumentTransactionDTO, DocumentTransaction> {
    @Mapping(
        target = "otherDocumentTransactionsContainer",
        source = "otherDocumentTransactionsContainer",
        qualifiedByName = "draftDocumentTransactionContainerId"
    )
    @Mapping(
        target = "canceledJustificationDocumentContainer",
        source = "canceledJustificationDocumentContainer",
        qualifiedByName = "draftDocumentTransactionContainerId"
    )
    @Mapping(
        target = "justificationDocumentTransactionsContainer",
        source = "justificationDocumentTransactionsContainer",
        qualifiedByName = "draftDocumentTransactionContainerId"
    )
    @Mapping(
        target = "receivedCommisionsContainer",
        source = "receivedCommisionsContainer",
        qualifiedByName = "draftDocumentTransactionContainerId"
    )
    @Mapping(
        target = "canceledDocumentTransactionsContainer",
        source = "canceledDocumentTransactionsContainer",
        qualifiedByName = "draftDocumentTransactionContainerId"
    )
    @Mapping(
        target = "returnedDefaultCurrencyCostsContainer",
        source = "returnedDefaultCurrencyCostsContainer",
        qualifiedByName = "draftDocumentTransactionContainerId"
    )
    @Mapping(
        target = "defaultCurrencyCostsDocumentContainer",
        source = "defaultCurrencyCostsDocumentContainer",
        qualifiedByName = "draftDocumentTransactionContainerId"
    )
    @Mapping(target = "customJustifications", source = "customJustifications", qualifiedByName = "customJustificationIdSet")
    DocumentTransactionDTO toDto(DocumentTransaction s);

    @Mapping(target = "customJustifications", ignore = true)
    @Mapping(target = "removeCustomJustification", ignore = true)
    DocumentTransaction toEntity(DocumentTransactionDTO documentTransactionDTO);

    @Named("draftDocumentTransactionContainerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDocumentTransactionContainerDTO toDtoDraftDocumentTransactionContainerId(
        DraftDocumentTransactionContainer draftDocumentTransactionContainer
    );

    @Named("customJustificationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomJustificationDTO toDtoCustomJustificationId(CustomJustification customJustification);

    @Named("customJustificationIdSet")
    default Set<CustomJustificationDTO> toDtoCustomJustificationIdSet(Set<CustomJustification> customJustification) {
        return customJustification.stream().map(this::toDtoCustomJustificationId).collect(Collectors.toSet());
    }
}
