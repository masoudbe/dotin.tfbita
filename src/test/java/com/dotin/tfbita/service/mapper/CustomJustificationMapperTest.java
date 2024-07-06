package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.CustomJustificationAsserts.*;
import static com.dotin.tfbita.domain.CustomJustificationTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomJustificationMapperTest {

    private CustomJustificationMapper customJustificationMapper;

    @BeforeEach
    void setUp() {
        customJustificationMapper = new CustomJustificationMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCustomJustificationSample1();
        var actual = customJustificationMapper.toEntity(customJustificationMapper.toDto(expected));
        assertCustomJustificationAllPropertiesEquals(expected, actual);
    }
}
