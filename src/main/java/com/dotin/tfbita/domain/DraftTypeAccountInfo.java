package com.dotin.tfbita.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DraftTypeAccountInfo.
 */
@Entity
@Table(name = "draft_type_account_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftTypeAccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "sell_currency_commission_account")
    private String sellCurrencyCommissionAccount;

    @Column(name = "income_account_number")
    private String incomeAccountNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftTypeAccountInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellCurrencyCommissionAccount() {
        return this.sellCurrencyCommissionAccount;
    }

    public DraftTypeAccountInfo sellCurrencyCommissionAccount(String sellCurrencyCommissionAccount) {
        this.setSellCurrencyCommissionAccount(sellCurrencyCommissionAccount);
        return this;
    }

    public void setSellCurrencyCommissionAccount(String sellCurrencyCommissionAccount) {
        this.sellCurrencyCommissionAccount = sellCurrencyCommissionAccount;
    }

    public String getIncomeAccountNumber() {
        return this.incomeAccountNumber;
    }

    public DraftTypeAccountInfo incomeAccountNumber(String incomeAccountNumber) {
        this.setIncomeAccountNumber(incomeAccountNumber);
        return this;
    }

    public void setIncomeAccountNumber(String incomeAccountNumber) {
        this.incomeAccountNumber = incomeAccountNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftTypeAccountInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftTypeAccountInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftTypeAccountInfo{" +
            "id=" + getId() +
            ", sellCurrencyCommissionAccount='" + getSellCurrencyCommissionAccount() + "'" +
            ", incomeAccountNumber='" + getIncomeAccountNumber() + "'" +
            "}";
    }
}
