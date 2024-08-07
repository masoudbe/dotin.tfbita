package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.AssertUtils.bigDecimalCompareTo;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomJustificationAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationAllPropertiesEquals(CustomJustification expected, CustomJustification actual) {
        assertCustomJustificationAutoGeneratedPropertiesEquals(expected, actual);
        assertCustomJustificationAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationAllUpdatablePropertiesEquals(CustomJustification expected, CustomJustification actual) {
        assertCustomJustificationUpdatableFieldsEquals(expected, actual);
        assertCustomJustificationUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationAutoGeneratedPropertiesEquals(CustomJustification expected, CustomJustification actual) {
        assertThat(expected)
            .as("Verify CustomJustification auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationUpdatableFieldsEquals(CustomJustification expected, CustomJustification actual) {
        assertThat(expected)
            .as("Verify CustomJustification relevant properties")
            .satisfies(e -> assertThat(e.getAgentId()).as("check agentId").isEqualTo(actual.getAgentId()))
            .satisfies(
                e ->
                    assertThat(e.getAgriculturalPublicPolicies())
                        .as("check agriculturalPublicPolicies")
                        .isEqualTo(actual.getAgriculturalPublicPolicies())
            )
            .satisfies(e -> assertThat(e.getAssessmentPlace()).as("check assessmentPlace").isEqualTo(actual.getAssessmentPlace()))
            .satisfies(e -> assertThat(e.getAttachedDocuments()).as("check attachedDocuments").isEqualTo(actual.getAttachedDocuments()))
            .satisfies(
                e ->
                    assertThat(e.getBalanceRate())
                        .as("check balanceRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getBalanceRate())
            )
            .satisfies(e -> assertThat(e.getBankCode()).as("check bankCode").isEqualTo(actual.getBankCode()))
            .satisfies(
                e -> assertThat(e.getBorderTransportType()).as("check borderTransportType").isEqualTo(actual.getBorderTransportType())
            )
            .satisfies(e -> assertThat(e.getBoxCountType()).as("check boxCountType").isEqualTo(actual.getBoxCountType()))
            .satisfies(e -> assertThat(e.getBoxMarks()).as("check boxMarks").isEqualTo(actual.getBoxMarks()))
            .satisfies(e -> assertThat(e.getCargoIndexNumber()).as("check cargoIndexNumber").isEqualTo(actual.getCargoIndexNumber()))
            .satisfies(
                e -> assertThat(e.getCentralBankCreditCode()).as("check centralBankCreditCode").isEqualTo(actual.getCentralBankCreditCode())
            )
            .satisfies(e -> assertThat(e.getComments()).as("check comments").isEqualTo(actual.getComments()))
            .satisfies(
                e ->
                    assertThat(e.getConstructorCountryCode())
                        .as("check constructorCountryCode")
                        .isEqualTo(actual.getConstructorCountryCode())
            )
            .satisfies(e -> assertThat(e.getCostDetails()).as("check costDetails").isEqualTo(actual.getCostDetails()))
            .satisfies(
                e ->
                    assertThat(e.getCottageNumber())
                        .as("check cottageNumber")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getCottageNumber())
            )
            .satisfies(
                e ->
                    assertThat(e.getCreditEquivalentAmount())
                        .as("check creditEquivalentAmount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getCreditEquivalentAmount())
            )
            .satisfies(e -> assertThat(e.getCurrency()).as("check currency").isEqualTo(actual.getCurrency()))
            .satisfies(
                e ->
                    assertThat(e.getCurrencyAmount())
                        .as("check currencyAmount")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getCurrencyAmount())
            )
            .satisfies(
                e ->
                    assertThat(e.getCurrencyRate())
                        .as("check currencyRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getCurrencyRate())
            )
            .satisfies(e -> assertThat(e.getCurrencySwiftCode()).as("check currencySwiftCode").isEqualTo(actual.getCurrencySwiftCode()))
            .satisfies(e -> assertThat(e.getCustomCompanyCode()).as("check customCompanyCode").isEqualTo(actual.getCustomCompanyCode()))
            .satisfies(e -> assertThat(e.getCustomerId()).as("check customerId").isEqualTo(actual.getCustomerId()))
            .satisfies(e -> assertThat(e.getDate()).as("check date").isEqualTo(actual.getDate()))
            .satisfies(e -> assertThat(e.getDestCountryCode()).as("check destCountryCode").isEqualTo(actual.getDestCountryCode()))
            .satisfies(e -> assertThat(e.getDestCustom()).as("check destCustom").isEqualTo(actual.getDestCustom()))
            .satisfies(e -> assertThat(e.getDestCustomCode()).as("check destCustomCode").isEqualTo(actual.getDestCustomCode()))
            .satisfies(
                e ->
                    assertThat(e.getDisciplinaryDocumentsIssued())
                        .as("check disciplinaryDocumentsIssued")
                        .isEqualTo(actual.getDisciplinaryDocumentsIssued())
            )
            .satisfies(
                e ->
                    assertThat(e.getDiscountPercent())
                        .as("check discountPercent")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getDiscountPercent())
            )
            .satisfies(
                e -> assertThat(e.getEightDigitOrderCode()).as("check eightDigitOrderCode").isEqualTo(actual.getEightDigitOrderCode())
            )
            .satisfies(
                e -> assertThat(e.getEntranceCustomCompany()).as("check entranceCustomCompany").isEqualTo(actual.getEntranceCustomCompany())
            )
            .satisfies(
                e ->
                    assertThat(e.getEntranceCustomCompanyId())
                        .as("check entranceCustomCompanyId")
                        .isEqualTo(actual.getEntranceCustomCompanyId())
            )
            .satisfies(e -> assertThat(e.getEvacuationPlace()).as("check evacuationPlace").isEqualTo(actual.getEvacuationPlace()))
            .satisfies(
                e -> assertThat(e.getEvaluationMethodCode()).as("check evaluationMethodCode").isEqualTo(actual.getEvaluationMethodCode())
            )
            .satisfies(e -> assertThat(e.getExportDate()).as("check exportDate").isEqualTo(actual.getExportDate()))
            .satisfies(e -> assertThat(e.getExporter()).as("check exporter").isEqualTo(actual.getExporter()))
            .satisfies(
                e -> assertThat(e.getExporterCountryCode()).as("check exporterCountryCode").isEqualTo(actual.getExporterCountryCode())
            )
            .satisfies(e -> assertThat(e.getExtensionDate()).as("check extensionDate").isEqualTo(actual.getExtensionDate()))
            .satisfies(e -> assertThat(e.getFactorTotalAmount()).as("check factorTotalAmount").isEqualTo(actual.getFactorTotalAmount()))
            .satisfies(e -> assertThat(e.getFreightIndexNumber()).as("check freightIndexNumber").isEqualTo(actual.getFreightIndexNumber()))
            .satisfies(e -> assertThat(e.getFrightLetter()).as("check frightLetter").isEqualTo(actual.getFrightLetter()))
            .satisfies(e -> assertThat(e.getImportLicence()).as("check importLicence").isEqualTo(actual.getImportLicence()))
            .satisfies(e -> assertThat(e.getImportLicense()).as("check importLicense").isEqualTo(actual.getImportLicense()))
            .satisfies(
                e ->
                    assertThat(e.getImpureWeight())
                        .as("check impureWeight")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getImpureWeight())
            )
            .satisfies(e -> assertThat(e.getIndices()).as("check indices").isEqualTo(actual.getIndices()))
            .satisfies(
                e -> assertThat(e.getInternalTransportType()).as("check internalTransportType").isEqualTo(actual.getInternalTransportType())
            )
            .satisfies(e -> assertThat(e.getIssuanceDate()).as("check issuanceDate").isEqualTo(actual.getIssuanceDate()))
            .satisfies(e -> assertThat(e.getItemNumber()).as("check itemNumber").isEqualTo(actual.getItemNumber()))
            .satisfies(e -> assertThat(e.getItems()).as("check items").isEqualTo(actual.getItems()))
            .satisfies(e -> assertThat(e.getJustificationAgent()).as("check justificationAgent").isEqualTo(actual.getJustificationAgent()))
            .satisfies(
                e ->
                    assertThat(e.getJustificationCurrencyRate())
                        .as("check justificationCurrencyRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getJustificationCurrencyRate())
            )
            .satisfies(e -> assertThat(e.getLicenceNumber()).as("check licenceNumber").isEqualTo(actual.getLicenceNumber()))
            .satisfies(
                e -> assertThat(e.getMakeCertificateNumber()).as("check makeCertificateNumber").isEqualTo(actual.getMakeCertificateNumber())
            )
            .satisfies(
                e ->
                    assertThat(e.getNewBorderTransportType())
                        .as("check newBorderTransportType")
                        .isEqualTo(actual.getNewBorderTransportType())
            )
            .satisfies(e -> assertThat(e.getNewEvacuationPlace()).as("check newEvacuationPlace").isEqualTo(actual.getNewEvacuationPlace()))
            .satisfies(
                e ->
                    assertThat(e.getNewInternalTransportType())
                        .as("check newInternalTransportType")
                        .isEqualTo(actual.getNewInternalTransportType())
            )
            .satisfies(
                e ->
                    assertThat(e.getNewProductItemCustomValue())
                        .as("check newProductItemCustomValue")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getNewProductItemCustomValue())
            )
            .satisfies(
                e -> assertThat(e.getOrderRegistrationDate()).as("check orderRegistrationDate").isEqualTo(actual.getOrderRegistrationDate())
            )
            .satisfies(
                e ->
                    assertThat(e.getOrderRegistrationNumber())
                        .as("check orderRegistrationNumber")
                        .isEqualTo(actual.getOrderRegistrationNumber())
            )
            .satisfies(e -> assertThat(e.getPapers()).as("check papers").isEqualTo(actual.getPapers()))
            .satisfies(e -> assertThat(e.getPaymentConditions()).as("check paymentConditions").isEqualTo(actual.getPaymentConditions()))
            .satisfies(e -> assertThat(e.getPreferences()).as("check preferences").isEqualTo(actual.getPreferences()))
            .satisfies(e -> assertThat(e.getProcedure()).as("check procedure").isEqualTo(actual.getProcedure()))
            .satisfies(e -> assertThat(e.getProductBoxCount()).as("check productBoxCount").isEqualTo(actual.getProductBoxCount()))
            .satisfies(e -> assertThat(e.getProductCode()).as("check productCode").isEqualTo(actual.getProductCode()))
            .satisfies(
                e ->
                    assertThat(e.getProductItemCost())
                        .as("check productItemCost")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getProductItemCost())
            )
            .satisfies(e -> assertThat(e.getProductItemCount()).as("check productItemCount").isEqualTo(actual.getProductItemCount()))
            .satisfies(
                e ->
                    assertThat(e.getProductItemCustomValue())
                        .as("check productItemCustomValue")
                        .isEqualTo(actual.getProductItemCustomValue())
            )
            .satisfies(e -> assertThat(e.getProductItemValue()).as("check productItemValue").isEqualTo(actual.getProductItemValue()))
            .satisfies(e -> assertThat(e.getProductMeasure()).as("check productMeasure").isEqualTo(actual.getProductMeasure()))
            .satisfies(e -> assertThat(e.getProductOwner()).as("check productOwner").isEqualTo(actual.getProductOwner()))
            .satisfies(e -> assertThat(e.getProductReleaseDate()).as("check productReleaseDate").isEqualTo(actual.getProductReleaseDate()))
            .satisfies(e -> assertThat(e.getProductType()).as("check productType").isEqualTo(actual.getProductType()))
            .satisfies(
                e ->
                    assertThat(e.getProductWorth())
                        .as("check productWorth")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getProductWorth())
            )
            .satisfies(
                e ->
                    assertThat(e.getProfitRate())
                        .as("check profitRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getProfitRate())
            )
            .satisfies(
                e ->
                    assertThat(e.getPureWeight())
                        .as("check pureWeight")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getPureWeight())
            )
            .satisfies(e -> assertThat(e.getQuota()).as("check quota").isEqualTo(actual.getQuota()))
            .satisfies(e -> assertThat(e.getReceiver()).as("check receiver").isEqualTo(actual.getReceiver()))
            .satisfies(e -> assertThat(e.getReferenceNumber()).as("check referenceNumber").isEqualTo(actual.getReferenceNumber()))
            .satisfies(e -> assertThat(e.getRegistrationNumber()).as("check registrationNumber").isEqualTo(actual.getRegistrationNumber()))
            .satisfies(
                e ->
                    assertThat(e.getReverseOfJustificationDisciplinaryDocumentNumber())
                        .as("check reverseOfJustificationDisciplinaryDocumentNumber")
                        .isEqualTo(actual.getReverseOfJustificationDisciplinaryDocumentNumber())
            )
            .satisfies(
                e ->
                    assertThat(e.getReverseOfJustificationDocumentsIssued())
                        .as("check reverseOfJustificationDocumentsIssued")
                        .isEqualTo(actual.getReverseOfJustificationDocumentsIssued())
            )
            .satisfies(
                e ->
                    assertThat(e.getRightsRate())
                        .as("check rightsRate")
                        .usingComparator(bigDecimalCompareTo)
                        .isEqualTo(actual.getRightsRate())
            )
            .satisfies(e -> assertThat(e.getTradingCountryCode()).as("check tradingCountryCode").isEqualTo(actual.getTradingCountryCode()))
            .satisfies(
                e -> assertThat(e.getTransactionTypeCode()).as("check transactionTypeCode").isEqualTo(actual.getTransactionTypeCode())
            )
            .satisfies(
                e -> assertThat(e.getWarehouseFactorNumber()).as("check warehouseFactorNumber").isEqualTo(actual.getWarehouseFactorNumber())
            )
            .satisfies(
                e ->
                    assertThat(e.getConstructorCountryName())
                        .as("check constructorCountryName")
                        .isEqualTo(actual.getConstructorCountryName())
            )
            .satisfies(e -> assertThat(e.getLastCountryName()).as("check lastCountryName").isEqualTo(actual.getLastCountryName()))
            .satisfies(e -> assertThat(e.getBranchCode()).as("check branchCode").isEqualTo(actual.getBranchCode()))
            .satisfies(
                e ->
                    assertThat(e.getJustificationCurrencyCode())
                        .as("check justificationCurrencyCode")
                        .isEqualTo(actual.getJustificationCurrencyCode())
            )
            .satisfies(e -> assertThat(e.getCreditCurrencyCode()).as("check creditCurrencyCode").isEqualTo(actual.getCreditCurrencyCode()))
            .satisfies(e -> assertThat(e.getIsMigrational()).as("check isMigrational").isEqualTo(actual.getIsMigrational()))
            .satisfies(
                e -> assertThat(e.getOriginalLetterImage()).as("check originalLetterImage").isEqualTo(actual.getOriginalLetterImage())
            )
            .satisfies(
                e ->
                    assertThat(e.getOriginalLetterImageContentType())
                        .as("check originalLetterImage contenty type")
                        .isEqualTo(actual.getOriginalLetterImageContentType())
            )
            .satisfies(e -> assertThat(e.getLetterImage()).as("check letterImage").isEqualTo(actual.getLetterImage()))
            .satisfies(
                e ->
                    assertThat(e.getLetterImageContentType())
                        .as("check letterImage contenty type")
                        .isEqualTo(actual.getLetterImageContentType())
            )
            .satisfies(e -> assertThat(e.getSourceCountryCode()).as("check sourceCountryCode").isEqualTo(actual.getSourceCountryCode()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCustomJustificationUpdatableRelationshipsEquals(CustomJustification expected, CustomJustification actual) {
        assertThat(expected)
            .as("Verify CustomJustification relationships")
            .satisfies(
                e ->
                    assertThat(e.getVehicleEnterNationality())
                        .as("check vehicleEnterNationality")
                        .isEqualTo(actual.getVehicleEnterNationality())
            )
            .satisfies(e -> assertThat(e.getContainer()).as("check container").isEqualTo(actual.getContainer()))
            .satisfies(
                e ->
                    assertThat(e.getVehicleCrossNationality())
                        .as("check vehicleCrossNationality")
                        .isEqualTo(actual.getVehicleCrossNationality())
            )
            .satisfies(e -> assertThat(e.getExportCustom()).as("check exportCustom").isEqualTo(actual.getExportCustom()))
            .satisfies(e -> assertThat(e.getEntranceCustom()).as("check entranceCustom").isEqualTo(actual.getEntranceCustom()))
            .satisfies(
                e -> assertThat(e.getTransportConditions()).as("check transportConditions").isEqualTo(actual.getTransportConditions())
            )
            .satisfies(e -> assertThat(e.getTradeTypeCode()).as("check tradeTypeCode").isEqualTo(actual.getTradeTypeCode()))
            .satisfies(
                e -> assertThat(e.getNewPaymentConditions()).as("check newPaymentConditions").isEqualTo(actual.getNewPaymentConditions())
            )
            .satisfies(
                e ->
                    assertThat(e.getJustificationDeductionAmount())
                        .as("check justificationDeductionAmount")
                        .isEqualTo(actual.getJustificationDeductionAmount())
            )
            .satisfies(e -> assertThat(e.getProducts()).as("check products").isEqualTo(actual.getProducts()))
            .satisfies(
                e ->
                    assertThat(e.getReverseOfJustificationDocumentTransactions())
                        .as("check reverseOfJustificationDocumentTransactions")
                        .isEqualTo(actual.getReverseOfJustificationDocumentTransactions())
            );
    }
}
