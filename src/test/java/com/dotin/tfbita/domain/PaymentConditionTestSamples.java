package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PaymentConditionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static PaymentCondition getPaymentConditionSample1() {
        return new PaymentCondition().id(1L).latinName("latinName1").name("name1");
    }

    public static PaymentCondition getPaymentConditionSample2() {
        return new PaymentCondition().id(2L).latinName("latinName2").name("name2");
    }

    public static PaymentCondition getPaymentConditionRandomSampleGenerator() {
        return new PaymentCondition()
            .id(longCount.incrementAndGet())
            .latinName(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString());
    }
}
