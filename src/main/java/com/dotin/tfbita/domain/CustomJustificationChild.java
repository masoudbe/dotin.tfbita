package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A CustomJustificationChild.
 */
@Entity
@Table(name = "custom_justification_child")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomJustificationChild implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "tariff_code")
    private String tariffCode;

    @Column(name = "product_name", precision = 21, scale = 2)
    private BigDecimal productName;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "box_count", precision = 21, scale = 2)
    private BigDecimal boxCount;

    @Column(name = "box_type")
    private String boxType;

    @Column(name = "pure_weight", precision = 21, scale = 2)
    private BigDecimal pureWeight;

    @Column(name = "impure_weight", precision = 21, scale = 2)
    private BigDecimal impureWeight;

    @Column(name = "amount_currency", precision = 21, scale = 2)
    private BigDecimal amountCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
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
    private CustomJustification customJustification;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CustomJustificationChild id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return this.item;
    }

    public CustomJustificationChild item(String item) {
        this.setItem(item);
        return this;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTariffCode() {
        return this.tariffCode;
    }

    public CustomJustificationChild tariffCode(String tariffCode) {
        this.setTariffCode(tariffCode);
        return this;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public BigDecimal getProductName() {
        return this.productName;
    }

    public CustomJustificationChild productName(BigDecimal productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(BigDecimal productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return this.productId;
    }

    public CustomJustificationChild productId(Long productId) {
        this.setProductId(productId);
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getBoxCount() {
        return this.boxCount;
    }

    public CustomJustificationChild boxCount(BigDecimal boxCount) {
        this.setBoxCount(boxCount);
        return this;
    }

    public void setBoxCount(BigDecimal boxCount) {
        this.boxCount = boxCount;
    }

    public String getBoxType() {
        return this.boxType;
    }

    public CustomJustificationChild boxType(String boxType) {
        this.setBoxType(boxType);
        return this;
    }

    public void setBoxType(String boxType) {
        this.boxType = boxType;
    }

    public BigDecimal getPureWeight() {
        return this.pureWeight;
    }

    public CustomJustificationChild pureWeight(BigDecimal pureWeight) {
        this.setPureWeight(pureWeight);
        return this;
    }

    public void setPureWeight(BigDecimal pureWeight) {
        this.pureWeight = pureWeight;
    }

    public BigDecimal getImpureWeight() {
        return this.impureWeight;
    }

    public CustomJustificationChild impureWeight(BigDecimal impureWeight) {
        this.setImpureWeight(impureWeight);
        return this;
    }

    public void setImpureWeight(BigDecimal impureWeight) {
        this.impureWeight = impureWeight;
    }

    public BigDecimal getAmountCurrency() {
        return this.amountCurrency;
    }

    public CustomJustificationChild amountCurrency(BigDecimal amountCurrency) {
        this.setAmountCurrency(amountCurrency);
        return this;
    }

    public void setAmountCurrency(BigDecimal amountCurrency) {
        this.amountCurrency = amountCurrency;
    }

    public CustomJustification getCustomJustification() {
        return this.customJustification;
    }

    public void setCustomJustification(CustomJustification customJustification) {
        this.customJustification = customJustification;
    }

    public CustomJustificationChild customJustification(CustomJustification customJustification) {
        this.setCustomJustification(customJustification);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomJustificationChild)) {
            return false;
        }
        return getId() != null && getId().equals(((CustomJustificationChild) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomJustificationChild{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", tariffCode='" + getTariffCode() + "'" +
            ", productName=" + getProductName() +
            ", productId=" + getProductId() +
            ", boxCount=" + getBoxCount() +
            ", boxType='" + getBoxType() + "'" +
            ", pureWeight=" + getPureWeight() +
            ", impureWeight=" + getImpureWeight() +
            ", amountCurrency=" + getAmountCurrency() +
            "}";
    }
}
