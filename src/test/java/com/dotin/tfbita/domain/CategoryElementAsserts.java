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
            .satisfies(e -> assertThat(e.getOrderRegType()).as("check orderRegType").isEqualTo(actual.getOrderRegType()))
            .satisfies(e -> assertThat(e.getRequestType()).as("check requestType").isEqualTo(actual.getRequestType()))
            .satisfies(e -> assertThat(e.getImportType()).as("check importType").isEqualTo(actual.getImportType()))
            .satisfies(e -> assertThat(e.getOperationTyp()).as("check operationTyp").isEqualTo(actual.getOperationTyp()))
            .satisfies(
                e -> assertThat(e.getCurrencyProvisionType()).as("check currencyProvisionType").isEqualTo(actual.getCurrencyProvisionType())
            )
            .satisfies(e -> assertThat(e.getPaymentTool()).as("check paymentTool").isEqualTo(actual.getPaymentTool()))
            .satisfies(e -> assertThat(e.getActivityType()).as("check activityType").isEqualTo(actual.getActivityType()))
            .satisfies(e -> assertThat(e.getOwnerType()).as("check ownerType").isEqualTo(actual.getOwnerType()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(
                e -> assertThat(e.getExternalCustomerType()).as("check externalCustomerType").isEqualTo(actual.getExternalCustomerType())
            )
            .satisfies(e -> assertThat(e.getTransportType()).as("check transportType").isEqualTo(actual.getTransportType()))
            .satisfies(e -> assertThat(e.getCurrencySupplier()).as("check currencySupplier").isEqualTo(actual.getCurrencySupplier()))
            .satisfies(e -> assertThat(e.getStatusPurchase()).as("check statusPurchase").isEqualTo(actual.getStatusPurchase()))
            .satisfies(
                e -> assertThat(e.getTransportVehicleType()).as("check transportVehicleType").isEqualTo(actual.getTransportVehicleType())
            )
            .satisfies(e -> assertThat(e.getFreightLetterType()).as("check freightLetterType").isEqualTo(actual.getFreightLetterType()))
            .satisfies(e -> assertThat(e.getActionCode()).as("check actionCode").isEqualTo(actual.getActionCode()))
            .satisfies(e -> assertThat(e.getOwnershipCode()).as("check ownershipCode").isEqualTo(actual.getOwnershipCode()))
            .satisfies(
                e ->
                    assertThat(e.getCurrencyContainerPlace())
                        .as("check currencyContainerPlace")
                        .isEqualTo(actual.getCurrencyContainerPlace())
            )
            .satisfies(e -> assertThat(e.getDraftSource()).as("check draftSource").isEqualTo(actual.getDraftSource()))
            .satisfies(
                e -> assertThat(e.getChargedExchangeBroker()).as("check chargedExchangeBroker").isEqualTo(actual.getChargedExchangeBroker())
            )
            .satisfies(e -> assertThat(e.getImpartType()).as("check impartType").isEqualTo(actual.getImpartType()))
            .satisfies(
                e -> assertThat(e.getInsuranceLetterType()).as("check insuranceLetterType").isEqualTo(actual.getInsuranceLetterType())
            )
            .satisfies(e -> assertThat(e.getAdvisorDepositType()).as("check advisorDepositType").isEqualTo(actual.getAdvisorDepositType()))
            .satisfies(
                e ->
                    assertThat(e.getInterfaceAdvisorDepositType())
                        .as("check interfaceAdvisorDepositType")
                        .isEqualTo(actual.getInterfaceAdvisorDepositType())
            )
            .satisfies(e -> assertThat(e.getPaymentType()).as("check paymentType").isEqualTo(actual.getPaymentType()))
            .satisfies(e -> assertThat(e.getDealType()).as("check dealType").isEqualTo(actual.getDealType()))
            .satisfies(
                e ->
                    assertThat(e.getCoveringAdvisorDepositType())
                        .as("check coveringAdvisorDepositType")
                        .isEqualTo(actual.getCoveringAdvisorDepositType())
            )
            .satisfies(e -> assertThat(e.getDepositType()).as("check depositType").isEqualTo(actual.getDepositType()))
            .satisfies(e -> assertThat(e.getType()).as("check type").isEqualTo(actual.getType()))
            .satisfies(e -> assertThat(e.getSecondaryType()).as("check secondaryType").isEqualTo(actual.getSecondaryType()))
            .satisfies(e -> assertThat(e.getDivision()).as("check division").isEqualTo(actual.getDivision()))
            .satisfies(e -> assertThat(e.getProductDimension()).as("check productDimension").isEqualTo(actual.getProductDimension()))
            .satisfies(e -> assertThat(e.getStateOfDocuments()).as("check stateOfDocuments").isEqualTo(actual.getStateOfDocuments()))
            .satisfies(
                e ->
                    assertThat(e.getCurrencyProvisionFileType())
                        .as("check currencyProvisionFileType")
                        .isEqualTo(actual.getCurrencyProvisionFileType())
            )
            .satisfies(e -> assertThat(e.getStatusDraft()).as("check statusDraft").isEqualTo(actual.getStatusDraft()))
            .satisfies(e -> assertThat(e.getCategoryElement()).as("check categoryElement").isEqualTo(actual.getCategoryElement()));
    }
}
