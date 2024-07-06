package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.CreditTypeConditionInfoAsserts.*;
import static com.dotin.tfbita.domain.CreditTypeConditionInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditTypeConditionInfoMapperTest {

    private CreditTypeConditionInfoMapper creditTypeConditionInfoMapper;

    @BeforeEach
    void setUp() {
        creditTypeConditionInfoMapper = new CreditTypeConditionInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCreditTypeConditionInfoSample1();
        var actual = creditTypeConditionInfoMapper.toEntity(creditTypeConditionInfoMapper.toDto(expected));
        assertCreditTypeConditionInfoAllPropertiesEquals(expected, actual);
    }
}
