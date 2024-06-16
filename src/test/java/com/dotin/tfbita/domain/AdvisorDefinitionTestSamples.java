package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AdvisorDefinitionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AdvisorDefinition getAdvisorDefinitionSample1() {
        return new AdvisorDefinition()
            .id(1L)
            .caption("caption1")
            .code("code1")
            .countryIsoCode("countryIsoCode1")
            .depositNum("depositNum1")
            .swiftCode("swiftCode1")
            .creditDate("creditDate1")
            .bankCode("bankCode1")
            .branchCode("branchCode1")
            .defaultCurrencyCode("defaultCurrencyCode1")
            .countryCode("countryCode1");
    }

    public static AdvisorDefinition getAdvisorDefinitionSample2() {
        return new AdvisorDefinition()
            .id(2L)
            .caption("caption2")
            .code("code2")
            .countryIsoCode("countryIsoCode2")
            .depositNum("depositNum2")
            .swiftCode("swiftCode2")
            .creditDate("creditDate2")
            .bankCode("bankCode2")
            .branchCode("branchCode2")
            .defaultCurrencyCode("defaultCurrencyCode2")
            .countryCode("countryCode2");
    }

    public static AdvisorDefinition getAdvisorDefinitionRandomSampleGenerator() {
        return new AdvisorDefinition()
            .id(longCount.incrementAndGet())
            .caption(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString())
            .countryIsoCode(UUID.randomUUID().toString())
            .depositNum(UUID.randomUUID().toString())
            .swiftCode(UUID.randomUUID().toString())
            .creditDate(UUID.randomUUID().toString())
            .bankCode(UUID.randomUUID().toString())
            .branchCode(UUID.randomUUID().toString())
            .defaultCurrencyCode(UUID.randomUUID().toString())
            .countryCode(UUID.randomUUID().toString());
    }
}
