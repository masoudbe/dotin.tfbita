package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.CategoryElementAsserts.*;
import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryElementMapperTest {

    private CategoryElementMapper categoryElementMapper;

    @BeforeEach
    void setUp() {
        categoryElementMapper = new CategoryElementMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCategoryElementSample1();
        var actual = categoryElementMapper.toEntity(categoryElementMapper.toDto(expected));
        assertCategoryElementAllPropertiesEquals(expected, actual);
    }
}
