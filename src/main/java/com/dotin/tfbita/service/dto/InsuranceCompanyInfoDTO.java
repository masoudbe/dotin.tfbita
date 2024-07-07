package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.InsuranceCompanyInfo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InsuranceCompanyInfoDTO implements Serializable {

    private Long id;

    private String modificationDate;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsuranceCompanyInfoDTO)) {
            return false;
        }

        InsuranceCompanyInfoDTO insuranceCompanyInfoDTO = (InsuranceCompanyInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, insuranceCompanyInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InsuranceCompanyInfoDTO{" +
            "id=" + getId() +
            ", modificationDate='" + getModificationDate() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
