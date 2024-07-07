package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.PaymentCurrencyRateType;
import com.dotin.tfbita.service.dto.PaymentCurrencyRateTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentCurrencyRateType} and its DTO {@link PaymentCurrencyRateTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentCurrencyRateTypeMapper extends EntityMapper<PaymentCurrencyRateTypeDTO, PaymentCurrencyRateType> {}
