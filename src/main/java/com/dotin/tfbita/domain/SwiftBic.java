package com.dotin.tfbita.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A SwiftBic.
 */
@Entity
@Table(name = "swift_bic")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SwiftBic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "address_3")
    private String address3;

    @Column(name = "address_4")
    private String address4;

    @Column(name = "bank")
    private String bank;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_name_2")
    private String bankName2;

    @Column(name = "bank_name_3")
    private String bankName3;

    @Column(name = "branch")
    private String branch;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_name_2")
    private String branchName2;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "location")
    private String location;

    @Column(name = "sub_type_indicator")
    private String subTypeIndicator;

    @Column(name = "zip")
    private String zip;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SwiftBic id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public SwiftBic address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return this.address2;
    }

    public SwiftBic address2(String address2) {
        this.setAddress2(address2);
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return this.address3;
    }

    public SwiftBic address3(String address3) {
        this.setAddress3(address3);
        return this;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return this.address4;
    }

    public SwiftBic address4(String address4) {
        this.setAddress4(address4);
        return this;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getBank() {
        return this.bank;
    }

    public SwiftBic bank(String bank) {
        this.setBank(bank);
        return this;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankName() {
        return this.bankName;
    }

    public SwiftBic bankName(String bankName) {
        this.setBankName(bankName);
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName2() {
        return this.bankName2;
    }

    public SwiftBic bankName2(String bankName2) {
        this.setBankName2(bankName2);
        return this;
    }

    public void setBankName2(String bankName2) {
        this.bankName2 = bankName2;
    }

    public String getBankName3() {
        return this.bankName3;
    }

    public SwiftBic bankName3(String bankName3) {
        this.setBankName3(bankName3);
        return this;
    }

    public void setBankName3(String bankName3) {
        this.bankName3 = bankName3;
    }

    public String getBranch() {
        return this.branch;
    }

    public SwiftBic branch(String branch) {
        this.setBranch(branch);
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public SwiftBic branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName2() {
        return this.branchName2;
    }

    public SwiftBic branchName2(String branchName2) {
        this.setBranchName2(branchName2);
        return this;
    }

    public void setBranchName2(String branchName2) {
        this.branchName2 = branchName2;
    }

    public String getCity() {
        return this.city;
    }

    public SwiftBic city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public SwiftBic country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return this.location;
    }

    public SwiftBic location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubTypeIndicator() {
        return this.subTypeIndicator;
    }

    public SwiftBic subTypeIndicator(String subTypeIndicator) {
        this.setSubTypeIndicator(subTypeIndicator);
        return this;
    }

    public void setSubTypeIndicator(String subTypeIndicator) {
        this.subTypeIndicator = subTypeIndicator;
    }

    public String getZip() {
        return this.zip;
    }

    public SwiftBic zip(String zip) {
        this.setZip(zip);
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SwiftBic)) {
            return false;
        }
        return getId() != null && getId().equals(((SwiftBic) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SwiftBic{" +
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
