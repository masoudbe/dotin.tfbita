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

    private String creditDate;

    private String bankCode;

    private String branchCode;

    private String defaultCurrencyCode;

    private String countryCode;

    private DraftDTO advisingBank;

    private DraftDTO interfaceAdvisingBank;

    private DraftDTO coveringBank;

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

    public String getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
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

    public String getDefaultCurrencyCode() {
        return defaultCurrencyCode;
    }

    public void setDefaultCurrencyCode(String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public DraftDTO getAdvisingBank() {
        return advisingBank;
    }

    public void setAdvisingBank(DraftDTO advisingBank) {
        this.advisingBank = advisingBank;
    }

    public DraftDTO getInterfaceAdvisingBank() {
        return interfaceAdvisingBank;
    }

    public void setInterfaceAdvisingBank(DraftDTO interfaceAdvisingBank) {
        this.interfaceAdvisingBank = interfaceAdvisingBank;
    }

    public DraftDTO getCoveringBank() {
        return coveringBank;
    }

    public void setCoveringBank(DraftDTO coveringBank) {
        this.coveringBank = coveringBank;
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
            ", creditDate='" + getCreditDate() + "'" +
            ", bankCode='" + getBankCode() + "'" +
            ", branchCode='" + getBranchCode() + "'" +
            ", defaultCurrencyCode='" + getDefaultCurrencyCode() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", advisingBank=" + getAdvisingBank() +
            ", interfaceAdvisingBank=" + getInterfaceAdvisingBank() +
            ", coveringBank=" + getCoveringBank() +
            "}";
    }
}
