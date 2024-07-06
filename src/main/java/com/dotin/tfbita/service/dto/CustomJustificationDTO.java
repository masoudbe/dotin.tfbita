package com.dotin.tfbita.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.CustomJustification} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomJustificationDTO implements Serializable {

    private Long id;

    private Long agentId;

    private String agriculturalPublicPolicies;

    private String assessmentPlace;

    private String attachedDocuments;

    private BigDecimal balanceRate;

    private String bankCode;

    private String borderTransportType;

    private String boxCountType;

    private String boxMarks;

    private String cargoIndexNumber;

    private String centralBankCreditCode;

    private String comments;

    private String constructorCountryCode;

    private String costDetails;

    private BigDecimal cottageNumber;

    private BigDecimal creditEquivalentAmount;

    private String currency;

    private BigDecimal currencyAmount;

    private BigDecimal currencyRate;

    private String currencySwiftCode;

    private String customCompanyCode;

    private Long customerId;

    private String date;

    private String destCountryCode;

    private String destCustom;

    private String destCustomCode;

    private Boolean disciplinaryDocumentsIssued;

    private BigDecimal discountPercent;

    private String eightDigitOrderCode;

    private Long entranceCustomCompany;

    private Long entranceCustomCompanyId;

    private String evacuationPlace;

    private String evaluationMethodCode;

    private String exportDate;

    private String exporter;

    private String exporterCountryCode;

    private String extensionDate;

    private String factorTotalAmount;

    private String freightIndexNumber;

    private String frightLetter;

    private String importLicence;

    private String importLicense;

    private BigDecimal impureWeight;

    private String indices;

    private String internalTransportType;

    private String issuanceDate;

    private String itemNumber;

    private Integer items;

    private String justificationAgent;

    private BigDecimal justificationCurrencyRate;

    private String licenceNumber;

    private String makeCertificateNumber;

    private Long newBorderTransportType;

    private Long newEvacuationPlace;

    private Long newInternalTransportType;

    private BigDecimal newProductItemCustomValue;

    private String orderRegistrationDate;

    private String orderRegistrationNumber;

    private Integer papers;

    private String paymentConditions;

    private String preferences;

    private String procedure;

    private Integer productBoxCount;

    private String productCode;

    private BigDecimal productItemCost;

    private String productItemCount;

    private String productItemCustomValue;

    private String productItemValue;

    private String productMeasure;

    private String productOwner;

    private String productReleaseDate;

    private String productType;

    private BigDecimal productWorth;

    private BigDecimal profitRate;

    private BigDecimal pureWeight;

    private String quota;

    private String receiver;

    private Integer referenceNumber;

    private String registrationNumber;

    private String reverseOfJustificationDisciplinaryDocumentNumber;

    private Boolean reverseOfJustificationDocumentsIssued;

    private BigDecimal rightsRate;

    private String tradingCountryCode;

    private String transactionTypeCode;

    private String warehouseFactorNumber;

    private String constructorCountryName;

    private String lastCountryName;

    private String branchCode;

    private String justificationCurrencyCode;

    private String creditCurrencyCode;

    private Boolean isMigrational;

    @Lob
    private byte[] originalLetterImage;

    private String originalLetterImageContentType;

    @Lob
    private byte[] letterImage;

    private String letterImageContentType;

    private String sourceCountryCode;

    private CategoryElementDTO vehicleEnterNationality;

    private CategoryElementDTO container;

    private CategoryElementDTO vehicleCrossNationality;

    private CustomDTO exportCustom;

    private CustomDTO entranceCustom;

    private TransportationTypeDTO transportConditions;

    private TradeTypeCodeDTO tradeTypeCode;

    private PaymentConditionDTO newPaymentConditions;

    private JustificationDeductionAmountDTO justificationDeductionAmount;

    private Set<ProductDTO> products = new HashSet<>();

    private Set<DocumentTransactionDTO> reverseOfJustificationDocumentTransactions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgriculturalPublicPolicies() {
        return agriculturalPublicPolicies;
    }

    public void setAgriculturalPublicPolicies(String agriculturalPublicPolicies) {
        this.agriculturalPublicPolicies = agriculturalPublicPolicies;
    }

    public String getAssessmentPlace() {
        return assessmentPlace;
    }

    public void setAssessmentPlace(String assessmentPlace) {
        this.assessmentPlace = assessmentPlace;
    }

    public String getAttachedDocuments() {
        return attachedDocuments;
    }

    public void setAttachedDocuments(String attachedDocuments) {
        this.attachedDocuments = attachedDocuments;
    }

    public BigDecimal getBalanceRate() {
        return balanceRate;
    }

    public void setBalanceRate(BigDecimal balanceRate) {
        this.balanceRate = balanceRate;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBorderTransportType() {
        return borderTransportType;
    }

    public void setBorderTransportType(String borderTransportType) {
        this.borderTransportType = borderTransportType;
    }

    public String getBoxCountType() {
        return boxCountType;
    }

    public void setBoxCountType(String boxCountType) {
        this.boxCountType = boxCountType;
    }

    public String getBoxMarks() {
        return boxMarks;
    }

    public void setBoxMarks(String boxMarks) {
        this.boxMarks = boxMarks;
    }

    public String getCargoIndexNumber() {
        return cargoIndexNumber;
    }

    public void setCargoIndexNumber(String cargoIndexNumber) {
        this.cargoIndexNumber = cargoIndexNumber;
    }

    public String getCentralBankCreditCode() {
        return centralBankCreditCode;
    }

    public void setCentralBankCreditCode(String centralBankCreditCode) {
        this.centralBankCreditCode = centralBankCreditCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getConstructorCountryCode() {
        return constructorCountryCode;
    }

    public void setConstructorCountryCode(String constructorCountryCode) {
        this.constructorCountryCode = constructorCountryCode;
    }

    public String getCostDetails() {
        return costDetails;
    }

    public void setCostDetails(String costDetails) {
        this.costDetails = costDetails;
    }

    public BigDecimal getCottageNumber() {
        return cottageNumber;
    }

    public void setCottageNumber(BigDecimal cottageNumber) {
        this.cottageNumber = cottageNumber;
    }

    public BigDecimal getCreditEquivalentAmount() {
        return creditEquivalentAmount;
    }

    public void setCreditEquivalentAmount(BigDecimal creditEquivalentAmount) {
        this.creditEquivalentAmount = creditEquivalentAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getCurrencySwiftCode() {
        return currencySwiftCode;
    }

    public void setCurrencySwiftCode(String currencySwiftCode) {
        this.currencySwiftCode = currencySwiftCode;
    }

    public String getCustomCompanyCode() {
        return customCompanyCode;
    }

    public void setCustomCompanyCode(String customCompanyCode) {
        this.customCompanyCode = customCompanyCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDestCountryCode() {
        return destCountryCode;
    }

    public void setDestCountryCode(String destCountryCode) {
        this.destCountryCode = destCountryCode;
    }

    public String getDestCustom() {
        return destCustom;
    }

    public void setDestCustom(String destCustom) {
        this.destCustom = destCustom;
    }

    public String getDestCustomCode() {
        return destCustomCode;
    }

    public void setDestCustomCode(String destCustomCode) {
        this.destCustomCode = destCustomCode;
    }

    public Boolean getDisciplinaryDocumentsIssued() {
        return disciplinaryDocumentsIssued;
    }

    public void setDisciplinaryDocumentsIssued(Boolean disciplinaryDocumentsIssued) {
        this.disciplinaryDocumentsIssued = disciplinaryDocumentsIssued;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getEightDigitOrderCode() {
        return eightDigitOrderCode;
    }

    public void setEightDigitOrderCode(String eightDigitOrderCode) {
        this.eightDigitOrderCode = eightDigitOrderCode;
    }

    public Long getEntranceCustomCompany() {
        return entranceCustomCompany;
    }

    public void setEntranceCustomCompany(Long entranceCustomCompany) {
        this.entranceCustomCompany = entranceCustomCompany;
    }

    public Long getEntranceCustomCompanyId() {
        return entranceCustomCompanyId;
    }

    public void setEntranceCustomCompanyId(Long entranceCustomCompanyId) {
        this.entranceCustomCompanyId = entranceCustomCompanyId;
    }

    public String getEvacuationPlace() {
        return evacuationPlace;
    }

    public void setEvacuationPlace(String evacuationPlace) {
        this.evacuationPlace = evacuationPlace;
    }

    public String getEvaluationMethodCode() {
        return evaluationMethodCode;
    }

    public void setEvaluationMethodCode(String evaluationMethodCode) {
        this.evaluationMethodCode = evaluationMethodCode;
    }

    public String getExportDate() {
        return exportDate;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    public String getExporter() {
        return exporter;
    }

    public void setExporter(String exporter) {
        this.exporter = exporter;
    }

    public String getExporterCountryCode() {
        return exporterCountryCode;
    }

    public void setExporterCountryCode(String exporterCountryCode) {
        this.exporterCountryCode = exporterCountryCode;
    }

    public String getExtensionDate() {
        return extensionDate;
    }

    public void setExtensionDate(String extensionDate) {
        this.extensionDate = extensionDate;
    }

    public String getFactorTotalAmount() {
        return factorTotalAmount;
    }

    public void setFactorTotalAmount(String factorTotalAmount) {
        this.factorTotalAmount = factorTotalAmount;
    }

    public String getFreightIndexNumber() {
        return freightIndexNumber;
    }

    public void setFreightIndexNumber(String freightIndexNumber) {
        this.freightIndexNumber = freightIndexNumber;
    }

    public String getFrightLetter() {
        return frightLetter;
    }

    public void setFrightLetter(String frightLetter) {
        this.frightLetter = frightLetter;
    }

    public String getImportLicence() {
        return importLicence;
    }

    public void setImportLicence(String importLicence) {
        this.importLicence = importLicence;
    }

    public String getImportLicense() {
        return importLicense;
    }

    public void setImportLicense(String importLicense) {
        this.importLicense = importLicense;
    }

    public BigDecimal getImpureWeight() {
        return impureWeight;
    }

    public void setImpureWeight(BigDecimal impureWeight) {
        this.impureWeight = impureWeight;
    }

    public String getIndices() {
        return indices;
    }

    public void setIndices(String indices) {
        this.indices = indices;
    }

    public String getInternalTransportType() {
        return internalTransportType;
    }

    public void setInternalTransportType(String internalTransportType) {
        this.internalTransportType = internalTransportType;
    }

    public String getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(String issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public String getJustificationAgent() {
        return justificationAgent;
    }

    public void setJustificationAgent(String justificationAgent) {
        this.justificationAgent = justificationAgent;
    }

    public BigDecimal getJustificationCurrencyRate() {
        return justificationCurrencyRate;
    }

    public void setJustificationCurrencyRate(BigDecimal justificationCurrencyRate) {
        this.justificationCurrencyRate = justificationCurrencyRate;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getMakeCertificateNumber() {
        return makeCertificateNumber;
    }

    public void setMakeCertificateNumber(String makeCertificateNumber) {
        this.makeCertificateNumber = makeCertificateNumber;
    }

    public Long getNewBorderTransportType() {
        return newBorderTransportType;
    }

    public void setNewBorderTransportType(Long newBorderTransportType) {
        this.newBorderTransportType = newBorderTransportType;
    }

    public Long getNewEvacuationPlace() {
        return newEvacuationPlace;
    }

    public void setNewEvacuationPlace(Long newEvacuationPlace) {
        this.newEvacuationPlace = newEvacuationPlace;
    }

    public Long getNewInternalTransportType() {
        return newInternalTransportType;
    }

    public void setNewInternalTransportType(Long newInternalTransportType) {
        this.newInternalTransportType = newInternalTransportType;
    }

    public BigDecimal getNewProductItemCustomValue() {
        return newProductItemCustomValue;
    }

    public void setNewProductItemCustomValue(BigDecimal newProductItemCustomValue) {
        this.newProductItemCustomValue = newProductItemCustomValue;
    }

    public String getOrderRegistrationDate() {
        return orderRegistrationDate;
    }

    public void setOrderRegistrationDate(String orderRegistrationDate) {
        this.orderRegistrationDate = orderRegistrationDate;
    }

    public String getOrderRegistrationNumber() {
        return orderRegistrationNumber;
    }

    public void setOrderRegistrationNumber(String orderRegistrationNumber) {
        this.orderRegistrationNumber = orderRegistrationNumber;
    }

    public Integer getPapers() {
        return papers;
    }

    public void setPapers(Integer papers) {
        this.papers = papers;
    }

    public String getPaymentConditions() {
        return paymentConditions;
    }

    public void setPaymentConditions(String paymentConditions) {
        this.paymentConditions = paymentConditions;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public Integer getProductBoxCount() {
        return productBoxCount;
    }

    public void setProductBoxCount(Integer productBoxCount) {
        this.productBoxCount = productBoxCount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getProductItemCost() {
        return productItemCost;
    }

    public void setProductItemCost(BigDecimal productItemCost) {
        this.productItemCost = productItemCost;
    }

    public String getProductItemCount() {
        return productItemCount;
    }

    public void setProductItemCount(String productItemCount) {
        this.productItemCount = productItemCount;
    }

    public String getProductItemCustomValue() {
        return productItemCustomValue;
    }

    public void setProductItemCustomValue(String productItemCustomValue) {
        this.productItemCustomValue = productItemCustomValue;
    }

    public String getProductItemValue() {
        return productItemValue;
    }

    public void setProductItemValue(String productItemValue) {
        this.productItemValue = productItemValue;
    }

    public String getProductMeasure() {
        return productMeasure;
    }

    public void setProductMeasure(String productMeasure) {
        this.productMeasure = productMeasure;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getProductReleaseDate() {
        return productReleaseDate;
    }

    public void setProductReleaseDate(String productReleaseDate) {
        this.productReleaseDate = productReleaseDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductWorth() {
        return productWorth;
    }

    public void setProductWorth(BigDecimal productWorth) {
        this.productWorth = productWorth;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal getPureWeight() {
        return pureWeight;
    }

    public void setPureWeight(BigDecimal pureWeight) {
        this.pureWeight = pureWeight;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getReverseOfJustificationDisciplinaryDocumentNumber() {
        return reverseOfJustificationDisciplinaryDocumentNumber;
    }

    public void setReverseOfJustificationDisciplinaryDocumentNumber(String reverseOfJustificationDisciplinaryDocumentNumber) {
        this.reverseOfJustificationDisciplinaryDocumentNumber = reverseOfJustificationDisciplinaryDocumentNumber;
    }

    public Boolean getReverseOfJustificationDocumentsIssued() {
        return reverseOfJustificationDocumentsIssued;
    }

    public void setReverseOfJustificationDocumentsIssued(Boolean reverseOfJustificationDocumentsIssued) {
        this.reverseOfJustificationDocumentsIssued = reverseOfJustificationDocumentsIssued;
    }

    public BigDecimal getRightsRate() {
        return rightsRate;
    }

    public void setRightsRate(BigDecimal rightsRate) {
        this.rightsRate = rightsRate;
    }

    public String getTradingCountryCode() {
        return tradingCountryCode;
    }

    public void setTradingCountryCode(String tradingCountryCode) {
        this.tradingCountryCode = tradingCountryCode;
    }

    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    public void setTransactionTypeCode(String transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    public String getWarehouseFactorNumber() {
        return warehouseFactorNumber;
    }

    public void setWarehouseFactorNumber(String warehouseFactorNumber) {
        this.warehouseFactorNumber = warehouseFactorNumber;
    }

    public String getConstructorCountryName() {
        return constructorCountryName;
    }

    public void setConstructorCountryName(String constructorCountryName) {
        this.constructorCountryName = constructorCountryName;
    }

    public String getLastCountryName() {
        return lastCountryName;
    }

    public void setLastCountryName(String lastCountryName) {
        this.lastCountryName = lastCountryName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getJustificationCurrencyCode() {
        return justificationCurrencyCode;
    }

    public void setJustificationCurrencyCode(String justificationCurrencyCode) {
        this.justificationCurrencyCode = justificationCurrencyCode;
    }

    public String getCreditCurrencyCode() {
        return creditCurrencyCode;
    }

    public void setCreditCurrencyCode(String creditCurrencyCode) {
        this.creditCurrencyCode = creditCurrencyCode;
    }

    public Boolean getIsMigrational() {
        return isMigrational;
    }

    public void setIsMigrational(Boolean isMigrational) {
        this.isMigrational = isMigrational;
    }

    public byte[] getOriginalLetterImage() {
        return originalLetterImage;
    }

    public void setOriginalLetterImage(byte[] originalLetterImage) {
        this.originalLetterImage = originalLetterImage;
    }

    public String getOriginalLetterImageContentType() {
        return originalLetterImageContentType;
    }

    public void setOriginalLetterImageContentType(String originalLetterImageContentType) {
        this.originalLetterImageContentType = originalLetterImageContentType;
    }

    public byte[] getLetterImage() {
        return letterImage;
    }

    public void setLetterImage(byte[] letterImage) {
        this.letterImage = letterImage;
    }

    public String getLetterImageContentType() {
        return letterImageContentType;
    }

    public void setLetterImageContentType(String letterImageContentType) {
        this.letterImageContentType = letterImageContentType;
    }

    public String getSourceCountryCode() {
        return sourceCountryCode;
    }

    public void setSourceCountryCode(String sourceCountryCode) {
        this.sourceCountryCode = sourceCountryCode;
    }

    public CategoryElementDTO getVehicleEnterNationality() {
        return vehicleEnterNationality;
    }

    public void setVehicleEnterNationality(CategoryElementDTO vehicleEnterNationality) {
        this.vehicleEnterNationality = vehicleEnterNationality;
    }

    public CategoryElementDTO getContainer() {
        return container;
    }

    public void setContainer(CategoryElementDTO container) {
        this.container = container;
    }

    public CategoryElementDTO getVehicleCrossNationality() {
        return vehicleCrossNationality;
    }

    public void setVehicleCrossNationality(CategoryElementDTO vehicleCrossNationality) {
        this.vehicleCrossNationality = vehicleCrossNationality;
    }

    public CustomDTO getExportCustom() {
        return exportCustom;
    }

    public void setExportCustom(CustomDTO exportCustom) {
        this.exportCustom = exportCustom;
    }

    public CustomDTO getEntranceCustom() {
        return entranceCustom;
    }

    public void setEntranceCustom(CustomDTO entranceCustom) {
        this.entranceCustom = entranceCustom;
    }

    public TransportationTypeDTO getTransportConditions() {
        return transportConditions;
    }

    public void setTransportConditions(TransportationTypeDTO transportConditions) {
        this.transportConditions = transportConditions;
    }

    public TradeTypeCodeDTO getTradeTypeCode() {
        return tradeTypeCode;
    }

    public void setTradeTypeCode(TradeTypeCodeDTO tradeTypeCode) {
        this.tradeTypeCode = tradeTypeCode;
    }

    public PaymentConditionDTO getNewPaymentConditions() {
        return newPaymentConditions;
    }

    public void setNewPaymentConditions(PaymentConditionDTO newPaymentConditions) {
        this.newPaymentConditions = newPaymentConditions;
    }

    public JustificationDeductionAmountDTO getJustificationDeductionAmount() {
        return justificationDeductionAmount;
    }

    public void setJustificationDeductionAmount(JustificationDeductionAmountDTO justificationDeductionAmount) {
        this.justificationDeductionAmount = justificationDeductionAmount;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    public Set<DocumentTransactionDTO> getReverseOfJustificationDocumentTransactions() {
        return reverseOfJustificationDocumentTransactions;
    }

    public void setReverseOfJustificationDocumentTransactions(Set<DocumentTransactionDTO> reverseOfJustificationDocumentTransactions) {
        this.reverseOfJustificationDocumentTransactions = reverseOfJustificationDocumentTransactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomJustificationDTO)) {
            return false;
        }

        CustomJustificationDTO customJustificationDTO = (CustomJustificationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, customJustificationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomJustificationDTO{" +
            "id=" + getId() +
            ", agentId=" + getAgentId() +
            ", agriculturalPublicPolicies='" + getAgriculturalPublicPolicies() + "'" +
            ", assessmentPlace='" + getAssessmentPlace() + "'" +
            ", attachedDocuments='" + getAttachedDocuments() + "'" +
            ", balanceRate=" + getBalanceRate() +
            ", bankCode='" + getBankCode() + "'" +
            ", borderTransportType='" + getBorderTransportType() + "'" +
            ", boxCountType='" + getBoxCountType() + "'" +
            ", boxMarks='" + getBoxMarks() + "'" +
            ", cargoIndexNumber='" + getCargoIndexNumber() + "'" +
            ", centralBankCreditCode='" + getCentralBankCreditCode() + "'" +
            ", comments='" + getComments() + "'" +
            ", constructorCountryCode='" + getConstructorCountryCode() + "'" +
            ", costDetails='" + getCostDetails() + "'" +
            ", cottageNumber=" + getCottageNumber() +
            ", creditEquivalentAmount=" + getCreditEquivalentAmount() +
            ", currency='" + getCurrency() + "'" +
            ", currencyAmount=" + getCurrencyAmount() +
            ", currencyRate=" + getCurrencyRate() +
            ", currencySwiftCode='" + getCurrencySwiftCode() + "'" +
            ", customCompanyCode='" + getCustomCompanyCode() + "'" +
            ", customerId=" + getCustomerId() +
            ", date='" + getDate() + "'" +
            ", destCountryCode='" + getDestCountryCode() + "'" +
            ", destCustom='" + getDestCustom() + "'" +
            ", destCustomCode='" + getDestCustomCode() + "'" +
            ", disciplinaryDocumentsIssued='" + getDisciplinaryDocumentsIssued() + "'" +
            ", discountPercent=" + getDiscountPercent() +
            ", eightDigitOrderCode='" + getEightDigitOrderCode() + "'" +
            ", entranceCustomCompany=" + getEntranceCustomCompany() +
            ", entranceCustomCompanyId=" + getEntranceCustomCompanyId() +
            ", evacuationPlace='" + getEvacuationPlace() + "'" +
            ", evaluationMethodCode='" + getEvaluationMethodCode() + "'" +
            ", exportDate='" + getExportDate() + "'" +
            ", exporter='" + getExporter() + "'" +
            ", exporterCountryCode='" + getExporterCountryCode() + "'" +
            ", extensionDate='" + getExtensionDate() + "'" +
            ", factorTotalAmount='" + getFactorTotalAmount() + "'" +
            ", freightIndexNumber='" + getFreightIndexNumber() + "'" +
            ", frightLetter='" + getFrightLetter() + "'" +
            ", importLicence='" + getImportLicence() + "'" +
            ", importLicense='" + getImportLicense() + "'" +
            ", impureWeight=" + getImpureWeight() +
            ", indices='" + getIndices() + "'" +
            ", internalTransportType='" + getInternalTransportType() + "'" +
            ", issuanceDate='" + getIssuanceDate() + "'" +
            ", itemNumber='" + getItemNumber() + "'" +
            ", items=" + getItems() +
            ", justificationAgent='" + getJustificationAgent() + "'" +
            ", justificationCurrencyRate=" + getJustificationCurrencyRate() +
            ", licenceNumber='" + getLicenceNumber() + "'" +
            ", makeCertificateNumber='" + getMakeCertificateNumber() + "'" +
            ", newBorderTransportType=" + getNewBorderTransportType() +
            ", newEvacuationPlace=" + getNewEvacuationPlace() +
            ", newInternalTransportType=" + getNewInternalTransportType() +
            ", newProductItemCustomValue=" + getNewProductItemCustomValue() +
            ", orderRegistrationDate='" + getOrderRegistrationDate() + "'" +
            ", orderRegistrationNumber='" + getOrderRegistrationNumber() + "'" +
            ", papers=" + getPapers() +
            ", paymentConditions='" + getPaymentConditions() + "'" +
            ", preferences='" + getPreferences() + "'" +
            ", procedure='" + getProcedure() + "'" +
            ", productBoxCount=" + getProductBoxCount() +
            ", productCode='" + getProductCode() + "'" +
            ", productItemCost=" + getProductItemCost() +
            ", productItemCount='" + getProductItemCount() + "'" +
            ", productItemCustomValue='" + getProductItemCustomValue() + "'" +
            ", productItemValue='" + getProductItemValue() + "'" +
            ", productMeasure='" + getProductMeasure() + "'" +
            ", productOwner='" + getProductOwner() + "'" +
            ", productReleaseDate='" + getProductReleaseDate() + "'" +
            ", productType='" + getProductType() + "'" +
            ", productWorth=" + getProductWorth() +
            ", profitRate=" + getProfitRate() +
            ", pureWeight=" + getPureWeight() +
            ", quota='" + getQuota() + "'" +
            ", receiver='" + getReceiver() + "'" +
            ", referenceNumber=" + getReferenceNumber() +
            ", registrationNumber='" + getRegistrationNumber() + "'" +
            ", reverseOfJustificationDisciplinaryDocumentNumber='" + getReverseOfJustificationDisciplinaryDocumentNumber() + "'" +
            ", reverseOfJustificationDocumentsIssued='" + getReverseOfJustificationDocumentsIssued() + "'" +
            ", rightsRate=" + getRightsRate() +
            ", tradingCountryCode='" + getTradingCountryCode() + "'" +
            ", transactionTypeCode='" + getTransactionTypeCode() + "'" +
            ", warehouseFactorNumber='" + getWarehouseFactorNumber() + "'" +
            ", constructorCountryName='" + getConstructorCountryName() + "'" +
            ", lastCountryName='" + getLastCountryName() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            ", justificationCurrencyCode='" + getJustificationCurrencyCode() + "'" +
            ", creditCurrencyCode='" + getCreditCurrencyCode() + "'" +
            ", isMigrational='" + getIsMigrational() + "'" +
            ", originalLetterImage='" + getOriginalLetterImage() + "'" +
            ", letterImage='" + getLetterImage() + "'" +
            ", sourceCountryCode='" + getSourceCountryCode() + "'" +
            ", vehicleEnterNationality=" + getVehicleEnterNationality() +
            ", container=" + getContainer() +
            ", vehicleCrossNationality=" + getVehicleCrossNationality() +
            ", exportCustom=" + getExportCustom() +
            ", entranceCustom=" + getEntranceCustom() +
            ", transportConditions=" + getTransportConditions() +
            ", tradeTypeCode=" + getTradeTypeCode() +
            ", newPaymentConditions=" + getNewPaymentConditions() +
            ", justificationDeductionAmount=" + getJustificationDeductionAmount() +
            ", products=" + getProducts() +
            ", reverseOfJustificationDocumentTransactions=" + getReverseOfJustificationDocumentTransactions() +
            "}";
    }
}
