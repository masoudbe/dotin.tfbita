package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnoreProperties(value = { "typeAttribute", "attributeValueGroup", "product" }, allowSetters = true)
    private Set<AttributeValue> attributeValues = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "productTypeAttributes" }, allowSetters = true)
    private ProductType productType;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "productInfos")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_product__draft",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "draft_id")
    )
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Set<Draft> drafts = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "products", "receipts", "draftCustomJustifications" }, allowSetters = true)
    private DraftReceipt draftProductInfos;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Product code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModificationDate() {
        return this.modificationDate;
    }

    public Product modificationDate(String modificationDate) {
        this.setModificationDate(modificationDate);
        return this;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AttributeValue> getAttributeValues() {
        return this.attributeValues;
    }

    public void setAttributeValues(Set<AttributeValue> attributeValues) {
        if (this.attributeValues != null) {
            this.attributeValues.forEach(i -> i.setProduct(null));
        }
        if (attributeValues != null) {
            attributeValues.forEach(i -> i.setProduct(this));
        }
        this.attributeValues = attributeValues;
    }

    public Product attributeValues(Set<AttributeValue> attributeValues) {
        this.setAttributeValues(attributeValues);
        return this;
    }

    public Product addAttributeValues(AttributeValue attributeValue) {
        this.attributeValues.add(attributeValue);
        attributeValue.setProduct(this);
        return this;
    }

    public Product removeAttributeValues(AttributeValue attributeValue) {
        this.attributeValues.remove(attributeValue);
        attributeValue.setProduct(null);
        return this;
    }

    public ProductType getProductType() {
        return this.productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Product productType(ProductType productType) {
        this.setProductType(productType);
        return this;
    }

    public Set<OrderRegistrationInfo> getOrderRegistrationInfos() {
        return this.orderRegistrationInfos;
    }

    public void setOrderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        if (this.orderRegistrationInfos != null) {
            this.orderRegistrationInfos.forEach(i -> i.removeProductInfo(this));
        }
        if (orderRegistrationInfos != null) {
            orderRegistrationInfos.forEach(i -> i.addProductInfo(this));
        }
        this.orderRegistrationInfos = orderRegistrationInfos;
    }

    public Product orderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        this.setOrderRegistrationInfos(orderRegistrationInfos);
        return this;
    }

    public Product addOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.add(orderRegistrationInfo);
        orderRegistrationInfo.getProductInfos().add(this);
        return this;
    }

    public Product removeOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.remove(orderRegistrationInfo);
        orderRegistrationInfo.getProductInfos().remove(this);
        return this;
    }

    public Set<Draft> getDrafts() {
        return this.drafts;
    }

    public void setDrafts(Set<Draft> drafts) {
        this.drafts = drafts;
    }

    public Product drafts(Set<Draft> drafts) {
        this.setDrafts(drafts);
        return this;
    }

    public Product addDraft(Draft draft) {
        this.drafts.add(draft);
        return this;
    }

    public Product removeDraft(Draft draft) {
        this.drafts.remove(draft);
        return this;
    }

    public DraftReceipt getDraftProductInfos() {
        return this.draftProductInfos;
    }

    public void setDraftProductInfos(DraftReceipt draftReceipt) {
        this.draftProductInfos = draftReceipt;
    }

    public Product draftProductInfos(DraftReceipt draftReceipt) {
        this.setDraftProductInfos(draftReceipt);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return getId() != null && getId().equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
