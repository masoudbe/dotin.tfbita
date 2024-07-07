package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DraftTypeTopicInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftTypeTopicInfo getDraftTypeTopicInfoSample1() {
        return new DraftTypeTopicInfo()
            .id(1L)
            .currencySellCommissionTopic(1L)
            .documentReceiptDisciplinaryTopic(1L)
            .draftMainTopic(1L)
            .insuranceCostTopic(1L)
            .justificationDisciplinaryTopic(1L)
            .openDraftDisciplinaryTopic(1L)
            .otherCostsTopic(1L)
            .postTelegraphSwiftCostsTopic(1L);
    }

    public static DraftTypeTopicInfo getDraftTypeTopicInfoSample2() {
        return new DraftTypeTopicInfo()
            .id(2L)
            .currencySellCommissionTopic(2L)
            .documentReceiptDisciplinaryTopic(2L)
            .draftMainTopic(2L)
            .insuranceCostTopic(2L)
            .justificationDisciplinaryTopic(2L)
            .openDraftDisciplinaryTopic(2L)
            .otherCostsTopic(2L)
            .postTelegraphSwiftCostsTopic(2L);
    }

    public static DraftTypeTopicInfo getDraftTypeTopicInfoRandomSampleGenerator() {
        return new DraftTypeTopicInfo()
            .id(longCount.incrementAndGet())
            .currencySellCommissionTopic(longCount.incrementAndGet())
            .documentReceiptDisciplinaryTopic(longCount.incrementAndGet())
            .draftMainTopic(longCount.incrementAndGet())
            .insuranceCostTopic(longCount.incrementAndGet())
            .justificationDisciplinaryTopic(longCount.incrementAndGet())
            .openDraftDisciplinaryTopic(longCount.incrementAndGet())
            .otherCostsTopic(longCount.incrementAndGet())
            .postTelegraphSwiftCostsTopic(longCount.incrementAndGet());
    }
}
