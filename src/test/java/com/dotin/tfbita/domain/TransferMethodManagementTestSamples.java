package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TransferMethodManagementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TransferMethodManagement getTransferMethodManagementSample1() {
        return new TransferMethodManagement().id(1L).code("code1").desc("desc1");
    }

    public static TransferMethodManagement getTransferMethodManagementSample2() {
        return new TransferMethodManagement().id(2L).code("code2").desc("desc2");
    }

    public static TransferMethodManagement getTransferMethodManagementRandomSampleGenerator() {
        return new TransferMethodManagement()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .desc(UUID.randomUUID().toString());
    }
}
