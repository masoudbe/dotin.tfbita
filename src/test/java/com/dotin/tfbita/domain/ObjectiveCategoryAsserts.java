package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectiveCategoryAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertObjectiveCategoryAllPropertiesEquals(ObjectiveCategory expected, ObjectiveCategory actual) {
        assertObjectiveCategoryAutoGeneratedPropertiesEquals(expected, actual);
        assertObjectiveCategoryAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertObjectiveCategoryAllUpdatablePropertiesEquals(ObjectiveCategory expected, ObjectiveCategory actual) {
        assertObjectiveCategoryUpdatableFieldsEquals(expected, actual);
        assertObjectiveCategoryUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertObjectiveCategoryAutoGeneratedPropertiesEquals(ObjectiveCategory expected, ObjectiveCategory actual) {
        assertThat(expected)
            .as("Verify ObjectiveCategory auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertObjectiveCategoryUpdatableFieldsEquals(ObjectiveCategory expected, ObjectiveCategory actual) {
        assertThat(expected)
            .as("Verify ObjectiveCategory relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertObjectiveCategoryUpdatableRelationshipsEquals(ObjectiveCategory expected, ObjectiveCategory actual) {}
}