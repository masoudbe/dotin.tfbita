package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftAsserts.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftMapperTest {

    private DraftMapper draftMapper;

    @BeforeEach
    void setUp() {
        draftMapper = new DraftMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftSample1();
        var actual = draftMapper.toEntity(draftMapper.toDto(expected));
        assertDraftAllPropertiesEquals(expected, actual);
    }
}
