package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.ServiceTariff} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServiceTariffDTO implements Serializable {

    private Long id;

    private String code;

    private String title;

    private Set<DraftDTO> drafts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(o instanceof ServiceTariffDTO)) {
            return false;
        }

        ServiceTariffDTO serviceTariffDTO = (ServiceTariffDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, serviceTariffDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceTariffDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", title='" + getTitle() + "'" +
            ", drafts=" + getDrafts() +
            "}";
    }
}
