package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.LicenceInfoAsserts.*;
import static com.dotin.tfbita.domain.LicenceInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LicenceInfoMapperTest {

    private LicenceInfoMapper licenceInfoMapper;

    @BeforeEach
    void setUp() {
        licenceInfoMapper = new LicenceInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLicenceInfoSample1();
        var actual = licenceInfoMapper.toEntity(licenceInfoMapper.toDto(expected));
        assertLicenceInfoAllPropertiesEquals(expected, actual);
    }
}
