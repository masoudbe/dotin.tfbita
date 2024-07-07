package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentCurrencyRateTypeAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCurrencyRateTypeAllPropertiesEquals(PaymentCurrencyRateType expected, PaymentCurrencyRateType actual) {
        assertPaymentCurrencyRateTypeAutoGeneratedPropertiesEquals(expected, actual);
        assertPaymentCurrencyRateTypeAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCurrencyRateTypeAllUpdatablePropertiesEquals(
        PaymentCurrencyRateType expected,
        PaymentCurrencyRateType actual
    ) {
        assertPaymentCurrencyRateTypeUpdatableFieldsEquals(expected, actual);
        assertPaymentCurrencyRateTypeUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCurrencyRateTypeAutoGeneratedPropertiesEquals(
        PaymentCurrencyRateType expected,
        PaymentCurrencyRateType actual
    ) {
        assertThat(expected)
            .as("Verify PaymentCurrencyRateType auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCurrencyRateTypeUpdatableFieldsEquals(
        PaymentCurrencyRateType expected,
        PaymentCurrencyRateType actual
    ) {
        assertThat(expected)
            .as("Verify PaymentCurrencyRateType relevant properties")
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPaymentCurrencyRateTypeUpdatableRelationshipsEquals(
        PaymentCurrencyRateType expected,
        PaymentCurrencyRateType actual
    ) {}
}