package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftExtendAsserts.*;
import static com.dotin.tfbita.domain.DraftExtendTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftExtendMapperTest {

    private DraftExtendMapper draftExtendMapper;

    @BeforeEach
    void setUp() {
        draftExtendMapper = new DraftExtendMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftExtendSample1();
        var actual = draftExtendMapper.toEntity(draftExtendMapper.toDto(expected));
        assertDraftExtendAllPropertiesEquals(expected, actual);
    }
}
