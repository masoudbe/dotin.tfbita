package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.DocumentTransactionAsserts.*;
import static com.dotin.tfbita.domain.DocumentTransactionTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DocumentTransactionMapperTest {

    private DocumentTransactionMapper documentTransactionMapper;

    @BeforeEach
    void setUp() {
        documentTransactionMapper = new DocumentTransactionMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDocumentTransactionSample1();
        var actual = documentTransactionMapper.toEntity(documentTransactionMapper.toDto(expected));
        assertDocumentTransactionAllPropertiesEquals(expected, actual);
    }
}
