package com.dotin.tfbita.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A DraftAccountInfo.
 */
@Entity
@Table(name = "draft_account_info")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftAccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "document_receipt_disciplinary_account_id")
    private Long documentReceiptDisciplinaryAccountId;

    @Column(name = "draft_main_account_id")
    private Long draftMainAccountId;

    @Column(name = "insurance_cost_account_id")
    private Long insuranceCostAccountId;

    @Column(name = "justification_disciplinary_account_id")
    private Long justificationDisciplinaryAccountId;

    @Column(name = "open_draft_disciplinary_account_id")
    private Long openDraftDisciplinaryAccountId;

    @Column(name = "other_costs_account_id")
    private Long otherCostsAccountId;

    @Column(name = "post_swift_costs_account_id")
    private Long postSwiftCostsAccountId;

    @Column(name = "amount_deduction_account_id")
    private Long amountDeductionAccountId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftAccountInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentReceiptDisciplinaryAccountId() {
        return this.documentReceiptDisciplinaryAccountId;
    }

    public DraftAccountInfo documentReceiptDisciplinaryAccountId(Long documentReceiptDisciplinaryAccountId) {
        this.setDocumentReceiptDisciplinaryAccountId(documentReceiptDisciplinaryAccountId);
        return this;
    }

    public void setDocumentReceiptDisciplinaryAccountId(Long documentReceiptDisciplinaryAccountId) {
        this.documentReceiptDisciplinaryAccountId = documentReceiptDisciplinaryAccountId;
    }

    public Long getDraftMainAccountId() {
        return this.draftMainAccountId;
    }

    public DraftAccountInfo draftMainAccountId(Long draftMainAccountId) {
        this.setDraftMainAccountId(draftMainAccountId);
        return this;
    }

    public void setDraftMainAccountId(Long draftMainAccountId) {
        this.draftMainAccountId = draftMainAccountId;
    }

    public Long getInsuranceCostAccountId() {
        return this.insuranceCostAccountId;
    }

    public DraftAccountInfo insuranceCostAccountId(Long insuranceCostAccountId) {
        this.setInsuranceCostAccountId(insuranceCostAccountId);
        return this;
    }

    public void setInsuranceCostAccountId(Long insuranceCostAccountId) {
        this.insuranceCostAccountId = insuranceCostAccountId;
    }

    public Long getJustificationDisciplinaryAccountId() {
        return this.justificationDisciplinaryAccountId;
    }

    public DraftAccountInfo justificationDisciplinaryAccountId(Long justificationDisciplinaryAccountId) {
        this.setJustificationDisciplinaryAccountId(justificationDisciplinaryAccountId);
        return this;
    }

    public void setJustificationDisciplinaryAccountId(Long justificationDisciplinaryAccountId) {
        this.justificationDisciplinaryAccountId = justificationDisciplinaryAccountId;
    }

    public Long getOpenDraftDisciplinaryAccountId() {
        return this.openDraftDisciplinaryAccountId;
    }

    public DraftAccountInfo openDraftDisciplinaryAccountId(Long openDraftDisciplinaryAccountId) {
        this.setOpenDraftDisciplinaryAccountId(openDraftDisciplinaryAccountId);
        return this;
    }

    public void setOpenDraftDisciplinaryAccountId(Long openDraftDisciplinaryAccountId) {
        this.openDraftDisciplinaryAccountId = openDraftDisciplinaryAccountId;
    }

    public Long getOtherCostsAccountId() {
        return this.otherCostsAccountId;
    }

    public DraftAccountInfo otherCostsAccountId(Long otherCostsAccountId) {
        this.setOtherCostsAccountId(otherCostsAccountId);
        return this;
    }

    public void setOtherCostsAccountId(Long otherCostsAccountId) {
        this.otherCostsAccountId = otherCostsAccountId;
    }

    public Long getPostSwiftCostsAccountId() {
        return this.postSwiftCostsAccountId;
    }

    public DraftAccountInfo postSwiftCostsAccountId(Long postSwiftCostsAccountId) {
        this.setPostSwiftCostsAccountId(postSwiftCostsAccountId);
        return this;
    }

    public void setPostSwiftCostsAccountId(Long postSwiftCostsAccountId) {
        this.postSwiftCostsAccountId = postSwiftCostsAccountId;
    }

    public Long getAmountDeductionAccountId() {
        return this.amountDeductionAccountId;
    }

    public DraftAccountInfo amountDeductionAccountId(Long amountDeductionAccountId) {
        this.setAmountDeductionAccountId(amountDeductionAccountId);
        return this;
    }

    public void setAmountDeductionAccountId(Long amountDeductionAccountId) {
        this.amountDeductionAccountId = amountDeductionAccountId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftAccountInfo)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftAccountInfo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftAccountInfo{" +
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
