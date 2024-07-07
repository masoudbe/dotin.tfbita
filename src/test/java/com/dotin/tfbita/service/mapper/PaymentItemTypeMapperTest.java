package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.PaymentItemTypeAsserts.*;
import static com.dotin.tfbita.domain.PaymentItemTypeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentItemTypeMapperTest {

    private PaymentItemTypeMapper paymentItemTypeMapper;

    @BeforeEach
    void setUp() {
        paymentItemTypeMapper = new PaymentItemTypeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPaymentItemTypeSample1();
        var actual = paymentItemTypeMapper.toEntity(paymentItemTypeMapper.toDto(expected));
        assertPaymentItemTypeAllPropertiesEquals(expected, actual);
    }
}
