package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class JustificationDeductionAmountTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static JustificationDeductionAmount getJustificationDeductionAmountSample1() {
        return new JustificationDeductionAmount().id(1L);
    }

    public static JustificationDeductionAmount getJustificationDeductionAmountSample2() {
        return new JustificationDeductionAmount().id(2L);
    }

    public static JustificationDeductionAmount getJustificationDeductionAmountRandomSampleGenerator() {
        return new JustificationDeductionAmount().id(longCount.incrementAndGet());
    }
}
