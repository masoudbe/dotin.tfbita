package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StringValueTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static StringValue getStringValueSample1() {
        return new StringValue().id(1L).val("val1");
    }

    public static StringValue getStringValueSample2() {
        return new StringValue().id(2L).val("val2");
    }

    public static StringValue getStringValueRandomSampleGenerator() {
        return new StringValue().id(longCount.incrementAndGet()).val(UUID.randomUUID().toString());
    }
}
