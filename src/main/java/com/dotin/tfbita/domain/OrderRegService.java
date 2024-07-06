package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A OrderRegService.
 */
@Entity
@Table(name = "order_reg_service")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderRegService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency_amount", precision = 21, scale = 2)
    private BigDecimal currencyAmount;

    @Column(name = "unit")
    private String unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "drafts" }, allowSetters = true)
    private ServiceTariff serviceTariff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "serviceInfos",
            "purchaseFromOtherResourcesLists",
            "orderRegType",
            "requestType",
            "importType",
            "operationType",
            "currencyProvisionType",
            "paymentTool",
            "activityType",
            "ownerType",
            "status",
            "externalCustomerType",
            "transportVehicleType",
            "transportType",
            "destCoustomers",
            "cargoPlaceCustoms",
            "entranceBorders",
            "productInfos",
            "commissionTransactionNumbers",
            "licenceInfos",
        },
        allowSetters = true
    )
    private OrderRegistrationInfo orderRegistrationInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OrderRegService id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public OrderRegService amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrencyAmount() {
        return this.currencyAmount;
    }

    public OrderRegService currencyAmount(BigDecimal currencyAmount) {
        this.setCurrencyAmount(currencyAmount);
        return this;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getUnit() {
        return this.unit;
    }

    public OrderRegService unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ServiceTariff getServiceTariff() {
        return this.serviceTariff;
    }

    public void setServiceTariff(ServiceTariff serviceTariff) {
        this.serviceTariff = serviceTariff;
    }

    public OrderRegService serviceTariff(ServiceTariff serviceTariff) {
        this.setServiceTariff(serviceTariff);
        return this;
    }

    public OrderRegistrationInfo getOrderRegistrationInfo() {
        return this.orderRegistrationInfo;
    }

    public void setOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfo = orderRegistrationInfo;
    }

    public OrderRegService orderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOrderRegistrationInfo(orderRegistrationInfo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderRegService)) {
            return false;
        }
        return getId() != null && getId().equals(((OrderRegService) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderRegService{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", currencyAmount=" + getCurrencyAmount() +
            ", unit='" + getUnit() + "'" +
            "}";
    }
}
