package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftDocumentTransactionContainer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftDocumentTransactionContainerDTO implements Serializable {

    private Long id;

    private DocumentTransactionDTO issueCommissionDocumentTransaction;

    private DocumentTransactionDTO paymentDocumentTransaction;

    private DocumentTransactionDTO settleDocumentTransaction;

    private DocumentTransactionDTO settleExcessDocumentTransaction;

    private DocumentTransactionDTO commissionDeleteDraftDocumentTransaction;

    private DocumentTransactionDTO commissionDraftExtendDocumentTransaction;

    private Set<DraftDTO> drafts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocumentTransactionDTO getIssueCommissionDocumentTransaction() {
        return issueCommissionDocumentTransaction;
    }

    public void setIssueCommissionDocumentTransaction(DocumentTransactionDTO issueCommissionDocumentTransaction) {
        this.issueCommissionDocumentTransaction = issueCommissionDocumentTransaction;
    }

    public DocumentTransactionDTO getPaymentDocumentTransaction() {
        return paymentDocumentTransaction;
    }

    public void setPaymentDocumentTransaction(DocumentTransactionDTO paymentDocumentTransaction) {
        this.paymentDocumentTransaction = paymentDocumentTransaction;
    }

    public DocumentTransactionDTO getSettleDocumentTransaction() {
        return settleDocumentTransaction;
    }

    public void setSettleDocumentTransaction(DocumentTransactionDTO settleDocumentTransaction) {
        this.settleDocumentTransaction = settleDocumentTransaction;
    }

    public DocumentTransactionDTO getSettleExcessDocumentTransaction() {
        return settleExcessDocumentTransaction;
    }

    public void setSettleExcessDocumentTransaction(DocumentTransactionDTO settleExcessDocumentTransaction) {
        this.settleExcessDocumentTransaction = settleExcessDocumentTransaction;
    }

    public DocumentTransactionDTO getCommissionDeleteDraftDocumentTransaction() {
        return commissionDeleteDraftDocumentTransaction;
    }

    public void setCommissionDeleteDraftDocumentTransaction(DocumentTransactionDTO commissionDeleteDraftDocumentTransaction) {
        this.commissionDeleteDraftDocumentTransaction = commissionDeleteDraftDocumentTransaction;
    }

    public DocumentTransactionDTO getCommissionDraftExtendDocumentTransaction() {
        return commissionDraftExtendDocumentTransaction;
    }

    public void setCommissionDraftExtendDocumentTransaction(DocumentTransactionDTO commissionDraftExtendDocumentTransaction) {
        this.commissionDraftExtendDocumentTransaction = commissionDraftExtendDocumentTransaction;
    }

    public Set<DraftDTO> getDrafts() {
        return drafts;
    }

    public void setDrafts(Set<DraftDTO> drafts) {
        this.drafts = drafts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftDocumentTransactionContainerDTO)) {
            return false;
        }

        DraftDocumentTransactionContainerDTO draftDocumentTransactionContainerDTO = (DraftDocumentTransactionContainerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftDocumentTransactionContainerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftDocumentTransactionContainerDTO{" +
            "id=" + getId() +
            ", issueCommissionDocumentTransaction=" + getIssueCommissionDocumentTransaction() +
            ", paymentDocumentTransaction=" + getPaymentDocumentTransaction() +
            ", settleDocumentTransaction=" + getSettleDocumentTransaction() +
            ", settleExcessDocumentTransaction=" + getSettleExcessDocumentTransaction() +
            ", commissionDeleteDraftDocumentTransaction=" + getCommissionDeleteDraftDocumentTransaction() +
            ", commissionDraftExtendDocumentTransaction=" + getCommissionDraftExtendDocumentTransaction() +
            ", drafts=" + getDrafts() +
            "}";
    }
}
