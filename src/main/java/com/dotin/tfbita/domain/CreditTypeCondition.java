package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A CreditTypeCondition.
 */
@Entity
@Table(name = "credit_type_condition")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreditTypeCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "assurance_percentage", precision = 21, scale = 2)
    private BigDecimal assurancePercentage;

    @Column(name = "commission_rate", precision = 21, scale = 2)
    private BigDecimal commissionRate;

    @Column(name = "customer_prepayment_rate_from", precision = 21, scale = 2)
    private BigDecimal customerPrepaymentRateFrom;

    @Column(name = "customer_prepayment_rate_to", precision = 21, scale = 2)
    private BigDecimal customerPrepaymentRateTo;

    @Column(name = "duration_from")
    private Integer durationFrom;

    @Column(name = "duration_to")
    private Integer durationTo;

    @Column(name = "order_registration_right_from", precision = 21, scale = 2)
    private BigDecimal orderRegistrationRightFrom;

    @Column(name = "order_registration_right_to", precision = 21, scale = 2)
    private BigDecimal orderRegistrationRightTo;

    @Column(name = "post_suspension_period_penalty_rate", precision = 21, scale = 2)
    private BigDecimal postSuspensionPeriodPenaltyRate;

    @Column(name = "price_from", precision = 21, scale = 2)
    private BigDecimal priceFrom;

    @Column(name = "price_to", precision = 21, scale = 2)
    private BigDecimal priceTo;

    @Column(name = "suspension_duration_from")
    private Integer suspensionDurationFrom;

    @Column(name = "suspension_duration_to")
    private Integer suspensionDurationTo;

    @Column(name = "suspension_period_interest_rate", precision = 21, scale = 2)
    private BigDecimal suspensionPeriodInterestRate;

    @Column(name = "update_commission_rate", precision = 21, scale = 2)
    private BigDecimal updateCommissionRate;

    @Column(name = "currency_code")
    private String currencyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement serviceOrProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "objectiveCategory" }, allowSetters = true)
    private ObjectiveCategoryElement neededIdentificationDocTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "objectiveCategory" }, allowSetters = true)
    private ObjectiveCategoryElement productTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "objectiveCategory" }, allowSetters = true)
    private ObjectiveCategoryElement assuranceTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "conditions", "defaultCondition" }, allowSetters = true)
    private CreditTypeConditionInfo creditTypeConditionInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CreditTypeCondition id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAssurancePercentage() {
        return this.assurancePercentage;
    }

    public CreditTypeCondition assurancePercentage(BigDecimal assurancePercentage) {
        this.setAssurancePercentage(assurancePercentage);
        return this;
    }

    public void setAssurancePercentage(BigDecimal assurancePercentage) {
        this.assurancePercentage = assurancePercentage;
    }

    public BigDecimal getCommissionRate() {
        return this.commissionRate;
    }

    public CreditTypeCondition commissionRate(BigDecimal commissionRate) {
        this.setCommissionRate(commissionRate);
        return this;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getCustomerPrepaymentRateFrom() {
        return this.customerPrepaymentRateFrom;
    }

    public CreditTypeCondition customerPrepaymentRateFrom(BigDecimal customerPrepaymentRateFrom) {
        this.setCustomerPrepaymentRateFrom(customerPrepaymentRateFrom);
        return this;
    }

    public void setCustomerPrepaymentRateFrom(BigDecimal customerPrepaymentRateFrom) {
        this.customerPrepaymentRateFrom = customerPrepaymentRateFrom;
    }

    public BigDecimal getCustomerPrepaymentRateTo() {
        return this.customerPrepaymentRateTo;
    }

    public CreditTypeCondition customerPrepaymentRateTo(BigDecimal customerPrepaymentRateTo) {
        this.setCustomerPrepaymentRateTo(customerPrepaymentRateTo);
        return this;
    }

    public void setCustomerPrepaymentRateTo(BigDecimal customerPrepaymentRateTo) {
        this.customerPrepaymentRateTo = customerPrepaymentRateTo;
    }

    public Integer getDurationFrom() {
        return this.durationFrom;
    }

    public CreditTypeCondition durationFrom(Integer durationFrom) {
        this.setDurationFrom(durationFrom);
        return this;
    }

    public void setDurationFrom(Integer durationFrom) {
        this.durationFrom = durationFrom;
    }

    public Integer getDurationTo() {
        return this.durationTo;
    }

    public CreditTypeCondition durationTo(Integer durationTo) {
        this.setDurationTo(durationTo);
        return this;
    }

    public void setDurationTo(Integer durationTo) {
        this.durationTo = durationTo;
    }

    public BigDecimal getOrderRegistrationRightFrom() {
        return this.orderRegistrationRightFrom;
    }

    public CreditTypeCondition orderRegistrationRightFrom(BigDecimal orderRegistrationRightFrom) {
        this.setOrderRegistrationRightFrom(orderRegistrationRightFrom);
        return this;
    }

    public void setOrderRegistrationRightFrom(BigDecimal orderRegistrationRightFrom) {
        this.orderRegistrationRightFrom = orderRegistrationRightFrom;
    }

    public BigDecimal getOrderRegistrationRightTo() {
        return this.orderRegistrationRightTo;
    }

    public CreditTypeCondition orderRegistrationRightTo(BigDecimal orderRegistrationRightTo) {
        this.setOrderRegistrationRightTo(orderRegistrationRightTo);
        return this;
    }

    public void setOrderRegistrationRightTo(BigDecimal orderRegistrationRightTo) {
        this.orderRegistrationRightTo = orderRegistrationRightTo;
    }

    public BigDecimal getPostSuspensionPeriodPenaltyRate() {
        return this.postSuspensionPeriodPenaltyRate;
    }

    public CreditTypeCondition postSuspensionPeriodPenaltyRate(BigDecimal postSuspensionPeriodPenaltyRate) {
        this.setPostSuspensionPeriodPenaltyRate(postSuspensionPeriodPenaltyRate);
        return this;
    }

    public void setPostSuspensionPeriodPenaltyRate(BigDecimal postSuspensionPeriodPenaltyRate) {
        this.postSuspensionPeriodPenaltyRate = postSuspensionPeriodPenaltyRate;
    }

    public BigDecimal getPriceFrom() {
        return this.priceFrom;
    }

    public CreditTypeCondition priceFrom(BigDecimal priceFrom) {
        this.setPriceFrom(priceFrom);
        return this;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return this.priceTo;
    }

    public CreditTypeCondition priceTo(BigDecimal priceTo) {
        this.setPriceTo(priceTo);
        return this;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getSuspensionDurationFrom() {
        return this.suspensionDurationFrom;
    }

    public CreditTypeCondition suspensionDurationFrom(Integer suspensionDurationFrom) {
        this.setSuspensionDurationFrom(suspensionDurationFrom);
        return this;
    }

    public void setSuspensionDurationFrom(Integer suspensionDurationFrom) {
        this.suspensionDurationFrom = suspensionDurationFrom;
    }

    public Integer getSuspensionDurationTo() {
        return this.suspensionDurationTo;
    }

    public CreditTypeCondition suspensionDurationTo(Integer suspensionDurationTo) {
        this.setSuspensionDurationTo(suspensionDurationTo);
        return this;
    }

    public void setSuspensionDurationTo(Integer suspensionDurationTo) {
        this.suspensionDurationTo = suspensionDurationTo;
    }

    public BigDecimal getSuspensionPeriodInterestRate() {
        return this.suspensionPeriodInterestRate;
    }

    public CreditTypeCondition suspensionPeriodInterestRate(BigDecimal suspensionPeriodInterestRate) {
        this.setSuspensionPeriodInterestRate(suspensionPeriodInterestRate);
        return this;
    }

    public void setSuspensionPeriodInterestRate(BigDecimal suspensionPeriodInterestRate) {
        this.suspensionPeriodInterestRate = suspensionPeriodInterestRate;
    }

    public BigDecimal getUpdateCommissionRate() {
        return this.updateCommissionRate;
    }

    public CreditTypeCondition updateCommissionRate(BigDecimal updateCommissionRate) {
        this.setUpdateCommissionRate(updateCommissionRate);
        return this;
    }

    public void setUpdateCommissionRate(BigDecimal updateCommissionRate) {
        this.updateCommissionRate = updateCommissionRate;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public CreditTypeCondition currencyCode(String currencyCode) {
        this.setCurrencyCode(currencyCode);
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public CategoryElement getServiceOrProduct() {
        return this.serviceOrProduct;
    }

    public void setServiceOrProduct(CategoryElement categoryElement) {
        this.serviceOrProduct = categoryElement;
    }

    public CreditTypeCondition serviceOrProduct(CategoryElement categoryElement) {
        this.setServiceOrProduct(categoryElement);
        return this;
    }

    public ObjectiveCategoryElement getNeededIdentificationDocTypes() {
        return this.neededIdentificationDocTypes;
    }

    public void setNeededIdentificationDocTypes(ObjectiveCategoryElement objectiveCategoryElement) {
        this.neededIdentificationDocTypes = objectiveCategoryElement;
    }

    public CreditTypeCondition neededIdentificationDocTypes(ObjectiveCategoryElement objectiveCategoryElement) {
        this.setNeededIdentificationDocTypes(objectiveCategoryElement);
        return this;
    }

    public ObjectiveCategoryElement getProductTypes() {
        return this.productTypes;
    }

    public void setProductTypes(ObjectiveCategoryElement objectiveCategoryElement) {
        this.productTypes = objectiveCategoryElement;
    }

    public CreditTypeCondition productTypes(ObjectiveCategoryElement objectiveCategoryElement) {
        this.setProductTypes(objectiveCategoryElement);
        return this;
    }

    public ObjectiveCategoryElement getAssuranceTypes() {
        return this.assuranceTypes;
    }

    public void setAssuranceTypes(ObjectiveCategoryElement objectiveCategoryElement) {
        this.assuranceTypes = objectiveCategoryElement;
    }

    public CreditTypeCondition assuranceTypes(ObjectiveCategoryElement objectiveCategoryElement) {
        this.setAssuranceTypes(objectiveCategoryElement);
        return this;
    }

    public CreditTypeConditionInfo getCreditTypeConditionInfo() {
        return this.creditTypeConditionInfo;
    }

    public void setCreditTypeConditionInfo(CreditTypeConditionInfo creditTypeConditionInfo) {
        this.creditTypeConditionInfo = creditTypeConditionInfo;
    }

    public CreditTypeCondition creditTypeConditionInfo(CreditTypeConditionInfo creditTypeConditionInfo) {
        this.setCreditTypeConditionInfo(creditTypeConditionInfo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditTypeCondition)) {
            return false;
        }
        return getId() != null && getId().equals(((CreditTypeCondition) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreditTypeCondition{" +
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
            "}";
    }
}
