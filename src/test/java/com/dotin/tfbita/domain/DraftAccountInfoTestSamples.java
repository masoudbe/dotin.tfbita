package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DraftAccountInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftAccountInfo getDraftAccountInfoSample1() {
        return new DraftAccountInfo()
            .id(1L)
            .documentReceiptDisciplinaryAccountId(1L)
            .draftMainAccountId(1L)
            .insuranceCostAccountId(1L)
            .justificationDisciplinaryAccountId(1L)
            .openDraftDisciplinaryAccountId(1L)
            .otherCostsAccountId(1L)
            .postSwiftCostsAccountId(1L)
            .amountDeductionAccountId(1L);
    }

    public static DraftAccountInfo getDraftAccountInfoSample2() {
        return new DraftAccountInfo()
            .id(2L)
            .documentReceiptDisciplinaryAccountId(2L)
            .draftMainAccountId(2L)
            .insuranceCostAccountId(2L)
            .justificationDisciplinaryAccountId(2L)
            .openDraftDisciplinaryAccountId(2L)
            .otherCostsAccountId(2L)
            .postSwiftCostsAccountId(2L)
            .amountDeductionAccountId(2L);
    }

    public static DraftAccountInfo getDraftAccountInfoRandomSampleGenerator() {
        return new DraftAccountInfo()
            .id(longCount.incrementAndGet())
            .documentReceiptDisciplinaryAccountId(longCount.incrementAndGet())
            .draftMainAccountId(longCount.incrementAndGet())
            .insuranceCostAccountId(longCount.incrementAndGet())
            .justificationDisciplinaryAccountId(longCount.incrementAndGet())
            .openDraftDisciplinaryAccountId(longCount.incrementAndGet())
            .otherCostsAccountId(longCount.incrementAndGet())
            .postSwiftCostsAccountId(longCount.incrementAndGet())
            .amountDeductionAccountId(longCount.incrementAndGet());
    }
}
