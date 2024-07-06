package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class JustificationDeductionAmountPartTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static JustificationDeductionAmountPart getJustificationDeductionAmountPartSample1() {
        return new JustificationDeductionAmountPart()
            .id(1L)
            .receiveTransactionCode("receiveTransactionCode1")
            .returnTransactionCode("returnTransactionCode1")
            .date("date1")
            .depositNumber("depositNumber1")
            .receiveCurrencyCode("receiveCurrencyCode1")
            .currencyRateDate("currencyRateDate1")
            .comment("comment1");
    }

    public static JustificationDeductionAmountPart getJustificationDeductionAmountPartSample2() {
        return new JustificationDeductionAmountPart()
            .id(2L)
            .receiveTransactionCode("receiveTransactionCode2")
            .returnTransactionCode("returnTransactionCode2")
            .date("date2")
            .depositNumber("depositNumber2")
            .receiveCurrencyCode("receiveCurrencyCode2")
            .currencyRateDate("currencyRateDate2")
            .comment("comment2");
    }

    public static JustificationDeductionAmountPart getJustificationDeductionAmountPartRandomSampleGenerator() {
        return new JustificationDeductionAmountPart()
            .id(longCount.incrementAndGet())
            .receiveTransactionCode(UUID.randomUUID().toString())
            .returnTransactionCode(UUID.randomUUID().toString())
            .date(UUID.randomUUID().toString())
            .depositNumber(UUID.randomUUID().toString())
            .receiveCurrencyCode(UUID.randomUUID().toString())
            .currencyRateDate(UUID.randomUUID().toString())
            .comment(UUID.randomUUID().toString());
    }
}
