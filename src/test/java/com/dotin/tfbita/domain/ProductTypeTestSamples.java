package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProductTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ProductType getProductTypeSample1() {
        return new ProductType().id(1L).description("description1").modificationDate("modificationDate1").topicCode("topicCode1");
    }

    public static ProductType getProductTypeSample2() {
        return new ProductType().id(2L).description("description2").modificationDate("modificationDate2").topicCode("topicCode2");
    }

    public static ProductType getProductTypeRandomSampleGenerator() {
        return new ProductType()
            .id(longCount.incrementAndGet())
            .description(UUID.randomUUID().toString())
            .modificationDate(UUID.randomUUID().toString())
            .topicCode(UUID.randomUUID().toString());
    }
}
