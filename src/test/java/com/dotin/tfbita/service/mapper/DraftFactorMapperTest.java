package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftFactorAsserts.*;
import static com.dotin.tfbita.domain.DraftFactorTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftFactorMapperTest {

    private DraftFactorMapper draftFactorMapper;

    @BeforeEach
    void setUp() {
        draftFactorMapper = new DraftFactorMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftFactorSample1();
        var actual = draftFactorMapper.toEntity(draftFactorMapper.toDto(expected));
        assertDraftFactorAllPropertiesEquals(expected, actual);
    }
}
