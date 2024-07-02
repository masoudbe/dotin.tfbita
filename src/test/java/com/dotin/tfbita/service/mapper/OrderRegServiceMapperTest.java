package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.OrderRegServiceAsserts.*;
import static com.dotin.tfbita.domain.OrderRegServiceTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderRegServiceMapperTest {

    private OrderRegServiceMapper orderRegServiceMapper;

    @BeforeEach
    void setUp() {
        orderRegServiceMapper = new OrderRegServiceMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getOrderRegServiceSample1();
        var actual = orderRegServiceMapper.toEntity(orderRegServiceMapper.toDto(expected));
        assertOrderRegServiceAllPropertiesEquals(expected, actual);
    }
}
