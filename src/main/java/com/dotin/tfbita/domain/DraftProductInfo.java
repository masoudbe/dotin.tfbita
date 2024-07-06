package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DraftProductInfo.
 */
@Entity
@Table(name = "draft_product_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "product_amount")
    private String productAmount;

    @Column(name = "product_dimension")
    private String productDimension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "attributeValues", "productType", "orderRegistrationInfos", "drafts", "customJustifications" },
        allowSetters = true
    )
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftProductInfos",
            "productDimension",
            "stateOfDocuments",
            "currencyProvisionFileType",
            "paymentCurrencyRateType",
            "paymentItem",
            "documentTransactionContainer",
            "draft",
            "draftCustomJustifications",
        },
        allowSetters = true
    )
    private DraftReceipt draftReceipt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftProductInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductAmount() {
        return this.productAmount;
    }

    public DraftProductInfo productAmount(String productAmount) {
        this.setProductAmount(productAmount);
        return this;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductDimension() {
        return this.productDimension;
    }

    public DraftProductInfo productDimension(String productDimension) {
        this.setProductDimension(productDimension);
        return this;
    }

    public void setProductDimension(String productDimension) {
        this.productDimension = productDimension;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public DraftProductInfo product(Product product) {
        this.setProduct(product);
        return this;
    }

    public DraftReceipt getDraftReceipt() {
        return this.draftReceipt;
    }

    public void setDraftReceipt(DraftReceipt draftReceipt) {
        this.draftReceipt = draftReceipt;
    }

    public DraftProductInfo draftReceipt(DraftReceipt draftReceipt) {
        this.setDraftReceipt(draftReceipt);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftProductInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftProductInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftProductInfo{" +
            "id=" + getId() +
            ", productAmount='" + getProductAmount() + "'" +
            ", productDimension='" + getProductDimension() + "'" +
            "}";
    }
}
