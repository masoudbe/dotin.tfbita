package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DocumentTransactionTestSamples.*;
import static com.dotin.tfbita.domain.DraftTaxTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftTaxTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftTax.class);
        DraftTax draftTax1 = getDraftTaxSample1();
        DraftTax draftTax2 = new DraftTax();
        assertThat(draftTax1).isNotEqualTo(draftTax2);

        draftTax2.setId(draftTax1.getId());
        assertThat(draftTax1).isEqualTo(draftTax2);

        draftTax2 = getDraftTaxSample2();
        assertThat(draftTax1).isNotEqualTo(draftTax2);
    }

    @Test
    void documentTransactionTest() {
        DraftTax draftTax = getDraftTaxRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftTax.setDocumentTransaction(documentTransactionBack);
        assertThat(draftTax.getDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftTax.documentTransaction(null);
        assertThat(draftTax.getDocumentTransaction()).isNull();
    }

    @Test
    void returnDocumentTransactionTest() {
        DraftTax draftTax = getDraftTaxRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftTax.setReturnDocumentTransaction(documentTransactionBack);
        assertThat(draftTax.getReturnDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftTax.returnDocumentTransaction(null);
        assertThat(draftTax.getReturnDocumentTransaction()).isNull();
    }

    @Test
    void draftTest() {
        DraftTax draftTax = getDraftTaxRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftTax.setDraft(draftBack);
        assertThat(draftTax.getDraft()).isEqualTo(draftBack);

        draftTax.draft(null);
        assertThat(draftTax.getDraft()).isNull();
    }
}
