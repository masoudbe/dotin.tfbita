package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.ObjectiveCategoryAsserts.*;
import static com.dotin.tfbita.domain.ObjectiveCategoryTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObjectiveCategoryMapperTest {

    private ObjectiveCategoryMapper objectiveCategoryMapper;

    @BeforeEach
    void setUp() {
        objectiveCategoryMapper = new ObjectiveCategoryMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getObjectiveCategorySample1();
        var actual = objectiveCategoryMapper.toEntity(objectiveCategoryMapper.toDto(expected));
        assertObjectiveCategoryAllPropertiesEquals(expected, actual);
    }
}
