package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.LongValue} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LongValueDTO implements Serializable {

    private Long id;

    private Long val;

    private Set<DraftDTO> drafts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVal() {
        return val;
    }

    public void setVal(Long val) {
        this.val = val;
    }

    public Set<DraftDTO> getDrafts() {
        return drafts;
    }

    public void setDrafts(Set<DraftDTO> drafts) {
        this.drafts = drafts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LongValueDTO)) {
            return false;
        }

        LongValueDTO longValueDTO = (LongValueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, longValueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LongValueDTO{" +
            "id=" + getId() +
            ", val=" + getVal() +
            ", drafts=" + getDrafts() +
            "}";
    }
}
