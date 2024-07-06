package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryElementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CategoryElement getCategoryElementSample1() {
        return new CategoryElement().id(1L).val("val1").categoryName("categoryName1").code("code1");
    }

    public static CategoryElement getCategoryElementSample2() {
        return new CategoryElement().id(2L).val("val2").categoryName("categoryName2").code("code2");
    }

    public static CategoryElement getCategoryElementRandomSampleGenerator() {
        return new CategoryElement()
            .id(longCount.incrementAndGet())
            .val(UUID.randomUUID().toString())
            .categoryName(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString());
    }
}
