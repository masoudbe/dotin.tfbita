package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class LongValueAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLongValueAllPropertiesEquals(LongValue expected, LongValue actual) {
        assertLongValueAutoGeneratedPropertiesEquals(expected, actual);
        assertLongValueAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLongValueAllUpdatablePropertiesEquals(LongValue expected, LongValue actual) {
        assertLongValueUpdatableFieldsEquals(expected, actual);
        assertLongValueUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLongValueAutoGeneratedPropertiesEquals(LongValue expected, LongValue actual) {
        assertThat(expected)
            .as("Verify LongValue auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLongValueUpdatableFieldsEquals(LongValue expected, LongValue actual) {
        assertThat(expected)
            .as("Verify LongValue relevant properties")
            .satisfies(e -> assertThat(e.getVal()).as("check val").isEqualTo(actual.getVal()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertLongValueUpdatableRelationshipsEquals(LongValue expected, LongValue actual) {
        assertThat(expected)
            .as("Verify LongValue relationships")
            .satisfies(e -> assertThat(e.getDrafts()).as("check drafts").isEqualTo(actual.getDrafts()));
    }
}
