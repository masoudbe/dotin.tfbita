package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DraftUsedAssurance.
 */
@Entity
@Table(name = "draft_used_assurance")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftUsedAssurance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "assurance_rate_id")
    private String assuranceRateId;

    @Column(name = "assurance_serial")
    private String assuranceSerial;

    @Column(name = "exchange_rate", precision = 21, scale = 2)
    private BigDecimal exchangeRate;

    @Column(name = "default_currency_used_cost", precision = 21, scale = 2)
    private BigDecimal defaultCurrencyUsedCost;

    @Column(name = "used_cost", precision = 21, scale = 2)
    private BigDecimal usedCost;

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
    private Draft usedAssurances;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftUsedAssurance id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssuranceRateId() {
        return this.assuranceRateId;
    }

    public DraftUsedAssurance assuranceRateId(String assuranceRateId) {
        this.setAssuranceRateId(assuranceRateId);
        return this;
    }

    public void setAssuranceRateId(String assuranceRateId) {
        this.assuranceRateId = assuranceRateId;
    }

    public String getAssuranceSerial() {
        return this.assuranceSerial;
    }

    public DraftUsedAssurance assuranceSerial(String assuranceSerial) {
        this.setAssuranceSerial(assuranceSerial);
        return this;
    }

    public void setAssuranceSerial(String assuranceSerial) {
        this.assuranceSerial = assuranceSerial;
    }

    public BigDecimal getExchangeRate() {
        return this.exchangeRate;
    }

    public DraftUsedAssurance exchangeRate(BigDecimal exchangeRate) {
        this.setExchangeRate(exchangeRate);
        return this;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getDefaultCurrencyUsedCost() {
        return this.defaultCurrencyUsedCost;
    }

    public DraftUsedAssurance defaultCurrencyUsedCost(BigDecimal defaultCurrencyUsedCost) {
        this.setDefaultCurrencyUsedCost(defaultCurrencyUsedCost);
        return this;
    }

    public void setDefaultCurrencyUsedCost(BigDecimal defaultCurrencyUsedCost) {
        this.defaultCurrencyUsedCost = defaultCurrencyUsedCost;
    }

    public BigDecimal getUsedCost() {
        return this.usedCost;
    }

    public DraftUsedAssurance usedCost(BigDecimal usedCost) {
        this.setUsedCost(usedCost);
        return this;
    }

    public void setUsedCost(BigDecimal usedCost) {
        this.usedCost = usedCost;
    }

    public Draft getUsedAssurances() {
        return this.usedAssurances;
    }

    public void setUsedAssurances(Draft draft) {
        this.usedAssurances = draft;
    }

    public DraftUsedAssurance usedAssurances(Draft draft) {
        this.setUsedAssurances(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftUsedAssurance)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftUsedAssurance) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftUsedAssurance{" +
            "id=" + getId() +
            ", assuranceRateId='" + getAssuranceRateId() + "'" +
            ", assuranceSerial='" + getAssuranceSerial() + "'" +
            ", exchangeRate=" + getExchangeRate() +
            ", defaultCurrencyUsedCost=" + getDefaultCurrencyUsedCost() +
            ", usedCost=" + getUsedCost() +
            "}";
    }
}
