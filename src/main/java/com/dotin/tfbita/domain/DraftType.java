package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "currencies_codes")
    private String currenciesCodes;

    @Column(name = "default_currency_code")
    private String defaultCurrencyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement secondaryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement division;

    @ManyToOne(fetch = FetchType.LAZY)
    private DraftTypeTopicInfo topicInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "conditions", "defaultCondition" }, allowSetters = true)
    private CreditTypeConditionInfo conditionInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private DraftTypeAccountInfo accountInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "certificateTypeLists" }, allowSetters = true)
    private DraftRequestType requestType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "objectiveCategory" }, allowSetters = true)
    private ObjectiveCategoryElement acceptableProductTypes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_draft_type__user_groups",
        joinColumns = @JoinColumn(name = "draft_type_id"),
        inverseJoinColumns = @JoinColumn(name = "user_groups_id")
    )
    @JsonIgnoreProperties(value = { "drafts", "draftTypes", "orderRegistrationInfos" }, allowSetters = true)
    private Set<StringValue> userGroups = new HashSet<>();

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

    public String getCurrenciesCodes() {
        return this.currenciesCodes;
    }

    public DraftType currenciesCodes(String currenciesCodes) {
        this.setCurrenciesCodes(currenciesCodes);
        return this;
    }

    public void setCurrenciesCodes(String currenciesCodes) {
        this.currenciesCodes = currenciesCodes;
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

    public CategoryElement getType() {
        return this.type;
    }

    public void setType(CategoryElement categoryElement) {
        this.type = categoryElement;
    }

    public DraftType type(CategoryElement categoryElement) {
        this.setType(categoryElement);
        return this;
    }

    public CategoryElement getSecondaryType() {
        return this.secondaryType;
    }

    public void setSecondaryType(CategoryElement categoryElement) {
        this.secondaryType = categoryElement;
    }

    public DraftType secondaryType(CategoryElement categoryElement) {
        this.setSecondaryType(categoryElement);
        return this;
    }

    public CategoryElement getDivision() {
        return this.division;
    }

    public void setDivision(CategoryElement categoryElement) {
        this.division = categoryElement;
    }

    public DraftType division(CategoryElement categoryElement) {
        this.setDivision(categoryElement);
        return this;
    }

    public DraftTypeTopicInfo getTopicInfo() {
        return this.topicInfo;
    }

    public void setTopicInfo(DraftTypeTopicInfo draftTypeTopicInfo) {
        this.topicInfo = draftTypeTopicInfo;
    }

    public DraftType topicInfo(DraftTypeTopicInfo draftTypeTopicInfo) {
        this.setTopicInfo(draftTypeTopicInfo);
        return this;
    }

    public CreditTypeConditionInfo getConditionInfo() {
        return this.conditionInfo;
    }

    public void setConditionInfo(CreditTypeConditionInfo creditTypeConditionInfo) {
        this.conditionInfo = creditTypeConditionInfo;
    }

    public DraftType conditionInfo(CreditTypeConditionInfo creditTypeConditionInfo) {
        this.setConditionInfo(creditTypeConditionInfo);
        return this;
    }

    public DraftTypeAccountInfo getAccountInfo() {
        return this.accountInfo;
    }

    public void setAccountInfo(DraftTypeAccountInfo draftTypeAccountInfo) {
        this.accountInfo = draftTypeAccountInfo;
    }

    public DraftType accountInfo(DraftTypeAccountInfo draftTypeAccountInfo) {
        this.setAccountInfo(draftTypeAccountInfo);
        return this;
    }

    public DraftRequestType getRequestType() {
        return this.requestType;
    }

    public void setRequestType(DraftRequestType draftRequestType) {
        this.requestType = draftRequestType;
    }

    public DraftType requestType(DraftRequestType draftRequestType) {
        this.setRequestType(draftRequestType);
        return this;
    }

    public ObjectiveCategoryElement getAcceptableProductTypes() {
        return this.acceptableProductTypes;
    }

    public void setAcceptableProductTypes(ObjectiveCategoryElement objectiveCategoryElement) {
        this.acceptableProductTypes = objectiveCategoryElement;
    }

    public DraftType acceptableProductTypes(ObjectiveCategoryElement objectiveCategoryElement) {
        this.setAcceptableProductTypes(objectiveCategoryElement);
        return this;
    }

    public Set<StringValue> getUserGroups() {
        return this.userGroups;
    }

    public void setUserGroups(Set<StringValue> stringValues) {
        this.userGroups = stringValues;
    }

    public DraftType userGroups(Set<StringValue> stringValues) {
        this.setUserGroups(stringValues);
        return this;
    }

    public DraftType addUserGroups(StringValue stringValue) {
        this.userGroups.add(stringValue);
        return this;
    }

    public DraftType removeUserGroups(StringValue stringValue) {
        this.userGroups.remove(stringValue);
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
            ", currenciesCodes='" + getCurrenciesCodes() + "'" +
            ", defaultCurrencyCode='" + getDefaultCurrencyCode() + "'" +
            "}";
    }
}
