package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.AttributeValueGroupAsserts.*;
import static com.dotin.tfbita.domain.AttributeValueGroupTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttributeValueGroupMapperTest {

    private AttributeValueGroupMapper attributeValueGroupMapper;

    @BeforeEach
    void setUp() {
        attributeValueGroupMapper = new AttributeValueGroupMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getAttributeValueGroupSample1();
        var actual = attributeValueGroupMapper.toEntity(attributeValueGroupMapper.toDto(expected));
        assertAttributeValueGroupAllPropertiesEquals(expected, actual);
    }
}
