package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftCustomJustificationTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
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
    void productTest() throws Exception {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        draftReceipt.addProduct(productBack);
        assertThat(draftReceipt.getProducts()).containsOnly(productBack);
        assertThat(productBack.getDraftProductInfos()).isEqualTo(draftReceipt);

        draftReceipt.removeProduct(productBack);
        assertThat(draftReceipt.getProducts()).doesNotContain(productBack);
        assertThat(productBack.getDraftProductInfos()).isNull();

        draftReceipt.products(new HashSet<>(Set.of(productBack)));
        assertThat(draftReceipt.getProducts()).containsOnly(productBack);
        assertThat(productBack.getDraftProductInfos()).isEqualTo(draftReceipt);

        draftReceipt.setProducts(new HashSet<>());
        assertThat(draftReceipt.getProducts()).doesNotContain(productBack);
        assertThat(productBack.getDraftProductInfos()).isNull();
    }

    @Test
    void receiptsTest() throws Exception {
        DraftReceipt draftReceipt = getDraftReceiptRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftReceipt.setReceipts(draftBack);
        assertThat(draftReceipt.getReceipts()).isEqualTo(draftBack);

        draftReceipt.receipts(null);
        assertThat(draftReceipt.getReceipts()).isNull();
    }

    @Test
    void draftCustomJustificationTest() throws Exception {
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
