package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.JustificationDeductionAmount;
import com.dotin.tfbita.domain.JustificationDeductionAmountPart;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountDTO;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountPartDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link JustificationDeductionAmountPart} and its DTO {@link JustificationDeductionAmountPartDTO}.
 */
@Mapper(componentModel = "spring")
public interface JustificationDeductionAmountPartMapper
    extends EntityMapper<JustificationDeductionAmountPartDTO, JustificationDeductionAmountPart> {
    @Mapping(
        target = "justificationDeductionAmount",
        source = "justificationDeductionAmount",
        qualifiedByName = "justificationDeductionAmountId"
    )
    JustificationDeductionAmountPartDTO toDto(JustificationDeductionAmountPart s);

    @Named("justificationDeductionAmountId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JustificationDeductionAmountDTO toDtoJustificationDeductionAmountId(JustificationDeductionAmount justificationDeductionAmount);
}
