package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class CreditTypeConditionAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCreditTypeConditionAllPropertiesEquals(CreditTypeCondition expected, CreditTypeCondition actual) {
        assertCreditTypeConditionAutoGeneratedPropertiesEquals(expected, actual);
        assertCreditTypeConditionAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCreditTypeConditionAllUpdatablePropertiesEquals(CreditTypeCondition expected, CreditTypeCondition actual) {
        assertCreditTypeConditionUpdatableFieldsEquals(expected, actual);
        assertCreditTypeConditionUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCreditTypeConditionAutoGeneratedPropertiesEquals(CreditTypeCondition expected, CreditTypeCondition actual) {
        assertThat(expected)
            .as("Verify CreditTypeCondition auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCreditTypeConditionUpdatableFieldsEquals(CreditTypeCondition expected, CreditTypeCondition actual) {
        assertThat(expected)
            .as("Verify CreditTypeCondition relevant properties")
            .satisfies(
                e ->
                    assertThat(e.getAssurancePercentage())
                        .as("check assurancePercentage")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getAssurancePercentage())
            )
            .satisfies(
                e ->
                    assertThat(e.getCommissionRate())
                        .as("check commissionRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getCommissionRate())
            )
            .satisfies(
                e ->
                    assertThat(e.getCustomerPrepaymentRateFrom())
                        .as("check customerPrepaymentRateFrom")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getCustomerPrepaymentRateFrom())
            )
            .satisfies(
                e ->
                    assertThat(e.getCustomerPrepaymentRateTo())
                        .as("check customerPrepaymentRateTo")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getCustomerPrepaymentRateTo())
            )
            .satisfies(e -> assertThat(e.getDurationFrom()).as("check durationFrom").isEqualTo(actual.getDurationFrom()))
            .satisfies(e -> assertThat(e.getDurationTo()).as("check durationTo").isEqualTo(actual.getDurationTo()))
            .satisfies(
                e ->
                    assertThat(e.getOrderRegistrationRightFrom())
                        .as("check orderRegistrationRightFrom")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getOrderRegistrationRightFrom())
            )
            .satisfies(
                e ->
                    assertThat(e.getOrderRegistrationRightTo())
                        .as("check orderRegistrationRightTo")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getOrderRegistrationRightTo())
            )
            .satisfies(
                e ->
                    assertThat(e.getPostSuspensionPeriodPenaltyRate())
                        .as("check postSuspensionPeriodPenaltyRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getPostSuspensionPeriodPenaltyRate())
            )
            .satisfies(
                e ->
                    assertThat(e.getPriceFrom()).as("check priceFrom").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getPriceFrom())
            )
            .satisfies(
                e -> assertThat(e.getPriceTo()).as("check priceTo").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getPriceTo())
            )
            .satisfies(
                e ->
                    assertThat(e.getSuspensionDurationFrom())
                        .as("check suspensionDurationFrom")
                        .isEqualTo(actual.getSuspensionDurationFrom())
            )
            .satisfies(
                e -> assertThat(e.getSuspensionDurationTo()).as("check suspensionDurationTo").isEqualTo(actual.getSuspensionDurationTo())
            )
            .satisfies(
                e ->
                    assertThat(e.getSuspensionPeriodInterestRate())
                        .as("check suspensionPeriodInterestRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getSuspensionPeriodInterestRate())
            )
            .satisfies(
                e ->
                    assertThat(e.getUpdateCommissionRate())
                        .as("check updateCommissionRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getUpdateCommissionRate())
            )
            .satisfies(e -> assertThat(e.getCurrencyCode()).as("check currencyCode").isEqualTo(actual.getCurrencyCode()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCreditTypeConditionUpdatableRelationshipsEquals(CreditTypeCondition expected, CreditTypeCondition actual) {
        assertThat(expected)
            .as("Verify CreditTypeCondition relationships")
            .satisfies(e -> assertThat(e.getServiceOrProduct()).as("check serviceOrProduct").isEqualTo(actual.getServiceOrProduct()))
            .satisfies(
                e ->
                    assertThat(e.getNeededIdentificationDocTypes())
                        .as("check neededIdentificationDocTypes")
                        .isEqualTo(actual.getNeededIdentificationDocTypes())
            )
            .satisfies(e -> assertThat(e.getProductTypes()).as("check productTypes").isEqualTo(actual.getProductTypes()))
            .satisfies(e -> assertThat(e.getAssuranceTypes()).as("check assuranceTypes").isEqualTo(actual.getAssuranceTypes()))
            .satisfies(
                e ->
                    assertThat(e.getCreditTypeConditionInfo())
                        .as("check creditTypeConditionInfo")
                        .isEqualTo(actual.getCreditTypeConditionInfo())
            );
    }
}