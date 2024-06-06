package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PurchaseFromOtherResourcesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static PurchaseFromOtherResources getPurchaseFromOtherResourcesSample1() {
        return new PurchaseFromOtherResources()
            .id(1L)
            .evidenceCode("evidenceCode1")
            .currencySupplierDescription("currencySupplierDescription1")
            .requestDate("requestDate1")
            .confirmationDate("confirmationDate1")
            .description("description1")
            .purchaseNumber("purchaseNumber1")
            .purchaseCurrencyName("purchaseCurrencyName1");
    }

    public static PurchaseFromOtherResources getPurchaseFromOtherResourcesSample2() {
        return new PurchaseFromOtherResources()
            .id(2L)
            .evidenceCode("evidenceCode2")
            .currencySupplierDescription("currencySupplierDescription2")
            .requestDate("requestDate2")
            .confirmationDate("confirmationDate2")
            .description("description2")
            .purchaseNumber("purchaseNumber2")
            .purchaseCurrencyName("purchaseCurrencyName2");
    }

    public static PurchaseFromOtherResources getPurchaseFromOtherResourcesRandomSampleGenerator() {
        return new PurchaseFromOtherResources()
            .id(longCount.incrementAndGet())
            .evidenceCode(UUID.randomUUID().toString())
            .currencySupplierDescription(UUID.randomUUID().toString())
            .requestDate(UUID.randomUUID().toString())
            .confirmationDate(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .purchaseNumber(UUID.randomUUID().toString())
            .purchaseCurrencyName(UUID.randomUUID().toString());
    }
}
