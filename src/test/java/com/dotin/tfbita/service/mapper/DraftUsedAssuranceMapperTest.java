package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftUsedAssuranceAsserts.*;
import static com.dotin.tfbita.domain.DraftUsedAssuranceTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftUsedAssuranceMapperTest {

    private DraftUsedAssuranceMapper draftUsedAssuranceMapper;

    @BeforeEach
    void setUp() {
        draftUsedAssuranceMapper = new DraftUsedAssuranceMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftUsedAssuranceSample1();
        var actual = draftUsedAssuranceMapper.toEntity(draftUsedAssuranceMapper.toDto(expected));
        assertDraftUsedAssuranceAllPropertiesEquals(expected, actual);
    }
}
