package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.ObjectiveCategoryElementAsserts.*;
import static com.dotin.tfbita.domain.ObjectiveCategoryElementTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObjectiveCategoryElementMapperTest {

    private ObjectiveCategoryElementMapper objectiveCategoryElementMapper;

    @BeforeEach
    void setUp() {
        objectiveCategoryElementMapper = new ObjectiveCategoryElementMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getObjectiveCategoryElementSample1();
        var actual = objectiveCategoryElementMapper.toEntity(objectiveCategoryElementMapper.toDto(expected));
        assertObjectiveCategoryElementAllPropertiesEquals(expected, actual);
    }
}
