package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DocumentTransaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentTransactionDTO implements Serializable {

    private Long id;

    private String currencyExchangeCode;

    private String transactionCode;

    private DraftDocumentTransactionContainerDTO otherDocumentTransactionsContainer;

    private DraftDocumentTransactionContainerDTO canceledJustificationDocumentContainer;

    private DraftDocumentTransactionContainerDTO justificationDocumentTransactionsContainer;

    private DraftDocumentTransactionContainerDTO receivedCommisionsContainer;

    private DraftDocumentTransactionContainerDTO canceledDocumentTransactionsContainer;

    private DraftDocumentTransactionContainerDTO returnedDefaultCurrencyCostsContainer;

    private DraftDocumentTransactionContainerDTO defaultCurrencyCostsDocumentContainer;

    private Set<CustomJustificationDTO> customJustifications = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyExchangeCode() {
        return currencyExchangeCode;
    }

    public void setCurrencyExchangeCode(String currencyExchangeCode) {
        this.currencyExchangeCode = currencyExchangeCode;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public DraftDocumentTransactionContainerDTO getOtherDocumentTransactionsContainer() {
        return otherDocumentTransactionsContainer;
    }

    public void setOtherDocumentTransactionsContainer(DraftDocumentTransactionContainerDTO otherDocumentTransactionsContainer) {
        this.otherDocumentTransactionsContainer = otherDocumentTransactionsContainer;
    }

    public DraftDocumentTransactionContainerDTO getCanceledJustificationDocumentContainer() {
        return canceledJustificationDocumentContainer;
    }

    public void setCanceledJustificationDocumentContainer(DraftDocumentTransactionContainerDTO canceledJustificationDocumentContainer) {
        this.canceledJustificationDocumentContainer = canceledJustificationDocumentContainer;
    }

    public DraftDocumentTransactionContainerDTO getJustificationDocumentTransactionsContainer() {
        return justificationDocumentTransactionsContainer;
    }

    public void setJustificationDocumentTransactionsContainer(
        DraftDocumentTransactionContainerDTO justificationDocumentTransactionsContainer
    ) {
        this.justificationDocumentTransactionsContainer = justificationDocumentTransactionsContainer;
    }

    public DraftDocumentTransactionContainerDTO getReceivedCommisionsContainer() {
        return receivedCommisionsContainer;
    }

    public void setReceivedCommisionsContainer(DraftDocumentTransactionContainerDTO receivedCommisionsContainer) {
        this.receivedCommisionsContainer = receivedCommisionsContainer;
    }

    public DraftDocumentTransactionContainerDTO getCanceledDocumentTransactionsContainer() {
        return canceledDocumentTransactionsContainer;
    }

    public void setCanceledDocumentTransactionsContainer(DraftDocumentTransactionContainerDTO canceledDocumentTransactionsContainer) {
        this.canceledDocumentTransactionsContainer = canceledDocumentTransactionsContainer;
    }

    public DraftDocumentTransactionContainerDTO getReturnedDefaultCurrencyCostsContainer() {
        return returnedDefaultCurrencyCostsContainer;
    }

    public void setReturnedDefaultCurrencyCostsContainer(DraftDocumentTransactionContainerDTO returnedDefaultCurrencyCostsContainer) {
        this.returnedDefaultCurrencyCostsContainer = returnedDefaultCurrencyCostsContainer;
    }

    public DraftDocumentTransactionContainerDTO getDefaultCurrencyCostsDocumentContainer() {
        return defaultCurrencyCostsDocumentContainer;
    }

    public void setDefaultCurrencyCostsDocumentContainer(DraftDocumentTransactionContainerDTO defaultCurrencyCostsDocumentContainer) {
        this.defaultCurrencyCostsDocumentContainer = defaultCurrencyCostsDocumentContainer;
    }

    public Set<CustomJustificationDTO> getCustomJustifications() {
        return customJustifications;
    }

    public void setCustomJustifications(Set<CustomJustificationDTO> customJustifications) {
        this.customJustifications = customJustifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentTransactionDTO)) {
            return false;
        }

        DocumentTransactionDTO documentTransactionDTO = (DocumentTransactionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, documentTransactionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentTransactionDTO{" +
            "id=" + getId() +
            ", currencyExchangeCode='" + getCurrencyExchangeCode() + "'" +
            ", transactionCode='" + getTransactionCode() + "'" +
            ", otherDocumentTransactionsContainer=" + getOtherDocumentTransactionsContainer() +
            ", canceledJustificationDocumentContainer=" + getCanceledJustificationDocumentContainer() +
            ", justificationDocumentTransactionsContainer=" + getJustificationDocumentTransactionsContainer() +
            ", receivedCommisionsContainer=" + getReceivedCommisionsContainer() +
            ", canceledDocumentTransactionsContainer=" + getCanceledDocumentTransactionsContainer() +
            ", returnedDefaultCurrencyCostsContainer=" + getReturnedDefaultCurrencyCostsContainer() +
            ", defaultCurrencyCostsDocumentContainer=" + getDefaultCurrencyCostsDocumentContainer() +
            ", customJustifications=" + getCustomJustifications() +
            "}";
    }
}
