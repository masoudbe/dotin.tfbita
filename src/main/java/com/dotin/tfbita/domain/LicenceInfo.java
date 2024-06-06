package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A LicenceInfo.
 */
@Entity
@Table(name = "licence_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LicenceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "organization_licence")
    private String organizationLicence;

    @Column(name = "licence_number")
    private String licenceNumber;

    @Column(name = "licence_date")
    private String licenceDate;

    @Column(name = "having_product")
    private Boolean havingProduct;

    @Column(name = "having_service")
    private Boolean havingService;

    @Column(name = "credit_date")
    private String creditDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "orderRegistrationInfos" }, allowSetters = true)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "orderRegistrationInfo", "licenceInfos" }, allowSetters = true)
    private OrderRegServ orderRegServ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "licenceInfos", "orderRegServs", "purchaseFromOtherResources", "customs", "productInfos" },
        allowSetters = true
    )
    private OrderRegistrationInfo orderRegistrationInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LicenceInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationLicence() {
        return this.organizationLicence;
    }

    public LicenceInfo organizationLicence(String organizationLicence) {
        this.setOrganizationLicence(organizationLicence);
        return this;
    }

    public void setOrganizationLicence(String organizationLicence) {
        this.organizationLicence = organizationLicence;
    }

    public String getLicenceNumber() {
        return this.licenceNumber;
    }

    public LicenceInfo licenceNumber(String licenceNumber) {
        this.setLicenceNumber(licenceNumber);
        return this;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getLicenceDate() {
        return this.licenceDate;
    }

    public LicenceInfo licenceDate(String licenceDate) {
        this.setLicenceDate(licenceDate);
        return this;
    }

    public void setLicenceDate(String licenceDate) {
        this.licenceDate = licenceDate;
    }

    public Boolean getHavingProduct() {
        return this.havingProduct;
    }

    public LicenceInfo havingProduct(Boolean havingProduct) {
        this.setHavingProduct(havingProduct);
        return this;
    }

    public void setHavingProduct(Boolean havingProduct) {
        this.havingProduct = havingProduct;
    }

    public Boolean getHavingService() {
        return this.havingService;
    }

    public LicenceInfo havingService(Boolean havingService) {
        this.setHavingService(havingService);
        return this;
    }

    public void setHavingService(Boolean havingService) {
        this.havingService = havingService;
    }

    public String getCreditDate() {
        return this.creditDate;
    }

    public LicenceInfo creditDate(String creditDate) {
        this.setCreditDate(creditDate);
        return this;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LicenceInfo product(Product product) {
        this.setProduct(product);
        return this;
    }

    public OrderRegServ getOrderRegServ() {
        return this.orderRegServ;
    }

    public void setOrderRegServ(OrderRegServ orderRegServ) {
        this.orderRegServ = orderRegServ;
    }

    public LicenceInfo orderRegServ(OrderRegServ orderRegServ) {
        this.setOrderRegServ(orderRegServ);
        return this;
    }

    public OrderRegistrationInfo getOrderRegistrationInfo() {
        return this.orderRegistrationInfo;
    }

    public void setOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfo = orderRegistrationInfo;
    }

    public LicenceInfo orderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.setOrderRegistrationInfo(orderRegistrationInfo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LicenceInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((LicenceInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LicenceInfo{" +
            "id=" + getId() +
            ", organizationLicence='" + getOrganizationLicence() + "'" +
            ", licenceNumber='" + getLicenceNumber() + "'" +
            ", licenceDate='" + getLicenceDate() + "'" +
            ", havingProduct='" + getHavingProduct() + "'" +
            ", havingService='" + getHavingService() + "'" +
            ", creditDate='" + getCreditDate() + "'" +
            "}";
    }
}
