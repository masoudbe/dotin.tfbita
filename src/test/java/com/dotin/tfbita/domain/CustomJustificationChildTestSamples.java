package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CustomJustificationChildTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CustomJustificationChild getCustomJustificationChildSample1() {
        return new CustomJustificationChild().id(1L).item("item1").tariffCode("tariffCode1").productId(1L).boxType("boxType1");
    }

    public static CustomJustificationChild getCustomJustificationChildSample2() {
        return new CustomJustificationChild().id(2L).item("item2").tariffCode("tariffCode2").productId(2L).boxType("boxType2");
    }

    public static CustomJustificationChild getCustomJustificationChildRandomSampleGenerator() {
        return new CustomJustificationChild()
            .id(longCount.incrementAndGet())
            .item(UUID.randomUUID().toString())
            .tariffCode(UUID.randomUUID().toString())
            .productId(longCount.incrementAndGet())
            .boxType(UUID.randomUUID().toString());
    }
}
