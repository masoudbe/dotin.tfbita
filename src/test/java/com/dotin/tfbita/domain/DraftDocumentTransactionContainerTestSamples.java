package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DraftDocumentTransactionContainerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftDocumentTransactionContainer getDraftDocumentTransactionContainerSample1() {
        return new DraftDocumentTransactionContainer().id(1L);
    }

    public static DraftDocumentTransactionContainer getDraftDocumentTransactionContainerSample2() {
        return new DraftDocumentTransactionContainer().id(2L);
    }

    public static DraftDocumentTransactionContainer getDraftDocumentTransactionContainerRandomSampleGenerator() {
        return new DraftDocumentTransactionContainer().id(longCount.incrementAndGet());
    }
}
