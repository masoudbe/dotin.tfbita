package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.JustificationDeductionDetailAsserts.*;
import static com.dotin.tfbita.domain.JustificationDeductionDetailTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JustificationDeductionDetailMapperTest {

    private JustificationDeductionDetailMapper justificationDeductionDetailMapper;

    @BeforeEach
    void setUp() {
        justificationDeductionDetailMapper = new JustificationDeductionDetailMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getJustificationDeductionDetailSample1();
        var actual = justificationDeductionDetailMapper.toEntity(justificationDeductionDetailMapper.toDto(expected));
        assertJustificationDeductionDetailAllPropertiesEquals(expected, actual);
    }
}
