package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.AdditionalBrokerInformation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AdditionalBrokerInformationDTO implements Serializable {

    private Long id;

    private String dateOfStartRelation;

    private String creditLimit;

    private String revokedDate;

    private String revokedNote;

    private String confidential;

    private String otherBrokerServices;

    private String sanctionedStatus;

    private String considerations;

    private String description;

    private String waysOfCommunication;

    private String servicesAvailable;

    private String customerAcceptancePolicy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateOfStartRelation() {
        return dateOfStartRelation;
    }

    public void setDateOfStartRelation(String dateOfStartRelation) {
        this.dateOfStartRelation = dateOfStartRelation;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getRevokedDate() {
        return revokedDate;
    }

    public void setRevokedDate(String revokedDate) {
        this.revokedDate = revokedDate;
    }

    public String getRevokedNote() {
        return revokedNote;
    }

    public void setRevokedNote(String revokedNote) {
        this.revokedNote = revokedNote;
    }

    public String getConfidential() {
        return confidential;
    }

    public void setConfidential(String confidential) {
        this.confidential = confidential;
    }

    public String getOtherBrokerServices() {
        return otherBrokerServices;
    }

    public void setOtherBrokerServices(String otherBrokerServices) {
        this.otherBrokerServices = otherBrokerServices;
    }

    public String getSanctionedStatus() {
        return sanctionedStatus;
    }

    public void setSanctionedStatus(String sanctionedStatus) {
        this.sanctionedStatus = sanctionedStatus;
    }

    public String getConsiderations() {
        return considerations;
    }

    public void setConsiderations(String considerations) {
        this.considerations = considerations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWaysOfCommunication() {
        return waysOfCommunication;
    }

    public void setWaysOfCommunication(String waysOfCommunication) {
        this.waysOfCommunication = waysOfCommunication;
    }

    public String getServicesAvailable() {
        return servicesAvailable;
    }

    public void setServicesAvailable(String servicesAvailable) {
        this.servicesAvailable = servicesAvailable;
    }

    public String getCustomerAcceptancePolicy() {
        return customerAcceptancePolicy;
    }

    public void setCustomerAcceptancePolicy(String customerAcceptancePolicy) {
        this.customerAcceptancePolicy = customerAcceptancePolicy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdditionalBrokerInformationDTO)) {
            return false;
        }

        AdditionalBrokerInformationDTO additionalBrokerInformationDTO = (AdditionalBrokerInformationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, additionalBrokerInformationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdditionalBrokerInformationDTO{" +
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
