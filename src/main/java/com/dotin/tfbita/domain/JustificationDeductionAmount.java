package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A JustificationDeductionAmount.
 */
@Entity
@Table(name = "justification_deduction_amount")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JustificationDeductionAmount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "deduction_amount", precision = 21, scale = 2)
    private BigDecimal deductionAmount;

    @Column(name = "remaining_deduction_amount", precision = 21, scale = 2)
    private BigDecimal remainingDeductionAmount;

    @Column(name = "received_deduction_amount", precision = 21, scale = 2)
    private BigDecimal receivedDeductionAmount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "justificationDeductionAmount")
    @JsonIgnoreProperties(value = { "justificationDeductionAmount" }, allowSetters = true)
    private Set<JustificationDeductionAmountPart> justificationDeductionAmountParts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "justificationDeductionAmount")
    @JsonIgnoreProperties(value = { "deductionReason", "justificationDeductionAmount" }, allowSetters = true)
    private Set<JustificationDeductionDetail> justificationDeductionDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public JustificationDeductionAmount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDeductionAmount() {
        return this.deductionAmount;
    }

    public JustificationDeductionAmount deductionAmount(BigDecimal deductionAmount) {
        this.setDeductionAmount(deductionAmount);
        return this;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public BigDecimal getRemainingDeductionAmount() {
        return this.remainingDeductionAmount;
    }

    public JustificationDeductionAmount remainingDeductionAmount(BigDecimal remainingDeductionAmount) {
        this.setRemainingDeductionAmount(remainingDeductionAmount);
        return this;
    }

    public void setRemainingDeductionAmount(BigDecimal remainingDeductionAmount) {
        this.remainingDeductionAmount = remainingDeductionAmount;
    }

    public BigDecimal getReceivedDeductionAmount() {
        return this.receivedDeductionAmount;
    }

    public JustificationDeductionAmount receivedDeductionAmount(BigDecimal receivedDeductionAmount) {
        this.setReceivedDeductionAmount(receivedDeductionAmount);
        return this;
    }

    public void setReceivedDeductionAmount(BigDecimal receivedDeductionAmount) {
        this.receivedDeductionAmount = receivedDeductionAmount;
    }

    public Set<JustificationDeductionAmountPart> getJustificationDeductionAmountParts() {
        return this.justificationDeductionAmountParts;
    }

    public void setJustificationDeductionAmountParts(Set<JustificationDeductionAmountPart> justificationDeductionAmountParts) {
        if (this.justificationDeductionAmountParts != null) {
            this.justificationDeductionAmountParts.forEach(i -> i.setJustificationDeductionAmount(null));
        }
        if (justificationDeductionAmountParts != null) {
            justificationDeductionAmountParts.forEach(i -> i.setJustificationDeductionAmount(this));
        }
        this.justificationDeductionAmountParts = justificationDeductionAmountParts;
    }

    public JustificationDeductionAmount justificationDeductionAmountParts(
        Set<JustificationDeductionAmountPart> justificationDeductionAmountParts
    ) {
        this.setJustificationDeductionAmountParts(justificationDeductionAmountParts);
        return this;
    }

    public JustificationDeductionAmount addJustificationDeductionAmountParts(
        JustificationDeductionAmountPart justificationDeductionAmountPart
    ) {
        this.justificationDeductionAmountParts.add(justificationDeductionAmountPart);
        justificationDeductionAmountPart.setJustificationDeductionAmount(this);
        return this;
    }

    public JustificationDeductionAmount removeJustificationDeductionAmountParts(
        JustificationDeductionAmountPart justificationDeductionAmountPart
    ) {
        this.justificationDeductionAmountParts.remove(justificationDeductionAmountPart);
        justificationDeductionAmountPart.setJustificationDeductionAmount(null);
        return this;
    }

    public Set<JustificationDeductionDetail> getJustificationDeductionDetails() {
        return this.justificationDeductionDetails;
    }

    public void setJustificationDeductionDetails(Set<JustificationDeductionDetail> justificationDeductionDetails) {
        if (this.justificationDeductionDetails != null) {
            this.justificationDeductionDetails.forEach(i -> i.setJustificationDeductionAmount(null));
        }
        if (justificationDeductionDetails != null) {
            justificationDeductionDetails.forEach(i -> i.setJustificationDeductionAmount(this));
        }
        this.justificationDeductionDetails = justificationDeductionDetails;
    }

    public JustificationDeductionAmount justificationDeductionDetails(Set<JustificationDeductionDetail> justificationDeductionDetails) {
        this.setJustificationDeductionDetails(justificationDeductionDetails);
        return this;
    }

    public JustificationDeductionAmount addJustificationDeductionDetails(JustificationDeductionDetail justificationDeductionDetail) {
        this.justificationDeductionDetails.add(justificationDeductionDetail);
        justificationDeductionDetail.setJustificationDeductionAmount(this);
        return this;
    }

    public JustificationDeductionAmount removeJustificationDeductionDetails(JustificationDeductionDetail justificationDeductionDetail) {
        this.justificationDeductionDetails.remove(justificationDeductionDetail);
        justificationDeductionDetail.setJustificationDeductionAmount(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JustificationDeductionAmount)) {
            return false;
        }
        return getId() != null && getId().equals(((JustificationDeductionAmount) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JustificationDeductionAmount{" +
            "id=" + getId() +
            ", deductionAmount=" + getDeductionAmount() +
            ", remainingDeductionAmount=" + getRemainingDeductionAmount() +
            ", receivedDeductionAmount=" + getReceivedDeductionAmount() +
            "}";
    }
}
