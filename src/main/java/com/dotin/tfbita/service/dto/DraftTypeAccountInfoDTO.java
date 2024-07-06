package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftTypeAccountInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftTypeAccountInfoDTO implements Serializable {

    private Long id;

    private String sellCurrencyCommissionAccount;

    private String incomeAccountNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellCurrencyCommissionAccount() {
        return sellCurrencyCommissionAccount;
    }

    public void setSellCurrencyCommissionAccount(String sellCurrencyCommissionAccount) {
        this.sellCurrencyCommissionAccount = sellCurrencyCommissionAccount;
    }

    public String getIncomeAccountNumber() {
        return incomeAccountNumber;
    }

    public void setIncomeAccountNumber(String incomeAccountNumber) {
        this.incomeAccountNumber = incomeAccountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftTypeAccountInfoDTO)) {
            return false;
        }

        DraftTypeAccountInfoDTO draftTypeAccountInfoDTO = (DraftTypeAccountInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftTypeAccountInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftTypeAccountInfoDTO{" +
            "id=" + getId() +
            ", sellCurrencyCommissionAccount='" + getSellCurrencyCommissionAccount() + "'" +
            ", incomeAccountNumber='" + getIncomeAccountNumber() + "'" +
            "}";
    }
}
