package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftStatusInfoAsserts.*;
import static com.dotin.tfbita.domain.DraftStatusInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftStatusInfoMapperTest {

    private DraftStatusInfoMapper draftStatusInfoMapper;

    @BeforeEach
    void setUp() {
        draftStatusInfoMapper = new DraftStatusInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftStatusInfoSample1();
        var actual = draftStatusInfoMapper.toEntity(draftStatusInfoMapper.toDto(expected));
        assertDraftStatusInfoAllPropertiesEquals(expected, actual);
    }
}
