package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftTaxAsserts.*;
import static com.dotin.tfbita.domain.DraftTaxTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftTaxMapperTest {

    private DraftTaxMapper draftTaxMapper;

    @BeforeEach
    void setUp() {
        draftTaxMapper = new DraftTaxMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftTaxSample1();
        var actual = draftTaxMapper.toEntity(draftTaxMapper.toDto(expected));
        assertDraftTaxAllPropertiesEquals(expected, actual);
    }
}
