package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DraftStatusInfo.
 */
@Entity
@Table(name = "draft_status_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftStatusInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "draft_request_accepted")
    private Boolean draftRequestAccepted;

    @Column(name = "insurance_cost_paid")
    private Boolean insuranceCostPaid;

    @Column(name = "issued")
    private Boolean issued;

    @Column(name = "other_costs_paid")
    private Boolean otherCostsPaid;

    @Column(name = "post_swift_cost_paied")
    private Boolean postSwiftCostPaied;

    @Column(name = "reject_description")
    private String rejectDescription;

    @Column(name = "remain_amount", precision = 21, scale = 2)
    private BigDecimal remainAmount;

    @Column(name = "stamp_cost_paid")
    private Boolean stampCostPaid;

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
    private Draft statusInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftStatusInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getApproved() {
        return this.approved;
    }

    public DraftStatusInfo approved(Boolean approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getDraftRequestAccepted() {
        return this.draftRequestAccepted;
    }

    public DraftStatusInfo draftRequestAccepted(Boolean draftRequestAccepted) {
        this.setDraftRequestAccepted(draftRequestAccepted);
        return this;
    }

    public void setDraftRequestAccepted(Boolean draftRequestAccepted) {
        this.draftRequestAccepted = draftRequestAccepted;
    }

    public Boolean getInsuranceCostPaid() {
        return this.insuranceCostPaid;
    }

    public DraftStatusInfo insuranceCostPaid(Boolean insuranceCostPaid) {
        this.setInsuranceCostPaid(insuranceCostPaid);
        return this;
    }

    public void setInsuranceCostPaid(Boolean insuranceCostPaid) {
        this.insuranceCostPaid = insuranceCostPaid;
    }

    public Boolean getIssued() {
        return this.issued;
    }

    public DraftStatusInfo issued(Boolean issued) {
        this.setIssued(issued);
        return this;
    }

    public void setIssued(Boolean issued) {
        this.issued = issued;
    }

    public Boolean getOtherCostsPaid() {
        return this.otherCostsPaid;
    }

    public DraftStatusInfo otherCostsPaid(Boolean otherCostsPaid) {
        this.setOtherCostsPaid(otherCostsPaid);
        return this;
    }

    public void setOtherCostsPaid(Boolean otherCostsPaid) {
        this.otherCostsPaid = otherCostsPaid;
    }

    public Boolean getPostSwiftCostPaied() {
        return this.postSwiftCostPaied;
    }

    public DraftStatusInfo postSwiftCostPaied(Boolean postSwiftCostPaied) {
        this.setPostSwiftCostPaied(postSwiftCostPaied);
        return this;
    }

    public void setPostSwiftCostPaied(Boolean postSwiftCostPaied) {
        this.postSwiftCostPaied = postSwiftCostPaied;
    }

    public String getRejectDescription() {
        return this.rejectDescription;
    }

    public DraftStatusInfo rejectDescription(String rejectDescription) {
        this.setRejectDescription(rejectDescription);
        return this;
    }

    public void setRejectDescription(String rejectDescription) {
        this.rejectDescription = rejectDescription;
    }

    public BigDecimal getRemainAmount() {
        return this.remainAmount;
    }

    public DraftStatusInfo remainAmount(BigDecimal remainAmount) {
        this.setRemainAmount(remainAmount);
        return this;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Boolean getStampCostPaid() {
        return this.stampCostPaid;
    }

    public DraftStatusInfo stampCostPaid(Boolean stampCostPaid) {
        this.setStampCostPaid(stampCostPaid);
        return this;
    }

    public void setStampCostPaid(Boolean stampCostPaid) {
        this.stampCostPaid = stampCostPaid;
    }

    public Draft getStatusInfo() {
        return this.statusInfo;
    }

    public void setStatusInfo(Draft draft) {
        this.statusInfo = draft;
    }

    public DraftStatusInfo statusInfo(Draft draft) {
        this.setStatusInfo(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftStatusInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftStatusInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftStatusInfo{" +
            "id=" + getId() +
            ", approved='" + getApproved() + "'" +
            ", draftRequestAccepted='" + getDraftRequestAccepted() + "'" +
            ", insuranceCostPaid='" + getInsuranceCostPaid() + "'" +
            ", issued='" + getIssued() + "'" +
            ", otherCostsPaid='" + getOtherCostsPaid() + "'" +
            ", postSwiftCostPaied='" + getPostSwiftCostPaied() + "'" +
            ", rejectDescription='" + getRejectDescription() + "'" +
            ", remainAmount=" + getRemainAmount() +
            ", stampCostPaid='" + getStampCostPaid() + "'" +
            "}";
    }
}
