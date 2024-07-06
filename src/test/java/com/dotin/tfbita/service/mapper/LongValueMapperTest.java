package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.LongValueAsserts.*;
import static com.dotin.tfbita.domain.LongValueTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LongValueMapperTest {

    private LongValueMapper longValueMapper;

    @BeforeEach
    void setUp() {
        longValueMapper = new LongValueMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLongValueSample1();
        var actual = longValueMapper.toEntity(longValueMapper.toDto(expected));
        assertLongValueAllPropertiesEquals(expected, actual);
    }
}
