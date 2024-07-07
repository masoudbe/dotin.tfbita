package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DraftProductInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftProductInfo getDraftProductInfoSample1() {
        return new DraftProductInfo().id(1L).productAmount("productAmount1").productDimension("productDimension1");
    }

    public static DraftProductInfo getDraftProductInfoSample2() {
        return new DraftProductInfo().id(2L).productAmount("productAmount2").productDimension("productDimension2");
    }

    public static DraftProductInfo getDraftProductInfoRandomSampleGenerator() {
        return new DraftProductInfo()
            .id(longCount.incrementAndGet())
            .productAmount(UUID.randomUUID().toString())
            .productDimension(UUID.randomUUID().toString());
    }
}
