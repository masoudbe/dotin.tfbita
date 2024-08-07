package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftRequestType} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftRequestTypeDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftRequestTypeDTO)) {
            return false;
        }

        DraftRequestTypeDTO draftRequestTypeDTO = (DraftRequestTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftRequestTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftRequestTypeDTO{" +
            "id=" + getId() +
            "}";
    }
}
