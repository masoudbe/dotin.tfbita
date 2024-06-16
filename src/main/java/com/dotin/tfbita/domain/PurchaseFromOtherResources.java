package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A PurchaseFromOtherResources.
 */
@Entity
@Table(name = "purchase_from_other_resources")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PurchaseFromOtherResources implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "evidence_code")
    private String evidenceCode;

    @Column(name = "currency_supplier_description")
    private String currencySupplierDescription;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "purchase_rate", precision = 21, scale = 2)
    private BigDecimal purchaseRate;

    @Column(name = "order_registration_amount", precision = 21, scale = 2)
    private BigDecimal orderRegistrationAmount;

    @Column(name = "request_date")
    private String requestDate;

    @Column(name = "confirmation_date")
    private String confirmationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "purchase_number")
    private String purchaseNumber;

    @Column(name = "purchase_currency_name")
    private String purchaseCurrencyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo purchaseFromOtherResources;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PurchaseFromOtherResources id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvidenceCode() {
        return this.evidenceCode;
    }

    public PurchaseFromOtherResources evidenceCode(String evidenceCode) {
        this.setEvidenceCode(evidenceCode);
        return this;
    }

    public void setEvidenceCode(String evidenceCode) {
        this.evidenceCode = evidenceCode;
    }

    public String getCurrencySupplierDescription() {
        return this.currencySupplierDescription;
    }

    public PurchaseFromOtherResources currencySupplierDescription(String currencySupplierDescription) {
        this.setCurrencySupplierDescription(currencySupplierDescription);
        return this;
    }

    public void setCurrencySupplierDescription(String currencySupplierDescription) {
        this.currencySupplierDescription = currencySupplierDescription;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public PurchaseFromOtherResources amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPurchaseRate() {
        return this.purchaseRate;
    }

    public PurchaseFromOtherResources purchaseRate(BigDecimal purchaseRate) {
        this.setPurchaseRate(purchaseRate);
        return this;
    }

    public void setPurchaseRate(BigDecimal purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    public BigDecimal getOrderRegistrationAmount() {
        return this.orderRegistrationAmount;
    }

    public PurchaseFromOtherResources orderRegistrationAmount(BigDecimal orderRegistrationAmount) {
        this.setOrderRegistrationAmount(orderRegistrationAmount);
        return this;
    }

    public void setOrderRegistrationAmount(BigDecimal orderRegistrationAmount) {
        this.orderRegistrationAmount = orderRegistrationAmount;
    }

    public String getRequestDate() {
        return this.requestDate;
    }

    public PurchaseFromOtherResources requestDate(String requestDate) {
        this.setRequestDate(requestDate);
        return this;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getConfirmationDate() {
        return this.confirmationDate;
    }

    public PurchaseFromOtherResources confirmationDate(String confirmationDate) {
        this.setConfirmationDate(confirmationDate);
        return this;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getDescription() {
        return this.description;
    }

    public PurchaseFromOtherResources description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurchaseNumber() {
        return this.purchaseNumber;
    }

    public PurchaseFromOtherResources purchaseNumber(String purchaseNumber) {
        this.setPurchaseNumber(purchaseNumber);
        return this;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getPurchaseCurrencyName() {
        return this.purchaseCurrencyName;
    }

    public PurchaseFromOtherResources purchaseCurrencyName(String purchaseCurrencyName) {
        this.setPurchaseCurrencyName(purchaseCurrencyName);
        return this;
    }

    public void setPurchaseCurrencyName(String purchaseCurrencyName) {
        this.purchaseCurrencyName = purchaseCurrencyName;
    }

    public OrderRegistrationInfo getPurchaseFromOtherResources() {
        return this.purchaseFromOtherResources;
    }

    public void setPurchaseFromOtherResources(OrderRegistrationInfo orderRegistrationInfo) {
        this.purchaseFromOtherResources = orderRegistrationInfo;
    }

    public PurchaseFromOtherResources purchaseFromOtherResources(OrderRegistrationInfo orderRegistrationInfo) {
        this.setPurchaseFromOtherResources(orderRegistrationInfo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseFromOtherResources)) {
            return false;
        }
        return getId() != null && getId().equals(((PurchaseFromOtherResources) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseFromOtherResources{" +
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
            "}";
    }
}
