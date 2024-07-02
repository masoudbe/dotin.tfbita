package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StringValueTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static StringValue getStringValueSample1() {
        return new StringValue().id(1L).value("value1");
    }

    public static StringValue getStringValueSample2() {
        return new StringValue().id(2L).value("value2");
    }

    public static StringValue getStringValueRandomSampleGenerator() {
        return new StringValue().id(longCount.incrementAndGet()).value(UUID.randomUUID().toString());
    }
}
