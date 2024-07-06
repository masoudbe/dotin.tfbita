package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DocumentTransaction.
 */
@Entity
@Table(name = "document_transaction")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "currency_exchange_code")
    private String currencyExchangeCode;

    @Column(name = "transaction_code")
    private String transactionCode;

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
    private DraftDocumentTransactionContainer otherDocumentTransactionsContainer;

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
    private DraftDocumentTransactionContainer canceledJustificationDocumentContainer;

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
    private DraftDocumentTransactionContainer justificationDocumentTransactionsContainer;

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
    private DraftDocumentTransactionContainer receivedCommisionsContainer;

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
    private DraftDocumentTransactionContainer canceledDocumentTransactionsContainer;

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
    private DraftDocumentTransactionContainer returnedDefaultCurrencyCostsContainer;

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
    private DraftDocumentTransactionContainer defaultCurrencyCostsDocumentContainer;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "reverseOfJustificationDocumentTransactions")
    @JsonIgnoreProperties(
        value = {
            "customJustificationChildLists",
            "vehicleEnterNationality",
            "container",
            "vehicleCrossNationality",
            "exportCustom",
            "entranceCustom",
            "transportConditions",
            "tradeTypeCode",
            "newPaymentConditions",
            "justificationDeductionAmount",
            "products",
            "reverseOfJustificationDocumentTransactions",
        },
        allowSetters = true
    )
    private Set<CustomJustification> customJustifications = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DocumentTransaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyExchangeCode() {
        return this.currencyExchangeCode;
    }

    public DocumentTransaction currencyExchangeCode(String currencyExchangeCode) {
        this.setCurrencyExchangeCode(currencyExchangeCode);
        return this;
    }

    public void setCurrencyExchangeCode(String currencyExchangeCode) {
        this.currencyExchangeCode = currencyExchangeCode;
    }

    public String getTransactionCode() {
        return this.transactionCode;
    }

    public DocumentTransaction transactionCode(String transactionCode) {
        this.setTransactionCode(transactionCode);
        return this;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public DraftDocumentTransactionContainer getOtherDocumentTransactionsContainer() {
        return this.otherDocumentTransactionsContainer;
    }

    public void setOtherDocumentTransactionsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.otherDocumentTransactionsContainer = draftDocumentTransactionContainer;
    }

    public DocumentTransaction otherDocumentTransactionsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.setOtherDocumentTransactionsContainer(draftDocumentTransactionContainer);
        return this;
    }

    public DraftDocumentTransactionContainer getCanceledJustificationDocumentContainer() {
        return this.canceledJustificationDocumentContainer;
    }

    public void setCanceledJustificationDocumentContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.canceledJustificationDocumentContainer = draftDocumentTransactionContainer;
    }

    public DocumentTransaction canceledJustificationDocumentContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.setCanceledJustificationDocumentContainer(draftDocumentTransactionContainer);
        return this;
    }

    public DraftDocumentTransactionContainer getJustificationDocumentTransactionsContainer() {
        return this.justificationDocumentTransactionsContainer;
    }

    public void setJustificationDocumentTransactionsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.justificationDocumentTransactionsContainer = draftDocumentTransactionContainer;
    }

    public DocumentTransaction justificationDocumentTransactionsContainer(
        DraftDocumentTransactionContainer draftDocumentTransactionContainer
    ) {
        this.setJustificationDocumentTransactionsContainer(draftDocumentTransactionContainer);
        return this;
    }

    public DraftDocumentTransactionContainer getReceivedCommisionsContainer() {
        return this.receivedCommisionsContainer;
    }

    public void setReceivedCommisionsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.receivedCommisionsContainer = draftDocumentTransactionContainer;
    }

    public DocumentTransaction receivedCommisionsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.setReceivedCommisionsContainer(draftDocumentTransactionContainer);
        return this;
    }

    public DraftDocumentTransactionContainer getCanceledDocumentTransactionsContainer() {
        return this.canceledDocumentTransactionsContainer;
    }

    public void setCanceledDocumentTransactionsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.canceledDocumentTransactionsContainer = draftDocumentTransactionContainer;
    }

    public DocumentTransaction canceledDocumentTransactionsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.setCanceledDocumentTransactionsContainer(draftDocumentTransactionContainer);
        return this;
    }

    public DraftDocumentTransactionContainer getReturnedDefaultCurrencyCostsContainer() {
        return this.returnedDefaultCurrencyCostsContainer;
    }

    public void setReturnedDefaultCurrencyCostsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.returnedDefaultCurrencyCostsContainer = draftDocumentTransactionContainer;
    }

    public DocumentTransaction returnedDefaultCurrencyCostsContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.setReturnedDefaultCurrencyCostsContainer(draftDocumentTransactionContainer);
        return this;
    }

    public DraftDocumentTransactionContainer getDefaultCurrencyCostsDocumentContainer() {
        return this.defaultCurrencyCostsDocumentContainer;
    }

    public void setDefaultCurrencyCostsDocumentContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.defaultCurrencyCostsDocumentContainer = draftDocumentTransactionContainer;
    }

    public DocumentTransaction defaultCurrencyCostsDocumentContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.setDefaultCurrencyCostsDocumentContainer(draftDocumentTransactionContainer);
        return this;
    }

    public Set<CustomJustification> getCustomJustifications() {
        return this.customJustifications;
    }

    public void setCustomJustifications(Set<CustomJustification> customJustifications) {
        if (this.customJustifications != null) {
            this.customJustifications.forEach(i -> i.removeReverseOfJustificationDocumentTransactions(this));
        }
        if (customJustifications != null) {
            customJustifications.forEach(i -> i.addReverseOfJustificationDocumentTransactions(this));
        }
        this.customJustifications = customJustifications;
    }

    public DocumentTransaction customJustifications(Set<CustomJustification> customJustifications) {
        this.setCustomJustifications(customJustifications);
        return this;
    }

    public DocumentTransaction addCustomJustification(CustomJustification customJustification) {
        this.customJustifications.add(customJustification);
        customJustification.getReverseOfJustificationDocumentTransactions().add(this);
        return this;
    }

    public DocumentTransaction removeCustomJustification(CustomJustification customJustification) {
        this.customJustifications.remove(customJustification);
        customJustification.getReverseOfJustificationDocumentTransactions().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentTransaction)) {
            return false;
        }
        return getId() != null && getId().equals(((DocumentTransaction) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentTransaction{" +
            "id=" + getId() +
            ", currencyExchangeCode='" + getCurrencyExchangeCode() + "'" +
            ", transactionCode='" + getTransactionCode() + "'" +
            "}";
    }
}
