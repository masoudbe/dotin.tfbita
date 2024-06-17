package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DraftFactor.
 */
@Entity
@Table(name = "draft_factor")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftFactor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "eq_amount", precision = 21, scale = 2)
    private BigDecimal eqAmount;

    @Column(name = "factor_date")
    private String factorDate;

    @Column(name = "issue_date")
    private String issueDate;

    @Column(name = "serial")
    private String serial;

    @Column(name = "currency_name")
    private String currencyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft draftFactors;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftFactor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public DraftFactor amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }

    public DraftFactor description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getEqAmount() {
        return this.eqAmount;
    }

    public DraftFactor eqAmount(BigDecimal eqAmount) {
        this.setEqAmount(eqAmount);
        return this;
    }

    public void setEqAmount(BigDecimal eqAmount) {
        this.eqAmount = eqAmount;
    }

    public String getFactorDate() {
        return this.factorDate;
    }

    public DraftFactor factorDate(String factorDate) {
        this.setFactorDate(factorDate);
        return this;
    }

    public void setFactorDate(String factorDate) {
        this.factorDate = factorDate;
    }

    public String getIssueDate() {
        return this.issueDate;
    }

    public DraftFactor issueDate(String issueDate) {
        this.setIssueDate(issueDate);
        return this;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getSerial() {
        return this.serial;
    }

    public DraftFactor serial(String serial) {
        this.setSerial(serial);
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCurrencyName() {
        return this.currencyName;
    }

    public DraftFactor currencyName(String currencyName) {
        this.setCurrencyName(currencyName);
        return this;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Draft getDraftFactors() {
        return this.draftFactors;
    }

    public void setDraftFactors(Draft draft) {
        this.draftFactors = draft;
    }

    public DraftFactor draftFactors(Draft draft) {
        this.setDraftFactors(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftFactor)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftFactor) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftFactor{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", description='" + getDescription() + "'" +
            ", eqAmount=" + getEqAmount() +
            ", factorDate='" + getFactorDate() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", serial='" + getSerial() + "'" +
            ", currencyName='" + getCurrencyName() + "'" +
            "}";
    }
}
