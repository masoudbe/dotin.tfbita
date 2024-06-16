package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DraftType.
 */
@Entity
@Table(name = "draft_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "alarm_time")
    private Integer alarmTime;

    @Column(name = "code")
    private String code;

    @Column(name = "disable_date")
    private String disableDate;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "has_assurance")
    private Boolean hasAssurance;

    @Column(name = "has_sanction")
    private Boolean hasSanction;

    @Column(name = "latest_credit_serial")
    private Long latestCreditSerial;

    @Column(name = "name")
    private String name;

    @Column(name = "portal")
    private Boolean portal;

    @Column(name = "usable")
    private Boolean usable;

    @Column(name = "default_currency_code")
    private String defaultCurrencyCode;

    @Column(name = "account_info_code")
    private String accountInfoCode;

    @Column(name = "topic_info_code")
    private String topicInfoCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft draftType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAlarmTime() {
        return this.alarmTime;
    }

    public DraftType alarmTime(Integer alarmTime) {
        this.setAlarmTime(alarmTime);
        return this;
    }

    public void setAlarmTime(Integer alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getCode() {
        return this.code;
    }

    public DraftType code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisableDate() {
        return this.disableDate;
    }

    public DraftType disableDate(String disableDate) {
        this.setDisableDate(disableDate);
        return this;
    }

    public void setDisableDate(String disableDate) {
        this.disableDate = disableDate;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public DraftType duration(Integer duration) {
        this.setDuration(duration);
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getHasAssurance() {
        return this.hasAssurance;
    }

    public DraftType hasAssurance(Boolean hasAssurance) {
        this.setHasAssurance(hasAssurance);
        return this;
    }

    public void setHasAssurance(Boolean hasAssurance) {
        this.hasAssurance = hasAssurance;
    }

    public Boolean getHasSanction() {
        return this.hasSanction;
    }

    public DraftType hasSanction(Boolean hasSanction) {
        this.setHasSanction(hasSanction);
        return this;
    }

    public void setHasSanction(Boolean hasSanction) {
        this.hasSanction = hasSanction;
    }

    public Long getLatestCreditSerial() {
        return this.latestCreditSerial;
    }

    public DraftType latestCreditSerial(Long latestCreditSerial) {
        this.setLatestCreditSerial(latestCreditSerial);
        return this;
    }

    public void setLatestCreditSerial(Long latestCreditSerial) {
        this.latestCreditSerial = latestCreditSerial;
    }

    public String getName() {
        return this.name;
    }

    public DraftType name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPortal() {
        return this.portal;
    }

    public DraftType portal(Boolean portal) {
        this.setPortal(portal);
        return this;
    }

    public void setPortal(Boolean portal) {
        this.portal = portal;
    }

    public Boolean getUsable() {
        return this.usable;
    }

    public DraftType usable(Boolean usable) {
        this.setUsable(usable);
        return this;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

    public String getDefaultCurrencyCode() {
        return this.defaultCurrencyCode;
    }

    public DraftType defaultCurrencyCode(String defaultCurrencyCode) {
        this.setDefaultCurrencyCode(defaultCurrencyCode);
        return this;
    }

    public void setDefaultCurrencyCode(String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public String getAccountInfoCode() {
        return this.accountInfoCode;
    }

    public DraftType accountInfoCode(String accountInfoCode) {
        this.setAccountInfoCode(accountInfoCode);
        return this;
    }

    public void setAccountInfoCode(String accountInfoCode) {
        this.accountInfoCode = accountInfoCode;
    }

    public String getTopicInfoCode() {
        return this.topicInfoCode;
    }

    public DraftType topicInfoCode(String topicInfoCode) {
        this.setTopicInfoCode(topicInfoCode);
        return this;
    }

    public void setTopicInfoCode(String topicInfoCode) {
        this.topicInfoCode = topicInfoCode;
    }

    public Draft getDraftType() {
        return this.draftType;
    }

    public void setDraftType(Draft draft) {
        this.draftType = draft;
    }

    public DraftType draftType(Draft draft) {
        this.setDraftType(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftType)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftType) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftType{" +
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
            "}";
    }
}
