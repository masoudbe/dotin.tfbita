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
 * Mapper for the entity {@link DraftCustomJustification} and its DTO {@link DraftCustomJustificationDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftCustomJustificationMapper extends EntityMapper<DraftCustomJustificationDTO, DraftCustomJustification> {
    @Mapping(target = "draftReceipts", source = "draftReceipts", qualifiedByName = "draftReceiptIdSet")
    @Mapping(target = "draft", source = "draft", qualifiedByName = "draftId")
    DraftCustomJustificationDTO toDto(DraftCustomJustification s);

    @Mapping(target = "removeDraftReceipts", ignore = true)
    DraftCustomJustification toEntity(DraftCustomJustificationDTO draftCustomJustificationDTO);

    @Named("draftReceiptId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftReceiptDTO toDtoDraftReceiptId(DraftReceipt draftReceipt);

    @Named("draftReceiptIdSet")
    default Set<DraftReceiptDTO> toDtoDraftReceiptIdSet(Set<DraftReceipt> draftReceipt) {
        return draftReceipt.stream().map(this::toDtoDraftReceiptId).collect(Collectors.toSet());
    }

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
