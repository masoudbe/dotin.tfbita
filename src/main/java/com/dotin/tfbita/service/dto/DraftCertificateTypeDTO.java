package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftCertificateType} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftCertificateTypeDTO implements Serializable {

    private Long id;

    private Boolean mandatory;

    private DraftRequestTypeDTO draftRequestType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public DraftRequestTypeDTO getDraftRequestType() {
        return draftRequestType;
    }

    public void setDraftRequestType(DraftRequestTypeDTO draftRequestType) {
        this.draftRequestType = draftRequestType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftCertificateTypeDTO)) {
            return false;
        }

        DraftCertificateTypeDTO draftCertificateTypeDTO = (DraftCertificateTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftCertificateTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftCertificateTypeDTO{" +
            "id=" + getId() +
            ", mandatory='" + getMandatory() + "'" +
            ", draftRequestType=" + getDraftRequestType() +
            "}";
    }
}
