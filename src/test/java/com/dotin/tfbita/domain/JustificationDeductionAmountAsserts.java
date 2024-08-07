package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class JustificationDeductionAmountAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertJustificationDeductionAmountAllPropertiesEquals(
        JustificationDeductionAmount expected,
        JustificationDeductionAmount actual
    ) {
        assertJustificationDeductionAmountAutoGeneratedPropertiesEquals(expected, actual);
        assertJustificationDeductionAmountAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertJustificationDeductionAmountAllUpdatablePropertiesEquals(
        JustificationDeductionAmount expected,
        JustificationDeductionAmount actual
    ) {
        assertJustificationDeductionAmountUpdatableFieldsEquals(expected, actual);
        assertJustificationDeductionAmountUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertJustificationDeductionAmountAutoGeneratedPropertiesEquals(
        JustificationDeductionAmount expected,
        JustificationDeductionAmount actual
    ) {
        assertThat(expected)
            .as("Verify JustificationDeductionAmount auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertJustificationDeductionAmountUpdatableFieldsEquals(
        JustificationDeductionAmount expected,
        JustificationDeductionAmount actual
    ) {
        assertThat(expected)
            .as("Verify JustificationDeductionAmount relevant properties")
            .satisfies(
                e ->
                    assertThat(e.getDeductionAmount())
                        .as("check deductionAmount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getDeductionAmount())
            )
            .satisfies(
                e ->
                    assertThat(e.getRemainingDeductionAmount())
                        .as("check remainingDeductionAmount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getRemainingDeductionAmount())
            )
            .satisfies(
                e ->
                    assertThat(e.getReceivedDeductionAmount())
                        .as("check receivedDeductionAmount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getReceivedDeductionAmount())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertJustificationDeductionAmountUpdatableRelationshipsEquals(
        JustificationDeductionAmount expected,
        JustificationDeductionAmount actual
    ) {}
}
