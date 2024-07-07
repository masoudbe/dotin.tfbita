package com.dotin.tfbita.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DraftTypeAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftTypeAllPropertiesEquals(DraftType expected, DraftType actual) {
        assertDraftTypeAutoGeneratedPropertiesEquals(expected, actual);
        assertDraftTypeAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftTypeAllUpdatablePropertiesEquals(DraftType expected, DraftType actual) {
        assertDraftTypeUpdatableFieldsEquals(expected, actual);
        assertDraftTypeUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftTypeAutoGeneratedPropertiesEquals(DraftType expected, DraftType actual) {
        assertThat(expected)
            .as("Verify DraftType auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftTypeUpdatableFieldsEquals(DraftType expected, DraftType actual) {
        assertThat(expected)
            .as("Verify DraftType relevant properties")
            .satisfies(e -> assertThat(e.getAlarmTime()).as("check alarmTime").isEqualTo(actual.getAlarmTime()))
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getDisableDate()).as("check disableDate").isEqualTo(actual.getDisableDate()))
            .satisfies(e -> assertThat(e.getDuration()).as("check duration").isEqualTo(actual.getDuration()))
            .satisfies(e -> assertThat(e.getHasAssurance()).as("check hasAssurance").isEqualTo(actual.getHasAssurance()))
            .satisfies(e -> assertThat(e.getHasSanction()).as("check hasSanction").isEqualTo(actual.getHasSanction()))
            .satisfies(e -> assertThat(e.getLatestCreditSerial()).as("check latestCreditSerial").isEqualTo(actual.getLatestCreditSerial()))
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getPortal()).as("check portal").isEqualTo(actual.getPortal()))
            .satisfies(e -> assertThat(e.getUsable()).as("check usable").isEqualTo(actual.getUsable()))
            .satisfies(e -> assertThat(e.getCurrenciesCodes()).as("check currenciesCodes").isEqualTo(actual.getCurrenciesCodes()))
            .satisfies(
                e -> assertThat(e.getDefaultCurrencyCode()).as("check defaultCurrencyCode").isEqualTo(actual.getDefaultCurrencyCode())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDraftTypeUpdatableRelationshipsEquals(DraftType expected, DraftType actual) {
        assertThat(expected)
            .as("Verify DraftType relationships")
            .satisfies(e -> assertThat(e.getType()).as("check type").isEqualTo(actual.getType()))
            .satisfies(e -> assertThat(e.getSecondaryType()).as("check secondaryType").isEqualTo(actual.getSecondaryType()))
            .satisfies(e -> assertThat(e.getDivision()).as("check division").isEqualTo(actual.getDivision()))
            .satisfies(e -> assertThat(e.getTopicInfo()).as("check topicInfo").isEqualTo(actual.getTopicInfo()))
            .satisfies(e -> assertThat(e.getConditionInfo()).as("check conditionInfo").isEqualTo(actual.getConditionInfo()))
            .satisfies(e -> assertThat(e.getAccountInfo()).as("check accountInfo").isEqualTo(actual.getAccountInfo()))
            .satisfies(e -> assertThat(e.getRequestType()).as("check requestType").isEqualTo(actual.getRequestType()))
            .satisfies(
                e ->
                    assertThat(e.getAcceptableProductTypes())
                        .as("check acceptableProductTypes")
                        .isEqualTo(actual.getAcceptableProductTypes())
            )
            .satisfies(e -> assertThat(e.getUserGroups()).as("check userGroups").isEqualTo(actual.getUserGroups()));
    }
}