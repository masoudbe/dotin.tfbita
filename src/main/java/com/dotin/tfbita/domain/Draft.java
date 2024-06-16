package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Draft.
 */
@Entity
@Table(name = "draft")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Draft implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "advisor_deposit_number")
    private String advisorDepositNumber;

    @Column(name = "advisor_request_deleted")
    private Boolean advisorRequestDeleted;

    @Column(name = "audit_cost", precision = 21, scale = 2)
    private BigDecimal auditCost;

    @Column(name = "beneficiary_info")
    private String beneficiaryInfo;

    @Column(name = "branch_stamp_cost", precision = 21, scale = 2)
    private BigDecimal branchStampCost;

    @Column(name = "central_bank_code")
    private String centralBankCode;

    @Column(name = "central_bank_code_reference")
    private String centralBankCodeReference;

    @Column(name = "charged_exchange_broker_amount", precision = 21, scale = 2)
    private BigDecimal chargedExchangeBrokerAmount;

    @Column(name = "charter_acceptable")
    private Boolean charterAcceptable;

    @Column(name = "comment")
    private String comment;

    @Column(name = "completion_date")
    private String completionDate;

    @Column(name = "covering_bank_deposit_number")
    private String coveringBankDepositNumber;

    @Column(name = "currency_exchange_deposit_number")
    private String currencyExchangeDepositNumber;

    @Column(name = "customer_deposit_number")
    private String customerDepositNumber;

    @Column(name = "deliver_duration")
    private Integer deliverDuration;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @Column(name = "draft_number")
    private String draftNumber;

    @Column(name = "draft_order_reg_product_worth", precision = 21, scale = 2)
    private BigDecimal draftOrderRegProductWorth;

    @Column(name = "draft_order_reg_service_worth", precision = 21, scale = 2)
    private BigDecimal draftOrderRegServiceWorth;

    @Column(name = "draft_order_reg_total_worth", precision = 21, scale = 2)
    private BigDecimal draftOrderRegTotalWorth;

    @Column(name = "draft_other_cost", precision = 21, scale = 2)
    private BigDecimal draftOtherCost;

    @Column(name = "has_transport_justification")
    private Boolean hasTransportJustification;

    @Column(name = "insurance_cost", precision = 21, scale = 2)
    private BigDecimal insuranceCost;

    @Column(name = "insurance_date")
    private String insuranceDate;

    @Column(name = "insurance_expiry_date")
    private String insuranceExpiryDate;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "interface_advisor_deposit_number")
    private String interfaceAdvisorDepositNumber;

    @Column(name = "issue_date")
    private String issueDate;

    @Column(name = "issue_draft_commission", precision = 21, scale = 2)
    private BigDecimal issueDraftCommission;

    @Column(name = "last_shipment_date")
    private String lastShipmentDate;

    @Column(name = "main_customer_number")
    private Long mainCustomerNumber;

    @Column(name = "make_certification")
    private Boolean makeCertification;

    @Column(name = "multiple_transportable")
    private Boolean multipleTransportable;

    @Column(name = "order_registration_date")
    private String orderRegistrationDate;

    @Column(name = "order_registration_expiry_date")
    private String orderRegistrationExpiryDate;

    @Column(name = "order_registration_number")
    private String orderRegistrationNumber;

    @Column(name = "other_cost", precision = 21, scale = 2)
    private BigDecimal otherCost;

    @Column(name = "payment_amount", precision = 21, scale = 2)
    private BigDecimal paymentAmount;

    @Column(name = "performa_date")
    private String performaDate;

    @Column(name = "performa_expiry_date")
    private String performaExpiryDate;

    @Column(name = "performa_number")
    private String performaNumber;

    @Column(name = "post_swift_cost", precision = 21, scale = 2)
    private BigDecimal postSwiftCost;

    @Column(name = "product_source_changeable")
    private Boolean productSourceChangeable;

    @Column(name = "receive_all_commission")
    private Boolean receiveAllCommission;

    @Column(name = "registeration_justification_amount", precision = 21, scale = 2)
    private BigDecimal registerationJustificationAmount;

    @Column(name = "request_date")
    private String requestDate;

    @Column(name = "sanction_serial")
    private String sanctionSerial;

    @Column(name = "serial")
    private Long serial;

    @Column(name = "shipment_cost", precision = 21, scale = 2)
    private BigDecimal shipmentCost;

    @Column(name = "source_transport_place")
    private String sourceTransportPlace;

    @Column(name = "swift_comment")
    private String swiftComment;

    @Column(name = "transfer_amount", precision = 21, scale = 2)
    private BigDecimal transferAmount;

    @Column(name = "transport_vehicle_changeable")
    private Boolean transportVehicleChangeable;

    @Column(name = "payment_tool")
    private String paymentTool;

    @Column(name = "letter_number_tazirat")
    private String letterNumberTazirat;

    @Column(name = "letter_date_tazirat")
    private String letterDateTazirat;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "is_migrational")
    private Boolean isMigrational;

    @Column(name = "is_new_certificate")
    private Boolean isNewCertificate;

    @Column(name = "is_without_payment")
    private Boolean isWithoutPayment;

    @Column(name = "destination_country_code")
    private String destinationCountryCode;

    @Column(name = "beneficiary_country_code")
    private String beneficiaryCountryCode;

    @Column(name = "producer_country_code")
    private String producerCountryCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "operational_branch_code")
    private String operationalBranchCode;

    @Column(name = "certificate_sender_branch_code")
    private String certificateSenderBranchCode;

    @Column(name = "main_account_currency_code")
    private String mainAccountCurrencyCode;

    @Column(name = "order_reg_currency_code")
    private String orderRegCurrencyCode;

    @Column(name = "charged_exchange_broker_currency")
    private String chargedExchangeBrokerCurrency;

    @Column(name = "registeration_justification_currency_code")
    private String registerationJustificationCurrencyCode;

    @Column(name = "currency_exchange_info_title")
    private String currencyExchangeInfoTitle;

    @Column(name = "transportation_type_name")
    private String transportationTypeName;

    @Column(name = "account_info_code")
    private String accountInfoCode;

    @Column(name = "customer_numbers")
    private Long customerNumbers;

    @Column(name = "sanction_serials")
    private String sanctionSerials;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receipts")
    @JsonIgnoreProperties(value = { "products", "receipts", "draftCustomJustifications" }, allowSetters = true)
    private Set<DraftReceipt> draftReceipts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usedAssurances")
    @JsonIgnoreProperties(value = { "usedAssurances" }, allowSetters = true)
    private Set<DraftUsedAssurance> draftUsedAssurances = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draftFactors")
    @JsonIgnoreProperties(value = { "draftFactors" }, allowSetters = true)
    private Set<DraftFactor> draftFactors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draftJustifications")
    @JsonIgnoreProperties(value = { "draftReceipts", "draftJustifications" }, allowSetters = true)
    private Set<DraftCustomJustification> draftCustomJustifications = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "extensions")
    @JsonIgnoreProperties(value = { "extensions" }, allowSetters = true)
    private Set<DraftExtend> draftExtends = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taxes")
    @JsonIgnoreProperties(value = { "taxes" }, allowSetters = true)
    private Set<DraftTax> draftTaxes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "drafts")
    @JsonIgnoreProperties(value = { "loadSwitchPlace", "orderRegistrationInfos", "drafts" }, allowSetters = true)
    private Set<Custom> customs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "drafts")
    @JsonIgnoreProperties(value = { "orderRegistrationInfos", "drafts", "draftProductInfos" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "drafts")
    @JsonIgnoreProperties(value = { "drafts" }, allowSetters = true)
    private Set<ServiceTariff> services = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Draft id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvisorDepositNumber() {
        return this.advisorDepositNumber;
    }

    public Draft advisorDepositNumber(String advisorDepositNumber) {
        this.setAdvisorDepositNumber(advisorDepositNumber);
        return this;
    }

    public void setAdvisorDepositNumber(String advisorDepositNumber) {
        this.advisorDepositNumber = advisorDepositNumber;
    }

    public Boolean getAdvisorRequestDeleted() {
        return this.advisorRequestDeleted;
    }

    public Draft advisorRequestDeleted(Boolean advisorRequestDeleted) {
        this.setAdvisorRequestDeleted(advisorRequestDeleted);
        return this;
    }

    public void setAdvisorRequestDeleted(Boolean advisorRequestDeleted) {
        this.advisorRequestDeleted = advisorRequestDeleted;
    }

    public BigDecimal getAuditCost() {
        return this.auditCost;
    }

    public Draft auditCost(BigDecimal auditCost) {
        this.setAuditCost(auditCost);
        return this;
    }

    public void setAuditCost(BigDecimal auditCost) {
        this.auditCost = auditCost;
    }

    public String getBeneficiaryInfo() {
        return this.beneficiaryInfo;
    }

    public Draft beneficiaryInfo(String beneficiaryInfo) {
        this.setBeneficiaryInfo(beneficiaryInfo);
        return this;
    }

    public void setBeneficiaryInfo(String beneficiaryInfo) {
        this.beneficiaryInfo = beneficiaryInfo;
    }

    public BigDecimal getBranchStampCost() {
        return this.branchStampCost;
    }

    public Draft branchStampCost(BigDecimal branchStampCost) {
        this.setBranchStampCost(branchStampCost);
        return this;
    }

    public void setBranchStampCost(BigDecimal branchStampCost) {
        this.branchStampCost = branchStampCost;
    }

    public String getCentralBankCode() {
        return this.centralBankCode;
    }

    public Draft centralBankCode(String centralBankCode) {
        this.setCentralBankCode(centralBankCode);
        return this;
    }

    public void setCentralBankCode(String centralBankCode) {
        this.centralBankCode = centralBankCode;
    }

    public String getCentralBankCodeReference() {
        return this.centralBankCodeReference;
    }

    public Draft centralBankCodeReference(String centralBankCodeReference) {
        this.setCentralBankCodeReference(centralBankCodeReference);
        return this;
    }

    public void setCentralBankCodeReference(String centralBankCodeReference) {
        this.centralBankCodeReference = centralBankCodeReference;
    }

    public BigDecimal getChargedExchangeBrokerAmount() {
        return this.chargedExchangeBrokerAmount;
    }

    public Draft chargedExchangeBrokerAmount(BigDecimal chargedExchangeBrokerAmount) {
        this.setChargedExchangeBrokerAmount(chargedExchangeBrokerAmount);
        return this;
    }

    public void setChargedExchangeBrokerAmount(BigDecimal chargedExchangeBrokerAmount) {
        this.chargedExchangeBrokerAmount = chargedExchangeBrokerAmount;
    }

    public Boolean getCharterAcceptable() {
        return this.charterAcceptable;
    }

    public Draft charterAcceptable(Boolean charterAcceptable) {
        this.setCharterAcceptable(charterAcceptable);
        return this;
    }

    public void setCharterAcceptable(Boolean charterAcceptable) {
        this.charterAcceptable = charterAcceptable;
    }

    public String getComment() {
        return this.comment;
    }

    public Draft comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCompletionDate() {
        return this.completionDate;
    }

    public Draft completionDate(String completionDate) {
        this.setCompletionDate(completionDate);
        return this;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getCoveringBankDepositNumber() {
        return this.coveringBankDepositNumber;
    }

    public Draft coveringBankDepositNumber(String coveringBankDepositNumber) {
        this.setCoveringBankDepositNumber(coveringBankDepositNumber);
        return this;
    }

    public void setCoveringBankDepositNumber(String coveringBankDepositNumber) {
        this.coveringBankDepositNumber = coveringBankDepositNumber;
    }

    public String getCurrencyExchangeDepositNumber() {
        return this.currencyExchangeDepositNumber;
    }

    public Draft currencyExchangeDepositNumber(String currencyExchangeDepositNumber) {
        this.setCurrencyExchangeDepositNumber(currencyExchangeDepositNumber);
        return this;
    }

    public void setCurrencyExchangeDepositNumber(String currencyExchangeDepositNumber) {
        this.currencyExchangeDepositNumber = currencyExchangeDepositNumber;
    }

    public String getCustomerDepositNumber() {
        return this.customerDepositNumber;
    }

    public Draft customerDepositNumber(String customerDepositNumber) {
        this.setCustomerDepositNumber(customerDepositNumber);
        return this;
    }

    public void setCustomerDepositNumber(String customerDepositNumber) {
        this.customerDepositNumber = customerDepositNumber;
    }

    public Integer getDeliverDuration() {
        return this.deliverDuration;
    }

    public Draft deliverDuration(Integer deliverDuration) {
        this.setDeliverDuration(deliverDuration);
        return this;
    }

    public void setDeliverDuration(Integer deliverDuration) {
        this.deliverDuration = deliverDuration;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public Draft discount(BigDecimal discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDraftNumber() {
        return this.draftNumber;
    }

    public Draft draftNumber(String draftNumber) {
        this.setDraftNumber(draftNumber);
        return this;
    }

    public void setDraftNumber(String draftNumber) {
        this.draftNumber = draftNumber;
    }

    public BigDecimal getDraftOrderRegProductWorth() {
        return this.draftOrderRegProductWorth;
    }

    public Draft draftOrderRegProductWorth(BigDecimal draftOrderRegProductWorth) {
        this.setDraftOrderRegProductWorth(draftOrderRegProductWorth);
        return this;
    }

    public void setDraftOrderRegProductWorth(BigDecimal draftOrderRegProductWorth) {
        this.draftOrderRegProductWorth = draftOrderRegProductWorth;
    }

    public BigDecimal getDraftOrderRegServiceWorth() {
        return this.draftOrderRegServiceWorth;
    }

    public Draft draftOrderRegServiceWorth(BigDecimal draftOrderRegServiceWorth) {
        this.setDraftOrderRegServiceWorth(draftOrderRegServiceWorth);
        return this;
    }

    public void setDraftOrderRegServiceWorth(BigDecimal draftOrderRegServiceWorth) {
        this.draftOrderRegServiceWorth = draftOrderRegServiceWorth;
    }

    public BigDecimal getDraftOrderRegTotalWorth() {
        return this.draftOrderRegTotalWorth;
    }

    public Draft draftOrderRegTotalWorth(BigDecimal draftOrderRegTotalWorth) {
        this.setDraftOrderRegTotalWorth(draftOrderRegTotalWorth);
        return this;
    }

    public void setDraftOrderRegTotalWorth(BigDecimal draftOrderRegTotalWorth) {
        this.draftOrderRegTotalWorth = draftOrderRegTotalWorth;
    }

    public BigDecimal getDraftOtherCost() {
        return this.draftOtherCost;
    }

    public Draft draftOtherCost(BigDecimal draftOtherCost) {
        this.setDraftOtherCost(draftOtherCost);
        return this;
    }

    public void setDraftOtherCost(BigDecimal draftOtherCost) {
        this.draftOtherCost = draftOtherCost;
    }

    public Boolean getHasTransportJustification() {
        return this.hasTransportJustification;
    }

    public Draft hasTransportJustification(Boolean hasTransportJustification) {
        this.setHasTransportJustification(hasTransportJustification);
        return this;
    }

    public void setHasTransportJustification(Boolean hasTransportJustification) {
        this.hasTransportJustification = hasTransportJustification;
    }

    public BigDecimal getInsuranceCost() {
        return this.insuranceCost;
    }

    public Draft insuranceCost(BigDecimal insuranceCost) {
        this.setInsuranceCost(insuranceCost);
        return this;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public String getInsuranceDate() {
        return this.insuranceDate;
    }

    public Draft insuranceDate(String insuranceDate) {
        this.setInsuranceDate(insuranceDate);
        return this;
    }

    public void setInsuranceDate(String insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public String getInsuranceExpiryDate() {
        return this.insuranceExpiryDate;
    }

    public Draft insuranceExpiryDate(String insuranceExpiryDate) {
        this.setInsuranceExpiryDate(insuranceExpiryDate);
        return this;
    }

    public void setInsuranceExpiryDate(String insuranceExpiryDate) {
        this.insuranceExpiryDate = insuranceExpiryDate;
    }

    public String getInsuranceNumber() {
        return this.insuranceNumber;
    }

    public Draft insuranceNumber(String insuranceNumber) {
        this.setInsuranceNumber(insuranceNumber);
        return this;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getInterfaceAdvisorDepositNumber() {
        return this.interfaceAdvisorDepositNumber;
    }

    public Draft interfaceAdvisorDepositNumber(String interfaceAdvisorDepositNumber) {
        this.setInterfaceAdvisorDepositNumber(interfaceAdvisorDepositNumber);
        return this;
    }

    public void setInterfaceAdvisorDepositNumber(String interfaceAdvisorDepositNumber) {
        this.interfaceAdvisorDepositNumber = interfaceAdvisorDepositNumber;
    }

    public String getIssueDate() {
        return this.issueDate;
    }

    public Draft issueDate(String issueDate) {
        this.setIssueDate(issueDate);
        return this;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getIssueDraftCommission() {
        return this.issueDraftCommission;
    }

    public Draft issueDraftCommission(BigDecimal issueDraftCommission) {
        this.setIssueDraftCommission(issueDraftCommission);
        return this;
    }

    public void setIssueDraftCommission(BigDecimal issueDraftCommission) {
        this.issueDraftCommission = issueDraftCommission;
    }

    public String getLastShipmentDate() {
        return this.lastShipmentDate;
    }

    public Draft lastShipmentDate(String lastShipmentDate) {
        this.setLastShipmentDate(lastShipmentDate);
        return this;
    }

    public void setLastShipmentDate(String lastShipmentDate) {
        this.lastShipmentDate = lastShipmentDate;
    }

    public Long getMainCustomerNumber() {
        return this.mainCustomerNumber;
    }

    public Draft mainCustomerNumber(Long mainCustomerNumber) {
        this.setMainCustomerNumber(mainCustomerNumber);
        return this;
    }

    public void setMainCustomerNumber(Long mainCustomerNumber) {
        this.mainCustomerNumber = mainCustomerNumber;
    }

    public Boolean getMakeCertification() {
        return this.makeCertification;
    }

    public Draft makeCertification(Boolean makeCertification) {
        this.setMakeCertification(makeCertification);
        return this;
    }

    public void setMakeCertification(Boolean makeCertification) {
        this.makeCertification = makeCertification;
    }

    public Boolean getMultipleTransportable() {
        return this.multipleTransportable;
    }

    public Draft multipleTransportable(Boolean multipleTransportable) {
        this.setMultipleTransportable(multipleTransportable);
        return this;
    }

    public void setMultipleTransportable(Boolean multipleTransportable) {
        this.multipleTransportable = multipleTransportable;
    }

    public String getOrderRegistrationDate() {
        return this.orderRegistrationDate;
    }

    public Draft orderRegistrationDate(String orderRegistrationDate) {
        this.setOrderRegistrationDate(orderRegistrationDate);
        return this;
    }

    public void setOrderRegistrationDate(String orderRegistrationDate) {
        this.orderRegistrationDate = orderRegistrationDate;
    }

    public String getOrderRegistrationExpiryDate() {
        return this.orderRegistrationExpiryDate;
    }

    public Draft orderRegistrationExpiryDate(String orderRegistrationExpiryDate) {
        this.setOrderRegistrationExpiryDate(orderRegistrationExpiryDate);
        return this;
    }

    public void setOrderRegistrationExpiryDate(String orderRegistrationExpiryDate) {
        this.orderRegistrationExpiryDate = orderRegistrationExpiryDate;
    }

    public String getOrderRegistrationNumber() {
        return this.orderRegistrationNumber;
    }

    public Draft orderRegistrationNumber(String orderRegistrationNumber) {
        this.setOrderRegistrationNumber(orderRegistrationNumber);
        return this;
    }

    public void setOrderRegistrationNumber(String orderRegistrationNumber) {
        this.orderRegistrationNumber = orderRegistrationNumber;
    }

    public BigDecimal getOtherCost() {
        return this.otherCost;
    }

    public Draft otherCost(BigDecimal otherCost) {
        this.setOtherCost(otherCost);
        return this;
    }

    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    public BigDecimal getPaymentAmount() {
        return this.paymentAmount;
    }

    public Draft paymentAmount(BigDecimal paymentAmount) {
        this.setPaymentAmount(paymentAmount);
        return this;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPerformaDate() {
        return this.performaDate;
    }

    public Draft performaDate(String performaDate) {
        this.setPerformaDate(performaDate);
        return this;
    }

    public void setPerformaDate(String performaDate) {
        this.performaDate = performaDate;
    }

    public String getPerformaExpiryDate() {
        return this.performaExpiryDate;
    }

    public Draft performaExpiryDate(String performaExpiryDate) {
        this.setPerformaExpiryDate(performaExpiryDate);
        return this;
    }

    public void setPerformaExpiryDate(String performaExpiryDate) {
        this.performaExpiryDate = performaExpiryDate;
    }

    public String getPerformaNumber() {
        return this.performaNumber;
    }

    public Draft performaNumber(String performaNumber) {
        this.setPerformaNumber(performaNumber);
        return this;
    }

    public void setPerformaNumber(String performaNumber) {
        this.performaNumber = performaNumber;
    }

    public BigDecimal getPostSwiftCost() {
        return this.postSwiftCost;
    }

    public Draft postSwiftCost(BigDecimal postSwiftCost) {
        this.setPostSwiftCost(postSwiftCost);
        return this;
    }

    public void setPostSwiftCost(BigDecimal postSwiftCost) {
        this.postSwiftCost = postSwiftCost;
    }

    public Boolean getProductSourceChangeable() {
        return this.productSourceChangeable;
    }

    public Draft productSourceChangeable(Boolean productSourceChangeable) {
        this.setProductSourceChangeable(productSourceChangeable);
        return this;
    }

    public void setProductSourceChangeable(Boolean productSourceChangeable) {
        this.productSourceChangeable = productSourceChangeable;
    }

    public Boolean getReceiveAllCommission() {
        return this.receiveAllCommission;
    }

    public Draft receiveAllCommission(Boolean receiveAllCommission) {
        this.setReceiveAllCommission(receiveAllCommission);
        return this;
    }

    public void setReceiveAllCommission(Boolean receiveAllCommission) {
        this.receiveAllCommission = receiveAllCommission;
    }

    public BigDecimal getRegisterationJustificationAmount() {
        return this.registerationJustificationAmount;
    }

    public Draft registerationJustificationAmount(BigDecimal registerationJustificationAmount) {
        this.setRegisterationJustificationAmount(registerationJustificationAmount);
        return this;
    }

    public void setRegisterationJustificationAmount(BigDecimal registerationJustificationAmount) {
        this.registerationJustificationAmount = registerationJustificationAmount;
    }

    public String getRequestDate() {
        return this.requestDate;
    }

    public Draft requestDate(String requestDate) {
        this.setRequestDate(requestDate);
        return this;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getSanctionSerial() {
        return this.sanctionSerial;
    }

    public Draft sanctionSerial(String sanctionSerial) {
        this.setSanctionSerial(sanctionSerial);
        return this;
    }

    public void setSanctionSerial(String sanctionSerial) {
        this.sanctionSerial = sanctionSerial;
    }

    public Long getSerial() {
        return this.serial;
    }

    public Draft serial(Long serial) {
        this.setSerial(serial);
        return this;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public BigDecimal getShipmentCost() {
        return this.shipmentCost;
    }

    public Draft shipmentCost(BigDecimal shipmentCost) {
        this.setShipmentCost(shipmentCost);
        return this;
    }

    public void setShipmentCost(BigDecimal shipmentCost) {
        this.shipmentCost = shipmentCost;
    }

    public String getSourceTransportPlace() {
        return this.sourceTransportPlace;
    }

    public Draft sourceTransportPlace(String sourceTransportPlace) {
        this.setSourceTransportPlace(sourceTransportPlace);
        return this;
    }

    public void setSourceTransportPlace(String sourceTransportPlace) {
        this.sourceTransportPlace = sourceTransportPlace;
    }

    public String getSwiftComment() {
        return this.swiftComment;
    }

    public Draft swiftComment(String swiftComment) {
        this.setSwiftComment(swiftComment);
        return this;
    }

    public void setSwiftComment(String swiftComment) {
        this.swiftComment = swiftComment;
    }

    public BigDecimal getTransferAmount() {
        return this.transferAmount;
    }

    public Draft transferAmount(BigDecimal transferAmount) {
        this.setTransferAmount(transferAmount);
        return this;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Boolean getTransportVehicleChangeable() {
        return this.transportVehicleChangeable;
    }

    public Draft transportVehicleChangeable(Boolean transportVehicleChangeable) {
        this.setTransportVehicleChangeable(transportVehicleChangeable);
        return this;
    }

    public void setTransportVehicleChangeable(Boolean transportVehicleChangeable) {
        this.transportVehicleChangeable = transportVehicleChangeable;
    }

    public String getPaymentTool() {
        return this.paymentTool;
    }

    public Draft paymentTool(String paymentTool) {
        this.setPaymentTool(paymentTool);
        return this;
    }

    public void setPaymentTool(String paymentTool) {
        this.paymentTool = paymentTool;
    }

    public String getLetterNumberTazirat() {
        return this.letterNumberTazirat;
    }

    public Draft letterNumberTazirat(String letterNumberTazirat) {
        this.setLetterNumberTazirat(letterNumberTazirat);
        return this;
    }

    public void setLetterNumberTazirat(String letterNumberTazirat) {
        this.letterNumberTazirat = letterNumberTazirat;
    }

    public String getLetterDateTazirat() {
        return this.letterDateTazirat;
    }

    public Draft letterDateTazirat(String letterDateTazirat) {
        this.setLetterDateTazirat(letterDateTazirat);
        return this;
    }

    public void setLetterDateTazirat(String letterDateTazirat) {
        this.letterDateTazirat = letterDateTazirat;
    }

    public String getLoanNumber() {
        return this.loanNumber;
    }

    public Draft loanNumber(String loanNumber) {
        this.setLoanNumber(loanNumber);
        return this;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Boolean getIsMigrational() {
        return this.isMigrational;
    }

    public Draft isMigrational(Boolean isMigrational) {
        this.setIsMigrational(isMigrational);
        return this;
    }

    public void setIsMigrational(Boolean isMigrational) {
        this.isMigrational = isMigrational;
    }

    public Boolean getIsNewCertificate() {
        return this.isNewCertificate;
    }

    public Draft isNewCertificate(Boolean isNewCertificate) {
        this.setIsNewCertificate(isNewCertificate);
        return this;
    }

    public void setIsNewCertificate(Boolean isNewCertificate) {
        this.isNewCertificate = isNewCertificate;
    }

    public Boolean getIsWithoutPayment() {
        return this.isWithoutPayment;
    }

    public Draft isWithoutPayment(Boolean isWithoutPayment) {
        this.setIsWithoutPayment(isWithoutPayment);
        return this;
    }

    public void setIsWithoutPayment(Boolean isWithoutPayment) {
        this.isWithoutPayment = isWithoutPayment;
    }

    public String getDestinationCountryCode() {
        return this.destinationCountryCode;
    }

    public Draft destinationCountryCode(String destinationCountryCode) {
        this.setDestinationCountryCode(destinationCountryCode);
        return this;
    }

    public void setDestinationCountryCode(String destinationCountryCode) {
        this.destinationCountryCode = destinationCountryCode;
    }

    public String getBeneficiaryCountryCode() {
        return this.beneficiaryCountryCode;
    }

    public Draft beneficiaryCountryCode(String beneficiaryCountryCode) {
        this.setBeneficiaryCountryCode(beneficiaryCountryCode);
        return this;
    }

    public void setBeneficiaryCountryCode(String beneficiaryCountryCode) {
        this.beneficiaryCountryCode = beneficiaryCountryCode;
    }

    public String getProducerCountryCode() {
        return this.producerCountryCode;
    }

    public Draft producerCountryCode(String producerCountryCode) {
        this.setProducerCountryCode(producerCountryCode);
        return this;
    }

    public void setProducerCountryCode(String producerCountryCode) {
        this.producerCountryCode = producerCountryCode;
    }

    public String getBranchCode() {
        return this.branchCode;
    }

    public Draft branchCode(String branchCode) {
        this.setBranchCode(branchCode);
        return this;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getOperationalBranchCode() {
        return this.operationalBranchCode;
    }

    public Draft operationalBranchCode(String operationalBranchCode) {
        this.setOperationalBranchCode(operationalBranchCode);
        return this;
    }

    public void setOperationalBranchCode(String operationalBranchCode) {
        this.operationalBranchCode = operationalBranchCode;
    }

    public String getCertificateSenderBranchCode() {
        return this.certificateSenderBranchCode;
    }

    public Draft certificateSenderBranchCode(String certificateSenderBranchCode) {
        this.setCertificateSenderBranchCode(certificateSenderBranchCode);
        return this;
    }

    public void setCertificateSenderBranchCode(String certificateSenderBranchCode) {
        this.certificateSenderBranchCode = certificateSenderBranchCode;
    }

    public String getMainAccountCurrencyCode() {
        return this.mainAccountCurrencyCode;
    }

    public Draft mainAccountCurrencyCode(String mainAccountCurrencyCode) {
        this.setMainAccountCurrencyCode(mainAccountCurrencyCode);
        return this;
    }

    public void setMainAccountCurrencyCode(String mainAccountCurrencyCode) {
        this.mainAccountCurrencyCode = mainAccountCurrencyCode;
    }

    public String getOrderRegCurrencyCode() {
        return this.orderRegCurrencyCode;
    }

    public Draft orderRegCurrencyCode(String orderRegCurrencyCode) {
        this.setOrderRegCurrencyCode(orderRegCurrencyCode);
        return this;
    }

    public void setOrderRegCurrencyCode(String orderRegCurrencyCode) {
        this.orderRegCurrencyCode = orderRegCurrencyCode;
    }

    public String getChargedExchangeBrokerCurrency() {
        return this.chargedExchangeBrokerCurrency;
    }

    public Draft chargedExchangeBrokerCurrency(String chargedExchangeBrokerCurrency) {
        this.setChargedExchangeBrokerCurrency(chargedExchangeBrokerCurrency);
        return this;
    }

    public void setChargedExchangeBrokerCurrency(String chargedExchangeBrokerCurrency) {
        this.chargedExchangeBrokerCurrency = chargedExchangeBrokerCurrency;
    }

    public String getRegisterationJustificationCurrencyCode() {
        return this.registerationJustificationCurrencyCode;
    }

    public Draft registerationJustificationCurrencyCode(String registerationJustificationCurrencyCode) {
        this.setRegisterationJustificationCurrencyCode(registerationJustificationCurrencyCode);
        return this;
    }

    public void setRegisterationJustificationCurrencyCode(String registerationJustificationCurrencyCode) {
        this.registerationJustificationCurrencyCode = registerationJustificationCurrencyCode;
    }

    public String getCurrencyExchangeInfoTitle() {
        return this.currencyExchangeInfoTitle;
    }

    public Draft currencyExchangeInfoTitle(String currencyExchangeInfoTitle) {
        this.setCurrencyExchangeInfoTitle(currencyExchangeInfoTitle);
        return this;
    }

    public void setCurrencyExchangeInfoTitle(String currencyExchangeInfoTitle) {
        this.currencyExchangeInfoTitle = currencyExchangeInfoTitle;
    }

    public String getTransportationTypeName() {
        return this.transportationTypeName;
    }

    public Draft transportationTypeName(String transportationTypeName) {
        this.setTransportationTypeName(transportationTypeName);
        return this;
    }

    public void setTransportationTypeName(String transportationTypeName) {
        this.transportationTypeName = transportationTypeName;
    }

    public String getAccountInfoCode() {
        return this.accountInfoCode;
    }

    public Draft accountInfoCode(String accountInfoCode) {
        this.setAccountInfoCode(accountInfoCode);
        return this;
    }

    public void setAccountInfoCode(String accountInfoCode) {
        this.accountInfoCode = accountInfoCode;
    }

    public Long getCustomerNumbers() {
        return this.customerNumbers;
    }

    public Draft customerNumbers(Long customerNumbers) {
        this.setCustomerNumbers(customerNumbers);
        return this;
    }

    public void setCustomerNumbers(Long customerNumbers) {
        this.customerNumbers = customerNumbers;
    }

    public String getSanctionSerials() {
        return this.sanctionSerials;
    }

    public Draft sanctionSerials(String sanctionSerials) {
        this.setSanctionSerials(sanctionSerials);
        return this;
    }

    public void setSanctionSerials(String sanctionSerials) {
        this.sanctionSerials = sanctionSerials;
    }

    public Set<DraftReceipt> getDraftReceipts() {
        return this.draftReceipts;
    }

    public void setDraftReceipts(Set<DraftReceipt> draftReceipts) {
        if (this.draftReceipts != null) {
            this.draftReceipts.forEach(i -> i.setReceipts(null));
        }
        if (draftReceipts != null) {
            draftReceipts.forEach(i -> i.setReceipts(this));
        }
        this.draftReceipts = draftReceipts;
    }

    public Draft draftReceipts(Set<DraftReceipt> draftReceipts) {
        this.setDraftReceipts(draftReceipts);
        return this;
    }

    public Draft addDraftReceipt(DraftReceipt draftReceipt) {
        this.draftReceipts.add(draftReceipt);
        draftReceipt.setReceipts(this);
        return this;
    }

    public Draft removeDraftReceipt(DraftReceipt draftReceipt) {
        this.draftReceipts.remove(draftReceipt);
        draftReceipt.setReceipts(null);
        return this;
    }

    public Set<DraftUsedAssurance> getDraftUsedAssurances() {
        return this.draftUsedAssurances;
    }

    public void setDraftUsedAssurances(Set<DraftUsedAssurance> draftUsedAssurances) {
        if (this.draftUsedAssurances != null) {
            this.draftUsedAssurances.forEach(i -> i.setUsedAssurances(null));
        }
        if (draftUsedAssurances != null) {
            draftUsedAssurances.forEach(i -> i.setUsedAssurances(this));
        }
        this.draftUsedAssurances = draftUsedAssurances;
    }

    public Draft draftUsedAssurances(Set<DraftUsedAssurance> draftUsedAssurances) {
        this.setDraftUsedAssurances(draftUsedAssurances);
        return this;
    }

    public Draft addDraftUsedAssurance(DraftUsedAssurance draftUsedAssurance) {
        this.draftUsedAssurances.add(draftUsedAssurance);
        draftUsedAssurance.setUsedAssurances(this);
        return this;
    }

    public Draft removeDraftUsedAssurance(DraftUsedAssurance draftUsedAssurance) {
        this.draftUsedAssurances.remove(draftUsedAssurance);
        draftUsedAssurance.setUsedAssurances(null);
        return this;
    }

    public Set<DraftFactor> getDraftFactors() {
        return this.draftFactors;
    }

    public void setDraftFactors(Set<DraftFactor> draftFactors) {
        if (this.draftFactors != null) {
            this.draftFactors.forEach(i -> i.setDraftFactors(null));
        }
        if (draftFactors != null) {
            draftFactors.forEach(i -> i.setDraftFactors(this));
        }
        this.draftFactors = draftFactors;
    }

    public Draft draftFactors(Set<DraftFactor> draftFactors) {
        this.setDraftFactors(draftFactors);
        return this;
    }

    public Draft addDraftFactor(DraftFactor draftFactor) {
        this.draftFactors.add(draftFactor);
        draftFactor.setDraftFactors(this);
        return this;
    }

    public Draft removeDraftFactor(DraftFactor draftFactor) {
        this.draftFactors.remove(draftFactor);
        draftFactor.setDraftFactors(null);
        return this;
    }

    public Set<DraftCustomJustification> getDraftCustomJustifications() {
        return this.draftCustomJustifications;
    }

    public void setDraftCustomJustifications(Set<DraftCustomJustification> draftCustomJustifications) {
        if (this.draftCustomJustifications != null) {
            this.draftCustomJustifications.forEach(i -> i.setDraftJustifications(null));
        }
        if (draftCustomJustifications != null) {
            draftCustomJustifications.forEach(i -> i.setDraftJustifications(this));
        }
        this.draftCustomJustifications = draftCustomJustifications;
    }

    public Draft draftCustomJustifications(Set<DraftCustomJustification> draftCustomJustifications) {
        this.setDraftCustomJustifications(draftCustomJustifications);
        return this;
    }

    public Draft addDraftCustomJustification(DraftCustomJustification draftCustomJustification) {
        this.draftCustomJustifications.add(draftCustomJustification);
        draftCustomJustification.setDraftJustifications(this);
        return this;
    }

    public Draft removeDraftCustomJustification(DraftCustomJustification draftCustomJustification) {
        this.draftCustomJustifications.remove(draftCustomJustification);
        draftCustomJustification.setDraftJustifications(null);
        return this;
    }

    public Set<DraftExtend> getDraftExtends() {
        return this.draftExtends;
    }

    public void setDraftExtends(Set<DraftExtend> draftExtends) {
        if (this.draftExtends != null) {
            this.draftExtends.forEach(i -> i.setExtensions(null));
        }
        if (draftExtends != null) {
            draftExtends.forEach(i -> i.setExtensions(this));
        }
        this.draftExtends = draftExtends;
    }

    public Draft draftExtends(Set<DraftExtend> draftExtends) {
        this.setDraftExtends(draftExtends);
        return this;
    }

    public Draft addDraftExtend(DraftExtend draftExtend) {
        this.draftExtends.add(draftExtend);
        draftExtend.setExtensions(this);
        return this;
    }

    public Draft removeDraftExtend(DraftExtend draftExtend) {
        this.draftExtends.remove(draftExtend);
        draftExtend.setExtensions(null);
        return this;
    }

    public Set<DraftTax> getDraftTaxes() {
        return this.draftTaxes;
    }

    public void setDraftTaxes(Set<DraftTax> draftTaxes) {
        if (this.draftTaxes != null) {
            this.draftTaxes.forEach(i -> i.setTaxes(null));
        }
        if (draftTaxes != null) {
            draftTaxes.forEach(i -> i.setTaxes(this));
        }
        this.draftTaxes = draftTaxes;
    }

    public Draft draftTaxes(Set<DraftTax> draftTaxes) {
        this.setDraftTaxes(draftTaxes);
        return this;
    }

    public Draft addDraftTax(DraftTax draftTax) {
        this.draftTaxes.add(draftTax);
        draftTax.setTaxes(this);
        return this;
    }

    public Draft removeDraftTax(DraftTax draftTax) {
        this.draftTaxes.remove(draftTax);
        draftTax.setTaxes(null);
        return this;
    }

    public Set<Custom> getCustoms() {
        return this.customs;
    }

    public void setCustoms(Set<Custom> customs) {
        if (this.customs != null) {
            this.customs.forEach(i -> i.removeDraft(this));
        }
        if (customs != null) {
            customs.forEach(i -> i.addDraft(this));
        }
        this.customs = customs;
    }

    public Draft customs(Set<Custom> customs) {
        this.setCustoms(customs);
        return this;
    }

    public Draft addCustom(Custom custom) {
        this.customs.add(custom);
        custom.getDrafts().add(this);
        return this;
    }

    public Draft removeCustom(Custom custom) {
        this.customs.remove(custom);
        custom.getDrafts().remove(this);
        return this;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        if (this.products != null) {
            this.products.forEach(i -> i.removeDraft(this));
        }
        if (products != null) {
            products.forEach(i -> i.addDraft(this));
        }
        this.products = products;
    }

    public Draft products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public Draft addProducts(Product product) {
        this.products.add(product);
        product.getDrafts().add(this);
        return this;
    }

    public Draft removeProducts(Product product) {
        this.products.remove(product);
        product.getDrafts().remove(this);
        return this;
    }

    public Set<ServiceTariff> getServices() {
        return this.services;
    }

    public void setServices(Set<ServiceTariff> serviceTariffs) {
        if (this.services != null) {
            this.services.forEach(i -> i.removeDraft(this));
        }
        if (serviceTariffs != null) {
            serviceTariffs.forEach(i -> i.addDraft(this));
        }
        this.services = serviceTariffs;
    }

    public Draft services(Set<ServiceTariff> serviceTariffs) {
        this.setServices(serviceTariffs);
        return this;
    }

    public Draft addServices(ServiceTariff serviceTariff) {
        this.services.add(serviceTariff);
        serviceTariff.getDrafts().add(this);
        return this;
    }

    public Draft removeServices(ServiceTariff serviceTariff) {
        this.services.remove(serviceTariff);
        serviceTariff.getDrafts().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Draft)) {
            return false;
        }
        return getId() != null && getId().equals(((Draft) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Draft{" +
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
            ", destinationCountryCode='" + getDestinationCountryCode() + "'" +
            ", beneficiaryCountryCode='" + getBeneficiaryCountryCode() + "'" +
            ", producerCountryCode='" + getProducerCountryCode() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            ", operationalBranchCode='" + getOperationalBranchCode() + "'" +
            ", certificateSenderBranchCode='" + getCertificateSenderBranchCode() + "'" +
            ", mainAccountCurrencyCode='" + getMainAccountCurrencyCode() + "'" +
            ", orderRegCurrencyCode='" + getOrderRegCurrencyCode() + "'" +
            ", chargedExchangeBrokerCurrency='" + getChargedExchangeBrokerCurrency() + "'" +
            ", registerationJustificationCurrencyCode='" + getRegisterationJustificationCurrencyCode() + "'" +
            ", currencyExchangeInfoTitle='" + getCurrencyExchangeInfoTitle() + "'" +
            ", transportationTypeName='" + getTransportationTypeName() + "'" +
            ", accountInfoCode='" + getAccountInfoCode() + "'" +
            ", customerNumbers=" + getCustomerNumbers() +
            ", sanctionSerials='" + getSanctionSerials() + "'" +
            "}";
    }
}
