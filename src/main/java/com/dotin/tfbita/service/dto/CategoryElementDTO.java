package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.CategoryElement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryElementDTO implements Serializable {

    private Long id;

    private String value;

    private String categoryName;

    private String code;

    private OrderRegistrationInfoDTO orderRegType;

    private OrderRegistrationInfoDTO requestType;

    private OrderRegistrationInfoDTO importType;

    private OrderRegistrationInfoDTO operationTyp;

    private OrderRegistrationInfoDTO currencyProvisionType;

    private OrderRegistrationInfoDTO paymentTool;

    private OrderRegistrationInfoDTO activityType;

    private OrderRegistrationInfoDTO ownerType;

    private OrderRegistrationInfoDTO status;

    private OrderRegistrationInfoDTO externalCustomerType;

    private OrderRegistrationInfoDTO transportType;

    private PurchaseFromOtherResourcesDTO currencySupplier;

    private PurchaseFromOtherResourcesDTO statusPurchase;

    private OrderRegistrationInfoDTO transportVehicleType;

    private DraftDTO freightLetterType;

    private DraftDTO actionCode;

    private DraftDTO ownershipCode;

    private DraftDTO currencyContainerPlace;

    private DraftDTO draftSource;

    private DraftDTO chargedExchangeBroker;

    private DraftDTO impartType;

    private DraftDTO insuranceLetterType;

    private DraftDTO advisorDepositType;

    private DraftDTO interfaceAdvisorDepositType;

    private DraftDTO paymentType;

    private DraftDTO dealType;

    private DraftDTO coveringAdvisorDepositType;

    private AdvisorDefinitionDTO depositType;

    private DraftTypeDTO type;

    private DraftTypeDTO secondaryType;

    private DraftTypeDTO division;

    private DraftReceiptDTO productDimension;

    private DraftReceiptDTO stateOfDocuments;

    private DraftReceiptDTO currencyProvisionFileType;

    private DraftStatusInfoDTO statusDraft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderRegistrationInfoDTO getOrderRegType() {
        return orderRegType;
    }

    public void setOrderRegType(OrderRegistrationInfoDTO orderRegType) {
        this.orderRegType = orderRegType;
    }

    public OrderRegistrationInfoDTO getRequestType() {
        return requestType;
    }

    public void setRequestType(OrderRegistrationInfoDTO requestType) {
        this.requestType = requestType;
    }

    public OrderRegistrationInfoDTO getImportType() {
        return importType;
    }

    public void setImportType(OrderRegistrationInfoDTO importType) {
        this.importType = importType;
    }

    public OrderRegistrationInfoDTO getOperationTyp() {
        return operationTyp;
    }

    public void setOperationTyp(OrderRegistrationInfoDTO operationTyp) {
        this.operationTyp = operationTyp;
    }

    public OrderRegistrationInfoDTO getCurrencyProvisionType() {
        return currencyProvisionType;
    }

    public void setCurrencyProvisionType(OrderRegistrationInfoDTO currencyProvisionType) {
        this.currencyProvisionType = currencyProvisionType;
    }

    public OrderRegistrationInfoDTO getPaymentTool() {
        return paymentTool;
    }

    public void setPaymentTool(OrderRegistrationInfoDTO paymentTool) {
        this.paymentTool = paymentTool;
    }

    public OrderRegistrationInfoDTO getActivityType() {
        return activityType;
    }

    public void setActivityType(OrderRegistrationInfoDTO activityType) {
        this.activityType = activityType;
    }

    public OrderRegistrationInfoDTO getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OrderRegistrationInfoDTO ownerType) {
        this.ownerType = ownerType;
    }

    public OrderRegistrationInfoDTO getStatus() {
        return status;
    }

    public void setStatus(OrderRegistrationInfoDTO status) {
        this.status = status;
    }

    public OrderRegistrationInfoDTO getExternalCustomerType() {
        return externalCustomerType;
    }

    public void setExternalCustomerType(OrderRegistrationInfoDTO externalCustomerType) {
        this.externalCustomerType = externalCustomerType;
    }

    public OrderRegistrationInfoDTO getTransportType() {
        return transportType;
    }

    public void setTransportType(OrderRegistrationInfoDTO transportType) {
        this.transportType = transportType;
    }

    public PurchaseFromOtherResourcesDTO getCurrencySupplier() {
        return currencySupplier;
    }

    public void setCurrencySupplier(PurchaseFromOtherResourcesDTO currencySupplier) {
        this.currencySupplier = currencySupplier;
    }

    public PurchaseFromOtherResourcesDTO getStatusPurchase() {
        return statusPurchase;
    }

    public void setStatusPurchase(PurchaseFromOtherResourcesDTO statusPurchase) {
        this.statusPurchase = statusPurchase;
    }

    public OrderRegistrationInfoDTO getTransportVehicleType() {
        return transportVehicleType;
    }

    public void setTransportVehicleType(OrderRegistrationInfoDTO transportVehicleType) {
        this.transportVehicleType = transportVehicleType;
    }

    public DraftDTO getFreightLetterType() {
        return freightLetterType;
    }

    public void setFreightLetterType(DraftDTO freightLetterType) {
        this.freightLetterType = freightLetterType;
    }

    public DraftDTO getActionCode() {
        return actionCode;
    }

    public void setActionCode(DraftDTO actionCode) {
        this.actionCode = actionCode;
    }

    public DraftDTO getOwnershipCode() {
        return ownershipCode;
    }

    public void setOwnershipCode(DraftDTO ownershipCode) {
        this.ownershipCode = ownershipCode;
    }

    public DraftDTO getCurrencyContainerPlace() {
        return currencyContainerPlace;
    }

    public void setCurrencyContainerPlace(DraftDTO currencyContainerPlace) {
        this.currencyContainerPlace = currencyContainerPlace;
    }

    public DraftDTO getDraftSource() {
        return draftSource;
    }

    public void setDraftSource(DraftDTO draftSource) {
        this.draftSource = draftSource;
    }

    public DraftDTO getChargedExchangeBroker() {
        return chargedExchangeBroker;
    }

    public void setChargedExchangeBroker(DraftDTO chargedExchangeBroker) {
        this.chargedExchangeBroker = chargedExchangeBroker;
    }

    public DraftDTO getImpartType() {
        return impartType;
    }

    public void setImpartType(DraftDTO impartType) {
        this.impartType = impartType;
    }

    public DraftDTO getInsuranceLetterType() {
        return insuranceLetterType;
    }

    public void setInsuranceLetterType(DraftDTO insuranceLetterType) {
        this.insuranceLetterType = insuranceLetterType;
    }

    public DraftDTO getAdvisorDepositType() {
        return advisorDepositType;
    }

    public void setAdvisorDepositType(DraftDTO advisorDepositType) {
        this.advisorDepositType = advisorDepositType;
    }

    public DraftDTO getInterfaceAdvisorDepositType() {
        return interfaceAdvisorDepositType;
    }

    public void setInterfaceAdvisorDepositType(DraftDTO interfaceAdvisorDepositType) {
        this.interfaceAdvisorDepositType = interfaceAdvisorDepositType;
    }

    public DraftDTO getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(DraftDTO paymentType) {
        this.paymentType = paymentType;
    }

    public DraftDTO getDealType() {
        return dealType;
    }

    public void setDealType(DraftDTO dealType) {
        this.dealType = dealType;
    }

    public DraftDTO getCoveringAdvisorDepositType() {
        return coveringAdvisorDepositType;
    }

    public void setCoveringAdvisorDepositType(DraftDTO coveringAdvisorDepositType) {
        this.coveringAdvisorDepositType = coveringAdvisorDepositType;
    }

    public AdvisorDefinitionDTO getDepositType() {
        return depositType;
    }

    public void setDepositType(AdvisorDefinitionDTO depositType) {
        this.depositType = depositType;
    }

    public DraftTypeDTO getType() {
        return type;
    }

    public void setType(DraftTypeDTO type) {
        this.type = type;
    }

    public DraftTypeDTO getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(DraftTypeDTO secondaryType) {
        this.secondaryType = secondaryType;
    }

    public DraftTypeDTO getDivision() {
        return division;
    }

    public void setDivision(DraftTypeDTO division) {
        this.division = division;
    }

    public DraftReceiptDTO getProductDimension() {
        return productDimension;
    }

    public void setProductDimension(DraftReceiptDTO productDimension) {
        this.productDimension = productDimension;
    }

    public DraftReceiptDTO getStateOfDocuments() {
        return stateOfDocuments;
    }

    public void setStateOfDocuments(DraftReceiptDTO stateOfDocuments) {
        this.stateOfDocuments = stateOfDocuments;
    }

    public DraftReceiptDTO getCurrencyProvisionFileType() {
        return currencyProvisionFileType;
    }

    public void setCurrencyProvisionFileType(DraftReceiptDTO currencyProvisionFileType) {
        this.currencyProvisionFileType = currencyProvisionFileType;
    }

    public DraftStatusInfoDTO getStatusDraft() {
        return statusDraft;
    }

    public void setStatusDraft(DraftStatusInfoDTO statusDraft) {
        this.statusDraft = statusDraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryElementDTO)) {
            return false;
        }

        CategoryElementDTO categoryElementDTO = (CategoryElementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, categoryElementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryElementDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", code='" + getCode() + "'" +
            ", orderRegType=" + getOrderRegType() +
            ", requestType=" + getRequestType() +
            ", importType=" + getImportType() +
            ", operationTyp=" + getOperationTyp() +
            ", currencyProvisionType=" + getCurrencyProvisionType() +
            ", paymentTool=" + getPaymentTool() +
            ", activityType=" + getActivityType() +
            ", ownerType=" + getOwnerType() +
            ", status=" + getStatus() +
            ", externalCustomerType=" + getExternalCustomerType() +
            ", transportType=" + getTransportType() +
            ", currencySupplier=" + getCurrencySupplier() +
            ", statusPurchase=" + getStatusPurchase() +
            ", transportVehicleType=" + getTransportVehicleType() +
            ", freightLetterType=" + getFreightLetterType() +
            ", actionCode=" + getActionCode() +
            ", ownershipCode=" + getOwnershipCode() +
            ", currencyContainerPlace=" + getCurrencyContainerPlace() +
            ", draftSource=" + getDraftSource() +
            ", chargedExchangeBroker=" + getChargedExchangeBroker() +
            ", impartType=" + getImpartType() +
            ", insuranceLetterType=" + getInsuranceLetterType() +
            ", advisorDepositType=" + getAdvisorDepositType() +
            ", interfaceAdvisorDepositType=" + getInterfaceAdvisorDepositType() +
            ", paymentType=" + getPaymentType() +
            ", dealType=" + getDealType() +
            ", coveringAdvisorDepositType=" + getCoveringAdvisorDepositType() +
            ", depositType=" + getDepositType() +
            ", type=" + getType() +
            ", secondaryType=" + getSecondaryType() +
            ", division=" + getDivision() +
            ", productDimension=" + getProductDimension() +
            ", stateOfDocuments=" + getStateOfDocuments() +
            ", currencyProvisionFileType=" + getCurrencyProvisionFileType() +
            ", statusDraft=" + getStatusDraft() +
            "}";
    }
}
