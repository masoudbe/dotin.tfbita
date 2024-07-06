package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A JustificationDeductionAmountPart.
 */
@Entity
@Table(name = "justification_deduction_amount_part")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JustificationDeductionAmountPart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "receive_transaction_code")
    private String receiveTransactionCode;

    @Column(name = "return_transaction_code")
    private String returnTransactionCode;

    @Column(name = "date")
    private String date;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "amount_based_on_rial", precision = 21, scale = 2)
    private BigDecimal amountBasedOnRial;

    @Column(name = "deposit_number")
    private String depositNumber;

    @Column(name = "receive_currency_code")
    private String receiveCurrencyCode;

    @Column(name = "currency_rate_date")
    private String currencyRateDate;

    @Column(name = "sell_rate", precision = 21, scale = 2)
    private BigDecimal sellRate;

    @Column(name = "buy_rate", precision = 21, scale = 2)
    private BigDecimal buyRate;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "justificationDeductionAmountParts", "justificationDeductionDetails" }, allowSetters = true)
    private JustificationDeductionAmount justificationDeductionAmount;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public JustificationDeductionAmountPart id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiveTransactionCode() {
        return this.receiveTransactionCode;
    }

    public JustificationDeductionAmountPart receiveTransactionCode(String receiveTransactionCode) {
        this.setReceiveTransactionCode(receiveTransactionCode);
        return this;
    }

    public void setReceiveTransactionCode(String receiveTransactionCode) {
        this.receiveTransactionCode = receiveTransactionCode;
    }

    public String getReturnTransactionCode() {
        return this.returnTransactionCode;
    }

    public JustificationDeductionAmountPart returnTransactionCode(String returnTransactionCode) {
        this.setReturnTransactionCode(returnTransactionCode);
        return this;
    }

    public void setReturnTransactionCode(String returnTransactionCode) {
        this.returnTransactionCode = returnTransactionCode;
    }

    public String getDate() {
        return this.date;
    }

    public JustificationDeductionAmountPart date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public JustificationDeductionAmountPart amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountBasedOnRial() {
        return this.amountBasedOnRial;
    }

    public JustificationDeductionAmountPart amountBasedOnRial(BigDecimal amountBasedOnRial) {
        this.setAmountBasedOnRial(amountBasedOnRial);
        return this;
    }

    public void setAmountBasedOnRial(BigDecimal amountBasedOnRial) {
        this.amountBasedOnRial = amountBasedOnRial;
    }

    public String getDepositNumber() {
        return this.depositNumber;
    }

    public JustificationDeductionAmountPart depositNumber(String depositNumber) {
        this.setDepositNumber(depositNumber);
        return this;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public String getReceiveCurrencyCode() {
        return this.receiveCurrencyCode;
    }

    public JustificationDeductionAmountPart receiveCurrencyCode(String receiveCurrencyCode) {
        this.setReceiveCurrencyCode(receiveCurrencyCode);
        return this;
    }

    public void setReceiveCurrencyCode(String receiveCurrencyCode) {
        this.receiveCurrencyCode = receiveCurrencyCode;
    }

    public String getCurrencyRateDate() {
        return this.currencyRateDate;
    }

    public JustificationDeductionAmountPart currencyRateDate(String currencyRateDate) {
        this.setCurrencyRateDate(currencyRateDate);
        return this;
    }

    public void setCurrencyRateDate(String currencyRateDate) {
        this.currencyRateDate = currencyRateDate;
    }

    public BigDecimal getSellRate() {
        return this.sellRate;
    }

    public JustificationDeductionAmountPart sellRate(BigDecimal sellRate) {
        this.setSellRate(sellRate);
        return this;
    }

    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }

    public BigDecimal getBuyRate() {
        return this.buyRate;
    }

    public JustificationDeductionAmountPart buyRate(BigDecimal buyRate) {
        this.setBuyRate(buyRate);
        return this;
    }

    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }

    public String getComment() {
        return this.comment;
    }

    public JustificationDeductionAmountPart comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public JustificationDeductionAmount getJustificationDeductionAmount() {
        return this.justificationDeductionAmount;
    }

    public void setJustificationDeductionAmount(JustificationDeductionAmount justificationDeductionAmount) {
        this.justificationDeductionAmount = justificationDeductionAmount;
    }

    public JustificationDeductionAmountPart justificationDeductionAmount(JustificationDeductionAmount justificationDeductionAmount) {
        this.setJustificationDeductionAmount(justificationDeductionAmount);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JustificationDeductionAmountPart)) {
            return false;
        }
        return getId() != null && getId().equals(((JustificationDeductionAmountPart) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JustificationDeductionAmountPart{" +
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
            "}";
    }
}
