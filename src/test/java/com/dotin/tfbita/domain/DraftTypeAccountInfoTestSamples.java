package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DraftTypeAccountInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftTypeAccountInfo getDraftTypeAccountInfoSample1() {
        return new DraftTypeAccountInfo()
            .id(1L)
            .sellCurrencyCommissionAccount("sellCurrencyCommissionAccount1")
            .incomeAccountNumber("incomeAccountNumber1");
    }

    public static DraftTypeAccountInfo getDraftTypeAccountInfoSample2() {
        return new DraftTypeAccountInfo()
            .id(2L)
            .sellCurrencyCommissionAccount("sellCurrencyCommissionAccount2")
            .incomeAccountNumber("incomeAccountNumber2");
    }

    public static DraftTypeAccountInfo getDraftTypeAccountInfoRandomSampleGenerator() {
        return new DraftTypeAccountInfo()
            .id(longCount.incrementAndGet())
            .sellCurrencyCommissionAccount(UUID.randomUUID().toString())
            .incomeAccountNumber(UUID.randomUUID().toString());
    }
}
