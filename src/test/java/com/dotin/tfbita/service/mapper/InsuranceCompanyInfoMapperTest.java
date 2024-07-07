package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.InsuranceCompanyInfoAsserts.*;
import static com.dotin.tfbita.domain.InsuranceCompanyInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsuranceCompanyInfoMapperTest {

    private InsuranceCompanyInfoMapper insuranceCompanyInfoMapper;

    @BeforeEach
    void setUp() {
        insuranceCompanyInfoMapper = new InsuranceCompanyInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getInsuranceCompanyInfoSample1();
        var actual = insuranceCompanyInfoMapper.toEntity(insuranceCompanyInfoMapper.toDto(expected));
        assertInsuranceCompanyInfoAllPropertiesEquals(expected, actual);
    }
}
