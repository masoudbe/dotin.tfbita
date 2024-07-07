package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.SwiftBicAsserts.*;
import static com.dotin.tfbita.domain.SwiftBicTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SwiftBicMapperTest {

    private SwiftBicMapper swiftBicMapper;

    @BeforeEach
    void setUp() {
        swiftBicMapper = new SwiftBicMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSwiftBicSample1();
        var actual = swiftBicMapper.toEntity(swiftBicMapper.toDto(expected));
        assertSwiftBicAllPropertiesEquals(expected, actual);
    }
}
