package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.CreditTypeCondition} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreditTypeConditionDTO implements Serializable {

    private Long id;

    private BigDecimal assurancePercentage;

    private BigDecimal commissionRate;

    private BigDecimal customerPrepaymentRateFrom;

    private BigDecimal customerPrepaymentRateTo;

    private Integer durationFrom;

    private Integer durationTo;

    private BigDecimal orderRegistrationRightFrom;

    private BigDecimal orderRegistrationRightTo;

    private BigDecimal postSuspensionPeriodPenaltyRate;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    private Integer suspensionDurationFrom;

    private Integer suspensionDurationTo;

    private BigDecimal suspensionPeriodInterestRate;

    private BigDecimal updateCommissionRate;

    private String currencyCode;

    private CategoryElementDTO serviceOrProduct;

    private ObjectiveCategoryElementDTO neededIdentificationDocTypes;

    private ObjectiveCategoryElementDTO productTypes;

    private ObjectiveCategoryElementDTO assuranceTypes;

    private CreditTypeConditionInfoDTO creditTypeConditionInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAssurancePercentage() {
        return assurancePercentage;
    }

    public void setAssurancePercentage(BigDecimal assurancePercentage) {
        this.assurancePercentage = assurancePercentage;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getCustomerPrepaymentRateFrom() {
        return customerPrepaymentRateFrom;
    }

    public void setCustomerPrepaymentRateFrom(BigDecimal customerPrepaymentRateFrom) {
        this.customerPrepaymentRateFrom = customerPrepaymentRateFrom;
    }

    public BigDecimal getCustomerPrepaymentRateTo() {
        return customerPrepaymentRateTo;
    }

    public void setCustomerPrepaymentRateTo(BigDecimal customerPrepaymentRateTo) {
        this.customerPrepaymentRateTo = customerPrepaymentRateTo;
    }

    public Integer getDurationFrom() {
        return durationFrom;
    }

    public void setDurationFrom(Integer durationFrom) {
        this.durationFrom = durationFrom;
    }

    public Integer getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(Integer durationTo) {
        this.durationTo = durationTo;
    }

    public BigDecimal getOrderRegistrationRightFrom() {
        return orderRegistrationRightFrom;
    }

    public void setOrderRegistrationRightFrom(BigDecimal orderRegistrationRightFrom) {
        this.orderRegistrationRightFrom = orderRegistrationRightFrom;
    }

    public BigDecimal getOrderRegistrationRightTo() {
        return orderRegistrationRightTo;
    }

    public void setOrderRegistrationRightTo(BigDecimal orderRegistrationRightTo) {
        this.orderRegistrationRightTo = orderRegistrationRightTo;
    }

    public BigDecimal getPostSuspensionPeriodPenaltyRate() {
        return postSuspensionPeriodPenaltyRate;
    }

    public void setPostSuspensionPeriodPenaltyRate(BigDecimal postSuspensionPeriodPenaltyRate) {
        this.postSuspensionPeriodPenaltyRate = postSuspensionPeriodPenaltyRate;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getSuspensionDurationFrom() {
        return suspensionDurationFrom;
    }

    public void setSuspensionDurationFrom(Integer suspensionDurationFrom) {
        this.suspensionDurationFrom = suspensionDurationFrom;
    }

    public Integer getSuspensionDurationTo() {
        return suspensionDurationTo;
    }

    public void setSuspensionDurationTo(Integer suspensionDurationTo) {
        this.suspensionDurationTo = suspensionDurationTo;
    }

    public BigDecimal getSuspensionPeriodInterestRate() {
        return suspensionPeriodInterestRate;
    }

    public void setSuspensionPeriodInterestRate(BigDecimal suspensionPeriodInterestRate) {
        this.suspensionPeriodInterestRate = suspensionPeriodInterestRate;
    }

    public BigDecimal getUpdateCommissionRate() {
        return updateCommissionRate;
    }

    public void setUpdateCommissionRate(BigDecimal updateCommissionRate) {
        this.updateCommissionRate = updateCommissionRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public CategoryElementDTO getServiceOrProduct() {
        return serviceOrProduct;
    }

    public void setServiceOrProduct(CategoryElementDTO serviceOrProduct) {
        this.serviceOrProduct = serviceOrProduct;
    }

    public ObjectiveCategoryElementDTO getNeededIdentificationDocTypes() {
        return neededIdentificationDocTypes;
    }

    public void setNeededIdentificationDocTypes(ObjectiveCategoryElementDTO neededIdentificationDocTypes) {
        this.neededIdentificationDocTypes = neededIdentificationDocTypes;
    }

    public ObjectiveCategoryElementDTO getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(ObjectiveCategoryElementDTO productTypes) {
        this.productTypes = productTypes;
    }

    public ObjectiveCategoryElementDTO getAssuranceTypes() {
        return assuranceTypes;
    }

    public void setAssuranceTypes(ObjectiveCategoryElementDTO assuranceTypes) {
        this.assuranceTypes = assuranceTypes;
    }

    public CreditTypeConditionInfoDTO getCreditTypeConditionInfo() {
        return creditTypeConditionInfo;
    }

    public void setCreditTypeConditionInfo(CreditTypeConditionInfoDTO creditTypeConditionInfo) {
        this.creditTypeConditionInfo = creditTypeConditionInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditTypeConditionDTO)) {
            return false;
        }

        CreditTypeConditionDTO creditTypeConditionDTO = (CreditTypeConditionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, creditTypeConditionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreditTypeConditionDTO{" +
            "id=" + getId() +
            ", assurancePercentage=" + getAssurancePercentage() +
            ", commissionRate=" + getCommissionRate() +
            ", customerPrepaymentRateFrom=" + getCustomerPrepaymentRateFrom() +
            ", customerPrepaymentRateTo=" + getCustomerPrepaymentRateTo() +
            ", durationFrom=" + getDurationFrom() +
            ", durationTo=" + getDurationTo() +
            ", orderRegistrationRightFrom=" + getOrderRegistrationRightFrom() +
            ", orderRegistrationRightTo=" + getOrderRegistrationRightTo() +
            ", postSuspensionPeriodPenaltyRate=" + getPostSuspensionPeriodPenaltyRate() +
            ", priceFrom=" + getPriceFrom() +
            ", priceTo=" + getPriceTo() +
            ", suspensionDurationFrom=" + getSuspensionDurationFrom() +
            ", suspensionDurationTo=" + getSuspensionDurationTo() +
            ", suspensionPeriodInterestRate=" + getSuspensionPeriodInterestRate() +
            ", updateCommissionRate=" + getUpdateCommissionRate() +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", serviceOrProduct=" + getServiceOrProduct() +
            ", neededIdentificationDocTypes=" + getNeededIdentificationDocTypes() +
            ", productTypes=" + getProductTypes() +
            ", assuranceTypes=" + getAssuranceTypes() +
            ", creditTypeConditionInfo=" + getCreditTypeConditionInfo() +
            "}";
    }
}
