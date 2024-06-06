package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class LicenceInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static LicenceInfo getLicenceInfoSample1() {
        return new LicenceInfo()
            .id(1L)
            .organizationLicence("organizationLicence1")
            .licenceNumber("licenceNumber1")
            .licenceDate("licenceDate1")
            .creditDate("creditDate1");
    }

    public static LicenceInfo getLicenceInfoSample2() {
        return new LicenceInfo()
            .id(2L)
            .organizationLicence("organizationLicence2")
            .licenceNumber("licenceNumber2")
            .licenceDate("licenceDate2")
            .creditDate("creditDate2");
    }

    public static LicenceInfo getLicenceInfoRandomSampleGenerator() {
        return new LicenceInfo()
            .id(longCount.incrementAndGet())
            .organizationLicence(UUID.randomUUID().toString())
            .licenceNumber(UUID.randomUUID().toString())
            .licenceDate(UUID.randomUUID().toString())
            .creditDate(UUID.randomUUID().toString());
    }
}
