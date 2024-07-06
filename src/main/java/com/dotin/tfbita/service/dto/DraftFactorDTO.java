package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftFactor} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftFactorDTO implements Serializable {

    private Long id;

    private BigDecimal amount;

    private String description;

    private BigDecimal eqAmount;

    private String factorDate;

    private String issueDate;

    private String serial;

    private String currencyCode;

    private DraftDTO draft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getEqAmount() {
        return eqAmount;
    }

    public void setEqAmount(BigDecimal eqAmount) {
        this.eqAmount = eqAmount;
    }

    public String getFactorDate() {
        return factorDate;
    }

    public void setFactorDate(String factorDate) {
        this.factorDate = factorDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public DraftDTO getDraft() {
        return draft;
    }

    public void setDraft(DraftDTO draft) {
        this.draft = draft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftFactorDTO)) {
            return false;
        }

        DraftFactorDTO draftFactorDTO = (DraftFactorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftFactorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftFactorDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", description='" + getDescription() + "'" +
            ", eqAmount=" + getEqAmount() +
            ", factorDate='" + getFactorDate() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", serial='" + getSerial() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", draft=" + getDraft() +
            "}";
    }
}
