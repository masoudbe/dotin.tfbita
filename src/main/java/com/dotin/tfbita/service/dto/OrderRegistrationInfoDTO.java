package com.dotin.tfbita.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.OrderRegistrationInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderRegistrationInfoDTO implements Serializable {

    private Long id;

    private String orderRegNum;

    private String startOrderRegDate;

    private String endOrderRegDate;

    private String requestNumber;

    private String bankCode;

    private String branchCode;

    private String customerNumber;

    private String applicantNationalcode;

    private String performaNumber;

    private String performaDate;

    private String performaExpiryDate;

    private String performaDatePersian;

    private String performaExpiryDatePersian;

    private String infoSubmissionDate;

    private String sellerName;

    private String beneficiaryCountryCode;

    private String producerCountriesCode;

    private String sourceCountry;

    private Boolean multipleTransportable;

    private String deliveryTimeOfGoods;

    private Double totalWeightInKg;

    private Boolean possibilityCarrying;

    private Boolean possibilityClearance;

    private Boolean ableAddServiceInProductOrder;

    private Boolean freeZone;

    private String currencyCode;

    private BigDecimal fobAmount;

    private BigDecimal discount;

    private BigDecimal shipmentCost;

    private BigDecimal othrCost;

    private BigDecimal totalAmount;

    private Boolean isFile;

    private String fileNumber;

    private Boolean extended;

    private Boolean updated;

    private Boolean generateFromService;

    private String certificateNumber;

    private String centralBankCode;

    private Boolean isMigrational;

    private Long externalCustomer;

    private Long sumRedemptionDuration;

    private Long extendedDayNumber;

    private String mainGroupProductCode;

    private String producerCountries;

    @Lob
    private byte[] excelFile;

    private String excelFileContentType;

    private String commissionTransactionNumber;

    private CategoryElementDTO orderRegType;

    private CategoryElementDTO requestType;

    private CategoryElementDTO importType;

    private CategoryElementDTO operationType;

    private CategoryElementDTO currencyProvisionType;

    private CategoryElementDTO paymentTool;

    private CategoryElementDTO activityType;

    private CategoryElementDTO ownerType;

    private CategoryElementDTO status;

    private CategoryElementDTO externalCustomerType;

    private CategoryElementDTO transportVehicleType;

    private TransportationTypeDTO transportType;

    private CustomDTO destCoustomers;

    private CustomDTO cargoPlaceCustoms;

    private CustomDTO entranceBorders;

    private Set<ProductDTO> productInfos = new HashSet<>();

    private Set<StringValueDTO> commissionTransactionNumbers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderRegNum() {
        return orderRegNum;
    }

    public void setOrderRegNum(String orderRegNum) {
        this.orderRegNum = orderRegNum;
    }

    public String getStartOrderRegDate() {
        return startOrderRegDate;
    }

    public void setStartOrderRegDate(String startOrderRegDate) {
        this.startOrderRegDate = startOrderRegDate;
    }

    public String getEndOrderRegDate() {
        return endOrderRegDate;
    }

    public void setEndOrderRegDate(String endOrderRegDate) {
        this.endOrderRegDate = endOrderRegDate;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getApplicantNationalcode() {
        return applicantNationalcode;
    }

    public void setApplicantNationalcode(String applicantNationalcode) {
        this.applicantNationalcode = applicantNationalcode;
    }

    public String getPerformaNumber() {
        return performaNumber;
    }

    public void setPerformaNumber(String performaNumber) {
        this.performaNumber = performaNumber;
    }

    public String getPerformaDate() {
        return performaDate;
    }

    public void setPerformaDate(String performaDate) {
        this.performaDate = performaDate;
    }

    public String getPerformaExpiryDate() {
        return performaExpiryDate;
    }

    public void setPerformaExpiryDate(String performaExpiryDate) {
        this.performaExpiryDate = performaExpiryDate;
    }

    public String getPerformaDatePersian() {
        return performaDatePersian;
    }

    public void setPerformaDatePersian(String performaDatePersian) {
        this.performaDatePersian = performaDatePersian;
    }

    public String getPerformaExpiryDatePersian() {
        return performaExpiryDatePersian;
    }

    public void setPerformaExpiryDatePersian(String performaExpiryDatePersian) {
        this.performaExpiryDatePersian = performaExpiryDatePersian;
    }

    public String getInfoSubmissionDate() {
        return infoSubmissionDate;
    }

    public void setInfoSubmissionDate(String infoSubmissionDate) {
        this.infoSubmissionDate = infoSubmissionDate;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBeneficiaryCountryCode() {
        return beneficiaryCountryCode;
    }

    public void setBeneficiaryCountryCode(String beneficiaryCountryCode) {
        this.beneficiaryCountryCode = beneficiaryCountryCode;
    }

    public String getProducerCountriesCode() {
        return producerCountriesCode;
    }

    public void setProducerCountriesCode(String producerCountriesCode) {
        this.producerCountriesCode = producerCountriesCode;
    }

    public String getSourceCountry() {
        return sourceCountry;
    }

    public void setSourceCountry(String sourceCountry) {
        this.sourceCountry = sourceCountry;
    }

    public Boolean getMultipleTransportable() {
        return multipleTransportable;
    }

    public void setMultipleTransportable(Boolean multipleTransportable) {
        this.multipleTransportable = multipleTransportable;
    }

    public String getDeliveryTimeOfGoods() {
        return deliveryTimeOfGoods;
    }

    public void setDeliveryTimeOfGoods(String deliveryTimeOfGoods) {
        this.deliveryTimeOfGoods = deliveryTimeOfGoods;
    }

    public Double getTotalWeightInKg() {
        return totalWeightInKg;
    }

    public void setTotalWeightInKg(Double totalWeightInKg) {
        this.totalWeightInKg = totalWeightInKg;
    }

    public Boolean getPossibilityCarrying() {
        return possibilityCarrying;
    }

    public void setPossibilityCarrying(Boolean possibilityCarrying) {
        this.possibilityCarrying = possibilityCarrying;
    }

    public Boolean getPossibilityClearance() {
        return possibilityClearance;
    }

    public void setPossibilityClearance(Boolean possibilityClearance) {
        this.possibilityClearance = possibilityClearance;
    }

    public Boolean getAbleAddServiceInProductOrder() {
        return ableAddServiceInProductOrder;
    }

    public void setAbleAddServiceInProductOrder(Boolean ableAddServiceInProductOrder) {
        this.ableAddServiceInProductOrder = ableAddServiceInProductOrder;
    }

    public Boolean getFreeZone() {
        return freeZone;
    }

    public void setFreeZone(Boolean freeZone) {
        this.freeZone = freeZone;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getFobAmount() {
        return fobAmount;
    }

    public void setFobAmount(BigDecimal fobAmount) {
        this.fobAmount = fobAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getShipmentCost() {
        return shipmentCost;
    }

    public void setShipmentCost(BigDecimal shipmentCost) {
        this.shipmentCost = shipmentCost;
    }

    public BigDecimal getOthrCost() {
        return othrCost;
    }

    public void setOthrCost(BigDecimal othrCost) {
        this.othrCost = othrCost;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getIsFile() {
        return isFile;
    }

    public void setIsFile(Boolean isFile) {
        this.isFile = isFile;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Boolean getExtended() {
        return extended;
    }

    public void setExtended(Boolean extended) {
        this.extended = extended;
    }

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public Boolean getGenerateFromService() {
        return generateFromService;
    }

    public void setGenerateFromService(Boolean generateFromService) {
        this.generateFromService = generateFromService;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCentralBankCode() {
        return centralBankCode;
    }

    public void setCentralBankCode(String centralBankCode) {
        this.centralBankCode = centralBankCode;
    }

    public Boolean getIsMigrational() {
        return isMigrational;
    }

    public void setIsMigrational(Boolean isMigrational) {
        this.isMigrational = isMigrational;
    }

    public Long getExternalCustomer() {
        return externalCustomer;
    }

    public void setExternalCustomer(Long externalCustomer) {
        this.externalCustomer = externalCustomer;
    }

    public Long getSumRedemptionDuration() {
        return sumRedemptionDuration;
    }

    public void setSumRedemptionDuration(Long sumRedemptionDuration) {
        this.sumRedemptionDuration = sumRedemptionDuration;
    }

    public Long getExtendedDayNumber() {
        return extendedDayNumber;
    }

    public void setExtendedDayNumber(Long extendedDayNumber) {
        this.extendedDayNumber = extendedDayNumber;
    }

    public String getMainGroupProductCode() {
        return mainGroupProductCode;
    }

    public void setMainGroupProductCode(String mainGroupProductCode) {
        this.mainGroupProductCode = mainGroupProductCode;
    }

    public String getProducerCountries() {
        return producerCountries;
    }

    public void setProducerCountries(String producerCountries) {
        this.producerCountries = producerCountries;
    }

    public byte[] getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(byte[] excelFile) {
        this.excelFile = excelFile;
    }

    public String getExcelFileContentType() {
        return excelFileContentType;
    }

    public void setExcelFileContentType(String excelFileContentType) {
        this.excelFileContentType = excelFileContentType;
    }

    public String getCommissionTransactionNumber() {
        return commissionTransactionNumber;
    }

    public void setCommissionTransactionNumber(String commissionTransactionNumber) {
        this.commissionTransactionNumber = commissionTransactionNumber;
    }

    public CategoryElementDTO getOrderRegType() {
        return orderRegType;
    }

    public void setOrderRegType(CategoryElementDTO orderRegType) {
        this.orderRegType = orderRegType;
    }

    public CategoryElementDTO getRequestType() {
        return requestType;
    }

    public void setRequestType(CategoryElementDTO requestType) {
        this.requestType = requestType;
    }

    public CategoryElementDTO getImportType() {
        return importType;
    }

    public void setImportType(CategoryElementDTO importType) {
        this.importType = importType;
    }

    public CategoryElementDTO getOperationType() {
        return operationType;
    }

    public void setOperationType(CategoryElementDTO operationType) {
        this.operationType = operationType;
    }

    public CategoryElementDTO getCurrencyProvisionType() {
        return currencyProvisionType;
    }

    public void setCurrencyProvisionType(CategoryElementDTO currencyProvisionType) {
        this.currencyProvisionType = currencyProvisionType;
    }

    public CategoryElementDTO getPaymentTool() {
        return paymentTool;
    }

    public void setPaymentTool(CategoryElementDTO paymentTool) {
        this.paymentTool = paymentTool;
    }

    public CategoryElementDTO getActivityType() {
        return activityType;
    }

    public void setActivityType(CategoryElementDTO activityType) {
        this.activityType = activityType;
    }

    public CategoryElementDTO getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(CategoryElementDTO ownerType) {
        this.ownerType = ownerType;
    }

    public CategoryElementDTO getStatus() {
        return status;
    }

    public void setStatus(CategoryElementDTO status) {
        this.status = status;
    }

    public CategoryElementDTO getExternalCustomerType() {
        return externalCustomerType;
    }

    public void setExternalCustomerType(CategoryElementDTO externalCustomerType) {
        this.externalCustomerType = externalCustomerType;
    }

    public CategoryElementDTO getTransportVehicleType() {
        return transportVehicleType;
    }

    public void setTransportVehicleType(CategoryElementDTO transportVehicleType) {
        this.transportVehicleType = transportVehicleType;
    }

    public TransportationTypeDTO getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportationTypeDTO transportType) {
        this.transportType = transportType;
    }

    public CustomDTO getDestCoustomers() {
        return destCoustomers;
    }

    public void setDestCoustomers(CustomDTO destCoustomers) {
        this.destCoustomers = destCoustomers;
    }

    public CustomDTO getCargoPlaceCustoms() {
        return cargoPlaceCustoms;
    }

    public void setCargoPlaceCustoms(CustomDTO cargoPlaceCustoms) {
        this.cargoPlaceCustoms = cargoPlaceCustoms;
    }

    public CustomDTO getEntranceBorders() {
        return entranceBorders;
    }

    public void setEntranceBorders(CustomDTO entranceBorders) {
        this.entranceBorders = entranceBorders;
    }

    public Set<ProductDTO> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(Set<ProductDTO> productInfos) {
        this.productInfos = productInfos;
    }

    public Set<StringValueDTO> getCommissionTransactionNumbers() {
        return commissionTransactionNumbers;
    }

    public void setCommissionTransactionNumbers(Set<StringValueDTO> commissionTransactionNumbers) {
        this.commissionTransactionNumbers = commissionTransactionNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderRegistrationInfoDTO)) {
            return false;
        }

        OrderRegistrationInfoDTO orderRegistrationInfoDTO = (OrderRegistrationInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderRegistrationInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderRegistrationInfoDTO{" +
            "id=" + getId() +
            ", orderRegNum='" + getOrderRegNum() + "'" +
            ", startOrderRegDate='" + getStartOrderRegDate() + "'" +
            ", endOrderRegDate='" + getEndOrderRegDate() + "'" +
            ", requestNumber='" + getRequestNumber() + "'" +
            ", bankCode='" + getBankCode() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            ", customerNumber='" + getCustomerNumber() + "'" +
            ", applicantNationalcode='" + getApplicantNationalcode() + "'" +
            ", performaNumber='" + getPerformaNumber() + "'" +
            ", performaDate='" + getPerformaDate() + "'" +
            ", performaExpiryDate='" + getPerformaExpiryDate() + "'" +
            ", performaDatePersian='" + getPerformaDatePersian() + "'" +
            ", performaExpiryDatePersian='" + getPerformaExpiryDatePersian() + "'" +
            ", infoSubmissionDate='" + getInfoSubmissionDate() + "'" +
            ", sellerName='" + getSellerName() + "'" +
            ", beneficiaryCountryCode='" + getBeneficiaryCountryCode() + "'" +
            ", producerCountriesCode='" + getProducerCountriesCode() + "'" +
            ", sourceCountry='" + getSourceCountry() + "'" +
            ", multipleTransportable='" + getMultipleTransportable() + "'" +
            ", deliveryTimeOfGoods='" + getDeliveryTimeOfGoods() + "'" +
            ", totalWeightInKg=" + getTotalWeightInKg() +
            ", possibilityCarrying='" + getPossibilityCarrying() + "'" +
            ", possibilityClearance='" + getPossibilityClearance() + "'" +
            ", ableAddServiceInProductOrder='" + getAbleAddServiceInProductOrder() + "'" +
            ", freeZone='" + getFreeZone() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", fobAmount=" + getFobAmount() +
            ", discount=" + getDiscount() +
            ", shipmentCost=" + getShipmentCost() +
            ", othrCost=" + getOthrCost() +
            ", totalAmount=" + getTotalAmount() +
            ", isFile='" + getIsFile() + "'" +
            ", fileNumber='" + getFileNumber() + "'" +
            ", extended='" + getExtended() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", generateFromService='" + getGenerateFromService() + "'" +
            ", certificateNumber='" + getCertificateNumber() + "'" +
            ", centralBankCode='" + getCentralBankCode() + "'" +
            ", isMigrational='" + getIsMigrational() + "'" +
            ", externalCustomer=" + getExternalCustomer() +
            ", sumRedemptionDuration=" + getSumRedemptionDuration() +
            ", extendedDayNumber=" + getExtendedDayNumber() +
            ", mainGroupProductCode='" + getMainGroupProductCode() + "'" +
            ", producerCountries='" + getProducerCountries() + "'" +
            ", excelFile='" + getExcelFile() + "'" +
            ", commissionTransactionNumber='" + getCommissionTransactionNumber() + "'" +
            ", orderRegType=" + getOrderRegType() +
            ", requestType=" + getRequestType() +
            ", importType=" + getImportType() +
            ", operationType=" + getOperationType() +
            ", currencyProvisionType=" + getCurrencyProvisionType() +
            ", paymentTool=" + getPaymentTool() +
            ", activityType=" + getActivityType() +
            ", ownerType=" + getOwnerType() +
            ", status=" + getStatus() +
            ", externalCustomerType=" + getExternalCustomerType() +
            ", transportVehicleType=" + getTransportVehicleType() +
            ", transportType=" + getTransportType() +
            ", destCoustomers=" + getDestCoustomers() +
            ", cargoPlaceCustoms=" + getCargoPlaceCustoms() +
            ", entranceBorders=" + getEntranceBorders() +
            ", productInfos=" + getProductInfos() +
            ", commissionTransactionNumbers=" + getCommissionTransactionNumbers() +
            "}";
    }
}
