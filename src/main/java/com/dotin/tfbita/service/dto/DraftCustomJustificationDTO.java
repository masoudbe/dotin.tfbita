package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftCustomJustification} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftCustomJustificationDTO implements Serializable {

    private Long id;

    private Set<DraftReceiptDTO> draftReceipts = new HashSet<>();

    private DraftDTO draft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DraftReceiptDTO> getDraftReceipts() {
        return draftReceipts;
    }

    public void setDraftReceipts(Set<DraftReceiptDTO> draftReceipts) {
        this.draftReceipts = draftReceipts;
    }

    public DraftDTO getDraft() {
        return draft;
    }

    public void setDraft(DraftDTO draft) {
        this.draft = draft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftCustomJustificationDTO)) {
            return false;
        }

        DraftCustomJustificationDTO draftCustomJustificationDTO = (DraftCustomJustificationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftCustomJustificationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftCustomJustificationDTO{" +
            "id=" + getId() +
            ", draftReceipts=" + getDraftReceipts() +
            ", draft=" + getDraft() +
            "}";
    }
}
