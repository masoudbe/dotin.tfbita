package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.JustificationDeductionAmount;
import com.dotin.tfbita.service.dto.JustificationDeductionAmountDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link JustificationDeductionAmount} and its DTO {@link JustificationDeductionAmountDTO}.
 */
@Mapper(componentModel = "spring")
public interface JustificationDeductionAmountMapper extends EntityMapper<JustificationDeductionAmountDTO, JustificationDeductionAmount> {}
