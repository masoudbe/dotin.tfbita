package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.PaymentCondition;
import com.dotin.tfbita.service.dto.PaymentConditionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentCondition} and its DTO {@link PaymentConditionDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentConditionMapper extends EntityMapper<PaymentConditionDTO, PaymentCondition> {}
