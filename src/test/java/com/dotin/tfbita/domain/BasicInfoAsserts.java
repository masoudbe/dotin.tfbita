package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicInfoAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBasicInfoAllPropertiesEquals(BasicInfo expected, BasicInfo actual) {
        assertBasicInfoAutoGeneratedPropertiesEquals(expected, actual);
        assertBasicInfoAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBasicInfoAllUpdatablePropertiesEquals(BasicInfo expected, BasicInfo actual) {
        assertBasicInfoUpdatableFieldsEquals(expected, actual);
        assertBasicInfoUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBasicInfoAutoGeneratedPropertiesEquals(BasicInfo expected, BasicInfo actual) {
        assertThat(expected)
            .as("Verify BasicInfo auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBasicInfoUpdatableFieldsEquals(BasicInfo expected, BasicInfo actual) {
        assertThat(expected)
            .as("Verify BasicInfo relevant properties")
            .satisfies(e -> assertThat(e.getApplyDate()).as("check applyDate").isEqualTo(actual.getApplyDate()))
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getDisabled()).as("check disabled").isEqualTo(actual.getDisabled()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBasicInfoUpdatableRelationshipsEquals(BasicInfo expected, BasicInfo actual) {}
}
