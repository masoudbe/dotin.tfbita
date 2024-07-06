package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DraftDocumentTransactionContainer.
 */
@Entity
@Table(name = "draft_document_transaction_container")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftDocumentTransactionContainer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draftDocumentTransactionContainer")
    @JsonIgnoreProperties(
        value = {
            "receiptIssueDocumentTransaction",
            "freightLetterStampCostDocumentTransaction",
            "deliverDocumentTransaction",
            "documentTransactionCanceledDeliver",
            "documentTransactionCanceledReceiptIssue",
            "receiptCommissionDocumentTransactions",
            "draftDocumentTransactionContainer",
        },
        allowSetters = true
    )
    private Set<DraftReceiptDocumentTransactionContainer> receipts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "otherDocumentTransactionsContainer")
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
    private Set<DocumentTransaction> otherDocumentTransactions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "canceledJustificationDocumentContainer")
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
    private Set<DocumentTransaction> canceledJustificationDocumentTransactions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "justificationDocumentTransactionsContainer")
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
    private Set<DocumentTransaction> justificationDocumentTransactions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receivedCommisionsContainer")
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
    private Set<DocumentTransaction> receivedCommisions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "canceledDocumentTransactionsContainer")
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
    private Set<DocumentTransaction> canceledDocumentTransactions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "returnedDefaultCurrencyCostsContainer")
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
    private Set<DocumentTransaction> returnedDefaultCurrencyCostsDocumentTransactions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "defaultCurrencyCostsDocumentContainer")
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
    private Set<DocumentTransaction> defaultCurrencyCostsDocumentTransactions = new HashSet<>();

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
    private DocumentTransaction issueCommissionDocumentTransaction;

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
    private DocumentTransaction paymentDocumentTransaction;

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
    private DocumentTransaction settleDocumentTransaction;

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
    private DocumentTransaction settleExcessDocumentTransaction;

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
    private DocumentTransaction commissionDeleteDraftDocumentTransaction;

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
    private DocumentTransaction commissionDraftExtendDocumentTransaction;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "documentTransactionContainers")
    @JsonIgnoreProperties(
        value = {
            "receipts",
            "taxes",
            "extensions",
            "draftFactors",
            "usedAssurances",
            "draftJustifications",
            "chargedExchangeBroker",
            "insuranceLetterType",
            "advisorDepositType",
            "interfaceAdvisorDepositType",
            "coveringAdvisorDepositType",
            "impartType",
            "dealType",
            "transportVehicleType",
            "freightLetterType",
            "actionCode",
            "ownershipCode",
            "currencyContainerPlace",
            "paymentType",
            "draftSource",
            "loadSwitchPlace",
            "draftType",
            "statusInfo",
            "insuranceCompanyInfo",
            "advisingBank",
            "interfaceAdvisingBank",
            "coveringBank",
            "auditCompanyInfo",
            "transportType",
            "currencyExchangeInfo",
            "accountInfo",
            "destinationCustomCompanies",
            "sourceCustomCompanies",
            "services",
            "products",
            "sanctionSerials",
            "customerNumbers",
            "suggestedSanctions",
            "documentTransactionContainers",
        },
        allowSetters = true
    )
    private Set<Draft> drafts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftDocumentTransactionContainer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DraftReceiptDocumentTransactionContainer> getReceipts() {
        return this.receipts;
    }

    public void setReceipts(Set<DraftReceiptDocumentTransactionContainer> draftReceiptDocumentTransactionContainers) {
        if (this.receipts != null) {
            this.receipts.forEach(i -> i.setDraftDocumentTransactionContainer(null));
        }
        if (draftReceiptDocumentTransactionContainers != null) {
            draftReceiptDocumentTransactionContainers.forEach(i -> i.setDraftDocumentTransactionContainer(this));
        }
        this.receipts = draftReceiptDocumentTransactionContainers;
    }

    public DraftDocumentTransactionContainer receipts(
        Set<DraftReceiptDocumentTransactionContainer> draftReceiptDocumentTransactionContainers
    ) {
        this.setReceipts(draftReceiptDocumentTransactionContainers);
        return this;
    }

    public DraftDocumentTransactionContainer addReceipts(
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer
    ) {
        this.receipts.add(draftReceiptDocumentTransactionContainer);
        draftReceiptDocumentTransactionContainer.setDraftDocumentTransactionContainer(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeReceipts(
        DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer
    ) {
        this.receipts.remove(draftReceiptDocumentTransactionContainer);
        draftReceiptDocumentTransactionContainer.setDraftDocumentTransactionContainer(null);
        return this;
    }

    public Set<DocumentTransaction> getOtherDocumentTransactions() {
        return this.otherDocumentTransactions;
    }

    public void setOtherDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        if (this.otherDocumentTransactions != null) {
            this.otherDocumentTransactions.forEach(i -> i.setOtherDocumentTransactionsContainer(null));
        }
        if (documentTransactions != null) {
            documentTransactions.forEach(i -> i.setOtherDocumentTransactionsContainer(this));
        }
        this.otherDocumentTransactions = documentTransactions;
    }

    public DraftDocumentTransactionContainer otherDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        this.setOtherDocumentTransactions(documentTransactions);
        return this;
    }

    public DraftDocumentTransactionContainer addOtherDocumentTransactions(DocumentTransaction documentTransaction) {
        this.otherDocumentTransactions.add(documentTransaction);
        documentTransaction.setOtherDocumentTransactionsContainer(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeOtherDocumentTransactions(DocumentTransaction documentTransaction) {
        this.otherDocumentTransactions.remove(documentTransaction);
        documentTransaction.setOtherDocumentTransactionsContainer(null);
        return this;
    }

    public Set<DocumentTransaction> getCanceledJustificationDocumentTransactions() {
        return this.canceledJustificationDocumentTransactions;
    }

    public void setCanceledJustificationDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        if (this.canceledJustificationDocumentTransactions != null) {
            this.canceledJustificationDocumentTransactions.forEach(i -> i.setCanceledJustificationDocumentContainer(null));
        }
        if (documentTransactions != null) {
            documentTransactions.forEach(i -> i.setCanceledJustificationDocumentContainer(this));
        }
        this.canceledJustificationDocumentTransactions = documentTransactions;
    }

    public DraftDocumentTransactionContainer canceledJustificationDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        this.setCanceledJustificationDocumentTransactions(documentTransactions);
        return this;
    }

    public DraftDocumentTransactionContainer addCanceledJustificationDocumentTransactions(DocumentTransaction documentTransaction) {
        this.canceledJustificationDocumentTransactions.add(documentTransaction);
        documentTransaction.setCanceledJustificationDocumentContainer(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeCanceledJustificationDocumentTransactions(DocumentTransaction documentTransaction) {
        this.canceledJustificationDocumentTransactions.remove(documentTransaction);
        documentTransaction.setCanceledJustificationDocumentContainer(null);
        return this;
    }

    public Set<DocumentTransaction> getJustificationDocumentTransactions() {
        return this.justificationDocumentTransactions;
    }

    public void setJustificationDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        if (this.justificationDocumentTransactions != null) {
            this.justificationDocumentTransactions.forEach(i -> i.setJustificationDocumentTransactionsContainer(null));
        }
        if (documentTransactions != null) {
            documentTransactions.forEach(i -> i.setJustificationDocumentTransactionsContainer(this));
        }
        this.justificationDocumentTransactions = documentTransactions;
    }

    public DraftDocumentTransactionContainer justificationDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        this.setJustificationDocumentTransactions(documentTransactions);
        return this;
    }

    public DraftDocumentTransactionContainer addJustificationDocumentTransactions(DocumentTransaction documentTransaction) {
        this.justificationDocumentTransactions.add(documentTransaction);
        documentTransaction.setJustificationDocumentTransactionsContainer(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeJustificationDocumentTransactions(DocumentTransaction documentTransaction) {
        this.justificationDocumentTransactions.remove(documentTransaction);
        documentTransaction.setJustificationDocumentTransactionsContainer(null);
        return this;
    }

    public Set<DocumentTransaction> getReceivedCommisions() {
        return this.receivedCommisions;
    }

    public void setReceivedCommisions(Set<DocumentTransaction> documentTransactions) {
        if (this.receivedCommisions != null) {
            this.receivedCommisions.forEach(i -> i.setReceivedCommisionsContainer(null));
        }
        if (documentTransactions != null) {
            documentTransactions.forEach(i -> i.setReceivedCommisionsContainer(this));
        }
        this.receivedCommisions = documentTransactions;
    }

    public DraftDocumentTransactionContainer receivedCommisions(Set<DocumentTransaction> documentTransactions) {
        this.setReceivedCommisions(documentTransactions);
        return this;
    }

    public DraftDocumentTransactionContainer addReceivedCommisions(DocumentTransaction documentTransaction) {
        this.receivedCommisions.add(documentTransaction);
        documentTransaction.setReceivedCommisionsContainer(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeReceivedCommisions(DocumentTransaction documentTransaction) {
        this.receivedCommisions.remove(documentTransaction);
        documentTransaction.setReceivedCommisionsContainer(null);
        return this;
    }

    public Set<DocumentTransaction> getCanceledDocumentTransactions() {
        return this.canceledDocumentTransactions;
    }

    public void setCanceledDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        if (this.canceledDocumentTransactions != null) {
            this.canceledDocumentTransactions.forEach(i -> i.setCanceledDocumentTransactionsContainer(null));
        }
        if (documentTransactions != null) {
            documentTransactions.forEach(i -> i.setCanceledDocumentTransactionsContainer(this));
        }
        this.canceledDocumentTransactions = documentTransactions;
    }

    public DraftDocumentTransactionContainer canceledDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        this.setCanceledDocumentTransactions(documentTransactions);
        return this;
    }

    public DraftDocumentTransactionContainer addCanceledDocumentTransactions(DocumentTransaction documentTransaction) {
        this.canceledDocumentTransactions.add(documentTransaction);
        documentTransaction.setCanceledDocumentTransactionsContainer(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeCanceledDocumentTransactions(DocumentTransaction documentTransaction) {
        this.canceledDocumentTransactions.remove(documentTransaction);
        documentTransaction.setCanceledDocumentTransactionsContainer(null);
        return this;
    }

    public Set<DocumentTransaction> getReturnedDefaultCurrencyCostsDocumentTransactions() {
        return this.returnedDefaultCurrencyCostsDocumentTransactions;
    }

    public void setReturnedDefaultCurrencyCostsDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        if (this.returnedDefaultCurrencyCostsDocumentTransactions != null) {
            this.returnedDefaultCurrencyCostsDocumentTransactions.forEach(i -> i.setReturnedDefaultCurrencyCostsContainer(null));
        }
        if (documentTransactions != null) {
            documentTransactions.forEach(i -> i.setReturnedDefaultCurrencyCostsContainer(this));
        }
        this.returnedDefaultCurrencyCostsDocumentTransactions = documentTransactions;
    }

    public DraftDocumentTransactionContainer returnedDefaultCurrencyCostsDocumentTransactions(
        Set<DocumentTransaction> documentTransactions
    ) {
        this.setReturnedDefaultCurrencyCostsDocumentTransactions(documentTransactions);
        return this;
    }

    public DraftDocumentTransactionContainer addReturnedDefaultCurrencyCostsDocumentTransactions(DocumentTransaction documentTransaction) {
        this.returnedDefaultCurrencyCostsDocumentTransactions.add(documentTransaction);
        documentTransaction.setReturnedDefaultCurrencyCostsContainer(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeReturnedDefaultCurrencyCostsDocumentTransactions(
        DocumentTransaction documentTransaction
    ) {
        this.returnedDefaultCurrencyCostsDocumentTransactions.remove(documentTransaction);
        documentTransaction.setReturnedDefaultCurrencyCostsContainer(null);
        return this;
    }

    public Set<DocumentTransaction> getDefaultCurrencyCostsDocumentTransactions() {
        return this.defaultCurrencyCostsDocumentTransactions;
    }

    public void setDefaultCurrencyCostsDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        if (this.defaultCurrencyCostsDocumentTransactions != null) {
            this.defaultCurrencyCostsDocumentTransactions.forEach(i -> i.setDefaultCurrencyCostsDocumentContainer(null));
        }
        if (documentTransactions != null) {
            documentTransactions.forEach(i -> i.setDefaultCurrencyCostsDocumentContainer(this));
        }
        this.defaultCurrencyCostsDocumentTransactions = documentTransactions;
    }

    public DraftDocumentTransactionContainer defaultCurrencyCostsDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        this.setDefaultCurrencyCostsDocumentTransactions(documentTransactions);
        return this;
    }

    public DraftDocumentTransactionContainer addDefaultCurrencyCostsDocumentTransactions(DocumentTransaction documentTransaction) {
        this.defaultCurrencyCostsDocumentTransactions.add(documentTransaction);
        documentTransaction.setDefaultCurrencyCostsDocumentContainer(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeDefaultCurrencyCostsDocumentTransactions(DocumentTransaction documentTransaction) {
        this.defaultCurrencyCostsDocumentTransactions.remove(documentTransaction);
        documentTransaction.setDefaultCurrencyCostsDocumentContainer(null);
        return this;
    }

    public DocumentTransaction getIssueCommissionDocumentTransaction() {
        return this.issueCommissionDocumentTransaction;
    }

    public void setIssueCommissionDocumentTransaction(DocumentTransaction documentTransaction) {
        this.issueCommissionDocumentTransaction = documentTransaction;
    }

    public DraftDocumentTransactionContainer issueCommissionDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setIssueCommissionDocumentTransaction(documentTransaction);
        return this;
    }

    public DocumentTransaction getPaymentDocumentTransaction() {
        return this.paymentDocumentTransaction;
    }

    public void setPaymentDocumentTransaction(DocumentTransaction documentTransaction) {
        this.paymentDocumentTransaction = documentTransaction;
    }

    public DraftDocumentTransactionContainer paymentDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setPaymentDocumentTransaction(documentTransaction);
        return this;
    }

    public DocumentTransaction getSettleDocumentTransaction() {
        return this.settleDocumentTransaction;
    }

    public void setSettleDocumentTransaction(DocumentTransaction documentTransaction) {
        this.settleDocumentTransaction = documentTransaction;
    }

    public DraftDocumentTransactionContainer settleDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setSettleDocumentTransaction(documentTransaction);
        return this;
    }

    public DocumentTransaction getSettleExcessDocumentTransaction() {
        return this.settleExcessDocumentTransaction;
    }

    public void setSettleExcessDocumentTransaction(DocumentTransaction documentTransaction) {
        this.settleExcessDocumentTransaction = documentTransaction;
    }

    public DraftDocumentTransactionContainer settleExcessDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setSettleExcessDocumentTransaction(documentTransaction);
        return this;
    }

    public DocumentTransaction getCommissionDeleteDraftDocumentTransaction() {
        return this.commissionDeleteDraftDocumentTransaction;
    }

    public void setCommissionDeleteDraftDocumentTransaction(DocumentTransaction documentTransaction) {
        this.commissionDeleteDraftDocumentTransaction = documentTransaction;
    }

    public DraftDocumentTransactionContainer commissionDeleteDraftDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setCommissionDeleteDraftDocumentTransaction(documentTransaction);
        return this;
    }

    public DocumentTransaction getCommissionDraftExtendDocumentTransaction() {
        return this.commissionDraftExtendDocumentTransaction;
    }

    public void setCommissionDraftExtendDocumentTransaction(DocumentTransaction documentTransaction) {
        this.commissionDraftExtendDocumentTransaction = documentTransaction;
    }

    public DraftDocumentTransactionContainer commissionDraftExtendDocumentTransaction(DocumentTransaction documentTransaction) {
        this.setCommissionDraftExtendDocumentTransaction(documentTransaction);
        return this;
    }

    public Set<Draft> getDrafts() {
        return this.drafts;
    }

    public void setDrafts(Set<Draft> drafts) {
        if (this.drafts != null) {
            this.drafts.forEach(i -> i.removeDocumentTransactionContainer(this));
        }
        if (drafts != null) {
            drafts.forEach(i -> i.addDocumentTransactionContainer(this));
        }
        this.drafts = drafts;
    }

    public DraftDocumentTransactionContainer drafts(Set<Draft> drafts) {
        this.setDrafts(drafts);
        return this;
    }

    public DraftDocumentTransactionContainer addDraft(Draft draft) {
        this.drafts.add(draft);
        draft.getDocumentTransactionContainers().add(this);
        return this;
    }

    public DraftDocumentTransactionContainer removeDraft(Draft draft) {
        this.drafts.remove(draft);
        draft.getDocumentTransactionContainers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftDocumentTransactionContainer)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftDocumentTransactionContainer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftDocumentTransactionContainer{" +
            "id=" + getId() +
            "}";
    }
}
