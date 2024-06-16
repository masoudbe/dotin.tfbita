package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftTax;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftTaxDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftTax} and its DTO {@link DraftTaxDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftTaxMapper extends EntityMapper<DraftTaxDTO, DraftTax> {
    @Mapping(target = "taxes", source = "taxes", qualifiedByName = "draftId")
    DraftTaxDTO toDto(DraftTax s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
