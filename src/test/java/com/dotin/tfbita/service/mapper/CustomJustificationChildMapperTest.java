package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.CustomJustificationChildAsserts.*;
import static com.dotin.tfbita.domain.CustomJustificationChildTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomJustificationChildMapperTest {

    private CustomJustificationChildMapper customJustificationChildMapper;

    @BeforeEach
    void setUp() {
        customJustificationChildMapper = new CustomJustificationChildMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCustomJustificationChildSample1();
        var actual = customJustificationChildMapper.toEntity(customJustificationChildMapper.toDto(expected));
        assertCustomJustificationChildAllPropertiesEquals(expected, actual);
    }
}
