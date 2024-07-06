package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.BasicInfoAsserts.*;
import static com.dotin.tfbita.domain.BasicInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicInfoMapperTest {

    private BasicInfoMapper basicInfoMapper;

    @BeforeEach
    void setUp() {
        basicInfoMapper = new BasicInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getBasicInfoSample1();
        var actual = basicInfoMapper.toEntity(basicInfoMapper.toDto(expected));
        assertBasicInfoAllPropertiesEquals(expected, actual);
    }
}
