package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftProductInfoAsserts.*;
import static com.dotin.tfbita.domain.DraftProductInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftProductInfoMapperTest {

    private DraftProductInfoMapper draftProductInfoMapper;

    @BeforeEach
    void setUp() {
        draftProductInfoMapper = new DraftProductInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftProductInfoSample1();
        var actual = draftProductInfoMapper.toEntity(draftProductInfoMapper.toDto(expected));
        assertDraftProductInfoAllPropertiesEquals(expected, actual);
    }
}
