package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A CustomJustification.
 */
@Entity
@Table(name = "custom_justification")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomJustification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "agricultural_public_policies")
    private String agriculturalPublicPolicies;

    @Column(name = "assessment_place")
    private String assessmentPlace;

    @Column(name = "attached_documents")
    private String attachedDocuments;

    @Column(name = "balance_rate", precision = 21, scale = 2)
    private BigDecimal balanceRate;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "border_transport_type")
    private String borderTransportType;

    @Column(name = "box_count_type")
    private String boxCountType;

    @Column(name = "box_marks")
    private String boxMarks;

    @Column(name = "cargo_index_number")
    private String cargoIndexNumber;

    @Column(name = "central_bank_credit_code")
    private String centralBankCreditCode;

    @Column(name = "comments")
    private String comments;

    @Column(name = "constructor_country_code")
    private String constructorCountryCode;

    @Column(name = "cost_details")
    private String costDetails;

    @Column(name = "cottage_number", precision = 21, scale = 2)
    private BigDecimal cottageNumber;

    @Column(name = "credit_equivalent_amount", precision = 21, scale = 2)
    private BigDecimal creditEquivalentAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "currency_amount", precision = 21, scale = 2)
    private BigDecimal currencyAmount;

    @Column(name = "currency_rate", precision = 21, scale = 2)
    private BigDecimal currencyRate;

    @Column(name = "currency_swift_code")
    private String currencySwiftCode;

    @Column(name = "custom_company_code")
    private String customCompanyCode;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "date")
    private String date;

    @Column(name = "dest_country_code")
    private String destCountryCode;

    @Column(name = "dest_custom")
    private String destCustom;

    @Column(name = "dest_custom_code")
    private String destCustomCode;

    @Column(name = "disciplinary_documents_issued")
    private Boolean disciplinaryDocumentsIssued;

    @Column(name = "discount_percent", precision = 21, scale = 2)
    private BigDecimal discountPercent;

    @Column(name = "eight_digit_order_code")
    private String eightDigitOrderCode;

    @Column(name = "entrance_custom_company")
    private Long entranceCustomCompany;

    @Column(name = "entrance_custom_company_id")
    private Long entranceCustomCompanyId;

    @Column(name = "evacuation_place")
    private String evacuationPlace;

    @Column(name = "evaluation_method_code")
    private String evaluationMethodCode;

    @Column(name = "export_date")
    private String exportDate;

    @Column(name = "exporter")
    private String exporter;

    @Column(name = "exporter_country_code")
    private String exporterCountryCode;

    @Column(name = "extension_date")
    private String extensionDate;

    @Column(name = "factor_total_amount")
    private String factorTotalAmount;

    @Column(name = "freight_index_number")
    private String freightIndexNumber;

    @Column(name = "fright_letter")
    private String frightLetter;

    @Column(name = "import_licence")
    private String importLicence;

    @Column(name = "import_license")
    private String importLicense;

    @Column(name = "impure_weight", precision = 21, scale = 2)
    private BigDecimal impureWeight;

    @Column(name = "indices")
    private String indices;

    @Column(name = "internal_transport_type")
    private String internalTransportType;

    @Column(name = "issuance_date")
    private String issuanceDate;

    @Column(name = "item_number")
    private String itemNumber;

    @Column(name = "items")
    private Integer items;

    @Column(name = "justification_agent")
    private String justificationAgent;

    @Column(name = "justification_currency_rate", precision = 21, scale = 2)
    private BigDecimal justificationCurrencyRate;

    @Column(name = "licence_number")
    private String licenceNumber;

    @Column(name = "make_certificate_number")
    private String makeCertificateNumber;

    @Column(name = "new_border_transport_type")
    private Long newBorderTransportType;

    @Column(name = "new_evacuation_place")
    private Long newEvacuationPlace;

    @Column(name = "new_internal_transport_type")
    private Long newInternalTransportType;

    @Column(name = "new_product_item_custom_value", precision = 21, scale = 2)
    private BigDecimal newProductItemCustomValue;

    @Column(name = "order_registration_date")
    private String orderRegistrationDate;

    @Column(name = "order_registration_number")
    private String orderRegistrationNumber;

    @Column(name = "papers")
    private Integer papers;

    @Column(name = "payment_conditions")
    private String paymentConditions;

    @Column(name = "preferences")
    private String preferences;

    @Column(name = "procedure")
    private String procedure;

    @Column(name = "product_box_count")
    private Integer productBoxCount;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_item_cost", precision = 21, scale = 2)
    private BigDecimal productItemCost;

    @Column(name = "product_item_count")
    private String productItemCount;

    @Column(name = "product_item_custom_value")
    private String productItemCustomValue;

    @Column(name = "product_item_value")
    private String productItemValue;

    @Column(name = "product_measure")
    private String productMeasure;

    @Column(name = "product_owner")
    private String productOwner;

    @Column(name = "product_release_date")
    private String productReleaseDate;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_worth", precision = 21, scale = 2)
    private BigDecimal productWorth;

    @Column(name = "profit_rate", precision = 21, scale = 2)
    private BigDecimal profitRate;

    @Column(name = "pure_weight", precision = 21, scale = 2)
    private BigDecimal pureWeight;

    @Column(name = "quota")
    private String quota;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "reference_number")
    private Integer referenceNumber;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "reverse_of_justification_disciplinary_document_number")
    private String reverseOfJustificationDisciplinaryDocumentNumber;

    @Column(name = "reverse_of_justification_documents_issued")
    private Boolean reverseOfJustificationDocumentsIssued;

    @Column(name = "rights_rate", precision = 21, scale = 2)
    private BigDecimal rightsRate;

    @Column(name = "trading_country_code")
    private String tradingCountryCode;

    @Column(name = "transaction_type_code")
    private String transactionTypeCode;

    @Column(name = "warehouse_factor_number")
    private String warehouseFactorNumber;

    @Column(name = "constructor_country_name")
    private String constructorCountryName;

    @Column(name = "last_country_name")
    private String lastCountryName;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "justification_currency_code")
    private String justificationCurrencyCode;

    @Column(name = "credit_currency_code")
    private String creditCurrencyCode;

    @Column(name = "is_migrational")
    private Boolean isMigrational;

    @Lob
    @Column(name = "original_letter_image")
    private byte[] originalLetterImage;

    @Column(name = "original_letter_image_content_type")
    private String originalLetterImageContentType;

    @Lob
    @Column(name = "letter_image")
    private byte[] letterImage;

    @Column(name = "letter_image_content_type")
    private String letterImageContentType;

    @Column(name = "source_country_code")
    private String sourceCountryCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customJustification")
    @JsonIgnoreProperties(value = { "customJustification" }, allowSetters = true)
    private Set<CustomJustificationChild> customJustificationChildLists = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement vehicleEnterNationality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement container;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement vehicleCrossNationality;

    @ManyToOne(fetch = FetchType.LAZY)
    private Custom exportCustom;

    @ManyToOne(fetch = FetchType.LAZY)
    private Custom entranceCustom;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportationType transportConditions;

    @ManyToOne(fetch = FetchType.LAZY)
    private TradeTypeCode tradeTypeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentCondition newPaymentConditions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "justificationDeductionAmountParts", "justificationDeductionDetails" }, allowSetters = true)
    private JustificationDeductionAmount justificationDeductionAmount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_custom_justification__products",
        joinColumns = @JoinColumn(name = "custom_justification_id"),
        inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    @JsonIgnoreProperties(
        value = { "attributeValues", "productType", "orderRegistrationInfos", "drafts", "customJustifications" },
        allowSetters = true
    )
    private Set<Product> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_custom_justification__reverse_of_justification_document__84",
        joinColumns = @JoinColumn(name = "custom_justification_id"),
        inverseJoinColumns = @JoinColumn(name = "reverse_of_justification_document_transactions_id")
    )
    @JsonIgnoreProperties(
        value = {
            "otherDocumentTransactionsContainer",
            "canceledJustificationDocumentContainer",
            "justificationDocumentTransactionsContainer",
            "receivedCommisionsContainer",
            "canceledDocumentTransactionsContainer",
            "returnedDefaultCurrencyCostsContainer",
            "defaultCurrencyCostsDocumentContainer",
            "customJustifications",
        },
        allowSetters = true
    )
    private Set<DocumentTransaction> reverseOfJustificationDocumentTransactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CustomJustification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgentId() {
        return this.agentId;
    }

    public CustomJustification agentId(Long agentId) {
        this.setAgentId(agentId);
        return this;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgriculturalPublicPolicies() {
        return this.agriculturalPublicPolicies;
    }

    public CustomJustification agriculturalPublicPolicies(String agriculturalPublicPolicies) {
        this.setAgriculturalPublicPolicies(agriculturalPublicPolicies);
        return this;
    }

    public void setAgriculturalPublicPolicies(String agriculturalPublicPolicies) {
        this.agriculturalPublicPolicies = agriculturalPublicPolicies;
    }

    public String getAssessmentPlace() {
        return this.assessmentPlace;
    }

    public CustomJustification assessmentPlace(String assessmentPlace) {
        this.setAssessmentPlace(assessmentPlace);
        return this;
    }

    public void setAssessmentPlace(String assessmentPlace) {
        this.assessmentPlace = assessmentPlace;
    }

    public String getAttachedDocuments() {
        return this.attachedDocuments;
    }

    public CustomJustification attachedDocuments(String attachedDocuments) {
        this.setAttachedDocuments(attachedDocuments);
        return this;
    }

    public void setAttachedDocuments(String attachedDocuments) {
        this.attachedDocuments = attachedDocuments;
    }

    public BigDecimal getBalanceRate() {
        return this.balanceRate;
    }

    public CustomJustification balanceRate(BigDecimal balanceRate) {
        this.setBalanceRate(balanceRate);
        return this;
    }

    public void setBalanceRate(BigDecimal balanceRate) {
        this.balanceRate = balanceRate;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public CustomJustification bankCode(String bankCode) {
        this.setBankCode(bankCode);
        return this;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBorderTransportType() {
        return this.borderTransportType;
    }

    public CustomJustification borderTransportType(String borderTransportType) {
        this.setBorderTransportType(borderTransportType);
        return this;
    }

    public void setBorderTransportType(String borderTransportType) {
        this.borderTransportType = borderTransportType;
    }

    public String getBoxCountType() {
        return this.boxCountType;
    }

    public CustomJustification boxCountType(String boxCountType) {
        this.setBoxCountType(boxCountType);
        return this;
    }

    public void setBoxCountType(String boxCountType) {
        this.boxCountType = boxCountType;
    }

    public String getBoxMarks() {
        return this.boxMarks;
    }

    public CustomJustification boxMarks(String boxMarks) {
        this.setBoxMarks(boxMarks);
        return this;
    }

    public void setBoxMarks(String boxMarks) {
        this.boxMarks = boxMarks;
    }

    public String getCargoIndexNumber() {
        return this.cargoIndexNumber;
    }

    public CustomJustification cargoIndexNumber(String cargoIndexNumber) {
        this.setCargoIndexNumber(cargoIndexNumber);
        return this;
    }

    public void setCargoIndexNumber(String cargoIndexNumber) {
        this.cargoIndexNumber = cargoIndexNumber;
    }

    public String getCentralBankCreditCode() {
        return this.centralBankCreditCode;
    }

    public CustomJustification centralBankCreditCode(String centralBankCreditCode) {
        this.setCentralBankCreditCode(centralBankCreditCode);
        return this;
    }

    public void setCentralBankCreditCode(String centralBankCreditCode) {
        this.centralBankCreditCode = centralBankCreditCode;
    }

    public String getComments() {
        return this.comments;
    }

    public CustomJustification comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getConstructorCountryCode() {
        return this.constructorCountryCode;
    }

    public CustomJustification constructorCountryCode(String constructorCountryCode) {
        this.setConstructorCountryCode(constructorCountryCode);
        return this;
    }

    public void setConstructorCountryCode(String constructorCountryCode) {
        this.constructorCountryCode = constructorCountryCode;
    }

    public String getCostDetails() {
        return this.costDetails;
    }

    public CustomJustification costDetails(String costDetails) {
        this.setCostDetails(costDetails);
        return this;
    }

    public void setCostDetails(String costDetails) {
        this.costDetails = costDetails;
    }

    public BigDecimal getCottageNumber() {
        return this.cottageNumber;
    }

    public CustomJustification cottageNumber(BigDecimal cottageNumber) {
        this.setCottageNumber(cottageNumber);
        return this;
    }

    public void setCottageNumber(BigDecimal cottageNumber) {
        this.cottageNumber = cottageNumber;
    }

    public BigDecimal getCreditEquivalentAmount() {
        return this.creditEquivalentAmount;
    }

    public CustomJustification creditEquivalentAmount(BigDecimal creditEquivalentAmount) {
        this.setCreditEquivalentAmount(creditEquivalentAmount);
        return this;
    }

    public void setCreditEquivalentAmount(BigDecimal creditEquivalentAmount) {
        this.creditEquivalentAmount = creditEquivalentAmount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public CustomJustification currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyAmount() {
        return this.currencyAmount;
    }

    public CustomJustification currencyAmount(BigDecimal currencyAmount) {
        this.setCurrencyAmount(currencyAmount);
        return this;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public BigDecimal getCurrencyRate() {
        return this.currencyRate;
    }

    public CustomJustification currencyRate(BigDecimal currencyRate) {
        this.setCurrencyRate(currencyRate);
        return this;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getCurrencySwiftCode() {
        return this.currencySwiftCode;
    }

    public CustomJustification currencySwiftCode(String currencySwiftCode) {
        this.setCurrencySwiftCode(currencySwiftCode);
        return this;
    }

    public void setCurrencySwiftCode(String currencySwiftCode) {
        this.currencySwiftCode = currencySwiftCode;
    }

    public String getCustomCompanyCode() {
        return this.customCompanyCode;
    }

    public CustomJustification customCompanyCode(String customCompanyCode) {
        this.setCustomCompanyCode(customCompanyCode);
        return this;
    }

    public void setCustomCompanyCode(String customCompanyCode) {
        this.customCompanyCode = customCompanyCode;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public CustomJustification customerId(Long customerId) {
        this.setCustomerId(customerId);
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return this.date;
    }

    public CustomJustification date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDestCountryCode() {
        return this.destCountryCode;
    }

    public CustomJustification destCountryCode(String destCountryCode) {
        this.setDestCountryCode(destCountryCode);
        return this;
    }

    public void setDestCountryCode(String destCountryCode) {
        this.destCountryCode = destCountryCode;
    }

    public String getDestCustom() {
        return this.destCustom;
    }

    public CustomJustification destCustom(String destCustom) {
        this.setDestCustom(destCustom);
        return this;
    }

    public void setDestCustom(String destCustom) {
        this.destCustom = destCustom;
    }

    public String getDestCustomCode() {
        return this.destCustomCode;
    }

    public CustomJustification destCustomCode(String destCustomCode) {
        this.setDestCustomCode(destCustomCode);
        return this;
    }

    public void setDestCustomCode(String destCustomCode) {
        this.destCustomCode = destCustomCode;
    }

    public Boolean getDisciplinaryDocumentsIssued() {
        return this.disciplinaryDocumentsIssued;
    }

    public CustomJustification disciplinaryDocumentsIssued(Boolean disciplinaryDocumentsIssued) {
        this.setDisciplinaryDocumentsIssued(disciplinaryDocumentsIssued);
        return this;
    }

    public void setDisciplinaryDocumentsIssued(Boolean disciplinaryDocumentsIssued) {
        this.disciplinaryDocumentsIssued = disciplinaryDocumentsIssued;
    }

    public BigDecimal getDiscountPercent() {
        return this.discountPercent;
    }

    public CustomJustification discountPercent(BigDecimal discountPercent) {
        this.setDiscountPercent(discountPercent);
        return this;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getEightDigitOrderCode() {
        return this.eightDigitOrderCode;
    }

    public CustomJustification eightDigitOrderCode(String eightDigitOrderCode) {
        this.setEightDigitOrderCode(eightDigitOrderCode);
        return this;
    }

    public void setEightDigitOrderCode(String eightDigitOrderCode) {
        this.eightDigitOrderCode = eightDigitOrderCode;
    }

    public Long getEntranceCustomCompany() {
        return this.entranceCustomCompany;
    }

    public CustomJustification entranceCustomCompany(Long entranceCustomCompany) {
        this.setEntranceCustomCompany(entranceCustomCompany);
        return this;
    }

    public void setEntranceCustomCompany(Long entranceCustomCompany) {
        this.entranceCustomCompany = entranceCustomCompany;
    }

    public Long getEntranceCustomCompanyId() {
        return this.entranceCustomCompanyId;
    }

    public CustomJustification entranceCustomCompanyId(Long entranceCustomCompanyId) {
        this.setEntranceCustomCompanyId(entranceCustomCompanyId);
        return this;
    }

    public void setEntranceCustomCompanyId(Long entranceCustomCompanyId) {
        this.entranceCustomCompanyId = entranceCustomCompanyId;
    }

    public String getEvacuationPlace() {
        return this.evacuationPlace;
    }

    public CustomJustification evacuationPlace(String evacuationPlace) {
        this.setEvacuationPlace(evacuationPlace);
        return this;
    }

    public void setEvacuationPlace(String evacuationPlace) {
        this.evacuationPlace = evacuationPlace;
    }

    public String getEvaluationMethodCode() {
        return this.evaluationMethodCode;
    }

    public CustomJustification evaluationMethodCode(String evaluationMethodCode) {
        this.setEvaluationMethodCode(evaluationMethodCode);
        return this;
    }

    public void setEvaluationMethodCode(String evaluationMethodCode) {
        this.evaluationMethodCode = evaluationMethodCode;
    }

    public String getExportDate() {
        return this.exportDate;
    }

    public CustomJustification exportDate(String exportDate) {
        this.setExportDate(exportDate);
        return this;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    public String getExporter() {
        return this.exporter;
    }

    public CustomJustification exporter(String exporter) {
        this.setExporter(exporter);
        return this;
    }

    public void setExporter(String exporter) {
        this.exporter = exporter;
    }

    public String getExporterCountryCode() {
        return this.exporterCountryCode;
    }

    public CustomJustification exporterCountryCode(String exporterCountryCode) {
        this.setExporterCountryCode(exporterCountryCode);
        return this;
    }

    public void setExporterCountryCode(String exporterCountryCode) {
        this.exporterCountryCode = exporterCountryCode;
    }

    public String getExtensionDate() {
        return this.extensionDate;
    }

    public CustomJustification extensionDate(String extensionDate) {
        this.setExtensionDate(extensionDate);
        return this;
    }

    public void setExtensionDate(String extensionDate) {
        this.extensionDate = extensionDate;
    }

    public String getFactorTotalAmount() {
        return this.factorTotalAmount;
    }

    public CustomJustification factorTotalAmount(String factorTotalAmount) {
        this.setFactorTotalAmount(factorTotalAmount);
        return this;
    }

    public void setFactorTotalAmount(String factorTotalAmount) {
        this.factorTotalAmount = factorTotalAmount;
    }

    public String getFreightIndexNumber() {
        return this.freightIndexNumber;
    }

    public CustomJustification freightIndexNumber(String freightIndexNumber) {
        this.setFreightIndexNumber(freightIndexNumber);
        return this;
    }

    public void setFreightIndexNumber(String freightIndexNumber) {
        this.freightIndexNumber = freightIndexNumber;
    }

    public String getFrightLetter() {
        return this.frightLetter;
    }

    public CustomJustification frightLetter(String frightLetter) {
        this.setFrightLetter(frightLetter);
        return this;
    }

    public void setFrightLetter(String frightLetter) {
        this.frightLetter = frightLetter;
    }

    public String getImportLicence() {
        return this.importLicence;
    }

    public CustomJustification importLicence(String importLicence) {
        this.setImportLicence(importLicence);
        return this;
    }

    public void setImportLicence(String importLicence) {
        this.importLicence = importLicence;
    }

    public String getImportLicense() {
        return this.importLicense;
    }

    public CustomJustification importLicense(String importLicense) {
        this.setImportLicense(importLicense);
        return this;
    }

    public void setImportLicense(String importLicense) {
        this.importLicense = importLicense;
    }

    public BigDecimal getImpureWeight() {
        return this.impureWeight;
    }

    public CustomJustification impureWeight(BigDecimal impureWeight) {
        this.setImpureWeight(impureWeight);
        return this;
    }

    public void setImpureWeight(BigDecimal impureWeight) {
        this.impureWeight = impureWeight;
    }

    public String getIndices() {
        return this.indices;
    }

    public CustomJustification indices(String indices) {
        this.setIndices(indices);
        return this;
    }

    public void setIndices(String indices) {
        this.indices = indices;
    }

    public String getInternalTransportType() {
        return this.internalTransportType;
    }

    public CustomJustification internalTransportType(String internalTransportType) {
        this.setInternalTransportType(internalTransportType);
        return this;
    }

    public void setInternalTransportType(String internalTransportType) {
        this.internalTransportType = internalTransportType;
    }

    public String getIssuanceDate() {
        return this.issuanceDate;
    }

    public CustomJustification issuanceDate(String issuanceDate) {
        this.setIssuanceDate(issuanceDate);
        return this;
    }

    public void setIssuanceDate(String issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public String getItemNumber() {
        return this.itemNumber;
    }

    public CustomJustification itemNumber(String itemNumber) {
        this.setItemNumber(itemNumber);
        return this;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getItems() {
        return this.items;
    }

    public CustomJustification items(Integer items) {
        this.setItems(items);
        return this;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public String getJustificationAgent() {
        return this.justificationAgent;
    }

    public CustomJustification justificationAgent(String justificationAgent) {
        this.setJustificationAgent(justificationAgent);
        return this;
    }

    public void setJustificationAgent(String justificationAgent) {
        this.justificationAgent = justificationAgent;
    }

    public BigDecimal getJustificationCurrencyRate() {
        return this.justificationCurrencyRate;
    }

    public CustomJustification justificationCurrencyRate(BigDecimal justificationCurrencyRate) {
        this.setJustificationCurrencyRate(justificationCurrencyRate);
        return this;
    }

    public void setJustificationCurrencyRate(BigDecimal justificationCurrencyRate) {
        this.justificationCurrencyRate = justificationCurrencyRate;
    }

    public String getLicenceNumber() {
        return this.licenceNumber;
    }

    public CustomJustification licenceNumber(String licenceNumber) {
        this.setLicenceNumber(licenceNumber);
        return this;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getMakeCertificateNumber() {
        return this.makeCertificateNumber;
    }

    public CustomJustification makeCertificateNumber(String makeCertificateNumber) {
        this.setMakeCertificateNumber(makeCertificateNumber);
        return this;
    }

    public void setMakeCertificateNumber(String makeCertificateNumber) {
        this.makeCertificateNumber = makeCertificateNumber;
    }

    public Long getNewBorderTransportType() {
        return this.newBorderTransportType;
    }

    public CustomJustification newBorderTransportType(Long newBorderTransportType) {
        this.setNewBorderTransportType(newBorderTransportType);
        return this;
    }

    public void setNewBorderTransportType(Long newBorderTransportType) {
        this.newBorderTransportType = newBorderTransportType;
    }

    public Long getNewEvacuationPlace() {
        return this.newEvacuationPlace;
    }

    public CustomJustification newEvacuationPlace(Long newEvacuationPlace) {
        this.setNewEvacuationPlace(newEvacuationPlace);
        return this;
    }

    public void setNewEvacuationPlace(Long newEvacuationPlace) {
        this.newEvacuationPlace = newEvacuationPlace;
    }

    public Long getNewInternalTransportType() {
        return this.newInternalTransportType;
    }

    public CustomJustification newInternalTransportType(Long newInternalTransportType) {
        this.setNewInternalTransportType(newInternalTransportType);
        return this;
    }

    public void setNewInternalTransportType(Long newInternalTransportType) {
        this.newInternalTransportType = newInternalTransportType;
    }

    public BigDecimal getNewProductItemCustomValue() {
        return this.newProductItemCustomValue;
    }

    public CustomJustification newProductItemCustomValue(BigDecimal newProductItemCustomValue) {
        this.setNewProductItemCustomValue(newProductItemCustomValue);
        return this;
    }

    public void setNewProductItemCustomValue(BigDecimal newProductItemCustomValue) {
        this.newProductItemCustomValue = newProductItemCustomValue;
    }

    public String getOrderRegistrationDate() {
        return this.orderRegistrationDate;
    }

    public CustomJustification orderRegistrationDate(String orderRegistrationDate) {
        this.setOrderRegistrationDate(orderRegistrationDate);
        return this;
    }

    public void setOrderRegistrationDate(String orderRegistrationDate) {
        this.orderRegistrationDate = orderRegistrationDate;
    }

    public String getOrderRegistrationNumber() {
        return this.orderRegistrationNumber;
    }

    public CustomJustification orderRegistrationNumber(String orderRegistrationNumber) {
        this.setOrderRegistrationNumber(orderRegistrationNumber);
        return this;
    }

    public void setOrderRegistrationNumber(String orderRegistrationNumber) {
        this.orderRegistrationNumber = orderRegistrationNumber;
    }

    public Integer getPapers() {
        return this.papers;
    }

    public CustomJustification papers(Integer papers) {
        this.setPapers(papers);
        return this;
    }

    public void setPapers(Integer papers) {
        this.papers = papers;
    }

    public String getPaymentConditions() {
        return this.paymentConditions;
    }

    public CustomJustification paymentConditions(String paymentConditions) {
        this.setPaymentConditions(paymentConditions);
        return this;
    }

    public void setPaymentConditions(String paymentConditions) {
        this.paymentConditions = paymentConditions;
    }

    public String getPreferences() {
        return this.preferences;
    }

    public CustomJustification preferences(String preferences) {
        this.setPreferences(preferences);
        return this;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getProcedure() {
        return this.procedure;
    }

    public CustomJustification procedure(String procedure) {
        this.setProcedure(procedure);
        return this;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public Integer getProductBoxCount() {
        return this.productBoxCount;
    }

    public CustomJustification productBoxCount(Integer productBoxCount) {
        this.setProductBoxCount(productBoxCount);
        return this;
    }

    public void setProductBoxCount(Integer productBoxCount) {
        this.productBoxCount = productBoxCount;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public CustomJustification productCode(String productCode) {
        this.setProductCode(productCode);
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getProductItemCost() {
        return this.productItemCost;
    }

    public CustomJustification productItemCost(BigDecimal productItemCost) {
        this.setProductItemCost(productItemCost);
        return this;
    }

    public void setProductItemCost(BigDecimal productItemCost) {
        this.productItemCost = productItemCost;
    }

    public String getProductItemCount() {
        return this.productItemCount;
    }

    public CustomJustification productItemCount(String productItemCount) {
        this.setProductItemCount(productItemCount);
        return this;
    }

    public void setProductItemCount(String productItemCount) {
        this.productItemCount = productItemCount;
    }

    public String getProductItemCustomValue() {
        return this.productItemCustomValue;
    }

    public CustomJustification productItemCustomValue(String productItemCustomValue) {
        this.setProductItemCustomValue(productItemCustomValue);
        return this;
    }

    public void setProductItemCustomValue(String productItemCustomValue) {
        this.productItemCustomValue = productItemCustomValue;
    }

    public String getProductItemValue() {
        return this.productItemValue;
    }

    public CustomJustification productItemValue(String productItemValue) {
        this.setProductItemValue(productItemValue);
        return this;
    }

    public void setProductItemValue(String productItemValue) {
        this.productItemValue = productItemValue;
    }

    public String getProductMeasure() {
        return this.productMeasure;
    }

    public CustomJustification productMeasure(String productMeasure) {
        this.setProductMeasure(productMeasure);
        return this;
    }

    public void setProductMeasure(String productMeasure) {
        this.productMeasure = productMeasure;
    }

    public String getProductOwner() {
        return this.productOwner;
    }

    public CustomJustification productOwner(String productOwner) {
        this.setProductOwner(productOwner);
        return this;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getProductReleaseDate() {
        return this.productReleaseDate;
    }

    public CustomJustification productReleaseDate(String productReleaseDate) {
        this.setProductReleaseDate(productReleaseDate);
        return this;
    }

    public void setProductReleaseDate(String productReleaseDate) {
        this.productReleaseDate = productReleaseDate;
    }

    public String getProductType() {
        return this.productType;
    }

    public CustomJustification productType(String productType) {
        this.setProductType(productType);
        return this;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductWorth() {
        return this.productWorth;
    }

    public CustomJustification productWorth(BigDecimal productWorth) {
        this.setProductWorth(productWorth);
        return this;
    }

    public void setProductWorth(BigDecimal productWorth) {
        this.productWorth = productWorth;
    }

    public BigDecimal getProfitRate() {
        return this.profitRate;
    }

    public CustomJustification profitRate(BigDecimal profitRate) {
        this.setProfitRate(profitRate);
        return this;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal getPureWeight() {
        return this.pureWeight;
    }

    public CustomJustification pureWeight(BigDecimal pureWeight) {
        this.setPureWeight(pureWeight);
        return this;
    }

    public void setPureWeight(BigDecimal pureWeight) {
        this.pureWeight = pureWeight;
    }

    public String getQuota() {
        return this.quota;
    }

    public CustomJustification quota(String quota) {
        this.setQuota(quota);
        return this;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public CustomJustification receiver(String receiver) {
        this.setReceiver(receiver);
        return this;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getReferenceNumber() {
        return this.referenceNumber;
    }

    public CustomJustification referenceNumber(Integer referenceNumber) {
        this.setReferenceNumber(referenceNumber);
        return this;
    }

    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public CustomJustification registrationNumber(String registrationNumber) {
        this.setRegistrationNumber(registrationNumber);
        return this;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getReverseOfJustificationDisciplinaryDocumentNumber() {
        return this.reverseOfJustificationDisciplinaryDocumentNumber;
    }

    public CustomJustification reverseOfJustificationDisciplinaryDocumentNumber(String reverseOfJustificationDisciplinaryDocumentNumber) {
        this.setReverseOfJustificationDisciplinaryDocumentNumber(reverseOfJustificationDisciplinaryDocumentNumber);
        return this;
    }

    public void setReverseOfJustificationDisciplinaryDocumentNumber(String reverseOfJustificationDisciplinaryDocumentNumber) {
        this.reverseOfJustificationDisciplinaryDocumentNumber = reverseOfJustificationDisciplinaryDocumentNumber;
    }

    public Boolean getReverseOfJustificationDocumentsIssued() {
        return this.reverseOfJustificationDocumentsIssued;
    }

    public CustomJustification reverseOfJustificationDocumentsIssued(Boolean reverseOfJustificationDocumentsIssued) {
        this.setReverseOfJustificationDocumentsIssued(reverseOfJustificationDocumentsIssued);
        return this;
    }

    public void setReverseOfJustificationDocumentsIssued(Boolean reverseOfJustificationDocumentsIssued) {
        this.reverseOfJustificationDocumentsIssued = reverseOfJustificationDocumentsIssued;
    }

    public BigDecimal getRightsRate() {
        return this.rightsRate;
    }

    public CustomJustification rightsRate(BigDecimal rightsRate) {
        this.setRightsRate(rightsRate);
        return this;
    }

    public void setRightsRate(BigDecimal rightsRate) {
        this.rightsRate = rightsRate;
    }

    public String getTradingCountryCode() {
        return this.tradingCountryCode;
    }

    public CustomJustification tradingCountryCode(String tradingCountryCode) {
        this.setTradingCountryCode(tradingCountryCode);
        return this;
    }

    public void setTradingCountryCode(String tradingCountryCode) {
        this.tradingCountryCode = tradingCountryCode;
    }

    public String getTransactionTypeCode() {
        return this.transactionTypeCode;
    }

    public CustomJustification transactionTypeCode(String transactionTypeCode) {
        this.setTransactionTypeCode(transactionTypeCode);
        return this;
    }

    public void setTransactionTypeCode(String transactionTypeCode) {
        this.transactionTypeCode = transactionTypeCode;
    }

    public String getWarehouseFactorNumber() {
        return this.warehouseFactorNumber;
    }

    public CustomJustification warehouseFactorNumber(String warehouseFactorNumber) {
        this.setWarehouseFactorNumber(warehouseFactorNumber);
        return this;
    }

    public void setWarehouseFactorNumber(String warehouseFactorNumber) {
        this.warehouseFactorNumber = warehouseFactorNumber;
    }

    public String getConstructorCountryName() {
        return this.constructorCountryName;
    }

    public CustomJustification constructorCountryName(String constructorCountryName) {
        this.setConstructorCountryName(constructorCountryName);
        return this;
    }

    public void setConstructorCountryName(String constructorCountryName) {
        this.constructorCountryName = constructorCountryName;
    }

    public String getLastCountryName() {
        return this.lastCountryName;
    }

    public CustomJustification lastCountryName(String lastCountryName) {
        this.setLastCountryName(lastCountryName);
        return this;
    }

    public void setLastCountryName(String lastCountryName) {
        this.lastCountryName = lastCountryName;
    }

    public String getBranchCode() {
        return this.branchCode;
    }

    public CustomJustification branchCode(String branchCode) {
        this.setBranchCode(branchCode);
        return this;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getJustificationCurrencyCode() {
        return this.justificationCurrencyCode;
    }

    public CustomJustification justificationCurrencyCode(String justificationCurrencyCode) {
        this.setJustificationCurrencyCode(justificationCurrencyCode);
        return this;
    }

    public void setJustificationCurrencyCode(String justificationCurrencyCode) {
        this.justificationCurrencyCode = justificationCurrencyCode;
    }

    public String getCreditCurrencyCode() {
        return this.creditCurrencyCode;
    }

    public CustomJustification creditCurrencyCode(String creditCurrencyCode) {
        this.setCreditCurrencyCode(creditCurrencyCode);
        return this;
    }

    public void setCreditCurrencyCode(String creditCurrencyCode) {
        this.creditCurrencyCode = creditCurrencyCode;
    }

    public Boolean getIsMigrational() {
        return this.isMigrational;
    }

    public CustomJustification isMigrational(Boolean isMigrational) {
        this.setIsMigrational(isMigrational);
        return this;
    }

    public void setIsMigrational(Boolean isMigrational) {
        this.isMigrational = isMigrational;
    }

    public byte[] getOriginalLetterImage() {
        return this.originalLetterImage;
    }

    public CustomJustification originalLetterImage(byte[] originalLetterImage) {
        this.setOriginalLetterImage(originalLetterImage);
        return this;
    }

    public void setOriginalLetterImage(byte[] originalLetterImage) {
        this.originalLetterImage = originalLetterImage;
    }

    public String getOriginalLetterImageContentType() {
        return this.originalLetterImageContentType;
    }

    public CustomJustification originalLetterImageContentType(String originalLetterImageContentType) {
        this.originalLetterImageContentType = originalLetterImageContentType;
        return this;
    }

    public void setOriginalLetterImageContentType(String originalLetterImageContentType) {
        this.originalLetterImageContentType = originalLetterImageContentType;
    }

    public byte[] getLetterImage() {
        return this.letterImage;
    }

    public CustomJustification letterImage(byte[] letterImage) {
        this.setLetterImage(letterImage);
        return this;
    }

    public void setLetterImage(byte[] letterImage) {
        this.letterImage = letterImage;
    }

    public String getLetterImageContentType() {
        return this.letterImageContentType;
    }

    public CustomJustification letterImageContentType(String letterImageContentType) {
        this.letterImageContentType = letterImageContentType;
        return this;
    }

    public void setLetterImageContentType(String letterImageContentType) {
        this.letterImageContentType = letterImageContentType;
    }

    public String getSourceCountryCode() {
        return this.sourceCountryCode;
    }

    public CustomJustification sourceCountryCode(String sourceCountryCode) {
        this.setSourceCountryCode(sourceCountryCode);
        return this;
    }

    public void setSourceCountryCode(String sourceCountryCode) {
        this.sourceCountryCode = sourceCountryCode;
    }

    public Set<CustomJustificationChild> getCustomJustificationChildLists() {
        return this.customJustificationChildLists;
    }

    public void setCustomJustificationChildLists(Set<CustomJustificationChild> customJustificationChildren) {
        if (this.customJustificationChildLists != null) {
            this.customJustificationChildLists.forEach(i -> i.setCustomJustification(null));
        }
        if (customJustificationChildren != null) {
            customJustificationChildren.forEach(i -> i.setCustomJustification(this));
        }
        this.customJustificationChildLists = customJustificationChildren;
    }

    public CustomJustification customJustificationChildLists(Set<CustomJustificationChild> customJustificationChildren) {
        this.setCustomJustificationChildLists(customJustificationChildren);
        return this;
    }

    public CustomJustification addCustomJustificationChildList(CustomJustificationChild customJustificationChild) {
        this.customJustificationChildLists.add(customJustificationChild);
        customJustificationChild.setCustomJustification(this);
        return this;
    }

    public CustomJustification removeCustomJustificationChildList(CustomJustificationChild customJustificationChild) {
        this.customJustificationChildLists.remove(customJustificationChild);
        customJustificationChild.setCustomJustification(null);
        return this;
    }

    public CategoryElement getVehicleEnterNationality() {
        return this.vehicleEnterNationality;
    }

    public void setVehicleEnterNationality(CategoryElement categoryElement) {
        this.vehicleEnterNationality = categoryElement;
    }

    public CustomJustification vehicleEnterNationality(CategoryElement categoryElement) {
        this.setVehicleEnterNationality(categoryElement);
        return this;
    }

    public CategoryElement getContainer() {
        return this.container;
    }

    public void setContainer(CategoryElement categoryElement) {
        this.container = categoryElement;
    }

    public CustomJustification container(CategoryElement categoryElement) {
        this.setContainer(categoryElement);
        return this;
    }

    public CategoryElement getVehicleCrossNationality() {
        return this.vehicleCrossNationality;
    }

    public void setVehicleCrossNationality(CategoryElement categoryElement) {
        this.vehicleCrossNationality = categoryElement;
    }

    public CustomJustification vehicleCrossNationality(CategoryElement categoryElement) {
        this.setVehicleCrossNationality(categoryElement);
        return this;
    }

    public Custom getExportCustom() {
        return this.exportCustom;
    }

    public void setExportCustom(Custom custom) {
        this.exportCustom = custom;
    }

    public CustomJustification exportCustom(Custom custom) {
        this.setExportCustom(custom);
        return this;
    }

    public Custom getEntranceCustom() {
        return this.entranceCustom;
    }

    public void setEntranceCustom(Custom custom) {
        this.entranceCustom = custom;
    }

    public CustomJustification entranceCustom(Custom custom) {
        this.setEntranceCustom(custom);
        return this;
    }

    public TransportationType getTransportConditions() {
        return this.transportConditions;
    }

    public void setTransportConditions(TransportationType transportationType) {
        this.transportConditions = transportationType;
    }

    public CustomJustification transportConditions(TransportationType transportationType) {
        this.setTransportConditions(transportationType);
        return this;
    }

    public TradeTypeCode getTradeTypeCode() {
        return this.tradeTypeCode;
    }

    public void setTradeTypeCode(TradeTypeCode tradeTypeCode) {
        this.tradeTypeCode = tradeTypeCode;
    }

    public CustomJustification tradeTypeCode(TradeTypeCode tradeTypeCode) {
        this.setTradeTypeCode(tradeTypeCode);
        return this;
    }

    public PaymentCondition getNewPaymentConditions() {
        return this.newPaymentConditions;
    }

    public void setNewPaymentConditions(PaymentCondition paymentCondition) {
        this.newPaymentConditions = paymentCondition;
    }

    public CustomJustification newPaymentConditions(PaymentCondition paymentCondition) {
        this.setNewPaymentConditions(paymentCondition);
        return this;
    }

    public JustificationDeductionAmount getJustificationDeductionAmount() {
        return this.justificationDeductionAmount;
    }

    public void setJustificationDeductionAmount(JustificationDeductionAmount justificationDeductionAmount) {
        this.justificationDeductionAmount = justificationDeductionAmount;
    }

    public CustomJustification justificationDeductionAmount(JustificationDeductionAmount justificationDeductionAmount) {
        this.setJustificationDeductionAmount(justificationDeductionAmount);
        return this;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public CustomJustification products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public CustomJustification addProducts(Product product) {
        this.products.add(product);
        return this;
    }

    public CustomJustification removeProducts(Product product) {
        this.products.remove(product);
        return this;
    }

    public Set<DocumentTransaction> getReverseOfJustificationDocumentTransactions() {
        return this.reverseOfJustificationDocumentTransactions;
    }

    public void setReverseOfJustificationDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        this.reverseOfJustificationDocumentTransactions = documentTransactions;
    }

    public CustomJustification reverseOfJustificationDocumentTransactions(Set<DocumentTransaction> documentTransactions) {
        this.setReverseOfJustificationDocumentTransactions(documentTransactions);
        return this;
    }

    public CustomJustification addReverseOfJustificationDocumentTransactions(DocumentTransaction documentTransaction) {
        this.reverseOfJustificationDocumentTransactions.add(documentTransaction);
        return this;
    }

    public CustomJustification removeReverseOfJustificationDocumentTransactions(DocumentTransaction documentTransaction) {
        this.reverseOfJustificationDocumentTransactions.remove(documentTransaction);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomJustification)) {
            return false;
        }
        return getId() != null && getId().equals(((CustomJustification) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomJustification{" +
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
            ", originalLetterImageContentType='" + getOriginalLetterImageContentType() + "'" +
            ", letterImage='" + getLetterImage() + "'" +
            ", letterImageContentType='" + getLetterImageContentType() + "'" +
            ", sourceCountryCode='" + getSourceCountryCode() + "'" +
            "}";
    }
}
