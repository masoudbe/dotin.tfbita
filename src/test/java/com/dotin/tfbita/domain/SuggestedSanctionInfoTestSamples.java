package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SuggestedSanctionInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static SuggestedSanctionInfo getSuggestedSanctionInfoSample1() {
        return new SuggestedSanctionInfo().id(1L).sanctionSerial("sanctionSerial1").personnelCode("personnelCode1");
    }

    public static SuggestedSanctionInfo getSuggestedSanctionInfoSample2() {
        return new SuggestedSanctionInfo().id(2L).sanctionSerial("sanctionSerial2").personnelCode("personnelCode2");
    }

    public static SuggestedSanctionInfo getSuggestedSanctionInfoRandomSampleGenerator() {
        return new SuggestedSanctionInfo()
            .id(longCount.incrementAndGet())
            .sanctionSerial(UUID.randomUUID().toString())
            .personnelCode(UUID.randomUUID().toString());
    }
}
