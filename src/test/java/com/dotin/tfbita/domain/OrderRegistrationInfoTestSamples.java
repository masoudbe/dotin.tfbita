package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OrderRegistrationInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OrderRegistrationInfo getOrderRegistrationInfoSample1() {
        return new OrderRegistrationInfo()
            .id(1L)
            .orderRegNum("orderRegNum1")
            .startOrderRegDate("startOrderRegDate1")
            .endOrderRegDate("endOrderRegDate1")
            .requestNumber("requestNumber1")
            .bankCode("bankCode1")
            .branchCode("branchCode1")
            .customerNumber("customerNumber1")
            .applicantNationalcode("applicantNationalcode1")
            .performaNumber("performaNumber1")
            .performaDate("performaDate1")
            .performaExpiryDate("performaExpiryDate1")
            .performaDatePersian("performaDatePersian1")
            .performaExpiryDatePersian("performaExpiryDatePersian1")
            .infoSubmissionDate("infoSubmissionDate1")
            .sellerName("sellerName1")
            .beneficiaryCountryCode("beneficiaryCountryCode1")
            .producerCountriesCode("producerCountriesCode1")
            .sourceCountry("sourceCountry1")
            .deliveryTimeOfGoods("deliveryTimeOfGoods1")
            .currencyCode("currencyCode1")
            .fileNumber("fileNumber1")
            .certificateNumber("certificateNumber1")
            .centralBankCode("centralBankCode1")
            .externalCustomer(1L)
            .sumRedemptionDuration(1L)
            .extendedDayNumber(1L)
            .mainGroupProductCode("mainGroupProductCode1")
            .producerCountries("producerCountries1")
            .commissionTransactionNumber("commissionTransactionNumber1");
    }

    public static OrderRegistrationInfo getOrderRegistrationInfoSample2() {
        return new OrderRegistrationInfo()
            .id(2L)
            .orderRegNum("orderRegNum2")
            .startOrderRegDate("startOrderRegDate2")
            .endOrderRegDate("endOrderRegDate2")
            .requestNumber("requestNumber2")
            .bankCode("bankCode2")
            .branchCode("branchCode2")
            .customerNumber("customerNumber2")
            .applicantNationalcode("applicantNationalcode2")
            .performaNumber("performaNumber2")
            .performaDate("performaDate2")
            .performaExpiryDate("performaExpiryDate2")
            .performaDatePersian("performaDatePersian2")
            .performaExpiryDatePersian("performaExpiryDatePersian2")
            .infoSubmissionDate("infoSubmissionDate2")
            .sellerName("sellerName2")
            .beneficiaryCountryCode("beneficiaryCountryCode2")
            .producerCountriesCode("producerCountriesCode2")
            .sourceCountry("sourceCountry2")
            .deliveryTimeOfGoods("deliveryTimeOfGoods2")
            .currencyCode("currencyCode2")
            .fileNumber("fileNumber2")
            .certificateNumber("certificateNumber2")
            .centralBankCode("centralBankCode2")
            .externalCustomer(2L)
            .sumRedemptionDuration(2L)
            .extendedDayNumber(2L)
            .mainGroupProductCode("mainGroupProductCode2")
            .producerCountries("producerCountries2")
            .commissionTransactionNumber("commissionTransactionNumber2");
    }

    public static OrderRegistrationInfo getOrderRegistrationInfoRandomSampleGenerator() {
        return new OrderRegistrationInfo()
            .id(longCount.incrementAndGet())
            .orderRegNum(UUID.randomUUID().toString())
            .startOrderRegDate(UUID.randomUUID().toString())
            .endOrderRegDate(UUID.randomUUID().toString())
            .requestNumber(UUID.randomUUID().toString())
            .bankCode(UUID.randomUUID().toString())
            .branchCode(UUID.randomUUID().toString())
            .customerNumber(UUID.randomUUID().toString())
            .applicantNationalcode(UUID.randomUUID().toString())
            .performaNumber(UUID.randomUUID().toString())
            .performaDate(UUID.randomUUID().toString())
            .performaExpiryDate(UUID.randomUUID().toString())
            .performaDatePersian(UUID.randomUUID().toString())
            .performaExpiryDatePersian(UUID.randomUUID().toString())
            .infoSubmissionDate(UUID.randomUUID().toString())
            .sellerName(UUID.randomUUID().toString())
            .beneficiaryCountryCode(UUID.randomUUID().toString())
            .producerCountriesCode(UUID.randomUUID().toString())
            .sourceCountry(UUID.randomUUID().toString())
            .deliveryTimeOfGoods(UUID.randomUUID().toString())
            .currencyCode(UUID.randomUUID().toString())
            .fileNumber(UUID.randomUUID().toString())
            .certificateNumber(UUID.randomUUID().toString())
            .centralBankCode(UUID.randomUUID().toString())
            .externalCustomer(longCount.incrementAndGet())
            .sumRedemptionDuration(longCount.incrementAndGet())
            .extendedDayNumber(longCount.incrementAndGet())
            .mainGroupProductCode(UUID.randomUUID().toString())
            .producerCountries(UUID.randomUUID().toString())
            .commissionTransactionNumber(UUID.randomUUID().toString());
    }
}
