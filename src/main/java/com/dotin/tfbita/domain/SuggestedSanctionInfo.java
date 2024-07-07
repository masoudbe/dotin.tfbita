package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A SuggestedSanctionInfo.
 */
@Entity
@Table(name = "suggested_sanction_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SuggestedSanctionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "sanction_serial")
    private String sanctionSerial;

    @Column(name = "personnel_code")
    private String personnelCode;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "suggestedSanctions")
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

    public SuggestedSanctionInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSanctionSerial() {
        return this.sanctionSerial;
    }

    public SuggestedSanctionInfo sanctionSerial(String sanctionSerial) {
        this.setSanctionSerial(sanctionSerial);
        return this;
    }

    public void setSanctionSerial(String sanctionSerial) {
        this.sanctionSerial = sanctionSerial;
    }

    public String getPersonnelCode() {
        return this.personnelCode;
    }

    public SuggestedSanctionInfo personnelCode(String personnelCode) {
        this.setPersonnelCode(personnelCode);
        return this;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
    }

    public Set<Draft> getDrafts() {
        return this.drafts;
    }

    public void setDrafts(Set<Draft> drafts) {
        if (this.drafts != null) {
            this.drafts.forEach(i -> i.removeSuggestedSanctions(this));
        }
        if (drafts != null) {
            drafts.forEach(i -> i.addSuggestedSanctions(this));
        }
        this.drafts = drafts;
    }

    public SuggestedSanctionInfo drafts(Set<Draft> drafts) {
        this.setDrafts(drafts);
        return this;
    }

    public SuggestedSanctionInfo addDraft(Draft draft) {
        this.drafts.add(draft);
        draft.getSuggestedSanctions().add(this);
        return this;
    }

    public SuggestedSanctionInfo removeDraft(Draft draft) {
        this.drafts.remove(draft);
        draft.getSuggestedSanctions().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuggestedSanctionInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((SuggestedSanctionInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SuggestedSanctionInfo{" +
            "id=" + getId() +
            ", sanctionSerial='" + getSanctionSerial() + "'" +
            ", personnelCode='" + getPersonnelCode() + "'" +
            "}";
    }
}
