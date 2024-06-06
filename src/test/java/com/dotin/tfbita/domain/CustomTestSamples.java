package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CustomTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Custom getCustomSample1() {
        return new Custom().id(1L).modificationDate("modificationDate1").latinName("latinName1").name("name1").tempId(1L);
    }

    public static Custom getCustomSample2() {
        return new Custom().id(2L).modificationDate("modificationDate2").latinName("latinName2").name("name2").tempId(2L);
    }

    public static Custom getCustomRandomSampleGenerator() {
        return new Custom()
            .id(longCount.incrementAndGet())
            .modificationDate(UUID.randomUUID().toString())
            .latinName(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .tempId(longCount.incrementAndGet());
    }
}
