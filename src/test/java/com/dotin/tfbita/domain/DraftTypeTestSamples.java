package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DraftTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DraftType getDraftTypeSample1() {
        return new DraftType()
            .id(1L)
            .alarmTime(1)
            .code("code1")
            .disableDate("disableDate1")
            .duration(1)
            .latestCreditSerial(1L)
            .name("name1")
            .defaultCurrencyCode("defaultCurrencyCode1")
            .accountInfoCode("accountInfoCode1")
            .topicInfoCode("topicInfoCode1");
    }

    public static DraftType getDraftTypeSample2() {
        return new DraftType()
            .id(2L)
            .alarmTime(2)
            .code("code2")
            .disableDate("disableDate2")
            .duration(2)
            .latestCreditSerial(2L)
            .name("name2")
            .defaultCurrencyCode("defaultCurrencyCode2")
            .accountInfoCode("accountInfoCode2")
            .topicInfoCode("topicInfoCode2");
    }

    public static DraftType getDraftTypeRandomSampleGenerator() {
        return new DraftType()
            .id(longCount.incrementAndGet())
            .alarmTime(intCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .disableDate(UUID.randomUUID().toString())
            .duration(intCount.incrementAndGet())
            .latestCreditSerial(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .defaultCurrencyCode(UUID.randomUUID().toString())
            .accountInfoCode(UUID.randomUUID().toString())
            .topicInfoCode(UUID.randomUUID().toString());
    }
}
