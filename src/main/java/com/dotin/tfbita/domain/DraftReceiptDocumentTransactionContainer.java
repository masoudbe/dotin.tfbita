package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DraftReceiptDocumentTransactionContainer.
 */
@Entity
@Table(name = "draft_receipt_document_transaction_container")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftReceiptDocumentTransactionContainer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "receive_receipt_commission")
    private Boolean receiveReceiptCommission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "otherDocumentTransactionsContainer",
            "canceledJustificationDocumentContainer",
            "justificationDocumentTransactionsContainer",
            "receivedCommisionsContainer",
            "canceledDocumentTransactionsContainer",
            "returnedDefaultCurrencyCostsContainer",
            "defaultCurrencyCostsDocumentContainer",
            "customJustifications",
        },
        allowSetters = true
    )
    private DocumentTransaction receiptIssueDocumentTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "otherDocumentTransactionsContainer",
            "canceledJustificationDocumentContainer",
            "justificationDocumentTransactionsContainer",
            "receivedCommisionsContainer",
            "canceledDocumentTransactionsContainer",
            "returnedDefaultCurrencyCostsContainer",
            "defaultCurrencyCostsDocumentContainer",
            "customJustifications",
        },
        allowSetters = true
    )
    private DocumentTransaction freightLetterStampCostDocumentTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "otherDocumentTransactionsContainer",
            "canceledJustificationDocumentContainer",
            "justificationDocumentTransactionsContainer",
            "receivedCommisionsContainer",
            "canceledDocumentTransactionsContainer",
            "returnedDefaultCurrencyCostsContainer",
            "defaultCurrencyCostsDocumentContainer",
            "customJustifications",
        },
        allowSetters = true
    )
    private DocumentTransaction deliverDocumentTransaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "otherDocumentTransactionsContainer",
            "canceledJustificationDocumentContainer",
            "justificationDocumentTransactionsContainer",
            "receivedCommisionsContainer",
            "canceledDocumentTransactionsContainer",
            "returnedDefaultCurrencyCostsContainer",
            "defaultCurrencyCostsDocumentContainer",
            "customJustifications",
        },
        allowSetters = true
    )
    private DocumentTransaction documentTransactionCanceledDeliver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "otherDocumentTransactionsContainer",
            "canceledJustificationDocumentContainer",
            "justificationDocumentTransactionsContainer",
            "receivedCommisionsContainer",
            "canceledDocumentTransactionsContainer",
            "returnedDefaultCurrencyCostsContainer",
            "defaultCurrencyCostsDocumentContainer",
            "customJustifications",
        },
        allowSetters = true
    )
    private DocumentTransaction documentTransactionCanceledReceiptIssue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "otherDocumentTransactionsContainer",
            "canceledJustificationDocumentContainer",
            "justificationDocumentTransactionsContainer",
            "receivedCommisionsContainer",
            "canceledDocumentTransactionsContainer",
            "returnedDefaultCurrencyCostsContainer",
            "defaultCurrencyCostsDocumentContainer",
            "customJustifications",
        },
        allowSetters = true
    )
    private DocumentTransaction receiptCommissionDocumentTransactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "receipts",
            "otherDocumentTransactions",
            "canceledJustificationDocumentTransactions",
            "justificationDocumentTransactions",
            "receivedCommisions",
            "canceledDocumentTransactions",
            "returnedDefaultCurrencyCostsDocumentTransactions",
            "defaultCurrencyCostsDocumentTransactions",
            "issueCommissionDocumentTransaction",
            "paymentDocumentTransaction",
            "settleDocumentTransaction",
            "settleExcessDocumentTransaction",
            "commissionDeleteDraftDocumentTransaction",
            "commissionDraftExtendDocumentTransaction",
            "drafts",
        },
        allowSetters = true
    )
    private DraftDocumentTransactionContainer draftDocumentTransactionContainer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftReceiptDocumentTransactionContainer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReceiveReceiptCommission() {
        return this.receiveReceiptCommission;
    }

    public DraftReceiptDocumentTransactionContainer receiveReceiptCommission(Boolean receiveReceiptCommission) {
        this.setReceiveReceiptCommission(receiveReceiptCommission);
        return this;
    }

    public void setReceiveReceiptCommission(Boolean receiveReceiptCommission) {
        this.receiveReceiptCommission = receiveReceiptCommission;
    }

    public DocumentTransaction getReceiptIssueDocumentTransaction() {
        return this.receiptIssueDocumentTransaction;
    }

    public void setReceiptIssueDocumentTransaction(DocumentTransaction documentTransaction) {
        this.receiptIssueDocumentTransaction = documentTransaction;
    }

    public DraftReceiptDocumentTransactionContainer receiptIssueDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setReceiptIssueDocumentTransaction(documentTransaction);
        return this;
    }

    public DocumentTransaction getFreightLetterStampCostDocumentTransaction() {
        return this.freightLetterStampCostDocumentTransaction;
    }

    public void setFreightLetterStampCostDocumentTransaction(DocumentTransaction documentTransaction) {
        this.freightLetterStampCostDocumentTransaction = documentTransaction;
    }

    public DraftReceiptDocumentTransactionContainer freightLetterStampCostDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setFreightLetterStampCostDocumentTransaction(documentTransaction);
        return this;
    }

    public DocumentTransaction getDeliverDocumentTransaction() {
        return this.deliverDocumentTransaction;
    }

    public void setDeliverDocumentTransaction(DocumentTransaction documentTransaction) {
        this.deliverDocumentTransaction = documentTransaction;
    }

    public DraftReceiptDocumentTransactionContainer deliverDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setDeliverDocumentTransaction(documentTransaction);
        return this;
    }

    public DocumentTransaction getDocumentTransactionCanceledDeliver() {
        return this.documentTransactionCanceledDeliver;
    }

    public void setDocumentTransactionCanceledDeliver(DocumentTransaction documentTransaction) {
        this.documentTransactionCanceledDeliver = documentTransaction;
    }

    public DraftReceiptDocumentTransactionContainer documentTransactionCanceledDeliver(DocumentTransaction documentTransaction) {
        this.setDocumentTransactionCanceledDeliver(documentTransaction);
        return this;
    }

    public DocumentTransaction getDocumentTransactionCanceledReceiptIssue() {
        return this.documentTransactionCanceledReceiptIssue;
    }

    public void setDocumentTransactionCanceledReceiptIssue(DocumentTransaction documentTransaction) {
        this.documentTransactionCanceledReceiptIssue = documentTransaction;
    }

    public DraftReceiptDocumentTransactionContainer documentTransactionCanceledReceiptIssue(DocumentTransaction documentTransaction) {
        this.setDocumentTransactionCanceledReceiptIssue(documentTransaction);
        return this;
    }

    public DocumentTransaction getReceiptCommissionDocumentTransactions() {
        return this.receiptCommissionDocumentTransactions;
    }

    public void setReceiptCommissionDocumentTransactions(DocumentTransaction documentTransaction) {
        this.receiptCommissionDocumentTransactions = documentTransaction;
    }

    public DraftReceiptDocumentTransactionContainer receiptCommissionDocumentTransactions(DocumentTransaction documentTransaction) {
        this.setReceiptCommissionDocumentTransactions(documentTransaction);
        return this;
    }

    public DraftDocumentTransactionContainer getDraftDocumentTransactionContainer() {
        return this.draftDocumentTransactionContainer;
    }

    public void setDraftDocumentTransactionContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.draftDocumentTransactionContainer = draftDocumentTransactionContainer;
    }

    public DraftReceiptDocumentTransactionContainer draftDocumentTransactionContainer(
        DraftDocumentTransactionContainer draftDocumentTransactionContainer
    ) {
        this.setDraftDocumentTransactionContainer(draftDocumentTransactionContainer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftReceiptDocumentTransactionContainer)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftReceiptDocumentTransactionContainer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftReceiptDocumentTransactionContainer{" +
            "id=" + getId() +
            ", receiveReceiptCommission='" + getReceiveReceiptCommission() + "'" +
            "}";
    }
}
