package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.SuggestedSanctionInfoAsserts.*;
import static com.dotin.tfbita.domain.SuggestedSanctionInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SuggestedSanctionInfoMapperTest {

    private SuggestedSanctionInfoMapper suggestedSanctionInfoMapper;

    @BeforeEach
    void setUp() {
        suggestedSanctionInfoMapper = new SuggestedSanctionInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSuggestedSanctionInfoSample1();
        var actual = suggestedSanctionInfoMapper.toEntity(suggestedSanctionInfoMapper.toDto(expected));
        assertSuggestedSanctionInfoAllPropertiesEquals(expected, actual);
    }
}
