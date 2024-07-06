package com.dotin.tfbita.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DraftTypeTopicInfo.
 */
@Entity
@Table(name = "draft_type_topic_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftTypeTopicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "currency_sell_commission_topic")
    private Long currencySellCommissionTopic;

    @Column(name = "document_receipt_disciplinary_topic")
    private Long documentReceiptDisciplinaryTopic;

    @Column(name = "draft_main_topic")
    private Long draftMainTopic;

    @Column(name = "insurance_cost_topic")
    private Long insuranceCostTopic;

    @Column(name = "justification_disciplinary_topic")
    private Long justificationDisciplinaryTopic;

    @Column(name = "open_draft_disciplinary_topic")
    private Long openDraftDisciplinaryTopic;

    @Column(name = "other_costs_topic")
    private Long otherCostsTopic;

    @Column(name = "post_telegraph_swift_costs_topic")
    private Long postTelegraphSwiftCostsTopic;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftTypeTopicInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrencySellCommissionTopic() {
        return this.currencySellCommissionTopic;
    }

    public DraftTypeTopicInfo currencySellCommissionTopic(Long currencySellCommissionTopic) {
        this.setCurrencySellCommissionTopic(currencySellCommissionTopic);
        return this;
    }

    public void setCurrencySellCommissionTopic(Long currencySellCommissionTopic) {
        this.currencySellCommissionTopic = currencySellCommissionTopic;
    }

    public Long getDocumentReceiptDisciplinaryTopic() {
        return this.documentReceiptDisciplinaryTopic;
    }

    public DraftTypeTopicInfo documentReceiptDisciplinaryTopic(Long documentReceiptDisciplinaryTopic) {
        this.setDocumentReceiptDisciplinaryTopic(documentReceiptDisciplinaryTopic);
        return this;
    }

    public void setDocumentReceiptDisciplinaryTopic(Long documentReceiptDisciplinaryTopic) {
        this.documentReceiptDisciplinaryTopic = documentReceiptDisciplinaryTopic;
    }

    public Long getDraftMainTopic() {
        return this.draftMainTopic;
    }

    public DraftTypeTopicInfo draftMainTopic(Long draftMainTopic) {
        this.setDraftMainTopic(draftMainTopic);
        return this;
    }

    public void setDraftMainTopic(Long draftMainTopic) {
        this.draftMainTopic = draftMainTopic;
    }

    public Long getInsuranceCostTopic() {
        return this.insuranceCostTopic;
    }

    public DraftTypeTopicInfo insuranceCostTopic(Long insuranceCostTopic) {
        this.setInsuranceCostTopic(insuranceCostTopic);
        return this;
    }

    public void setInsuranceCostTopic(Long insuranceCostTopic) {
        this.insuranceCostTopic = insuranceCostTopic;
    }

    public Long getJustificationDisciplinaryTopic() {
        return this.justificationDisciplinaryTopic;
    }

    public DraftTypeTopicInfo justificationDisciplinaryTopic(Long justificationDisciplinaryTopic) {
        this.setJustificationDisciplinaryTopic(justificationDisciplinaryTopic);
        return this;
    }

    public void setJustificationDisciplinaryTopic(Long justificationDisciplinaryTopic) {
        this.justificationDisciplinaryTopic = justificationDisciplinaryTopic;
    }

    public Long getOpenDraftDisciplinaryTopic() {
        return this.openDraftDisciplinaryTopic;
    }

    public DraftTypeTopicInfo openDraftDisciplinaryTopic(Long openDraftDisciplinaryTopic) {
        this.setOpenDraftDisciplinaryTopic(openDraftDisciplinaryTopic);
        return this;
    }

    public void setOpenDraftDisciplinaryTopic(Long openDraftDisciplinaryTopic) {
        this.openDraftDisciplinaryTopic = openDraftDisciplinaryTopic;
    }

    public Long getOtherCostsTopic() {
        return this.otherCostsTopic;
    }

    public DraftTypeTopicInfo otherCostsTopic(Long otherCostsTopic) {
        this.setOtherCostsTopic(otherCostsTopic);
        return this;
    }

    public void setOtherCostsTopic(Long otherCostsTopic) {
        this.otherCostsTopic = otherCostsTopic;
    }

    public Long getPostTelegraphSwiftCostsTopic() {
        return this.postTelegraphSwiftCostsTopic;
    }

    public DraftTypeTopicInfo postTelegraphSwiftCostsTopic(Long postTelegraphSwiftCostsTopic) {
        this.setPostTelegraphSwiftCostsTopic(postTelegraphSwiftCostsTopic);
        return this;
    }

    public void setPostTelegraphSwiftCostsTopic(Long postTelegraphSwiftCostsTopic) {
        this.postTelegraphSwiftCostsTopic = postTelegraphSwiftCostsTopic;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftTypeTopicInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftTypeTopicInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftTypeTopicInfo{" +
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
