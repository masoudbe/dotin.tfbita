package com.dotin.tfbita.service.mapper;

import static com.dotin.tfbita.domain.PurchaseFromOtherResourcesAsserts.*;
import static com.dotin.tfbita.domain.PurchaseFromOtherResourcesTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseFromOtherResourcesMapperTest {

    private PurchaseFromOtherResourcesMapper purchaseFromOtherResourcesMapper;

    @BeforeEach
    void setUp() {
        purchaseFromOtherResourcesMapper = new PurchaseFromOtherResourcesMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPurchaseFromOtherResourcesSample1();
        var actual = purchaseFromOtherResourcesMapper.toEntity(purchaseFromOtherResourcesMapper.toDto(expected));
        assertPurchaseFromOtherResourcesAllPropertiesEquals(expected, actual);
    }
}
