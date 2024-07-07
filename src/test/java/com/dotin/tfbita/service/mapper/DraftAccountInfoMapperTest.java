package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftAccountInfoAsserts.*;
import static com.dotin.tfbita.domain.DraftAccountInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftAccountInfoMapperTest {

    private DraftAccountInfoMapper draftAccountInfoMapper;

    @BeforeEach
    void setUp() {
        draftAccountInfoMapper = new DraftAccountInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftAccountInfoSample1();
        var actual = draftAccountInfoMapper.toEntity(draftAccountInfoMapper.toDto(expected));
        assertDraftAccountInfoAllPropertiesEquals(expected, actual);
    }
}
