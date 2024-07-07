package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CurrencyExchangeInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CurrencyExchangeInfo getCurrencyExchangeInfoSample1() {
        return new CurrencyExchangeInfo().id(1L).title("title1");
    }

    public static CurrencyExchangeInfo getCurrencyExchangeInfoSample2() {
        return new CurrencyExchangeInfo().id(2L).title("title2");
    }

    public static CurrencyExchangeInfo getCurrencyExchangeInfoRandomSampleGenerator() {
        return new CurrencyExchangeInfo().id(longCount.incrementAndGet()).title(UUID.randomUUID().toString());
    }
}
