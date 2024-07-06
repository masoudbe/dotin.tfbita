package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.SuggestedSanctionInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SuggestedSanctionInfoDTO implements Serializable {

    private Long id;

    private String sanctionSerial;

    private String personnelCode;

    private Set<DraftDTO> drafts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSanctionSerial() {
        return sanctionSerial;
    }

    public void setSanctionSerial(String sanctionSerial) {
        this.sanctionSerial = sanctionSerial;
    }

    public String getPersonnelCode() {
        return personnelCode;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
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
        if (!(o instanceof SuggestedSanctionInfoDTO)) {
            return false;
        }

        SuggestedSanctionInfoDTO suggestedSanctionInfoDTO = (SuggestedSanctionInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, suggestedSanctionInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SuggestedSanctionInfoDTO{" +
            "id=" + getId() +
            ", sanctionSerial='" + getSanctionSerial() + "'" +
            ", personnelCode='" + getPersonnelCode() + "'" +
            ", drafts=" + getDrafts() +
            "}";
    }
}
