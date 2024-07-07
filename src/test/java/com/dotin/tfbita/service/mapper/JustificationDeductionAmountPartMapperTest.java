package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.JustificationDeductionAmountPartAsserts.*;
import static com.dotin.tfbita.domain.JustificationDeductionAmountPartTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JustificationDeductionAmountPartMapperTest {

    private JustificationDeductionAmountPartMapper justificationDeductionAmountPartMapper;

    @BeforeEach
    void setUp() {
        justificationDeductionAmountPartMapper = new JustificationDeductionAmountPartMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getJustificationDeductionAmountPartSample1();
        var actual = justificationDeductionAmountPartMapper.toEntity(justificationDeductionAmountPartMapper.toDto(expected));
        assertJustificationDeductionAmountPartAllPropertiesEquals(expected, actual);
    }
}
