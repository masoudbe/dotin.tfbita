package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.JustificationDeductionAmountAsserts.*;
import static com.dotin.tfbita.domain.JustificationDeductionAmountTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JustificationDeductionAmountMapperTest {

    private JustificationDeductionAmountMapper justificationDeductionAmountMapper;

    @BeforeEach
    void setUp() {
        justificationDeductionAmountMapper = new JustificationDeductionAmountMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getJustificationDeductionAmountSample1();
        var actual = justificationDeductionAmountMapper.toEntity(justificationDeductionAmountMapper.toDto(expected));
        assertJustificationDeductionAmountAllPropertiesEquals(expected, actual);
    }
}
