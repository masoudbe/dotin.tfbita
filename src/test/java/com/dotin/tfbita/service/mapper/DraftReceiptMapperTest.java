package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftReceiptAsserts.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftReceiptMapperTest {

    private DraftReceiptMapper draftReceiptMapper;

    @BeforeEach
    void setUp() {
        draftReceiptMapper = new DraftReceiptMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftReceiptSample1();
        var actual = draftReceiptMapper.toEntity(draftReceiptMapper.toDto(expected));
        assertDraftReceiptAllPropertiesEquals(expected, actual);
    }
}
