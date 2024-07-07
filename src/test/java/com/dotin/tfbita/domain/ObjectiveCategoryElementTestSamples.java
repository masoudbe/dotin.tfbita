package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ObjectiveCategoryElementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ObjectiveCategoryElement getObjectiveCategoryElementSample1() {
        return new ObjectiveCategoryElement().id(1L).entityClass("entityClass1").entityId(1L);
    }

    public static ObjectiveCategoryElement getObjectiveCategoryElementSample2() {
        return new ObjectiveCategoryElement().id(2L).entityClass("entityClass2").entityId(2L);
    }

    public static ObjectiveCategoryElement getObjectiveCategoryElementRandomSampleGenerator() {
        return new ObjectiveCategoryElement()
            .id(longCount.incrementAndGet())
            .entityClass(UUID.randomUUID().toString())
            .entityId(longCount.incrementAndGet());
    }
}
