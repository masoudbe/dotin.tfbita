package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DraftFactorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftFactor getDraftFactorSample1() {
        return new DraftFactor()
            .id(1L)
            .description("description1")
            .factorDate("factorDate1")
            .issueDate("issueDate1")
            .serial("serial1")
            .currencyName("currencyName1");
    }

    public static DraftFactor getDraftFactorSample2() {
        return new DraftFactor()
            .id(2L)
            .description("description2")
            .factorDate("factorDate2")
            .issueDate("issueDate2")
            .serial("serial2")
            .currencyName("currencyName2");
    }

    public static DraftFactor getDraftFactorRandomSampleGenerator() {
        return new DraftFactor()
            .id(longCount.incrementAndGet())
            .description(UUID.randomUUID().toString())
            .factorDate(UUID.randomUUID().toString())
            .issueDate(UUID.randomUUID().toString())
            .serial(UUID.randomUUID().toString())
            .currencyName(UUID.randomUUID().toString());
    }
}
