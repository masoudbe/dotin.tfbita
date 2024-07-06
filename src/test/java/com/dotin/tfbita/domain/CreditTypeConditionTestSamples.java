package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CreditTypeConditionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CreditTypeCondition getCreditTypeConditionSample1() {
        return new CreditTypeCondition()
            .id(1L)
            .durationFrom(1)
            .durationTo(1)
            .suspensionDurationFrom(1)
            .suspensionDurationTo(1)
            .currencyCode("currencyCode1");
    }

    public static CreditTypeCondition getCreditTypeConditionSample2() {
        return new CreditTypeCondition()
            .id(2L)
            .durationFrom(2)
            .durationTo(2)
            .suspensionDurationFrom(2)
            .suspensionDurationTo(2)
            .currencyCode("currencyCode2");
    }

    public static CreditTypeCondition getCreditTypeConditionRandomSampleGenerator() {
        return new CreditTypeCondition()
            .id(longCount.incrementAndGet())
            .durationFrom(intCount.incrementAndGet())
            .durationTo(intCount.incrementAndGet())
            .suspensionDurationFrom(intCount.incrementAndGet())
            .suspensionDurationTo(intCount.incrementAndGet())
            .currencyCode(UUID.randomUUID().toString());
    }
}
