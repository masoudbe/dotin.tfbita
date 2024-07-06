package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.TransferMethodManagementAsserts.*;
import static com.dotin.tfbita.domain.TransferMethodManagementTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransferMethodManagementMapperTest {

    private TransferMethodManagementMapper transferMethodManagementMapper;

    @BeforeEach
    void setUp() {
        transferMethodManagementMapper = new TransferMethodManagementMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTransferMethodManagementSample1();
        var actual = transferMethodManagementMapper.toEntity(transferMethodManagementMapper.toDto(expected));
        assertTransferMethodManagementAllPropertiesEquals(expected, actual);
    }
}
