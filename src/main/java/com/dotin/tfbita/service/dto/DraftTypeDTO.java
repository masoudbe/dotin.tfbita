package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

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

    private String defaultCurrencyCode;

    private String accountInfoCode;

    private String topicInfoCode;

    private DraftDTO draftType;

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

    public String getDefaultCurrencyCode() {
        return defaultCurrencyCode;
    }

    public void setDefaultCurrencyCode(String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public String getAccountInfoCode() {
        return accountInfoCode;
    }

    public void setAccountInfoCode(String accountInfoCode) {
        this.accountInfoCode = accountInfoCode;
    }

    public String getTopicInfoCode() {
        return topicInfoCode;
    }

    public void setTopicInfoCode(String topicInfoCode) {
        this.topicInfoCode = topicInfoCode;
    }

    public DraftDTO getDraftType() {
        return draftType;
    }

    public void setDraftType(DraftDTO draftType) {
        this.draftType = draftType;
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
            ", defaultCurrencyCode='" + getDefaultCurrencyCode() + "'" +
            ", accountInfoCode='" + getAccountInfoCode() + "'" +
            ", topicInfoCode='" + getTopicInfoCode() + "'" +
            ", draftType=" + getDraftType() +
            "}";
    }
}
