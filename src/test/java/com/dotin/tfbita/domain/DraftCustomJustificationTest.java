package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DraftCustomJustificationTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DraftCustomJustificationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftCustomJustification.class);
        DraftCustomJustification draftCustomJustification1 = getDraftCustomJustificationSample1();
        DraftCustomJustification draftCustomJustification2 = new DraftCustomJustification();
        assertThat(draftCustomJustification1).isNotEqualTo(draftCustomJustification2);

        draftCustomJustification2.setId(draftCustomJustification1.getId());
        assertThat(draftCustomJustification1).isEqualTo(draftCustomJustification2);

        draftCustomJustification2 = getDraftCustomJustificationSample2();
        assertThat(draftCustomJustification1).isNotEqualTo(draftCustomJustification2);
    }

    @Test
    void draftReceiptsTest() {
        DraftCustomJustification draftCustomJustification = getDraftCustomJustificationRandomSampleGenerator();
        DraftReceipt draftReceiptBack = getDraftReceiptRandomSampleGenerator();

        draftCustomJustification.addDraftReceipts(draftReceiptBack);
        assertThat(draftCustomJustification.getDraftReceipts()).containsOnly(draftReceiptBack);

        draftCustomJustification.removeDraftReceipts(draftReceiptBack);
        assertThat(draftCustomJustification.getDraftReceipts()).doesNotContain(draftReceiptBack);

        draftCustomJustification.draftReceipts(new HashSet<>(Set.of(draftReceiptBack)));
        assertThat(draftCustomJustification.getDraftReceipts()).containsOnly(draftReceiptBack);

        draftCustomJustification.setDraftReceipts(new HashSet<>());
        assertThat(draftCustomJustification.getDraftReceipts()).doesNotContain(draftReceiptBack);
    }

    @Test
    void draftTest() {
        DraftCustomJustification draftCustomJustification = getDraftCustomJustificationRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftCustomJustification.setDraft(draftBack);
        assertThat(draftCustomJustification.getDraft()).isEqualTo(draftBack);

        draftCustomJustification.draft(null);
        assertThat(draftCustomJustification.getDraft()).isNull();
    }
}
