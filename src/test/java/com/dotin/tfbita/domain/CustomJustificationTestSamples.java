package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CustomJustificationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CustomJustification getCustomJustificationSample1() {
        return new CustomJustification()
            .id(1L)
            .agentId(1L)
            .agriculturalPublicPolicies("agriculturalPublicPolicies1")
            .assessmentPlace("assessmentPlace1")
            .attachedDocuments("attachedDocuments1")
            .bankCode("bankCode1")
            .borderTransportType("borderTransportType1")
            .boxCountType("boxCountType1")
            .boxMarks("boxMarks1")
            .cargoIndexNumber("cargoIndexNumber1")
            .centralBankCreditCode("centralBankCreditCode1")
            .comments("comments1")
            .constructorCountryCode("constructorCountryCode1")
            .costDetails("costDetails1")
            .currency("currency1")
            .currencySwiftCode("currencySwiftCode1")
            .customCompanyCode("customCompanyCode1")
            .customerId(1L)
            .date("date1")
            .destCountryCode("destCountryCode1")
            .destCustom("destCustom1")
            .destCustomCode("destCustomCode1")
            .eightDigitOrderCode("eightDigitOrderCode1")
            .entranceCustomCompany(1L)
            .entranceCustomCompanyId(1L)
            .evacuationPlace("evacuationPlace1")
            .evaluationMethodCode("evaluationMethodCode1")
            .exportDate("exportDate1")
            .exporter("exporter1")
            .exporterCountryCode("exporterCountryCode1")
            .extensionDate("extensionDate1")
            .factorTotalAmount("factorTotalAmount1")
            .freightIndexNumber("freightIndexNumber1")
            .frightLetter("frightLetter1")
            .importLicence("importLicence1")
            .importLicense("importLicense1")
            .indices("indices1")
            .internalTransportType("internalTransportType1")
            .issuanceDate("issuanceDate1")
            .itemNumber("itemNumber1")
            .items(1)
            .justificationAgent("justificationAgent1")
            .licenceNumber("licenceNumber1")
            .makeCertificateNumber("makeCertificateNumber1")
            .newBorderTransportType(1L)
            .newEvacuationPlace(1L)
            .newInternalTransportType(1L)
            .orderRegistrationDate("orderRegistrationDate1")
            .orderRegistrationNumber("orderRegistrationNumber1")
            .papers(1)
            .paymentConditions("paymentConditions1")
            .preferences("preferences1")
            .procedure("procedure1")
            .productBoxCount(1)
            .productCode("productCode1")
            .productItemCount("productItemCount1")
            .productItemCustomValue("productItemCustomValue1")
            .productItemValue("productItemValue1")
            .productMeasure("productMeasure1")
            .productOwner("productOwner1")
            .productReleaseDate("productReleaseDate1")
            .productType("productType1")
            .quota("quota1")
            .receiver("receiver1")
            .referenceNumber(1)
            .registrationNumber("registrationNumber1")
            .reverseOfJustificationDisciplinaryDocumentNumber("reverseOfJustificationDisciplinaryDocumentNumber1")
            .tradingCountryCode("tradingCountryCode1")
            .transactionTypeCode("transactionTypeCode1")
            .warehouseFactorNumber("warehouseFactorNumber1")
            .constructorCountryName("constructorCountryName1")
            .lastCountryName("lastCountryName1")
            .branchCode("branchCode1")
            .justificationCurrencyCode("justificationCurrencyCode1")
            .creditCurrencyCode("creditCurrencyCode1")
            .sourceCountryCode("sourceCountryCode1");
    }

    public static CustomJustification getCustomJustificationSample2() {
        return new CustomJustification()
            .id(2L)
            .agentId(2L)
            .agriculturalPublicPolicies("agriculturalPublicPolicies2")
            .assessmentPlace("assessmentPlace2")
            .attachedDocuments("attachedDocuments2")
            .bankCode("bankCode2")
            .borderTransportType("borderTransportType2")
            .boxCountType("boxCountType2")
            .boxMarks("boxMarks2")
            .cargoIndexNumber("cargoIndexNumber2")
            .centralBankCreditCode("centralBankCreditCode2")
            .comments("comments2")
            .constructorCountryCode("constructorCountryCode2")
            .costDetails("costDetails2")
            .currency("currency2")
            .currencySwiftCode("currencySwiftCode2")
            .customCompanyCode("customCompanyCode2")
            .customerId(2L)
            .date("date2")
            .destCountryCode("destCountryCode2")
            .destCustom("destCustom2")
            .destCustomCode("destCustomCode2")
            .eightDigitOrderCode("eightDigitOrderCode2")
            .entranceCustomCompany(2L)
            .entranceCustomCompanyId(2L)
            .evacuationPlace("evacuationPlace2")
            .evaluationMethodCode("evaluationMethodCode2")
            .exportDate("exportDate2")
            .exporter("exporter2")
            .exporterCountryCode("exporterCountryCode2")
            .extensionDate("extensionDate2")
            .factorTotalAmount("factorTotalAmount2")
            .freightIndexNumber("freightIndexNumber2")
            .frightLetter("frightLetter2")
            .importLicence("importLicence2")
            .importLicense("importLicense2")
            .indices("indices2")
            .internalTransportType("internalTransportType2")
            .issuanceDate("issuanceDate2")
            .itemNumber("itemNumber2")
            .items(2)
            .justificationAgent("justificationAgent2")
            .licenceNumber("licenceNumber2")
            .makeCertificateNumber("makeCertificateNumber2")
            .newBorderTransportType(2L)
            .newEvacuationPlace(2L)
            .newInternalTransportType(2L)
            .orderRegistrationDate("orderRegistrationDate2")
            .orderRegistrationNumber("orderRegistrationNumber2")
            .papers(2)
            .paymentConditions("paymentConditions2")
            .preferences("preferences2")
            .procedure("procedure2")
            .productBoxCount(2)
            .productCode("productCode2")
            .productItemCount("productItemCount2")
            .productItemCustomValue("productItemCustomValue2")
            .productItemValue("productItemValue2")
            .productMeasure("productMeasure2")
            .productOwner("productOwner2")
            .productReleaseDate("productReleaseDate2")
            .productType("productType2")
            .quota("quota2")
            .receiver("receiver2")
            .referenceNumber(2)
            .registrationNumber("registrationNumber2")
            .reverseOfJustificationDisciplinaryDocumentNumber("reverseOfJustificationDisciplinaryDocumentNumber2")
            .tradingCountryCode("tradingCountryCode2")
            .transactionTypeCode("transactionTypeCode2")
            .warehouseFactorNumber("warehouseFactorNumber2")
            .constructorCountryName("constructorCountryName2")
            .lastCountryName("lastCountryName2")
            .branchCode("branchCode2")
            .justificationCurrencyCode("justificationCurrencyCode2")
            .creditCurrencyCode("creditCurrencyCode2")
            .sourceCountryCode("sourceCountryCode2");
    }

    public static CustomJustification getCustomJustificationRandomSampleGenerator() {
        return new CustomJustification()
            .id(longCount.incrementAndGet())
            .agentId(longCount.incrementAndGet())
            .agriculturalPublicPolicies(UUID.randomUUID().toString())
            .assessmentPlace(UUID.randomUUID().toString())
            .attachedDocuments(UUID.randomUUID().toString())
            .bankCode(UUID.randomUUID().toString())
            .borderTransportType(UUID.randomUUID().toString())
            .boxCountType(UUID.randomUUID().toString())
            .boxMarks(UUID.randomUUID().toString())
            .cargoIndexNumber(UUID.randomUUID().toString())
            .centralBankCreditCode(UUID.randomUUID().toString())
            .comments(UUID.randomUUID().toString())
            .constructorCountryCode(UUID.randomUUID().toString())
            .costDetails(UUID.randomUUID().toString())
            .currency(UUID.randomUUID().toString())
            .currencySwiftCode(UUID.randomUUID().toString())
            .customCompanyCode(UUID.randomUUID().toString())
            .customerId(longCount.incrementAndGet())
            .date(UUID.randomUUID().toString())
            .destCountryCode(UUID.randomUUID().toString())
            .destCustom(UUID.randomUUID().toString())
            .destCustomCode(UUID.randomUUID().toString())
            .eightDigitOrderCode(UUID.randomUUID().toString())
            .entranceCustomCompany(longCount.incrementAndGet())
            .entranceCustomCompanyId(longCount.incrementAndGet())
            .evacuationPlace(UUID.randomUUID().toString())
            .evaluationMethodCode(UUID.randomUUID().toString())
            .exportDate(UUID.randomUUID().toString())
            .exporter(UUID.randomUUID().toString())
            .exporterCountryCode(UUID.randomUUID().toString())
            .extensionDate(UUID.randomUUID().toString())
            .factorTotalAmount(UUID.randomUUID().toString())
            .freightIndexNumber(UUID.randomUUID().toString())
            .frightLetter(UUID.randomUUID().toString())
            .importLicence(UUID.randomUUID().toString())
            .importLicense(UUID.randomUUID().toString())
            .indices(UUID.randomUUID().toString())
            .internalTransportType(UUID.randomUUID().toString())
            .issuanceDate(UUID.randomUUID().toString())
            .itemNumber(UUID.randomUUID().toString())
            .items(intCount.incrementAndGet())
            .justificationAgent(UUID.randomUUID().toString())
            .licenceNumber(UUID.randomUUID().toString())
            .makeCertificateNumber(UUID.randomUUID().toString())
            .newBorderTransportType(longCount.incrementAndGet())
            .newEvacuationPlace(longCount.incrementAndGet())
            .newInternalTransportType(longCount.incrementAndGet())
            .orderRegistrationDate(UUID.randomUUID().toString())
            .orderRegistrationNumber(UUID.randomUUID().toString())
            .papers(intCount.incrementAndGet())
            .paymentConditions(UUID.randomUUID().toString())
            .preferences(UUID.randomUUID().toString())
            .procedure(UUID.randomUUID().toString())
            .productBoxCount(intCount.incrementAndGet())
            .productCode(UUID.randomUUID().toString())
            .productItemCount(UUID.randomUUID().toString())
            .productItemCustomValue(UUID.randomUUID().toString())
            .productItemValue(UUID.randomUUID().toString())
            .productMeasure(UUID.randomUUID().toString())
            .productOwner(UUID.randomUUID().toString())
            .productReleaseDate(UUID.randomUUID().toString())
            .productType(UUID.randomUUID().toString())
            .quota(UUID.randomUUID().toString())
            .receiver(UUID.randomUUID().toString())
            .referenceNumber(intCount.incrementAndGet())
            .registrationNumber(UUID.randomUUID().toString())
            .reverseOfJustificationDisciplinaryDocumentNumber(UUID.randomUUID().toString())
            .tradingCountryCode(UUID.randomUUID().toString())
            .transactionTypeCode(UUID.randomUUID().toString())
            .warehouseFactorNumber(UUID.randomUUID().toString())
            .constructorCountryName(UUID.randomUUID().toString())
            .lastCountryName(UUID.randomUUID().toString())
            .branchCode(UUID.randomUUID().toString())
            .justificationCurrencyCode(UUID.randomUUID().toString())
            .creditCurrencyCode(UUID.randomUUID().toString())
            .sourceCountryCode(UUID.randomUUID().toString());
    }
}
