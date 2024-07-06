package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.DocumentTransactionTestSamples.*;
import static com.dotin.tfbita.domain.DraftDocumentTransactionContainerTestSamples.*;
import static com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainerTestSamples.*;
import static com.dotin.tfbita.domain.DraftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DraftDocumentTransactionContainerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DraftDocumentTransactionContainer.class);
        DraftDocumentTransactionContainer draftDocumentTransactionContainer1 = getDraftDocumentTransactionContainerSample1();
        DraftDocumentTransactionContainer draftDocumentTransactionContainer2 = new DraftDocumentTransactionContainer();
        assertThat(draftDocumentTransactionContainer1).isNotEqualTo(draftDocumentTransactionContainer2);

        draftDocumentTransactionContainer2.setId(draftDocumentTransactionContainer1.getId());
        assertThat(draftDocumentTransactionContainer1).isEqualTo(draftDocumentTransactionContainer2);

        draftDocumentTransactionContainer2 = getDraftDocumentTransactionContainerSample2();
        assertThat(draftDocumentTransactionContainer1).isNotEqualTo(draftDocumentTransactionContainer2);
    }

    @Test
    void receiptsTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainerBack =
            getDraftReceiptDocumentTransactionContainerRandomSampleGenerator();

        draftDocumentTransactionContainer.addReceipts(draftReceiptDocumentTransactionContainerBack);
        assertThat(draftDocumentTransactionContainer.getReceipts()).containsOnly(draftReceiptDocumentTransactionContainerBack);
        assertThat(draftReceiptDocumentTransactionContainerBack.getDraftDocumentTransactionContainer()).isEqualTo(
            draftDocumentTransactionContainer
        );

        draftDocumentTransactionContainer.removeReceipts(draftReceiptDocumentTransactionContainerBack);
        assertThat(draftDocumentTransactionContainer.getReceipts()).doesNotContain(draftReceiptDocumentTransactionContainerBack);
        assertThat(draftReceiptDocumentTransactionContainerBack.getDraftDocumentTransactionContainer()).isNull();

        draftDocumentTransactionContainer.receipts(new HashSet<>(Set.of(draftReceiptDocumentTransactionContainerBack)));
        assertThat(draftDocumentTransactionContainer.getReceipts()).containsOnly(draftReceiptDocumentTransactionContainerBack);
        assertThat(draftReceiptDocumentTransactionContainerBack.getDraftDocumentTransactionContainer()).isEqualTo(
            draftDocumentTransactionContainer
        );

        draftDocumentTransactionContainer.setReceipts(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getReceipts()).doesNotContain(draftReceiptDocumentTransactionContainerBack);
        assertThat(draftReceiptDocumentTransactionContainerBack.getDraftDocumentTransactionContainer()).isNull();
    }

    @Test
    void otherDocumentTransactionsTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.addOtherDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getOtherDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getOtherDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.removeOtherDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getOtherDocumentTransactions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getOtherDocumentTransactionsContainer()).isNull();

        draftDocumentTransactionContainer.otherDocumentTransactions(new HashSet<>(Set.of(documentTransactionBack)));
        assertThat(draftDocumentTransactionContainer.getOtherDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getOtherDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.setOtherDocumentTransactions(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getOtherDocumentTransactions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getOtherDocumentTransactionsContainer()).isNull();
    }

    @Test
    void canceledJustificationDocumentTransactionsTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.addCanceledJustificationDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getCanceledJustificationDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getCanceledJustificationDocumentContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.removeCanceledJustificationDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getCanceledJustificationDocumentTransactions()).doesNotContain(
            documentTransactionBack
        );
        assertThat(documentTransactionBack.getCanceledJustificationDocumentContainer()).isNull();

        draftDocumentTransactionContainer.canceledJustificationDocumentTransactions(new HashSet<>(Set.of(documentTransactionBack)));
        assertThat(draftDocumentTransactionContainer.getCanceledJustificationDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getCanceledJustificationDocumentContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.setCanceledJustificationDocumentTransactions(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getCanceledJustificationDocumentTransactions()).doesNotContain(
            documentTransactionBack
        );
        assertThat(documentTransactionBack.getCanceledJustificationDocumentContainer()).isNull();
    }

    @Test
    void justificationDocumentTransactionsTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.addJustificationDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getJustificationDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getJustificationDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.removeJustificationDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getJustificationDocumentTransactions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getJustificationDocumentTransactionsContainer()).isNull();

        draftDocumentTransactionContainer.justificationDocumentTransactions(new HashSet<>(Set.of(documentTransactionBack)));
        assertThat(draftDocumentTransactionContainer.getJustificationDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getJustificationDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.setJustificationDocumentTransactions(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getJustificationDocumentTransactions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getJustificationDocumentTransactionsContainer()).isNull();
    }

    @Test
    void receivedCommisionsTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.addReceivedCommisions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getReceivedCommisions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getReceivedCommisionsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.removeReceivedCommisions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getReceivedCommisions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getReceivedCommisionsContainer()).isNull();

        draftDocumentTransactionContainer.receivedCommisions(new HashSet<>(Set.of(documentTransactionBack)));
        assertThat(draftDocumentTransactionContainer.getReceivedCommisions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getReceivedCommisionsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.setReceivedCommisions(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getReceivedCommisions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getReceivedCommisionsContainer()).isNull();
    }

    @Test
    void canceledDocumentTransactionsTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.addCanceledDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getCanceledDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getCanceledDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.removeCanceledDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getCanceledDocumentTransactions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getCanceledDocumentTransactionsContainer()).isNull();

        draftDocumentTransactionContainer.canceledDocumentTransactions(new HashSet<>(Set.of(documentTransactionBack)));
        assertThat(draftDocumentTransactionContainer.getCanceledDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getCanceledDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.setCanceledDocumentTransactions(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getCanceledDocumentTransactions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getCanceledDocumentTransactionsContainer()).isNull();
    }

    @Test
    void returnedDefaultCurrencyCostsDocumentTransactionsTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.addReturnedDefaultCurrencyCostsDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getReturnedDefaultCurrencyCostsDocumentTransactions()).containsOnly(
            documentTransactionBack
        );
        assertThat(documentTransactionBack.getReturnedDefaultCurrencyCostsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.removeReturnedDefaultCurrencyCostsDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getReturnedDefaultCurrencyCostsDocumentTransactions()).doesNotContain(
            documentTransactionBack
        );
        assertThat(documentTransactionBack.getReturnedDefaultCurrencyCostsContainer()).isNull();

        draftDocumentTransactionContainer.returnedDefaultCurrencyCostsDocumentTransactions(new HashSet<>(Set.of(documentTransactionBack)));
        assertThat(draftDocumentTransactionContainer.getReturnedDefaultCurrencyCostsDocumentTransactions()).containsOnly(
            documentTransactionBack
        );
        assertThat(documentTransactionBack.getReturnedDefaultCurrencyCostsContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.setReturnedDefaultCurrencyCostsDocumentTransactions(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getReturnedDefaultCurrencyCostsDocumentTransactions()).doesNotContain(
            documentTransactionBack
        );
        assertThat(documentTransactionBack.getReturnedDefaultCurrencyCostsContainer()).isNull();
    }

    @Test
    void defaultCurrencyCostsDocumentTransactionsTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.addDefaultCurrencyCostsDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getDefaultCurrencyCostsDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getDefaultCurrencyCostsDocumentContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.removeDefaultCurrencyCostsDocumentTransactions(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getDefaultCurrencyCostsDocumentTransactions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getDefaultCurrencyCostsDocumentContainer()).isNull();

        draftDocumentTransactionContainer.defaultCurrencyCostsDocumentTransactions(new HashSet<>(Set.of(documentTransactionBack)));
        assertThat(draftDocumentTransactionContainer.getDefaultCurrencyCostsDocumentTransactions()).containsOnly(documentTransactionBack);
        assertThat(documentTransactionBack.getDefaultCurrencyCostsDocumentContainer()).isEqualTo(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.setDefaultCurrencyCostsDocumentTransactions(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getDefaultCurrencyCostsDocumentTransactions()).doesNotContain(documentTransactionBack);
        assertThat(documentTransactionBack.getDefaultCurrencyCostsDocumentContainer()).isNull();
    }

    @Test
    void issueCommissionDocumentTransactionTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.setIssueCommissionDocumentTransaction(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getIssueCommissionDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftDocumentTransactionContainer.issueCommissionDocumentTransaction(null);
        assertThat(draftDocumentTransactionContainer.getIssueCommissionDocumentTransaction()).isNull();
    }

    @Test
    void paymentDocumentTransactionTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.setPaymentDocumentTransaction(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getPaymentDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftDocumentTransactionContainer.paymentDocumentTransaction(null);
        assertThat(draftDocumentTransactionContainer.getPaymentDocumentTransaction()).isNull();
    }

    @Test
    void settleDocumentTransactionTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.setSettleDocumentTransaction(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getSettleDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftDocumentTransactionContainer.settleDocumentTransaction(null);
        assertThat(draftDocumentTransactionContainer.getSettleDocumentTransaction()).isNull();
    }

    @Test
    void settleExcessDocumentTransactionTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.setSettleExcessDocumentTransaction(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getSettleExcessDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftDocumentTransactionContainer.settleExcessDocumentTransaction(null);
        assertThat(draftDocumentTransactionContainer.getSettleExcessDocumentTransaction()).isNull();
    }

    @Test
    void commissionDeleteDraftDocumentTransactionTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.setCommissionDeleteDraftDocumentTransaction(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getCommissionDeleteDraftDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftDocumentTransactionContainer.commissionDeleteDraftDocumentTransaction(null);
        assertThat(draftDocumentTransactionContainer.getCommissionDeleteDraftDocumentTransaction()).isNull();
    }

    @Test
    void commissionDraftExtendDocumentTransactionTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        DocumentTransaction documentTransactionBack = getDocumentTransactionRandomSampleGenerator();

        draftDocumentTransactionContainer.setCommissionDraftExtendDocumentTransaction(documentTransactionBack);
        assertThat(draftDocumentTransactionContainer.getCommissionDraftExtendDocumentTransaction()).isEqualTo(documentTransactionBack);

        draftDocumentTransactionContainer.commissionDraftExtendDocumentTransaction(null);
        assertThat(draftDocumentTransactionContainer.getCommissionDraftExtendDocumentTransaction()).isNull();
    }

    @Test
    void draftTest() {
        DraftDocumentTransactionContainer draftDocumentTransactionContainer = getDraftDocumentTransactionContainerRandomSampleGenerator();
        Draft draftBack = getDraftRandomSampleGenerator();

        draftDocumentTransactionContainer.addDraft(draftBack);
        assertThat(draftDocumentTransactionContainer.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getDocumentTransactionContainers()).containsOnly(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.removeDraft(draftBack);
        assertThat(draftDocumentTransactionContainer.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getDocumentTransactionContainers()).doesNotContain(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.drafts(new HashSet<>(Set.of(draftBack)));
        assertThat(draftDocumentTransactionContainer.getDrafts()).containsOnly(draftBack);
        assertThat(draftBack.getDocumentTransactionContainers()).containsOnly(draftDocumentTransactionContainer);

        draftDocumentTransactionContainer.setDrafts(new HashSet<>());
        assertThat(draftDocumentTransactionContainer.getDrafts()).doesNotContain(draftBack);
        assertThat(draftBack.getDocumentTransactionContainers()).doesNotContain(draftDocumentTransactionContainer);
    }
}
