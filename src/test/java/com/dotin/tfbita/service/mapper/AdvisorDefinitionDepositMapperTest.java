package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.AdvisorDefinitionDepositAsserts.*;
import static com.dotin.tfbita.domain.AdvisorDefinitionDepositTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdvisorDefinitionDepositMapperTest {

    private AdvisorDefinitionDepositMapper advisorDefinitionDepositMapper;

    @BeforeEach
    void setUp() {
        advisorDefinitionDepositMapper = new AdvisorDefinitionDepositMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getAdvisorDefinitionDepositSample1();
        var actual = advisorDefinitionDepositMapper.toEntity(advisorDefinitionDepositMapper.toDto(expected));
        assertAdvisorDefinitionDepositAllPropertiesEquals(expected, actual);
    }
}
