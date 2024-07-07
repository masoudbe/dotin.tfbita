package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftRequestTypeAsserts.*;
import static com.dotin.tfbita.domain.DraftRequestTypeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftRequestTypeMapperTest {

    private DraftRequestTypeMapper draftRequestTypeMapper;

    @BeforeEach
    void setUp() {
        draftRequestTypeMapper = new DraftRequestTypeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftRequestTypeSample1();
        var actual = draftRequestTypeMapper.toEntity(draftRequestTypeMapper.toDto(expected));
        assertDraftRequestTypeAllPropertiesEquals(expected, actual);
    }
}
