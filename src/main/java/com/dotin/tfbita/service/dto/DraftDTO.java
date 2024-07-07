package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.Draft} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftDTO implements Serializable {

    private Long id;

    private String advisorDepositNumber;

    private Boolean advisorRequestDeleted;

    private BigDecimal auditCost;

    private String beneficiaryInfo;

    private BigDecimal branchStampCost;

    private String centralBankCode;

    private String centralBankCodeReference;

    private BigDecimal chargedExchangeBrokerAmount;

    private Boolean charterAcceptable;

    private String comment;

    private String completionDate;

    private String coveringBankDepositNumber;

    private String currencyExchangeDepositNumber;

    private String customerDepositNumber;

    private Integer deliverDuration;

    private BigDecimal discount;

    private String draftNumber;

    private BigDecimal draftOrderRegProductWorth;

    private BigDecimal draftOrderRegServiceWorth;

    private BigDecimal draftOrderRegTotalWorth;

    private BigDecimal draftOtherCost;

    private Boolean hasTransportJustification;

    private BigDecimal insuranceCost;

    private String insuranceDate;

    private String insuranceExpiryDate;

    private String insuranceNumber;

    private String interfaceAdvisorDepositNumber;

    private String issueDate;

    private BigDecimal issueDraftCommission;

    private String lastShipmentDate;

    private Long mainCustomerNumber;

    private Boolean makeCertification;

    private Boolean multipleTransportable;

    private String orderRegistrationDate;

    private String orderRegistrationExpiryDate;

    private String orderRegistrationNumber;

    private BigDecimal otherCost;

    private BigDecimal paymentAmount;

    private String performaDate;

    private String performaExpiryDate;

    private String performaNumber;

    private BigDecimal postSwiftCost;

    private Boolean productSourceChangeable;

    private Boolean receiveAllCommission;

    private BigDecimal registerationJustificationAmount;

    private String requestDate;

    private String sanctionSerial;

    private Long serial;

    private BigDecimal shipmentCost;

    private String sourceTransportPlace;

    private String swiftComment;

    private BigDecimal transferAmount;

    private Boolean transportVehicleChangeable;

    private String paymentTool;

    private String letterNumberTazirat;

    private String letterDateTazirat;

    private String loanNumber;

    private Boolean isMigrational;

    private Boolean isNewCertificate;

    private Boolean isWithoutPayment;

    private String mainAccountCurrencyCode;

    private String orderRegCurrencyCode;

    private String chargedExchangeBrokerCurrencyCode;

    private String destinationCountryCode;

    private String beneficiaryCountryCode;

    private String producerCountryCode;

    private String registerationJustificationCurrencyCode;

    private String branchCode;

    private String operationalBranchCode;

    private String certificateSenderBranchCode;

    private CategoryElementDTO chargedExchangeBroker;

    private CategoryElementDTO insuranceLetterType;

    private CategoryElementDTO advisorDepositType;

    private CategoryElementDTO interfaceAdvisorDepositType;

    private CategoryElementDTO coveringAdvisorDepositType;

    private CategoryElementDTO impartType;

    private CategoryElementDTO dealType;

    private CategoryElementDTO transportVehicleType;

    private CategoryElementDTO freightLetterType;

    private CategoryElementDTO actionCode;

    private CategoryElementDTO ownershipCode;

    private CategoryElementDTO currencyContainerPlace;

    private CategoryElementDTO paymentType;

    private CategoryElementDTO draftSource;

    private CustomDTO loadSwitchPlace;

    private DraftTypeDTO draftType;

    private DraftStatusInfoDTO statusInfo;

    private InsuranceCompanyInfoDTO insuranceCompanyInfo;

    private AdvisorDefinitionDTO advisingBank;

    private AdvisorDefinitionDTO interfaceAdvisingBank;

    private AdvisorDefinitionDTO coveringBank;

    private AuditCompanyInfoDTO auditCompanyInfo;

    private TransportationTypeDTO transportType;

    private CurrencyExchangeInfoDTO currencyExchangeInfo;

    private DraftAccountInfoDTO accountInfo;

    private CustomDTO destinationCustomCompanies;

    private CustomDTO sourceCustomCompanies;

    private Set<ServiceTariffDTO> services = new HashSet<>();

    private Set<ProductDTO> products = new HashSet<>();

    private Set<StringValueDTO> sanctionSerials = new HashSet<>();

    private Set<LongValueDTO> customerNumbers = new HashSet<>();

    private Set<SuggestedSanctionInfoDTO> suggestedSanctions = new HashSet<>();

    private Set<DraftDocumentTransactionContainerDTO> documentTransactionContainers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvisorDepositNumber() {
        return advisorDepositNumber;
    }

    public void setAdvisorDepositNumber(String advisorDepositNumber) {
        this.advisorDepositNumber = advisorDepositNumber;
    }

    public Boolean getAdvisorRequestDeleted() {
        return advisorRequestDeleted;
    }

    public void setAdvisorRequestDeleted(Boolean advisorRequestDeleted) {
        this.advisorRequestDeleted = advisorRequestDeleted;
    }

    public BigDecimal getAuditCost() {
        return auditCost;
    }

    public void setAuditCost(BigDecimal auditCost) {
        this.auditCost = auditCost;
    }

    public String getBeneficiaryInfo() {
        return beneficiaryInfo;
    }

    public void setBeneficiaryInfo(String beneficiaryInfo) {
        this.beneficiaryInfo = beneficiaryInfo;
    }

    public BigDecimal getBranchStampCost() {
        return branchStampCost;
    }

    public void setBranchStampCost(BigDecimal branchStampCost) {
        this.branchStampCost = branchStampCost;
    }

    public String getCentralBankCode() {
        return centralBankCode;
    }

    public void setCentralBankCode(String centralBankCode) {
        this.centralBankCode = centralBankCode;
    }

    public String getCentralBankCodeReference() {
        return centralBankCodeReference;
    }

    public void setCentralBankCodeReference(String centralBankCodeReference) {
        this.centralBankCodeReference = centralBankCodeReference;
    }

    public BigDecimal getChargedExchangeBrokerAmount() {
        return chargedExchangeBrokerAmount;
    }

    public void setChargedExchangeBrokerAmount(BigDecimal chargedExchangeBrokerAmount) {
        this.chargedExchangeBrokerAmount = chargedExchangeBrokerAmount;
    }

    public Boolean getCharterAcceptable() {
        return charterAcceptable;
    }

    public void setCharterAcceptable(Boolean charterAcceptable) {
        this.charterAcceptable = charterAcceptable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getCoveringBankDepositNumber() {
        return coveringBankDepositNumber;
    }

    public void setCoveringBankDepositNumber(String coveringBankDepositNumber) {
        this.coveringBankDepositNumber = coveringBankDepositNumber;
    }

    public String getCurrencyExchangeDepositNumber() {
        return currencyExchangeDepositNumber;
    }

    public void setCurrencyExchangeDepositNumber(String currencyExchangeDepositNumber) {
        this.currencyExchangeDepositNumber = currencyExchangeDepositNumber;
    }

    public String getCustomerDepositNumber() {
        return customerDepositNumber;
    }

    public void setCustomerDepositNumber(String customerDepositNumber) {
        this.customerDepositNumber = customerDepositNumber;
    }

    public Integer getDeliverDuration() {
        return deliverDuration;
    }

    public void setDeliverDuration(Integer deliverDuration) {
        this.deliverDuration = deliverDuration;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDraftNumber() {
        return draftNumber;
    }

    public void setDraftNumber(String draftNumber) {
        this.draftNumber = draftNumber;
    }

    public BigDecimal getDraftOrderRegProductWorth() {
        return draftOrderRegProductWorth;
    }

    public void setDraftOrderRegProductWorth(BigDecimal draftOrderRegProductWorth) {
        this.draftOrderRegProductWorth = draftOrderRegProductWorth;
    }

    public BigDecimal getDraftOrderRegServiceWorth() {
        return draftOrderRegServiceWorth;
    }

    public void setDraftOrderRegServiceWorth(BigDecimal draftOrderRegServiceWorth) {
        this.draftOrderRegServiceWorth = draftOrderRegServiceWorth;
    }

    public BigDecimal getDraftOrderRegTotalWorth() {
        return draftOrderRegTotalWorth;
    }

    public void setDraftOrderRegTotalWorth(BigDecimal draftOrderRegTotalWorth) {
        this.draftOrderRegTotalWorth = draftOrderRegTotalWorth;
    }

    public BigDecimal getDraftOtherCost() {
        return draftOtherCost;
    }

    public void setDraftOtherCost(BigDecimal draftOtherCost) {
        this.draftOtherCost = draftOtherCost;
    }

    public Boolean getHasTransportJustification() {
        return hasTransportJustification;
    }

    public void setHasTransportJustification(Boolean hasTransportJustification) {
        this.hasTransportJustification = hasTransportJustification;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public String getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(String insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public String getInsuranceExpiryDate() {
        return insuranceExpiryDate;
    }

    public void setInsuranceExpiryDate(String insuranceExpiryDate) {
        this.insuranceExpiryDate = insuranceExpiryDate;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getInterfaceAdvisorDepositNumber() {
        return interfaceAdvisorDepositNumber;
    }

    public void setInterfaceAdvisorDepositNumber(String interfaceAdvisorDepositNumber) {
        this.interfaceAdvisorDepositNumber = interfaceAdvisorDepositNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getIssueDraftCommission() {
        return issueDraftCommission;
    }

    public void setIssueDraftCommission(BigDecimal issueDraftCommission) {
        this.issueDraftCommission = issueDraftCommission;
    }

    public String getLastShipmentDate() {
        return lastShipmentDate;
    }

    public void setLastShipmentDate(String lastShipmentDate) {
        this.lastShipmentDate = lastShipmentDate;
    }

    public Long getMainCustomerNumber() {
        return mainCustomerNumber;
    }

    public void setMainCustomerNumber(Long mainCustomerNumber) {
        this.mainCustomerNumber = mainCustomerNumber;
    }

    public Boolean getMakeCertification() {
        return makeCertification;
    }

    public void setMakeCertification(Boolean makeCertification) {
        this.makeCertification = makeCertification;
    }

    public Boolean getMultipleTransportable() {
        return multipleTransportable;
    }

    public void setMultipleTransportable(Boolean multipleTransportable) {
        this.multipleTransportable = multipleTransportable;
    }

    public String getOrderRegistrationDate() {
        return orderRegistrationDate;
    }

    public void setOrderRegistrationDate(String orderRegistrationDate) {
        this.orderRegistrationDate = orderRegistrationDate;
    }

    public String getOrderRegistrationExpiryDate() {
        return orderRegistrationExpiryDate;
    }

    public void setOrderRegistrationExpiryDate(String orderRegistrationExpiryDate) {
        this.orderRegistrationExpiryDate = orderRegistrationExpiryDate;
    }

    public String getOrderRegistrationNumber() {
        return orderRegistrationNumber;
    }

    public void setOrderRegistrationNumber(String orderRegistrationNumber) {
        this.orderRegistrationNumber = orderRegistrationNumber;
    }

    public BigDecimal getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
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

    public String getPerformaNumber() {
        return performaNumber;
    }

    public void setPerformaNumber(String performaNumber) {
        this.performaNumber = performaNumber;
    }

    public BigDecimal getPostSwiftCost() {
        return postSwiftCost;
    }

    public void setPostSwiftCost(BigDecimal postSwiftCost) {
        this.postSwiftCost = postSwiftCost;
    }

    public Boolean getProductSourceChangeable() {
        return productSourceChangeable;
    }

    public void setProductSourceChangeable(Boolean productSourceChangeable) {
        this.productSourceChangeable = productSourceChangeable;
    }

    public Boolean getReceiveAllCommission() {
        return receiveAllCommission;
    }

    public void setReceiveAllCommission(Boolean receiveAllCommission) {
        this.receiveAllCommission = receiveAllCommission;
    }

    public BigDecimal getRegisterationJustificationAmount() {
        return registerationJustificationAmount;
    }

    public void setRegisterationJustificationAmount(BigDecimal registerationJustificationAmount) {
        this.registerationJustificationAmount = registerationJustificationAmount;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getSanctionSerial() {
        return sanctionSerial;
    }

    public void setSanctionSerial(String sanctionSerial) {
        this.sanctionSerial = sanctionSerial;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public BigDecimal getShipmentCost() {
        return shipmentCost;
    }

    public void setShipmentCost(BigDecimal shipmentCost) {
        this.shipmentCost = shipmentCost;
    }

    public String getSourceTransportPlace() {
        return sourceTransportPlace;
    }

    public void setSourceTransportPlace(String sourceTransportPlace) {
        this.sourceTransportPlace = sourceTransportPlace;
    }

    public String getSwiftComment() {
        return swiftComment;
    }

    public void setSwiftComment(String swiftComment) {
        this.swiftComment = swiftComment;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Boolean getTransportVehicleChangeable() {
        return transportVehicleChangeable;
    }

    public void setTransportVehicleChangeable(Boolean transportVehicleChangeable) {
        this.transportVehicleChangeable = transportVehicleChangeable;
    }

    public String getPaymentTool() {
        return paymentTool;
    }

    public void setPaymentTool(String paymentTool) {
        this.paymentTool = paymentTool;
    }

    public String getLetterNumberTazirat() {
        return letterNumberTazirat;
    }

    public void setLetterNumberTazirat(String letterNumberTazirat) {
        this.letterNumberTazirat = letterNumberTazirat;
    }

    public String getLetterDateTazirat() {
        return letterDateTazirat;
    }

    public void setLetterDateTazirat(String letterDateTazirat) {
        this.letterDateTazirat = letterDateTazirat;
    }

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Boolean getIsMigrational() {
        return isMigrational;
    }

    public void setIsMigrational(Boolean isMigrational) {
        this.isMigrational = isMigrational;
    }

    public Boolean getIsNewCertificate() {
        return isNewCertificate;
    }

    public void setIsNewCertificate(Boolean isNewCertificate) {
        this.isNewCertificate = isNewCertificate;
    }

    public Boolean getIsWithoutPayment() {
        return isWithoutPayment;
    }

    public void setIsWithoutPayment(Boolean isWithoutPayment) {
        this.isWithoutPayment = isWithoutPayment;
    }

    public String getMainAccountCurrencyCode() {
        return mainAccountCurrencyCode;
    }

    public void setMainAccountCurrencyCode(String mainAccountCurrencyCode) {
        this.mainAccountCurrencyCode = mainAccountCurrencyCode;
    }

    public String getOrderRegCurrencyCode() {
        return orderRegCurrencyCode;
    }

    public void setOrderRegCurrencyCode(String orderRegCurrencyCode) {
        this.orderRegCurrencyCode = orderRegCurrencyCode;
    }

    public String getChargedExchangeBrokerCurrencyCode() {
        return chargedExchangeBrokerCurrencyCode;
    }

    public void setChargedExchangeBrokerCurrencyCode(String chargedExchangeBrokerCurrencyCode) {
        this.chargedExchangeBrokerCurrencyCode = chargedExchangeBrokerCurrencyCode;
    }

    public String getDestinationCountryCode() {
        return destinationCountryCode;
    }

    public void setDestinationCountryCode(String destinationCountryCode) {
        this.destinationCountryCode = destinationCountryCode;
    }

    public String getBeneficiaryCountryCode() {
        return beneficiaryCountryCode;
    }

    public void setBeneficiaryCountryCode(String beneficiaryCountryCode) {
        this.beneficiaryCountryCode = beneficiaryCountryCode;
    }

    public String getProducerCountryCode() {
        return producerCountryCode;
    }

    public void setProducerCountryCode(String producerCountryCode) {
        this.producerCountryCode = producerCountryCode;
    }

    public String getRegisterationJustificationCurrencyCode() {
        return registerationJustificationCurrencyCode;
    }

    public void setRegisterationJustificationCurrencyCode(String registerationJustificationCurrencyCode) {
        this.registerationJustificationCurrencyCode = registerationJustificationCurrencyCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getOperationalBranchCode() {
        return operationalBranchCode;
    }

    public void setOperationalBranchCode(String operationalBranchCode) {
        this.operationalBranchCode = operationalBranchCode;
    }

    public String getCertificateSenderBranchCode() {
        return certificateSenderBranchCode;
    }

    public void setCertificateSenderBranchCode(String certificateSenderBranchCode) {
        this.certificateSenderBranchCode = certificateSenderBranchCode;
    }

    public CategoryElementDTO getChargedExchangeBroker() {
        return chargedExchangeBroker;
    }

    public void setChargedExchangeBroker(CategoryElementDTO chargedExchangeBroker) {
        this.chargedExchangeBroker = chargedExchangeBroker;
    }

    public CategoryElementDTO getInsuranceLetterType() {
        return insuranceLetterType;
    }

    public void setInsuranceLetterType(CategoryElementDTO insuranceLetterType) {
        this.insuranceLetterType = insuranceLetterType;
    }

    public CategoryElementDTO getAdvisorDepositType() {
        return advisorDepositType;
    }

    public void setAdvisorDepositType(CategoryElementDTO advisorDepositType) {
        this.advisorDepositType = advisorDepositType;
    }

    public CategoryElementDTO getInterfaceAdvisorDepositType() {
        return interfaceAdvisorDepositType;
    }

    public void setInterfaceAdvisorDepositType(CategoryElementDTO interfaceAdvisorDepositType) {
        this.interfaceAdvisorDepositType = interfaceAdvisorDepositType;
    }

    public CategoryElementDTO getCoveringAdvisorDepositType() {
        return coveringAdvisorDepositType;
    }

    public void setCoveringAdvisorDepositType(CategoryElementDTO coveringAdvisorDepositType) {
        this.coveringAdvisorDepositType = coveringAdvisorDepositType;
    }

    public CategoryElementDTO getImpartType() {
        return impartType;
    }

    public void setImpartType(CategoryElementDTO impartType) {
        this.impartType = impartType;
    }

    public CategoryElementDTO getDealType() {
        return dealType;
    }

    public void setDealType(CategoryElementDTO dealType) {
        this.dealType = dealType;
    }

    public CategoryElementDTO getTransportVehicleType() {
        return transportVehicleType;
    }

    public void setTransportVehicleType(CategoryElementDTO transportVehicleType) {
        this.transportVehicleType = transportVehicleType;
    }

    public CategoryElementDTO getFreightLetterType() {
        return freightLetterType;
    }

    public void setFreightLetterType(CategoryElementDTO freightLetterType) {
        this.freightLetterType = freightLetterType;
    }

    public CategoryElementDTO getActionCode() {
        return actionCode;
    }

    public void setActionCode(CategoryElementDTO actionCode) {
        this.actionCode = actionCode;
    }

    public CategoryElementDTO getOwnershipCode() {
        return ownershipCode;
    }

    public void setOwnershipCode(CategoryElementDTO ownershipCode) {
        this.ownershipCode = ownershipCode;
    }

    public CategoryElementDTO getCurrencyContainerPlace() {
        return currencyContainerPlace;
    }

    public void setCurrencyContainerPlace(CategoryElementDTO currencyContainerPlace) {
        this.currencyContainerPlace = currencyContainerPlace;
    }

    public CategoryElementDTO getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(CategoryElementDTO paymentType) {
        this.paymentType = paymentType;
    }

    public CategoryElementDTO getDraftSource() {
        return draftSource;
    }

    public void setDraftSource(CategoryElementDTO draftSource) {
        this.draftSource = draftSource;
    }

    public CustomDTO getLoadSwitchPlace() {
        return loadSwitchPlace;
    }

    public void setLoadSwitchPlace(CustomDTO loadSwitchPlace) {
        this.loadSwitchPlace = loadSwitchPlace;
    }

    public DraftTypeDTO getDraftType() {
        return draftType;
    }

    public void setDraftType(DraftTypeDTO draftType) {
        this.draftType = draftType;
    }

    public DraftStatusInfoDTO getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(DraftStatusInfoDTO statusInfo) {
        this.statusInfo = statusInfo;
    }

    public InsuranceCompanyInfoDTO getInsuranceCompanyInfo() {
        return insuranceCompanyInfo;
    }

    public void setInsuranceCompanyInfo(InsuranceCompanyInfoDTO insuranceCompanyInfo) {
        this.insuranceCompanyInfo = insuranceCompanyInfo;
    }

    public AdvisorDefinitionDTO getAdvisingBank() {
        return advisingBank;
    }

    public void setAdvisingBank(AdvisorDefinitionDTO advisingBank) {
        this.advisingBank = advisingBank;
    }

    public AdvisorDefinitionDTO getInterfaceAdvisingBank() {
        return interfaceAdvisingBank;
    }

    public void setInterfaceAdvisingBank(AdvisorDefinitionDTO interfaceAdvisingBank) {
        this.interfaceAdvisingBank = interfaceAdvisingBank;
    }

    public AdvisorDefinitionDTO getCoveringBank() {
        return coveringBank;
    }

    public void setCoveringBank(AdvisorDefinitionDTO coveringBank) {
        this.coveringBank = coveringBank;
    }

    public AuditCompanyInfoDTO getAuditCompanyInfo() {
        return auditCompanyInfo;
    }

    public void setAuditCompanyInfo(AuditCompanyInfoDTO auditCompanyInfo) {
        this.auditCompanyInfo = auditCompanyInfo;
    }

    public TransportationTypeDTO getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportationTypeDTO transportType) {
        this.transportType = transportType;
    }

    public CurrencyExchangeInfoDTO getCurrencyExchangeInfo() {
        return currencyExchangeInfo;
    }

    public void setCurrencyExchangeInfo(CurrencyExchangeInfoDTO currencyExchangeInfo) {
        this.currencyExchangeInfo = currencyExchangeInfo;
    }

    public DraftAccountInfoDTO getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(DraftAccountInfoDTO accountInfo) {
        this.accountInfo = accountInfo;
    }

    public CustomDTO getDestinationCustomCompanies() {
        return destinationCustomCompanies;
    }

    public void setDestinationCustomCompanies(CustomDTO destinationCustomCompanies) {
        this.destinationCustomCompanies = destinationCustomCompanies;
    }

    public CustomDTO getSourceCustomCompanies() {
        return sourceCustomCompanies;
    }

    public void setSourceCustomCompanies(CustomDTO sourceCustomCompanies) {
        this.sourceCustomCompanies = sourceCustomCompanies;
    }

    public Set<ServiceTariffDTO> getServices() {
        return services;
    }

    public void setServices(Set<ServiceTariffDTO> services) {
        this.services = services;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }

    public Set<StringValueDTO> getSanctionSerials() {
        return sanctionSerials;
    }

    public void setSanctionSerials(Set<StringValueDTO> sanctionSerials) {
        this.sanctionSerials = sanctionSerials;
    }

    public Set<LongValueDTO> getCustomerNumbers() {
        return customerNumbers;
    }

    public void setCustomerNumbers(Set<LongValueDTO> customerNumbers) {
        this.customerNumbers = customerNumbers;
    }

    public Set<SuggestedSanctionInfoDTO> getSuggestedSanctions() {
        return suggestedSanctions;
    }

    public void setSuggestedSanctions(Set<SuggestedSanctionInfoDTO> suggestedSanctions) {
        this.suggestedSanctions = suggestedSanctions;
    }

    public Set<DraftDocumentTransactionContainerDTO> getDocumentTransactionContainers() {
        return documentTransactionContainers;
    }

    public void setDocumentTransactionContainers(Set<DraftDocumentTransactionContainerDTO> documentTransactionContainers) {
        this.documentTransactionContainers = documentTransactionContainers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftDTO)) {
            return false;
        }

        DraftDTO draftDTO = (DraftDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftDTO{" +
            "id=" + getId() +
            ", advisorDepositNumber='" + getAdvisorDepositNumber() + "'" +
            ", advisorRequestDeleted='" + getAdvisorRequestDeleted() + "'" +
            ", auditCost=" + getAuditCost() +
            ", beneficiaryInfo='" + getBeneficiaryInfo() + "'" +
            ", branchStampCost=" + getBranchStampCost() +
            ", centralBankCode='" + getCentralBankCode() + "'" +
            ", centralBankCodeReference='" + getCentralBankCodeReference() + "'" +
            ", chargedExchangeBrokerAmount=" + getChargedExchangeBrokerAmount() +
            ", charterAcceptable='" + getCharterAcceptable() + "'" +
            ", comment='" + getComment() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", coveringBankDepositNumber='" + getCoveringBankDepositNumber() + "'" +
            ", currencyExchangeDepositNumber='" + getCurrencyExchangeDepositNumber() + "'" +
            ", customerDepositNumber='" + getCustomerDepositNumber() + "'" +
            ", deliverDuration=" + getDeliverDuration() +
            ", discount=" + getDiscount() +
            ", draftNumber='" + getDraftNumber() + "'" +
            ", draftOrderRegProductWorth=" + getDraftOrderRegProductWorth() +
            ", draftOrderRegServiceWorth=" + getDraftOrderRegServiceWorth() +
            ", draftOrderRegTotalWorth=" + getDraftOrderRegTotalWorth() +
            ", draftOtherCost=" + getDraftOtherCost() +
            ", hasTransportJustification='" + getHasTransportJustification() + "'" +
            ", insuranceCost=" + getInsuranceCost() +
            ", insuranceDate='" + getInsuranceDate() + "'" +
            ", insuranceExpiryDate='" + getInsuranceExpiryDate() + "'" +
            ", insuranceNumber='" + getInsuranceNumber() + "'" +
            ", interfaceAdvisorDepositNumber='" + getInterfaceAdvisorDepositNumber() + "'" +
            ", issueDate='" + getIssueDate() + "'" +
            ", issueDraftCommission=" + getIssueDraftCommission() +
            ", lastShipmentDate='" + getLastShipmentDate() + "'" +
            ", mainCustomerNumber=" + getMainCustomerNumber() +
            ", makeCertification='" + getMakeCertification() + "'" +
            ", multipleTransportable='" + getMultipleTransportable() + "'" +
            ", orderRegistrationDate='" + getOrderRegistrationDate() + "'" +
            ", orderRegistrationExpiryDate='" + getOrderRegistrationExpiryDate() + "'" +
            ", orderRegistrationNumber='" + getOrderRegistrationNumber() + "'" +
            ", otherCost=" + getOtherCost() +
            ", paymentAmount=" + getPaymentAmount() +
            ", performaDate='" + getPerformaDate() + "'" +
            ", performaExpiryDate='" + getPerformaExpiryDate() + "'" +
            ", performaNumber='" + getPerformaNumber() + "'" +
            ", postSwiftCost=" + getPostSwiftCost() +
            ", productSourceChangeable='" + getProductSourceChangeable() + "'" +
            ", receiveAllCommission='" + getReceiveAllCommission() + "'" +
            ", registerationJustificationAmount=" + getRegisterationJustificationAmount() +
            ", requestDate='" + getRequestDate() + "'" +
            ", sanctionSerial='" + getSanctionSerial() + "'" +
            ", serial=" + getSerial() +
            ", shipmentCost=" + getShipmentCost() +
            ", sourceTransportPlace='" + getSourceTransportPlace() + "'" +
            ", swiftComment='" + getSwiftComment() + "'" +
            ", transferAmount=" + getTransferAmount() +
            ", transportVehicleChangeable='" + getTransportVehicleChangeable() + "'" +
            ", paymentTool='" + getPaymentTool() + "'" +
            ", letterNumberTazirat='" + getLetterNumberTazirat() + "'" +
            ", letterDateTazirat='" + getLetterDateTazirat() + "'" +
            ", loanNumber='" + getLoanNumber() + "'" +
            ", isMigrational='" + getIsMigrational() + "'" +
            ", isNewCertificate='" + getIsNewCertificate() + "'" +
            ", isWithoutPayment='" + getIsWithoutPayment() + "'" +
            ", mainAccountCurrencyCode='" + getMainAccountCurrencyCode() + "'" +
            ", orderRegCurrencyCode='" + getOrderRegCurrencyCode() + "'" +
            ", chargedExchangeBrokerCurrencyCode='" + getChargedExchangeBrokerCurrencyCode() + "'" +
            ", destinationCountryCode='" + getDestinationCountryCode() + "'" +
            ", beneficiaryCountryCode='" + getBeneficiaryCountryCode() + "'" +
            ", producerCountryCode='" + getProducerCountryCode() + "'" +
            ", registerationJustificationCurrencyCode='" + getRegisterationJustificationCurrencyCode() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            ", operationalBranchCode='" + getOperationalBranchCode() + "'" +
            ", certificateSenderBranchCode='" + getCertificateSenderBranchCode() + "'" +
            ", chargedExchangeBroker=" + getChargedExchangeBroker() +
            ", insuranceLetterType=" + getInsuranceLetterType() +
            ", advisorDepositType=" + getAdvisorDepositType() +
            ", interfaceAdvisorDepositType=" + getInterfaceAdvisorDepositType() +
            ", coveringAdvisorDepositType=" + getCoveringAdvisorDepositType() +
            ", impartType=" + getImpartType() +
            ", dealType=" + getDealType() +
            ", transportVehicleType=" + getTransportVehicleType() +
            ", freightLetterType=" + getFreightLetterType() +
            ", actionCode=" + getActionCode() +
            ", ownershipCode=" + getOwnershipCode() +
            ", currencyContainerPlace=" + getCurrencyContainerPlace() +
            ", paymentType=" + getPaymentType() +
            ", draftSource=" + getDraftSource() +
            ", loadSwitchPlace=" + getLoadSwitchPlace() +
            ", draftType=" + getDraftType() +
            ", statusInfo=" + getStatusInfo() +
            ", insuranceCompanyInfo=" + getInsuranceCompanyInfo() +
            ", advisingBank=" + getAdvisingBank() +
            ", interfaceAdvisingBank=" + getInterfaceAdvisingBank() +
            ", coveringBank=" + getCoveringBank() +
            ", auditCompanyInfo=" + getAuditCompanyInfo() +
            ", transportType=" + getTransportType() +
            ", currencyExchangeInfo=" + getCurrencyExchangeInfo() +
            ", accountInfo=" + getAccountInfo() +
            ", destinationCustomCompanies=" + getDestinationCustomCompanies() +
            ", sourceCustomCompanies=" + getSourceCustomCompanies() +
            ", services=" + getServices() +
            ", products=" + getProducts() +
            ", sanctionSerials=" + getSanctionSerials() +
            ", customerNumbers=" + getCustomerNumbers() +
            ", suggestedSanctions=" + getSuggestedSanctions() +
            ", documentTransactionContainers=" + getDocumentTransactionContainers() +
            "}";
    }
}
