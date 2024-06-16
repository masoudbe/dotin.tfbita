package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DraftUsedAssuranceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftUsedAssurance getDraftUsedAssuranceSample1() {
        return new DraftUsedAssurance().id(1L).assuranceRateId("assuranceRateId1").assuranceSerial("assuranceSerial1");
    }

    public static DraftUsedAssurance getDraftUsedAssuranceSample2() {
        return new DraftUsedAssurance().id(2L).assuranceRateId("assuranceRateId2").assuranceSerial("assuranceSerial2");
    }

    public static DraftUsedAssurance getDraftUsedAssuranceRandomSampleGenerator() {
        return new DraftUsedAssurance()
            .id(longCount.incrementAndGet())
            .assuranceRateId(UUID.randomUUID().toString())
            .assuranceSerial(UUID.randomUUID().toString());
    }
}
