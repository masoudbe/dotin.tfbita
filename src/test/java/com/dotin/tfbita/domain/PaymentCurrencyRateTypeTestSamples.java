package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PaymentCurrencyRateTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static PaymentCurrencyRateType getPaymentCurrencyRateTypeSample1() {
        return new PaymentCurrencyRateType().id(1L).description("description1");
    }

    public static PaymentCurrencyRateType getPaymentCurrencyRateTypeSample2() {
        return new PaymentCurrencyRateType().id(2L).description("description2");
    }

    public static PaymentCurrencyRateType getPaymentCurrencyRateTypeRandomSampleGenerator() {
        return new PaymentCurrencyRateType().id(longCount.incrementAndGet()).description(UUID.randomUUID().toString());
    }
}
