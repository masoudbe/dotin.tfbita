package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.PaymentItemType;
import com.dotin.tfbita.service.dto.PaymentItemTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentItemType} and its DTO {@link PaymentItemTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentItemTypeMapper extends EntityMapper<PaymentItemTypeDTO, PaymentItemType> {}
