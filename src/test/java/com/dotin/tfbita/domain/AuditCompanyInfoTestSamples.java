package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AuditCompanyInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AuditCompanyInfo getAuditCompanyInfoSample1() {
        return new AuditCompanyInfo()
            .id(1L)
            .address("address1")
            .barCodes("barCodes1")
            .dateOfRegistration("dateOfRegistration1")
            .fax("fax1")
            .floor("floor1")
            .inernationalobserviation("inernationalobserviation1")
            .mainStreet("mainStreet1")
            .mobile("mobile1")
            .plaque("plaque1")
            .postalCode("postalCode1")
            .registrationNumber("registrationNumber1")
            .telephone("telephone1")
            .tempId(1L)
            .theSideStreet("theSideStreet1")
            .title("title1")
            .unit("unit1")
            .cityCode("cityCode1");
    }

    public static AuditCompanyInfo getAuditCompanyInfoSample2() {
        return new AuditCompanyInfo()
            .id(2L)
            .address("address2")
            .barCodes("barCodes2")
            .dateOfRegistration("dateOfRegistration2")
            .fax("fax2")
            .floor("floor2")
            .inernationalobserviation("inernationalobserviation2")
            .mainStreet("mainStreet2")
            .mobile("mobile2")
            .plaque("plaque2")
            .postalCode("postalCode2")
            .registrationNumber("registrationNumber2")
            .telephone("telephone2")
            .tempId(2L)
            .theSideStreet("theSideStreet2")
            .title("title2")
            .unit("unit2")
            .cityCode("cityCode2");
    }

    public static AuditCompanyInfo getAuditCompanyInfoRandomSampleGenerator() {
        return new AuditCompanyInfo()
            .id(longCount.incrementAndGet())
            .address(UUID.randomUUID().toString())
            .barCodes(UUID.randomUUID().toString())
            .dateOfRegistration(UUID.randomUUID().toString())
            .fax(UUID.randomUUID().toString())
            .floor(UUID.randomUUID().toString())
            .inernationalobserviation(UUID.randomUUID().toString())
            .mainStreet(UUID.randomUUID().toString())
            .mobile(UUID.randomUUID().toString())
            .plaque(UUID.randomUUID().toString())
            .postalCode(UUID.randomUUID().toString())
            .registrationNumber(UUID.randomUUID().toString())
            .telephone(UUID.randomUUID().toString())
            .tempId(longCount.incrementAndGet())
            .theSideStreet(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .unit(UUID.randomUUID().toString())
            .cityCode(UUID.randomUUID().toString());
    }
}
