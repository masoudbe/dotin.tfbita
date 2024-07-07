package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomJustificationChildAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationChildAllPropertiesEquals(
        CustomJustificationChild expected,
        CustomJustificationChild actual
    ) {
        assertCustomJustificationChildAutoGeneratedPropertiesEquals(expected, actual);
        assertCustomJustificationChildAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationChildAllUpdatablePropertiesEquals(
        CustomJustificationChild expected,
        CustomJustificationChild actual
    ) {
        assertCustomJustificationChildUpdatableFieldsEquals(expected, actual);
        assertCustomJustificationChildUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationChildAutoGeneratedPropertiesEquals(
        CustomJustificationChild expected,
        CustomJustificationChild actual
    ) {
        assertThat(expected)
            .as("Verify CustomJustificationChild auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationChildUpdatableFieldsEquals(
        CustomJustificationChild expected,
        CustomJustificationChild actual
    ) {
        assertThat(expected)
            .as("Verify CustomJustificationChild relevant properties")
            .satisfies(e -> assertThat(e.getItem()).as("check item").isEqualTo(actual.getItem()))
            .satisfies(e -> assertThat(e.getTariffCode()).as("check tariffCode").isEqualTo(actual.getTariffCode()))
            .satisfies(
                e ->
                    assertThat(e.getProductName())
                        .as("check productName")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getProductName())
            )
            .satisfies(e -> assertThat(e.getProductId()).as("check productId").isEqualTo(actual.getProductId()))
            .satisfies(
                e -> assertThat(e.getBoxCount()).as("check boxCount").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getBoxCount())
            )
            .satisfies(e -> assertThat(e.getBoxType()).as("check boxType").isEqualTo(actual.getBoxType()))
            .satisfies(
                e ->
                    assertThat(e.getPureWeight())
                        .as("check pureWeight")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getPureWeight())
            )
            .satisfies(
                e ->
                    assertThat(e.getImpureWeight())
                        .as("check impureWeight")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getImpureWeight())
            )
            .satisfies(
                e ->
                    assertThat(e.getAmountCurrency())
                        .as("check amountCurrency")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getAmountCurrency())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationChildUpdatableRelationshipsEquals(
        CustomJustificationChild expected,
        CustomJustificationChild actual
    ) {
        assertThat(expected)
            .as("Verify CustomJustificationChild relationships")
            .satisfies(
                e -> assertThat(e.getCustomJustification()).as("check customJustification").isEqualTo(actual.getCustomJustification())
            );
    }
}
