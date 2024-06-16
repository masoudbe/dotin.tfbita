package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DraftExtendTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DraftExtend getDraftExtendSample1() {
        return new DraftExtend().id(1L).date("date1").duration(1).time("time1");
    }

    public static DraftExtend getDraftExtendSample2() {
        return new DraftExtend().id(2L).date("date2").duration(2).time("time2");
    }

    public static DraftExtend getDraftExtendRandomSampleGenerator() {
        return new DraftExtend()
            .id(longCount.incrementAndGet())
            .date(UUID.randomUUID().toString())
            .duration(intCount.incrementAndGet())
            .time(UUID.randomUUID().toString());
    }
}
