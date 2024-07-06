package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DraftReceiptDocumentTransactionContainerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftReceiptDocumentTransactionContainer getDraftReceiptDocumentTransactionContainerSample1() {
        return new DraftReceiptDocumentTransactionContainer().id(1L);
    }

    public static DraftReceiptDocumentTransactionContainer getDraftReceiptDocumentTransactionContainerSample2() {
        return new DraftReceiptDocumentTransactionContainer().id(2L);
    }

    public static DraftReceiptDocumentTransactionContainer getDraftReceiptDocumentTransactionContainerRandomSampleGenerator() {
        return new DraftReceiptDocumentTransactionContainer().id(longCount.incrementAndGet());
    }
}
