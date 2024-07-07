package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DraftStatusInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftStatusInfo getDraftStatusInfoSample1() {
        return new DraftStatusInfo().id(1L).rejectDescription("rejectDescription1");
    }

    public static DraftStatusInfo getDraftStatusInfoSample2() {
        return new DraftStatusInfo().id(2L).rejectDescription("rejectDescription2");
    }

    public static DraftStatusInfo getDraftStatusInfoRandomSampleGenerator() {
        return new DraftStatusInfo().id(longCount.incrementAndGet()).rejectDescription(UUID.randomUUID().toString());
    }
}
