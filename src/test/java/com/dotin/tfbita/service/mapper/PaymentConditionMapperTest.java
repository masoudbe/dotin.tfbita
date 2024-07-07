package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.PaymentConditionAsserts.*;
import static com.dotin.tfbita.domain.PaymentConditionTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentConditionMapperTest {

    private PaymentConditionMapper paymentConditionMapper;

    @BeforeEach
    void setUp() {
        paymentConditionMapper = new PaymentConditionMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPaymentConditionSample1();
        var actual = paymentConditionMapper.toEntity(paymentConditionMapper.toDto(expected));
        assertPaymentConditionAllPropertiesEquals(expected, actual);
    }
}
