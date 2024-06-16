package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.PurchaseFromOtherResources} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseFromOtherResourcesDTO implements Serializable {

    private Long id;

    private String evidenceCode;

    private String currencySupplierDescription;

    private BigDecimal amount;

    private BigDecimal purchaseRate;

    private BigDecimal orderRegistrationAmount;

    private String requestDate;

    private String confirmationDate;

    private String description;

    private String purchaseNumber;

    private String purchaseCurrencyName;

    private OrderRegistrationInfoDTO purchaseFromOtherResources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvidenceCode() {
        return evidenceCode;
    }

    public void setEvidenceCode(String evidenceCode) {
        this.evidenceCode = evidenceCode;
    }

    public String getCurrencySupplierDescription() {
        return currencySupplierDescription;
    }

    public void setCurrencySupplierDescription(String currencySupplierDescription) {
        this.currencySupplierDescription = currencySupplierDescription;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(BigDecimal purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public BigDecimal getOrderRegistrationAmount() {
        return orderRegistrationAmount;
    }

    public void setOrderRegistrationAmount(BigDecimal orderRegistrationAmount) {
        this.orderRegistrationAmount = orderRegistrationAmount;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getPurchaseCurrencyName() {
        return purchaseCurrencyName;
    }

    public void setPurchaseCurrencyName(String purchaseCurrencyName) {
        this.purchaseCurrencyName = purchaseCurrencyName;
    }

    public OrderRegistrationInfoDTO getPurchaseFromOtherResources() {
        return purchaseFromOtherResources;
    }

    public void setPurchaseFromOtherResources(OrderRegistrationInfoDTO purchaseFromOtherResources) {
        this.purchaseFromOtherResources = purchaseFromOtherResources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseFromOtherResourcesDTO)) {
            return false;
        }

        PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO = (PurchaseFromOtherResourcesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, purchaseFromOtherResourcesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseFromOtherResourcesDTO{" +
            "id=" + getId() +
            ", evidenceCode='" + getEvidenceCode() + "'" +
            ", currencySupplierDescription='" + getCurrencySupplierDescription() + "'" +
            ", amount=" + getAmount() +
            ", purchaseRate=" + getPurchaseRate() +
            ", orderRegistrationAmount=" + getOrderRegistrationAmount() +
            ", requestDate='" + getRequestDate() + "'" +
            ", confirmationDate='" + getConfirmationDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", purchaseNumber='" + getPurchaseNumber() + "'" +
            ", purchaseCurrencyName='" + getPurchaseCurrencyName() + "'" +
            ", purchaseFromOtherResources=" + getPurchaseFromOtherResources() +
            "}";
    }
}
