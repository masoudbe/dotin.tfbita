package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class InsuranceCompanyInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static InsuranceCompanyInfo getInsuranceCompanyInfoSample1() {
        return new InsuranceCompanyInfo().id(1L).modificationDate("modificationDate1").name("name1");
    }

    public static InsuranceCompanyInfo getInsuranceCompanyInfoSample2() {
        return new InsuranceCompanyInfo().id(2L).modificationDate("modificationDate2").name("name2");
    }

    public static InsuranceCompanyInfo getInsuranceCompanyInfoRandomSampleGenerator() {
        return new InsuranceCompanyInfo()
            .id(longCount.incrementAndGet())
            .modificationDate(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString());
    }
}
