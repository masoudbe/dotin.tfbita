package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.CreditTypeConditionInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreditTypeConditionInfoDTO implements Serializable {

    private Long id;

    private Integer durationFrom;

    private Integer durationTo;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    private Long justificationDisciplinaryTopic;

    private Long openDraftDisciplinaryTopic;

    private Long otherCostsTopic;

    private Long postTelegraphSwiftCostsTopic;

    private CreditTypeConditionDTO defaultCondition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getJustificationDisciplinaryTopic() {
        return justificationDisciplinaryTopic;
    }

    public void setJustificationDisciplinaryTopic(Long justificationDisciplinaryTopic) {
        this.justificationDisciplinaryTopic = justificationDisciplinaryTopic;
    }

    public Long getOpenDraftDisciplinaryTopic() {
        return openDraftDisciplinaryTopic;
    }

    public void setOpenDraftDisciplinaryTopic(Long openDraftDisciplinaryTopic) {
        this.openDraftDisciplinaryTopic = openDraftDisciplinaryTopic;
    }

    public Long getOtherCostsTopic() {
        return otherCostsTopic;
    }

    public void setOtherCostsTopic(Long otherCostsTopic) {
        this.otherCostsTopic = otherCostsTopic;
    }

    public Long getPostTelegraphSwiftCostsTopic() {
        return postTelegraphSwiftCostsTopic;
    }

    public void setPostTelegraphSwiftCostsTopic(Long postTelegraphSwiftCostsTopic) {
        this.postTelegraphSwiftCostsTopic = postTelegraphSwiftCostsTopic;
    }

    public CreditTypeConditionDTO getDefaultCondition() {
        return defaultCondition;
    }

    public void setDefaultCondition(CreditTypeConditionDTO defaultCondition) {
        this.defaultCondition = defaultCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditTypeConditionInfoDTO)) {
            return false;
        }

        CreditTypeConditionInfoDTO creditTypeConditionInfoDTO = (CreditTypeConditionInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, creditTypeConditionInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreditTypeConditionInfoDTO{" +
            "id=" + getId() +
            ", durationFrom=" + getDurationFrom() +
            ", durationTo=" + getDurationTo() +
            ", priceFrom=" + getPriceFrom() +
            ", priceTo=" + getPriceTo() +
            ", justificationDisciplinaryTopic=" + getJustificationDisciplinaryTopic() +
            ", openDraftDisciplinaryTopic=" + getOpenDraftDisciplinaryTopic() +
            ", otherCostsTopic=" + getOtherCostsTopic() +
            ", postTelegraphSwiftCostsTopic=" + getPostTelegraphSwiftCostsTopic() +
            ", defaultCondition=" + getDefaultCondition() +
            "}";
    }
}
