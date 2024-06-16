package com.dotin.tfbita.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftTax} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftTaxDTO implements Serializable {

    private Long id;

    private BigDecimal orderRegCurrencyAmount;

    private BigDecimal mainAccountCurrencyAmount;

    private String letterNumber;

    @Lob
    private byte[] letterImage;

    private String letterImageContentType;

    private String description;

    private String registrationDate;

    private Boolean returnTaxesAmount;

    private BigDecimal orderRegRate;

    private BigDecimal mainAccountRate;

    private String documentTransactionNumber;

    private String returnDocumentTransactionNumber;

    private DraftDTO taxes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getOrderRegCurrencyAmount() {
        return orderRegCurrencyAmount;
    }

    public void setOrderRegCurrencyAmount(BigDecimal orderRegCurrencyAmount) {
        this.orderRegCurrencyAmount = orderRegCurrencyAmount;
    }

    public BigDecimal getMainAccountCurrencyAmount() {
        return mainAccountCurrencyAmount;
    }

    public void setMainAccountCurrencyAmount(BigDecimal mainAccountCurrencyAmount) {
        this.mainAccountCurrencyAmount = mainAccountCurrencyAmount;
    }

    public String getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(String letterNumber) {
        this.letterNumber = letterNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getReturnTaxesAmount() {
        return returnTaxesAmount;
    }

    public void setReturnTaxesAmount(Boolean returnTaxesAmount) {
        this.returnTaxesAmount = returnTaxesAmount;
    }

    public BigDecimal getOrderRegRate() {
        return orderRegRate;
    }

    public void setOrderRegRate(BigDecimal orderRegRate) {
        this.orderRegRate = orderRegRate;
    }

    public BigDecimal getMainAccountRate() {
        return mainAccountRate;
    }

    public void setMainAccountRate(BigDecimal mainAccountRate) {
        this.mainAccountRate = mainAccountRate;
    }

    public String getDocumentTransactionNumber() {
        return documentTransactionNumber;
    }

    public void setDocumentTransactionNumber(String documentTransactionNumber) {
        this.documentTransactionNumber = documentTransactionNumber;
    }

    public String getReturnDocumentTransactionNumber() {
        return returnDocumentTransactionNumber;
    }

    public void setReturnDocumentTransactionNumber(String returnDocumentTransactionNumber) {
        this.returnDocumentTransactionNumber = returnDocumentTransactionNumber;
    }

    public DraftDTO getTaxes() {
        return taxes;
    }

    public void setTaxes(DraftDTO taxes) {
        this.taxes = taxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftTaxDTO)) {
            return false;
        }

        DraftTaxDTO draftTaxDTO = (DraftTaxDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftTaxDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftTaxDTO{" +
            "id=" + getId() +
            ", orderRegCurrencyAmount=" + getOrderRegCurrencyAmount() +
            ", mainAccountCurrencyAmount=" + getMainAccountCurrencyAmount() +
            ", letterNumber='" + getLetterNumber() + "'" +
            ", letterImage='" + getLetterImage() + "'" +
            ", description='" + getDescription() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            ", returnTaxesAmount='" + getReturnTaxesAmount() + "'" +
            ", orderRegRate=" + getOrderRegRate() +
            ", mainAccountRate=" + getMainAccountRate() +
            ", documentTransactionNumber='" + getDocumentTransactionNumber() + "'" +
            ", returnDocumentTransactionNumber='" + getReturnDocumentTransactionNumber() + "'" +
            ", taxes=" + getTaxes() +
            "}";
    }
}
