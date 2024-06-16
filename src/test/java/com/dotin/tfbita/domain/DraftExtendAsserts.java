package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DraftExtendAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftExtendAllPropertiesEquals(DraftExtend expected, DraftExtend actual) {
        assertDraftExtendAutoGeneratedPropertiesEquals(expected, actual);
        assertDraftExtendAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftExtendAllUpdatablePropertiesEquals(DraftExtend expected, DraftExtend actual) {
        assertDraftExtendUpdatableFieldsEquals(expected, actual);
        assertDraftExtendUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftExtendAutoGeneratedPropertiesEquals(DraftExtend expected, DraftExtend actual) {
        assertThat(expected)
            .as("Verify DraftExtend auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftExtendUpdatableFieldsEquals(DraftExtend expected, DraftExtend actual) {
        assertThat(expected)
            .as("Verify DraftExtend relevant properties")
            .satisfies(e -> assertThat(e.getDate()).as("check date").isEqualTo(actual.getDate()))
            .satisfies(e -> assertThat(e.getDuration()).as("check duration").isEqualTo(actual.getDuration()))
            .satisfies(e -> assertThat(e.getTime()).as("check time").isEqualTo(actual.getTime()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftExtendUpdatableRelationshipsEquals(DraftExtend expected, DraftExtend actual) {
        assertThat(expected)
            .as("Verify DraftExtend relationships")
            .satisfies(e -> assertThat(e.getExtensions()).as("check extensions").isEqualTo(actual.getExtensions()));
    }
}
