package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DraftTax.
 */
@Entity
@Table(name = "draft_tax")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftTax implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "order_reg_currency_amount", precision = 21, scale = 2)
    private BigDecimal orderRegCurrencyAmount;

    @Column(name = "main_account_currency_amount", precision = 21, scale = 2)
    private BigDecimal mainAccountCurrencyAmount;

    @Column(name = "letter_number")
    private String letterNumber;

    @Lob
    @Column(name = "letter_image")
    private byte[] letterImage;

    @Column(name = "letter_image_content_type")
    private String letterImageContentType;

    @Column(name = "description")
    private String description;

    @Column(name = "registration_date")
    private String registrationDate;

    @Column(name = "return_taxes_amount")
    private Boolean returnTaxesAmount;

    @Column(name = "order_reg_rate", precision = 21, scale = 2)
    private BigDecimal orderRegRate;

    @Column(name = "main_account_rate", precision = 21, scale = 2)
    private BigDecimal mainAccountRate;

    @Column(name = "document_transaction_number")
    private String documentTransactionNumber;

    @Column(name = "return_document_transaction_number")
    private String returnDocumentTransactionNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft taxes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftTax id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getOrderRegCurrencyAmount() {
        return this.orderRegCurrencyAmount;
    }

    public DraftTax orderRegCurrencyAmount(BigDecimal orderRegCurrencyAmount) {
        this.setOrderRegCurrencyAmount(orderRegCurrencyAmount);
        return this;
    }

    public void setOrderRegCurrencyAmount(BigDecimal orderRegCurrencyAmount) {
        this.orderRegCurrencyAmount = orderRegCurrencyAmount;
    }

    public BigDecimal getMainAccountCurrencyAmount() {
        return this.mainAccountCurrencyAmount;
    }

    public DraftTax mainAccountCurrencyAmount(BigDecimal mainAccountCurrencyAmount) {
        this.setMainAccountCurrencyAmount(mainAccountCurrencyAmount);
        return this;
    }

    public void setMainAccountCurrencyAmount(BigDecimal mainAccountCurrencyAmount) {
        this.mainAccountCurrencyAmount = mainAccountCurrencyAmount;
    }

    public String getLetterNumber() {
        return this.letterNumber;
    }

    public DraftTax letterNumber(String letterNumber) {
        this.setLetterNumber(letterNumber);
        return this;
    }

    public void setLetterNumber(String letterNumber) {
        this.letterNumber = letterNumber;
    }

    public byte[] getLetterImage() {
        return this.letterImage;
    }

    public DraftTax letterImage(byte[] letterImage) {
        this.setLetterImage(letterImage);
        return this;
    }

    public void setLetterImage(byte[] letterImage) {
        this.letterImage = letterImage;
    }

    public String getLetterImageContentType() {
        return this.letterImageContentType;
    }

    public DraftTax letterImageContentType(String letterImageContentType) {
        this.letterImageContentType = letterImageContentType;
        return this;
    }

    public void setLetterImageContentType(String letterImageContentType) {
        this.letterImageContentType = letterImageContentType;
    }

    public String getDescription() {
        return this.description;
    }

    public DraftTax description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegistrationDate() {
        return this.registrationDate;
    }

    public DraftTax registrationDate(String registrationDate) {
        this.setRegistrationDate(registrationDate);
        return this;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getReturnTaxesAmount() {
        return this.returnTaxesAmount;
    }

    public DraftTax returnTaxesAmount(Boolean returnTaxesAmount) {
        this.setReturnTaxesAmount(returnTaxesAmount);
        return this;
    }

    public void setReturnTaxesAmount(Boolean returnTaxesAmount) {
        this.returnTaxesAmount = returnTaxesAmount;
    }

    public BigDecimal getOrderRegRate() {
        return this.orderRegRate;
    }

    public DraftTax orderRegRate(BigDecimal orderRegRate) {
        this.setOrderRegRate(orderRegRate);
        return this;
    }

    public void setOrderRegRate(BigDecimal orderRegRate) {
        this.orderRegRate = orderRegRate;
    }

    public BigDecimal getMainAccountRate() {
        return this.mainAccountRate;
    }

    public DraftTax mainAccountRate(BigDecimal mainAccountRate) {
        this.setMainAccountRate(mainAccountRate);
        return this;
    }

    public void setMainAccountRate(BigDecimal mainAccountRate) {
        this.mainAccountRate = mainAccountRate;
    }

    public String getDocumentTransactionNumber() {
        return this.documentTransactionNumber;
    }

    public DraftTax documentTransactionNumber(String documentTransactionNumber) {
        this.setDocumentTransactionNumber(documentTransactionNumber);
        return this;
    }

    public void setDocumentTransactionNumber(String documentTransactionNumber) {
        this.documentTransactionNumber = documentTransactionNumber;
    }

    public String getReturnDocumentTransactionNumber() {
        return this.returnDocumentTransactionNumber;
    }

    public DraftTax returnDocumentTransactionNumber(String returnDocumentTransactionNumber) {
        this.setReturnDocumentTransactionNumber(returnDocumentTransactionNumber);
        return this;
    }

    public void setReturnDocumentTransactionNumber(String returnDocumentTransactionNumber) {
        this.returnDocumentTransactionNumber = returnDocumentTransactionNumber;
    }

    public Draft getTaxes() {
        return this.taxes;
    }

    public void setTaxes(Draft draft) {
        this.taxes = draft;
    }

    public DraftTax taxes(Draft draft) {
        this.setTaxes(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftTax)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftTax) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftTax{" +
            "id=" + getId() +
            ", orderRegCurrencyAmount=" + getOrderRegCurrencyAmount() +
            ", mainAccountCurrencyAmount=" + getMainAccountCurrencyAmount() +
            ", letterNumber='" + getLetterNumber() + "'" +
            ", letterImage='" + getLetterImage() + "'" +
            ", letterImageContentType='" + getLetterImageContentType() + "'" +
            ", description='" + getDescription() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            ", returnTaxesAmount='" + getReturnTaxesAmount() + "'" +
            ", orderRegRate=" + getOrderRegRate() +
            ", mainAccountRate=" + getMainAccountRate() +
            ", documentTransactionNumber='" + getDocumentTransactionNumber() + "'" +
            ", returnDocumentTransactionNumber='" + getReturnDocumentTransactionNumber() + "'" +
            "}";
    }
}
