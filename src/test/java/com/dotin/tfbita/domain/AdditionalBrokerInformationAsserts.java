package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class AdditionalBrokerInformationAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAdditionalBrokerInformationAllPropertiesEquals(
        AdditionalBrokerInformation expected,
        AdditionalBrokerInformation actual
    ) {
        assertAdditionalBrokerInformationAutoGeneratedPropertiesEquals(expected, actual);
        assertAdditionalBrokerInformationAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAdditionalBrokerInformationAllUpdatablePropertiesEquals(
        AdditionalBrokerInformation expected,
        AdditionalBrokerInformation actual
    ) {
        assertAdditionalBrokerInformationUpdatableFieldsEquals(expected, actual);
        assertAdditionalBrokerInformationUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAdditionalBrokerInformationAutoGeneratedPropertiesEquals(
        AdditionalBrokerInformation expected,
        AdditionalBrokerInformation actual
    ) {
        assertThat(expected)
            .as("Verify AdditionalBrokerInformation auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAdditionalBrokerInformationUpdatableFieldsEquals(
        AdditionalBrokerInformation expected,
        AdditionalBrokerInformation actual
    ) {
        assertThat(expected)
            .as("Verify AdditionalBrokerInformation relevant properties")
            .satisfies(
                e -> assertThat(e.getDateOfStartRelation()).as("check dateOfStartRelation").isEqualTo(actual.getDateOfStartRelation())
            )
            .satisfies(e -> assertThat(e.getCreditLimit()).as("check creditLimit").isEqualTo(actual.getCreditLimit()))
            .satisfies(e -> assertThat(e.getRevokedDate()).as("check revokedDate").isEqualTo(actual.getRevokedDate()))
            .satisfies(e -> assertThat(e.getRevokedNote()).as("check revokedNote").isEqualTo(actual.getRevokedNote()))
            .satisfies(e -> assertThat(e.getConfidential()).as("check confidential").isEqualTo(actual.getConfidential()))
            .satisfies(
                e -> assertThat(e.getOtherBrokerServices()).as("check otherBrokerServices").isEqualTo(actual.getOtherBrokerServices())
            )
            .satisfies(e -> assertThat(e.getSanctionedStatus()).as("check sanctionedStatus").isEqualTo(actual.getSanctionedStatus()))
            .satisfies(e -> assertThat(e.getConsiderations()).as("check considerations").isEqualTo(actual.getConsiderations()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(
                e -> assertThat(e.getWaysOfCommunication()).as("check waysOfCommunication").isEqualTo(actual.getWaysOfCommunication())
            )
            .satisfies(e -> assertThat(e.getServicesAvailable()).as("check servicesAvailable").isEqualTo(actual.getServicesAvailable()))
            .satisfies(
                e ->
                    assertThat(e.getCustomerAcceptancePolicy())
                        .as("check customerAcceptancePolicy")
                        .isEqualTo(actual.getCustomerAcceptancePolicy())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAdditionalBrokerInformationUpdatableRelationshipsEquals(
        AdditionalBrokerInformation expected,
        AdditionalBrokerInformation actual
    ) {}
}