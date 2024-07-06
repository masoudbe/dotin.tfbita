package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftType} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftTypeDTO implements Serializable {

    private Long id;

    private Integer alarmTime;

    private String code;

    private String disableDate;

    private Integer duration;

    private Boolean hasAssurance;

    private Boolean hasSanction;

    private Long latestCreditSerial;

    private String name;

    private Boolean portal;

    private Boolean usable;

    private String currenciesCodes;

    private String defaultCurrencyCode;

    private CategoryElementDTO type;

    private CategoryElementDTO secondaryType;

    private CategoryElementDTO division;

    private DraftTypeTopicInfoDTO topicInfo;

    private CreditTypeConditionInfoDTO conditionInfo;

    private DraftTypeAccountInfoDTO accountInfo;

    private DraftRequestTypeDTO requestType;

    private ObjectiveCategoryElementDTO acceptableProductTypes;

    private Set<StringValueDTO> userGroups = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Integer alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(String disableDate) {
        this.disableDate = disableDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getHasAssurance() {
        return hasAssurance;
    }

    public void setHasAssurance(Boolean hasAssurance) {
        this.hasAssurance = hasAssurance;
    }

    public Boolean getHasSanction() {
        return hasSanction;
    }

    public void setHasSanction(Boolean hasSanction) {
        this.hasSanction = hasSanction;
    }

    public Long getLatestCreditSerial() {
        return latestCreditSerial;
    }

    public void setLatestCreditSerial(Long latestCreditSerial) {
        this.latestCreditSerial = latestCreditSerial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPortal() {
        return portal;
    }

    public void setPortal(Boolean portal) {
        this.portal = portal;
    }

    public Boolean getUsable() {
        return usable;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    public String getCurrenciesCodes() {
        return currenciesCodes;
    }

    public void setCurrenciesCodes(String currenciesCodes) {
        this.currenciesCodes = currenciesCodes;
    }

    public String getDefaultCurrencyCode() {
        return defaultCurrencyCode;
    }

    public void setDefaultCurrencyCode(String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public CategoryElementDTO getType() {
        return type;
    }

    public void setType(CategoryElementDTO type) {
        this.type = type;
    }

    public CategoryElementDTO getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(CategoryElementDTO secondaryType) {
        this.secondaryType = secondaryType;
    }

    public CategoryElementDTO getDivision() {
        return division;
    }

    public void setDivision(CategoryElementDTO division) {
        this.division = division;
    }

    public DraftTypeTopicInfoDTO getTopicInfo() {
        return topicInfo;
    }

    public void setTopicInfo(DraftTypeTopicInfoDTO topicInfo) {
        this.topicInfo = topicInfo;
    }

    public CreditTypeConditionInfoDTO getConditionInfo() {
        return conditionInfo;
    }

    public void setConditionInfo(CreditTypeConditionInfoDTO conditionInfo) {
        this.conditionInfo = conditionInfo;
    }

    public DraftTypeAccountInfoDTO getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(DraftTypeAccountInfoDTO accountInfo) {
        this.accountInfo = accountInfo;
    }

    public DraftRequestTypeDTO getRequestType() {
        return requestType;
    }

    public void setRequestType(DraftRequestTypeDTO requestType) {
        this.requestType = requestType;
    }

    public ObjectiveCategoryElementDTO getAcceptableProductTypes() {
        return acceptableProductTypes;
    }

    public void setAcceptableProductTypes(ObjectiveCategoryElementDTO acceptableProductTypes) {
        this.acceptableProductTypes = acceptableProductTypes;
    }

    public Set<StringValueDTO> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<StringValueDTO> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftTypeDTO)) {
            return false;
        }

        DraftTypeDTO draftTypeDTO = (DraftTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftTypeDTO{" +
            "id=" + getId() +
            ", alarmTime=" + getAlarmTime() +
            ", code='" + getCode() + "'" +
            ", disableDate='" + getDisableDate() + "'" +
            ", duration=" + getDuration() +
            ", hasAssurance='" + getHasAssurance() + "'" +
            ", hasSanction='" + getHasSanction() + "'" +
            ", latestCreditSerial=" + getLatestCreditSerial() +
            ", name='" + getName() + "'" +
            ", portal='" + getPortal() + "'" +
            ", usable='" + getUsable() + "'" +
            ", currenciesCodes='" + getCurrenciesCodes() + "'" +
            ", defaultCurrencyCode='" + getDefaultCurrencyCode() + "'" +
            ", type=" + getType() +
            ", secondaryType=" + getSecondaryType() +
            ", division=" + getDivision() +
            ", topicInfo=" + getTopicInfo() +
            ", conditionInfo=" + getConditionInfo() +
            ", accountInfo=" + getAccountInfo() +
            ", requestType=" + getRequestType() +
            ", acceptableProductTypes=" + getAcceptableProductTypes() +
            ", userGroups=" + getUserGroups() +
            "}";
    }
}
