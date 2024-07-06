package com.dotin.tfbita.domain;

import static com.dotin.tfbita.domain.CustomJustificationTestSamples.*;
import static com.dotin.tfbita.domain.DocumentTransactionTestSamples.*;
import static com.dotin.tfbita.domain.DraftDocumentTransactionContainerTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.dotin.tfbita.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DocumentTransactionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentTransaction.class);
        DocumentTransaction documentTransaction1 = getDocumentTransactionSample1();
        DocumentTransaction documentTransaction2 = new DocumentTransaction();
        assertThat(documentTransaction1).isNotEqualTo(documentTransaction2);

        documentTransaction2.setId(documentTransaction1.getId());
        assertThat(documentTransaction1).isEqualTo(documentTransaction2);

        documentTransaction2 = getDocumentTransactionSample2();
        assertThat(documentTransaction1).isNotEqualTo(documentTransaction2);
    }

    @Test
    void otherDocumentTransactionsContainerTest() {
        DocumentTransaction documentTransaction = getDocumentTransactionRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        documentTransaction.setOtherDocumentTransactionsContainer(draftDocumentTransactionContainerBack);
        assertThat(documentTransaction.getOtherDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainerBack);

        documentTransaction.otherDocumentTransactionsContainer(null);
        assertThat(documentTransaction.getOtherDocumentTransactionsContainer()).isNull();
    }

    @Test
    void canceledJustificationDocumentContainerTest() {
        DocumentTransaction documentTransaction = getDocumentTransactionRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        documentTransaction.setCanceledJustificationDocumentContainer(draftDocumentTransactionContainerBack);
        assertThat(documentTransaction.getCanceledJustificationDocumentContainer()).isEqualTo(draftDocumentTransactionContainerBack);

        documentTransaction.canceledJustificationDocumentContainer(null);
        assertThat(documentTransaction.getCanceledJustificationDocumentContainer()).isNull();
    }

    @Test
    void justificationDocumentTransactionsContainerTest() {
        DocumentTransaction documentTransaction = getDocumentTransactionRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        documentTransaction.setJustificationDocumentTransactionsContainer(draftDocumentTransactionContainerBack);
        assertThat(documentTransaction.getJustificationDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainerBack);

        documentTransaction.justificationDocumentTransactionsContainer(null);
        assertThat(documentTransaction.getJustificationDocumentTransactionsContainer()).isNull();
    }

    @Test
    void receivedCommisionsContainerTest() {
        DocumentTransaction documentTransaction = getDocumentTransactionRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        documentTransaction.setReceivedCommisionsContainer(draftDocumentTransactionContainerBack);
        assertThat(documentTransaction.getReceivedCommisionsContainer()).isEqualTo(draftDocumentTransactionContainerBack);

        documentTransaction.receivedCommisionsContainer(null);
        assertThat(documentTransaction.getReceivedCommisionsContainer()).isNull();
    }

    @Test
    void canceledDocumentTransactionsContainerTest() {
        DocumentTransaction documentTransaction = getDocumentTransactionRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        documentTransaction.setCanceledDocumentTransactionsContainer(draftDocumentTransactionContainerBack);
        assertThat(documentTransaction.getCanceledDocumentTransactionsContainer()).isEqualTo(draftDocumentTransactionContainerBack);

        documentTransaction.canceledDocumentTransactionsContainer(null);
        assertThat(documentTransaction.getCanceledDocumentTransactionsContainer()).isNull();
    }

    @Test
    void returnedDefaultCurrencyCostsContainerTest() {
        DocumentTransaction documentTransaction = getDocumentTransactionRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        documentTransaction.setReturnedDefaultCurrencyCostsContainer(draftDocumentTransactionContainerBack);
        assertThat(documentTransaction.getReturnedDefaultCurrencyCostsContainer()).isEqualTo(draftDocumentTransactionContainerBack);

        documentTransaction.returnedDefaultCurrencyCostsContainer(null);
        assertThat(documentTransaction.getReturnedDefaultCurrencyCostsContainer()).isNull();
    }

    @Test
    void defaultCurrencyCostsDocumentContainerTest() {
        DocumentTransaction documentTransaction = getDocumentTransactionRandomSampleGenerator();
        DraftDocumentTransactionContainer draftDocumentTransactionContainerBack =
            getDraftDocumentTransactionContainerRandomSampleGenerator();

        documentTransaction.setDefaultCurrencyCostsDocumentContainer(draftDocumentTransactionContainerBack);
        assertThat(documentTransaction.getDefaultCurrencyCostsDocumentContainer()).isEqualTo(draftDocumentTransactionContainerBack);

        documentTransaction.defaultCurrencyCostsDocumentContainer(null);
        assertThat(documentTransaction.getDefaultCurrencyCostsDocumentContainer()).isNull();
    }

    @Test
    void customJustificationTest() {
        DocumentTransaction documentTransaction = getDocumentTransactionRandomSampleGenerator();
        CustomJustification customJustificationBack = getCustomJustificationRandomSampleGenerator();

        documentTransaction.addCustomJustification(customJustificationBack);
        assertThat(documentTransaction.getCustomJustifications()).containsOnly(customJustificationBack);
        assertThat(customJustificationBack.getReverseOfJustificationDocumentTransactions()).containsOnly(documentTransaction);

        documentTransaction.removeCustomJustification(customJustificationBack);
        assertThat(documentTransaction.getCustomJustifications()).doesNotContain(customJustificationBack);
        assertThat(customJustificationBack.getReverseOfJustificationDocumentTransactions()).doesNotContain(documentTransaction);

        documentTransaction.customJustifications(new HashSet<>(Set.of(customJustificationBack)));
        assertThat(documentTransaction.getCustomJustifications()).containsOnly(customJustificationBack);
        assertThat(customJustificationBack.getReverseOfJustificationDocumentTransactions()).containsOnly(documentTransaction);

        documentTransaction.setCustomJustifications(new HashSet<>());
        assertThat(documentTransaction.getCustomJustifications()).doesNotContain(customJustificationBack);
        assertThat(customJustificationBack.getReverseOfJustificationDocumentTransactions()).doesNotContain(documentTransaction);
    }
}
