package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftCustomJustification;
import com.dotin.tfbita.domain.DraftReceipt;
import com.dotin.tfbita.service.dto.DraftCustomJustificationDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftReceiptDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftReceipt} and its DTO {@link DraftReceiptDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftReceiptMapper extends EntityMapper<DraftReceiptDTO, DraftReceipt> {
    @Mapping(target = "receipts", source = "receipts", qualifiedByName = "draftId")
    @Mapping(target = "draftCustomJustifications", source = "draftCustomJustifications", qualifiedByName = "draftCustomJustificationIdSet")
    DraftReceiptDTO toDto(DraftReceipt s);

    @Mapping(target = "draftCustomJustifications", ignore = true)
    @Mapping(target = "removeDraftCustomJustification", ignore = true)
    DraftReceipt toEntity(DraftReceiptDTO draftReceiptDTO);

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
