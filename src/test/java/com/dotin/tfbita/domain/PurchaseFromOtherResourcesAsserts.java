package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseFromOtherResourcesAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPurchaseFromOtherResourcesAllPropertiesEquals(
        PurchaseFromOtherResources expected,
        PurchaseFromOtherResources actual
    ) {
        assertPurchaseFromOtherResourcesAutoGeneratedPropertiesEquals(expected, actual);
        assertPurchaseFromOtherResourcesAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPurchaseFromOtherResourcesAllUpdatablePropertiesEquals(
        PurchaseFromOtherResources expected,
        PurchaseFromOtherResources actual
    ) {
        assertPurchaseFromOtherResourcesUpdatableFieldsEquals(expected, actual);
        assertPurchaseFromOtherResourcesUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPurchaseFromOtherResourcesAutoGeneratedPropertiesEquals(
        PurchaseFromOtherResources expected,
        PurchaseFromOtherResources actual
    ) {
        assertThat(expected)
            .as("Verify PurchaseFromOtherResources auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPurchaseFromOtherResourcesUpdatableFieldsEquals(
        PurchaseFromOtherResources expected,
        PurchaseFromOtherResources actual
    ) {
        assertThat(expected)
            .as("Verify PurchaseFromOtherResources relevant properties")
            .satisfies(e -> assertThat(e.getEvidenceCode()).as("check evidenceCode").isEqualTo(actual.getEvidenceCode()))
            .satisfies(
                e ->
                    assertThat(e.getCurrencySupplierDescription())
                        .as("check currencySupplierDescription")
                        .isEqualTo(actual.getCurrencySupplierDescription())
            )
            .satisfies(e -> assertThat(e.getAmount()).as("check amount").usingComparator(bigDecimalCompareTo).isEqualTo(actual.getAmount()))
            .satisfies(
                e ->
                    assertThat(e.getPurchaseRate())
                        .as("check purchaseRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getPurchaseRate())
            )
            .satisfies(
                e ->
                    assertThat(e.getOrderRegistrationAmount())
                        .as("check orderRegistrationAmount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getOrderRegistrationAmount())
            )
            .satisfies(e -> assertThat(e.getRequestDate()).as("check requestDate").isEqualTo(actual.getRequestDate()))
            .satisfies(e -> assertThat(e.getConfirmationDate()).as("check confirmationDate").isEqualTo(actual.getConfirmationDate()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getPurchaseNumber()).as("check purchaseNumber").isEqualTo(actual.getPurchaseNumber()))
            .satisfies(
                e -> assertThat(e.getPurchaseCurrencyName()).as("check purchaseCurrencyName").isEqualTo(actual.getPurchaseCurrencyName())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPurchaseFromOtherResourcesUpdatableRelationshipsEquals(
        PurchaseFromOtherResources expected,
        PurchaseFromOtherResources actual
    ) {
        assertThat(expected)
            .as("Verify PurchaseFromOtherResources relationships")
            .satisfies(e -> assertThat(e.getCurrencySupplier()).as("check currencySupplier").isEqualTo(actual.getCurrencySupplier()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(
                e -> assertThat(e.getOrderRegistrationInfo()).as("check orderRegistrationInfo").isEqualTo(actual.getOrderRegistrationInfo())
            );
    }
}