package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A CreditTypeConditionInfo.
 */
@Entity
@Table(name = "credit_type_condition_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreditTypeConditionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "duration_from")
    private Integer durationFrom;

    @Column(name = "duration_to")
    private Integer durationTo;

    @Column(name = "price_from", precision = 21, scale = 2)
    private BigDecimal priceFrom;

    @Column(name = "price_to", precision = 21, scale = 2)
    private BigDecimal priceTo;

    @Column(name = "justification_disciplinary_topic")
    private Long justificationDisciplinaryTopic;

    @Column(name = "open_draft_disciplinary_topic")
    private Long openDraftDisciplinaryTopic;

    @Column(name = "other_costs_topic")
    private Long otherCostsTopic;

    @Column(name = "post_telegraph_swift_costs_topic")
    private Long postTelegraphSwiftCostsTopic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creditTypeConditionInfo")
    @JsonIgnoreProperties(
        value = { "serviceOrProduct", "neededIdentificationDocTypes", "productTypes", "assuranceTypes", "creditTypeConditionInfo" },
        allowSetters = true
    )
    private Set<CreditTypeCondition> conditions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "serviceOrProduct", "neededIdentificationDocTypes", "productTypes", "assuranceTypes", "creditTypeConditionInfo" },
        allowSetters = true
    )
    private CreditTypeCondition defaultCondition;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CreditTypeConditionInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDurationFrom() {
        return this.durationFrom;
    }

    public CreditTypeConditionInfo durationFrom(Integer durationFrom) {
        this.setDurationFrom(durationFrom);
        return this;
    }

    public void setDurationFrom(Integer durationFrom) {
        this.durationFrom = durationFrom;
    }

    public Integer getDurationTo() {
        return this.durationTo;
    }

    public CreditTypeConditionInfo durationTo(Integer durationTo) {
        this.setDurationTo(durationTo);
        return this;
    }

    public void setDurationTo(Integer durationTo) {
        this.durationTo = durationTo;
    }

    public BigDecimal getPriceFrom() {
        return this.priceFrom;
    }

    public CreditTypeConditionInfo priceFrom(BigDecimal priceFrom) {
        this.setPriceFrom(priceFrom);
        return this;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return this.priceTo;
    }

    public CreditTypeConditionInfo priceTo(BigDecimal priceTo) {
        this.setPriceTo(priceTo);
        return this;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    public Long getJustificationDisciplinaryTopic() {
        return this.justificationDisciplinaryTopic;
    }

    public CreditTypeConditionInfo justificationDisciplinaryTopic(Long justificationDisciplinaryTopic) {
        this.setJustificationDisciplinaryTopic(justificationDisciplinaryTopic);
        return this;
    }

    public void setJustificationDisciplinaryTopic(Long justificationDisciplinaryTopic) {
        this.justificationDisciplinaryTopic = justificationDisciplinaryTopic;
    }

    public Long getOpenDraftDisciplinaryTopic() {
        return this.openDraftDisciplinaryTopic;
    }

    public CreditTypeConditionInfo openDraftDisciplinaryTopic(Long openDraftDisciplinaryTopic) {
        this.setOpenDraftDisciplinaryTopic(openDraftDisciplinaryTopic);
        return this;
    }

    public void setOpenDraftDisciplinaryTopic(Long openDraftDisciplinaryTopic) {
        this.openDraftDisciplinaryTopic = openDraftDisciplinaryTopic;
    }

    public Long getOtherCostsTopic() {
        return this.otherCostsTopic;
    }

    public CreditTypeConditionInfo otherCostsTopic(Long otherCostsTopic) {
        this.setOtherCostsTopic(otherCostsTopic);
        return this;
    }

    public void setOtherCostsTopic(Long otherCostsTopic) {
        this.otherCostsTopic = otherCostsTopic;
    }

    public Long getPostTelegraphSwiftCostsTopic() {
        return this.postTelegraphSwiftCostsTopic;
    }

    public CreditTypeConditionInfo postTelegraphSwiftCostsTopic(Long postTelegraphSwiftCostsTopic) {
        this.setPostTelegraphSwiftCostsTopic(postTelegraphSwiftCostsTopic);
        return this;
    }

    public void setPostTelegraphSwiftCostsTopic(Long postTelegraphSwiftCostsTopic) {
        this.postTelegraphSwiftCostsTopic = postTelegraphSwiftCostsTopic;
    }

    public Set<CreditTypeCondition> getConditions() {
        return this.conditions;
    }

    public void setConditions(Set<CreditTypeCondition> creditTypeConditions) {
        if (this.conditions != null) {
            this.conditions.forEach(i -> i.setCreditTypeConditionInfo(null));
        }
        if (creditTypeConditions != null) {
            creditTypeConditions.forEach(i -> i.setCreditTypeConditionInfo(this));
        }
        this.conditions = creditTypeConditions;
    }

    public CreditTypeConditionInfo conditions(Set<CreditTypeCondition> creditTypeConditions) {
        this.setConditions(creditTypeConditions);
        return this;
    }

    public CreditTypeConditionInfo addConditions(CreditTypeCondition creditTypeCondition) {
        this.conditions.add(creditTypeCondition);
        creditTypeCondition.setCreditTypeConditionInfo(this);
        return this;
    }

    public CreditTypeConditionInfo removeConditions(CreditTypeCondition creditTypeCondition) {
        this.conditions.remove(creditTypeCondition);
        creditTypeCondition.setCreditTypeConditionInfo(null);
        return this;
    }

    public CreditTypeCondition getDefaultCondition() {
        return this.defaultCondition;
    }

    public void setDefaultCondition(CreditTypeCondition creditTypeCondition) {
        this.defaultCondition = creditTypeCondition;
    }

    public CreditTypeConditionInfo defaultCondition(CreditTypeCondition creditTypeCondition) {
        this.setDefaultCondition(creditTypeCondition);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditTypeConditionInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((CreditTypeConditionInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreditTypeConditionInfo{" +
            "id=" + getId() +
            ", durationFrom=" + getDurationFrom() +
            ", durationTo=" + getDurationTo() +
            ", priceFrom=" + getPriceFrom() +
            ", priceTo=" + getPriceTo() +
            ", justificationDisciplinaryTopic=" + getJustificationDisciplinaryTopic() +
            ", openDraftDisciplinaryTopic=" + getOpenDraftDisciplinaryTopic() +
            ", otherCostsTopic=" + getOtherCostsTopic() +
            ", postTelegraphSwiftCostsTopic=" + getPostTelegraphSwiftCostsTopic() +
            "}";
    }
}
