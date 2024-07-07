package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BasicInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static BasicInfo getBasicInfoSample1() {
        return new BasicInfo().id(1L).applyDate("applyDate1").code("code1");
    }

    public static BasicInfo getBasicInfoSample2() {
        return new BasicInfo().id(2L).applyDate("applyDate2").code("code2");
    }

    public static BasicInfo getBasicInfoRandomSampleGenerator() {
        return new BasicInfo().id(longCount.incrementAndGet()).applyDate(UUID.randomUUID().toString()).code(UUID.randomUUID().toString());
    }
}
