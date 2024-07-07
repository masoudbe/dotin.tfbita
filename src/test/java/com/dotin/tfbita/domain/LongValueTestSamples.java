package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class LongValueTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LongValue getLongValueSample1() {
        return new LongValue().id(1L).val(1L);
    }

    public static LongValue getLongValueSample2() {
        return new LongValue().id(2L).val(2L);
    }

    public static LongValue getLongValueRandomSampleGenerator() {
        return new LongValue().id(longCount.incrementAndGet()).val(longCount.incrementAndGet());
    }
}
