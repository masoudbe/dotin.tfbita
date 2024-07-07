package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftCustomJustificationAsserts.*;
import static com.dotin.tfbita.domain.DraftCustomJustificationTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftCustomJustificationMapperTest {

    private DraftCustomJustificationMapper draftCustomJustificationMapper;

    @BeforeEach
    void setUp() {
        draftCustomJustificationMapper = new DraftCustomJustificationMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftCustomJustificationSample1();
        var actual = draftCustomJustificationMapper.toEntity(draftCustomJustificationMapper.toDto(expected));
        assertDraftCustomJustificationAllPropertiesEquals(expected, actual);
    }
}
