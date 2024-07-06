package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DraftExtend.
 */
@Entity
@Table(name = "draft_extend")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "time")
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
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
    private Draft draftField;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftExtend id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public DraftExtend date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public DraftExtend duration(Integer duration) {
        this.setDuration(duration);
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTime() {
        return this.time;
    }

    public DraftExtend time(String time) {
        this.setTime(time);
        return this;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Draft getDraftField() {
        return this.draftField;
    }

    public void setDraftField(Draft draft) {
        this.draftField = draft;
    }

    public DraftExtend draftField(Draft draft) {
        this.setDraftField(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftExtend)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftExtend) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftExtend{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", duration=" + getDuration() +
            ", time='" + getTime() + "'" +
            "}";
    }
}
