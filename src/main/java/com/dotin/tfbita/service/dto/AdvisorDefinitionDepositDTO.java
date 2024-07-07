package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.AdvisorDefinitionDeposit} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AdvisorDefinitionDepositDTO implements Serializable {

    private Long id;

    private String advisorDepositNumber;

    private Boolean defaultAdvisorDeposit;

    private String depositNum;

    private String swiftCode;

    private String currencyCode;

    private CategoryElementDTO depositType;

    private AdvisorDefinitionDTO advisorDefinition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvisorDepositNumber() {
        return advisorDepositNumber;
    }

    public void setAdvisorDepositNumber(String advisorDepositNumber) {
        this.advisorDepositNumber = advisorDepositNumber;
    }

    public Boolean getDefaultAdvisorDeposit() {
        return defaultAdvisorDeposit;
    }

    public void setDefaultAdvisorDeposit(Boolean defaultAdvisorDeposit) {
        this.defaultAdvisorDeposit = defaultAdvisorDeposit;
    }

    public String getDepositNum() {
        return depositNum;
    }

    public void setDepositNum(String depositNum) {
        this.depositNum = depositNum;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public CategoryElementDTO getDepositType() {
        return depositType;
    }

    public void setDepositType(CategoryElementDTO depositType) {
        this.depositType = depositType;
    }

    public AdvisorDefinitionDTO getAdvisorDefinition() {
        return advisorDefinition;
    }

    public void setAdvisorDefinition(AdvisorDefinitionDTO advisorDefinition) {
        this.advisorDefinition = advisorDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdvisorDefinitionDepositDTO)) {
            return false;
        }

        AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO = (AdvisorDefinitionDepositDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, advisorDefinitionDepositDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdvisorDefinitionDepositDTO{" +
            "id=" + getId() +
            ", advisorDepositNumber='" + getAdvisorDepositNumber() + "'" +
            ", defaultAdvisorDeposit='" + getDefaultAdvisorDeposit() + "'" +
            ", depositNum='" + getDepositNum() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", depositType=" + getDepositType() +
            ", advisorDefinition=" + getAdvisorDefinition() +
            "}";
    }
}
