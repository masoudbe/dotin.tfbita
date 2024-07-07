package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AdvisorDefinitionDepositTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AdvisorDefinitionDeposit getAdvisorDefinitionDepositSample1() {
        return new AdvisorDefinitionDeposit()
            .id(1L)
            .advisorDepositNumber("advisorDepositNumber1")
            .depositNum("depositNum1")
            .swiftCode("swiftCode1")
            .currencyCode("currencyCode1");
    }

    public static AdvisorDefinitionDeposit getAdvisorDefinitionDepositSample2() {
        return new AdvisorDefinitionDeposit()
            .id(2L)
            .advisorDepositNumber("advisorDepositNumber2")
            .depositNum("depositNum2")
            .swiftCode("swiftCode2")
            .currencyCode("currencyCode2");
    }

    public static AdvisorDefinitionDeposit getAdvisorDefinitionDepositRandomSampleGenerator() {
        return new AdvisorDefinitionDeposit()
            .id(longCount.incrementAndGet())
            .advisorDepositNumber(UUID.randomUUID().toString())
            .depositNum(UUID.randomUUID().toString())
            .swiftCode(UUID.randomUUID().toString())
            .currencyCode(UUID.randomUUID().toString());
    }
}
