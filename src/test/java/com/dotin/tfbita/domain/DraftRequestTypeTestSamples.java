package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DraftRequestTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftRequestType getDraftRequestTypeSample1() {
        return new DraftRequestType().id(1L);
    }

    public static DraftRequestType getDraftRequestTypeSample2() {
        return new DraftRequestType().id(2L);
    }

    public static DraftRequestType getDraftRequestTypeRandomSampleGenerator() {
        return new DraftRequestType().id(longCount.incrementAndGet());
    }
}
