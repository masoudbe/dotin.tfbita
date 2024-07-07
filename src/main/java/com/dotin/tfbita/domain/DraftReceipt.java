package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A DraftReceipt.
 */
@Entity
@Table(name = "draft_receipt")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftReceipt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "customer_deliver_date")
    private String customerDeliverDate;

    @Column(name = "date")
    private String date;

    @Column(name = "delete_date")
    private String deleteDate;

    @Column(name = "deliver_date")
    private String deliverDate;

    @Column(name = "deliver_duration")
    private Integer deliverDuration;

    @Column(name = "delivered")
    private Boolean delivered;

    @Column(name = "document_transaction_effective_date")
    private String documentTransactionEffectiveDate;

    @Column(name = "freight_letter_date")
    private String freightLetterDate;

    @Column(name = "freight_letter_number")
    private String freightLetterNumber;

    @Column(name = "freight_letter_stamp_cost", precision = 21, scale = 2)
    private BigDecimal freightLetterStampCost;

    @Column(name = "issue_date")
    private String issueDate;

    @Column(name = "issue_document")
    private Boolean issueDocument;

    @Column(name = "product_amount", precision = 21, scale = 2)
    private BigDecimal productAmount;

    @Column(name = "receipt_amount", precision = 21, scale = 2)
    private BigDecimal receiptAmount;

    @Column(name = "receipt_date")
    private String receiptDate;

    @Column(name = "row")
    private Integer row;

    @Column(name = "serial")
    private String serial;

    @Column(name = "transport_row")
    private String transportRow;

    @Column(name = "usable")
    private Boolean usable;

    @Column(name = "net_weight", precision = 21, scale = 2)
    private BigDecimal netWeight;

    @Column(name = "gross_weight", precision = 21, scale = 2)
    private BigDecimal grossWeight;

    @Column(name = "total_net_weight", precision = 21, scale = 2)
    private BigDecimal totalNetWeight;

    @Column(name = "total_gross_weight", precision = 21, scale = 2)
    private BigDecimal totalGrossWeight;

    @Column(name = "letter_number_tazirat")
    private String letterNumberTazirat;

    @Column(name = "letter_date_tazirat")
    private String letterDateTazirat;

    @Column(name = "fob_amount", precision = 21, scale = 2)
    private BigDecimal fobAmount;

    @Column(name = "shipping_fare", precision = 21, scale = 2)
    private BigDecimal shippingFare;

    @Column(name = "inspection_cost", precision = 21, scale = 2)
    private BigDecimal inspectionCost;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @Column(name = "deadline_submit_document_date")
    private String deadlineSubmitDocumentDate;

    @Lob
    @Column(name = "currency_provision_file")
    private byte[] currencyProvisionFile;

    @Column(name = "currency_provision_file_content_type")
    private String currencyProvisionFileContentType;

    @Column(name = "is_migrational")
    private Boolean isMigrational;

    @Column(name = "other_cost", precision = 21, scale = 2)
    private BigDecimal otherCost;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draftReceipt")
    @JsonIgnoreProperties(value = { "product", "draftReceipt" }, allowSetters = true)
    private Set<DraftProductInfo> draftProductInfos = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement productDimension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement stateOfDocuments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement currencyProvisionFileType;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentCurrencyRateType paymentCurrencyRateType;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentItemType paymentItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "receiptIssueDocumentTransaction",
            "freightLetterStampCostDocumentTransaction",
            "deliverDocumentTransaction",
            "documentTransactionCanceledDeliver",
            "documentTransactionCanceledReceiptIssue",
            "receiptCommissionDocumentTransactions",
            "draftDocumentTransactionContainer",
        },
        allowSetters = true
    )
    private DraftReceiptDocumentTransactionContainer documentTransactionContainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "receipts",
            "taxes",
            "extensions",
            "draftFactors",
            "usedAssurances",
            "draftJustifications",
            "chargedExchangeBroker",
            "insuranceLetterType",
            "advisorDepositType",
            "interfaceAdvisorDepositType",
            "coveringAdvisorDepositType",
            "impartType",
            "dealType",
            "transportVehicleType",
            "freightLetterType",
            "actionCode",
            "ownershipCode",
            "currencyContainerPlace",
            "paymentType",
            "draftSource",
            "loadSwitchPlace",
            "draftType",
            "statusInfo",
            "insuranceCompanyInfo",
            "advisingBank",
            "interfaceAdvisingBank",
            "coveringBank",
            "auditCompanyInfo",
            "transportType",
            "currencyExchangeInfo",
            "accountInfo",
            "destinationCustomCompanies",
            "sourceCustomCompanies",
            "services",
            "products",
            "sanctionSerials",
            "customerNumbers",
            "suggestedSanctions",
            "documentTransactionContainers",
        },
        allowSetters = true
    )
    private Draft draft;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "draftReceipts")
    @JsonIgnoreProperties(value = { "draftReceipts", "draft" }, allowSetters = true)
    private Set<DraftCustomJustification> draftCustomJustifications = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftReceipt id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return this.comment;
    }

    public DraftReceipt comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomerDeliverDate() {
        return this.customerDeliverDate;
    }

    public DraftReceipt customerDeliverDate(String customerDeliverDate) {
        this.setCustomerDeliverDate(customerDeliverDate);
        return this;
    }

    public void setCustomerDeliverDate(String customerDeliverDate) {
        this.customerDeliverDate = customerDeliverDate;
    }

    public String getDate() {
        return this.date;
    }

    public DraftReceipt date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeleteDate() {
        return this.deleteDate;
    }

    public DraftReceipt deleteDate(String deleteDate) {
        this.setDeleteDate(deleteDate);
        return this;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getDeliverDate() {
        return this.deliverDate;
    }

    public DraftReceipt deliverDate(String deliverDate) {
        this.setDeliverDate(deliverDate);
        return this;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Integer getDeliverDuration() {
        return this.deliverDuration;
    }

    public DraftReceipt deliverDuration(Integer deliverDuration) {
        this.setDeliverDuration(deliverDuration);
        return this;
    }

    public void setDeliverDuration(Integer deliverDuration) {
        this.deliverDuration = deliverDuration;
    }

    public Boolean getDelivered() {
        return this.delivered;
    }

    public DraftReceipt delivered(Boolean delivered) {
        this.setDelivered(delivered);
        return this;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public String getDocumentTransactionEffectiveDate() {
        return this.documentTransactionEffectiveDate;
    }

    public DraftReceipt documentTransactionEffectiveDate(String documentTransactionEffectiveDate) {
        this.setDocumentTransactionEffectiveDate(documentTransactionEffectiveDate);
        return this;
    }

    public void setDocumentTransactionEffectiveDate(String documentTransactionEffectiveDate) {
        this.documentTransactionEffectiveDate = documentTransactionEffectiveDate;
    }

    public String getFreightLetterDate() {
        return this.freightLetterDate;
    }

    public DraftReceipt freightLetterDate(String freightLetterDate) {
        this.setFreightLetterDate(freightLetterDate);
        return this;
    }

    public void setFreightLetterDate(String freightLetterDate) {
        this.freightLetterDate = freightLetterDate;
    }

    public String getFreightLetterNumber() {
        return this.freightLetterNumber;
    }

    public DraftReceipt freightLetterNumber(String freightLetterNumber) {
        this.setFreightLetterNumber(freightLetterNumber);
        return this;
    }

    public void setFreightLetterNumber(String freightLetterNumber) {
        this.freightLetterNumber = freightLetterNumber;
    }

    public BigDecimal getFreightLetterStampCost() {
        return this.freightLetterStampCost;
    }

    public DraftReceipt freightLetterStampCost(BigDecimal freightLetterStampCost) {
        this.setFreightLetterStampCost(freightLetterStampCost);
        return this;
    }

    public void setFreightLetterStampCost(BigDecimal freightLetterStampCost) {
        this.freightLetterStampCost = freightLetterStampCost;
    }

    public String getIssueDate() {
        return this.issueDate;
    }

    public DraftReceipt issueDate(String issueDate) {
        this.setIssueDate(issueDate);
        return this;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public Boolean getIssueDocument() {
        return this.issueDocument;
    }

    public DraftReceipt issueDocument(Boolean issueDocument) {
        this.setIssueDocument(issueDocument);
        return this;
    }

    public void setIssueDocument(Boolean issueDocument) {
        this.issueDocument = issueDocument;
    }

    public BigDecimal getProductAmount() {
        return this.productAmount;
    }

    public DraftReceipt productAmount(BigDecimal productAmount) {
        this.setProductAmount(productAmount);
        return this;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getReceiptAmount() {
        return this.receiptAmount;
    }

    public DraftReceipt receiptAmount(BigDecimal receiptAmount) {
        this.setReceiptAmount(receiptAmount);
        return this;
    }

    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getReceiptDate() {
        return this.receiptDate;
    }

    public DraftReceipt receiptDate(String receiptDate) {
        this.setReceiptDate(receiptDate);
        return this;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Integer getRow() {
        return this.row;
    }

    public DraftReceipt row(Integer row) {
        this.setRow(row);
        return this;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getSerial() {
        return this.serial;
    }

    public DraftReceipt serial(String serial) {
        this.setSerial(serial);
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTransportRow() {
        return this.transportRow;
    }

    public DraftReceipt transportRow(String transportRow) {
        this.setTransportRow(transportRow);
        return this;
    }

    public void setTransportRow(String transportRow) {
        this.transportRow = transportRow;
    }

    public Boolean getUsable() {
        return this.usable;
    }

    public DraftReceipt usable(Boolean usable) {
        this.setUsable(usable);
        return this;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    public BigDecimal getNetWeight() {
        return this.netWeight;
    }

    public DraftReceipt netWeight(BigDecimal netWeight) {
        this.setNetWeight(netWeight);
        return this;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public BigDecimal getGrossWeight() {
        return this.grossWeight;
    }

    public DraftReceipt grossWeight(BigDecimal grossWeight) {
        this.setGrossWeight(grossWeight);
        return this;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getTotalNetWeight() {
        return this.totalNetWeight;
    }

    public DraftReceipt totalNetWeight(BigDecimal totalNetWeight) {
        this.setTotalNetWeight(totalNetWeight);
        return this;
    }

    public void setTotalNetWeight(BigDecimal totalNetWeight) {
        this.totalNetWeight = totalNetWeight;
    }

    public BigDecimal getTotalGrossWeight() {
        return this.totalGrossWeight;
    }

    public DraftReceipt totalGrossWeight(BigDecimal totalGrossWeight) {
        this.setTotalGrossWeight(totalGrossWeight);
        return this;
    }

    public void setTotalGrossWeight(BigDecimal totalGrossWeight) {
        this.totalGrossWeight = totalGrossWeight;
    }

    public String getLetterNumberTazirat() {
        return this.letterNumberTazirat;
    }

    public DraftReceipt letterNumberTazirat(String letterNumberTazirat) {
        this.setLetterNumberTazirat(letterNumberTazirat);
        return this;
    }

    public void setLetterNumberTazirat(String letterNumberTazirat) {
        this.letterNumberTazirat = letterNumberTazirat;
    }

    public String getLetterDateTazirat() {
        return this.letterDateTazirat;
    }

    public DraftReceipt letterDateTazirat(String letterDateTazirat) {
        this.setLetterDateTazirat(letterDateTazirat);
        return this;
    }

    public void setLetterDateTazirat(String letterDateTazirat) {
        this.letterDateTazirat = letterDateTazirat;
    }

    public BigDecimal getFobAmount() {
        return this.fobAmount;
    }

    public DraftReceipt fobAmount(BigDecimal fobAmount) {
        this.setFobAmount(fobAmount);
        return this;
    }

    public void setFobAmount(BigDecimal fobAmount) {
        this.fobAmount = fobAmount;
    }

    public BigDecimal getShippingFare() {
        return this.shippingFare;
    }

    public DraftReceipt shippingFare(BigDecimal shippingFare) {
        this.setShippingFare(shippingFare);
        return this;
    }

    public void setShippingFare(BigDecimal shippingFare) {
        this.shippingFare = shippingFare;
    }

    public BigDecimal getInspectionCost() {
        return this.inspectionCost;
    }

    public DraftReceipt inspectionCost(BigDecimal inspectionCost) {
        this.setInspectionCost(inspectionCost);
        return this;
    }

    public void setInspectionCost(BigDecimal inspectionCost) {
        this.inspectionCost = inspectionCost;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public DraftReceipt discount(BigDecimal discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDeadlineSubmitDocumentDate() {
        return this.deadlineSubmitDocumentDate;
    }

    public DraftReceipt deadlineSubmitDocumentDate(String deadlineSubmitDocumentDate) {
        this.setDeadlineSubmitDocumentDate(deadlineSubmitDocumentDate);
        return this;
    }

    public void setDeadlineSubmitDocumentDate(String deadlineSubmitDocumentDate) {
        this.deadlineSubmitDocumentDate = deadlineSubmitDocumentDate;
    }

    public byte[] getCurrencyProvisionFile() {
        return this.currencyProvisionFile;
    }

    public DraftReceipt currencyProvisionFile(byte[] currencyProvisionFile) {
        this.setCurrencyProvisionFile(currencyProvisionFile);
        return this;
    }

    public void setCurrencyProvisionFile(byte[] currencyProvisionFile) {
        this.currencyProvisionFile = currencyProvisionFile;
    }

    public String getCurrencyProvisionFileContentType() {
        return this.currencyProvisionFileContentType;
    }

    public DraftReceipt currencyProvisionFileContentType(String currencyProvisionFileContentType) {
        this.currencyProvisionFileContentType = currencyProvisionFileContentType;
        return this;
    }

    public void setCurrencyProvisionFileContentType(String currencyProvisionFileContentType) {
        this.currencyProvisionFileContentType = currencyProvisionFileContentType;
    }

    public Boolean getIsMigrational() {
        return this.isMigrational;
    }

    public DraftReceipt isMigrational(Boolean isMigrational) {
        this.setIsMigrational(isMigrational);
        return this;
    }

    public void setIsMigrational(Boolean isMigrational) {
        this.isMigrational = isMigrational;
    }

    public BigDecimal getOtherCost() {
        return this.otherCost;
    }

    public DraftReceipt otherCost(BigDecimal otherCost) {
        this.setOtherCost(otherCost);
        return this;
    }

    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    public Set<DraftProductInfo> getDraftProductInfos() {
        return this.draftProductInfos;
    }

    public void setDraftProductInfos(Set<DraftProductInfo> draftProductInfos) {
        if (this.draftProductInfos != null) {
            this.draftProductInfos.forEach(i -> i.setDraftReceipt(null));
        }
        if (draftProductInfos != null) {
            draftProductInfos.forEach(i -> i.setDraftReceipt(this));
        }
        this.draftProductInfos = draftProductInfos;
    }

    public DraftReceipt draftProductInfos(Set<DraftProductInfo> draftProductInfos) {
        this.setDraftProductInfos(draftProductInfos);
        return this;
    }

    public DraftReceipt addDraftProductInfos(DraftProductInfo draftProductInfo) {
        this.draftProductInfos.add(draftProductInfo);
        draftProductInfo.setDraftReceipt(this);
        return this;
    }

    public DraftReceipt removeDraftProductInfos(DraftProductInfo draftProductInfo) {
        this.draftProductInfos.remove(draftProductInfo);
        draftProductInfo.setDraftReceipt(null);
        return this;
    }

    public CategoryElement getProductDimension() {
        return this.productDimension;
    }

    public void setProductDimension(CategoryElement categoryElement) {
        this.productDimension = categoryElement;
    }

    public DraftReceipt productDimension(CategoryElement categoryElement) {
        this.setProductDimension(categoryElement);
        return this;
    }

    public CategoryElement getStateOfDocuments() {
        return this.stateOfDocuments;
    }

    public void setStateOfDocuments(CategoryElement categoryElement) {
        this.stateOfDocuments = categoryElement;
    }

    public DraftReceipt stateOfDocuments(CategoryElement categoryElement) {
        this.setStateOfDocuments(categoryElement);
        return this;
    }

    public CategoryElement getCurrencyProvisionFileType() {
        return this.currencyProvisionFileType;
    }

    public void setCurrencyProvisionFileType(CategoryElement categoryElement) {
        this.currencyProvisionFileType = categoryElement;
    }

    public DraftReceipt currencyProvisionFileType(CategoryElement categoryElement) {
        this.setCurrencyProvisionFileType(categoryElement);
        return this;
    }

    public PaymentCurrencyRateType getPaymentCurrencyRateType() {
        return this.paymentCurrencyRateType;
    }

    public void setPaymentCurrencyRateType(PaymentCurrencyRateType paymentCurrencyRateType) {
        this.paymentCurrencyRateType = paymentCurrencyRateType;
    }

    public DraftReceipt paymentCurrencyRateType(PaymentCurrencyRateType paymentCurrencyRateType) {
        this.setPaymentCurrencyRateType(paymentCurrencyRateType);
        return this;
    }

    public PaymentItemType getPaymentItem() {
        return this.paymentItem;
    }

    public void setPaymentItem(PaymentItemType paymentItemType) {
        this.paymentItem = paymentItemType;
    }

    public DraftReceipt paymentItem(PaymentItemType paymentItemType) {
        this.setPaymentItem(paymentItemType);
        return this;
    }

    public DraftReceiptDocumentTransactionContainer getDocumentTransactionContainer() {
        return this.documentTransactionContainer;
    }

    public void setDocumentTransactionContainer(DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer) {
        this.documentTransactionContainer = draftReceiptDocumentTransactionContainer;
    }

    public DraftReceipt documentTransactionContainer(DraftReceiptDocumentTransactionContainer draftReceiptDocumentTransactionContainer) {
        this.setDocumentTransactionContainer(draftReceiptDocumentTransactionContainer);
        return this;
    }

    public Draft getDraft() {
        return this.draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    public DraftReceipt draft(Draft draft) {
        this.setDraft(draft);
        return this;
    }

    public Set<DraftCustomJustification> getDraftCustomJustifications() {
        return this.draftCustomJustifications;
    }

    public void setDraftCustomJustifications(Set<DraftCustomJustification> draftCustomJustifications) {
        if (this.draftCustomJustifications != null) {
            this.draftCustomJustifications.forEach(i -> i.removeDraftReceipts(this));
        }
        if (draftCustomJustifications != null) {
            draftCustomJustifications.forEach(i -> i.addDraftReceipts(this));
        }
        this.draftCustomJustifications = draftCustomJustifications;
    }

    public DraftReceipt draftCustomJustifications(Set<DraftCustomJustification> draftCustomJustifications) {
        this.setDraftCustomJustifications(draftCustomJustifications);
        return this;
    }

    public DraftReceipt addDraftCustomJustification(DraftCustomJustification draftCustomJustification) {
        this.draftCustomJustifications.add(draftCustomJustification);
        draftCustomJustification.getDraftReceipts().add(this);
        return this;
    }

    public DraftReceipt removeDraftCustomJustification(DraftCustomJustification draftCustomJustification) {
        this.draftCustomJustifications.remove(draftCustomJustification);
        draftCustomJustification.getDraftReceipts().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftReceipt)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftReceipt) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftReceipt{" +
            "id=" + getId() +
            ", comment='" + getComment() + "'" +
            ", customerDeliverDate='" + getCustomerDeliverDate() + "'" +
            ", date='" + getDate() + "'" +
            ", deleteDate='" + getDeleteDate() + "'" +
            ", deliverDate='" + getDeliverDate() + "'" +
            ", deliverDuration=" + getDeliverDuration() +
            ", delivered='" + getDelivered() + "'" +
            ", documentTransactionEffectiveDate='" + getDocumentTransactionEffectiveDate() + "'" +
            ", freightLetterDate='" + getFreightLetterDate() + "'" +
            ", freightLetterNumber='" + getFreightLetterNumber() + "'" +
            ", freightLetterStampCost=" + getFreightLetterStampCost() +
            ", issueDate='" + getIssueDate() + "'" +
            ", issueDocument='" + getIssueDocument() + "'" +
            ", productAmount=" + getProductAmount() +
            ", receiptAmount=" + getReceiptAmount() +
            ", receiptDate='" + getReceiptDate() + "'" +
            ", row=" + getRow() +
            ", serial='" + getSerial() + "'" +
            ", transportRow='" + getTransportRow() + "'" +
            ", usable='" + getUsable() + "'" +
            ", netWeight=" + getNetWeight() +
            ", grossWeight=" + getGrossWeight() +
            ", totalNetWeight=" + getTotalNetWeight() +
            ", totalGrossWeight=" + getTotalGrossWeight() +
            ", letterNumberTazirat='" + getLetterNumberTazirat() + "'" +
            ", letterDateTazirat='" + getLetterDateTazirat() + "'" +
            ", fobAmount=" + getFobAmount() +
            ", shippingFare=" + getShippingFare() +
            ", inspectionCost=" + getInspectionCost() +
            ", discount=" + getDiscount() +
            ", deadlineSubmitDocumentDate='" + getDeadlineSubmitDocumentDate() + "'" +
            ", currencyProvisionFile='" + getCurrencyProvisionFile() + "'" +
            ", currencyProvisionFileContentType='" + getCurrencyProvisionFileContentType() + "'" +
            ", isMigrational='" + getIsMigrational() + "'" +
            ", otherCost=" + getOtherCost() +
            "}";
    }
}
