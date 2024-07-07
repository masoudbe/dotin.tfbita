package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftProductInfoTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;
import static com.dotin.tfbita.domain.ProductTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftProductInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftProductInfo.class);
        DraftProductInfo draftProductInfo1 = getDraftProductInfoSample1();
        DraftProductInfo draftProductInfo2 = new DraftProductInfo();
        assertThat(draftProductInfo1).isNotEqualTo(draftProductInfo2);

        draftProductInfo2.setId(draftProductInfo1.getId());
        assertThat(draftProductInfo1).isEqualTo(draftProductInfo2);

        draftProductInfo2 = getDraftProductInfoSample2();
        assertThat(draftProductInfo1).isNotEqualTo(draftProductInfo2);
    }

    @Test
    void productTest() {
        DraftProductInfo draftProductInfo = getDraftProductInfoRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        draftProductInfo.setProduct(productBack);
        assertThat(draftProductInfo.getProduct()).isEqualTo(productBack);

        draftProductInfo.product(null);
        assertThat(draftProductInfo.getProduct()).isNull();
    }

    @Test
    void draftReceiptTest() {
        DraftProductInfo draftProductInfo = getDraftProductInfoRandomSampleGenerator();
        DraftReceipt draftReceiptBack = getDraftReceiptRandomSampleGenerator();

        draftProductInfo.setDraftReceipt(draftReceiptBack);
        assertThat(draftProductInfo.getDraftReceipt()).isEqualTo(draftReceiptBack);

        draftProductInfo.draftReceipt(null);
        assertThat(draftProductInfo.getDraftReceipt()).isNull();
    }
}
