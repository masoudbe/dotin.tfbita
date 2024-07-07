package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PaymentItemTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static PaymentItemType getPaymentItemTypeSample1() {
        return new PaymentItemType().id(1L).description("description1");
    }

    public static PaymentItemType getPaymentItemTypeSample2() {
        return new PaymentItemType().id(2L).description("description2");
    }

    public static PaymentItemType getPaymentItemTypeRandomSampleGenerator() {
        return new PaymentItemType().id(longCount.incrementAndGet()).description(UUID.randomUUID().toString());
    }
}
