package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class TypeAttributeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TypeAttribute getTypeAttributeSample1() {
        return new TypeAttribute().id(1L);
    }

    public static TypeAttribute getTypeAttributeSample2() {
        return new TypeAttribute().id(2L);
    }

    public static TypeAttribute getTypeAttributeRandomSampleGenerator() {
        return new TypeAttribute().id(longCount.incrementAndGet());
    }
}
