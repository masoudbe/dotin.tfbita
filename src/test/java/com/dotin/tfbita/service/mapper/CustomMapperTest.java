package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.CustomAsserts.*;
import static com.dotin.tfbita.domain.CustomTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomMapperTest {

    private CustomMapper customMapper;

    @BeforeEach
    void setUp() {
        customMapper = new CustomMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCustomSample1();
        var actual = customMapper.toEntity(customMapper.toDto(expected));
        assertCustomAllPropertiesEquals(expected, actual);
    }
}
