package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.CreditTypeConditionAsserts.*;
import static com.dotin.tfbita.domain.CreditTypeConditionTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditTypeConditionMapperTest {

    private CreditTypeConditionMapper creditTypeConditionMapper;

    @BeforeEach
    void setUp() {
        creditTypeConditionMapper = new CreditTypeConditionMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCreditTypeConditionSample1();
        var actual = creditTypeConditionMapper.toEntity(creditTypeConditionMapper.toDto(expected));
        assertCreditTypeConditionAllPropertiesEquals(expected, actual);
    }
}
