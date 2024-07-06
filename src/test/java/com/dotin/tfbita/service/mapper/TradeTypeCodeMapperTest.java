package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.TradeTypeCodeAsserts.*;
import static com.dotin.tfbita.domain.TradeTypeCodeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TradeTypeCodeMapperTest {

    private TradeTypeCodeMapper tradeTypeCodeMapper;

    @BeforeEach
    void setUp() {
        tradeTypeCodeMapper = new TradeTypeCodeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTradeTypeCodeSample1();
        var actual = tradeTypeCodeMapper.toEntity(tradeTypeCodeMapper.toDto(expected));
        assertTradeTypeCodeAllPropertiesEquals(expected, actual);
    }
}
