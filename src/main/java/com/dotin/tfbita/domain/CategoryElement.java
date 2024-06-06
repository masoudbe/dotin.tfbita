package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A CategoryElement.
 */
@Entity
@Table(name = "category_element")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo orderRegType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo requestType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo importType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo operationTyp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo currencyProvisionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo paymentTool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo activityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo ownerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo externalCustomerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo transportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "orderRegistrationInfo" }, allowSetters = true)
    private PurchaseFromOtherResources currencySupplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "orderRegistrationInfo" }, allowSetters = true)
    private PurchaseFromOtherResources statusPurchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo transportVehicleType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CategoryElement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public CategoryElement value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public CategoryElement categoryName(String categoryName) {
        this.setCategoryName(categoryName);
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return this.code;
    }

    public CategoryElement code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderRegistrationInfo getOrderRegType() {
        return this.orderRegType;
    }

    public void setOrderRegType(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegType = orderRegistrationInfo;
    }

    public CategoryElement orderRegType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOrderRegType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getRequestType() {
        return this.requestType;
    }

    public void setRequestType(OrderRegistrationInfo orderRegistrationInfo) {
        this.requestType = orderRegistrationInfo;
    }

    public CategoryElement requestType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setRequestType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getImportType() {
        return this.importType;
    }

    public void setImportType(OrderRegistrationInfo orderRegistrationInfo) {
        this.importType = orderRegistrationInfo;
    }

    public CategoryElement importType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setImportType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getOperationTyp() {
        return this.operationTyp;
    }

    public void setOperationTyp(OrderRegistrationInfo orderRegistrationInfo) {
        this.operationTyp = orderRegistrationInfo;
    }

    public CategoryElement operationTyp(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOperationTyp(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getCurrencyProvisionType() {
        return this.currencyProvisionType;
    }

    public void setCurrencyProvisionType(OrderRegistrationInfo orderRegistrationInfo) {
        this.currencyProvisionType = orderRegistrationInfo;
    }

    public CategoryElement currencyProvisionType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setCurrencyProvisionType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getPaymentTool() {
        return this.paymentTool;
    }

    public void setPaymentTool(OrderRegistrationInfo orderRegistrationInfo) {
        this.paymentTool = orderRegistrationInfo;
    }

    public CategoryElement paymentTool(OrderRegistrationInfo orderRegistrationInfo) {
        this.setPaymentTool(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getActivityType() {
        return this.activityType;
    }

    public void setActivityType(OrderRegistrationInfo orderRegistrationInfo) {
        this.activityType = orderRegistrationInfo;
    }

    public CategoryElement activityType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setActivityType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getOwnerType() {
        return this.ownerType;
    }

    public void setOwnerType(OrderRegistrationInfo orderRegistrationInfo) {
        this.ownerType = orderRegistrationInfo;
    }

    public CategoryElement ownerType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOwnerType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getStatus() {
        return this.status;
    }

    public void setStatus(OrderRegistrationInfo orderRegistrationInfo) {
        this.status = orderRegistrationInfo;
    }

    public CategoryElement status(OrderRegistrationInfo orderRegistrationInfo) {
        this.setStatus(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getExternalCustomerType() {
        return this.externalCustomerType;
    }

    public void setExternalCustomerType(OrderRegistrationInfo orderRegistrationInfo) {
        this.externalCustomerType = orderRegistrationInfo;
    }

    public CategoryElement externalCustomerType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setExternalCustomerType(orderRegistrationInfo);
        return this;
    }

    public OrderRegistrationInfo getTransportType() {
        return this.transportType;
    }

    public void setTransportType(OrderRegistrationInfo orderRegistrationInfo) {
        this.transportType = orderRegistrationInfo;
    }

    public CategoryElement transportType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setTransportType(orderRegistrationInfo);
        return this;
    }

    public PurchaseFromOtherResources getCurrencySupplier() {
        return this.currencySupplier;
    }

    public void setCurrencySupplier(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.currencySupplier = purchaseFromOtherResources;
    }

    public CategoryElement currencySupplier(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.setCurrencySupplier(purchaseFromOtherResources);
        return this;
    }

    public PurchaseFromOtherResources getStatusPurchase() {
        return this.statusPurchase;
    }

    public void setStatusPurchase(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.statusPurchase = purchaseFromOtherResources;
    }

    public CategoryElement statusPurchase(PurchaseFromOtherResources purchaseFromOtherResources) {
        this.setStatusPurchase(purchaseFromOtherResources);
        return this;
    }

    public OrderRegistrationInfo getTransportVehicleType() {
        return this.transportVehicleType;
    }

    public void setTransportVehicleType(OrderRegistrationInfo orderRegistrationInfo) {
        this.transportVehicleType = orderRegistrationInfo;
    }

    public CategoryElement transportVehicleType(OrderRegistrationInfo orderRegistrationInfo) {
        this.setTransportVehicleType(orderRegistrationInfo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryElement)) {
            return false;
        }
        return getId() != null && getId().equals(((CategoryElement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryElement{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
