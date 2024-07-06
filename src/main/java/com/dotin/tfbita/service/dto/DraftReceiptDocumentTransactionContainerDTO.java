package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftReceiptDocumentTransactionContainer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftReceiptDocumentTransactionContainerDTO implements Serializable {

    private Long id;

    private Boolean receiveReceiptCommission;

    private DocumentTransactionDTO receiptIssueDocumentTransaction;

    private DocumentTransactionDTO freightLetterStampCostDocumentTransaction;

    private DocumentTransactionDTO deliverDocumentTransaction;

    private DocumentTransactionDTO documentTransactionCanceledDeliver;

    private DocumentTransactionDTO documentTransactionCanceledReceiptIssue;

    private DocumentTransactionDTO receiptCommissionDocumentTransactions;

    private DraftDocumentTransactionContainerDTO draftDocumentTransactionContainer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReceiveReceiptCommission() {
        return receiveReceiptCommission;
    }

    public void setReceiveReceiptCommission(Boolean receiveReceiptCommission) {
        this.receiveReceiptCommission = receiveReceiptCommission;
    }

    public DocumentTransactionDTO getReceiptIssueDocumentTransaction() {
        return receiptIssueDocumentTransaction;
    }

    public void setReceiptIssueDocumentTransaction(DocumentTransactionDTO receiptIssueDocumentTransaction) {
        this.receiptIssueDocumentTransaction = receiptIssueDocumentTransaction;
    }

    public DocumentTransactionDTO getFreightLetterStampCostDocumentTransaction() {
        return freightLetterStampCostDocumentTransaction;
    }

    public void setFreightLetterStampCostDocumentTransaction(DocumentTransactionDTO freightLetterStampCostDocumentTransaction) {
        this.freightLetterStampCostDocumentTransaction = freightLetterStampCostDocumentTransaction;
    }

    public DocumentTransactionDTO getDeliverDocumentTransaction() {
        return deliverDocumentTransaction;
    }

    public void setDeliverDocumentTransaction(DocumentTransactionDTO deliverDocumentTransaction) {
        this.deliverDocumentTransaction = deliverDocumentTransaction;
    }

    public DocumentTransactionDTO getDocumentTransactionCanceledDeliver() {
        return documentTransactionCanceledDeliver;
    }

    public void setDocumentTransactionCanceledDeliver(DocumentTransactionDTO documentTransactionCanceledDeliver) {
        this.documentTransactionCanceledDeliver = documentTransactionCanceledDeliver;
    }

    public DocumentTransactionDTO getDocumentTransactionCanceledReceiptIssue() {
        return documentTransactionCanceledReceiptIssue;
    }

    public void setDocumentTransactionCanceledReceiptIssue(DocumentTransactionDTO documentTransactionCanceledReceiptIssue) {
        this.documentTransactionCanceledReceiptIssue = documentTransactionCanceledReceiptIssue;
    }

    public DocumentTransactionDTO getReceiptCommissionDocumentTransactions() {
        return receiptCommissionDocumentTransactions;
    }

    public void setReceiptCommissionDocumentTransactions(DocumentTransactionDTO receiptCommissionDocumentTransactions) {
        this.receiptCommissionDocumentTransactions = receiptCommissionDocumentTransactions;
    }

    public DraftDocumentTransactionContainerDTO getDraftDocumentTransactionContainer() {
        return draftDocumentTransactionContainer;
    }

    public void setDraftDocumentTransactionContainer(DraftDocumentTransactionContainerDTO draftDocumentTransactionContainer) {
        this.draftDocumentTransactionContainer = draftDocumentTransactionContainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftReceiptDocumentTransactionContainerDTO)) {
            return false;
        }

        DraftReceiptDocumentTransactionContainerDTO draftReceiptDocumentTransactionContainerDTO =
            (DraftReceiptDocumentTransactionContainerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftReceiptDocumentTransactionContainerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftReceiptDocumentTransactionContainerDTO{" +
            "id=" + getId() +
            ", receiveReceiptCommission='" + getReceiveReceiptCommission() + "'" +
            ", receiptIssueDocumentTransaction=" + getReceiptIssueDocumentTransaction() +
            ", freightLetterStampCostDocumentTransaction=" + getFreightLetterStampCostDocumentTransaction() +
            ", deliverDocumentTransaction=" + getDeliverDocumentTransaction() +
            ", documentTransactionCanceledDeliver=" + getDocumentTransactionCanceledDeliver() +
            ", documentTransactionCanceledReceiptIssue=" + getDocumentTransactionCanceledReceiptIssue() +
            ", receiptCommissionDocumentTransactions=" + getReceiptCommissionDocumentTransactions() +
            ", draftDocumentTransactionContainer=" + getDraftDocumentTransactionContainer() +
            "}";
    }
}
