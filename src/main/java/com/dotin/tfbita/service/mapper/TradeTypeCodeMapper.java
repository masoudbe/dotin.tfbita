package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.TradeTypeCode;
import com.dotin.tfbita.service.dto.TradeTypeCodeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TradeTypeCode} and its DTO {@link TradeTypeCodeDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradeTypeCodeMapper extends EntityMapper<TradeTypeCodeDTO, TradeTypeCode> {}
