package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.LicenceInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LicenceInfoDTO implements Serializable {

    private Long id;

    private String organizationLicence;

    private String licenceNumber;

    private String licenceDate;

    private Boolean havingProduct;

    private Boolean havingService;

    private String creditDate;

    private ProductDTO product;

    private OrderRegServDTO orderRegServ;

    private OrderRegistrationInfoDTO orderRegistrationInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationLicence() {
        return organizationLicence;
    }

    public void setOrganizationLicence(String organizationLicence) {
        this.organizationLicence = organizationLicence;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getLicenceDate() {
        return licenceDate;
    }

    public void setLicenceDate(String licenceDate) {
        this.licenceDate = licenceDate;
    }

    public Boolean getHavingProduct() {
        return havingProduct;
    }

    public void setHavingProduct(Boolean havingProduct) {
        this.havingProduct = havingProduct;
    }

    public Boolean getHavingService() {
        return havingService;
    }

    public void setHavingService(Boolean havingService) {
        this.havingService = havingService;
    }

    public String getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public OrderRegServDTO getOrderRegServ() {
        return orderRegServ;
    }

    public void setOrderRegServ(OrderRegServDTO orderRegServ) {
        this.orderRegServ = orderRegServ;
    }

    public OrderRegistrationInfoDTO getOrderRegistrationInfo() {
        return orderRegistrationInfo;
    }

    public void setOrderRegistrationInfo(OrderRegistrationInfoDTO orderRegistrationInfo) {
        this.orderRegistrationInfo = orderRegistrationInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LicenceInfoDTO)) {
            return false;
        }

        LicenceInfoDTO licenceInfoDTO = (LicenceInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, licenceInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LicenceInfoDTO{" +
            "id=" + getId() +
            ", organizationLicence='" + getOrganizationLicence() + "'" +
            ", licenceNumber='" + getLicenceNumber() + "'" +
            ", licenceDate='" + getLicenceDate() + "'" +
            ", havingProduct='" + getHavingProduct() + "'" +
            ", havingService='" + getHavingService() + "'" +
            ", creditDate='" + getCreditDate() + "'" +
            ", product=" + getProduct() +
            ", orderRegServ=" + getOrderRegServ() +
            ", orderRegistrationInfo=" + getOrderRegistrationInfo() +
            "}";
    }
}
