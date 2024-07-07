package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftTypeAsserts.*;
import static com.dotin.tfbita.domain.DraftTypeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftTypeMapperTest {

    private DraftTypeMapper draftTypeMapper;

    @BeforeEach
    void setUp() {
        draftTypeMapper = new DraftTypeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftTypeSample1();
        var actual = draftTypeMapper.toEntity(draftTypeMapper.toDto(expected));
        assertDraftTypeAllPropertiesEquals(expected, actual);
    }
}
