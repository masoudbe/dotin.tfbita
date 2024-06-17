package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A AdvisorDefinition.
 */
@Entity
@Table(name = "advisor_definition")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AdvisorDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "code")
    private String code;

    @Column(name = "country_iso_code")
    private String countryIsoCode;

    @Column(name = "deposit_num")
    private String depositNum;

    @Column(name = "swift_code")
    private String swiftCode;

    @Column(name = "credit_date")
    private String creditDate;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "default_currency_code")
    private String defaultCurrencyCode;

    @Column(name = "country_code")
    private String countryCode;

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
    private Draft advisingBank;

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
    private Draft interfaceAdvisingBank;

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
    private Draft coveringBank;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AdvisorDefinition id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return this.caption;
    }

    public AdvisorDefinition caption(String caption) {
        this.setCaption(caption);
        return this;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCode() {
        return this.code;
    }

    public AdvisorDefinition code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryIsoCode() {
        return this.countryIsoCode;
    }

    public AdvisorDefinition countryIsoCode(String countryIsoCode) {
        this.setCountryIsoCode(countryIsoCode);
        return this;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getDepositNum() {
        return this.depositNum;
    }

    public AdvisorDefinition depositNum(String depositNum) {
        this.setDepositNum(depositNum);
        return this;
    }

    public void setDepositNum(String depositNum) {
        this.depositNum = depositNum;
    }

    public String getSwiftCode() {
        return this.swiftCode;
    }

    public AdvisorDefinition swiftCode(String swiftCode) {
        this.setSwiftCode(swiftCode);
        return this;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getCreditDate() {
        return this.creditDate;
    }

    public AdvisorDefinition creditDate(String creditDate) {
        this.setCreditDate(creditDate);
        return this;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public AdvisorDefinition bankCode(String bankCode) {
        this.setBankCode(bankCode);
        return this;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return this.branchCode;
    }

    public AdvisorDefinition branchCode(String branchCode) {
        this.setBranchCode(branchCode);
        return this;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getDefaultCurrencyCode() {
        return this.defaultCurrencyCode;
    }

    public AdvisorDefinition defaultCurrencyCode(String defaultCurrencyCode) {
        this.setDefaultCurrencyCode(defaultCurrencyCode);
        return this;
    }

    public void setDefaultCurrencyCode(String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public AdvisorDefinition countryCode(String countryCode) {
        this.setCountryCode(countryCode);
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Draft getAdvisingBank() {
        return this.advisingBank;
    }

    public void setAdvisingBank(Draft draft) {
        this.advisingBank = draft;
    }

    public AdvisorDefinition advisingBank(Draft draft) {
        this.setAdvisingBank(draft);
        return this;
    }

    public Draft getInterfaceAdvisingBank() {
        return this.interfaceAdvisingBank;
    }

    public void setInterfaceAdvisingBank(Draft draft) {
        this.interfaceAdvisingBank = draft;
    }

    public AdvisorDefinition interfaceAdvisingBank(Draft draft) {
        this.setInterfaceAdvisingBank(draft);
        return this;
    }

    public Draft getCoveringBank() {
        return this.coveringBank;
    }

    public void setCoveringBank(Draft draft) {
        this.coveringBank = draft;
    }

    public AdvisorDefinition coveringBank(Draft draft) {
        this.setCoveringBank(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdvisorDefinition)) {
            return false;
        }
        return getId() != null && getId().equals(((AdvisorDefinition) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdvisorDefinition{" +
            "id=" + getId() +
            ", caption='" + getCaption() + "'" +
            ", code='" + getCode() + "'" +
            ", countryIsoCode='" + getCountryIsoCode() + "'" +
            ", depositNum='" + getDepositNum() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            ", creditDate='" + getCreditDate() + "'" +
            ", bankCode='" + getBankCode() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            ", defaultCurrencyCode='" + getDefaultCurrencyCode() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            "}";
    }
}
