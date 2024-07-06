package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftTypeAccountInfoAsserts.*;
import static com.dotin.tfbita.domain.DraftTypeAccountInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftTypeAccountInfoMapperTest {

    private DraftTypeAccountInfoMapper draftTypeAccountInfoMapper;

    @BeforeEach
    void setUp() {
        draftTypeAccountInfoMapper = new DraftTypeAccountInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftTypeAccountInfoSample1();
        var actual = draftTypeAccountInfoMapper.toEntity(draftTypeAccountInfoMapper.toDto(expected));
        assertDraftTypeAccountInfoAllPropertiesEquals(expected, actual);
    }
}
