package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DocumentTransactionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DocumentTransaction getDocumentTransactionSample1() {
        return new DocumentTransaction().id(1L).currencyExchangeCode("currencyExchangeCode1").transactionCode("transactionCode1");
    }

    public static DocumentTransaction getDocumentTransactionSample2() {
        return new DocumentTransaction().id(2L).currencyExchangeCode("currencyExchangeCode2").transactionCode("transactionCode2");
    }

    public static DocumentTransaction getDocumentTransactionRandomSampleGenerator() {
        return new DocumentTransaction()
            .id(longCount.incrementAndGet())
            .currencyExchangeCode(UUID.randomUUID().toString())
            .transactionCode(UUID.randomUUID().toString());
    }
}
