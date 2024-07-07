package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.ServiceTariffAsserts.*;
import static com.dotin.tfbita.domain.ServiceTariffTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceTariffMapperTest {

    private ServiceTariffMapper serviceTariffMapper;

    @BeforeEach
    void setUp() {
        serviceTariffMapper = new ServiceTariffMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getServiceTariffSample1();
        var actual = serviceTariffMapper.toEntity(serviceTariffMapper.toDto(expected));
        assertServiceTariffAllPropertiesEquals(expected, actual);
    }
}
