package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.JustificationDeductionAmountPart} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JustificationDeductionAmountPartDTO implements Serializable {

    private Long id;

    private String receiveTransactionCode;

    private String returnTransactionCode;

    private String date;

    private BigDecimal amount;

    private BigDecimal amountBasedOnRial;

    private String depositNumber;

    private String receiveCurrencyCode;

    private String currencyRateDate;

    private BigDecimal sellRate;

    private BigDecimal buyRate;

    private String comment;

    private JustificationDeductionAmountDTO justificationDeductionAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiveTransactionCode() {
        return receiveTransactionCode;
    }

    public void setReceiveTransactionCode(String receiveTransactionCode) {
        this.receiveTransactionCode = receiveTransactionCode;
    }

    public String getReturnTransactionCode() {
        return returnTransactionCode;
    }

    public void setReturnTransactionCode(String returnTransactionCode) {
        this.returnTransactionCode = returnTransactionCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountBasedOnRial() {
        return amountBasedOnRial;
    }

    public void setAmountBasedOnRial(BigDecimal amountBasedOnRial) {
        this.amountBasedOnRial = amountBasedOnRial;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public String getReceiveCurrencyCode() {
        return receiveCurrencyCode;
    }

    public void setReceiveCurrencyCode(String receiveCurrencyCode) {
        this.receiveCurrencyCode = receiveCurrencyCode;
    }

    public String getCurrencyRateDate() {
        return currencyRateDate;
    }

    public void setCurrencyRateDate(String currencyRateDate) {
        this.currencyRateDate = currencyRateDate;
    }

    public BigDecimal getSellRate() {
        return sellRate;
    }

    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }

    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        if (!(o instanceof JustificationDeductionAmountPartDTO)) {
            return false;
        }

        JustificationDeductionAmountPartDTO justificationDeductionAmountPartDTO = (JustificationDeductionAmountPartDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, justificationDeductionAmountPartDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JustificationDeductionAmountPartDTO{" +
            "id=" + getId() +
            ", receiveTransactionCode='" + getReceiveTransactionCode() + "'" +
            ", returnTransactionCode='" + getReturnTransactionCode() + "'" +
            ", date='" + getDate() + "'" +
            ", amount=" + getAmount() +
            ", amountBasedOnRial=" + getAmountBasedOnRial() +
            ", depositNumber='" + getDepositNumber() + "'" +
            ", receiveCurrencyCode='" + getReceiveCurrencyCode() + "'" +
            ", currencyRateDate='" + getCurrencyRateDate() + "'" +
            ", sellRate=" + getSellRate() +
            ", buyRate=" + getBuyRate() +
            ", comment='" + getComment() + "'" +
            ", justificationDeductionAmount=" + getJustificationDeductionAmount() +
            "}";
    }
}
