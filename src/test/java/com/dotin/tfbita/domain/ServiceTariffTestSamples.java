package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceTariffTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ServiceTariff getServiceTariffSample1() {
        return new ServiceTariff().id(1L).code("code1").title("title1");
    }

    public static ServiceTariff getServiceTariffSample2() {
        return new ServiceTariff().id(2L).code("code2").title("title2");
    }

    public static ServiceTariff getServiceTariffRandomSampleGenerator() {
        return new ServiceTariff().id(longCount.incrementAndGet()).code(UUID.randomUUID().toString()).title(UUID.randomUUID().toString());
    }
}
