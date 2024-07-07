package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TradeTypeCodeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TradeTypeCode getTradeTypeCodeSample1() {
        return new TradeTypeCode().id(1L).latinName("latinName1").name("name1");
    }

    public static TradeTypeCode getTradeTypeCodeSample2() {
        return new TradeTypeCode().id(2L).latinName("latinName2").name("name2");
    }

    public static TradeTypeCode getTradeTypeCodeRandomSampleGenerator() {
        return new TradeTypeCode()
            .id(longCount.incrementAndGet())
            .latinName(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString());
    }
}
