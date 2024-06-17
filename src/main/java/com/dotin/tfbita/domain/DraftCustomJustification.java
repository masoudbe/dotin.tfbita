package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DraftCustomJustification.
 */
@Entity
@Table(name = "draft_custom_justification")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftCustomJustification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_draft_custom_justification__draft_receipts",
        joinColumns = @JoinColumn(name = "draft_custom_justification_id"),
        inverseJoinColumns = @JoinColumn(name = "draft_receipts_id")
    )
    @JsonIgnoreProperties(value = { "products", "receipts", "draftCustomJustifications" }, allowSetters = true)
    private Set<DraftReceipt> draftReceipts = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "draftReceipts",
            "draftUsedAssurances",
            "draftFactors",
            "draftCustomJustifications",
            "draftExtends",
            "draftTaxes",
            "draftStatusInfos",
            "customs",
            "products",
            "services",
        },
        allowSetters = true
    )
    private Draft draftJustifications;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DraftCustomJustification id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DraftReceipt> getDraftReceipts() {
        return this.draftReceipts;
    }

    public void setDraftReceipts(Set<DraftReceipt> draftReceipts) {
        this.draftReceipts = draftReceipts;
    }

    public DraftCustomJustification draftReceipts(Set<DraftReceipt> draftReceipts) {
        this.setDraftReceipts(draftReceipts);
        return this;
    }

    public DraftCustomJustification addDraftReceipts(DraftReceipt draftReceipt) {
        this.draftReceipts.add(draftReceipt);
        return this;
    }

    public DraftCustomJustification removeDraftReceipts(DraftReceipt draftReceipt) {
        this.draftReceipts.remove(draftReceipt);
        return this;
    }

    public Draft getDraftJustifications() {
        return this.draftJustifications;
    }

    public void setDraftJustifications(Draft draft) {
        this.draftJustifications = draft;
    }

    public DraftCustomJustification draftJustifications(Draft draft) {
        this.setDraftJustifications(draft);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftCustomJustification)) {
            return false;
        }
        return getId() != null && getId().equals(((DraftCustomJustification) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftCustomJustification{" +
            "id=" + getId() +
            "}";
    }
}
