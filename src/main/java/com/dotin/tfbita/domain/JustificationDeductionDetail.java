package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A JustificationDeductionDetail.
 */
@Entity
@Table(name = "justification_deduction_detail")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JustificationDeductionDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "deduction_amount", precision = 21, scale = 2)
    private BigDecimal deductionAmount;

    @Column(name = "equivalent_deduction_amount", precision = 21, scale = 2)
    private BigDecimal equivalentDeductionAmount;

    @Column(name = "receive_currency_code")
    private String receiveCurrencyCode;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement deductionReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "justificationDeductionAmountParts", "justificationDeductionDetails" }, allowSetters = true)
    private JustificationDeductionAmount justificationDeductionAmount;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public JustificationDeductionDetail id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDeductionAmount() {
        return this.deductionAmount;
    }

    public JustificationDeductionDetail deductionAmount(BigDecimal deductionAmount) {
        this.setDeductionAmount(deductionAmount);
        return this;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public BigDecimal getEquivalentDeductionAmount() {
        return this.equivalentDeductionAmount;
    }

    public JustificationDeductionDetail equivalentDeductionAmount(BigDecimal equivalentDeductionAmount) {
        this.setEquivalentDeductionAmount(equivalentDeductionAmount);
        return this;
    }

    public void setEquivalentDeductionAmount(BigDecimal equivalentDeductionAmount) {
        this.equivalentDeductionAmount = equivalentDeductionAmount;
    }

    public String getReceiveCurrencyCode() {
        return this.receiveCurrencyCode;
    }

    public JustificationDeductionDetail receiveCurrencyCode(String receiveCurrencyCode) {
        this.setReceiveCurrencyCode(receiveCurrencyCode);
        return this;
    }

    public void setReceiveCurrencyCode(String receiveCurrencyCode) {
        this.receiveCurrencyCode = receiveCurrencyCode;
    }

    public String getComment() {
        return this.comment;
    }

    public JustificationDeductionDetail comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CategoryElement getDeductionReason() {
        return this.deductionReason;
    }

    public void setDeductionReason(CategoryElement categoryElement) {
        this.deductionReason = categoryElement;
    }

    public JustificationDeductionDetail deductionReason(CategoryElement categoryElement) {
        this.setDeductionReason(categoryElement);
        return this;
    }

    public JustificationDeductionAmount getJustificationDeductionAmount() {
        return this.justificationDeductionAmount;
    }

    public void setJustificationDeductionAmount(JustificationDeductionAmount justificationDeductionAmount) {
        this.justificationDeductionAmount = justificationDeductionAmount;
    }

    public JustificationDeductionDetail justificationDeductionAmount(JustificationDeductionAmount justificationDeductionAmount) {
        this.setJustificationDeductionAmount(justificationDeductionAmount);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JustificationDeductionDetail)) {
            return false;
        }
        return getId() != null && getId().equals(((JustificationDeductionDetail) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JustificationDeductionDetail{" +
            "id=" + getId() +
            ", deductionAmount=" + getDeductionAmount() +
            ", equivalentDeductionAmount=" + getEquivalentDeductionAmount() +
            ", receiveCurrencyCode='" + getReceiveCurrencyCode() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
