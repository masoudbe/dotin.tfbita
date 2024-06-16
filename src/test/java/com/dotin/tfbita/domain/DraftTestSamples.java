package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DraftTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Draft getDraftSample1() {
        return new Draft()
            .id(1L)
            .advisorDepositNumber("advisorDepositNumber1")
            .beneficiaryInfo("beneficiaryInfo1")
            .centralBankCode("centralBankCode1")
            .centralBankCodeReference("centralBankCodeReference1")
            .comment("comment1")
            .completionDate("completionDate1")
            .coveringBankDepositNumber("coveringBankDepositNumber1")
            .currencyExchangeDepositNumber("currencyExchangeDepositNumber1")
            .customerDepositNumber("customerDepositNumber1")
            .deliverDuration(1)
            .draftNumber("draftNumber1")
            .insuranceDate("insuranceDate1")
            .insuranceExpiryDate("insuranceExpiryDate1")
            .insuranceNumber("insuranceNumber1")
            .interfaceAdvisorDepositNumber("interfaceAdvisorDepositNumber1")
            .issueDate("issueDate1")
            .lastShipmentDate("lastShipmentDate1")
            .mainCustomerNumber(1L)
            .orderRegistrationDate("orderRegistrationDate1")
            .orderRegistrationExpiryDate("orderRegistrationExpiryDate1")
            .orderRegistrationNumber("orderRegistrationNumber1")
            .performaDate("performaDate1")
            .performaExpiryDate("performaExpiryDate1")
            .performaNumber("performaNumber1")
            .requestDate("requestDate1")
            .sanctionSerial("sanctionSerial1")
            .serial(1L)
            .sourceTransportPlace("sourceTransportPlace1")
            .swiftComment("swiftComment1")
            .paymentTool("paymentTool1")
            .letterNumberTazirat("letterNumberTazirat1")
            .letterDateTazirat("letterDateTazirat1")
            .loanNumber("loanNumber1")
            .destinationCountryCode("destinationCountryCode1")
            .beneficiaryCountryCode("beneficiaryCountryCode1")
            .producerCountryCode("producerCountryCode1")
            .branchCode("branchCode1")
            .operationalBranchCode("operationalBranchCode1")
            .certificateSenderBranchCode("certificateSenderBranchCode1")
            .mainAccountCurrencyCode("mainAccountCurrencyCode1")
            .orderRegCurrencyCode("orderRegCurrencyCode1")
            .chargedExchangeBrokerCurrency("chargedExchangeBrokerCurrency1")
            .registerationJustificationCurrencyCode("registerationJustificationCurrencyCode1")
            .currencyExchangeInfoTitle("currencyExchangeInfoTitle1")
            .transportationTypeName("transportationTypeName1")
            .accountInfoCode("accountInfoCode1")
            .customerNumbers(1L)
            .sanctionSerials("sanctionSerials1");
    }

    public static Draft getDraftSample2() {
        return new Draft()
            .id(2L)
            .advisorDepositNumber("advisorDepositNumber2")
            .beneficiaryInfo("beneficiaryInfo2")
            .centralBankCode("centralBankCode2")
            .centralBankCodeReference("centralBankCodeReference2")
            .comment("comment2")
            .completionDate("completionDate2")
            .coveringBankDepositNumber("coveringBankDepositNumber2")
            .currencyExchangeDepositNumber("currencyExchangeDepositNumber2")
            .customerDepositNumber("customerDepositNumber2")
            .deliverDuration(2)
            .draftNumber("draftNumber2")
            .insuranceDate("insuranceDate2")
            .insuranceExpiryDate("insuranceExpiryDate2")
            .insuranceNumber("insuranceNumber2")
            .interfaceAdvisorDepositNumber("interfaceAdvisorDepositNumber2")
            .issueDate("issueDate2")
            .lastShipmentDate("lastShipmentDate2")
            .mainCustomerNumber(2L)
            .orderRegistrationDate("orderRegistrationDate2")
            .orderRegistrationExpiryDate("orderRegistrationExpiryDate2")
            .orderRegistrationNumber("orderRegistrationNumber2")
            .performaDate("performaDate2")
            .performaExpiryDate("performaExpiryDate2")
            .performaNumber("performaNumber2")
            .requestDate("requestDate2")
            .sanctionSerial("sanctionSerial2")
            .serial(2L)
            .sourceTransportPlace("sourceTransportPlace2")
            .swiftComment("swiftComment2")
            .paymentTool("paymentTool2")
            .letterNumberTazirat("letterNumberTazirat2")
            .letterDateTazirat("letterDateTazirat2")
            .loanNumber("loanNumber2")
            .destinationCountryCode("destinationCountryCode2")
            .beneficiaryCountryCode("beneficiaryCountryCode2")
            .producerCountryCode("producerCountryCode2")
            .branchCode("branchCode2")
            .operationalBranchCode("operationalBranchCode2")
            .certificateSenderBranchCode("certificateSenderBranchCode2")
            .mainAccountCurrencyCode("mainAccountCurrencyCode2")
            .orderRegCurrencyCode("orderRegCurrencyCode2")
            .chargedExchangeBrokerCurrency("chargedExchangeBrokerCurrency2")
            .registerationJustificationCurrencyCode("registerationJustificationCurrencyCode2")
            .currencyExchangeInfoTitle("currencyExchangeInfoTitle2")
            .transportationTypeName("transportationTypeName2")
            .accountInfoCode("accountInfoCode2")
            .customerNumbers(2L)
            .sanctionSerials("sanctionSerials2");
    }

    public static Draft getDraftRandomSampleGenerator() {
        return new Draft()
            .id(longCount.incrementAndGet())
            .advisorDepositNumber(UUID.randomUUID().toString())
            .beneficiaryInfo(UUID.randomUUID().toString())
            .centralBankCode(UUID.randomUUID().toString())
            .centralBankCodeReference(UUID.randomUUID().toString())
            .comment(UUID.randomUUID().toString())
            .completionDate(UUID.randomUUID().toString())
            .coveringBankDepositNumber(UUID.randomUUID().toString())
            .currencyExchangeDepositNumber(UUID.randomUUID().toString())
            .customerDepositNumber(UUID.randomUUID().toString())
            .deliverDuration(intCount.incrementAndGet())
            .draftNumber(UUID.randomUUID().toString())
            .insuranceDate(UUID.randomUUID().toString())
            .insuranceExpiryDate(UUID.randomUUID().toString())
            .insuranceNumber(UUID.randomUUID().toString())
            .interfaceAdvisorDepositNumber(UUID.randomUUID().toString())
            .issueDate(UUID.randomUUID().toString())
            .lastShipmentDate(UUID.randomUUID().toString())
            .mainCustomerNumber(longCount.incrementAndGet())
            .orderRegistrationDate(UUID.randomUUID().toString())
            .orderRegistrationExpiryDate(UUID.randomUUID().toString())
            .orderRegistrationNumber(UUID.randomUUID().toString())
            .performaDate(UUID.randomUUID().toString())
            .performaExpiryDate(UUID.randomUUID().toString())
            .performaNumber(UUID.randomUUID().toString())
            .requestDate(UUID.randomUUID().toString())
            .sanctionSerial(UUID.randomUUID().toString())
            .serial(longCount.incrementAndGet())
            .sourceTransportPlace(UUID.randomUUID().toString())
            .swiftComment(UUID.randomUUID().toString())
            .paymentTool(UUID.randomUUID().toString())
            .letterNumberTazirat(UUID.randomUUID().toString())
            .letterDateTazirat(UUID.randomUUID().toString())
            .loanNumber(UUID.randomUUID().toString())
            .destinationCountryCode(UUID.randomUUID().toString())
            .beneficiaryCountryCode(UUID.randomUUID().toString())
            .producerCountryCode(UUID.randomUUID().toString())
            .branchCode(UUID.randomUUID().toString())
            .operationalBranchCode(UUID.randomUUID().toString())
            .certificateSenderBranchCode(UUID.randomUUID().toString())
            .mainAccountCurrencyCode(UUID.randomUUID().toString())
            .orderRegCurrencyCode(UUID.randomUUID().toString())
            .chargedExchangeBrokerCurrency(UUID.randomUUID().toString())
            .registerationJustificationCurrencyCode(UUID.randomUUID().toString())
            .currencyExchangeInfoTitle(UUID.randomUUID().toString())
            .transportationTypeName(UUID.randomUUID().toString())
            .accountInfoCode(UUID.randomUUID().toString())
            .customerNumbers(longCount.incrementAndGet())
            .sanctionSerials(UUID.randomUUID().toString());
    }
}
