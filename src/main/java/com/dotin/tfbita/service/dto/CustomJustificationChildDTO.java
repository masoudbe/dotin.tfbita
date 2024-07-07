package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.CustomJustificationChild} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomJustificationChildDTO implements Serializable {

    private Long id;

    private String item;

    private String tariffCode;

    private BigDecimal productName;

    private Long productId;

    private BigDecimal boxCount;

    private String boxType;

    private BigDecimal pureWeight;

    private BigDecimal impureWeight;

    private BigDecimal amountCurrency;

    private CustomJustificationDTO customJustification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public BigDecimal getProductName() {
        return productName;
    }

    public void setProductName(BigDecimal productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(BigDecimal boxCount) {
        this.boxCount = boxCount;
    }

    public String getBoxType() {
        return boxType;
    }

    public void setBoxType(String boxType) {
        this.boxType = boxType;
    }

    public BigDecimal getPureWeight() {
        return pureWeight;
    }

    public void setPureWeight(BigDecimal pureWeight) {
        this.pureWeight = pureWeight;
    }

    public BigDecimal getImpureWeight() {
        return impureWeight;
    }

    public void setImpureWeight(BigDecimal impureWeight) {
        this.impureWeight = impureWeight;
    }

    public BigDecimal getAmountCurrency() {
        return amountCurrency;
    }

    public void setAmountCurrency(BigDecimal amountCurrency) {
        this.amountCurrency = amountCurrency;
    }

    public CustomJustificationDTO getCustomJustification() {
        return customJustification;
    }

    public void setCustomJustification(CustomJustificationDTO customJustification) {
        this.customJustification = customJustification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomJustificationChildDTO)) {
            return false;
        }

        CustomJustificationChildDTO customJustificationChildDTO = (CustomJustificationChildDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, customJustificationChildDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomJustificationChildDTO{" +
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
            ", customJustification=" + getCustomJustification() +
            "}";
    }
}
