package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.JustificationDeductionDetail} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JustificationDeductionDetailDTO implements Serializable {

    private Long id;

    private BigDecimal deductionAmount;

    private BigDecimal equivalentDeductionAmount;

    private String receiveCurrencyCode;

    private String comment;

    private CategoryElementDTO deductionReason;

    private JustificationDeductionAmountDTO justificationDeductionAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(BigDecimal deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public BigDecimal getEquivalentDeductionAmount() {
        return equivalentDeductionAmount;
    }

    public void setEquivalentDeductionAmount(BigDecimal equivalentDeductionAmount) {
        this.equivalentDeductionAmount = equivalentDeductionAmount;
    }

    public String getReceiveCurrencyCode() {
        return receiveCurrencyCode;
    }

    public void setReceiveCurrencyCode(String receiveCurrencyCode) {
        this.receiveCurrencyCode = receiveCurrencyCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CategoryElementDTO getDeductionReason() {
        return deductionReason;
    }

    public void setDeductionReason(CategoryElementDTO deductionReason) {
        this.deductionReason = deductionReason;
    }

    public JustificationDeductionAmountDTO getJustificationDeductionAmount() {
        return justificationDeductionAmount;
    }

    public void setJustificationDeductionAmount(JustificationDeductionAmountDTO justificationDeductionAmount) {
        this.justificationDeductionAmount = justificationDeductionAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JustificationDeductionDetailDTO)) {
            return false;
        }

        JustificationDeductionDetailDTO justificationDeductionDetailDTO = (JustificationDeductionDetailDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, justificationDeductionDetailDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JustificationDeductionDetailDTO{" +
            "id=" + getId() +
            ", deductionAmount=" + getDeductionAmount() +
            ", equivalentDeductionAmount=" + getEquivalentDeductionAmount() +
            ", receiveCurrencyCode='" + getReceiveCurrencyCode() + "'" +
            ", comment='" + getComment() + "'" +
            ", deductionReason=" + getDeductionReason() +
            ", justificationDeductionAmount=" + getJustificationDeductionAmount() +
            "}";
    }
}
