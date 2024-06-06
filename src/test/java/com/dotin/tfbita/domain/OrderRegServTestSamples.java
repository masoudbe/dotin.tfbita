package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OrderRegServTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OrderRegServ getOrderRegServSample1() {
        return new OrderRegServ().id(1L).unit("unit1").title("title1").code("code1");
    }

    public static OrderRegServ getOrderRegServSample2() {
        return new OrderRegServ().id(2L).unit("unit2").title("title2").code("code2");
    }

    public static OrderRegServ getOrderRegServRandomSampleGenerator() {
        return new OrderRegServ()
            .id(longCount.incrementAndGet())
            .unit(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString());
    }
}
