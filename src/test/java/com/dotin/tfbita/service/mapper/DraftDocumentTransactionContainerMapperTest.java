package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DraftDocumentTransactionContainerAsserts.*;
import static com.dotin.tfbita.domain.DraftDocumentTransactionContainerTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DraftDocumentTransactionContainerMapperTest {

    private DraftDocumentTransactionContainerMapper draftDocumentTransactionContainerMapper;

    @BeforeEach
    void setUp() {
        draftDocumentTransactionContainerMapper = new DraftDocumentTransactionContainerMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDraftDocumentTransactionContainerSample1();
        var actual = draftDocumentTransactionContainerMapper.toEntity(draftDocumentTransactionContainerMapper.toDto(expected));
        assertDraftDocumentTransactionContainerAllPropertiesEquals(expected, actual);
    }
}
