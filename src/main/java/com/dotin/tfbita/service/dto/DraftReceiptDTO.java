package com.dotin.tfbita.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftReceipt} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftReceiptDTO implements Serializable {

    private Long id;

    private String comment;

    private String customerDeliverDate;

    private String date;

    private String deleteDate;

    private String deliverDate;

    private Integer deliverDuration;

    private Boolean delivered;

    private String documentTransactionEffectiveDate;

    private String freightLetterDate;

    private String freightLetterNumber;

    private BigDecimal freightLetterStampCost;

    private String issueDate;

    private Boolean issueDocument;

    private BigDecimal productAmount;

    private BigDecimal receiptAmount;

    private String receiptDate;

    private Integer row;

    private String serial;

    private String transportRow;

    private Boolean usable;

    private String paymentCurrencyRateTypeDesc;

    private String paymentItemTypeDesc;

    private BigDecimal netWeight;

    private BigDecimal grossWeight;

    private BigDecimal totalNetWeight;

    private BigDecimal totalGrossWeight;

    private String letterNumberTazirat;

    private String letterDateTazirat;

    private BigDecimal fobAmount;

    private BigDecimal shippingFare;

    private BigDecimal inspectionCost;

    private BigDecimal discount;

    private String deadlineSubmitDocumentDate;

    @Lob
    private byte[] currencyProvisionFile;

    private String currencyProvisionFileContentType;

    private Boolean isMigrational;

    private BigDecimal otherCost;

    private DraftDTO receipts;

    private Set<DraftCustomJustificationDTO> draftCustomJustifications = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomerDeliverDate() {
        return customerDeliverDate;
    }

    public void setCustomerDeliverDate(String customerDeliverDate) {
        this.customerDeliverDate = customerDeliverDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Integer getDeliverDuration() {
        return deliverDuration;
    }

    public void setDeliverDuration(Integer deliverDuration) {
        this.deliverDuration = deliverDuration;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public String getDocumentTransactionEffectiveDate() {
        return documentTransactionEffectiveDate;
    }

    public void setDocumentTransactionEffectiveDate(String documentTransactionEffectiveDate) {
        this.documentTransactionEffectiveDate = documentTransactionEffectiveDate;
    }

    public String getFreightLetterDate() {
        return freightLetterDate;
    }

    public void setFreightLetterDate(String freightLetterDate) {
        this.freightLetterDate = freightLetterDate;
    }

    public String getFreightLetterNumber() {
        return freightLetterNumber;
    }

    public void setFreightLetterNumber(String freightLetterNumber) {
        this.freightLetterNumber = freightLetterNumber;
    }

    public BigDecimal getFreightLetterStampCost() {
        return freightLetterStampCost;
    }

    public void setFreightLetterStampCost(BigDecimal freightLetterStampCost) {
        this.freightLetterStampCost = freightLetterStampCost;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public Boolean getIssueDocument() {
        return issueDocument;
    }

    public void setIssueDocument(Boolean issueDocument) {
        this.issueDocument = issueDocument;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(BigDecimal receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTransportRow() {
        return transportRow;
    }

    public void setTransportRow(String transportRow) {
        this.transportRow = transportRow;
    }

    public Boolean getUsable() {
        return usable;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    public String getPaymentCurrencyRateTypeDesc() {
        return paymentCurrencyRateTypeDesc;
    }

    public void setPaymentCurrencyRateTypeDesc(String paymentCurrencyRateTypeDesc) {
        this.paymentCurrencyRateTypeDesc = paymentCurrencyRateTypeDesc;
    }

    public String getPaymentItemTypeDesc() {
        return paymentItemTypeDesc;
    }

    public void setPaymentItemTypeDesc(String paymentItemTypeDesc) {
        this.paymentItemTypeDesc = paymentItemTypeDesc;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getTotalNetWeight() {
        return totalNetWeight;
    }

    public void setTotalNetWeight(BigDecimal totalNetWeight) {
        this.totalNetWeight = totalNetWeight;
    }

    public BigDecimal getTotalGrossWeight() {
        return totalGrossWeight;
    }

    public void setTotalGrossWeight(BigDecimal totalGrossWeight) {
        this.totalGrossWeight = totalGrossWeight;
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

    public BigDecimal getFobAmount() {
        return fobAmount;
    }

    public void setFobAmount(BigDecimal fobAmount) {
        this.fobAmount = fobAmount;
    }

    public BigDecimal getShippingFare() {
        return shippingFare;
    }

    public void setShippingFare(BigDecimal shippingFare) {
        this.shippingFare = shippingFare;
    }

    public BigDecimal getInspectionCost() {
        return inspectionCost;
    }

    public void setInspectionCost(BigDecimal inspectionCost) {
        this.inspectionCost = inspectionCost;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDeadlineSubmitDocumentDate() {
        return deadlineSubmitDocumentDate;
    }

    public void setDeadlineSubmitDocumentDate(String deadlineSubmitDocumentDate) {
        this.deadlineSubmitDocumentDate = deadlineSubmitDocumentDate;
    }

    public byte[] getCurrencyProvisionFile() {
        return currencyProvisionFile;
    }

    public void setCurrencyProvisionFile(byte[] currencyProvisionFile) {
        this.currencyProvisionFile = currencyProvisionFile;
    }

    public String getCurrencyProvisionFileContentType() {
        return currencyProvisionFileContentType;
    }

    public void setCurrencyProvisionFileContentType(String currencyProvisionFileContentType) {
        this.currencyProvisionFileContentType = currencyProvisionFileContentType;
    }

    public Boolean getIsMigrational() {
        return isMigrational;
    }

    public void setIsMigrational(Boolean isMigrational) {
        this.isMigrational = isMigrational;
    }

    public BigDecimal getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(BigDecimal otherCost) {
        this.otherCost = otherCost;
    }

    public DraftDTO getReceipts() {
        return receipts;
    }

    public void setReceipts(DraftDTO receipts) {
        this.receipts = receipts;
    }

    public Set<DraftCustomJustificationDTO> getDraftCustomJustifications() {
        return draftCustomJustifications;
    }

    public void setDraftCustomJustifications(Set<DraftCustomJustificationDTO> draftCustomJustifications) {
        this.draftCustomJustifications = draftCustomJustifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftReceiptDTO)) {
            return false;
        }

        DraftReceiptDTO draftReceiptDTO = (DraftReceiptDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftReceiptDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftReceiptDTO{" +
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
            ", paymentCurrencyRateTypeDesc='" + getPaymentCurrencyRateTypeDesc() + "'" +
            ", paymentItemTypeDesc='" + getPaymentItemTypeDesc() + "'" +
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
            ", isMigrational='" + getIsMigrational() + "'" +
            ", otherCost=" + getOtherCost() +
            ", receipts=" + getReceipts() +
            ", draftCustomJustifications=" + getDraftCustomJustifications() +
            "}";
    }
}
