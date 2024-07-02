package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A StringValue.
 */
@Entity
@Table(name = "string_value")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StringValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "commissionTransactionNumbers")
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
            "transportType",
            "destCoustomers",
            "cargoPlaceCustoms",
            "entranceBorders",
            "transportVehicleTypes",
            "productInfos",
            "commissionTransactionNumbers",
            "licenceInfos",
        },
        allowSetters = true
    )
    private Set<OrderRegistrationInfo> orderRegistrationInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public StringValue id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public StringValue value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<OrderRegistrationInfo> getOrderRegistrationInfos() {
        return this.orderRegistrationInfos;
    }

    public void setOrderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        if (this.orderRegistrationInfos != null) {
            this.orderRegistrationInfos.forEach(i -> i.removeCommissionTransactionNumber(this));
        }
        if (orderRegistrationInfos != null) {
            orderRegistrationInfos.forEach(i -> i.addCommissionTransactionNumber(this));
        }
        this.orderRegistrationInfos = orderRegistrationInfos;
    }

    public StringValue orderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        this.setOrderRegistrationInfos(orderRegistrationInfos);
        return this;
    }

    public StringValue addOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.add(orderRegistrationInfo);
        orderRegistrationInfo.getCommissionTransactionNumbers().add(this);
        return this;
    }

    public StringValue removeOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.remove(orderRegistrationInfo);
        orderRegistrationInfo.getCommissionTransactionNumbers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StringValue)) {
            return false;
        }
        return getId() != null && getId().equals(((StringValue) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StringValue{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            "}";
    }
}
