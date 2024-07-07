package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.AdditionalBrokerInformationAsserts.*;
import static com.dotin.tfbita.domain.AdditionalBrokerInformationTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdditionalBrokerInformationMapperTest {

    private AdditionalBrokerInformationMapper additionalBrokerInformationMapper;

    @BeforeEach
    void setUp() {
        additionalBrokerInformationMapper = new AdditionalBrokerInformationMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getAdditionalBrokerInformationSample1();
        var actual = additionalBrokerInformationMapper.toEntity(additionalBrokerInformationMapper.toDto(expected));
        assertAdditionalBrokerInformationAllPropertiesEquals(expected, actual);
    }
}
