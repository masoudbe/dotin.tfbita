package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.OrderRegServAsserts.*;
import static com.dotin.tfbita.domain.OrderRegServTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderRegServMapperTest {

    private OrderRegServMapper orderRegServMapper;

    @BeforeEach
    void setUp() {
        orderRegServMapper = new OrderRegServMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getOrderRegServSample1();
        var actual = orderRegServMapper.toEntity(orderRegServMapper.toDto(expected));
        assertOrderRegServAllPropertiesEquals(expected, actual);
    }
}
