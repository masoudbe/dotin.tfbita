package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderRegServiceAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOrderRegServiceAllPropertiesEquals(OrderRegService expected, OrderRegService actual) {
        assertOrderRegServiceAutoGeneratedPropertiesEquals(expected, actual);
        assertOrderRegServiceAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOrderRegServiceAllUpdatablePropertiesEquals(OrderRegService expected, OrderRegService actual) {
        assertOrderRegServiceUpdatableFieldsEquals(expected, actual);
        assertOrderRegServiceUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOrderRegServiceAutoGeneratedPropertiesEquals(OrderRegService expected, OrderRegService actual) {
        assertThat(expected)
            .as("Verify OrderRegService auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOrderRegServiceUpdatableFieldsEquals(OrderRegService expected, OrderRegService actual) {
        assertThat(expected)
            .as("Verify OrderRegService relevant properties")
            .satisfies(e -> assertThat(e.getAmount()).as("check amount").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getAmount()))
            .satisfies(
                e ->
                    assertThat(e.getCurrencyAmount())
                        .as("check currencyAmount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getCurrencyAmount())
            )
            .satisfies(e -> assertThat(e.getUnit()).as("check unit").isEqualTo(actual.getUnit()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertOrderRegServiceUpdatableRelationshipsEquals(OrderRegService expected, OrderRegService actual) {
        assertThat(expected)
            .as("Verify OrderRegService relationships")
            .satisfies(e -> assertThat(e.getServiceTariff()).as("check serviceTariff").isEqualTo(actual.getServiceTariff()))
            .satisfies(
                e -> assertThat(e.getOrderRegistrationInfo()).as("check orderRegistrationInfo").isEqualTo(actual.getOrderRegistrationInfo())
            );
    }
}
