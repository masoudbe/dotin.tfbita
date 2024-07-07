package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class DraftCertificateTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DraftCertificateType getDraftCertificateTypeSample1() {
        return new DraftCertificateType().id(1L);
    }

    public static DraftCertificateType getDraftCertificateTypeSample2() {
        return new DraftCertificateType().id(2L);
    }

    public static DraftCertificateType getDraftCertificateTypeRandomSampleGenerator() {
        return new DraftCertificateType().id(longCount.incrementAndGet());
    }
}
