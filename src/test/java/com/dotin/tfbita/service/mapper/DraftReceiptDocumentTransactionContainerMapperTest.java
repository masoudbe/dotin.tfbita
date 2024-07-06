package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainerAsserts.*;
import static com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainerTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftReceiptDocumentTransactionContainerMapperTest {

    private DraftReceiptDocumentTransactionContainerMapper draftReceiptDocumentTransactionContainerMapper;

    @BeforeEach
    void setUp() {
        draftReceiptDocumentTransactionContainerMapper = new DraftReceiptDocumentTransactionContainerMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftReceiptDocumentTransactionContainerSample1();
        var actual = draftReceiptDocumentTransactionContainerMapper.toEntity(
            draftReceiptDocumentTransactionContainerMapper.toDto(expected)
        );
        assertDraftReceiptDocumentTransactionContainerAllPropertiesEquals(expected, actual);
    }
}
