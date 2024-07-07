package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CreditTypeConditionInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CreditTypeConditionInfo getCreditTypeConditionInfoSample1() {
        return new CreditTypeConditionInfo()
            .id(1L)
            .durationFrom(1)
            .durationTo(1)
            .justificationDisciplinaryTopic(1L)
            .openDraftDisciplinaryTopic(1L)
            .otherCostsTopic(1L)
            .postTelegraphSwiftCostsTopic(1L);
    }

    public static CreditTypeConditionInfo getCreditTypeConditionInfoSample2() {
        return new CreditTypeConditionInfo()
            .id(2L)
            .durationFrom(2)
            .durationTo(2)
            .justificationDisciplinaryTopic(2L)
            .openDraftDisciplinaryTopic(2L)
            .otherCostsTopic(2L)
            .postTelegraphSwiftCostsTopic(2L);
    }

    public static CreditTypeConditionInfo getCreditTypeConditionInfoRandomSampleGenerator() {
        return new CreditTypeConditionInfo()
            .id(longCount.incrementAndGet())
            .durationFrom(intCount.incrementAndGet())
            .durationTo(intCount.incrementAndGet())
            .justificationDisciplinaryTopic(longCount.incrementAndGet())
            .openDraftDisciplinaryTopic(longCount.incrementAndGet())
            .otherCostsTopic(longCount.incrementAndGet())
            .postTelegraphSwiftCostsTopic(longCount.incrementAndGet());
    }
}
