package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CategoryElementTestSamples.*;
import static com.dotin.tfbita.domain.DraftCustomJustificationTestSamples.*;
import static com.dotin.tfbita.domain.DraftProductInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainerTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.PaymentCurrencyRateTypeTestSamples.*;
import static com.dotin.tfbita.domain.PaymentItemTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DraftReceiptTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftReceipt.class);
        DraftReceipt draftReceipt1 = getDraftReceiptSample1();
        DraftReceipt draftReceipt2 = new DraftReceipt();
        assertThat(draftReceipt1).isNotEqualTo(draftReceipt2);

        draftReceipt2.setId(draftReceipt1.getId());
        assertThat(draftReceipt1).isEqualTo(draftReceipt2);

        draftReceipt2 = getDraftReceiptSample2();
        assertThat(draftReceipt1).isNotEqualTo(draftReceipt2);
    }

    @Test
    void draftProductInfosTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        DraftProductInfo draftProductInfoBack = getDraftProductInfoRandomSampleGenerator();

        draftReceipt.addDraftProductInfos(draftProductInfoBack);
        assertThat(draftReceipt.getDraftProductInfos()).containsOnly(draftProductInfoBack);
        assertThat(draftProductInfoBack.getDraftReceipt()).isEqualTo(draftReceipt);

        draftReceipt.removeDraftProductInfos(draftProductInfoBack);
        assertThat(draftReceipt.getDraftProductInfos()).doesNotContain(draftProductInfoBack);
        assertThat(draftProductInfoBack.getDraftReceipt()).isNull();

        draftReceipt.draftProductInfos(new HashSet<>(Set.of(draftProductInfoBack)));
        assertThat(draftReceipt.getDraftProductInfos()).containsOnly(draftProductInfoBack);
        assertThat(draftProductInfoBack.getDraftReceipt()).isEqualTo(draftReceipt);

        draftReceipt.setDraftProductInfos(new HashSet<>());
        assertThat(draftReceipt.getDraftProductInfos()).doesNotContain(draftProductInfoBack);
        assertThat(draftProductInfoBack.getDraftReceipt()).isNull();
    }

    @Test
    void productDimensionTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draftReceipt.setProductDimension(categoryElementBack);
        assertThat(draftReceipt.getProductDimension()).isEqualTo(categoryElementBack);

        draftReceipt.productDimension(null);
        assertThat(draftReceipt.getProductDimension()).isNull();
    }

    @Test
    void stateOfDocumentsTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draftReceipt.setStateOfDocuments(categoryElementBack);
        assertThat(draftReceipt.getStateOfDocuments()).isEqualTo(categoryElementBack);

        draftReceipt.stateOfDocuments(null);
        assertThat(draftReceipt.getStateOfDocuments()).isNull();
    }

    @Test
    void currencyProvisionFileTypeTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        CategoryElement categoryElementBack = getCategoryElementRandomSampleGenerator();

        draftReceipt.setCurrencyProvisionFileType(categoryElementBack);
        assertThat(draftReceipt.getCurrencyProvisionFileType()).isEqualTo(categoryElementBack);

        draftReceipt.currencyProvisionFileType(null);
        assertThat(draftReceipt.getCurrencyProvisionFileType()).isNull();
    }

    @Test
    void paymentCurrencyRateTypeTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        PaymentCurrencyRateType paymentCurrencyRateTypeBack = getPaymentCurrencyRateTypeRandomSampleGenerator();

        draftReceipt.setPaymentCurrencyRateType(paymentCurrencyRateTypeBack);
        assertThat(draftReceipt.getPaymentCurrencyRateType()).isEqualTo(paymentCurrencyRateTypeBack);

        draftReceipt.paymentCurrencyRateType(null);
        assertThat(draftReceipt.getPaymentCurrencyRateType()).isNull();
    }

    @Test
    void paymentItemTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        PaymentItemType paymentItemTypeBack = getPaymentItemTypeRandomSampleGenerator();

        draftReceipt.setPaymentItem(paymentItemTypeBack);
        assertThat(draftReceipt.getPaymentItem()).isEqualTo(paymentItemTypeBack);

        draftReceipt.paymentItem(null);
        assertThat(draftReceipt.getPaymentItem()).isNull();
    }

    @Test
    void documentTransactionContainerTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainerBack =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();

        draftReceipt.setDocumentTransactionContainer(draftReceiptDocumentTransactionContainerBack);
        assertThat(draftReceipt.getDocumentTransactionContainer()).isEqualTo(draftReceiptDocumentTransactionContainerBack);

        draftReceipt.documentTransactionContainer(null);
        assertThat(draftReceipt.getDocumentTransactionContainer()).isNull();
    }

    @Test
    void draftTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftReceipt.setDraft(draftBack);
        assertThat(draftReceipt.getDraft()).isEqualTo(draftBack);

        draftReceipt.draft(null);
        assertThat(draftReceipt.getDraft()).isNull();
    }

    @Test
    void draftCustomJustificationTest() {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        DraftCustomJustification draftCustomJustificationBack = getDraftCustomJustificationRandomSampleGenerator();

        draftReceipt.addDraftCustomJustification(draftCustomJustificationBack);
        assertThat(draftReceipt.getDraftCustomJustifications()).containsOnly(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraftReceipts()).containsOnly(draftReceipt);

        draftReceipt.removeDraftCustomJustification(draftCustomJustificationBack);
        assertThat(draftReceipt.getDraftCustomJustifications()).doesNotContain(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraftReceipts()).doesNotContain(draftReceipt);

        draftReceipt.draftCustomJustifications(new HashSet<>(Set.of(draftCustomJustificationBack)));
        assertThat(draftReceipt.getDraftCustomJustifications()).containsOnly(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraftReceipts()).containsOnly(draftReceipt);

        draftReceipt.setDraftCustomJustifications(new HashSet<>());
        assertThat(draftReceipt.getDraftCustomJustifications()).doesNotContain(draftCustomJustificationBack);
        assertThat(draftCustomJustificationBack.getDraftReceipts()).doesNotContain(draftReceipt);
    }
}
