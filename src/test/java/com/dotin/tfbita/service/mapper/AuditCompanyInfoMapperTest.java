package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.AuditCompanyInfoAsserts.*;
import static com.dotin.tfbita.domain.AuditCompanyInfoTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuditCompanyInfoMapperTest {

    private AuditCompanyInfoMapper auditCompanyInfoMapper;

    @BeforeEach
    void setUp() {
        auditCompanyInfoMapper = new AuditCompanyInfoMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getAuditCompanyInfoSample1();
        var actual = auditCompanyInfoMapper.toEntity(auditCompanyInfoMapper.toDto(expected));
        assertAuditCompanyInfoAllPropertiesEquals(expected, actual);
    }
}
