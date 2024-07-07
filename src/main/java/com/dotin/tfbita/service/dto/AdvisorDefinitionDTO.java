package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.AdvisorDefinition} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AdvisorDefinitionDTO implements Serializable {

    private Long id;

    private String caption;

    private String code;

    private String countryIsoCode;

    private String depositNum;

    private String swiftCode;

    private String defaultCurrencyCode;

    private String currenciesCodes;

    private String countryCode;

    private String bankCode;

    private String branchCode;

    private AdditionalBrokerInformationDTO additionalBrokerInformation;

    private AdvisorDefinitionDepositDTO defaultVostroDeposit;

    private AdvisorDefinitionDepositDTO defaultNostroDeposit;

    private TransferMethodManagementDTO receiveMethod;

    private TransferMethodManagementDTO payMethod;

    private SwiftBicDTO swiftBic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
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

    public String getDefaultCurrencyCode() {
        return defaultCurrencyCode;
    }

    public void setDefaultCurrencyCode(String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public String getCurrenciesCodes() {
        return currenciesCodes;
    }

    public void setCurrenciesCodes(String currenciesCodes) {
        this.currenciesCodes = currenciesCodes;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public AdditionalBrokerInformationDTO getAdditionalBrokerInformation() {
        return additionalBrokerInformation;
    }

    public void setAdditionalBrokerInformation(AdditionalBrokerInformationDTO additionalBrokerInformation) {
        this.additionalBrokerInformation = additionalBrokerInformation;
    }

    public AdvisorDefinitionDepositDTO getDefaultVostroDeposit() {
        return defaultVostroDeposit;
    }

    public void setDefaultVostroDeposit(AdvisorDefinitionDepositDTO defaultVostroDeposit) {
        this.defaultVostroDeposit = defaultVostroDeposit;
    }

    public AdvisorDefinitionDepositDTO getDefaultNostroDeposit() {
        return defaultNostroDeposit;
    }

    public void setDefaultNostroDeposit(AdvisorDefinitionDepositDTO defaultNostroDeposit) {
        this.defaultNostroDeposit = defaultNostroDeposit;
    }

    public TransferMethodManagementDTO getReceiveMethod() {
        return receiveMethod;
    }

    public void setReceiveMethod(TransferMethodManagementDTO receiveMethod) {
        this.receiveMethod = receiveMethod;
    }

    public TransferMethodManagementDTO getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(TransferMethodManagementDTO payMethod) {
        this.payMethod = payMethod;
    }

    public SwiftBicDTO getSwiftBic() {
        return swiftBic;
    }

    public void setSwiftBic(SwiftBicDTO swiftBic) {
        this.swiftBic = swiftBic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdvisorDefinitionDTO)) {
            return false;
        }

        AdvisorDefinitionDTO advisorDefinitionDTO = (AdvisorDefinitionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, advisorDefinitionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdvisorDefinitionDTO{" +
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
            ", additionalBrokerInformation=" + getAdditionalBrokerInformation() +
            ", defaultVostroDeposit=" + getDefaultVostroDeposit() +
            ", defaultNostroDeposit=" + getDefaultNostroDeposit() +
            ", receiveMethod=" + getReceiveMethod() +
            ", payMethod=" + getPayMethod() +
            ", swiftBic=" + getSwiftBic() +
            "}";
    }
}
