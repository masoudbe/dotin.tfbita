package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A StringValue.
 */
@Entity
@Table(name = "string_value")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StringValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "val")
    private String val;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sanctionSerials")
    @JsonIgnoreProperties(
        value = {
            "receipts",
            "taxes",
            "extensions",
            "draftFactors",
            "usedAssurances",
            "draftJustifications",
            "chargedExchangeBroker",
            "insuranceLetterType",
            "advisorDepositType",
            "interfaceAdvisorDepositType",
            "coveringAdvisorDepositType",
            "impartType",
            "dealType",
            "transportVehicleType",
            "freightLetterType",
            "actionCode",
            "ownershipCode",
            "currencyContainerPlace",
            "paymentType",
            "draftSource",
            "loadSwitchPlace",
            "draftType",
            "statusInfo",
            "insuranceCompanyInfo",
            "advisingBank",
            "interfaceAdvisingBank",
            "coveringBank",
            "auditCompanyInfo",
            "transportType",
            "currencyExchangeInfo",
            "accountInfo",
            "destinationCustomCompanies",
            "sourceCustomCompanies",
            "services",
            "products",
            "sanctionSerials",
            "customerNumbers",
            "suggestedSanctions",
            "documentTransactionContainers",
        },
        allowSetters = true
    )
    private Set<Draft> drafts = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userGroups")
    @JsonIgnoreProperties(
        value = {
            "type",
            "secondaryType",
            "division",
            "topicInfo",
            "conditionInfo",
            "accountInfo",
            "requestType",
            "acceptableProductTypes",
            "userGroups",
        },
        allowSetters = true
    )
    private Set<DraftType> draftTypes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "commissionTransactionNumbers")
    @JsonIgnoreProperties(
        value = {
            "serviceInfos",
            "purchaseFromOtherResourcesLists",
            "orderRegType",
            "requestType",
            "importType",
            "operationType",
            "currencyProvisionType",
            "paymentTool",
            "activityType",
            "ownerType",
            "status",
            "externalCustomerType",
            "transportVehicleType",
            "transportType",
            "destCoustomers",
            "cargoPlaceCustoms",
            "entranceBorders",
            "productInfos",
            "commissionTransactionNumbers",
            "licenceInfos",
        },
        allowSetters = true
    )
    private Set<OrderRegistrationInfo> orderRegistrationInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public StringValue id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVal() {
        return this.val;
    }

    public StringValue val(String val) {
        this.setVal(val);
        return this;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Set<Draft> getDrafts() {
        return this.drafts;
    }

    public void setDrafts(Set<Draft> drafts) {
        if (this.drafts != null) {
            this.drafts.forEach(i -> i.removeSanctionSerials(this));
        }
        if (drafts != null) {
            drafts.forEach(i -> i.addSanctionSerials(this));
        }
        this.drafts = drafts;
    }

    public StringValue drafts(Set<Draft> drafts) {
        this.setDrafts(drafts);
        return this;
    }

    public StringValue addDraft(Draft draft) {
        this.drafts.add(draft);
        draft.getSanctionSerials().add(this);
        return this;
    }

    public StringValue removeDraft(Draft draft) {
        this.drafts.remove(draft);
        draft.getSanctionSerials().remove(this);
        return this;
    }

    public Set<DraftType> getDraftTypes() {
        return this.draftTypes;
    }

    public void setDraftTypes(Set<DraftType> draftTypes) {
        if (this.draftTypes != null) {
            this.draftTypes.forEach(i -> i.removeUserGroups(this));
        }
        if (draftTypes != null) {
            draftTypes.forEach(i -> i.addUserGroups(this));
        }
        this.draftTypes = draftTypes;
    }

    public StringValue draftTypes(Set<DraftType> draftTypes) {
        this.setDraftTypes(draftTypes);
        return this;
    }

    public StringValue addDraftType(DraftType draftType) {
        this.draftTypes.add(draftType);
        draftType.getUserGroups().add(this);
        return this;
    }

    public StringValue removeDraftType(DraftType draftType) {
        this.draftTypes.remove(draftType);
        draftType.getUserGroups().remove(this);
        return this;
    }

    public Set<OrderRegistrationInfo> getOrderRegistrationInfos() {
        return this.orderRegistrationInfos;
    }

    public void setOrderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        if (this.orderRegistrationInfos != null) {
            this.orderRegistrationInfos.forEach(i -> i.removeCommissionTransactionNumber(this));
        }
        if (orderRegistrationInfos != null) {
            orderRegistrationInfos.forEach(i -> i.addCommissionTransactionNumber(this));
        }
        this.orderRegistrationInfos = orderRegistrationInfos;
    }

    public StringValue orderRegistrationInfos(Set<OrderRegistrationInfo> orderRegistrationInfos) {
        this.setOrderRegistrationInfos(orderRegistrationInfos);
        return this;
    }

    public StringValue addOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.add(orderRegistrationInfo);
        orderRegistrationInfo.getCommissionTransactionNumbers().add(this);
        return this;
    }

    public StringValue removeOrderRegistrationInfo(OrderRegistrationInfo orderRegistrationInfo) {
        this.orderRegistrationInfos.remove(orderRegistrationInfo);
        orderRegistrationInfo.getCommissionTransactionNumbers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StringValue)) {
            return false;
        }
        return getId() != null && getId().equals(((StringValue) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StringValue{" +
            "id=" + getId() +
            ", val='" + getVal() + "'" +
            "}";
    }
}
