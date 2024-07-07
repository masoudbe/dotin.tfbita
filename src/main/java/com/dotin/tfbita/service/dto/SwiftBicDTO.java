package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.SwiftBic} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SwiftBicDTO implements Serializable {

    private Long id;

    private String address;

    private String address2;

    private String address3;

    private String address4;

    private String bank;

    private String bankName;

    private String bankName2;

    private String bankName3;

    private String branch;

    private String branchName;

    private String branchName2;

    private String city;

    private String country;

    private String location;

    private String subTypeIndicator;

    private String zip;

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

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName2() {
        return bankName2;
    }

    public void setBankName2(String bankName2) {
        this.bankName2 = bankName2;
    }

    public String getBankName3() {
        return bankName3;
    }

    public void setBankName3(String bankName3) {
        this.bankName3 = bankName3;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName2() {
        return branchName2;
    }

    public void setBranchName2(String branchName2) {
        this.branchName2 = branchName2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubTypeIndicator() {
        return subTypeIndicator;
    }

    public void setSubTypeIndicator(String subTypeIndicator) {
        this.subTypeIndicator = subTypeIndicator;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SwiftBicDTO)) {
            return false;
        }

        SwiftBicDTO swiftBicDTO = (SwiftBicDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, swiftBicDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SwiftBicDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", address3='" + getAddress3() + "'" +
            ", address4='" + getAddress4() + "'" +
            ", bank='" + getBank() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", bankName2='" + getBankName2() + "'" +
            ", bankName3='" + getBankName3() + "'" +
            ", branch='" + getBranch() + "'" +
            ", branchName='" + getBranchName() + "'" +
            ", branchName2='" + getBranchName2() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", location='" + getLocation() + "'" +
            ", subTypeIndicator='" + getSubTypeIndicator() + "'" +
            ", zip='" + getZip() + "'" +
            "}";
    }
}
