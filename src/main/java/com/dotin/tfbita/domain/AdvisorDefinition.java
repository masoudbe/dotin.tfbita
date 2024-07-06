package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "default_currency_code")
    private String defaultCurrencyCode;

    @Column(name = "currencies_codes")
    private String currenciesCodes;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "branch_code")
    private String branchCode;

    @JsonIgnoreProperties(value = { "advisorDefinition" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private AdditionalBrokerInformation additionalBrokerInformation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advisorDefinition")
    @JsonIgnoreProperties(value = { "depositType", "advisorDefinition" }, allowSetters = true)
    private Set<AdvisorDefinitionDeposit> advisorDeposits = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "depositType", "advisorDefinition" }, allowSetters = true)
    private AdvisorDefinitionDeposit defaultVostroDeposit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "depositType", "advisorDefinition" }, allowSetters = true)
    private AdvisorDefinitionDeposit defaultNostroDeposit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "type" }, allowSetters = true)
    private TransferMethodManagement receiveMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "type" }, allowSetters = true)
    private TransferMethodManagement payMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    private SwiftBic swiftBic;

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

    public String getCurrenciesCodes() {
        return this.currenciesCodes;
    }

    public AdvisorDefinition currenciesCodes(String currenciesCodes) {
        this.setCurrenciesCodes(currenciesCodes);
        return this;
    }

    public void setCurrenciesCodes(String currenciesCodes) {
        this.currenciesCodes = currenciesCodes;
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

    public AdditionalBrokerInformation getAdditionalBrokerInformation() {
        return this.additionalBrokerInformation;
    }

    public void setAdditionalBrokerInformation(AdditionalBrokerInformation additionalBrokerInformation) {
        this.additionalBrokerInformation = additionalBrokerInformation;
    }

    public AdvisorDefinition additionalBrokerInformation(AdditionalBrokerInformation additionalBrokerInformation) {
        this.setAdditionalBrokerInformation(additionalBrokerInformation);
        return this;
    }

    public Set<AdvisorDefinitionDeposit> getAdvisorDeposits() {
        return this.advisorDeposits;
    }

    public void setAdvisorDeposits(Set<AdvisorDefinitionDeposit> advisorDefinitionDeposits) {
        if (this.advisorDeposits != null) {
            this.advisorDeposits.forEach(i -> i.setAdvisorDefinition(null));
        }
        if (advisorDefinitionDeposits != null) {
            advisorDefinitionDeposits.forEach(i -> i.setAdvisorDefinition(this));
        }
        this.advisorDeposits = advisorDefinitionDeposits;
    }

    public AdvisorDefinition advisorDeposits(Set<AdvisorDefinitionDeposit> advisorDefinitionDeposits) {
        this.setAdvisorDeposits(advisorDefinitionDeposits);
        return this;
    }

    public AdvisorDefinition addAdvisorDeposit(AdvisorDefinitionDeposit advisorDefinitionDeposit) {
        this.advisorDeposits.add(advisorDefinitionDeposit);
        advisorDefinitionDeposit.setAdvisorDefinition(this);
        return this;
    }

    public AdvisorDefinition removeAdvisorDeposit(AdvisorDefinitionDeposit advisorDefinitionDeposit) {
        this.advisorDeposits.remove(advisorDefinitionDeposit);
        advisorDefinitionDeposit.setAdvisorDefinition(null);
        return this;
    }

    public AdvisorDefinitionDeposit getDefaultVostroDeposit() {
        return this.defaultVostroDeposit;
    }

    public void setDefaultVostroDeposit(AdvisorDefinitionDeposit advisorDefinitionDeposit) {
        this.defaultVostroDeposit = advisorDefinitionDeposit;
    }

    public AdvisorDefinition defaultVostroDeposit(AdvisorDefinitionDeposit advisorDefinitionDeposit) {
        this.setDefaultVostroDeposit(advisorDefinitionDeposit);
        return this;
    }

    public AdvisorDefinitionDeposit getDefaultNostroDeposit() {
        return this.defaultNostroDeposit;
    }

    public void setDefaultNostroDeposit(AdvisorDefinitionDeposit advisorDefinitionDeposit) {
        this.defaultNostroDeposit = advisorDefinitionDeposit;
    }

    public AdvisorDefinition defaultNostroDeposit(AdvisorDefinitionDeposit advisorDefinitionDeposit) {
        this.setDefaultNostroDeposit(advisorDefinitionDeposit);
        return this;
    }

    public TransferMethodManagement getReceiveMethod() {
        return this.receiveMethod;
    }

    public void setReceiveMethod(TransferMethodManagement transferMethodManagement) {
        this.receiveMethod = transferMethodManagement;
    }

    public AdvisorDefinition receiveMethod(TransferMethodManagement transferMethodManagement) {
        this.setReceiveMethod(transferMethodManagement);
        return this;
    }

    public TransferMethodManagement getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(TransferMethodManagement transferMethodManagement) {
        this.payMethod = transferMethodManagement;
    }

    public AdvisorDefinition payMethod(TransferMethodManagement transferMethodManagement) {
        this.setPayMethod(transferMethodManagement);
        return this;
    }

    public SwiftBic getSwiftBic() {
        return this.swiftBic;
    }

    public void setSwiftBic(SwiftBic swiftBic) {
        this.swiftBic = swiftBic;
    }

    public AdvisorDefinition swiftBic(SwiftBic swiftBic) {
        this.setSwiftBic(swiftBic);
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
            ", defaultCurrencyCode='" + getDefaultCurrencyCode() + "'" +
            ", currenciesCodes='" + getCurrenciesCodes() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", bankCode='" + getBankCode() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            "}";
    }
}
