package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A AuditCompanyInfo.
 */
@Entity
@Table(name = "audit_company_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AuditCompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "bar_codes")
    private String barCodes;

    @Column(name = "date_of_registration")
    private String dateOfRegistration;

    @Column(name = "fax")
    private String fax;

    @Column(name = "floor")
    private String floor;

    @Column(name = "inernationalobserviation")
    private String inernationalobserviation;

    @Column(name = "main_street")
    private String mainStreet;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "plaque")
    private String plaque;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "temp_id")
    private Long tempId;

    @Column(name = "the_side_street")
    private String theSideStreet;

    @Column(name = "title")
    private String title;

    @Column(name = "unit")
    private String unit;

    @Column(name = "city")
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft auditCompanyInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AuditCompanyInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public AuditCompanyInfo address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarCodes() {
        return this.barCodes;
    }

    public AuditCompanyInfo barCodes(String barCodes) {
        this.setBarCodes(barCodes);
        return this;
    }

    public void setBarCodes(String barCodes) {
        this.barCodes = barCodes;
    }

    public String getDateOfRegistration() {
        return this.dateOfRegistration;
    }

    public AuditCompanyInfo dateOfRegistration(String dateOfRegistration) {
        this.setDateOfRegistration(dateOfRegistration);
        return this;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getFax() {
        return this.fax;
    }

    public AuditCompanyInfo fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFloor() {
        return this.floor;
    }

    public AuditCompanyInfo floor(String floor) {
        this.setFloor(floor);
        return this;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getInernationalobserviation() {
        return this.inernationalobserviation;
    }

    public AuditCompanyInfo inernationalobserviation(String inernationalobserviation) {
        this.setInernationalobserviation(inernationalobserviation);
        return this;
    }

    public void setInernationalobserviation(String inernationalobserviation) {
        this.inernationalobserviation = inernationalobserviation;
    }

    public String getMainStreet() {
        return this.mainStreet;
    }

    public AuditCompanyInfo mainStreet(String mainStreet) {
        this.setMainStreet(mainStreet);
        return this;
    }

    public void setMainStreet(String mainStreet) {
        this.mainStreet = mainStreet;
    }

    public String getMobile() {
        return this.mobile;
    }

    public AuditCompanyInfo mobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPlaque() {
        return this.plaque;
    }

    public AuditCompanyInfo plaque(String plaque) {
        this.setPlaque(plaque);
        return this;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public AuditCompanyInfo postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public AuditCompanyInfo registrationNumber(String registrationNumber) {
        this.setRegistrationNumber(registrationNumber);
        return this;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public AuditCompanyInfo telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getTempId() {
        return this.tempId;
    }

    public AuditCompanyInfo tempId(Long tempId) {
        this.setTempId(tempId);
        return this;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public String getTheSideStreet() {
        return this.theSideStreet;
    }

    public AuditCompanyInfo theSideStreet(String theSideStreet) {
        this.setTheSideStreet(theSideStreet);
        return this;
    }

    public void setTheSideStreet(String theSideStreet) {
        this.theSideStreet = theSideStreet;
    }

    public String getTitle() {
        return this.title;
    }

    public AuditCompanyInfo title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return this.unit;
    }

    public AuditCompanyInfo unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCity() {
        return this.city;
    }

    public AuditCompanyInfo city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Draft getAuditCompanyInfo() {
        return this.auditCompanyInfo;
    }

    public void setAuditCompanyInfo(Draft draft) {
        this.auditCompanyInfo = draft;
    }

    public AuditCompanyInfo auditCompanyInfo(Draft draft) {
        this.setAuditCompanyInfo(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuditCompanyInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((AuditCompanyInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AuditCompanyInfo{" +
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
            ", city='" + getCity() + "'" +
            "}";
    }
}
