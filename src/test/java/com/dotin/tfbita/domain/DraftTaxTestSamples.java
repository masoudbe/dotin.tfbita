package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DraftTaxTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftTax getDraftTaxSample1() {
        return new DraftTax().id(1L).letterNumber("letterNumber1").description("description1").registrationDate("registrationDate1");
    }

    public static DraftTax getDraftTaxSample2() {
        return new DraftTax().id(2L).letterNumber("letterNumber2").description("description2").registrationDate("registrationDate2");
    }

    public static DraftTax getDraftTaxRandomSampleGenerator() {
        return new DraftTax()
            .id(longCount.incrementAndGet())
            .letterNumber(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .registrationDate(UUID.randomUUID().toString());
    }
}
