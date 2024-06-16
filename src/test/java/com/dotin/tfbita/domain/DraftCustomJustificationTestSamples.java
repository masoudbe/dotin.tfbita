package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DraftCustomJustificationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftCustomJustification getDraftCustomJustificationSample1() {
        return new DraftCustomJustification().id(1L);
    }

    public static DraftCustomJustification getDraftCustomJustificationSample2() {
        return new DraftCustomJustification().id(2L);
    }

    public static DraftCustomJustification getDraftCustomJustificationRandomSampleGenerator() {
        return new DraftCustomJustification().id(longCount.incrementAndGet());
    }
}
