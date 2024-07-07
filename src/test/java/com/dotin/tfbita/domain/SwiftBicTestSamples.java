package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SwiftBicTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static SwiftBic getSwiftBicSample1() {
        return new SwiftBic()
            .id(1L)
            .address("address1")
            .address2("address21")
            .address3("address31")
            .address4("address41")
            .bank("bank1")
            .bankName("bankName1")
            .bankName2("bankName21")
            .bankName3("bankName31")
            .branch("branch1")
            .branchName("branchName1")
            .branchName2("branchName21")
            .city("city1")
            .country("country1")
            .location("location1")
            .subTypeIndicator("subTypeIndicator1")
            .zip("zip1");
    }

    public static SwiftBic getSwiftBicSample2() {
        return new SwiftBic()
            .id(2L)
            .address("address2")
            .address2("address22")
            .address3("address32")
            .address4("address42")
            .bank("bank2")
            .bankName("bankName2")
            .bankName2("bankName22")
            .bankName3("bankName32")
            .branch("branch2")
            .branchName("branchName2")
            .branchName2("branchName22")
            .city("city2")
            .country("country2")
            .location("location2")
            .subTypeIndicator("subTypeIndicator2")
            .zip("zip2");
    }

    public static SwiftBic getSwiftBicRandomSampleGenerator() {
        return new SwiftBic()
            .id(longCount.incrementAndGet())
            .address(UUID.randomUUID().toString())
            .address2(UUID.randomUUID().toString())
            .address3(UUID.randomUUID().toString())
            .address4(UUID.randomUUID().toString())
            .bank(UUID.randomUUID().toString())
            .bankName(UUID.randomUUID().toString())
            .bankName2(UUID.randomUUID().toString())
            .bankName3(UUID.randomUUID().toString())
            .branch(UUID.randomUUID().toString())
            .branchName(UUID.randomUUID().toString())
            .branchName2(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .country(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString())
            .subTypeIndicator(UUID.randomUUID().toString())
            .zip(UUID.randomUUID().toString());
    }
}
