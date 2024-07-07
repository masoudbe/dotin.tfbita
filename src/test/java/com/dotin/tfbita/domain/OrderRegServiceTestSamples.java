package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OrderRegServiceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OrderRegService getOrderRegServiceSample1() {
        return new OrderRegService().id(1L).unit("unit1");
    }

    public static OrderRegService getOrderRegServiceSample2() {
        return new OrderRegService().id(2L).unit("unit2");
    }

    public static OrderRegService getOrderRegServiceRandomSampleGenerator() {
        return new OrderRegService().id(longCount.incrementAndGet()).unit(UUID.randomUUID().toString());
    }
}
