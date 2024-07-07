package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TransportationTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TransportationType getTransportationTypeSample1() {
        return new TransportationType().id(1L).latinName("latinName1").modificationDate("modificationDate1").name("name1");
    }

    public static TransportationType getTransportationTypeSample2() {
        return new TransportationType().id(2L).latinName("latinName2").modificationDate("modificationDate2").name("name2");
    }

    public static TransportationType getTransportationTypeRandomSampleGenerator() {
        return new TransportationType()
            .id(longCount.incrementAndGet())
            .latinName(UUID.randomUUID().toString())
            .modificationDate(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString());
    }
}
