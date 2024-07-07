package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DocumentTransactionTestSamples.*;
import static com.dotin.tfbita.domain.DraftDocumentTransactionContainerTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainerTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DraftReceiptDocumentTransactionContainerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftReceiptDocumentTransactionContainer.class);
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer1 =
            getDraftReceiptDocumentTransactionContainerSample1();
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer2 = new DraftReceiptDocumentTransactionContainer();
        assertThat(draftReceiptDocumentTransactionContainer1).isNotEqualTo(draftReceiptDocumentTransactionContainer2);

        draftReceiptDocumentTransactionContainer2.setId(draftReceiptDocumentTransactionContainer1.getId());
        assertThat(draftReceiptDocumentTransactionContainer1).isEqualTo(draftReceiptDocumentTransactionContainer2);

        draftReceiptDocumentTransactionContainer2 = getDraftReceiptDocumentTransactionContainerSample2();
        assertThat(draftReceiptDocumentTransactionContainer1).isNotEqualTo(draftReceiptDocumentTransactionContainer2);
    }

    @Test
    void receiptIssueDocumentTransactionTest() {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftReceiptDocumentTransactionContainer.setReceiptIssueDocumentTransaction(documentTransactionBack);
        assertThat(draftReceiptDocumentTransactionContainer.getReceiptIssueDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftReceiptDocumentTransactionContainer.receiptIssueDocumentTransaction(null);
        assertThat(draftReceiptDocumentTransactionContainer.getReceiptIssueDocumentTransaction()).isNull();
    }

    @Test
    void freightLetterStampCostDocumentTransactionTest() {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftReceiptDocumentTransactionContainer.setFreightLetterStampCostDocumentTransaction(documentTransactionBack);
        assertThat(draftReceiptDocumentTransactionContainer.getFreightLetterStampCostDocumentTransaction()).isEqualTo(
            documentTransactionBack
        );

        draftReceiptDocumentTransactionContainer.freightLetterStampCostDocumentTransaction(null);
        assertThat(draftReceiptDocumentTransactionContainer.getFreightLetterStampCostDocumentTransaction()).isNull();
    }

    @Test
    void deliverDocumentTransactionTest() {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftReceiptDocumentTransactionContainer.setDeliverDocumentTransaction(documentTransactionBack);
        assertThat(draftReceiptDocumentTransactionContainer.getDeliverDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftReceiptDocumentTransactionContainer.deliverDocumentTransaction(null);
        assertThat(draftReceiptDocumentTransactionContainer.getDeliverDocumentTransaction()).isNull();
    }

    @Test
    void documentTransactionCanceledDeliverTest() {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftReceiptDocumentTransactionContainer.setDocumentTransactionCanceledDeliver(documentTransactionBack);
        assertThat(draftReceiptDocumentTransactionContainer.getDocumentTransactionCanceledDeliver()).isEqualTo(documentTransactionBack);

        draftReceiptDocumentTransactionContainer.documentTransactionCanceledDeliver(null);
        assertThat(draftReceiptDocumentTransactionContainer.getDocumentTransactionCanceledDeliver()).isNull();
    }

    @Test
    void documentTransactionCanceledReceiptIssueTest() {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftReceiptDocumentTransactionContainer.setDocumentTransactionCanceledReceiptIssue(documentTransactionBack);
        assertThat(draftReceiptDocumentTransactionContainer.getDocumentTransactionCanceledReceiptIssue()).isEqualTo(
            documentTransactionBack
        );

        draftReceiptDocumentTransactionContainer.documentTransactionCanceledReceiptIssue(null);
        assertThat(draftReceiptDocumentTransactionContainer.getDocumentTransactionCanceledReceiptIssue()).isNull();
    }

    @Test
    void receiptCommissionDocumentTransactionsTest() {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftReceiptDocumentTransactionContainer.setReceiptCommissionDocumentTransactions(documentTransactionBack);
        assertThat(draftReceiptDocumentTransactionContainer.getReceiptCommissionDocumentTransactions()).isEqualTo(documentTransactionBack);

        draftReceiptDocumentTransactionContainer.receiptCommissionDocumentTransactions(null);
        assertThat(draftReceiptDocumentTransactionContainer.getReceiptCommissionDocumentTransactions()).isNull();
    }

    @Test
    void draftDocumentTransactionContainerTest() {
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        draftReceiptDocumentTransactionContainer.setDraftDocumentTransactionContainer(draftDocumentTransactionContainerBack);
        assertThat(draftReceiptDocumentTransactionContainer.getDraftDocumentTransactionContainer()).isEqualTo(
            draftDocumentTransactionContainerBack
        );

        draftReceiptDocumentTransactionContainer.draftDocumentTransactionContainer(null);
        assertThat(draftReceiptDocumentTransactionContainer.getDraftDocumentTransactionContainer()).isNull();
    }
}
