package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ServiceTariff.
 */
@Entity
@Table(name = "service_tariff")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServiceTariff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "services")
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

    public ServiceTariff id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public ServiceTariff code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public ServiceTariff title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Draft> getDrafts() {
        return this.drafts;
    }

    public void setDrafts(Set<Draft> drafts) {
        if (this.drafts != null) {
            this.drafts.forEach(i -> i.removeServices(this));
        }
        if (drafts != null) {
            drafts.forEach(i -> i.addServices(this));
        }
        this.drafts = drafts;
    }

    public ServiceTariff drafts(Set<Draft> drafts) {
        this.setDrafts(drafts);
        return this;
    }

    public ServiceTariff addDraft(Draft draft) {
        this.drafts.add(draft);
        draft.getServices().add(this);
        return this;
    }

    public ServiceTariff removeDraft(Draft draft) {
        this.drafts.remove(draft);
        draft.getServices().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceTariff)) {
            return false;
        }
        return getId() != null && getId().equals(((ServiceTariff) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceTariff{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
