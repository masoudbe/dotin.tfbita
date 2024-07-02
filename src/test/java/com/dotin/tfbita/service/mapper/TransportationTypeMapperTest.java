package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.TransportationTypeAsserts.*;
import static com.dotin.tfbita.domain.TransportationTypeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransportationTypeMapperTest {

    private TransportationTypeMapper transportationTypeMapper;

    @BeforeEach
    void setUp() {
        transportationTypeMapper = new TransportationTypeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTransportationTypeSample1();
        var actual = transportationTypeMapper.toEntity(transportationTypeMapper.toDto(expected));
        assertTransportationTypeAllPropertiesEquals(expected, actual);
    }
}
