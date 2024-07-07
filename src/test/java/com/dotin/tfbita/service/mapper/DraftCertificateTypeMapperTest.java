package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftCertificateTypeAsserts.*;
import static com.dotin.tfbita.domain.DraftCertificateTypeTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftCertificateTypeMapperTest {

    private DraftCertificateTypeMapper draftCertificateTypeMapper;

    @BeforeEach
    void setUp() {
        draftCertificateTypeMapper = new DraftCertificateTypeMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftCertificateTypeSample1();
        var actual = draftCertificateTypeMapper.toEntity(draftCertificateTypeMapper.toDto(expected));
        assertDraftCertificateTypeAllPropertiesEquals(expected, actual);
    }
}
