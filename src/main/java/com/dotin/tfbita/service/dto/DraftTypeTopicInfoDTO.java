package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftTypeTopicInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftTypeTopicInfoDTO implements Serializable {

    private Long id;

    private Long currencySellCommissionTopic;

    private Long documentReceiptDisciplinaryTopic;

    private Long draftMainTopic;

    private Long insuranceCostTopic;

    private Long justificationDisciplinaryTopic;

    private Long openDraftDisciplinaryTopic;

    private Long otherCostsTopic;

    private Long postTelegraphSwiftCostsTopic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrencySellCommissionTopic() {
        return currencySellCommissionTopic;
    }

    public void setCurrencySellCommissionTopic(Long currencySellCommissionTopic) {
        this.currencySellCommissionTopic = currencySellCommissionTopic;
    }

    public Long getDocumentReceiptDisciplinaryTopic() {
        return documentReceiptDisciplinaryTopic;
    }

    public void setDocumentReceiptDisciplinaryTopic(Long documentReceiptDisciplinaryTopic) {
        this.documentReceiptDisciplinaryTopic = documentReceiptDisciplinaryTopic;
    }

    public Long getDraftMainTopic() {
        return draftMainTopic;
    }

    public void setDraftMainTopic(Long draftMainTopic) {
        this.draftMainTopic = draftMainTopic;
    }

    public Long getInsuranceCostTopic() {
        return insuranceCostTopic;
    }

    public void setInsuranceCostTopic(Long insuranceCostTopic) {
        this.insuranceCostTopic = insuranceCostTopic;
    }

    public Long getJustificationDisciplinaryTopic() {
        return justificationDisciplinaryTopic;
    }

    public void setJustificationDisciplinaryTopic(Long justificationDisciplinaryTopic) {
        this.justificationDisciplinaryTopic = justificationDisciplinaryTopic;
    }

    public Long getOpenDraftDisciplinaryTopic() {
        return openDraftDisciplinaryTopic;
    }

    public void setOpenDraftDisciplinaryTopic(Long openDraftDisciplinaryTopic) {
        this.openDraftDisciplinaryTopic = openDraftDisciplinaryTopic;
    }

    public Long getOtherCostsTopic() {
        return otherCostsTopic;
    }

    public void setOtherCostsTopic(Long otherCostsTopic) {
        this.otherCostsTopic = otherCostsTopic;
    }

    public Long getPostTelegraphSwiftCostsTopic() {
        return postTelegraphSwiftCostsTopic;
    }

    public void setPostTelegraphSwiftCostsTopic(Long postTelegraphSwiftCostsTopic) {
        this.postTelegraphSwiftCostsTopic = postTelegraphSwiftCostsTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftTypeTopicInfoDTO)) {
            return false;
        }

        DraftTypeTopicInfoDTO draftTypeTopicInfoDTO = (DraftTypeTopicInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftTypeTopicInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftTypeTopicInfoDTO{" +
            "id=" + getId() +
            ", currencySellCommissionTopic=" + getCurrencySellCommissionTopic() +
            ", documentReceiptDisciplinaryTopic=" + getDocumentReceiptDisciplinaryTopic() +
            ", draftMainTopic=" + getDraftMainTopic() +
            ", insuranceCostTopic=" + getInsuranceCostTopic() +
            ", justificationDisciplinaryTopic=" + getJustificationDisciplinaryTopic() +
            ", openDraftDisciplinaryTopic=" + getOpenDraftDisciplinaryTopic() +
            ", otherCostsTopic=" + getOtherCostsTopic() +
            ", postTelegraphSwiftCostsTopic=" + getPostTelegraphSwiftCostsTopic() +
            "}";
    }
}
