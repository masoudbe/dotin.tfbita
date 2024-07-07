package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A LongValue.
 */
@Entity
@Table(name = "long_value")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LongValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "val")
    private Long val;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "customerNumbers")
    @JsonIgnoreProperties(
        value = {
            "receipts",
            "taxes",
            "extensions",
            "draftFactors",
            "usedAssurances",
            "draftJustifications",
            "chargedExchangeBroker",
            "insuranceLetterType",
            "advisorDepositType",
            "interfaceAdvisorDepositType",
            "coveringAdvisorDepositType",
            "impartType",
            "dealType",
            "transportVehicleType",
            "freightLetterType",
            "actionCode",
            "ownershipCode",
            "currencyContainerPlace",
            "paymentType",
            "draftSource",
            "loadSwitchPlace",
            "draftType",
            "statusInfo",
            "insuranceCompanyInfo",
            "advisingBank",
            "interfaceAdvisingBank",
            "coveringBank",
            "auditCompanyInfo",
            "transportType",
            "currencyExchangeInfo",
            "accountInfo",
            "destinationCustomCompanies",
            "sourceCustomCompanies",
            "services",
            "products",
            "sanctionSerials",
            "customerNumbers",
            "suggestedSanctions",
            "documentTransactionContainers",
        },
        allowSetters = true
    )
    private Set<Draft> drafts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LongValue id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVal() {
        return this.val;
    }

    public LongValue val(Long val) {
        this.setVal(val);
        return this;
    }

    public void setVal(Long val) {
        this.val = val;
    }

    public Set<Draft> getDrafts() {
        return this.drafts;
    }

    public void setDrafts(Set<Draft> drafts) {
        if (this.drafts != null) {
            this.drafts.forEach(i -> i.removeCustomerNumbers(this));
        }
        if (drafts != null) {
            drafts.forEach(i -> i.addCustomerNumbers(this));
        }
        this.drafts = drafts;
    }

    public LongValue drafts(Set<Draft> drafts) {
        this.setDrafts(drafts);
        return this;
    }

    public LongValue addDraft(Draft draft) {
        this.drafts.add(draft);
        draft.getCustomerNumbers().add(this);
        return this;
    }

    public LongValue removeDraft(Draft draft) {
        this.drafts.remove(draft);
        draft.getCustomerNumbers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LongValue)) {
            return false;
        }
        return getId() != null && getId().equals(((LongValue) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LongValue{" +
            "id=" + getId() +
            ", val=" + getVal() +
            "}";
    }
}
