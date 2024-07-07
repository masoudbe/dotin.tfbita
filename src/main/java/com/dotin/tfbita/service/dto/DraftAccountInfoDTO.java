package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftAccountInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftAccountInfoDTO implements Serializable {

    private Long id;

    private Long documentReceiptDisciplinaryAccountId;

    private Long draftMainAccountId;

    private Long insuranceCostAccountId;

    private Long justificationDisciplinaryAccountId;

    private Long openDraftDisciplinaryAccountId;

    private Long otherCostsAccountId;

    private Long postSwiftCostsAccountId;

    private Long amountDeductionAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentReceiptDisciplinaryAccountId() {
        return documentReceiptDisciplinaryAccountId;
    }

    public void setDocumentReceiptDisciplinaryAccountId(Long documentReceiptDisciplinaryAccountId) {
        this.documentReceiptDisciplinaryAccountId = documentReceiptDisciplinaryAccountId;
    }

    public Long getDraftMainAccountId() {
        return draftMainAccountId;
    }

    public void setDraftMainAccountId(Long draftMainAccountId) {
        this.draftMainAccountId = draftMainAccountId;
    }

    public Long getInsuranceCostAccountId() {
        return insuranceCostAccountId;
    }

    public void setInsuranceCostAccountId(Long insuranceCostAccountId) {
        this.insuranceCostAccountId = insuranceCostAccountId;
    }

    public Long getJustificationDisciplinaryAccountId() {
        return justificationDisciplinaryAccountId;
    }

    public void setJustificationDisciplinaryAccountId(Long justificationDisciplinaryAccountId) {
        this.justificationDisciplinaryAccountId = justificationDisciplinaryAccountId;
    }

    public Long getOpenDraftDisciplinaryAccountId() {
        return openDraftDisciplinaryAccountId;
    }

    public void setOpenDraftDisciplinaryAccountId(Long openDraftDisciplinaryAccountId) {
        this.openDraftDisciplinaryAccountId = openDraftDisciplinaryAccountId;
    }

    public Long getOtherCostsAccountId() {
        return otherCostsAccountId;
    }

    public void setOtherCostsAccountId(Long otherCostsAccountId) {
        this.otherCostsAccountId = otherCostsAccountId;
    }

    public Long getPostSwiftCostsAccountId() {
        return postSwiftCostsAccountId;
    }

    public void setPostSwiftCostsAccountId(Long postSwiftCostsAccountId) {
        this.postSwiftCostsAccountId = postSwiftCostsAccountId;
    }

    public Long getAmountDeductionAccountId() {
        return amountDeductionAccountId;
    }

    public void setAmountDeductionAccountId(Long amountDeductionAccountId) {
        this.amountDeductionAccountId = amountDeductionAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftAccountInfoDTO)) {
            return false;
        }

        DraftAccountInfoDTO draftAccountInfoDTO = (DraftAccountInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftAccountInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftAccountInfoDTO{" +
            "id=" + getId() +
            ", documentReceiptDisciplinaryAccountId=" + getDocumentReceiptDisciplinaryAccountId() +
            ", draftMainAccountId=" + getDraftMainAccountId() +
            ", insuranceCostAccountId=" + getInsuranceCostAccountId() +
            ", justificationDisciplinaryAccountId=" + getJustificationDisciplinaryAccountId() +
            ", openDraftDisciplinaryAccountId=" + getOpenDraftDisciplinaryAccountId() +
            ", otherCostsAccountId=" + getOtherCostsAccountId() +
            ", postSwiftCostsAccountId=" + getPostSwiftCostsAccountId() +
            ", amountDeductionAccountId=" + getAmountDeductionAccountId() +
            "}";
    }
}
