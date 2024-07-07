package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryElementAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCategoryElementAllPropertiesEquals(CategoryElement expected, CategoryElement actual) {
        assertCategoryElementAutoGeneratedPropertiesEquals(expected, actual);
        assertCategoryElementAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCategoryElementAllUpdatablePropertiesEquals(CategoryElement expected, CategoryElement actual) {
        assertCategoryElementUpdatableFieldsEquals(expected, actual);
        assertCategoryElementUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCategoryElementAutoGeneratedPropertiesEquals(CategoryElement expected, CategoryElement actual) {
        assertThat(expected)
            .as("Verify CategoryElement auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCategoryElementUpdatableFieldsEquals(CategoryElement expected, CategoryElement actual) {
        assertThat(expected)
            .as("Verify CategoryElement relevant properties")
            .satisfies(e -> assertThat(e.getValue()).as("check value").isEqualTo(actual.getValue()))
            .satisfies(e -> assertThat(e.getCategoryName()).as("check categoryName").isEqualTo(actual.getCategoryName()))
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCategoryElementUpdatableRelationshipsEquals(CategoryElement expected, CategoryElement actual) {
        assertThat(expected)
            .as("Verify CategoryElement relationships")
            .satisfies(e -> assertThat(e.getCategory()).as("check category").isEqualTo(actual.getCategory()));
    }
}
