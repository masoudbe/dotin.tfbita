package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.StringValueAsserts.*;
import static com.dotin.tfbita.domain.StringValueTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringValueMapperTest {

    private StringValueMapper stringValueMapper;

    @BeforeEach
    void setUp() {
        stringValueMapper = new StringValueMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getStringValueSample1();
        var actual = stringValueMapper.toEntity(stringValueMapper.toDto(expected));
        assertStringValueAllPropertiesEquals(expected, actual);
    }
}
