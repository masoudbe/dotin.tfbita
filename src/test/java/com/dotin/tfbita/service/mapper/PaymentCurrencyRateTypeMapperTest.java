package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.PaymentCurrencyRateTypeAsserts.*;
import static com.dotin.tfbita.domain.PaymentCurrencyRateTypeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentCurrencyRateTypeMapperTest {

    private PaymentCurrencyRateTypeMapper paymentCurrencyRateTypeMapper;

    @BeforeEach
    void setUp() {
        paymentCurrencyRateTypeMapper = new PaymentCurrencyRateTypeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPaymentCurrencyRateTypeSample1();
        var actual = paymentCurrencyRateTypeMapper.toEntity(paymentCurrencyRateTypeMapper.toDto(expected));
        assertPaymentCurrencyRateTypeAllPropertiesEquals(expected, actual);
    }
}
