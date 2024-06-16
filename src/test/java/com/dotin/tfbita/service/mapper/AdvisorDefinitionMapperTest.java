package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.AdvisorDefinitionAsserts.*;
import static com.dotin.tfbita.domain.AdvisorDefinitionTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdvisorDefinitionMapperTest {

    private AdvisorDefinitionMapper advisorDefinitionMapper;

    @BeforeEach
    void setUp() {
        advisorDefinitionMapper = new AdvisorDefinitionMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getAdvisorDefinitionSample1();
        var actual = advisorDefinitionMapper.toEntity(advisorDefinitionMapper.toDto(expected));
        assertAdvisorDefinitionAllPropertiesEquals(expected, actual);
    }
}
