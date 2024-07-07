package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.JustificationDeductionAmount} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JustificationDeductionAmountDTO implements Serializable {

    private Long id;

    private BigDecimal deductionAmount;

    private BigDecimal remainingDeductionAmount;

    private BigDecimal receivedDeductionAmount;

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

    public BigDecimal getRemainingDeductionAmount() {
        return remainingDeductionAmount;
    }

    public void setRemainingDeductionAmount(BigDecimal remainingDeductionAmount) {
        this.remainingDeductionAmount = remainingDeductionAmount;
    }

    public BigDecimal getReceivedDeductionAmount() {
        return receivedDeductionAmount;
    }

    public void setReceivedDeductionAmount(BigDecimal receivedDeductionAmount) {
        this.receivedDeductionAmount = receivedDeductionAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JustificationDeductionAmountDTO)) {
            return false;
        }

        JustificationDeductionAmountDTO justificationDeductionAmountDTO = (JustificationDeductionAmountDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, justificationDeductionAmountDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JustificationDeductionAmountDTO{" +
            "id=" + getId() +
            ", deductionAmount=" + getDeductionAmount() +
            ", remainingDeductionAmount=" + getRemainingDeductionAmount() +
            ", receivedDeductionAmount=" + getReceivedDeductionAmount() +
            "}";
    }
}
