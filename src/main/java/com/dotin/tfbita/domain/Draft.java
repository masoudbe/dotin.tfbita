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

    @Column(name = "main_account_currency_code")
    private String mainAccountCurrencyCode;

    @Column(name = "order_reg_currency_code")
    private String orderRegCurrencyCode;

    @Column(name = "charged_exchange_broker_currency_code")
    private String chargedExchangeBrokerCurrencyCode;

    @Column(name = "destination_country_code")
    private String destinationCountryCode;

    @Column(name = "beneficiary_country_code")
    private String beneficiaryCountryCode;

    @Column(name = "producer_country_code")
    private String producerCountryCode;

    @Column(name = "registeration_justification_currency_code")
    private String registerationJustificationCurrencyCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "operational_branch_code")
    private String operationalBranchCode;

    @Column(name = "certificate_sender_branch_code")
    private String certificateSenderBranchCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draft")
    @JsonIgnoreProperties(
        value = {
            "draftProductInfos",
            "productDimension",
            "stateOfDocuments",
            "currencyProvisionFileType",
            "paymentCurrencyRateType",
            "paymentItem",
            "documentTransactionContainer",
            "draft",
            "draftCustomJustifications",
        },
        allowSetters = true
    )
    private Set<DraftReceipt> receipts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draft")
    @JsonIgnoreProperties(value = { "documentTransaction", "returnDocumentTransaction", "draft" }, allowSetters = true)
    private Set<DraftTax> taxes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draftField")
    @JsonIgnoreProperties(value = { "draftField" }, allowSetters = true)
    private Set<DraftExtend> extensions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draft")
    @JsonIgnoreProperties(value = { "draft" }, allowSetters = true)
    private Set<DraftFactor> draftFactors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draft")
    @JsonIgnoreProperties(value = { "draft" }, allowSetters = true)
    private Set<DraftUsedAssurance> usedAssurances = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draft")
    @JsonIgnoreProperties(value = { "draftReceipts", "draft" }, allowSetters = true)
    private Set<DraftCustomJustification> draftJustifications = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement chargedExchangeBroker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement insuranceLetterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement advisorDepositType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement interfaceAdvisorDepositType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement coveringAdvisorDepositType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement impartType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement dealType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement transportVehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement freightLetterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement actionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement ownershipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement currencyContainerPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement draftSource;

    @ManyToOne(fetch = FetchType.LAZY)
    private Custom loadSwitchPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "type",
            "secondaryType",
            "division",
            "topicInfo",
            "conditionInfo",
            "accountInfo",
            "requestType",
            "acceptableProductTypes",
            "userGroups",
        },
        allowSetters = true
    )
    private DraftType draftType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "status" }, allowSetters = true)
    private DraftStatusInfo statusInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private InsuranceCompanyInfo insuranceCompanyInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "additionalBrokerInformation",
            "advisorDeposits",
            "defaultVostroDeposit",
            "defaultNostroDeposit",
            "receiveMethod",
            "payMethod",
            "swiftBic",
        },
        allowSetters = true
    )
    private AdvisorDefinition advisingBank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "additionalBrokerInformation",
            "advisorDeposits",
            "defaultVostroDeposit",
            "defaultNostroDeposit",
            "receiveMethod",
            "payMethod",
            "swiftBic",
        },
        allowSetters = true
    )
    private AdvisorDefinition interfaceAdvisingBank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "additionalBrokerInformation",
            "advisorDeposits",
            "defaultVostroDeposit",
            "defaultNostroDeposit",
            "receiveMethod",
            "payMethod",
            "swiftBic",
        },
        allowSetters = true
    )
    private AdvisorDefinition coveringBank;

    @ManyToOne(fetch = FetchType.LAZY)
    private AuditCompanyInfo auditCompanyInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportationType transportType;

    @ManyToOne(fetch = FetchType.LAZY)
    private CurrencyExchangeInfo currencyExchangeInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private DraftAccountInfo accountInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Custom destinationCustomCompanies;

    @ManyToOne(fetch = FetchType.LAZY)
    private Custom sourceCustomCompanies;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_draft__services",
        joinColumns = @JoinColumn(name = "draft_id"),
        inverseJoinColumns = @JoinColumn(name = "services_id")
    )
    @JsonIgnoreProperties(value = { "drafts" }, allowSetters = true)
    private Set<ServiceTariff> services = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_draft__products",
        joinColumns = @JoinColumn(name = "draft_id"),
        inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    @JsonIgnoreProperties(
        value = { "attributeValues", "productType", "orderRegistrationInfos", "drafts", "customJustifications" },
        allowSetters = true
    )
    private Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_draft__sanction_serials",
        joinColumns = @JoinColumn(name = "draft_id"),
        inverseJoinColumns = @JoinColumn(name = "sanction_serials_id")
    )
    @JsonIgnoreProperties(value = { "orderRegistrationInfos", "drafts", "draftTypes" }, allowSetters = true)
    private Set<StringValue> sanctionSerials = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_draft__customer_numbers",
        joinColumns = @JoinColumn(name = "draft_id"),
        inverseJoinColumns = @JoinColumn(name = "customer_numbers_id")
    )
    @JsonIgnoreProperties(value = { "drafts" }, allowSetters = true)
    private Set<LongValue> customerNumbers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_draft__suggested_sanctions",
        joinColumns = @JoinColumn(name = "draft_id"),
        inverseJoinColumns = @JoinColumn(name = "suggested_sanctions_id")
    )
    @JsonIgnoreProperties(value = { "drafts" }, allowSetters = true)
    private Set<SuggestedSanctionInfo> suggestedSanctions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_draft__document_transaction_container",
        joinColumns = @JoinColumn(name = "draft_id"),
        inverseJoinColumns = @JoinColumn(name = "document_transaction_container_id")
    )
    @JsonIgnoreProperties(
        value = {
            "receipts",
            "otherDocumentTransactions",
            "canceledJustificationDocumentTransactions",
            "justificationDocumentTransactions",
            "receivedCommisions",
            "canceledDocumentTransactions",
            "returnedDefaultCurrencyCostsDocumentTransactions",
            "defaultCurrencyCostsDocumentTransactions",
            "issueCommissionDocumentTransaction",
            "paymentDocumentTransaction",
            "settleDocumentTransaction",
            "settleExcessDocumentTransaction",
            "commissionDeleteDraftDocumentTransaction",
            "commissionDraftExtendDocumentTransaction",
            "drafts",
        },
        allowSetters = true
    )
    private Set<DraftDocumentTransactionContainer> documentTransactionContainers = new HashSet<>();

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

    public String getChargedExchangeBrokerCurrencyCode() {
        return this.chargedExchangeBrokerCurrencyCode;
    }

    public Draft chargedExchangeBrokerCurrencyCode(String chargedExchangeBrokerCurrencyCode) {
        this.setChargedExchangeBrokerCurrencyCode(chargedExchangeBrokerCurrencyCode);
        return this;
    }

    public void setChargedExchangeBrokerCurrencyCode(String chargedExchangeBrokerCurrencyCode) {
        this.chargedExchangeBrokerCurrencyCode = chargedExchangeBrokerCurrencyCode;
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

    public Set<DraftReceipt> getReceipts() {
        return this.receipts;
    }

    public void setReceipts(Set<DraftReceipt> draftReceipts) {
        if (this.receipts != null) {
            this.receipts.forEach(i -> i.setDraft(null));
        }
        if (draftReceipts != null) {
            draftReceipts.forEach(i -> i.setDraft(this));
        }
        this.receipts = draftReceipts;
    }

    public Draft receipts(Set<DraftReceipt> draftReceipts) {
        this.setReceipts(draftReceipts);
        return this;
    }

    public Draft addReceipts(DraftReceipt draftReceipt) {
        this.receipts.add(draftReceipt);
        draftReceipt.setDraft(this);
        return this;
    }

    public Draft removeReceipts(DraftReceipt draftReceipt) {
        this.receipts.remove(draftReceipt);
        draftReceipt.setDraft(null);
        return this;
    }

    public Set<DraftTax> getTaxes() {
        return this.taxes;
    }

    public void setTaxes(Set<DraftTax> draftTaxes) {
        if (this.taxes != null) {
            this.taxes.forEach(i -> i.setDraft(null));
        }
        if (draftTaxes != null) {
            draftTaxes.forEach(i -> i.setDraft(this));
        }
        this.taxes = draftTaxes;
    }

    public Draft taxes(Set<DraftTax> draftTaxes) {
        this.setTaxes(draftTaxes);
        return this;
    }

    public Draft addTaxes(DraftTax draftTax) {
        this.taxes.add(draftTax);
        draftTax.setDraft(this);
        return this;
    }

    public Draft removeTaxes(DraftTax draftTax) {
        this.taxes.remove(draftTax);
        draftTax.setDraft(null);
        return this;
    }

    public Set<DraftExtend> getExtensions() {
        return this.extensions;
    }

    public void setExtensions(Set<DraftExtend> draftExtends) {
        if (this.extensions != null) {
            this.extensions.forEach(i -> i.setDraftField(null));
        }
        if (draftExtends != null) {
            draftExtends.forEach(i -> i.setDraftField(this));
        }
        this.extensions = draftExtends;
    }

    public Draft extensions(Set<DraftExtend> draftExtends) {
        this.setExtensions(draftExtends);
        return this;
    }

    public Draft addExtensions(DraftExtend draftExtend) {
        this.extensions.add(draftExtend);
        draftExtend.setDraftField(this);
        return this;
    }

    public Draft removeExtensions(DraftExtend draftExtend) {
        this.extensions.remove(draftExtend);
        draftExtend.setDraftField(null);
        return this;
    }

    public Set<DraftFactor> getDraftFactors() {
        return this.draftFactors;
    }

    public void setDraftFactors(Set<DraftFactor> draftFactors) {
        if (this.draftFactors != null) {
            this.draftFactors.forEach(i -> i.setDraft(null));
        }
        if (draftFactors != null) {
            draftFactors.forEach(i -> i.setDraft(this));
        }
        this.draftFactors = draftFactors;
    }

    public Draft draftFactors(Set<DraftFactor> draftFactors) {
        this.setDraftFactors(draftFactors);
        return this;
    }

    public Draft addDraftFactors(DraftFactor draftFactor) {
        this.draftFactors.add(draftFactor);
        draftFactor.setDraft(this);
        return this;
    }

    public Draft removeDraftFactors(DraftFactor draftFactor) {
        this.draftFactors.remove(draftFactor);
        draftFactor.setDraft(null);
        return this;
    }

    public Set<DraftUsedAssurance> getUsedAssurances() {
        return this.usedAssurances;
    }

    public void setUsedAssurances(Set<DraftUsedAssurance> draftUsedAssurances) {
        if (this.usedAssurances != null) {
            this.usedAssurances.forEach(i -> i.setDraft(null));
        }
        if (draftUsedAssurances != null) {
            draftUsedAssurances.forEach(i -> i.setDraft(this));
        }
        this.usedAssurances = draftUsedAssurances;
    }

    public Draft usedAssurances(Set<DraftUsedAssurance> draftUsedAssurances) {
        this.setUsedAssurances(draftUsedAssurances);
        return this;
    }

    public Draft addUsedAssurances(DraftUsedAssurance draftUsedAssurance) {
        this.usedAssurances.add(draftUsedAssurance);
        draftUsedAssurance.setDraft(this);
        return this;
    }

    public Draft removeUsedAssurances(DraftUsedAssurance draftUsedAssurance) {
        this.usedAssurances.remove(draftUsedAssurance);
        draftUsedAssurance.setDraft(null);
        return this;
    }

    public Set<DraftCustomJustification> getDraftJustifications() {
        return this.draftJustifications;
    }

    public void setDraftJustifications(Set<DraftCustomJustification> draftCustomJustifications) {
        if (this.draftJustifications != null) {
            this.draftJustifications.forEach(i -> i.setDraft(null));
        }
        if (draftCustomJustifications != null) {
            draftCustomJustifications.forEach(i -> i.setDraft(this));
        }
        this.draftJustifications = draftCustomJustifications;
    }

    public Draft draftJustifications(Set<DraftCustomJustification> draftCustomJustifications) {
        this.setDraftJustifications(draftCustomJustifications);
        return this;
    }

    public Draft addDraftJustifications(DraftCustomJustification draftCustomJustification) {
        this.draftJustifications.add(draftCustomJustification);
        draftCustomJustification.setDraft(this);
        return this;
    }

    public Draft removeDraftJustifications(DraftCustomJustification draftCustomJustification) {
        this.draftJustifications.remove(draftCustomJustification);
        draftCustomJustification.setDraft(null);
        return this;
    }

    public CategoryElement getChargedExchangeBroker() {
        return this.chargedExchangeBroker;
    }

    public void setChargedExchangeBroker(CategoryElement categoryElement) {
        this.chargedExchangeBroker = categoryElement;
    }

    public Draft chargedExchangeBroker(CategoryElement categoryElement) {
        this.setChargedExchangeBroker(categoryElement);
        return this;
    }

    public CategoryElement getInsuranceLetterType() {
        return this.insuranceLetterType;
    }

    public void setInsuranceLetterType(CategoryElement categoryElement) {
        this.insuranceLetterType = categoryElement;
    }

    public Draft insuranceLetterType(CategoryElement categoryElement) {
        this.setInsuranceLetterType(categoryElement);
        return this;
    }

    public CategoryElement getAdvisorDepositType() {
        return this.advisorDepositType;
    }

    public void setAdvisorDepositType(CategoryElement categoryElement) {
        this.advisorDepositType = categoryElement;
    }

    public Draft advisorDepositType(CategoryElement categoryElement) {
        this.setAdvisorDepositType(categoryElement);
        return this;
    }

    public CategoryElement getInterfaceAdvisorDepositType() {
        return this.interfaceAdvisorDepositType;
    }

    public void setInterfaceAdvisorDepositType(CategoryElement categoryElement) {
        this.interfaceAdvisorDepositType = categoryElement;
    }

    public Draft interfaceAdvisorDepositType(CategoryElement categoryElement) {
        this.setInterfaceAdvisorDepositType(categoryElement);
        return this;
    }

    public CategoryElement getCoveringAdvisorDepositType() {
        return this.coveringAdvisorDepositType;
    }

    public void setCoveringAdvisorDepositType(CategoryElement categoryElement) {
        this.coveringAdvisorDepositType = categoryElement;
    }

    public Draft coveringAdvisorDepositType(CategoryElement categoryElement) {
        this.setCoveringAdvisorDepositType(categoryElement);
        return this;
    }

    public CategoryElement getImpartType() {
        return this.impartType;
    }

    public void setImpartType(CategoryElement categoryElement) {
        this.impartType = categoryElement;
    }

    public Draft impartType(CategoryElement categoryElement) {
        this.setImpartType(categoryElement);
        return this;
    }

    public CategoryElement getDealType() {
        return this.dealType;
    }

    public void setDealType(CategoryElement categoryElement) {
        this.dealType = categoryElement;
    }

    public Draft dealType(CategoryElement categoryElement) {
        this.setDealType(categoryElement);
        return this;
    }

    public CategoryElement getTransportVehicleType() {
        return this.transportVehicleType;
    }

    public void setTransportVehicleType(CategoryElement categoryElement) {
        this.transportVehicleType = categoryElement;
    }

    public Draft transportVehicleType(CategoryElement categoryElement) {
        this.setTransportVehicleType(categoryElement);
        return this;
    }

    public CategoryElement getFreightLetterType() {
        return this.freightLetterType;
    }

    public void setFreightLetterType(CategoryElement categoryElement) {
        this.freightLetterType = categoryElement;
    }

    public Draft freightLetterType(CategoryElement categoryElement) {
        this.setFreightLetterType(categoryElement);
        return this;
    }

    public CategoryElement getActionCode() {
        return this.actionCode;
    }

    public void setActionCode(CategoryElement categoryElement) {
        this.actionCode = categoryElement;
    }

    public Draft actionCode(CategoryElement categoryElement) {
        this.setActionCode(categoryElement);
        return this;
    }

    public CategoryElement getOwnershipCode() {
        return this.ownershipCode;
    }

    public void setOwnershipCode(CategoryElement categoryElement) {
        this.ownershipCode = categoryElement;
    }

    public Draft ownershipCode(CategoryElement categoryElement) {
        this.setOwnershipCode(categoryElement);
        return this;
    }

    public CategoryElement getCurrencyContainerPlace() {
        return this.currencyContainerPlace;
    }

    public void setCurrencyContainerPlace(CategoryElement categoryElement) {
        this.currencyContainerPlace = categoryElement;
    }

    public Draft currencyContainerPlace(CategoryElement categoryElement) {
        this.setCurrencyContainerPlace(categoryElement);
        return this;
    }

    public CategoryElement getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(CategoryElement categoryElement) {
        this.paymentType = categoryElement;
    }

    public Draft paymentType(CategoryElement categoryElement) {
        this.setPaymentType(categoryElement);
        return this;
    }

    public CategoryElement getDraftSource() {
        return this.draftSource;
    }

    public void setDraftSource(CategoryElement categoryElement) {
        this.draftSource = categoryElement;
    }

    public Draft draftSource(CategoryElement categoryElement) {
        this.setDraftSource(categoryElement);
        return this;
    }

    public Custom getLoadSwitchPlace() {
        return this.loadSwitchPlace;
    }

    public void setLoadSwitchPlace(Custom custom) {
        this.loadSwitchPlace = custom;
    }

    public Draft loadSwitchPlace(Custom custom) {
        this.setLoadSwitchPlace(custom);
        return this;
    }

    public DraftType getDraftType() {
        return this.draftType;
    }

    public void setDraftType(DraftType draftType) {
        this.draftType = draftType;
    }

    public Draft draftType(DraftType draftType) {
        this.setDraftType(draftType);
        return this;
    }

    public DraftStatusInfo getStatusInfo() {
        return this.statusInfo;
    }

    public void setStatusInfo(DraftStatusInfo draftStatusInfo) {
        this.statusInfo = draftStatusInfo;
    }

    public Draft statusInfo(DraftStatusInfo draftStatusInfo) {
        this.setStatusInfo(draftStatusInfo);
        return this;
    }

    public InsuranceCompanyInfo getInsuranceCompanyInfo() {
        return this.insuranceCompanyInfo;
    }

    public void setInsuranceCompanyInfo(InsuranceCompanyInfo insuranceCompanyInfo) {
        this.insuranceCompanyInfo = insuranceCompanyInfo;
    }

    public Draft insuranceCompanyInfo(InsuranceCompanyInfo insuranceCompanyInfo) {
        this.setInsuranceCompanyInfo(insuranceCompanyInfo);
        return this;
    }

    public AdvisorDefinition getAdvisingBank() {
        return this.advisingBank;
    }

    public void setAdvisingBank(AdvisorDefinition advisorDefinition) {
        this.advisingBank = advisorDefinition;
    }

    public Draft advisingBank(AdvisorDefinition advisorDefinition) {
        this.setAdvisingBank(advisorDefinition);
        return this;
    }

    public AdvisorDefinition getInterfaceAdvisingBank() {
        return this.interfaceAdvisingBank;
    }

    public void setInterfaceAdvisingBank(AdvisorDefinition advisorDefinition) {
        this.interfaceAdvisingBank = advisorDefinition;
    }

    public Draft interfaceAdvisingBank(AdvisorDefinition advisorDefinition) {
        this.setInterfaceAdvisingBank(advisorDefinition);
        return this;
    }

    public AdvisorDefinition getCoveringBank() {
        return this.coveringBank;
    }

    public void setCoveringBank(AdvisorDefinition advisorDefinition) {
        this.coveringBank = advisorDefinition;
    }

    public Draft coveringBank(AdvisorDefinition advisorDefinition) {
        this.setCoveringBank(advisorDefinition);
        return this;
    }

    public AuditCompanyInfo getAuditCompanyInfo() {
        return this.auditCompanyInfo;
    }

    public void setAuditCompanyInfo(AuditCompanyInfo auditCompanyInfo) {
        this.auditCompanyInfo = auditCompanyInfo;
    }

    public Draft auditCompanyInfo(AuditCompanyInfo auditCompanyInfo) {
        this.setAuditCompanyInfo(auditCompanyInfo);
        return this;
    }

    public TransportationType getTransportType() {
        return this.transportType;
    }

    public void setTransportType(TransportationType transportationType) {
        this.transportType = transportationType;
    }

    public Draft transportType(TransportationType transportationType) {
        this.setTransportType(transportationType);
        return this;
    }

    public CurrencyExchangeInfo getCurrencyExchangeInfo() {
        return this.currencyExchangeInfo;
    }

    public void setCurrencyExchangeInfo(CurrencyExchangeInfo currencyExchangeInfo) {
        this.currencyExchangeInfo = currencyExchangeInfo;
    }

    public Draft currencyExchangeInfo(CurrencyExchangeInfo currencyExchangeInfo) {
        this.setCurrencyExchangeInfo(currencyExchangeInfo);
        return this;
    }

    public DraftAccountInfo getAccountInfo() {
        return this.accountInfo;
    }

    public void setAccountInfo(DraftAccountInfo draftAccountInfo) {
        this.accountInfo = draftAccountInfo;
    }

    public Draft accountInfo(DraftAccountInfo draftAccountInfo) {
        this.setAccountInfo(draftAccountInfo);
        return this;
    }

    public Custom getDestinationCustomCompanies() {
        return this.destinationCustomCompanies;
    }

    public void setDestinationCustomCompanies(Custom custom) {
        this.destinationCustomCompanies = custom;
    }

    public Draft destinationCustomCompanies(Custom custom) {
        this.setDestinationCustomCompanies(custom);
        return this;
    }

    public Custom getSourceCustomCompanies() {
        return this.sourceCustomCompanies;
    }

    public void setSourceCustomCompanies(Custom custom) {
        this.sourceCustomCompanies = custom;
    }

    public Draft sourceCustomCompanies(Custom custom) {
        this.setSourceCustomCompanies(custom);
        return this;
    }

    public Set<ServiceTariff> getServices() {
        return this.services;
    }

    public void setServices(Set<ServiceTariff> serviceTariffs) {
        this.services = serviceTariffs;
    }

    public Draft services(Set<ServiceTariff> serviceTariffs) {
        this.setServices(serviceTariffs);
        return this;
    }

    public Draft addServices(ServiceTariff serviceTariff) {
        this.services.add(serviceTariff);
        return this;
    }

    public Draft removeServices(ServiceTariff serviceTariff) {
        this.services.remove(serviceTariff);
        return this;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Draft products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public Draft addProducts(Product product) {
        this.products.add(product);
        return this;
    }

    public Draft removeProducts(Product product) {
        this.products.remove(product);
        return this;
    }

    public Set<StringValue> getSanctionSerials() {
        return this.sanctionSerials;
    }

    public void setSanctionSerials(Set<StringValue> stringValues) {
        this.sanctionSerials = stringValues;
    }

    public Draft sanctionSerials(Set<StringValue> stringValues) {
        this.setSanctionSerials(stringValues);
        return this;
    }

    public Draft addSanctionSerials(StringValue stringValue) {
        this.sanctionSerials.add(stringValue);
        return this;
    }

    public Draft removeSanctionSerials(StringValue stringValue) {
        this.sanctionSerials.remove(stringValue);
        return this;
    }

    public Set<LongValue> getCustomerNumbers() {
        return this.customerNumbers;
    }

    public void setCustomerNumbers(Set<LongValue> longValues) {
        this.customerNumbers = longValues;
    }

    public Draft customerNumbers(Set<LongValue> longValues) {
        this.setCustomerNumbers(longValues);
        return this;
    }

    public Draft addCustomerNumbers(LongValue longValue) {
        this.customerNumbers.add(longValue);
        return this;
    }

    public Draft removeCustomerNumbers(LongValue longValue) {
        this.customerNumbers.remove(longValue);
        return this;
    }

    public Set<SuggestedSanctionInfo> getSuggestedSanctions() {
        return this.suggestedSanctions;
    }

    public void setSuggestedSanctions(Set<SuggestedSanctionInfo> suggestedSanctionInfos) {
        this.suggestedSanctions = suggestedSanctionInfos;
    }

    public Draft suggestedSanctions(Set<SuggestedSanctionInfo> suggestedSanctionInfos) {
        this.setSuggestedSanctions(suggestedSanctionInfos);
        return this;
    }

    public Draft addSuggestedSanctions(SuggestedSanctionInfo suggestedSanctionInfo) {
        this.suggestedSanctions.add(suggestedSanctionInfo);
        return this;
    }

    public Draft removeSuggestedSanctions(SuggestedSanctionInfo suggestedSanctionInfo) {
        this.suggestedSanctions.remove(suggestedSanctionInfo);
        return this;
    }

    public Set<DraftDocumentTransactionContainer> getDocumentTransactionContainers() {
        return this.documentTransactionContainers;
    }

    public void setDocumentTransactionContainers(Set<DraftDocumentTransactionContainer> draftDocumentTransactionContainers) {
        this.documentTransactionContainers = draftDocumentTransactionContainers;
    }

    public Draft documentTransactionContainers(Set<DraftDocumentTransactionContainer> draftDocumentTransactionContainers) {
        this.setDocumentTransactionContainers(draftDocumentTransactionContainers);
        return this;
    }

    public Draft addDocumentTransactionContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.documentTransactionContainers.add(draftDocumentTransactionContainer);
        return this;
    }

    public Draft removeDocumentTransactionContainer(DraftDocumentTransactionContainer draftDocumentTransactionContainer) {
        this.documentTransactionContainers.remove(draftDocumentTransactionContainer);
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
            "}";
    }
}
