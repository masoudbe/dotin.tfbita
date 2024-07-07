package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class JustificationDeductionDetailTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static JustificationDeductionDetail getJustificationDeductionDetailSample1() {
        return new JustificationDeductionDetail().id(1L).receiveCurrencyCode("receiveCurrencyCode1").comment("comment1");
    }

    public static JustificationDeductionDetail getJustificationDeductionDetailSample2() {
        return new JustificationDeductionDetail().id(2L).receiveCurrencyCode("receiveCurrencyCode2").comment("comment2");
    }

    public static JustificationDeductionDetail getJustificationDeductionDetailRandomSampleGenerator() {
        return new JustificationDeductionDetail()
            .id(longCount.incrementAndGet())
            .receiveCurrencyCode(UUID.randomUUID().toString())
            .comment(UUID.randomUUID().toString());
    }
}
