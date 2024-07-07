package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyExchangeInfoAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCurrencyExchangeInfoAllPropertiesEquals(CurrencyExchangeInfo expected, CurrencyExchangeInfo actual) {
        assertCurrencyExchangeInfoAutoGeneratedPropertiesEquals(expected, actual);
        assertCurrencyExchangeInfoAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCurrencyExchangeInfoAllUpdatablePropertiesEquals(CurrencyExchangeInfo expected, CurrencyExchangeInfo actual) {
        assertCurrencyExchangeInfoUpdatableFieldsEquals(expected, actual);
        assertCurrencyExchangeInfoUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCurrencyExchangeInfoAutoGeneratedPropertiesEquals(CurrencyExchangeInfo expected, CurrencyExchangeInfo actual) {
        assertThat(expected)
            .as("Verify CurrencyExchangeInfo auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCurrencyExchangeInfoUpdatableFieldsEquals(CurrencyExchangeInfo expected, CurrencyExchangeInfo actual) {
        assertThat(expected)
            .as("Verify CurrencyExchangeInfo relevant properties")
            .satisfies(e -> assertThat(e.getTitle()).as("check title").isEqualTo(actual.getTitle()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCurrencyExchangeInfoUpdatableRelationshipsEquals(CurrencyExchangeInfo expected, CurrencyExchangeInfo actual) {}
}