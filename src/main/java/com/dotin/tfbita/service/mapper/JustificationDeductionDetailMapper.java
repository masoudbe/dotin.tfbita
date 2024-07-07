package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.JustificationDeductionAmount;
import com.dotin.tfbita.domain.JustificationDeductionDetail;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountDTO;
import com.dotin.tfbita.service.dto.JustificationDeductionDetailDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link JustificationDeductionDetail} and its DTO {@link JustificationDeductionDetailDTO}.
 */
@Mapper(componentModel = "spring")
public interface JustificationDeductionDetailMapper extends EntityMapper<JustificationDeductionDetailDTO, JustificationDeductionDetail> {
    @Mapping(target = "deductionReason", source = "deductionReason", qualifiedByName = "categoryElementId")
    @Mapping(
        target = "justificationDeductionAmount",
        source = "justificationDeductionAmount",
        qualifiedByName = "justificationDeductionAmountId"
    )
    JustificationDeductionDetailDTO toDto(JustificationDeductionDetail s);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("justificationDeductionAmountId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JustificationDeductionAmountDTO toDtoJustificationDeductionAmountId(JustificationDeductionAmount justificationDeductionAmount);
}
