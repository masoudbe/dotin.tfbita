package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class AttributeValueGroupAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAttributeValueGroupAllPropertiesEquals(AttributeValueGroup expected, AttributeValueGroup actual) {
        assertAttributeValueGroupAutoGeneratedPropertiesEquals(expected, actual);
        assertAttributeValueGroupAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAttributeValueGroupAllUpdatablePropertiesEquals(AttributeValueGroup expected, AttributeValueGroup actual) {
        assertAttributeValueGroupUpdatableFieldsEquals(expected, actual);
        assertAttributeValueGroupUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAttributeValueGroupAutoGeneratedPropertiesEquals(AttributeValueGroup expected, AttributeValueGroup actual) {
        assertThat(expected)
            .as("Verify AttributeValueGroup auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAttributeValueGroupUpdatableFieldsEquals(AttributeValueGroup expected, AttributeValueGroup actual) {
        assertThat(expected)
            .as("Verify AttributeValueGroup relevant properties")
            .satisfies(e -> assertThat(e.getMandatory()).as("check mandatory").isEqualTo(actual.getMandatory()))
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertAttributeValueGroupUpdatableRelationshipsEquals(AttributeValueGroup expected, AttributeValueGroup actual) {
        assertThat(expected)
            .as("Verify AttributeValueGroup relationships")
            .satisfies(e -> assertThat(e.getAttribute()).as("check attribute").isEqualTo(actual.getAttribute()));
    }
}