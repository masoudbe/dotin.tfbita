package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftTypeTopicInfoAsserts.*;
import static com.dotin.tfbita.domain.DraftTypeTopicInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftTypeTopicInfoMapperTest {

    private DraftTypeTopicInfoMapper draftTypeTopicInfoMapper;

    @BeforeEach
    void setUp() {
        draftTypeTopicInfoMapper = new DraftTypeTopicInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftTypeTopicInfoSample1();
        var actual = draftTypeTopicInfoMapper.toEntity(draftTypeTopicInfoMapper.toDto(expected));
        assertDraftTypeTopicInfoAllPropertiesEquals(expected, actual);
    }
}
