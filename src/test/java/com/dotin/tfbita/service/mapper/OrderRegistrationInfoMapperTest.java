package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.OrderRegistrationInfoAsserts.*;
import static com.dotin.tfbita.domain.OrderRegistrationInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderRegistrationInfoMapperTest {

    private OrderRegistrationInfoMapper orderRegistrationInfoMapper;

    @BeforeEach
    void setUp() {
        orderRegistrationInfoMapper = new OrderRegistrationInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getOrderRegistrationInfoSample1();
        var actual = orderRegistrationInfoMapper.toEntity(orderRegistrationInfoMapper.toDto(expected));
        assertOrderRegistrationInfoAllPropertiesEquals(expected, actual);
    }
}
