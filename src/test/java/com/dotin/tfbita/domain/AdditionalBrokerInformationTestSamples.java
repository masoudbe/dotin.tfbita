package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AdditionalBrokerInformationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AdditionalBrokerInformation getAdditionalBrokerInformationSample1() {
        return new AdditionalBrokerInformation()
            .id(1L)
            .dateOfStartRelation("dateOfStartRelation1")
            .creditLimit("creditLimit1")
            .revokedDate("revokedDate1")
            .revokedNote("revokedNote1")
            .confidential("confidential1")
            .otherBrokerServices("otherBrokerServices1")
            .sanctionedStatus("sanctionedStatus1")
            .considerations("considerations1")
            .description("description1")
            .waysOfCommunication("waysOfCommunication1")
            .servicesAvailable("servicesAvailable1")
            .customerAcceptancePolicy("customerAcceptancePolicy1");
    }

    public static AdditionalBrokerInformation getAdditionalBrokerInformationSample2() {
        return new AdditionalBrokerInformation()
            .id(2L)
            .dateOfStartRelation("dateOfStartRelation2")
            .creditLimit("creditLimit2")
            .revokedDate("revokedDate2")
            .revokedNote("revokedNote2")
            .confidential("confidential2")
            .otherBrokerServices("otherBrokerServices2")
            .sanctionedStatus("sanctionedStatus2")
            .considerations("considerations2")
            .description("description2")
            .waysOfCommunication("waysOfCommunication2")
            .servicesAvailable("servicesAvailable2")
            .customerAcceptancePolicy("customerAcceptancePolicy2");
    }

    public static AdditionalBrokerInformation getAdditionalBrokerInformationRandomSampleGenerator() {
        return new AdditionalBrokerInformation()
            .id(longCount.incrementAndGet())
            .dateOfStartRelation(UUID.randomUUID().toString())
            .creditLimit(UUID.randomUUID().toString())
            .revokedDate(UUID.randomUUID().toString())
            .revokedNote(UUID.randomUUID().toString())
            .confidential(UUID.randomUUID().toString())
            .otherBrokerServices(UUID.randomUUID().toString())
            .sanctionedStatus(UUID.randomUUID().toString())
            .considerations(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .waysOfCommunication(UUID.randomUUID().toString())
            .servicesAvailable(UUID.randomUUID().toString())
            .customerAcceptancePolicy(UUID.randomUUID().toString());
    }
}
