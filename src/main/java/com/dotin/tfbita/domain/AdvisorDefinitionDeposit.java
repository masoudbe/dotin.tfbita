package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A AdvisorDefinitionDeposit.
 */
@Entity
@Table(name = "advisor_definition_deposit")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AdvisorDefinitionDeposit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "advisor_deposit_number")
    private String advisorDepositNumber;

    @Column(name = "default_advisor_deposit")
    private Boolean defaultAdvisorDeposit;

    @Column(name = "deposit_num")
    private String depositNum;

    @Column(name = "swift_code")
    private String swiftCode;

    @Column(name = "currency_code")
    private String currencyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement depositType;

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
    private AdvisorDefinition advisorDefinition;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AdvisorDefinitionDeposit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvisorDepositNumber() {
        return this.advisorDepositNumber;
    }

    public AdvisorDefinitionDeposit advisorDepositNumber(String advisorDepositNumber) {
        this.setAdvisorDepositNumber(advisorDepositNumber);
        return this;
    }

    public void setAdvisorDepositNumber(String advisorDepositNumber) {
        this.advisorDepositNumber = advisorDepositNumber;
    }

    public Boolean getDefaultAdvisorDeposit() {
        return this.defaultAdvisorDeposit;
    }

    public AdvisorDefinitionDeposit defaultAdvisorDeposit(Boolean defaultAdvisorDeposit) {
        this.setDefaultAdvisorDeposit(defaultAdvisorDeposit);
        return this;
    }

    public void setDefaultAdvisorDeposit(Boolean defaultAdvisorDeposit) {
        this.defaultAdvisorDeposit = defaultAdvisorDeposit;
    }

    public String getDepositNum() {
        return this.depositNum;
    }

    public AdvisorDefinitionDeposit depositNum(String depositNum) {
        this.setDepositNum(depositNum);
        return this;
    }

    public void setDepositNum(String depositNum) {
        this.depositNum = depositNum;
    }

    public String getSwiftCode() {
        return this.swiftCode;
    }

    public AdvisorDefinitionDeposit swiftCode(String swiftCode) {
        this.setSwiftCode(swiftCode);
        return this;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public AdvisorDefinitionDeposit currencyCode(String currencyCode) {
        this.setCurrencyCode(currencyCode);
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public CategoryElement getDepositType() {
        return this.depositType;
    }

    public void setDepositType(CategoryElement categoryElement) {
        this.depositType = categoryElement;
    }

    public AdvisorDefinitionDeposit depositType(CategoryElement categoryElement) {
        this.setDepositType(categoryElement);
        return this;
    }

    public AdvisorDefinition getAdvisorDefinition() {
        return this.advisorDefinition;
    }

    public void setAdvisorDefinition(AdvisorDefinition advisorDefinition) {
        this.advisorDefinition = advisorDefinition;
    }

    public AdvisorDefinitionDeposit advisorDefinition(AdvisorDefinition advisorDefinition) {
        this.setAdvisorDefinition(advisorDefinition);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdvisorDefinitionDeposit)) {
            return false;
        }
        return getId() != null && getId().equals(((AdvisorDefinitionDeposit) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdvisorDefinitionDeposit{" +
            "id=" + getId() +
            ", advisorDepositNumber='" + getAdvisorDepositNumber() + "'" +
            ", defaultAdvisorDeposit='" + getDefaultAdvisorDeposit() + "'" +
            ", depositNum='" + getDepositNum() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            "}";
    }
}
