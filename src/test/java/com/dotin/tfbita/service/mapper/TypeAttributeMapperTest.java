package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.TypeAttributeAsserts.*;
import static com.dotin.tfbita.domain.TypeAttributeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TypeAttributeMapperTest {

    private TypeAttributeMapper typeAttributeMapper;

    @BeforeEach
    void setUp() {
        typeAttributeMapper = new TypeAttributeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTypeAttributeSample1();
        var actual = typeAttributeMapper.toEntity(typeAttributeMapper.toDto(expected));
        assertTypeAttributeAllPropertiesEquals(expected, actual);
    }
}
