package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.CurrencyExchangeInfo;
import com.dotin.tfbita.service.dto.CurrencyExchangeInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CurrencyExchangeInfo} and its DTO {@link CurrencyExchangeInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface CurrencyExchangeInfoMapper extends EntityMapper<CurrencyExchangeInfoDTO, CurrencyExchangeInfo> {}
