package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.CustomJustificationChildTestSamples.*;
import static com.dotin.tfbita.domain.CustomJustificationTestSamples.*;
import static com.dotin.tfbita.domain.CustomTestSamples.*;
import static com.dotin.tfbita.domain.DocumentTransactionTestSamples.*;
import static com.dotin.tfbita.domain.JustificationDeductionAmountTestSamples.*;
import static com.dotin.tfbita.domain.PaymentConditionTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static com.dotin.tfbita.domain.TradeTypeCodeTestSamples.*;
import static com.dotin.tfbita.domain.TransportationTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CustomJustificationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomJustification.class);
        CustomJustification customJustification1 = getCustomJustificationSample1();
        CustomJustification customJustification2 = new CustomJustification();
        assertThat(customJustification1).isNotEqualTo(customJustification2);

        customJustification2.setId(customJustification1.getId());
        assertThat(customJustification1).isEqualTo(customJustification2);

        customJustification2 = getCustomJustificationSample2();
        assertThat(customJustification1).isNotEqualTo(customJustification2);
    }

    @Test
    void customJustificationChildListTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        CustomJustificationChild customJustificationChildBack = getCustomJustificationChildRandomSampleGenerator();

        customJustification.addCustomJustificationChildList(customJustificationChildBack);
        assertThat(customJustification.getCustomJustificationChildLists()).containsOnly(customJustificationChildBack);
        assertThat(customJustificationChildBack.getCustomJustification()).isEqualTo(customJustification);

        customJustification.removeCustomJustificationChildList(customJustificationChildBack);
        assertThat(customJustification.getCustomJustificationChildLists()).doesNotContain(customJustificationChildBack);
        assertThat(customJustificationChildBack.getCustomJustification()).isNull();

        customJustification.customJustificationChildLists(new HashSet<>(Set.of(customJustificationChildBack)));
        assertThat(customJustification.getCustomJustificationChildLists()).containsOnly(customJustificationChildBack);
        assertThat(customJustificationChildBack.getCustomJustification()).isEqualTo(customJustification);

        customJustification.setCustomJustificationChildLists(new HashSet<>());
        assertThat(customJustification.getCustomJustificationChildLists()).doesNotContain(customJustificationChildBack);
        assertThat(customJustificationChildBack.getCustomJustification()).isNull();
    }

    @Test
    void vehicleEnterNationalityTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        customJustification.setVehicleEnterNationality(categoryElementBack);
        assertThat(customJustification.getVehicleEnterNationality()).isEqualTo(categoryElementBack);

        customJustification.vehicleEnterNationality(null);
        assertThat(customJustification.getVehicleEnterNationality()).isNull();
    }

    @Test
    void containerTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        customJustification.setContainer(categoryElementBack);
        assertThat(customJustification.getContainer()).isEqualTo(categoryElementBack);

        customJustification.container(null);
        assertThat(customJustification.getContainer()).isNull();
    }

    @Test
    void vehicleCrossNationalityTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        customJustification.setVehicleCrossNationality(categoryElementBack);
        assertThat(customJustification.getVehicleCrossNationality()).isEqualTo(categoryElementBack);

        customJustification.vehicleCrossNationality(null);
        assertThat(customJustification.getVehicleCrossNationality()).isNull();
    }

    @Test
    void exportCustomTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        customJustification.setExportCustom(customBack);
        assertThat(customJustification.getExportCustom()).isEqualTo(customBack);

        customJustification.exportCustom(null);
        assertThat(customJustification.getExportCustom()).isNull();
    }

    @Test
    void entranceCustomTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        Custom customBack = getCustomRandomSampleGenerator();

        customJustification.setEntranceCustom(customBack);
        assertThat(customJustification.getEntranceCustom()).isEqualTo(customBack);

        customJustification.entranceCustom(null);
        assertThat(customJustification.getEntranceCustom()).isNull();
    }

    @Test
    void transportConditionsTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        TransportationType transportationTypeBack = getTransportationTypeRandomSampleGenerator();

        customJustification.setTransportConditions(transportationTypeBack);
        assertThat(customJustification.getTransportConditions()).isEqualTo(transportationTypeBack);

        customJustification.transportConditions(null);
        assertThat(customJustification.getTransportConditions()).isNull();
    }

    @Test
    void tradeTypeCodeTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        TradeTypeCode tradeTypeCodeBack = getTradeTypeCodeRandomSampleGenerator();

        customJustification.setTradeTypeCode(tradeTypeCodeBack);
        assertThat(customJustification.getTradeTypeCode()).isEqualTo(tradeTypeCodeBack);

        customJustification.tradeTypeCode(null);
        assertThat(customJustification.getTradeTypeCode()).isNull();
    }

    @Test
    void newPaymentConditionsTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        PaymentCondition paymentConditionBack = getPaymentConditionRandomSampleGenerator();

        customJustification.setNewPaymentConditions(paymentConditionBack);
        assertThat(customJustification.getNewPaymentConditions()).isEqualTo(paymentConditionBack);

        customJustification.newPaymentConditions(null);
        assertThat(customJustification.getNewPaymentConditions()).isNull();
    }

    @Test
    void justificationDeductionAmountTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        JustificationDeductionAmount justificationDeductionAmountBack = getJustificationDeductionAmountRandomSampleGenerator();

        customJustification.setJustificationDeductionAmount(justificationDeductionAmountBack);
        assertThat(customJustification.getJustificationDeductionAmount()).isEqualTo(justificationDeductionAmountBack);

        customJustification.justificationDeductionAmount(null);
        assertThat(customJustification.getJustificationDeductionAmount()).isNull();
    }

    @Test
    void productsTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        customJustification.addProducts(productBack);
        assertThat(customJustification.getProducts()).containsOnly(productBack);

        customJustification.removeProducts(productBack);
        assertThat(customJustification.getProducts()).doesNotContain(productBack);

        customJustification.products(new HashSet<>(Set.of(productBack)));
        assertThat(customJustification.getProducts()).containsOnly(productBack);

        customJustification.setProducts(new HashSet<>());
        assertThat(customJustification.getProducts()).doesNotContain(productBack);
    }

    @Test
    void reverseOfJustificationDocumentTransactionsTest() {
        CustomJustification customJustification = getCustomJustificationRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        customJustification.addReverseOfJustificationDocumentTransactions(documentTransactionBack);
        assertThat(customJustification.getReverseOfJustificationDocumentTransactions()).containsOnly(documentTransactionBack);

        customJustification.removeReverseOfJustificationDocumentTransactions(documentTransactionBack);
        assertThat(customJustification.getReverseOfJustificationDocumentTransactions()).doesNotContain(documentTransactionBack);

        customJustification.reverseOfJustificationDocumentTransactions(new HashSet<>(Set.of(documentTransactionBack)));
        assertThat(customJustification.getReverseOfJustificationDocumentTransactions()).containsOnly(documentTransactionBack);

        customJustification.setReverseOfJustificationDocumentTransactions(new HashSet<>());
        assertThat(customJustification.getReverseOfJustificationDocumentTransactions()).doesNotContain(documentTransactionBack);
    }
}
