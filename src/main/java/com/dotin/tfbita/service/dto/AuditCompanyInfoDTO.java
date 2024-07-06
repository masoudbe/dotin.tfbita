package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.AuditCompanyInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AuditCompanyInfoDTO implements Serializable {

    private Long id;

    private String address;

    private String barCodes;

    private String dateOfRegistration;

    private String fax;

    private String floor;

    private String inernationalobserviation;

    private String mainStreet;

    private String mobile;

    private String plaque;

    private String postalCode;

    private String registrationNumber;

    private String telephone;

    private Long tempId;

    private String theSideStreet;

    private String title;

    private String unit;

    private String cityCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarCodes() {
        return barCodes;
    }

    public void setBarCodes(String barCodes) {
        this.barCodes = barCodes;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getInernationalobserviation() {
        return inernationalobserviation;
    }

    public void setInernationalobserviation(String inernationalobserviation) {
        this.inernationalobserviation = inernationalobserviation;
    }

    public String getMainStreet() {
        return mainStreet;
    }

    public void setMainStreet(String mainStreet) {
        this.mainStreet = mainStreet;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public String getTheSideStreet() {
        return theSideStreet;
    }

    public void setTheSideStreet(String theSideStreet) {
        this.theSideStreet = theSideStreet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuditCompanyInfoDTO)) {
            return false;
        }

        AuditCompanyInfoDTO auditCompanyInfoDTO = (AuditCompanyInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, auditCompanyInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AuditCompanyInfoDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", barCodes='" + getBarCodes() + "'" +
            ", dateOfRegistration='" + getDateOfRegistration() + "'" +
            ", fax='" + getFax() + "'" +
            ", floor='" + getFloor() + "'" +
            ", inernationalobserviation='" + getInernationalobserviation() + "'" +
            ", mainStreet='" + getMainStreet() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", plaque='" + getPlaque() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", registrationNumber='" + getRegistrationNumber() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", tempId=" + getTempId() +
            ", theSideStreet='" + getTheSideStreet() + "'" +
            ", title='" + getTitle() + "'" +
            ", unit='" + getUnit() + "'" +
            ", cityCode='" + getCityCode() + "'" +
            "}";
    }
}
