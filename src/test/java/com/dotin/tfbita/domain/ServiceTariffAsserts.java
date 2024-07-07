package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceTariffAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertServiceTariffAllPropertiesEquals(ServiceTariff expected, ServiceTariff actual) {
        assertServiceTariffAutoGeneratedPropertiesEquals(expected, actual);
        assertServiceTariffAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertServiceTariffAllUpdatablePropertiesEquals(ServiceTariff expected, ServiceTariff actual) {
        assertServiceTariffUpdatableFieldsEquals(expected, actual);
        assertServiceTariffUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertServiceTariffAutoGeneratedPropertiesEquals(ServiceTariff expected, ServiceTariff actual) {
        assertThat(expected)
            .as("Verify ServiceTariff auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertServiceTariffUpdatableFieldsEquals(ServiceTariff expected, ServiceTariff actual) {
        assertThat(expected)
            .as("Verify ServiceTariff relevant properties")
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getTitle()).as("check title").isEqualTo(actual.getTitle()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertServiceTariffUpdatableRelationshipsEquals(ServiceTariff expected, ServiceTariff actual) {
        assertThat(expected)
            .as("Verify ServiceTariff relationships")
            .satisfies(e -> assertThat(e.getDrafts()).as("check drafts").isEqualTo(actual.getDrafts()));
    }
}