package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftUsedAssurance} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftUsedAssuranceDTO implements Serializable {

    private Long id;

    private String assuranceRateId;

    private String assuranceSerial;

    private BigDecimal exchangeRate;

    private BigDecimal defaultCurrencyUsedCost;

    private BigDecimal usedCost;

    private DraftDTO usedAssurances;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssuranceRateId() {
        return assuranceRateId;
    }

    public void setAssuranceRateId(String assuranceRateId) {
        this.assuranceRateId = assuranceRateId;
    }

    public String getAssuranceSerial() {
        return assuranceSerial;
    }

    public void setAssuranceSerial(String assuranceSerial) {
        this.assuranceSerial = assuranceSerial;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getDefaultCurrencyUsedCost() {
        return defaultCurrencyUsedCost;
    }

    public void setDefaultCurrencyUsedCost(BigDecimal defaultCurrencyUsedCost) {
        this.defaultCurrencyUsedCost = defaultCurrencyUsedCost;
    }

    public BigDecimal getUsedCost() {
        return usedCost;
    }

    public void setUsedCost(BigDecimal usedCost) {
        this.usedCost = usedCost;
    }

    public DraftDTO getUsedAssurances() {
        return usedAssurances;
    }

    public void setUsedAssurances(DraftDTO usedAssurances) {
        this.usedAssurances = usedAssurances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftUsedAssuranceDTO)) {
            return false;
        }

        DraftUsedAssuranceDTO draftUsedAssuranceDTO = (DraftUsedAssuranceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftUsedAssuranceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftUsedAssuranceDTO{" +
            "id=" + getId() +
            ", assuranceRateId='" + getAssuranceRateId() + "'" +
            ", assuranceSerial='" + getAssuranceSerial() + "'" +
            ", exchangeRate=" + getExchangeRate() +
            ", defaultCurrencyUsedCost=" + getDefaultCurrencyUsedCost() +
            ", usedCost=" + getUsedCost() +
            ", usedAssurances=" + getUsedAssurances() +
            "}";
    }
}
