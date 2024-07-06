package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftStatusInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftStatusInfoDTO implements Serializable {

    private Long id;

    private Boolean approved;

    private Boolean draftRequestAccepted;

    private Boolean insuranceCostPaid;

    private Boolean issued;

    private Boolean otherCostsPaid;

    private Boolean postSwiftCostPaied;

    private String rejectDescription;

    private BigDecimal remainAmount;

    private Boolean stampCostPaid;

    private CategoryElementDTO status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getDraftRequestAccepted() {
        return draftRequestAccepted;
    }

    public void setDraftRequestAccepted(Boolean draftRequestAccepted) {
        this.draftRequestAccepted = draftRequestAccepted;
    }

    public Boolean getInsuranceCostPaid() {
        return insuranceCostPaid;
    }

    public void setInsuranceCostPaid(Boolean insuranceCostPaid) {
        this.insuranceCostPaid = insuranceCostPaid;
    }

    public Boolean getIssued() {
        return issued;
    }

    public void setIssued(Boolean issued) {
        this.issued = issued;
    }

    public Boolean getOtherCostsPaid() {
        return otherCostsPaid;
    }

    public void setOtherCostsPaid(Boolean otherCostsPaid) {
        this.otherCostsPaid = otherCostsPaid;
    }

    public Boolean getPostSwiftCostPaied() {
        return postSwiftCostPaied;
    }

    public void setPostSwiftCostPaied(Boolean postSwiftCostPaied) {
        this.postSwiftCostPaied = postSwiftCostPaied;
    }

    public String getRejectDescription() {
        return rejectDescription;
    }

    public void setRejectDescription(String rejectDescription) {
        this.rejectDescription = rejectDescription;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Boolean getStampCostPaid() {
        return stampCostPaid;
    }

    public void setStampCostPaid(Boolean stampCostPaid) {
        this.stampCostPaid = stampCostPaid;
    }

    public CategoryElementDTO getStatus() {
        return status;
    }

    public void setStatus(CategoryElementDTO status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftStatusInfoDTO)) {
            return false;
        }

        DraftStatusInfoDTO draftStatusInfoDTO = (DraftStatusInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftStatusInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftStatusInfoDTO{" +
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
            ", status=" + getStatus() +
            "}";
    }
}
