package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ObjectiveCategoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ObjectiveCategory getObjectiveCategorySample1() {
        return new ObjectiveCategory().id(1L).name("name1");
    }

    public static ObjectiveCategory getObjectiveCategorySample2() {
        return new ObjectiveCategory().id(2L).name("name2");
    }

    public static ObjectiveCategory getObjectiveCategoryRandomSampleGenerator() {
        return new ObjectiveCategory().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
