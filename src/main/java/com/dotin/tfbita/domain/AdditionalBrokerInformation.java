package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A AdditionalBrokerInformation.
 */
@Entity
@Table(name = "additional_broker_information")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AdditionalBrokerInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "date_of_start_relation")
    private String dateOfStartRelation;

    @Column(name = "credit_limit")
    private String creditLimit;

    @Column(name = "revoked_date")
    private String revokedDate;

    @Column(name = "revoked_note")
    private String revokedNote;

    @Column(name = "confidential")
    private String confidential;

    @Column(name = "other_broker_services")
    private String otherBrokerServices;

    @Column(name = "sanctioned_status")
    private String sanctionedStatus;

    @Column(name = "considerations")
    private String considerations;

    @Column(name = "description")
    private String description;

    @Column(name = "ways_of_communication")
    private String waysOfCommunication;

    @Column(name = "services_available")
    private String servicesAvailable;

    @Column(name = "customer_acceptance_policy")
    private String customerAcceptancePolicy;

    @JsonIgnoreProperties(
        value = {
            "additionalBrokerInformation",
            "advisorDeposits",
            "defaultVostroDeposit",
            "defaultNostroDeposit",
            "receiveMethod",
            "payMethod",
            "swiftBic",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "additionalBrokerInformation")
    private AdvisorDefinition advisorDefinition;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AdditionalBrokerInformation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfStartRelation() {
        return this.dateOfStartRelation;
    }

    public AdditionalBrokerInformation dateOfStartRelation(String dateOfStartRelation) {
        this.setDateOfStartRelation(dateOfStartRelation);
        return this;
    }

    public void setDateOfStartRelation(String dateOfStartRelation) {
        this.dateOfStartRelation = dateOfStartRelation;
    }

    public String getCreditLimit() {
        return this.creditLimit;
    }

    public AdditionalBrokerInformation creditLimit(String creditLimit) {
        this.setCreditLimit(creditLimit);
        return this;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getRevokedDate() {
        return this.revokedDate;
    }

    public AdditionalBrokerInformation revokedDate(String revokedDate) {
        this.setRevokedDate(revokedDate);
        return this;
    }

    public void setRevokedDate(String revokedDate) {
        this.revokedDate = revokedDate;
    }

    public String getRevokedNote() {
        return this.revokedNote;
    }

    public AdditionalBrokerInformation revokedNote(String revokedNote) {
        this.setRevokedNote(revokedNote);
        return this;
    }

    public void setRevokedNote(String revokedNote) {
        this.revokedNote = revokedNote;
    }

    public String getConfidential() {
        return this.confidential;
    }

    public AdditionalBrokerInformation confidential(String confidential) {
        this.setConfidential(confidential);
        return this;
    }

    public void setConfidential(String confidential) {
        this.confidential = confidential;
    }

    public String getOtherBrokerServices() {
        return this.otherBrokerServices;
    }

    public AdditionalBrokerInformation otherBrokerServices(String otherBrokerServices) {
        this.setOtherBrokerServices(otherBrokerServices);
        return this;
    }

    public void setOtherBrokerServices(String otherBrokerServices) {
        this.otherBrokerServices = otherBrokerServices;
    }

    public String getSanctionedStatus() {
        return this.sanctionedStatus;
    }

    public AdditionalBrokerInformation sanctionedStatus(String sanctionedStatus) {
        this.setSanctionedStatus(sanctionedStatus);
        return this;
    }

    public void setSanctionedStatus(String sanctionedStatus) {
        this.sanctionedStatus = sanctionedStatus;
    }

    public String getConsiderations() {
        return this.considerations;
    }

    public AdditionalBrokerInformation considerations(String considerations) {
        this.setConsiderations(considerations);
        return this;
    }

    public void setConsiderations(String considerations) {
        this.considerations = considerations;
    }

    public String getDescription() {
        return this.description;
    }

    public AdditionalBrokerInformation description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWaysOfCommunication() {
        return this.waysOfCommunication;
    }

    public AdditionalBrokerInformation waysOfCommunication(String waysOfCommunication) {
        this.setWaysOfCommunication(waysOfCommunication);
        return this;
    }

    public void setWaysOfCommunication(String waysOfCommunication) {
        this.waysOfCommunication = waysOfCommunication;
    }

    public String getServicesAvailable() {
        return this.servicesAvailable;
    }

    public AdditionalBrokerInformation servicesAvailable(String servicesAvailable) {
        this.setServicesAvailable(servicesAvailable);
        return this;
    }

    public void setServicesAvailable(String servicesAvailable) {
        this.servicesAvailable = servicesAvailable;
    }

    public String getCustomerAcceptancePolicy() {
        return this.customerAcceptancePolicy;
    }

    public AdditionalBrokerInformation customerAcceptancePolicy(String customerAcceptancePolicy) {
        this.setCustomerAcceptancePolicy(customerAcceptancePolicy);
        return this;
    }

    public void setCustomerAcceptancePolicy(String customerAcceptancePolicy) {
        this.customerAcceptancePolicy = customerAcceptancePolicy;
    }

    public AdvisorDefinition getAdvisorDefinition() {
        return this.advisorDefinition;
    }

    public void setAdvisorDefinition(AdvisorDefinition advisorDefinition) {
        if (this.advisorDefinition != null) {
            this.advisorDefinition.setAdditionalBrokerInformation(null);
        }
        if (advisorDefinition != null) {
            advisorDefinition.setAdditionalBrokerInformation(this);
        }
        this.advisorDefinition = advisorDefinition;
    }

    public AdditionalBrokerInformation advisorDefinition(AdvisorDefinition advisorDefinition) {
        this.setAdvisorDefinition(advisorDefinition);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdditionalBrokerInformation)) {
            return false;
        }
        return getId() != null && getId().equals(((AdditionalBrokerInformation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdditionalBrokerInformation{" +
            "id=" + getId() +
            ", dateOfStartRelation='" + getDateOfStartRelation() + "'" +
            ", creditLimit='" + getCreditLimit() + "'" +
            ", revokedDate='" + getRevokedDate() + "'" +
            ", revokedNote='" + getRevokedNote() + "'" +
            ", confidential='" + getConfidential() + "'" +
            ", otherBrokerServices='" + getOtherBrokerServices() + "'" +
            ", sanctionedStatus='" + getSanctionedStatus() + "'" +
            ", considerations='" + getConsiderations() + "'" +
            ", description='" + getDescription() + "'" +
            ", waysOfCommunication='" + getWaysOfCommunication() + "'" +
            ", servicesAvailable='" + getServicesAvailable() + "'" +
            ", customerAcceptancePolicy='" + getCustomerAcceptancePolicy() + "'" +
            "}";
    }
}
