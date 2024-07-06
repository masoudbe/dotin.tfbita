package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.CurrencyExchangeInfoAsserts.*;
import static com.dotin.tfbita.domain.CurrencyExchangeInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurrencyExchangeInfoMapperTest {

    private CurrencyExchangeInfoMapper currencyExchangeInfoMapper;

    @BeforeEach
    void setUp() {
        currencyExchangeInfoMapper = new CurrencyExchangeInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCurrencyExchangeInfoSample1();
        var actual = currencyExchangeInfoMapper.toEntity(currencyExchangeInfoMapper.toDto(expected));
        assertCurrencyExchangeInfoAllPropertiesEquals(expected, actual);
    }
}
